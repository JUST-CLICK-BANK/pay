package com.click.payment.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private String businessKey;
    private String businessPassword;

    public static SignInResponse from(String businessKey, String businessPassword) {
        return new SignInResponse(businessKey, businessPassword);
    }
}
