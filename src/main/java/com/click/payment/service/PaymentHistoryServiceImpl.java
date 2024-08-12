package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PayTokenResponse;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.dto.response.SuccessPaymentResponse;
import com.click.payment.domain.dto.response.LastStandCardResponse;
import com.click.payment.domain.entity.LastStandCard;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.repository.LastStandCardRepository;
import com.click.payment.domain.repository.PaymentHistoryRepository;
import com.click.payment.domain.repository.BusinessRepository;
import com.click.payment.domain.type.PaymentState;
import com.click.payment.global.api.ApiAccount;
import com.click.payment.global.api.ApiCard;
import com.click.payment.global.dto.request.AccountMoneyRequest;
import com.click.payment.global.dto.response.AccountAmountResponse;
import com.click.payment.utils.JwtUtils;
import java.time.LocalDateTime;
import java.util.List;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final BusinessRepository businessRepository;
    private final LastStandCardRepository lastStandCardRepository;
    private final JwtUtils jwtUtils;
    private final ApiCard apiCard;
    private final ApiAccount apiAccount;

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistoryResponse> getPaymentHistories(String businessKey) {
        Business byBusinessId = businessRepository.findByBusinessKeyAndBusinessAbleIsTrue(
            businessKey);
        if (byBusinessId.getBusinessId() == null) {
            throw new NullPointerException("가맹점 오류");
        }

        return paymentHistoryRepository.findByBusiness_businessId(byBusinessId.getBusinessId())
            .stream().map(PaymentHistoryResponse::from).toList();
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(Long payId) {
        PaymentHistory paymentHistory = paymentHistoryRepository.findByPayId(payId);
        return PaymentHistoryResponse.from(paymentHistory);
    }

    // 결제 내역 생성
    @Override
    public SuccessPaymentResponse insertPaymentHistory(String businessKey,
        PaymentHistoryRequest req) {
        Business byBusinessKey = businessRepository.findByBusinessKeyAndBusinessAbleIsTrue(
            businessKey);
        if (byBusinessKey.getBusinessId() == null) {
            throw new NullPointerException("가맹점 오류");
        }

        UUID byBusinessId = businessRepository.getByBusinessId(businessKey);
        Business business = businessRepository.findByBusinessIdAndBusinessAbleIsTrue(
            byBusinessId);

        PaymentHistory save = paymentHistoryRepository.save(req.toEntity(business));
        Long payId = save.getPayId();
        Long payAmount = save.getPayAmount();

        String token = jwtUtils.generateToken(payId, business.getBusinessName(), req.failRedirUrl(),
            req.successRedirUrl(), payAmount);

        String appLink = String.format("exp://192.168.0.16:8081/--/path/into/app/pay/%s", token);
        return SuccessPaymentResponse.from(payId, appLink);
    }

    // 결제 상태 수정
    @Override
    @Transactional
    public String updatePaymentHistoryState(String userToken, Long payId,
        UpdatePaymentHistoryRequest req) {
        PaymentHistory byBusinessIdAndPayId = paymentHistoryRepository.findByPayId(payId);

        if (byBusinessIdAndPayId.getPayId() == null) {
            throw new NullPointerException("결제내역 오류");
        }

        // userToken
        UUID userId = jwtUtils.parseUserToken(userToken);

        // 카드 유효성 검사
        Boolean myCard = apiCard.getAbleMycard(req.cardId());
        if (!myCard) {
            // 카드 유효 여부가 false일 경우
            byBusinessIdAndPayId.setPayState(PaymentState.valueOf("PAY_FAILED")); // 결제 실패
            return "결제 실패";
        }

        // 계좌 유효성 검사
        AccountAmountResponse accountAmount = apiAccount.getAccountAmount(req.account());
        if (byBusinessIdAndPayId.getPayAmount() > accountAmount.amount()
            || !accountAmount.accountAble()) {
            // 계좌 금액이 부족할 경우 혹은 유효 여부가 false일 경우
            byBusinessIdAndPayId.setPayState(PaymentState.valueOf("PAY_FAILED")); // 결제 실패
            return "결제 실패";
        }

        // 상태 업데이트
        byBusinessIdAndPayId.setPayState(req.payState());
        // 결제 카드 업데이트
        byBusinessIdAndPayId.setCardId(req.cardId());
        // 마지막 결제 카드 저장
        LastStandCard lastStandCard = new LastStandCard(userId, req.cardId());
        lastStandCardRepository.save(lastStandCard);

        // 계좌 연동 (가맹점)
        AccountMoneyRequest businessUpdateMoneyReq = new AccountMoneyRequest(
            "deposit",
            byBusinessIdAndPayId.getBusiness().getBusinessAccount(),
            byBusinessIdAndPayId.getPayAmount(),
    9
        );
        apiAccount.updateMoney(businessUpdateMoneyReq);
        // 계좌 연동 (고객)
        AccountMoneyRequest customerUpdateMoneyReq = new AccountMoneyRequest(
            "transfer",
            req.account(),
            byBusinessIdAndPayId.getPayAmount(),
            3
        );
        apiAccount.updateMoney(customerUpdateMoneyReq);

        return "결제 완료";
    }

    // payToken
    @Override
    public PayTokenResponse parsePayToken(String payToken) {
        return jwtUtils.parsePayToken(payToken);
    }

    // userToken
    @Override
    public LastStandCardResponse getLastStandCard(String userToken) {
        int code;

        UUID userId = jwtUtils.parseUserToken(userToken);
        LastStandCard card = lastStandCardRepository.findByUserId(userId);

        if (card != null) {
            code = 0;
            return LastStandCardResponse.from(code, card);
        } else {
            code = 1;
            return LastStandCardResponse.from(code);
        }
    }

    @Override
    public String cancelPaymentHistory(Long payId) {
        PaymentHistory byPayId = paymentHistoryRepository.findByPayId(payId);

        byPayId.setPayState(PaymentState.valueOf("PAY_CANCEL"));

        return "결제 취소";
    }

    @Override
    public String refundPaymentHistory(Long payId) {
        PaymentHistory byPayId = paymentHistoryRepository.findByPayId(payId);

        byPayId.setPayState(PaymentState.valueOf("REFUND_COMPLETE"));
        byPayId.setPayRefundAt(LocalDateTime.now());

        return "환불 완료";
    }
}
