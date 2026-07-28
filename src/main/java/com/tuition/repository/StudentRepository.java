package com.tuition.repository;

import com.tuition.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}