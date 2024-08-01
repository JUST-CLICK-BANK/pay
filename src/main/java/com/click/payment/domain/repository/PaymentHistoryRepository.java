package com.click.payment.domain.repository;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
    List<PaymentHistory> findByStoreId(Store store);
    PaymentHistory findByStoreIdAndPayId(Store store, Long payId);
}
