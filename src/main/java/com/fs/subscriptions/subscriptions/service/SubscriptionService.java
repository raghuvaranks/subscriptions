package com.fs.subscriptions.subscriptions.service;

import com.fs.subscriptions.subscriptions.payload.SubscriptionDTO;

import java.util.*;

public interface SubscriptionService {
    SubscriptionDTO findById(Long l);

    List<SubscriptionDTO> getSubscriptions();
    SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO,Long customerId);
    String deleteById(Long l);
    SubscriptionDTO updateSubscription(Long l,SubscriptionDTO subscriptionDTO,Long customerId);

}
