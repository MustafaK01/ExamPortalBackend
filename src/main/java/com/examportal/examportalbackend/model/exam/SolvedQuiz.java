package com.examportal.examportalbackend.model.exam;

import com.examportal.examportalbackend.model.Student;

import javax.persistence.*;

@Entity
public class SolvedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student student;

    private String score;

    public SolvedQuiz() {
    }

    public SolvedQuiz(Long id, Quiz quiz, Student student, String score) {
        this.id = id;
        this.quiz = quiz;
        this.student = student;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
