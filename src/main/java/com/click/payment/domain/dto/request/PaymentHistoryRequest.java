package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;

public record PaymentHistoryRequest(
    String pay_id,
    Store store_id,
    Long card_id,
    Long pay_num,
    Integer pay_amount,
    PaymentState pay_state
) {
    public PaymentHistory toEntity() {
        return PaymentHistory.builder()
            .pay_id(pay_id)
            .store_id(store_id)
            .card_id(card_id)
            .pay_num(pay_num)
            .pay_amount(pay_amount)
            .pay_state(pay_state)
            .pay_create_at(LocalDateTime.now())
            .pay_refund_at(null)
            .build();
    }
}
