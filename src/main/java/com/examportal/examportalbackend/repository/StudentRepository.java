package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
