package com.click.payment.controller;

import com.click.payment.domain.dto.request.StoreRequest;
import com.click.payment.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class StoreController {

    private final StoreService storeService;

    // Create
    @PostMapping
    public void registerStore(@RequestBody StoreRequest storeRequest) {
        storeService.registerStore(storeRequest);
    }

    // Read


    // Update


    // Delete
}
