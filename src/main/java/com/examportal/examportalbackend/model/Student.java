package com.examportal.examportalbackend.model;

import com.examportal.examportalbackend.model.exam.SolvedQuiz;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="students")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userStudentId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId",referencedColumnName = "userId")
    private User studentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentName",referencedColumnName = "userName")
    private User studentName;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SolvedQuiz> solvedQuizzes;


    public Student() {
    }

    public Student(Long userStudentId, User studentId, User studentName) {
        this.userStudentId = userStudentId;
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Student(List<SolvedQuiz> solvedQuizzes) {
        this.solvedQuizzes = solvedQuizzes;
    }

    public Long getId() {
        return userStudentId;
    }

    public void setId(Long userStudentId) {
        this.userStudentId = userStudentId;
    }


    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(User studentName) {
        this.studentName = studentName;
    }

    public User getStudentId() {
        return studentId;
    }

    public User getStudentName() {
        return studentName;
    }
}
