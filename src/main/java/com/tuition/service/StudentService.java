package com.tuition.service;

import com.tuition.dto.request.StudentRegistrationRequest;
import com.tuition.dto.response.StudentRegistrationResponse;

public interface StudentService {

    StudentRegistrationResponse registerStudent(StudentRegistrationRequest request);

}