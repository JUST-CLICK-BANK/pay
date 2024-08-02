package com.click.payment.domain.entity;

import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "BUSINESS")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Business {
    // 가맹점 ID
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "BUSINESS_ID")
    private UUID businessId;

    // 가맹점 이름
    @Column(name = "BUSINESS_NAME")
    @Setter
    private String businessName;

    // 가맹점 대표
    @Column(name = "BUSINESS_CEO")
    @Setter
    private String businessCeo;

    // 가맹점 API key
    @Column(name = "BUSINESS_KEY")
    @Setter
    private String businessKey;

    // 연동 계좌
    @Column(name = "BUSINESS_ACCOUNT")
    @Setter
    private String businessAccount;

    // 발급 일자
    @Column(name = "BUSINESS_CREATE_AT")
    private LocalDateTime businessCreateAt;

    // 사용 여부
    @Column(name = "BUSINESS_DISABLE")
    @Setter
    private boolean businessDisable;

    // B2B 비밀번호
    @Column(name = "BUSINESS_PASSWORD")
    private String businessPassword;

    @OneToMany(mappedBy = "businessId")
    private List<PaymentHistory> paymentHistories;

    @OneToMany(mappedBy = "businessId")
    private List<AllowedRedirect> allowedRedirects;

    public void updateInfo(UpdateBusinessRequest updateBusinessRequest) {
        // 주소 수정
        if (updateBusinessRequest.businessAccount() != null) {
            this.businessAccount = updateBusinessRequest.businessAccount();
        }

        // 가맹점 상호 수정
        if (updateBusinessRequest.businessName() != null) {
            this.businessName = updateBusinessRequest.businessName();
        }

        // 가맹점 대표 수정
        if (updateBusinessRequest.businessCeo() != null) {
            this.businessCeo = updateBusinessRequest.businessCeo();
        }
    }
}
