package com.kristiantoyanuar.service;

import com.kristiantoyanuar.exception.ApplicationException;
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
     * Required <pre>com.kristiantoyanuar.model.ApplicationAuthority.USER_SEARCH}</pre> authority.
     *
     * @param userSpecification search criteria
     * @param pageable pagination criteria
     * @return collections of {@link Page}
     * @throws ApplicationException throw an exception if failed search the user
     */
    @PreAuthorize("hasAuthority('USER_SEARCH')")
    Collection<Page> search(Specification<User> userSpecification, Pageable pageable) throws ApplicationException;

    /**
     * Method that allow user creation.
     * Required <pre>com.kristiantoyanuar.model.ApplicationAuthority.USER_CREATE}</pre> authority.
     * @param user newly created user with no given id
     * @return return the user if successfully created
     * @throws  ApplicationException throw an exception if failed create the user
     */
    @PreAuthorize("hasAuthority('USER_CREATE')")
    User create(User user) throws ApplicationException;

    /**
     * Method that allow user modification.
     * Required <pre>com.kristiantoyanuar.model.ApplicationAuthority.USER_EDIT}</pre> authority.
     * @param user user with existing id
     * @return return the user if successfully modified
     * @throws  ApplicationException if failed update the user
     */
    @PreAuthorize("hasAuthority('USER_EDIT')")
    User edit(User user) throws ApplicationException;

    /**
     * Find user by id
     * @param id the requested id
     * @return the user if exist
     * @throws ApplicationException throw an exception if not found
     */
    @PreAuthorize("hasAuthority('USER_SEARCH')")
    User getById(Long id) throws ApplicationException;

    /**
     * Find user by username
     * @param username the username
     * @return the user if exist
     * @throws ApplicationException throw and exception if not found
     */
    @PreAuthorize("hasAuthority('USER_SEARCH')")
    User getByUsername(String username) throws ApplicationException;

}
