package com.examportal.examportalbackend.model;

import javax.persistence.*;

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

    public Student() {
    }

    public Student(Long userStudentId, User studentId, User studentName) {
        this.userStudentId = userStudentId;
        this.studentId = studentId;
        this.studentName = studentName;
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
