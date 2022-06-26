package com.example.btechproject.controller;

import com.example.btechproject.dto.ResponseDto;
import com.example.btechproject.dto.user.SignInDto;
import com.example.btechproject.dto.user.SignInResponseDto;
import com.example.btechproject.dto.user.SignupDto;
import com.example.btechproject.dto.user.UserCreateDto;
import com.example.btechproject.exceptions.AuthenticationFailException;
import com.example.btechproject.exceptions.CustomException;
import com.example.btechproject.model.User;
import com.example.btechproject.repository.UserRepository;
import com.example.btechproject.service.AuthenticationService;
import com.example.btechproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
     UserService userService;

    @Autowired
    AuthenticationService authenticationService;




    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.findAll();
    }

    @PostMapping("/signUp")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        log.info("Trying to create account for {}",signupDto.getEmail());
        return userService.signUp(signupDto);
    }


    @PostMapping("/signIn")
    public SignInResponseDto signin(@RequestBody SignInDto signInDto){
        log.info("{} signed in",signInDto.getEmail());
        return userService.signIn(signInDto);
    }

    @PostMapping("/createUser")
    public ResponseDto createUser(@RequestParam("token") String token, @RequestBody UserCreateDto userCreateDto)
            throws CustomException, AuthenticationFailException {
        authenticationService.authenticate(token);
        return userService.createUser(token, userCreateDto);
    }

}
