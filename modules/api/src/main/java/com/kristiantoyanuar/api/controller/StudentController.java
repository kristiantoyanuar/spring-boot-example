package com.kristiantoyanuar.api.controller;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.Student;
import com.kristiantoyanuar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kristianto Yanuar on 5/24/2016.
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable("id") Long id) throws ApplicationException {
        return studentService.getById(id);
    }

}
