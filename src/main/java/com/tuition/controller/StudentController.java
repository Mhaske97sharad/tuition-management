package com.tuition.controller;

import com.tuition.dto.request.StudentRegistrationRequest;
import com.tuition.dto.response.StudentRegistrationResponse;
import com.tuition.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/register")
    public ResponseEntity<StudentRegistrationResponse> registerStudent(
            @Valid @RequestBody StudentRegistrationRequest request) {

        StudentRegistrationResponse response = studentService.registerStudent(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}