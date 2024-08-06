package com.click.payment.domain.dto.request;

import com.click.payment.Util.PasswordUtils;
import com.click.payment.domain.entity.Business;
import java.time.LocalDateTime;

public record BusinessRequest(
    String businessName,
    String businessCeo,
    String businessAccount,
    String businessPassword
) {

    public Business toEntity(String businessKey, PasswordUtils passwordUtils) {
        String salt = passwordUtils.generateSalt();

        return Business.builder()
            .businessId(null)
            .businessName(businessName)
            .businessCeo(businessCeo)
            .businessKey(businessKey)
            .businessAccount(businessAccount)
            .businessCreateAt(LocalDateTime.now())
            .businessDisable(false)
            .businessPassword(passwordUtils.passwordHashing(businessPassword, salt))
            .build();
    }
}
