package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.Store;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;

public record PaymentHistoryResponse(
    String pay_id,
    Store store_id,
    Long card_id,
    Long pay_num,
    Integer pay_amount,
    PaymentState pay_state,
    LocalDateTime pay_create_at,
    LocalDateTime pay_refund_at
) {
    public static PaymentHistoryResponse from(
        String pay_id,
        Store store_id,
        Long card_id,
        Long pay_num,
        Integer pay_amount,
        PaymentState pay_state,
        LocalDateTime pay_create_at,
        LocalDateTime pay_refund_at

    ) {
        return new PaymentHistoryResponse(
            pay_id,
            store_id,
            card_id,
            pay_num,
            pay_amount,
            pay_state,
            pay_create_at,
            pay_refund_at
        );
    }
}
