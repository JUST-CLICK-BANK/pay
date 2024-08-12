package com.click.payment.global.dto.response;

public record UpdateMoneyResponse(
    String accountStatus,
    String account,
    Long moneyAmount,
    Integer category
) {

    public static UpdateMoneyResponse from(
        String accountStatus,
        String account,
        Long moneyAmount,
        Integer category
    ) {
        return new UpdateMoneyResponse(
            accountStatus,
            account,
            moneyAmount,
            category
        );
    }
}
