package com.kristiantoyanuar.test.repo;

import com.kristiantoyanuar.model.User;
import com.kristiantoyanuar.repo.UserRepository;
import com.kristiantoyanuar.test.config.TransactionalTestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TransactionalTestConfiguration.class)
@Rollback
@DirtiesContext
public class UserRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    UserRepository userRepository;

    @Test
    @Sql(statements = "insert into user_account(username, email) values ('jojon', 'jojon@gmail.com')")
    public void testSearchByUsername() {
        Specification<User> searchSpecification = new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("username"), "jojon");
            }
        };

        Collection<Page> result = userRepository.findAll(searchSpecification, null);

        assertEquals(result.iterator().next().getTotalElements(), 1);
        assertEquals(((User)result.iterator().next().getContent().get(0)).getUsername(), "jojon");
    }

    @Test
    @Sql(statements = "insert into user_account(username, email) values ('jojon', 'jojon@gmail.com')")
    public void testFindByUsername() {
        assertNotNull(userRepository.findByUsername("jojon"));
    }

}
