package com.examportal.examportalbackend.service.concrete;

import com.examportal.examportalbackend.exception.UserNotFoundException;
import com.examportal.examportalbackend.model.Student;
import com.examportal.examportalbackend.model.User;
import com.examportal.examportalbackend.repository.StudentRepository;
import com.examportal.examportalbackend.service.abstracts.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void addStudent(User user) {
        if (user!=null){
            Student student = new Student();
            student.setStudentId(user);
            student.setStudentEmail(user.getEmail());
            student.setStudentName(user.getUserName());
            this.studentRepository.save(student);
        }else throw new UserNotFoundException("");
    }
}
