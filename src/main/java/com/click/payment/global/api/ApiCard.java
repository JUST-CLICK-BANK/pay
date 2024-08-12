package com.click.payment.global.api;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiCard {
    public final FeignCard feignCard;

    @Async
    public Boolean getAbleMycard(Long cardId) {
        return feignCard.getAbleMycard(cardId);
    }
}
