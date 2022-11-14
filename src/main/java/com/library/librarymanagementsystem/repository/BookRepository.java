package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    @Query(value = "SELECT * FROM lbs_book_master s WHERE isbn = ?1", nativeQuery = true)
    public Books findByISBN(String isbn);
}
