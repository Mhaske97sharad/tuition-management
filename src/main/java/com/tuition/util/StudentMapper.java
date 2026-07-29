package com.tuition.util;

import com.tuition.dto.request.StudentRegistrationRequest;
import com.tuition.dto.response.StudentRegistrationResponse;
import com.tuition.entity.Student;
import com.tuition.enums.Role;

public class StudentMapper {

    private StudentMapper() {
    }

    public static Student toEntity(StudentRegistrationRequest request, String encodedPassword) {
        return Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .phone(request.getPhone())
                .role(Role.ROLE_STUDENT)
                .build();
    }

    public static StudentRegistrationResponse toResponse(Student student) {
        return StudentRegistrationResponse.builder()
                .id(student.getId())
                .message("Student registered successfully")
                .build();
    }
}
