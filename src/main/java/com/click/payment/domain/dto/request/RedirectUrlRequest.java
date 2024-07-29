package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.Store;

public record RedirectUrlRequest(
    String storeId,
    String storeAccount
) {

    public AllowedRedirect toEntity(Store store) {
        return AllowedRedirect.builder()
            .redir_id(null)
            .storeId(store)
            .redir_url(storeAccount)
            .build();
    }
}
