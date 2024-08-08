package com.click.payment.controller;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.dto.response.SuccessPaymentResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import com.click.payment.service.PaymentHistoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/paymentHistories")
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    // 전체 결제 내역 조회
    @GetMapping("/{businessKey}")
    public List<PaymentHistory> getPaymentHistories(
        @PathVariable("businessKey") String businessKey
    ) {
        return paymentHistoryService.getPaymentHistories(businessKey);
    }

    // 특정 결제 내역 조회 (단일)
    @GetMapping("/{payId}")
    public PaymentHistoryResponse getPaymentHistory(
        @PathVariable("payId") Long payId
    ) {
        return paymentHistoryService.getPaymentHistory(payId);
    }

    /**
     * 가맹점이 Pay서버로 결제 데이터 생성 요청할 때 결제 내역을 생성합니다.
     * @param paymentHistoryRequest
     */
    @PostMapping("/{business}")
    public SuccessPaymentResponse insertPaymentHistory(
        @PathVariable("business") String businessKey,
        @RequestBody PaymentHistoryRequest paymentHistoryRequest
    ) {
        return paymentHistoryService.insertPaymentHistory(businessKey, paymentHistoryRequest);
    }

    // 결제 상태 수정
    @PutMapping("/{payId}")
    public void updatePaymentHistoryState(
        @PathVariable("payId") Long payId,
        @RequestBody UpdatePaymentHistoryRequest req
    ) {
        paymentHistoryService.updatePaymentHistoryState(payId, req);
    }
}
