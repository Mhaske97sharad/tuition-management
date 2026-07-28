package com.tuition.service.impl;

import com.tuition.dto.request.StudentRegistrationRequest;
import com.tuition.dto.response.StudentRegistrationResponse;
import com.tuition.entity.Student;
import com.tuition.exception.DuplicateResourceException;
import com.tuition.repository.StudentRepository;
import com.tuition.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public StudentRegistrationResponse registerStudent(StudentRegistrationRequest request) {

        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already registered");
        }

        if (studentRepository.existsByPhone(request.getPhone())) {
            throw new DuplicateResourceException("Phone number already registered");
        }

        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .build();

        Student savedStudent = studentRepository.save(student);

        return StudentRegistrationResponse.builder()
                .id(savedStudent.getId())
                .message("Student registered successfully")
                .build();
    }
}