package com.kristiantoyanuar.test.repo;

import com.kristiantoyanuar.model.User;
import com.kristiantoyanuar.repo.UserRepository;
import com.kristiantoyanuar.test.config.TransactionalTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionalTestConfiguration.class)
@Sql(scripts = "classpath:script/UserRepositoryTest.sql")
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testSearchByUsername() {
        Specification<User> searchSpecification = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("username"), "yanuar199");
            }
        };

        Collection<Page> result = userRepository.findAll(searchSpecification, null);

        assertEquals(result.iterator().next().getTotalElements(), 1);
        assertEquals(((User)result.iterator().next().getContent().get(0)).getUsername(), "yanuar199");
    }

}
