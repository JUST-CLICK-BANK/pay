package com.click.payment.global.dto;

public record AccountResponse(
    Long accMoneyAmount,
    Boolean accAble
) {

    public static AccountResponse from(
        Long accMoneyAmount,
        Boolean accAble
    ) {
        return new AccountResponse(
            accMoneyAmount,
            accAble
        );
    }
}
