package com.examportal.examportalbackend.dto;

import lombok.Builder;

@Builder
public class QuestionDto {

    private String questionContent;

    private String option;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private QuizDto quizDto;
    public QuestionDto() {
    }

    public QuestionDto(String questionContent, String option,
                       String option1, String option2, String option3,
                       String option4, String answer, QuizDto quizDto) {
        this.questionContent = questionContent;
        this.option = option;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answer = answer;
        this.quizDto = quizDto;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuizDto getQuizDto() {
        return quizDto;
    }

    public void setQuizDto(QuizDto quizDto) {
        this.quizDto = quizDto;
    }
}
