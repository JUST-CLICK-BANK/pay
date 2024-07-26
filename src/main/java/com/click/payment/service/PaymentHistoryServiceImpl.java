package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistoryResponse> getPaymentHistories(UUID storeId) {
        return List.of();
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(UUID storeId) {
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
