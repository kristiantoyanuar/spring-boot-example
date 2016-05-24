package com.kristiantoyanuar.service;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.Student;
import com.kristiantoyanuar.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kristianto Yanuar on 5/24/2016.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student create(Student student) throws ApplicationException {
        return studentRepository.save(student);
    }

    public Student getById(Long id) throws ApplicationException {
        Student existingStudent = studentRepository.findOne(id);
        if (existingStudent != null)
            return existingStudent;
        throw new ApplicationException("Student is not exist");
    }
}
