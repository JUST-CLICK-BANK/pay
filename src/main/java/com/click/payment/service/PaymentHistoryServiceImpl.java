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
import com.click.payment.utils.JwtUtils;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Objects;
import java.util.Optional;
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

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistory> getPaymentHistories(String businessKey) {
        Business byBusinessId = businessRepository.findByBusinessKeyAndBusinessDisableIsFalse(businessKey);
        if(byBusinessId.getBusinessId() == null) throw new NullPointerException("가맹점 오류");

        return paymentHistoryRepository.findByBusiness(byBusinessId);
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(Long payId) {
        PaymentHistory paymentHistory = paymentHistoryRepository.findByPayId(payId);
        return PaymentHistoryResponse.from(paymentHistory);
    }

    // 결제 내역 생성
    @Override
    public SuccessPaymentResponse insertPaymentHistory(String businessKey, PaymentHistoryRequest req) {
        Business byBusinessKey = businessRepository.findByBusinessKeyAndBusinessDisableIsFalse(businessKey);
        if(byBusinessKey.getBusinessId() == null) throw new NullPointerException("가맹점 오류");

        UUID byBusinessId = businessRepository.getByBusinessId(businessKey);
        Business business = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(
            byBusinessId);

        PaymentHistory save = paymentHistoryRepository.save(req.toEntity(business));
        Long payId = save.getPayId();
        Integer payAmount = save.getPayAmount();

        String token = jwtUtils.generateToken(payId, business.getBusinessName(), req.failRedirUrl(), req.successRedirUrl(), payAmount);

        String appLink = String.format("exp://192.168.0.16:8081/--/path/into/app/pay/%s", token);
        return SuccessPaymentResponse.from(payId, appLink);
    }

    // 결제 상태 수정
    @Override
    @Transactional
    public void updatePaymentHistoryState(Long payId, UpdatePaymentHistoryRequest req) {
        PaymentHistory byBusinessIdAndPayId = paymentHistoryRepository.findByPayId(payId);
        if(byBusinessIdAndPayId.getPayId() == null) throw new NullPointerException("결제내역 오류");

        // TODO cardId도 추가
        if(Objects.equals(req.payState().toString(), "REFUND_COMPLETE"))
            byBusinessIdAndPayId.setPayRefundAt(LocalDateTime.now());

        byBusinessIdAndPayId.setPayState(req.payState());
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
}
