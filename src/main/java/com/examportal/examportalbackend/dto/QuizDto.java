package com.examportal.examportalbackend.dto;

import lombok.Builder;

@Builder
public class QuizDto {

    private String quizTitle;
    private String quizDescription;
    private String maxMarks;
    private String numOfQuestions;
    private boolean isActive;

    public QuizDto() {
    }

    public QuizDto(String quizTitle, String quizDescription,
                   String maxMarks, String numOfQuestions
            , boolean isActive) {
        this.quizTitle = quizTitle;
        this.quizDescription = quizDescription;
        this.maxMarks = maxMarks;
        this.numOfQuestions = numOfQuestions;
        this.isActive = isActive;
    }

    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    public String getQuizDescription() {
        return quizDescription;
    }

    public void setQuizDescription(String quizDescription) {
        this.quizDescription = quizDescription;
    }

    public String getMaxMarks() {
        return maxMarks;
    }

    public void setMaxMarks(String maxMarks) {
        this.maxMarks = maxMarks;
    }

    public String getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(String numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
