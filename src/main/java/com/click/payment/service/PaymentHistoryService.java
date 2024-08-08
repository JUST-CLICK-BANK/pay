package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.dto.response.SuccessPaymentResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;

import java.util.List;
import java.util.UUID;

public interface PaymentHistoryService {
    // 전체 결제 내역 조회
    List<PaymentHistory> getPaymentHistories(String businessKey);

    // 특정 결제 내역 조회 (단일)
    PaymentHistoryResponse getPaymentHistory(Long payId);

    // 결제 내역 생성
    SuccessPaymentResponse insertPaymentHistory(String businessKey, PaymentHistoryRequest req);

    // 결제 상태 수정
    void updatePaymentHistoryState(Long payId, UpdatePaymentHistoryRequest req);
}
