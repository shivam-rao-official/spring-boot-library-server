package com.library.librarymanagementsystem.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class IssueReturnBooksDto{
    private final String issueOn;
    private final String issuedTill;
}