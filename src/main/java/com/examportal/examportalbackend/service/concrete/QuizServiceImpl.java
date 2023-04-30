package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.core.utils.results.Result;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.dto.QuizDto;
import com.examportal.examportalbackend.dto.converter.QuizDtoConverter;
import com.examportal.examportalbackend.exception.InvalidParamException;
import com.examportal.examportalbackend.model.exam.Quiz;
import com.examportal.examportalbackend.repository.QuizRepository;
import com.examportal.examportalbackend.service.abstracts.QuizService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizDtoConverter quizDtoConverter;

    public QuizServiceImpl(QuizRepository quizRepository, QuizDtoConverter quizDtoConverter) {
        this.quizRepository = quizRepository;
        this.quizDtoConverter = quizDtoConverter;
    }

    @Override
    public Result addQuiz(Quiz quiz) {
        if(quiz!=null) return new ResultData<>(this.quizRepository.save(quiz),true);
        return null;
    }

    @Override
    public Result updateQuiz(Quiz quiz) {
        if(quiz!=null) return new ResultData<>(this.quizRepository.save(quiz),true);
        return null;
    }

    @Override
    public Set<QuizDto> getQuizzes() {
        List<Quiz> quizzes = this.quizRepository.findAll();
        if(!quizzes.isEmpty()){
            return new HashSet<>(
                    this.quizDtoConverter
                            .convertToDto(quizzes));
        }
        return null;
    }

    @Override
    public QuizDto getQuiz(Long quizId) {
        return this.quizDtoConverter.convertToDto(
                this.quizRepository.findById(quizId).orElseThrow(
                        ()->new InvalidParamException("Invalid Param")));
    }

    @Override
    public Result deleteQuiz(Long quizId) {
        Optional<Quiz> quiz =  this.quizRepository.findById(quizId);
        if(quiz.isEmpty()) return new Result(false);
        quiz.ifPresent(this.quizRepository::delete);
        return new Result(true);
    }
}
