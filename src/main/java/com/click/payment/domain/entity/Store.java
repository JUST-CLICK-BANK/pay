package com.click.payment.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "STORES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {
    // 가맹점 ID
    @Id
    @Column(name = "STORE_ID")
    private UUID store_id;
    // 가맹점 이름
    @Column(name = "STORE_NAME")
    private String store_name;
    // 가맹점 대표
    @Column(name = "STORE_CEO")
    private String store_ceo;
    // 가맹점 API key
    @Column(name = "STORE_KEY")
    private String store_key;
    // 연동 계좌
    @Column(name = "ACCOUNT")
    private String account;
    // 발급 일자
    @Column(name = "STORE_CREATE_AT")
    private LocalDateTime store_create_at;
    // 사용 여부
    @Column(name = "STORE_DISABLE")
    private boolean store_disable;
}
