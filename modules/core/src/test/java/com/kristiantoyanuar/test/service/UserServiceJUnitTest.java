package com.kristiantoyanuar.test.service;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.User;
import com.kristiantoyanuar.service.UserService;
import com.kristiantoyanuar.test.config.SecurityTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.Assert.*;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityTestConfiguration.class)
@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
@Rollback
public class UserServiceJUnitTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private UserService userService;

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testSearchUserByUsernameInsufficientAuthority() throws ApplicationException {
        userService.search(null, null);
    }

    @Test
    @WithMockUser(authorities = "USER_SEARCH")
    public void testSearchUserByUsername() throws ApplicationException {
        userService.search(null, null);
    }

    @Test
    @WithMockUser(authorities = "USER_CREATE")
    public void testCreateUser() throws ApplicationException {
        User stranger = new User();
        stranger.setUsername("stranger");
        stranger.setEmail("stranger@unknown.com");
        stranger.setPassword("fakepassword123!");

        userService.create(stranger);

        assertNotNull(stranger.getId());
    }

    @Test(expected = AuthenticationCredentialsNotFoundException.class)
    public void testCreateUserInsufficientAuthority() throws ApplicationException {
        User stranger2 = new User();
        stranger2.setUsername("stranger2");
        stranger2.setEmail("stranger2@unknown.com");
        stranger2.setPassword("fakepassword123!");

        userService.create(stranger2);

        assertNotNull(stranger2.getId());
    }



    @Test
    @WithMockUser(authorities = "USER_EDIT")
    public void testEditUser() throws ApplicationException {
        User yanuar199 =

        yanuar199.setUsername("kristianto.yanuar");
        userService.edit(yanuar199);

        assertEquals(countRowsInTableWhere("user", "username=\'yanuar199\'"), 0);
        assertEquals(countRowsInTableWhere("user", "username=\'kristianto.yanuar\'"), 1);
    }

}
