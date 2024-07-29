package com.click.payment.service;

import com.click.payment.domain.dto.request.StoreRequest;
import com.click.payment.domain.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public void registerStore(StoreRequest storeRequest) {
        storeRepository.save(storeRequest.toEntity());
    }
}
