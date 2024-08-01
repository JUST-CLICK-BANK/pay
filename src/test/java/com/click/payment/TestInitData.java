package com.click.payment;

import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Store;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TestInitData {
    protected final PaymentHistory paymentHistory;
    protected final Store store;

    List<PaymentHistory> paymentHistoryTest = null;

    public TestInitData() {
        this.store = new Store(
            UUID.randomUUID(),
            "가맹점",
            "대표",
            "aaa",
            "1111111111",
            LocalDateTime.now(),
            true,
            paymentHistoryTest
        );
        this.paymentHistory = new PaymentHistory(
            1L,
            1L,
            1L,
            1000,
            PaymentState.PAY_STANDBY,
            LocalDateTime.now(),
            null,
            store
        );
    }
}
