package com.click.payment.global.dto.request;

public record UpdateMoneyRequest(
    String accountStatus,
    String account,
    Long moneyAmount,
    Integer category
) {
}
