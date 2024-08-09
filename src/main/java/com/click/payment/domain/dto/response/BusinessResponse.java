package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.Business;

public record BusinessResponse(
    String businessName,
    String businessCeo,
    String businessAccount
) {
    public static BusinessResponse from(Business business) {
        return new BusinessResponse(
            business.getBusinessName(),
            business.getBusinessCeo(),
            business.getBusinessAccount()
        );
    }
}
