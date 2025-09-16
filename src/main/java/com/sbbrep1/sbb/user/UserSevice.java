package com.sbbrep1.sbb.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserSevice {
    private final SiteUserRepository siteUserRepository;

    public SiteUser create(String username, String email, String password) {
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        siteUser.setPassword(passwordEncoder.encode(password));
        this.siteUserRepository.save(siteUser);
        return siteUser;
    }
}
