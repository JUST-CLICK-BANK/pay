package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.Business;

public record SignInRequest(
    String businessKey,
    String businessPassword
) {
    public Business toEntity() {
        return Business.builder()
            .businessKey(businessKey)
            .businessPassword(businessPassword)
            .build();
    }
}
