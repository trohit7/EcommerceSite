package com.example.btechproject.dto.user;

import com.example.btechproject.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserCreateDto {


    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String password;

}
