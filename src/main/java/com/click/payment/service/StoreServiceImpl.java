package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.StoreRequest;
import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.repository.AllowedRedirectRepository;
import com.click.payment.domain.repository.StoreRepository;
import jakarta.transaction.Transactional;
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

    // 가맹점 정보 수정
    @Transactional
    @Override
    public void updateStoreInfo(UUID storeId, StoreRequest storeRequest) {
        Optional<Store> byId = storeRepository.findById(storeId);
        Store store = byId.orElseThrow(
            () -> new IllegalArgumentException("Store not found"));

        store.updateInfo(storeRequest);
    }

    // Redirect Url 수정
    // TODO: 어차피 일대다 인데 굳이 수정이 필요한가? 삭제 혹은 추가 아닌가?
    @Override
    public void updateRedirectUrl(UUID storeId, String redirectUrl) {
        Optional<Store> byId = storeRepository.findById(storeId);
        Store store = byId.orElseThrow(() -> new IllegalArgumentException("Store not found"));

        AllowedRedirect redirect = allowedRedirectRepository.findByStoreId(store);

        redirect.setRedirUrl(redirectUrl);
    }

    // Redirect Url 삭제
    @Override
    public void deleteRedirectUrl(Long redirectId) {
        allowedRedirectRepository.deleteById(redirectId);
    }

    // 가맹점 폐업 시 삭제(disable = false)
    @Override
    public void deleteStore(UUID storeId) {
        Optional<Store> byId = storeRepository.findById(storeId);
        Store store = byId.orElseThrow(() -> new IllegalArgumentException("Store not found"));

        store.setStore_disable(false);
    }
}
