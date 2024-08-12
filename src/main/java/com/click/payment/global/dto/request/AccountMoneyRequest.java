package com.click.payment.global.dto.request;

public record AccountMoneyRequest(
    String accountStatus,
    String account,
    Long moneyAmount,
    Integer category
) {
}
