package com.library.librarymanagementsystem.dto;

import com.library.librarymanagementsystem.entity.Staff;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;

@Data
public class StaffDto{
    @NotBlank(message = "Name required")
    @Length(min = 3, max = 30, message = "Name must be between 3 to 30 characters")
    private final String name;
    @Email
//    @Pattern(regexp = "^@gmail.com", message = "Email must be a valid gmail id")
    private final String emailId;
//    @Pattern(
//            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
//            message = "Password must contain a number, alphabet and special character")
    private final String pswd;
    @NotBlank(message = "College ID no required")
    private final String collegeId;
}