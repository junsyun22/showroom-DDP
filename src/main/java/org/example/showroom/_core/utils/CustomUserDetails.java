package org.example.showroom._core.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
    private final String memberName;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String memberName) {
        super(username, password, authorities);
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }
}