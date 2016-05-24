package com.kristiantoyanuar.test.service;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.User;
import com.kristiantoyanuar.repo.UserRepository;
import com.kristiantoyanuar.service.UserService;
import com.kristiantoyanuar.service.UserServiceImpl;
import com.kristiantoyanuar.test.config.SecurityTestConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * This test intended to test the s
 * Created by Kristianto Yanuar on 5/21/16.
 */

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = SecurityTestConfiguration.class)
@TestExecutionListeners(listeners={
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        WithSecurityContextTestExecutionListener.class})
public class UserServiceMockitoTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    private List<Page> userPages;
    private Specification<User> searchSpecification;
    private Pageable pagination = null;

    @Before
    public void prepareTest() {
        // Prepare search criteria
        searchSpecification = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("username"), "yanuar199");
            }
        };

        // Mock test data
        userPages = new ArrayList<Page>();
        List<User> users = new ArrayList<User>();
        User yanuarUser = new User();
        yanuarUser.setUsername("yanuar199");
        users.add(yanuarUser);
        PageImpl userPage1 = new PageImpl(users);
        userPages.add(userPage1);

        // Setup mock
        when(userRepository.findAll(searchSpecification, pagination)).thenReturn(userPages);
    }

    @Test
    public void testSearchUserByUsername() throws ApplicationException {
        Collection<Page> searchResult = userService.search(searchSpecification, pagination);
        assertEquals(searchResult.iterator().next().getTotalElements(), 1);
        assertEquals(((User)searchResult.iterator().next().getContent().get(0)).getUsername(), "yanuar199");
    }

}
