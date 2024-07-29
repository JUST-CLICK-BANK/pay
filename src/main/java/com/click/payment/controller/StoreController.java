package com.click.payment.controller;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.StoreRequest;
import com.click.payment.service.AllowedRedirectService;
import com.click.payment.service.StoreService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class StoreController {

    private final StoreService storeService;
    private final AllowedRedirectService allowedRedirectService;
    // Create
    @PostMapping
    public void registerStore(@RequestBody StoreRequest storeRequest) {
        storeService.registerStore(storeRequest);
    }

    @PostMapping("/redirect")
    public void registerRedirectUrl(@RequestBody RedirectUrlRequest redirectUrlRequest) {
        allowedRedirectService.registerUrl(redirectUrlRequest);
    }

    // Read
    @GetMapping("/redirect")
    public List<String> getRedirectUrl(@RequestParam UUID storeId) {
        return allowedRedirectService.getUrl(storeId);
    }

    // Update


    // Delete
}
