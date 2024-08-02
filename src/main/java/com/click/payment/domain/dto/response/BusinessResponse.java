package com.click.payment.domain.dto.response;

import com.click.payment.domain.entity.Business;
import java.time.LocalDateTime;
import java.util.UUID;

public record BusinessResponse(
    UUID businessId,
    String businessName,
    String businessCeo,
    String businessKey,
    String businessAccount,
    LocalDateTime businessCreateAt
) {
    public static BusinessResponse from(Business business) {
        return new BusinessResponse(
            business.getBusinessId(),
            business.getBusinessName(),
            business.getBusinessCeo(),
            business.getBusinessKey(),
            business.getBusinessAccount(),
            business.getBusinessCreateAt()
        );
    }
}
