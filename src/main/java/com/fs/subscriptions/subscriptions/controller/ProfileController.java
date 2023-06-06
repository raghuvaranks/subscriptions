package com.fs.subscriptions.subscriptions.controller;

import com.fs.subscriptions.subscriptions.entity.Profiles;
import com.fs.subscriptions.subscriptions.payload.ProfileDTO;
import com.fs.subscriptions.subscriptions.service.ProfileService;
import com.fs.subscriptions.subscriptions.service.SubscriptionService;
import com.fs.subscriptions.subscriptions.service.impl.CustomerValidationImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Slf4j
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private CustomerValidationImpl customerValidation;

    @PostMapping("/custdetails/{custId}/subscriptions/{subscriptionId}/profiles")
    public ProfileDTO addProfiles(@RequestBody @Valid ProfileDTO profileDTO, @PathVariable Long custId, @PathVariable Long subscriptionId){
        Long custIdentifier=customerValidation.validateCustomer(custId).getId();
        return profileService.saveProfile(profileDTO,subscriptionId);
    }

    @RequestMapping("/custdetails/{custId}/subscriptions/{subscriptionId}/profiles")
    public ResponseEntity<Set<ProfileDTO>> profileDetails(@PathVariable Long custId, @PathVariable Long subscriptionId){
        Long custIdentifier=customerValidation.validateCustomer(custId).getId();
        return ResponseEntity.ok(profileService.getProfiles(subscriptionId));
    }
}
