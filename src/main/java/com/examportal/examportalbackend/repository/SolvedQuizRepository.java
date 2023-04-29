package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.exam.SolvedQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz,Long> {
}
