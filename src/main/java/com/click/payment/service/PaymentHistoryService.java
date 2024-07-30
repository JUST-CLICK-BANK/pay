package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import java.util.List;
import java.util.UUID;

public interface PaymentHistoryService {
    // 전체 결제 내역 조회
    List<PaymentHistoryResponse> getPaymentHistories(UUID storeId);

    // 특정 결제 내역 조회 (단일)
    PaymentHistoryResponse getPaymentHistory(UUID storeId);

    // 결제 내역 생성
    void insertPaymentHistory(PaymentHistoryRequest req);

    // 결제 상태 수정
    void updatePaymentHistoryState(String payId, String state);
}
