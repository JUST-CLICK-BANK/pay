package com.click.payment.domain.repository;

import com.click.payment.domain.dto.response.PaymentHistoryResponse;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {

    List<PaymentHistory> findByBusiness_businessId(UUID business_businessId);

    PaymentHistory findByPayId(Long payId);
}
