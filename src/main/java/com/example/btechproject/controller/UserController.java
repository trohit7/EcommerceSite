package com.example.btechproject.controller;

import com.example.btechproject.dto.ResponseDto;
import com.example.btechproject.dto.user.SignInDto;
import com.example.btechproject.dto.user.SignInResponseDto;
import com.example.btechproject.dto.user.SignupDto;
import com.example.btechproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
     UserService userService;

    @PostMapping("/signUp")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.signUp(signupDto);
    }


    @PostMapping("/signIn")
    public SignInResponseDto signup(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }


}
