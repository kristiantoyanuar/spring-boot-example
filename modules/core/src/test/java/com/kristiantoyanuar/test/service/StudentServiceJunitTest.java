package com.kristiantoyanuar.test.service;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.Student;
import com.kristiantoyanuar.model.User;
import com.kristiantoyanuar.service.StudentService;
import com.kristiantoyanuar.test.config.SecurityTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kristianto Yanuar on 5/24/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityTestConfiguration.class)
@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@Rollback
@DirtiesContext
public class StudentServiceJunitTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private StudentService studentService;

    @Test
    @WithMockUser(authorities = "STUDENT_CREATE")
    public void testCreateStudent() throws ApplicationException {
        Student student = new Student();
        student.setCode("672007199");
        student.setBirthDate(new Date());
        student.setFirstName("Kristianto");
        student.setLastName("Yanuar");

        studentService.create(student);

        assertEquals(countRowsInTableWhere("student", "code='672007199' and firstname='Kristianto' and lastname = " +
                "'Yanuar'"), 1);
    }
}
