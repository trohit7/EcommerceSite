package com.example.btechproject.controller;


import com.example.btechproject.common.ApiResponse;
import com.example.btechproject.model.UserProfile;
import com.example.btechproject.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserProfileController {

        @Autowired
        private UserProfileService userProfileService;

        @GetMapping("/")
        public ResponseEntity<List<UserProfile>> getUsers() {
            List<UserProfile> dtos = userProfileService.listProfiles();
            return new ResponseEntity<List<UserProfile>>(dtos, HttpStatus.OK);
        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addSurvey(@RequestBody @Valid UserProfile profile) {
            userProfileService.addProfile(profile);
            return new ResponseEntity<>(new ApiResponse(true, "Profile has been created."), HttpStatus.CREATED);
        }
    }


