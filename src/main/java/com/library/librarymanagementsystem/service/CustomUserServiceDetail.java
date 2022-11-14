//package com.library.librarymanagementsystem.service;
//
//import com.library.librarymanagementsystem.config.CustomUserDetail;
//import com.library.librarymanagementsystem.entity.Staff;
//import com.library.librarymanagementsystem.repository.StaffRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomUserServiceDetail implements UserDetailsService {
//
//    @Autowired
//    private StaffRepository staffRepository;
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Staff isStaffExist = this.staffRepository.findByEmailId(username);
//        if (isStaffExist == null) throw new UsernameNotFoundException("No User found.");
//        return new CustomUserDetail(isStaffExist);
//    }
//}
