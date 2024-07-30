package com.click.payment.domain.entity;


import com.click.payment.domain.type.PaymentState;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "PAYMENT_HISTORIES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentHistory {
    // 결재 내역 ID
    @Id
    @Column(name = "PAY_ID")
    private String payId;
    // 결제 카드 ID
    @Column(name = "CARD_ID")
    private Long cardId;
    // 주문 번호
    @Column(name = "PAY_NUM")
    private Long payNum;
    // 결제 금액
    @Column(name = "PAY_AMOUNT")
    private Integer payAmount;
    // 결제 상태
    @Column(name = "PAY_STATE")
    private PaymentState payState;
    // 금액 결제 시간
    @Column(name = "PAY_CREATE_AT")
    private LocalDateTime payCreateAt;
    // 금액 환불 시간
    @Column(name = "PAY_REFUND_AT")
    private LocalDateTime payRefundAt;
    // 가맹점 ID
    @ManyToOne
    @Column(name = "STORE_ID")
    private Store storeId;
}
