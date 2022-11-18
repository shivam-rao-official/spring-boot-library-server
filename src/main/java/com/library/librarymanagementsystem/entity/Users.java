package com.library.librarymanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(name = "users_master")
@Entity

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name", nullable = false)
    @NotBlank(message = "Name required")
    @Length(min = 3, max = 30, message = "Name must be between 3 to 30 characters")
    private String name;

    @Column(name = "mail_id")
    private String emailId;


    @Column(name = "password")
    private String pswd;

    @Column(name = "dept")
    private String dept;

    @Column(name = "college_id", unique = true)
    private String collegeId;

    private String createdAt;

    private boolean isActive;

    private String modifiedOn;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
