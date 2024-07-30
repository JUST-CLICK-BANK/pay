package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.repository.PaymentHistoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistoryResponse> getPaymentHistories(Store storeId) {
        return paymentHistoryRepository.findByStoreId(storeId);
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(Store storeId) {
        return null;
    }

    // 결제 내역 생성
    @Override
    public void insertPaymentHistory(PaymentHistoryRequest req) {

    }

    // 결제 상태 수정
    @Override
    public void updatePaymentHistoryState(String payId, String state) {

    }
}
