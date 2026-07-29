package com.tuition.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {

    private Long studentId;

    private String firstName;

    private String lastName;

    private String email;

    private String token;

    private String message;
}