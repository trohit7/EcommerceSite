package com.example.btechproject.dto.user;

import com.example.btechproject.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserUpdateDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Role role;


}
