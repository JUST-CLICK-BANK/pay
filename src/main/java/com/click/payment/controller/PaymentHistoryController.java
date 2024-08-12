package com.click.payment.controller;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PayTokenResponse;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.dto.response.SuccessPaymentResponse;
import com.click.payment.domain.dto.response.LastStandCardResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.service.PaymentHistoryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-histories")
public class PaymentHistoryController {

    private final PaymentHistoryService paymentHistoryService;

    // 전체 결제 내역 조회
    @GetMapping("/all/{businessKey}")
    public List<PaymentHistoryResponse> getPaymentHistories(
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

    /**
     * 결제 내역 정보(카드, 결제 상태)를 수정합니다.
     * @param payId
     * @param updatePaymentHistoryRequest
     * @return String
     */
    @PutMapping("/{payId}")
    public String updatePaymentHistoryState(
        @RequestHeader("Authorization") String userToken,
        @PathVariable("payId") Long payId,
        @RequestBody UpdatePaymentHistoryRequest updatePaymentHistoryRequest
    ) {
        return paymentHistoryService.updatePaymentHistoryState(userToken, payId, updatePaymentHistoryRequest);
    }

    /**
     * payToken을 파싱합니다. (payId, businessName, failRedirUrl, successRedirUrl, payAmount)
     * @param payToken
     * @return PayTokenResponse
     */
    @GetMapping("/pay-token")
    public PayTokenResponse parsePayToken(
        @RequestHeader("Authorization") String payToken
    ) {
        return paymentHistoryService.parsePayToken(payToken);
    }

    /**
     * userToken을 파싱하고 (userId) <br/>
     * 마지막 카드 결제를 반환해 줍니다. <br/>
     * 성공 시 - code: 0, cardId: Long <br/>
     * 실패 시 - code: 1, cardId: null
     * @param userToken
     * @return UserTokenResponse
     */
    @GetMapping("/last-card")
    public LastStandCardResponse getUser(
        @RequestHeader("Authorization") String userToken
    ) {
        return paymentHistoryService.getLastStandCard(userToken);
    }

    @PutMapping("/{payId}/cancel")
    public String cancelPaymentHistory(
        @PathVariable("payId") Long payId
    ) {
        return paymentHistoryService.cancelPaymentHistory(payId);
    }

    @PutMapping("/{payId}/refund")
    public String refundPaymentHistory(
        @PathVariable("payId") Long payId
    ) {
        return paymentHistoryService.refundPaymentHistory(payId);
    }
}
