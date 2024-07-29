package com.click.payment.service;

import com.click.payment.domain.dto.request.StoreRequest;

public interface StoreService {

    void registerStore(StoreRequest storeRequest);
}
