package com.examportal.examportalbackend.model;

import javax.persistence.*;

@Entity
@Table(name="instructors")
public class Instructor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInstructorId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructorId",referencedColumnName = "userId")
    private User instructorId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructorName",referencedColumnName = "userName")
    private User instructorName;

    public Instructor() {
    }

    public Instructor(Long userInstructorId, User instructorId, User instructorName) {
        this.userInstructorId = userInstructorId;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
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

    public User getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(User instructorName) {
        this.instructorName = instructorName;
    }
}
