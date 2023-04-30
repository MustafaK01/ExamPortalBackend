package com.examportal.examportalbackend.dto.converter;

import com.examportal.examportalbackend.dto.QuestionDto;
import com.examportal.examportalbackend.dto.QuizDto;
import com.examportal.examportalbackend.model.exam.Question;
import com.examportal.examportalbackend.model.exam.Quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDtoConverter {

    private final QuizDtoConverter quizDtoConverter;

    public QuestionDtoConverter(QuizDtoConverter quizDtoConverter) {
        this.quizDtoConverter = quizDtoConverter;
    }

    public QuestionDto convertToDto(Question question){
        return QuestionDto.builder()
                .questionContent(question.getQuestionContent())
                .option(question.getOption())
                .option1(question.getOption1())
                .option2(question.getOption2())
                .option3(question.getOption3())
                .option4(question.getOption4())
                .answer(question.getAnswer())
                .quizDto(this.quizDtoConverter.convertToDto(question.getQuiz()))
                .build();
    }

    public List<QuestionDto> convertToDto(List<Question> questions){
        return questions.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
