package com.click.payment.domain.repository;

import com.click.payment.domain.entity.Business;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    Business findByBusinessIdAndBusinessDisableIsFalse(UUID businessId);
}
