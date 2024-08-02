package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.dto.response.BusinessResponse;
import com.click.payment.domain.entity.Business;
import com.click.payment.domain.repository.AllowedRedirectRepository;
import com.click.payment.domain.repository.BusinessRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService, AllowedRedirectService {

    private final BusinessRepository businessRepository;
    private final AllowedRedirectRepository allowedRedirectRepository;

    // 가맹점 등록
    @Override
    public void registerBusiness(BusinessRequest businessRequest) {
        businessRepository.save(businessRequest.toEntity());
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
        Optional<Business> byId = businessRepository.findById(businessId);
        Business business = byId.orElseThrow(
            () -> new IllegalArgumentException("Business not found"));

        business.updateInfo(updateBusinessRequest);
    }

    // 가맹점 폐업 시 삭제(disable = false)
    @Override
    @Transactional
    public void deleteBusiness(UUID businessId) {
        Optional<Business> byId = businessRepository.findById(businessId);
        Business business = byId.orElseThrow(() -> new IllegalArgumentException("Business not found"));

        business.setBusinessDisable(true);
    }

    // Redirect Url 등록
    @Override
    public void registerUrl(RedirectUrlRequest redirectUrlRequest) {
        Optional<Business> byId = businessRepository.findById(
            UUID.fromString(redirectUrlRequest.businessId()));
        Business business = byId.orElseThrow(() -> new IllegalArgumentException("Business not found"));

        allowedRedirectRepository.save(redirectUrlRequest.toEntity(business));
    }

    // Redirect Url 조회
    @Override
    public List<String> getRedirectUrl(UUID businessId) {
        Optional<Business> byId = businessRepository.findById(businessId);
        Business business = byId.orElseThrow(
            () -> new IllegalArgumentException("Business not found"));
        return allowedRedirectRepository.findRedirectUrlByBusinessId(business);
    }

    // Redirect Url 삭제
    @Override
    @Transactional
    public void deleteRedirectUrl(Business business) {
        allowedRedirectRepository.deleteByBusinessId(business);
    }
}