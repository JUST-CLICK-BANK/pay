package com.click.payment.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.click.payment.TestInitData;
import com.click.payment.domain.dto.request.PaymentHistoryRequest;
import com.click.payment.domain.dto.request.UpdatePaymentHistoryRequest;
import com.click.payment.domain.repository.PaymentHistoryRepository;
import com.click.payment.domain.repository.BusinessRepository;
import com.click.payment.domain.type.PaymentState;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentHistoryServiceTest extends TestInitData {
    @InjectMocks
    private PaymentHistoryServiceImpl paymentHistoryService;
    @Mock
    PaymentHistoryRepository paymentHistoryRepository;
    @Mock
    BusinessRepository businessRepository;

    UUID businessId = paymentHistory.getBusinessId().getBusinessId();
    Long payId = paymentHistory.getPayId();
    UUID randomId = UUID.randomUUID();

    @Nested
    class getPaymentHistories {
        @Test
        void 성공_가맹점의_전체_결제_내역_조회함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(business.getBusinessId()))
                    .willReturn(business);
            BDDMockito.given(paymentHistoryRepository.findByBusinessId(paymentHistory.getBusinessId()))
                .willReturn(List.of(paymentHistory));

            // when
            paymentHistoryService.getPaymentHistories(paymentHistory.getBusinessId().getBusinessId());

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.times(1))
                .findByBusinessId(paymentHistory.getBusinessId());
        }

        @Test
        void 실패_가맹점이_유효하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(randomId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> paymentHistoryService.getPaymentHistories(randomId));

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.never())
                .findByBusinessId(business);
        }
    }

    @Nested
    class getPaymentHistory {
        @Test
        void 성공_가맹점의_특정_결제_내역_조회함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(business.getBusinessId()))
                .willReturn(business);
            BDDMockito.given(paymentHistoryRepository.findByBusinessIdAndPayId(business, payId))
                .willReturn(paymentHistory);

            // when
            paymentHistoryService.getPaymentHistory(businessId, payId);

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.times(1))
                .findByBusinessIdAndPayId(business, payId);
        }

        @Test
        void 실패_가맹점이_유효하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(randomId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> paymentHistoryService.getPaymentHistory(randomId, payId));

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.never())
                .findByBusinessId(business);
        }
    }

    @Nested
    class insertPaymentHistory {
        PaymentHistoryRequest req = new PaymentHistoryRequest(
            paymentHistory.getBusinessId(),
            paymentHistory.getCardId(),
            paymentHistory.getPayNum(),
            paymentHistory.getPayAmount()
        );

        @Test
        void 성공_결제_내역_생성() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId))
                .willReturn(business);

            // when
            paymentHistoryService.insertPaymentHistory(business, req);

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.times(1))
                .save(any());
        }

        @Test
        void 실패_가맹점이_유효하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(business.getBusinessId()))
                .willReturn(null);
            // when
            assertThrows(NullPointerException.class, () -> paymentHistoryService.insertPaymentHistory(
                business, req));

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.never())
                .findByBusinessId(business);
        }
    }

    @Nested
    class updatePaymentHistoryState {
        @Test
        void 성공_특정_결제_내역의_결제_상태가_수정됨() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId))
                .willReturn(business);
            BDDMockito.given(paymentHistoryRepository.findByBusinessIdAndPayId(business, payId))
                .willReturn(paymentHistory);
            UpdatePaymentHistoryRequest req = new UpdatePaymentHistoryRequest(
                PaymentState.PAY_COMPLETE
            );

            // when
            paymentHistoryService.updatePaymentHistoryState(businessId, payId, req);
        }

        @Test
        void 실패_가맹점이_유효하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(randomId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> paymentHistoryService.getPaymentHistories(randomId));

            // then
            Mockito.verify(paymentHistoryRepository, Mockito.never())
                .findByBusinessId(business);
        }

        @Test
        void 실패_결제_내역이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId))
                .willReturn(business);
            BDDMockito.given(paymentHistoryRepository.findByBusinessIdAndPayId(paymentHistory.getBusinessId(), payId))
                .willReturn(null);
            UpdatePaymentHistoryRequest req = new UpdatePaymentHistoryRequest(
                PaymentState.PAY_COMPLETE
            );

            // when
            assertThrows(NullPointerException.class, () -> paymentHistoryService.updatePaymentHistoryState(businessId, payId, req));
        }
    }
}