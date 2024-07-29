package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import java.util.List;
import java.util.UUID;

public interface AllowedRedirectService {

    void registerUrl(RedirectUrlRequest redirectUrlRequest);

    List<String> getUrl(UUID storeId);
}
