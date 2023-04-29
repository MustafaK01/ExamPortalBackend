package com.examportal.examportalbackend.repository;
import com.examportal.examportalbackend.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import com.examportal.examportalbackend.model.User;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
