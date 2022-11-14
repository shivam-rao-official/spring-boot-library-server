package com.library.librarymanagementsystem.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class BooksDto {

    @NotBlank(message = "Book name required")
    private String bookName;
    @NotBlank(message = "Author name required")
    private String bookAuthorName;
    @NotBlank(message = "Book Price required")
    private String bookPrice;
    private String bookImg;
    private String bookEdition;
    @NotBlank(message = "ISBN required")
    private String bookISBNNumber;
}
