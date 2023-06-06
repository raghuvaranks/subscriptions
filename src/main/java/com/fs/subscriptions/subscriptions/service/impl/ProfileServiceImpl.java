package com.fs.subscriptions.subscriptions.service.impl;

import com.fs.subscriptions.subscriptions.entity.Profiles;
import com.fs.subscriptions.subscriptions.entity.Subscription;
import com.fs.subscriptions.subscriptions.payload.ProfileDTO;
import com.fs.subscriptions.subscriptions.repository.ProfileRepository;
import com.fs.subscriptions.subscriptions.repository.SubscriptionRepository;
import com.fs.subscriptions.subscriptions.service.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private ProfileRepository profileRepository;
    private ModelMapper modelMapper;
    private SubscriptionRepository subscriptionRepository;

    public ProfileServiceImpl(ModelMapper modelMapper,ProfileRepository profileRepository,SubscriptionRepository subscriptionRepository){
        this.modelMapper=modelMapper;
        this.profileRepository=profileRepository;
        this.subscriptionRepository=subscriptionRepository;
    }

    @Override
    public ProfileDTO findById(Long l) {
        return null;
    }

    @Override
    public ProfileDTO saveProfile(ProfileDTO profileDTO, Long id) {
        Optional<Subscription> subscriptionOptional=subscriptionRepository.findById(id);
        if(!subscriptionOptional.isPresent()){
            throw new RuntimeException("Subscription Not Found");
        }
        Subscription subscription=subscriptionOptional.get();
        Profiles profiles=maptoprofile(profileDTO);
        profileRepository.save(profiles);
        return maptoprofileDTO(profiles);
    }

    @Override
    public void deleteById(Long l) {

    }

    @Override
    public ProfileDTO updateProfile(Long l, ProfileDTO profileDTO) {
        return null;
    }

    @Override
    public Set<ProfileDTO> getProfiles(Long subscriptionId) {
        Optional<Subscription> subscriptionOptional=subscriptionRepository.findById(subscriptionId);
        if(!subscriptionOptional.isPresent()){
            throw new RuntimeException("Subscription Not Found");
        }
        Subscription subscription=subscriptionOptional.get();
        Set<ProfileDTO> profileDTO=subscription.getProfiles().stream().map(profiles -> maptoprofileDTO(profiles)).collect(Collectors.toSet());
        return profileDTO;
    }

    public ProfileDTO maptoprofileDTO(Profiles profile){
        ProfileDTO profileDTO=modelMapper.map(profile,ProfileDTO.class);
        return profileDTO;
    }

    public Profiles maptoprofile(ProfileDTO profileDTO){
        Profiles profile=modelMapper.map(profileDTO,Profiles.class);
        return profile;
    }
}
