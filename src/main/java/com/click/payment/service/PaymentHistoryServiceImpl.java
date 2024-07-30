package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.repository.PaymentHistoryRepository;
import java.util.List;

import com.click.payment.domain.type.PaymentState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistory> getPaymentHistories(Store storeId) {
        return paymentHistoryRepository.findByStoreId(storeId);
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(Store storeId, String payId) {
        return paymentHistoryRepository.findByStoreIdAndPayId(storeId, payId);
    }

    // 결제 내역 생성
    @Override
    public void insertPaymentHistory(Store storeId, PaymentHistoryRequest req) {
        List<PaymentHistory> byStoreId = paymentHistoryRepository.findByStoreId(storeId);
        if(byStoreId.isEmpty()) throw new IllegalArgumentException("가맹점 오류");

        paymentHistoryRepository.save(req.toEntity());
    }

    // 결제 상태 수정
    @Override
    public void updatePaymentHistoryState(Store storeId, String payId, PaymentHistoryRequest req) {
        List<PaymentHistory> byStoreId = paymentHistoryRepository.findByStoreId(storeId);
        if(byStoreId.isEmpty()) throw new IllegalArgumentException("가맹점 오류");
        PaymentHistoryResponse byStoreIdAndPayId = paymentHistoryRepository.findByStoreIdAndPayId(storeId, payId);
        if(byStoreIdAndPayId.pay_id().isEmpty()) throw new IllegalArgumentException("결제내역 오류");

        paymentHistoryRepository.save(req.toEntity());
    }
}
