package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PayTokenResponse;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.dto.response.SuccessPaymentResponse;
import com.click.payment.domain.dto.response.LastStandCardResponse;
import com.click.payment.domain.entity.PaymentHistory;

import java.util.List;

public interface PaymentHistoryService {
    // 전체 결제 내역 조회
    List<PaymentHistoryResponse> getPaymentHistories(String businessKey);

    // 특정 결제 내역 조회 (단일)
    PaymentHistoryResponse getPaymentHistory(Long payId);

    // 결제 내역 생성
    SuccessPaymentResponse insertPaymentHistory(String businessKey, PaymentHistoryRequest req);

    // 결제 상태 수정
    String updatePaymentHistoryState(String userToken, Long payId, UpdatePaymentHistoryRequest req);

    // payToken
    PayTokenResponse parsePayToken(String payToken);

    // userToken
    LastStandCardResponse getLastStandCard(String userToken);

    // 결제 취소
    String cancelPaymentHistory(Long payId);

    // 환불
    String refundPaymentHistory(Long payId);
}
