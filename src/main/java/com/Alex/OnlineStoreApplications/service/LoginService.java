package com.Alex.OnlineStoreApplications.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
    UserDetails loadUserByUsername(String email);
}
