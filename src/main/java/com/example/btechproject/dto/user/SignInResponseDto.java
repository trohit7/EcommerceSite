package com.example.btechproject.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInResponseDto {

    private String status;
    private String token;

    public SignInResponseDto(String status, String token) {
        this.status = status;
        this.token = token;
    }
}
