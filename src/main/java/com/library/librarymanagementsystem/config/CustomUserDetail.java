//package com.library.librarymanagementsystem.config;
//
//import com.library.librarymanagementsystem.entity.Staff;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetail implements UserDetails {
//
//    private Staff staff;
//
//    public CustomUserDetail(Staff staff) {
//        this.staff = staff;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections
//                .singleton(new SimpleGrantedAuthority(
//                        staff.isAdmin() ? "ADMIN": "STAFF"));
//    }
//
//    @Override
//    public String getPassword() {
//        return staff.getPswd();
//    }
//
//    @Override
//    public String getUsername() {
//        return staff.getEmailId();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
