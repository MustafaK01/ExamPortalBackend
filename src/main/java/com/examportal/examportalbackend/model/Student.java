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

    private String studentEmail;
    private String studentName;


    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<SolvedQuiz> solvedQuizzes;


    public Student() {
    }

    public Student(Long userStudentId, User studentId,String studentEmail, String studentName) {
        this.userStudentId = userStudentId;
        this.studentId = studentId;
        this.studentEmail = studentEmail;
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

    public User getStudentId() {
        return studentId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
