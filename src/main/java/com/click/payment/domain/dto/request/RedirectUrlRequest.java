package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.Business;

public record RedirectUrlRequest(
    String businessId,
    String redirUrl
) {

    public AllowedRedirect toEntity(Business business) {
        return AllowedRedirect.builder()
            .redirId(null)
            .businessId(business)
            .redirUrl(redirUrl)
            .build();
    }
}
