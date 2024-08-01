package com.click.payment.domain.dto.request;

import com.click.payment.domain.entity.Store;
import java.time.LocalDateTime;

public record StoreRequest(
    String storeName,
    String storeCeo,
    String storeKey,
    String storeAccount
) {

    public Store toEntity() {
        return Store.builder()
            .storeId(null)
            .storeName(storeName)
            .storeCeo(storeCeo)
            .storeKey(storeKey)
            .storeAccount(storeAccount)
            .storeCreateAt(LocalDateTime.now())
            .storeDisable(true)
            .build();
    }
}
