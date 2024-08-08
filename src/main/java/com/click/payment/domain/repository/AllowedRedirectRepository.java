package com.click.payment.domain.repository;

import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.Business;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AllowedRedirectRepository extends JpaRepository<AllowedRedirect, Long> {

    @Query("SELECT r.redirUrl FROM AllowedRedirect r WHERE r.business = :business")
    List<String> findRedirectUrlByBusinessId(Business business);

    @Modifying
    @Query("DELETE FROM AllowedRedirect r WHERE r.business = :business")
    void deleteByBusinessId(@Param("business") Business business);
}
