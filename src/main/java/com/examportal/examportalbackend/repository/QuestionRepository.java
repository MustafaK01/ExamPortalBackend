package com.examportal.examportalbackend.repository;

import com.examportal.examportalbackend.model.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findByQuiz(Long quizId);
}
