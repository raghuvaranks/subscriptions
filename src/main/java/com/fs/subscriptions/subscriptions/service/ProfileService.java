package com.fs.subscriptions.subscriptions.service;

import com.fs.subscriptions.subscriptions.payload.ProfileDTO;

import java.util.Set;

public interface ProfileService {
    ProfileDTO findById(Long l);
    ProfileDTO saveProfile(ProfileDTO profileDTO,Long id);
    void deleteById(Long l);
    ProfileDTO updateProfile(Long l, ProfileDTO profileDTO);
    Set<ProfileDTO> getProfiles(Long subscriptionId);
}
