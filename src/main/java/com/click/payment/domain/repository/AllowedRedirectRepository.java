package com.click.payment.domain.repository;

import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.Store;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AllowedRedirectRepository extends JpaRepository<AllowedRedirect, Long> {

    @Query("SELECT r.redir_url FROM AllowedRedirect r WHERE r.storeId=:store")
    List<String> findRedirectUrlByStoreId(Store store);
}
