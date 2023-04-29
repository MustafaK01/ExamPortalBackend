package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Instructor,Long> {
}
