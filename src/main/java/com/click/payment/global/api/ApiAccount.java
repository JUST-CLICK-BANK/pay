package com.click.payment.global.api;

import com.click.payment.global.dto.response.AccountResponse;
import com.click.payment.global.dto.request.UpdateMoneyRequest;
import com.click.payment.global.dto.response.UpdateMoneyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiAccount {
    public final FeignAccount feignAccount;

    @Async
    public AccountResponse getAccountAmount(String account) {
        return feignAccount.getAccountAmount(account);
    }

    @Async
    public UpdateMoneyResponse updateMoney(UpdateMoneyRequest updateMoneyRequest) {
        return feignAccount.updateMoney(updateMoneyRequest);
    }
}
