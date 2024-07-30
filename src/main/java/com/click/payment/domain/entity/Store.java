package com.click.payment.domain.entity;

import com.click.payment.domain.dto.request.StoreRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "STORES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    // 가맹점 ID
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "STORE_ID")
    private UUID store_id;

    // 가맹점 이름
    @Column(name = "STORE_NAME")
    @Setter
    private String store_name;

    // 가맹점 대표
    @Column(name = "STORE_CEO")
    @Setter
    private String store_ceo;

    // 가맹점 API key
    @Column(name = "STORE_KEY")
    private String store_key;

    // 연동 계좌
    @Column(name = "ACCOUNT")
    @Setter
    private String store_account;

    // 발급 일자
    @Column(name = "STORE_CREATE_AT")
    private LocalDateTime store_create_at;

    // 사용 여부
    @Column(name = "STORE_DISABLE") @Setter
    private boolean store_disable;

    public void updateInfo(StoreRequest storeRequest) {
        // 주소 수정
        if (storeRequest.storeAccount() != null) {
            this.store_account = storeRequest.storeAccount();
        }

        // 가맹점 상호 수정
        if (storeRequest.storeName() != null) {
            this.store_name = storeRequest.storeName();
        }

        // 가맹점 대표 수정
        if (storeRequest.storeCeo() != null) {
            this.store_ceo = storeRequest.storeCeo();
        }
    }
}
