package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;

public record PaymentHistoryRequest(
    Store store,
    Long cardId,
    Long payNum,
    Integer payAmount
) {
    public PaymentHistory toEntity(Store store) {
        return PaymentHistory.builder()
            .storeId(store)
            .cardId(cardId)
            .payNum(payNum)
            .payAmount(payAmount)
            .payState(PaymentState.valueOf("PAY_STANDBY"))
            .payCreateAt(LocalDateTime.now())
            .payRefundAt(null)
            .build();
    }
}
