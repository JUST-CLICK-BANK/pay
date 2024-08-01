package com.click.payment.controller;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.service.PaymentHistoryService;
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
    @GetMapping("/{storeId}")
    public List<PaymentHistory> getPaymentHistories(
        @PathVariable("storeId") UUID storeId
    ) {
        return paymentHistoryService.getPaymentHistories(storeId);
    }

    // 특정 결제 내역 조회 (단일)
    @GetMapping("/{storeId}/{payId}")
    public PaymentHistoryResponse getPaymentHistory(
        @PathVariable("storeId") UUID storeId,
        @PathVariable("payId") Long payId
    ) {
        return paymentHistoryService.getPaymentHistory(storeId, payId);
    }

    // 결제 내역 생성
    @PostMapping("/{storeId}")
    public void insertPaymentHistory(
        @PathVariable("storeId") Store store,
        @RequestBody PaymentHistoryRequest req
    ) {
        paymentHistoryService.insertPaymentHistory(store, req);
    }

    // 결제 상태 수정
    @PutMapping("/{storeId}/{payId}")
    public void updatePaymentHistoryState(
        @PathVariable("storeId") UUID storeId,
        @PathVariable("payId") Long payId,
        @RequestBody UpdatePaymentHistoryRequest req
    ) {
        paymentHistoryService.updatePaymentHistoryState(storeId, payId, req);
    }
}
