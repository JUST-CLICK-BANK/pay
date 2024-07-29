package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.Store;

public record RedirectUrlRequest(
    String storeId,
    String redirUrl
) {

    public AllowedRedirect toEntity(Store store) {
        return AllowedRedirect.builder()
            .redirId(null)
            .storeId(store)
            .redirUrl(redirUrl)
            .build();
    }
}
