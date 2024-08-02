package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;

public record PaymentHistoryRequest(
    Business business,
    Long cardId,
    Long payNum,
    Integer payAmount
) {
    public PaymentHistory toEntity(Business business) {
        return PaymentHistory.builder()
            .businessId(business)
            .cardId(cardId)
            .payNum(payNum)
            .payAmount(payAmount)
            .payState(PaymentState.valueOf("PAY_STANDBY"))
            .payCreateAt(LocalDateTime.now())
            .payRefundAt(null)
            .build();
    }
}
