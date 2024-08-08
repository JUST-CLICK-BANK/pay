package com.click.payment.domain.repository;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, String> {

    List<PaymentHistory> findByBusiness(Business business);

//    PaymentHistory findByBusinessKeyAndPayId(Business business, Long payId);

    PaymentHistory findByPayId(Long payId);
}
