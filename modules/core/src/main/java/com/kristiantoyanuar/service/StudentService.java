package com.kristiantoyanuar.service;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by Kristianto Yanuar on 5/24/2016.
 */
public interface StudentService {

    /**
     * Create new student
     * @param student Newly created student without given ID
     * @return the student successfully created
     * @throws ApplicationException throw and exception when create the student
     */
    @PreAuthorize("hasAuthority('STUDENT_CREATE')")
    Student create(Student student) throws ApplicationException;

}
