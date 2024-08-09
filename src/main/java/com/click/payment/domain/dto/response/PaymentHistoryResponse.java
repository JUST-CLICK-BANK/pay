package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentHistoryResponse(
    Long payId,
    Long cardId,
    Long payNum,
    Integer payAmount,
    PaymentState payState,
    LocalDateTime payCreateAt,
    LocalDateTime payRefundAt,
    BusinessInfoResponse businessInfo
) {
    public static PaymentHistoryResponse from(PaymentHistory paymentHistory) {
        return new PaymentHistoryResponse(
            paymentHistory.getPayId(),
            paymentHistory.getCardId(),
            paymentHistory.getPayNum(),
            paymentHistory.getPayAmount(),
            paymentHistory.getPayState(),
            paymentHistory.getPayCreateAt(),
            paymentHistory.getPayRefundAt(),
            BusinessInfoResponse.from(paymentHistory.getBusiness())
        );
    }
}
