package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.LastStandCard;
import java.util.UUID;

public record LastStandCardResponse(
    UUID userId,
    Integer code,
    Long cardId
) {
    // card가 null인 경우
    public static LastStandCardResponse from(UUID userId, Integer code) {
        return new LastStandCardResponse(
            userId,
            code,
            null
        );
    }

    // card가 존재하는 경우
    public static LastStandCardResponse from(UUID userId, Integer code, LastStandCard card) {
        return new LastStandCardResponse(
            userId,
            code,
            card.getCardId()
        );
    }
}
