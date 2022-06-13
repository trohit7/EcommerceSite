package com.example.btechproject.service;

import com.example.btechproject.dto.ResponseDto;
import com.example.btechproject.dto.user.SignupDto;
import com.example.btechproject.exceptions.CustomException;
import com.example.btechproject.model.AuthenticationToken;
import com.example.btechproject.model.User;
import com.example.btechproject.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;


    public ResponseDto signUp(SignupDto signupDto){
        // check if the user is already  present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))){
            //if we have the user already present then we will handle with Exceptions
            throw new CustomException("user already present ");
        }
        // hash(encrypting) the password

        String encryptedpassword = signupDto.getPassword();

        try {
            encryptedpassword = hashpassword(signupDto.getPassword());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        // create a new user
        User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),encryptedpassword);
        userRepository.save(user);
        // save the user with encrypted password
        // now create the token for new user
       final AuthenticationToken authenticationToken =  new AuthenticationToken(user);
       authenticationService.saveConfirmationToken(authenticationToken);
       ResponseDto responseDto = new ResponseDto("success","new user is signedup");
        return responseDto;

    }

    private String hashpassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;

    }

}


