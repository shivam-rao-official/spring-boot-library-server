package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.IssueReturnBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssueReturnRepository extends JpaRepository<IssueReturnBooks, Long> {

    @Query(value = "SELECT * FROM issue_return_master WHERE book_id = ?2 and user_id = ?1", nativeQuery = true)
    public IssueReturnBooks findByUserIdAndISBN(Long userId, Long bookId);
}
