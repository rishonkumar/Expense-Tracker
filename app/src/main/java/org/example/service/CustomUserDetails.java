package org.example.service;

import org.example.entites.UserInfo;
import org.example.entites.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails {


    private String userName;

    private String password;

    Collection<?extends GrantedAuthority> authorities;

    public CustomUserDetails(UserInfo userInfo) {
        this.userName = userInfo.getUsername();
        this.password = userInfo.getPassword();

        List<GrantedAuthority> authorityList = new ArrayList<>();

        for(UserRole userRole : userInfo.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(userRole.getName().toUpperCase()));
        }
        this.authorities = authorityList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
