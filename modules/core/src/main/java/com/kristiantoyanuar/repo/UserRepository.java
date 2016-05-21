package com.kristiantoyanuar.repo;

import com.kristiantoyanuar.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Designed to entity-specific of User model
     * @param userSpecification search criteria
     * @param pageable pagination criteria
     * @return return collection of {@link Page}
     */
    Collection<Page> findAll(Specification<User> userSpecification, Pageable pageable);

}
