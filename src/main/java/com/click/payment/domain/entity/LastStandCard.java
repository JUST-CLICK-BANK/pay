package com.click.payment.domain.entity;

import jakarta.annotation.Nullable;
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
@Table(name = "LAST_STAND_CARD")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LastStandCard {
    // 유저 ID
    @Id
    @Column(name = "USER_ID")
    private UUID userId;

    // 카드 ID
    @Column(name = "CARD_ID")
    private Long cardId;

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
