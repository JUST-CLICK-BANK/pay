package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;

public record UpdatePaymentHistoryRequest(
        PaymentState payState
) {
    public PaymentHistory toEntity(LocalDateTime payRefundAt) {
        return PaymentHistory.builder()
                .payState(payState)
                .payRefundAt(payRefundAt)
                .build();
    }
}
