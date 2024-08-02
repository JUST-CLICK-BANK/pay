package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.Business;
import java.time.LocalDateTime;

public record BusinessRequest(
    String businessName,
    String businessCeo,
    String businessKey,
    String businessAccount
) {

    public Business toEntity() {
        return Business.builder()
            .businessId(null)
            .businessName(businessName)
            .businessCeo(businessCeo)
            .businessKey(businessKey)
            .businessAccount(businessAccount)
            .businessCreateAt(LocalDateTime.now())
            .businessDisable(false)
            .businessPassword(String.valueOf(1234))
            .build();
    }
}
