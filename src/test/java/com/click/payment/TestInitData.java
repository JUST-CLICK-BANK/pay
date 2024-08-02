package com.click.payment;

import com.click.payment.domain.entity.AllowedRedirect;
import com.click.payment.domain.entity.PaymentHistory;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.type.PaymentState;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TestInitData {
    protected final PaymentHistory paymentHistory;
    protected final Business business;
    protected final AllowedRedirect allowedRedirect;

    List<PaymentHistory> paymentHistoriesTest = null;
    List<AllowedRedirect> allowedRedirectsTest = null;

    public TestInitData() {
        this.business = new Business(
            UUID.randomUUID(),
            "가맹점",
            "대표",
            "aaa",
            "1111111111",
            LocalDateTime.now(),
            false,
            String.valueOf(1234),
            paymentHistoriesTest,
            allowedRedirectsTest
        );
        this.paymentHistory = new PaymentHistory(
            1L,
            1L,
            1L,
            1000,
            PaymentState.PAY_STANDBY,
            LocalDateTime.now(),
            null,
            business
        );
        this.allowedRedirect = new AllowedRedirect(
            1L,
            "a.com",
            business
        );
    }
}
