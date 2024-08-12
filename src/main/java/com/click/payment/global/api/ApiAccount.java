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
    public AccountAmountResponse getAccountAmount(String userToken, String account) {
        return feignAccount.getAccountAmount(userToken, account);
    }

    @Async
    public void updateMoney(String userToken, AccountMoneyRequest accountMoneyRequest) {
        feignAccount.updateMoney(userToken, accountMoneyRequest);
    }
}
