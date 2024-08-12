package com.click.payment.global.api;

import com.click.payment.global.dto.response.CardResponse;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiCard {
    public final FeignCard feignCard;

    @Async
    public CardResponse getMyCard(Map<String, String> query) {
        return feignCard.getMyCard(query);
    }
}
