package com.kristiantoyanuar.service;

import com.kristiantoyanuar.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;

/**
 * All application logics related with "User"
 *
 * Created by Kristianto Yanuar on 5/21/16.
 */
public interface UserService {

    /**
     * Search user based on given specification. Only user has
     * <pre>com.kristiantoyanuar.model.ApplicationAuthority.USER_SEARCH}</pre> authority can access use this logic.
     *
     * @param userSpecification search criteria
     * @param pageable pagination criteria
     * @return collections of {@link Page}
     */
    @PreAuthorize("hasAuthority('USER_SEARCH')")
    Collection<Page> search(Specification<User> userSpecification, Pageable pageable);

}
