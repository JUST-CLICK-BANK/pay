package com.click.payment.global.api;

import com.click.payment.global.dto.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignCard", url = "https://just-click.shop/api")
public interface FeignAccount {

    @GetMapping(value = "/v1/accounts/pay")
    AccountResponse getAccountAmount(@RequestParam("account") String account);
}
