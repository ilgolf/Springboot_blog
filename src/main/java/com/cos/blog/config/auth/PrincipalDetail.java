package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetail implements UserDetails {
    private User user;

    
}
