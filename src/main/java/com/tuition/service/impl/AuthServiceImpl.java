package com.tuition.service.impl;

import com.tuition.dto.request.LoginRequest;
import com.tuition.dto.response.LoginResponse;
import com.tuition.entity.Student;
import com.tuition.exception.InvalidCredentialsException;
import com.tuition.repository.StudentRepository;
import com.tuition.service.AuthService;
import com.tuition.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request) {

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        UserDetails userDetails =
                User.builder()
                        .username(student.getEmail())
                        .password(student.getPassword())
                        .authorities(student.getRole().name())
                        .build();

        String token = jwtService.generateToken(userDetails);

        return LoginResponse.builder()
                .studentId(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .token(token)
                .message("Login successful")
                .build();
    }
}