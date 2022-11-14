package com.library.librarymanagementsystem.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "issue_return_master")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueReturnBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long issueId;

    @Column(name = "issued_on")
    private String issueOn;
    @Column(name = "issued_till")
    private String issuedTill;
    @Column(name = "returned_on")
    private String returnOn;

    @OneToOne
    @JoinColumn(name = "book_id")
    private Books books;

    @OneToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
