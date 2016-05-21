package com.kristiantoyanuar.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * List of user authorities available on application
 *
 * Created by Kristianto Yanuar on 5/21/16.
 */
public enum ApplicationAuthority implements GrantedAuthority{
    LOGIN,
    USER_SEARCH,
    USER_CREATE,
    USER_EDIT,
    USER_DELETE;

    public String getAuthority() {
        return name();
    }
}
