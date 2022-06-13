package com.example.btechproject.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {

    private String status;
    private String message;

    public ResponseDto(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
