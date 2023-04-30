package com.examportal.examportalbackend.service.abstracts;

import com.examportal.examportalbackend.core.utils.results.Result;
import com.examportal.examportalbackend.dto.QuizDto;
import com.examportal.examportalbackend.model.exam.Quiz;

import java.util.Set;

public interface QuizService {

    Result addQuiz(Quiz quiz);
    Result updateQuiz(Quiz quiz);
    Set<QuizDto> getQuizzes();
    QuizDto getQuiz(Long quizId);
    Result deleteQuiz(Long quizId);


}
