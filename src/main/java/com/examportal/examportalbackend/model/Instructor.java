package com.examportal.examportalbackend.model;

import com.examportal.examportalbackend.model.exam.Quiz;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="instructors")
public class Instructor  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInstructorId;

    @OneToOne
    @JoinColumn(name = "userId")
    private User instructorId;

    private String instructorName;

    private String instructorEmail;


    @OneToMany(mappedBy = "instructor",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quizzes;

    public Instructor() {
    }

    public Instructor(Long userInstructorId, User instructorId
            ,String instructorName,String instructorEmail) {
        this.userInstructorId = userInstructorId;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.instructorEmail = instructorEmail;
    }

    public Long getId() {
        return userInstructorId;
    }

    public void setId(Long userInstructorId) {
        this.userInstructorId = userInstructorId;
    }

    public User getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(User instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
}
