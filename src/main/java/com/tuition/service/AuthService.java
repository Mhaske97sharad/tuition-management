package com.tuition.service;

import com.tuition.dto.request.LoginRequest;
import com.tuition.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse login(LoginRequest request);

}