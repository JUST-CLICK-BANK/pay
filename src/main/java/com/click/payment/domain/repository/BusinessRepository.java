package com.click.payment.domain.repository;

import com.click.payment.domain.entity.Business;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    Business findByBusinessIdAndBusinessDisableIsFalse(UUID businessId);
    Business findByBusinessKeyAndBusinessDisableIsFalse(String businessKey);

    @Query("SELECT b.businessKey FROM Business b WHERE b.businessKey = :businessKey AND b.businessDisable = false")
    String findByBusinessKey(String businessKey);

    @Query("SELECT b.businessPassword FROM Business b WHERE b.businessKey = :businessKey AND b.businessDisable = false")
    String findByBusinessPassword(String businessKey);

    @Query("SELECT b.businessSalt FROM Business b WHERE b.businessKey = :businessKey AND b.businessDisable = false")
    String findByBusinessSalt(String businessKey);

    @Query("SELECT b.businessId FROM Business b WHERE b.businessKey = :businessKey AND b.businessDisable = false")
    UUID getByBusinessId(String businessKey);
}
