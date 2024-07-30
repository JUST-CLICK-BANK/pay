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
            .payId(pay_id)
            .storeId(store_id)
            .cardId(card_id)
            .payNum(pay_num)
            .payAmount(pay_amount)
            .payState(pay_state)
            .payCreateAt(LocalDateTime.now())
            .payRefundAt(null)
            .build();
    }
}
