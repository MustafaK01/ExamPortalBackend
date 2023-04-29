package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.model.Instructor;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.repository.InstructorRepository;
import com.examportal.examportalbackend.repository.UserRepository;
import com.examportal.examportalbackend.service.abstracts.InstructorService;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void addInstructor(User user) {
        if (user!=null){
            Instructor instructor = new Instructor();
            instructor.setInstructorId(user);
            instructor.setInstructorEmail(user.getEmail());
            instructor.setInstructorName(user.getUserName());
            this.instructorRepository.save(instructor);
        }else throw new UserNotFoundException("");
    }
}
