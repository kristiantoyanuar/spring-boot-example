package com.kristiantoyanuar.test.service;

import com.kristiantoyanuar.service.UserService;
import com.kristiantoyanuar.test.config.SecurityTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityTestConfiguration.class)
@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class UserServiceJUnitTest {

    @Autowired
    private UserService userService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testSearchUserByUsernameInsufficientAuthority() {
        userService.search(null, null);
    }

    @Test
    @WithMockUser(authorities = "USER_SEARCH")
    public void testSearchUserByUsername() {
        userService.search(null, null);
    }

}
