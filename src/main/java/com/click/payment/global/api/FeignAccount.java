package com.click.payment.global.api;

import com.click.payment.global.dto.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignAccount", url = "https://just-click.shop")
public interface FeignAccount {

    @GetMapping(value = "/api/v1/accounts/pay")
    AccountResponse getAccountAmount(@RequestParam("account") String account);
}
