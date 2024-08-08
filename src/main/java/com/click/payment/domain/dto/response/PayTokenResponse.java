package com.click.payment.domain.dto.response;

public record PayTokenResponse(
    Long payId,
    String businessName,
    String failRedirUrl,
    String successRedirUrl,
    Integer payAmount
) {
    public static PayTokenResponse from(
        Long payId,
        String businessName,
        String failRedirUrl,
        String successRedirUrl,
        Integer payAmount
    ) {
        return new PayTokenResponse(
            payId,
            businessName,
            failRedirUrl,
            successRedirUrl,
            payAmount
        );
    }
}
