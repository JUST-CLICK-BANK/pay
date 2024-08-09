package com.click.payment.controller;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.SignInRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.dto.response.BusinessResponse;
import com.click.payment.domain.dto.response.SignInResponse;
import com.click.payment.domain.entity.Business;
import com.click.payment.service.AllowedRedirectService;
import com.click.payment.service.BusinessService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * 가맹점 정보를 생성합니다. (회원가입)
     * @param businessRequest
     */
    @PostMapping
    public String registerBusiness(
        @Valid
        @RequestBody BusinessRequest businessRequest
    ) {
        return businessService.registerBusiness(businessRequest);
    }

    /**
     * 가맹점 B2B 로그인
     * @param signInRequest
     * @return ResponseEntity<String>
     */
    @PostMapping("/signin")
    public ResponseEntity<UUID> signInBusiness(
        @RequestBody SignInRequest signInRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(businessService.signInBusiness(signInRequest));
    }

    /**
     * 가맹점 정보를 조회합니다. (조회 시, able = true)
     * @param businessId
     * @return BusinessResponse
     */
    @GetMapping("/{businessId}")
    public BusinessResponse getBusiness(@PathVariable("businessId") UUID businessId) {
        return businessService.getBusiness(businessId);
    }

    /**
     * 가맹점 정보를 수정합니다.
     * @param businessId
     * @param updateBusinessRequest
     */
    @PutMapping("/{businessId}")
    public void updateBusiness(@PathVariable("businessId") UUID businessId,
        @RequestBody UpdateBusinessRequest updateBusinessRequest) {
        businessService.updateBusinessInfo(businessId, updateBusinessRequest);
    }

    /**
     * 가맹점의 정보를 disable = true로 수정합니다. (삭제)
     * @param businessId
     */
    @DeleteMapping("/{businessId}")
    public void deleteBusiness(@PathVariable("businessId") UUID businessId) {
        businessService.deleteBusiness(businessId);
    }

    /**
     * 가맹점에게 보낼 리다이렉션 URL을 생성합니다.
     * @param redirectUrlRequest
     */
    @PostMapping("/redirect")
    public void registerRedirectUrl(@RequestBody RedirectUrlRequest redirectUrlRequest) {
        allowedRedirectService.registerUrl(redirectUrlRequest);
    }

    /**
     * 가맹점의 리다이렉션 URL들을 조회합니다.
     * @param businessId
     * @return List<String>
     */
    @GetMapping("/redirect/{businessId}")
    public List<String> getRedirectUrl(@PathVariable("businessId") UUID businessId) {
        return allowedRedirectService.getRedirectUrl(businessId);
    }

    /**
     * 가맹점의 리다이렉션 URL을 삭제합니다.
     * @param business
     */
    @DeleteMapping("/redirect/{businessId}")
    public void deleteRedirectUrl(@PathVariable("businessId") Business business) {
        allowedRedirectService.deleteRedirectUrl(business);
    }
}
