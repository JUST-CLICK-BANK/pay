package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.repository.AllowedRedirectRepository;
import com.click.payment.domain.repository.StoreRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllowedRedirectServiceImpl implements AllowedRedirectService {

    private final AllowedRedirectRepository allowedRedirectRepository;
    private final StoreRepository storeRepository;

    @Override
    public void registerUrl(RedirectUrlRequest redirectUrlRequest) {
        Optional<Store> byId = storeRepository.findById(
            UUID.fromString(redirectUrlRequest.storeId()));
        Store store = byId.orElseThrow(() -> new IllegalArgumentException("Store not found"));

        allowedRedirectRepository.save(redirectUrlRequest.toEntity(store));
    }
}
