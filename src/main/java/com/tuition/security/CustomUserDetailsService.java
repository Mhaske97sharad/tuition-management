package com.tuition.security;

import com.tuition.entity.Student;
import com.tuition.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Student not found"));

        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .authorities(student.getRole().name())
                .build();
    }
}