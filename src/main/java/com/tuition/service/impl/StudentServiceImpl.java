package com.tuition.service.impl;

import com.tuition.dto.request.StudentRegistrationRequest;
import com.tuition.dto.response.StudentRegistrationResponse;
import com.tuition.entity.Student;
import com.tuition.exception.DuplicateResourceException;
import com.tuition.repository.StudentRepository;
import com.tuition.service.StudentService;
import com.tuition.util.StudentMapper;
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

        Student student = StudentMapper.toEntity(
                request,
                passwordEncoder.encode(request.getPassword())
        );

        Student savedStudent = studentRepository.save(student);

        return StudentMapper.toResponse(savedStudent);
    }
}