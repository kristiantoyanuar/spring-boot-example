package com.kristiantoyanuar.service;

import com.kristiantoyanuar.exception.ApplicationException;
import com.kristiantoyanuar.model.User;
import com.kristiantoyanuar.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Created by Kristianto Yanuar on 5/21/16.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Collection<Page> search(Specification<User> userSpecification, Pageable pageable) {
        return userRepository.findAll(userSpecification, pageable);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User edit(User user) throws ApplicationException {
        if (user != null && userRepository.exists(user.getId()))
            return userRepository.save(user);
        else throw new ApplicationException("User is not exist");
    }

    public User getById(Long id) throws ApplicationException {
        User existingUser = userRepository.findOne(id);
        if (existingUser != null)
            return existingUser;
        throw new ApplicationException("User is not exist");
    }

    public User getByUsername(String username) throws ApplicationException {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null)
            return existingUser;
        throw new ApplicationException("User is not exist");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return getByUsername(s);
        } catch (ApplicationException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}
