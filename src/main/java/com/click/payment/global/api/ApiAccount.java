package com.click.payment.global.api;

import com.click.payment.global.dto.response.AccountAmountResponse;
import com.click.payment.global.dto.request.AccountMoneyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAccount {
    public final FeignAccount feignAccount;

    @Async
    public AccountAmountResponse getAccountAmount(String account) {
        return feignAccount.getAccountAmount(account);
    }

    @Async
    public void updateMoney(String userToken, AccountMoneyRequest accountMoneyRequest) {
        System.out.println("==========================================");
        System.out.println("api userToken: " + userToken);
        System.out.println("==========================================");
        feignAccount.updateMoney(userToken, accountMoneyRequest);
    }
}
