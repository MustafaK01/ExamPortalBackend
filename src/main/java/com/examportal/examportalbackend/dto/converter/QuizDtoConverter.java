package com.examportal.examportalbackend.dto.converter;

import com.examportal.examportalbackend.dto.CategoryDto;
import com.examportal.examportalbackend.dto.QuizDto;
import com.examportal.examportalbackend.model.exam.Category;
import com.examportal.examportalbackend.model.exam.Quiz;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizDtoConverter {

    public QuizDto convertToDto(Quiz quiz){
        return QuizDto.builder()
                .quizTitle(quiz.getQuizTitle())
                .quizDescription(quiz.getQuizDescription())
                .numOfQuestions(quiz.getNumOfQuestions())
                .maxMarks(quiz.getMaxMarks())
                .isActive(quiz.isActive())
                .build();
    }

    public List<QuizDto> convertToDto(List<Quiz> quizzes){
        return quizzes.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
