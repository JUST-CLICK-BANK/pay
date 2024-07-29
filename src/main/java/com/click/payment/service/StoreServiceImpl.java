package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.StoreRequest;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.repository.AllowedRedirectRepository;
import com.click.payment.domain.repository.StoreRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService, AllowedRedirectService {

    private final StoreRepository storeRepository;
    private final AllowedRedirectRepository allowedRedirectRepository;

    // 가맹점 등록
    @Override
    public void registerStore(StoreRequest storeRequest) {
        storeRepository.save(storeRequest.toEntity());
    }

    // Redirect Url 등록
    @Override
    public void registerUrl(RedirectUrlRequest redirectUrlRequest) {
        Optional<Store> byId = storeRepository.findById(
            UUID.fromString(redirectUrlRequest.storeId()));
        Store store = byId.orElseThrow(() -> new IllegalArgumentException("Store not found"));

        allowedRedirectRepository.save(redirectUrlRequest.toEntity(store));
    }

    // Redirect Url 조회
    @Override
    public List<String> getUrl(UUID storeId) {
        Optional<Store> byId = storeRepository.findById(storeId);
        Store store = byId.orElseThrow(
            () -> new IllegalArgumentException("Store not found"));
        return allowedRedirectRepository.findRedirectUrlByStoreId(store);
    }
}
