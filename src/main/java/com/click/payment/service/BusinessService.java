package com.click.payment.service;

import com.click.payment.domain.dto.request.BusinessRequest;
import com.click.payment.domain.dto.request.UpdateBusinessRequest;
import com.click.payment.domain.dto.response.BusinessResponse;
import java.util.UUID;

public interface BusinessService {

    void registerBusiness(BusinessRequest businessRequest);

    BusinessResponse getBusiness(UUID businessId);

    void updateBusinessInfo(UUID businessId, UpdateBusinessRequest updateBusinessRequest);

    void deleteBusiness(UUID businessId);
}
