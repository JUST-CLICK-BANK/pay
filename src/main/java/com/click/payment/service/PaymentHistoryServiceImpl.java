package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.repository.PaymentHistoryRepository;
import com.click.payment.domain.repository.BusinessRepository;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final BusinessRepository businessRepository;

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistory> getPaymentHistories(UUID businessId) {
        Business byBusinessId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId);
        if(byBusinessId.getBusinessId() == null) throw new NullPointerException("가맹점 오류");

        return paymentHistoryRepository.findByBusinessId(byBusinessId);
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(UUID businessId, Long payId) {
        Business byBusinessId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId);
        if(byBusinessId.getBusinessId() == null) throw new NullPointerException("가맹점 오류");

        PaymentHistory paymentHistory = paymentHistoryRepository.findByBusinessIdAndPayId(byBusinessId, payId);
        return PaymentHistoryResponse.from(paymentHistory);
    }

    // 결제 내역 생성
    @Override
    public void insertPaymentHistory(Business business, PaymentHistoryRequest req) {
        Business byBusinessId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(business.getBusinessId());
        if(byBusinessId.getBusinessId() == null) throw new NullPointerException("가맹점 오류");

        paymentHistoryRepository.save(req.toEntity(business));
    }

    // 결제 상태 수정
    @Override
    @Transactional
    public void updatePaymentHistoryState(UUID businessId, Long payId, UpdatePaymentHistoryRequest req) {
        Business byBusinessId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId);
        if(byBusinessId.getBusinessId() == null) throw new NullPointerException("가맹점 오류");
        PaymentHistory byBusinessIdAndPayId = paymentHistoryRepository.findByBusinessIdAndPayId(
            byBusinessId, payId);
        if(byBusinessIdAndPayId.getPayId() == null) throw new NullPointerException("결제내역 오류");

        if(Objects.equals(req.payState().toString(), "REFUND_COMPLETE"))
            byBusinessIdAndPayId.setPayRefundAt(LocalDateTime.now());

        byBusinessIdAndPayId.setPayState(req.payState());
    }
}
