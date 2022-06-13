package com.example.btechproject.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignupDto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
