package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
