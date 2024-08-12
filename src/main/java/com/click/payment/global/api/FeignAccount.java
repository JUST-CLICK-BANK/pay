package com.click.payment.global.api;

import com.click.payment.global.dto.request.AccountMoneyRequest;
import com.click.payment.global.dto.response.AccountAmountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "feignAccount", url = "https://just-click.shop")
public interface FeignAccount {

    // 계좌 검증
    @GetMapping(value = "/api/v1/accounts/pay")
    AccountAmountResponse getAccountAmount(@RequestParam("account") String account);

    // 계좌 입금 & 출금
    @GetMapping(value = "/api/v1/accounts/amount")
    void updateMoney(
        @RequestBody AccountMoneyRequest accountMoneyRequest
    );

}
