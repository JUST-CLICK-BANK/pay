package com.click.payment.controller;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.dto.response.BusinessResponse;
import com.click.payment.domain.entity.Business;
import com.click.payment.service.AllowedRedirectService;
import com.click.payment.service.BusinessService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/businesses")
public class BusinessController {

    private final BusinessService businessService;
    private final AllowedRedirectService allowedRedirectService;

    // 가맹점 정보 생성
    @PostMapping
    public void registerBusiness(@RequestBody BusinessRequest businessRequest) {
        businessService.registerBusiness(businessRequest);
    }

    // 가맹점 정보 조회
    @GetMapping("/{businessId}")
    public BusinessResponse getBusiness(@PathVariable("businessId") UUID businessId) {
        return businessService.getBusiness(businessId);
    }

    // 가맹점 정보 수정
    @PutMapping("/{businessId}")
    public void updateBusiness(@PathVariable("businessId") UUID businessId,
        @RequestBody UpdateBusinessRequest updateBusinessRequest) {
        businessService.updateBusinessInfo(businessId, updateBusinessRequest);
    }

    // 가맹점 삭제
    @DeleteMapping("/{businessId}")
    public void deleteBusiness(@PathVariable("businessId") UUID businessId) {
        businessService.deleteBusiness(businessId);
    }

    // 가맹점 리다이렉션 주소 생성
    @PostMapping("/redirect")
    public void registerRedirectUrl(@RequestBody RedirectUrlRequest redirectUrlRequest) {
        allowedRedirectService.registerUrl(redirectUrlRequest);
    }

    // 가맹점 리다이렉션 주소 조회
    @GetMapping("/redirect/{businessId}")
    public List<String> getRedirectUrl(@PathVariable("businessId") UUID businessId) {
        return allowedRedirectService.getRedirectUrl(businessId);
    }

    // 가맹점 리다이렉션 주소 삭제
    @DeleteMapping("/redirect/{businessId}")
    public void deleteRedirectUrl(@PathVariable("businessId") Business business) {
        allowedRedirectService.deleteRedirectUrl(business);
    }
}
