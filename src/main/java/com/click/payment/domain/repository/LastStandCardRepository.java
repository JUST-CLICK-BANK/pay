package com.click.payment.domain.repository;

import com.click.payment.domain.entity.LastStandCard;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LastStandCardRepository extends JpaRepository<LastStandCard, UUID> {
    LastStandCard findByUserId(UUID userId);
}
