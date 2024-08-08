package com.click.payment.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessPaymentResponse {

    private int code;
    private Long payId;
    private String appLink;

    public static SuccessPaymentResponse from(Long payId, String appLink) {
        return new SuccessPaymentResponse(0, payId, appLink);
    }
}