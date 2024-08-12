package com.click.payment.global.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feignCard", url = "https://just-click.shop")
public interface FeignCard {

    @GetMapping(value = "/api/v1/cards/pay", consumes = "application/json")
    Boolean getAbleMycard(@RequestBody Long cardId);
}
