package com.click.payment.controller;

import com.click.payment.domain.dto.request.RedirectUrlRequest;
import com.click.payment.domain.dto.request.StoreRequest;
import com.click.payment.service.AllowedRedirectService;
import com.click.payment.service.StoreService;
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
    @PutMapping("/{storeId}")
    public void updateStore(
        @PathVariable("storeId") UUID storeId,
        @RequestBody StoreRequest storeRequest
    ) {
        storeService.updateStoreInfo(storeId, storeRequest);
    }

    @PutMapping("/redirect/{storeId}")
    public void updateRedirectUrl(
        @PathVariable("storeId") UUID storeId,
        @RequestBody String redirectUrl
    ) {
        storeService.updateRedirectUrl(storeId, redirectUrl);
    }

    // Delete
    @DeleteMapping("/redirect/{redirectId}")
    public void deleteRedirectUrl(@PathVariable("redirectId") Long redirectId) {
        storeService.deleteRedirectUrl(redirectId);
    }

    @DeleteMapping("/{storeId}")
    public void deleteStore(@PathVariable("storeId") UUID storeId) {
        storeService.deleteStore(storeId);
    }
}
