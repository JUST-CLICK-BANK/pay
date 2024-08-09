package com.click.payment.global.api;

import com.click.payment.global.dto.CardResponse;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// TODO 추후에 https://just-click.shop/graphql로 바꾸기
@FeignClient(name = "feignCard", url = "http://35.239.149.187:31982")
public interface FeignCard {

    @PostMapping(value = "/graphql", consumes = "application/json")
    CardResponse getMyCard(@RequestBody Map<String, String> query);
    // "query":"{getMyCard(1){cardId account 추가}}"
}
