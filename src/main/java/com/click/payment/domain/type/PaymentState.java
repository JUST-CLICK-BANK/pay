package com.click.payment.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentState {
    PAY_STANDBY("결제 대기 중"),
    PAY_PROGRESS("결제 진행 중"),
    PAY_CANCEL("결제 취소"),
    PAY_FAILED("결제 실패"),
    PAY_COMPLETE("결제 완료"),
    REFUND_PROGRESS("환불 진행 중"),
    REFUND_COMPLETE("환불 완료");

    private final String state;
}
