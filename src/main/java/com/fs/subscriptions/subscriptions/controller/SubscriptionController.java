package com.fs.subscriptions.subscriptions.controller;

import com.fs.subscriptions.subscriptions.payload.SubscriptionDTO;
import com.fs.subscriptions.subscriptions.service.SubscriptionService;
import com.fs.subscriptions.subscriptions.service.impl.CustomerValidationImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final CustomerValidationImpl customerValidation;

    public SubscriptionController(SubscriptionService subscriptionService, CustomerValidationImpl customerValidation) {
        this.subscriptionService = subscriptionService;
        this.customerValidation = customerValidation;
    }

    @PostMapping("/custdetails/{custId}/subsriptions")
    public SubscriptionDTO addSubscriptions(@RequestBody @Valid SubscriptionDTO subscriptionDTO, @PathVariable Long custId) {
        Long custIdentifier = customerValidation.validateCustomer(custId).getId();
        return subscriptionService.saveSubscription(subscriptionDTO, custIdentifier);
    }
    @PutMapping("/custdetails/{custId}/subsriptions/{id}")
    public SubscriptionDTO updateSubscriptions(@RequestBody @Valid SubscriptionDTO subscriptionDTO, @PathVariable Long custId, @PathVariable Long id) {
        Long custIdentifier = customerValidation.validateCustomer(custId).getId();
        return subscriptionService.updateSubscription(id, subscriptionDTO, custIdentifier);
    }

    @RequestMapping("/custdetails/{custId}/subscription")
    public ResponseEntity<List<SubscriptionDTO>> subscriptiondetails(@PathVariable Long custId){
        Long custIdentifier = customerValidation.validateCustomer(custId).getId();
        return ResponseEntity.ok(subscriptionService.getSubscriptions());
    }

    @RequestMapping("/custdetails/{custId}/subscription/{id}")
    public ResponseEntity<SubscriptionDTO> getsubscription(@PathVariable Long custId, @PathVariable Long id){
        Long custIdentifier = customerValidation.validateCustomer(custId).getId();
        return ResponseEntity.ok(subscriptionService.findById(id));
    }

    @DeleteMapping("/custdetails/{custId}/subscription/{id}")
    public String deleteSubscription(@PathVariable Long custId,@PathVariable Long id){
        return subscriptionService.deleteById(id);
    }
}