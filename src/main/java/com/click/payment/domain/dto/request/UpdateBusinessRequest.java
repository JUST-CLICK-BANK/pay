package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.Business;

public record UpdateBusinessRequest(
    String businessName,
    String businessCeo,
    String businessKey,
    String businessAccount
) {

    public Business toEntity() {
        return Business.builder()
            .businessName(businessName)
            .businessCeo(businessCeo)
            .businessKey(businessKey)
            .businessAccount(businessAccount)
            .build();
    }
}
