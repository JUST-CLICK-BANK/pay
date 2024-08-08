package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.LastStandCard;

public record LastStandCardResponse(
    Integer code,
    Long cardId
) {
    // card가 null인 경우
    public static LastStandCardResponse from(Integer code) {
        return new LastStandCardResponse(
            code,
            null
        );
    }

    // card가 존재하는 경우
    public static LastStandCardResponse from(Integer code, LastStandCard card) {
        return new LastStandCardResponse(
            code,
            card.getCardId()
        );
    }
}
