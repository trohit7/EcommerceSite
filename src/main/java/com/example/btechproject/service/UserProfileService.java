package com.example.btechproject.service;

import com.example.btechproject.model.UserProfile;
import com.example.btechproject.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public void addProfile(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public List<UserProfile> listProfiles(){
        return userProfileRepository.findAll();
    }

}
