package com.library.librarymanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "staff_master")
@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "staff_name", nullable = false)
    private String name;

    @Column(name = "mail_id")
    private String emailId;


    @Column(name = "password")
    private String pswd;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "college_id", unique = true)
    private String collegeId;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "modified_on")
    private String modified_on;

    @Column(name = "created_by")
    private Long createdBy;
}
