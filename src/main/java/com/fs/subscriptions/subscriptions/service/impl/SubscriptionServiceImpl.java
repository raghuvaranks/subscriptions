package com.fs.subscriptions.subscriptions.service.impl;

import com.fs.subscriptions.subscriptions.entity.Profiles;
import com.fs.subscriptions.subscriptions.entity.Subscription;
import com.fs.subscriptions.subscriptions.exception.ResourceNotFoundException;
import com.fs.subscriptions.subscriptions.payload.ProfileDTO;
import com.fs.subscriptions.subscriptions.payload.SubscriptionDTO;
import com.fs.subscriptions.subscriptions.repository.SubscriptionRepository;
import com.fs.subscriptions.subscriptions.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private ModelMapper mapper;
    private SubscriptionRepository subscriptionRepository;
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,ModelMapper mapper){
        this.subscriptionRepository=subscriptionRepository;
        this.mapper=mapper;
    }
    @Override
    public SubscriptionDTO findById(Long l) {
        Subscription subscription = subscriptionRepository.findById(l).orElseThrow(()->new ResourceNotFoundException("Subscription ID not Found"));
        return maptsubscribtionDTO(subscription);
    }

    @Override
    public List<SubscriptionDTO> getSubscriptions() {
        List<Subscription> subscriptions=subscriptionRepository.findAll();
        return subscriptions.stream().map(subscription -> maptsubscribtionDTO(subscription)).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO saveSubscription(SubscriptionDTO subscriptionDTO, Long customerId) {
        subscriptionDTO.setCustomerId(customerId.toString());
        Subscription subscription=subscriptionRepository.save(maptosubscription(subscriptionDTO));
        return maptsubscribtionDTO(subscription);
    }

    @Override
    public String deleteById(Long l) {
        subscriptionRepository.deleteById(l);
        return "SUCCESSFULLY DELETED";
    }

    @Override
    public SubscriptionDTO updateSubscription(Long l, SubscriptionDTO subscriptionDTO, Long customerId) {
        Subscription subscription1 = subscriptionRepository.findById(l).orElseThrow(()->new ResourceNotFoundException("Subscription ID not Found"));
        Subscription subscription=maptosubscription(subscriptionDTO);
        subscription.setId(l);
        subscription.setCustomerId(customerId.toString());
        Subscription updatedSubscription=subscriptionRepository.save(subscription);
        return maptsubscribtionDTO(updatedSubscription);
    }

    public SubscriptionDTO maptsubscribtionDTO(Subscription subscription){
        SubscriptionDTO subscriptionDTO=mapper.map(subscription,SubscriptionDTO.class);
        return subscriptionDTO;
    }

    public Subscription maptosubscription(SubscriptionDTO subscriptionDTO){
        Subscription subscription=mapper.map(subscriptionDTO,Subscription.class);
        return subscription;
    }
}
