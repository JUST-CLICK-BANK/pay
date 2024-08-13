package com.click.payment.global.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feignCard", url = "https://just-click.shop/api/v1/cards")
public interface FeignCard {

    @GetMapping(value = "/pay/{cardId}", consumes = "application/json")
    Boolean getAbleMycard(@PathVariable("cardId") Long cardId);
}
