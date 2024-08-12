package com.click.payment.global.dto.response;

public record AccountAmountResponse(
    Long amount,
    Boolean accountAble
) {

    public static AccountAmountResponse from(
        Long amount,
        Boolean accountAble
    ) {
        return new AccountAmountResponse(
            amount,
            accountAble
        );
    }
}
