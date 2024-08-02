package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.entity.Business;
import java.util.List;
import java.util.UUID;

public interface AllowedRedirectService {

    void registerUrl(RedirectUrlRequest redirectUrlRequest);

    List<String> getRedirectUrl(UUID businessId);

    void deleteRedirectUrl(Business business);
}
