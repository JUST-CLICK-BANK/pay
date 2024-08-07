package com.click.payment.service;

import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.SignInRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.dto.response.BusinessResponse;
import com.click.payment.domain.dto.response.SignInResponse;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface BusinessService {

    String registerBusiness(BusinessRequest businessRequest);

    UUID signInBusiness(SignInRequest signInRequest);

    BusinessResponse getBusiness(UUID businessId);

    void updateBusinessInfo(UUID businessId, UpdateBusinessRequest updateBusinessRequest);

    void deleteBusiness(UUID businessId);
}
