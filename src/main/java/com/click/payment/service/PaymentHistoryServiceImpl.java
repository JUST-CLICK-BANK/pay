package com.click.payment.service;

import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.repository.PaymentHistoryRepository;
import com.click.payment.domain.repository.StoreRepository;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final StoreRepository storeRepository;

    // 전체 결제 내역 조회
    @Override
    public List<PaymentHistory> getPaymentHistories(UUID storeId) {
        Store byStoreId = storeRepository.findByStoreId(storeId);
        if(byStoreId.getStoreId() == null) throw new NullPointerException("가맹점 오류");

        return paymentHistoryRepository.findByStoreId(byStoreId);
    }

    // 특정 결제 내역 조회 (단일)
    @Override
    public PaymentHistoryResponse getPaymentHistory(UUID storeId, Long payId) {
        Store byStoreId = storeRepository.findByStoreId(storeId);
        if(byStoreId.getStoreId() == null) throw new NullPointerException("가맹점 오류");

        PaymentHistory paymentHistory = paymentHistoryRepository.findByStoreIdAndPayId(byStoreId, payId);
        return PaymentHistoryResponse.from(paymentHistory);
    }

    // 결제 내역 생성
    @Override
    public void insertPaymentHistory(Store store, PaymentHistoryRequest req) {
        Store byStoreId = storeRepository.findByStoreId(store.getStoreId());
        if(byStoreId.getStoreId() == null) throw new NullPointerException("가맹점 오류");

        paymentHistoryRepository.save(req.toEntity(store));
    }

    // 결제 상태 수정
    @Override
    @Transactional
    public void updatePaymentHistoryState(UUID storeId, Long payId, UpdatePaymentHistoryRequest req) {
        Store byStoreId = storeRepository.findByStoreId(storeId);
        if(byStoreId.getStoreId() == null) throw new NullPointerException("가맹점 오류");
        PaymentHistory byStoreIdAndPayId = paymentHistoryRepository.findByStoreIdAndPayId(byStoreId, payId);
        if(byStoreIdAndPayId.getPayId() == null) throw new NullPointerException("결제내역 오류");

        if(Objects.equals(req.payState().toString(), "REFUND_COMPLETE"))
            byStoreIdAndPayId.setPayRefundAt(LocalDateTime.now());

        byStoreIdAndPayId.setPayState(req.payState());
    }
}
