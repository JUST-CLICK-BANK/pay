package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.type.PaymentState;

public record UpdatePaymentHistoryRequest(
        PaymentState pay_state
) {
    public PaymentHistory toEntity() {
        return PaymentHistory.builder()
                .payState(pay_state)
                .build();
    }
}
