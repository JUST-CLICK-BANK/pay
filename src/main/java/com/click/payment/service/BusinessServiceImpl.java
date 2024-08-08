package com.click.payment.service;

import com.click.payment.domain.dto.request.SignInRequest;
import com.click.payment.domain.dto.response.SignInResponse;
import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.dto.response.BusinessResponse;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.repository.AllowedRedirectRepository;
import com.click.payment.domain.repository.BusinessRepository;
import com.click.payment.utils.GenerateApiKey;
import com.click.payment.utils.PasswordUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService, AllowedRedirectService {

    private final BusinessRepository businessRepository;
    private final AllowedRedirectRepository allowedRedirectRepository;
    private final PasswordUtils passwordUtils;

    // 가맹점 등록
    @Override
    public String registerBusiness(BusinessRequest businessRequest) {
        String businessKey = new GenerateApiKey().generateApiKey();
        businessRepository.save(businessRequest.toEntity(businessKey, passwordUtils));
        return businessKey;
    }

    // 로그인
    @Override
    public UUID signInBusiness(SignInRequest signInRequest) {
        // 가맹점 키
        String businessKey = businessRepository.findByBusinessKey(signInRequest.businessKey());
        // 가맹점 salt
        String salt = businessRepository.findByBusinessSalt(signInRequest.businessKey());

        // 입력한 비밀번호
        String inputPassword = passwordUtils.passwordHashing(signInRequest.businessPassword(), salt);
        // 가맹점 비밀번호
        String businessPassword = businessRepository.findByBusinessPassword(businessKey);

        if(businessKey == null || !inputPassword.equals(businessPassword))
            throw new IllegalArgumentException("API Key 혹은 비밀번호가 틀렸습니다.");

        SignInResponse.from(businessKey, businessPassword);
        return businessRepository.getByBusinessId(businessKey);
    }

    // 가맹점 조회
    @Override
    public BusinessResponse getBusiness(UUID businessId) {
        Business byBusiness = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(
            businessId);
        return BusinessResponse.from(byBusiness);
    }

    // 가맹점 정보 수정
    @Override
    @Transactional
    public void updateBusinessInfo(UUID businessId, UpdateBusinessRequest updateBusinessRequest) {
        Business byId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId);
        if(byId.getBusinessId() == null)
            throw new IllegalArgumentException("Business not found");

        byId.updateInfo(updateBusinessRequest);
    }

    // 가맹점 폐업 시 삭제(disable = false)
    @Override
    @Transactional
    public void deleteBusiness(UUID businessId) {
        Business byId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId);
        if(byId.getBusinessId() == null)
            throw new IllegalArgumentException("Business not found");

        byId.setBusinessDisable(true);
    }

    // Redirect Url 등록
    @Override
    public void registerUrl(RedirectUrlRequest redirectUrlRequest) {
        Business byId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(UUID.fromString(redirectUrlRequest.businessId()));
        if(byId.getBusinessId() == null)
            throw new NullPointerException("Business not found");

        allowedRedirectRepository.save(redirectUrlRequest.toEntity(byId));
    }

    // Redirect Url 조회
    @Override
    public List<String> getRedirectUrl(UUID businessId) {
        Business byId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(businessId);
        if(byId.getBusinessId() == null)
            throw new NullPointerException("Business not found");

        return allowedRedirectRepository.findRedirectUrlByBusinessId(byId);
    }

    // Redirect Url 삭제
    @Override
    @Transactional
    public void deleteRedirectUrl(Business business) {
        Business byId = businessRepository.findByBusinessIdAndBusinessDisableIsFalse(business.getBusinessId());
        if(byId.getBusinessId() == null)
            throw new NullPointerException("Business not found");

        allowedRedirectRepository.deleteByBusinessId(byId);
    }
}
