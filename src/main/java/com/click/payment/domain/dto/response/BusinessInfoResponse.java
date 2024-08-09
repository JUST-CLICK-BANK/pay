package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.Business;
import java.util.UUID;

public record BusinessInfoResponse(
    UUID businessId,
    String businessName
) {
    public static BusinessInfoResponse from(Business business) {
        return new BusinessInfoResponse(
            business.getBusinessId(),
            business.getBusinessName()
        );
    }
}
