package com.examportal.examportalbackend.service.abstracts;

import com.examportal.examportalbackend.core.utils.results.Result;
import com.examportal.examportalbackend.dto.QuestionDto;
import com.examportal.examportalbackend.model.exam.Question;

import java.util.Set;

public interface QuestionService {

    Result addQuestion(Question question);
    Result updateQuestion(Question question);
    Set<QuestionDto> getQuestions();
    QuestionDto getQuestion(Long questionId);
    Set<QuestionDto> getQuestionsOfQuiz(Long quizId);

}
