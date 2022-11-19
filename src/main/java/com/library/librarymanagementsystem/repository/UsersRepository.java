package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    //    TO CHECK WHETHER USER EXIST WITH SAME EMAIL OR NOT
    boolean existsByEmailId(String emailId);

    Users findByEmailId(String emailId);

    @Query(value = "SELECT COUNT(1) FROM users_master", nativeQuery = true)
    public int countOfUsers();

    @Query(value = "SELECT COUNT(1) FROM users_master where is_active = 0", nativeQuery = true)
    public int countOfActiveUser();

    @Query(value = "SELECT COUNT(1) FROM users_master where is_active = 1", nativeQuery = true)
    public int countOfInactiveUser();

    @Query(value = "SELECT * FROM users_master WHERE is_active = ?1", nativeQuery = true)
    public List<Users> findAllByFilter(String filter);


}
