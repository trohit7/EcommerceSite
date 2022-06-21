package com.example.btechproject.dto.checkout;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StripeResponse {

    private String sessionId;

    public StripeResponse(String sessionId) {
        this.sessionId = sessionId;
    }
}
