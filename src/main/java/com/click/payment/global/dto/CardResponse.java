package com.click.payment.global.dto;

public record CardResponse(
    Boolean cardAble
) {

    public static CardResponse from(Boolean cardAble) {
        return new CardResponse(cardAble);
    }
}
