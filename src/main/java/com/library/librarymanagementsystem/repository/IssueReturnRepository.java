package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.IssueReturnBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueReturnRepository extends JpaRepository<IssueReturnBooks, Long> {

    @Query(value = "SELECT * FROM issue_return_master WHERE book_id = ?2 and user_id = ?1", nativeQuery = true)
    public IssueReturnBooks findByUserIdAndISBN(Long userId, Long bookId);

    @Query(value = "SELECT * from issue_return_master where user_id = ?1", nativeQuery = true)
    public List<IssueReturnBooks> findAllByEmail(String id);

    @Query(value = "SELECT * from issue_return_master where user_id = ?1 and returned_on is null", nativeQuery = true)
    public List<IssueReturnBooks> findAllActiveByEmail(String id);

    @Query(value = "SELECT * from issue_return_master where user_id = ?1 and returned_on is not null", nativeQuery = true)
    public List<IssueReturnBooks> findAllNonActiveByEmail(String id);
}
