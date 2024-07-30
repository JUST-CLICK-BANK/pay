package com.click.payment.controller;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.service.PaymentHistoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public List<PaymentHistory> getPaymentHistories(
        @PathVariable("storeId") Store storeId
    ) {
        return paymentHistoryService.getPaymentHistories(storeId);
    }

    // 특정 결제 내역 조회 (단일)
    @GetMapping("/{storeId}")
    public PaymentHistoryResponse getPaymentHistory(
        @PathVariable("storeId") Store storeId,
        @RequestParam("payment") String payId
    ) {
        return paymentHistoryService.getPaymentHistory(storeId, payId);
    }

    // 결제 내역 생성
    @PostMapping("/{storeId}")
    public void insertPaymentHistory(
        @PathVariable("storeId") Store storeId,
        @RequestBody PaymentHistoryRequest req
    ) {
        paymentHistoryService.insertPaymentHistory(storeId, req);
    }

    // 결제 상태 수정
    @PutMapping("/{storeId}")
    public void updatePaymentHistoryState(
        @PathVariable("storeId") Store storeId,
        @RequestParam("payment") String payId,
        @RequestBody PaymentHistoryRequest req
    ) {
        paymentHistoryService.updatePaymentHistoryState(storeId, payId, req);
    }
}
