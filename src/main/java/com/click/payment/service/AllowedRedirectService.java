package com.click.payment.service;

import com.click.payment.domain.dto.request.RedirectUrlRequest;

public interface AllowedRedirectService {

    void registerUrl(RedirectUrlRequest redirectUrlRequest);
}
