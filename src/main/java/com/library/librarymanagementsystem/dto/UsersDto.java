package com.library.librarymanagementsystem.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UsersDto implements Serializable {
    @NotBlank(message = "Name required")
    @Length(min = 3, max = 30, message = "Name must be between 3 to 30 characters")
    private final String name;
    @Email
//    @Pattern(regexp = "@gmail.com", message = "Email must be a valid gmail id")
    private final String emailId;
//    @Pattern(
//            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8,20}$",
//            message = "Password must contain a number, alphabet and special character")
    private final String pswd;
    @NotBlank(message = "Department required")
    private final String dept;
    @NotBlank(message = "College ID no required")
    private final String collegeId;
}