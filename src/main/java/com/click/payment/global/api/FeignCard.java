package com.click.payment.global.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feignCard", url = "https://just-click.shop")
public interface FeignCard {

    @GetMapping(value = "/api/v1/cards/pay/{cardId}", consumes = "application/json")
    Boolean getAbleMycard(@PathVariable("cardId") Long cardId);
}
