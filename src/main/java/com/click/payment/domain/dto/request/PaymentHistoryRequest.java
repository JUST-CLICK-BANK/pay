package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;

public record PaymentHistoryRequest(
    Long payNum,
    Integer payAmount,
    String failRedirUrl,
    String successRedirUrl
) {
    public PaymentHistory toEntity(Business business) {
        return PaymentHistory.builder()
            .business(business)
            .cardId(null)
            .payNum(payNum)
            .payAmount(payAmount)
            .payState(PaymentState.valueOf("PAY_STANDBY"))
            .payCreateAt(LocalDateTime.now())
            .payRefundAt(null)
            .build();
    }
}
