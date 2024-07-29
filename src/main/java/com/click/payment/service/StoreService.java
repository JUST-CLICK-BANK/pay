package com.click.payment.service;

import com.click.payment.domain.dto.request.StoreRequest;
import java.util.UUID;

public interface StoreService {

    void registerStore(StoreRequest storeRequest);

    void updateStoreInfo(UUID storeId, StoreRequest storeRequest);

    void updateRedirectUrl(UUID storeId, String redirectUrl);

    void deleteRedirectUrl(Long redirectId);

    void deleteStore(UUID storeId);
}
