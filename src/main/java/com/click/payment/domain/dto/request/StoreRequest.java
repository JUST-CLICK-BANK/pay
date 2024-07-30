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
            .store_id(null)
            .store_name(storeName)
            .store_ceo(storeCeo)
            .store_key(storeKey)
            .store_account(storeAccount)
            .store_create_at(LocalDateTime.now())
            .store_disable(true)
            .build();
    }
}
