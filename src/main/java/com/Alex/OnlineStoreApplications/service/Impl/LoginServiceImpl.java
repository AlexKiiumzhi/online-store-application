package com.Alex.OnlineStoreApplications.service.Impl;

import com.Alex.OnlineStoreApplications.entity.User;
import com.Alex.OnlineStoreApplications.repository.UserRepository;
import com.Alex.OnlineStoreApplications.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LoginServiceImpl implements UserDetailsService, LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email){

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            Authentication authentication = new Authentication() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return optionalUser.get().getAuthorities();
                }
                @Override
                public Object getCredentials() {
                    return null;
                }

                @Override
                public Object getDetails() {
                    return null;
                }

                @Override
                public Object getPrincipal() {
                    return optionalUser.get();
                }

                @Override
                public boolean isAuthenticated() {
                    return true;
                }

                @Override
                public void setAuthenticated(boolean b) throws IllegalArgumentException {
                }

                @Override
                public String getName() {
                    return null;
                }
            };
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return optionalUser.get();
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
