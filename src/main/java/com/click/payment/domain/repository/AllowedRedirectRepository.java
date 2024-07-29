package com.click.payment.domain.repository;

import com.click.payment.domain.entity.AllowedRedirect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllowedRedirectRepository extends JpaRepository<AllowedRedirect, Long> {

}
