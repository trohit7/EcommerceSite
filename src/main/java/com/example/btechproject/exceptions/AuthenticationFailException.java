package com.example.btechproject.exceptions;

public class AuthenticationFailException extends IllegalArgumentException{
    // handle the user authentication

    public AuthenticationFailException(String msg) {

        super(msg);
    }
}
