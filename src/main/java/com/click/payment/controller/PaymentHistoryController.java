package com.click.payment.controller;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.service.PaymentHistoryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/paymentHistories")
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    // 전체 결제 내역 조회
    @GetMapping("/{storeId}")
    public List<PaymentHistoryResponse> getPaymentHistories(
        @PathVariable("storeId") UUID storeId
    ) {
        return paymentHistoryService.getPaymentHistories(storeId);
    }

    // 특정 결제 내역 조회 (단일)
    @GetMapping("/{storeId}")
    public PaymentHistoryResponse getPaymentHistory(
        @PathVariable("storeId") UUID storeId
    ) {
        return paymentHistoryService.getPaymentHistory(storeId);
    }

    // 결제 내역 생성
    @PostMapping
    public void insertPaymentHistory(
        @RequestBody PaymentHistoryRequest req
    ) {
        paymentHistoryService.insertPaymentHistory(req);
    }

    // 결제 상태 수정
    @PutMapping("/{payId}")
    public void updatePaymentHistoryState(
        @PathVariable("payId") String payId,
        @RequestParam("state") String state
    ) {
        paymentHistoryService.updatePaymentHistoryState(payId, state);
    }
}
