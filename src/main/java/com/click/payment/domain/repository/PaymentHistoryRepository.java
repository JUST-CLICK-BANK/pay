package com.click.payment.domain.repository;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {
    List<PaymentHistory> findByBusinessId(Business business);
    PaymentHistory findByBusinessIdAndPayId(Business business, Long payId);
}
