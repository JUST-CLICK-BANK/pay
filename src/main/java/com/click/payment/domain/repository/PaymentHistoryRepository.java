package com.click.payment.domain.repository;

import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
    List<PaymentHistory> findByStoreId(Store storeId);
    PaymentHistoryResponse findByStoreIdAndPayId(Store storeId, String payId);
}
