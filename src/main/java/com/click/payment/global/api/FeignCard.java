package com.click.payment.global.api;

import com.click.payment.global.dto.response.CardResponse;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feignCard", url = "https://just-click.shop")
public interface FeignCard {

    @PostMapping(value = "/graphql", consumes = "application/json")
    CardResponse getMyCard(@RequestBody Map<String, String> query);
    // "query":"query { getMyCard(cardId: 5) { cardAble } }"
}
