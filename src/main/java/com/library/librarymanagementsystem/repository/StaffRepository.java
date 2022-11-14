package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    boolean existsByEmailId(String emailId);

    @Query(value = "SELECT id FROM staff_master WHERE mail_id = ?1", nativeQuery = true)
    Long findUserIdByEmailId(String emailId);

    Staff findByEmailId(String emailId);
    boolean existsByCollegeId(String collegeId);

    @Query(value = "SELECT is_admin FROM staff_master WHERE mail_id = ?1", nativeQuery = true)
    boolean findAdminIdByEmailId(String emailId);
}
