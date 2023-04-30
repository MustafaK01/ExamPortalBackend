package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.core.utils.results.Result;
import com.examportal.examportalbackend.core.utils.results.ResultData;
import com.examportal.examportalbackend.dto.QuestionDto;
import com.examportal.examportalbackend.dto.converter.QuestionDtoConverter;
import com.examportal.examportalbackend.exception.InvalidParamException;
import com.examportal.examportalbackend.model.exam.Question;
import com.examportal.examportalbackend.model.exam.Quiz;
import com.examportal.examportalbackend.repository.QuestionRepository;
import com.examportal.examportalbackend.repository.QuizRepository;
import com.examportal.examportalbackend.service.abstracts.QuestionService;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final QuestionDtoConverter questionDtoConverter;
    
    
    public QuestionServiceImpl(QuestionRepository questionRepository, QuizRepository quizRepository, QuestionDtoConverter questionDtoConverter) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.questionDtoConverter = questionDtoConverter;
    }

    @Override
    public Result addQuestion(Question question) {
        if(question!=null) return new ResultData<>(this.questionRepository.save(question),true);
        return null;
    }

    @Override
    public Result updateQuestion(Question question) {
        if(question!=null) return new ResultData<>(this.questionRepository.save(question),true);
        return null;
    }

    @Override
    public Set<QuestionDto> getQuestions() {
        List<Question> questions = this.questionRepository.findAll();
        if(!questions.isEmpty()){
            return new LinkedHashSet<>(
                    this.questionDtoConverter
                            .convertToDto(questions));
        }
        return null;
    }

    @Override
    public QuestionDto getQuestion(Long questionId) {
        return this.questionDtoConverter.convertToDto(
                this.questionRepository.findById(questionId).orElseThrow(
                        ()->new InvalidParamException("Invalid Param")));
    }
    
    @Override
    public Set<QuestionDto> getQuestionsOfQuiz(Long quizId) {
        //Create questionAndQuizDto to prevent repeatings
        //Create custom excepiton
        Optional<Quiz> quiz = this.quizRepository.findById(quizId);
        if(quiz.isPresent()){
            List<Question> questions = this.questionRepository.findByQuiz(quiz.get().getQuizId());
            if(!questions.isEmpty()){
                return new LinkedHashSet<>(
                        this.questionDtoConverter
                                .convertToDto(questions));
            }
        }
        throw new InvalidParamException("Quiz not found");
    }
}
