package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    //    TO CHECK WHETHER USER EXIST WITH SAME EMAIL OR NOT
    boolean existsByEmailId(String emailId);

    Users findByEmailId(String emailId);
}
