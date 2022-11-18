package com.library.librarymanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "lbs_book_master")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_name")
    private String bookAuthorName;
    @Column(name = "price")
    private String bookPrice;
    private String bookImg;
    @Column(name = "description")
    private String bookEdition;
    @Column(name = "isbn", unique = true)
    private String bookISBNNumber;
    @Column(name = "added_on")
    private String addedOn;
    @Column(name = "is_stock")
    private boolean isInStock;
    @Column(name = "dept")
    private String dept;



    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;
}
