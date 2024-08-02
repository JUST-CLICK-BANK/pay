package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;

import java.util.List;
import java.util.UUID;

public interface PaymentHistoryService {
    // 전체 결제 내역 조회
    List<PaymentHistory> getPaymentHistories(UUID businessId);

    // 특정 결제 내역 조회 (단일)
    PaymentHistoryResponse getPaymentHistory(UUID businessId, Long payId);

    // 결제 내역 생성
    void insertPaymentHistory(Business business, PaymentHistoryRequest req);

    // 결제 상태 수정
    void updatePaymentHistoryState(UUID businessId, Long payId, UpdatePaymentHistoryRequest req);
}
