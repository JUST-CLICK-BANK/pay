package com.click.payment.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.click.payment.TestInitData;
import com.click.payment.domain.dto.request.SignInRequest;
import com.click.payment.domain.dto.response.SignInResponse;
import com.click.payment.util.PasswordUtils;
import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.repository.AllowedRedirectRepository;
import com.click.payment.domain.repository.BusinessRepository;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
class BusinessServiceTest extends TestInitData {
    @InjectMocks
    private BusinessServiceImpl businessService;
    @Mock
    BusinessRepository businessRepository;
    @Mock
    AllowedRedirectRepository allowedRedirectRepository;
    @Mock
    PasswordUtils passwordUtils;

    UUID businessId = business.getBusinessId();
    UUID randomId = UUID.randomUUID();
    UUID redirBusinessId = allowedRedirect.getBusinessId().getBusinessId();

    @Nested
    @Transactional
    class registerBusiness {
        BusinessRequest req = new BusinessRequest(
            business.getBusinessName(),
            business.getBusinessCeo(),
            business.getBusinessKey(),
            business.getBusinessAccount()
        );

        @Test
        void 성공_가맹점_등록됨() {
            // when
            businessService.registerBusiness(req);

            // then
            Mockito.verify(businessRepository, Mockito.times(1))
                .save(any());
        }

        @Test
        void 실패_가맹점_등록이_안됨() {
            // given
            String businessKey = "a";

            // when
            businessService.registerBusiness(req);

            // then
            Mockito.verify(businessRepository, Mockito.never())
                .save(req.toEntity(businessKey, passwordUtils));
        }
    }

    @Nested
    class signInBusiness {
        String businessKey = business.getBusinessKey();
        String salt = business.getBusinessSalt();
        SignInRequest req = new SignInRequest(
            businessKey,
            "1234"
        );
        String businessPassword = business.getBusinessPassword();

        @Test
        void 성공_로그인_성공함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessKey(businessKey))
                .willReturn(businessKey);
            BDDMockito.given(businessRepository.findByBusinessSalt(businessKey))
                .willReturn(salt);
            BDDMockito.given(passwordUtils.passwordHashing(req.businessPassword(), salt))
                .willReturn(businessPassword);
            BDDMockito.given(businessRepository.findByBusinessPassword(businessKey))
                .willReturn(businessPassword);

            // when
            businessService.signInBusiness(req);
        }
    }

    @Nested
    class getBusiness {
        @Test
        void 성공_가맹점을_조회함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId))
                .willReturn(business);

            // when
            businessService.getBusiness(businessId);

            // then
            Mockito.verify(businessRepository, Mockito.times(1))
                .findByBusinessIdAndBusinessDisableIsFalse(businessId);
        }

        @Test
        void 실패_가맹점이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(randomId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> businessService.getBusiness(randomId));

            // then
            Mockito.verify(businessRepository, Mockito.never())
                .findByBusinessIdAndBusinessDisableIsFalse(businessId);
        }
    }

    @Nested
    @Transactional
    class updateBusinessInfo {
        UpdateBusinessRequest req = new UpdateBusinessRequest(
            business.getBusinessName(),
            business.getBusinessCeo(),
            business.getBusinessAccount()
        );

        @Test
        void 성공_가맹점의_정보를_수정함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId))
                .willReturn(business);

            // when
            businessService.updateBusinessInfo(businessId, req);
        }

        @Test
        void 실패_가맹점이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(randomId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> businessService.updateBusinessInfo(randomId, req));
        }
    }

    @Nested
    class deleteBusiness {
        @Test
        void 성공_가맹점을_삭제함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId))
                .willReturn(business);

            // when
            businessService.deleteBusiness(businessId);
        }

        @Test
        void 실패_가맹점이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(randomId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> businessService.deleteBusiness(randomId));
        }
    }

    @Nested
    class registerUrl {
        RedirectUrlRequest req = new RedirectUrlRequest(
            allowedRedirect.getBusinessId().getBusinessId().toString(),
            allowedRedirect.getRedirUrl()
        );

        @Test
        void 성공_가맹점의_리다이렉트_URL을_생성함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(redirBusinessId))
                .willReturn(business);

            // when
            businessService.registerUrl(req);

            // then
            Mockito.verify(allowedRedirectRepository, Mockito.times(1))
                .save(any());
        }

        @Test
        void 실패_가맹점이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(redirBusinessId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> businessService.registerUrl(req));

            // then
            Mockito.verify(allowedRedirectRepository, Mockito.never())
                .save(any());
        }
    }

    @Nested
    class getRedirectUrl {
        @Test
        void 성공_가맹점의_리다이렉트_URL들을_조회함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(redirBusinessId))
                .willReturn(business);

            // when
            businessService.getRedirectUrl(redirBusinessId);

            // then
            Mockito.verify(allowedRedirectRepository, Mockito.times(1))
                .findRedirectUrlByBusinessId(business);
        }

        @Test
        void 실패_가맹점이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(redirBusinessId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> businessService.getRedirectUrl(redirBusinessId));

            // then
            Mockito.verify(allowedRedirectRepository, Mockito.never())
                .findRedirectUrlByBusinessId(business);
        }
    }

    @Nested
    class deleteRedirectUrl {
        @Test
        void 성공_가맹점의_리다이렉트_URL을_삭제함() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(redirBusinessId))
                .willReturn(business);

            // when
            businessService.deleteRedirectUrl(business);

            // then
            Mockito.verify(allowedRedirectRepository, Mockito.times(1))
                .deleteByBusinessId(business);
        }

        @Test
        void 실패_가맹점이_존재하지_않음() {
            // given
            BDDMockito.given(businessRepository.findByBusinessIdAndBusinessDisableIsFalse(redirBusinessId))
                .willReturn(null);

            // when
            assertThrows(NullPointerException.class, () -> businessService.deleteRedirectUrl(business));

            // then
            Mockito.verify(allowedRedirectRepository, Mockito.never())
                .deleteByBusinessId(business);
        }
    }
}