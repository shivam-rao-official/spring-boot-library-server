package com.library.librarymanagementsystem.repository;

import com.library.librarymanagementsystem.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

//    public Books showActiveBooks();

    @Query(value = "SELECT * FROM lbs_book_master s WHERE isbn = ?1", nativeQuery = true)
    public Books findByISBN(String isbn);

    @Query(value = "SELECT COUNT(1) FROM lbs_book_master", nativeQuery = true)
    public int countOfBooks();

    @Query(value = "SELECT COUNT(1) FROM lbs_book_master where is_stock = 0", nativeQuery = true)
    public int countOfIssuedBooks();

    @Query(value = "SELECT COUNT(1) FROM lbs_book_master where is_stock = 1", nativeQuery = true)
    public int countOfUnIssuedBooks();

    @Query(value = "SELECT is_stock FROM lbs_book_master where isbn = ?1", nativeQuery = true)
    public Object bookIsIssued(String isbn);

    @Query(value = "SELECT * FROM lbs_book_master where is_stock = ?1", nativeQuery = true)
    public List<Books> findAllByFilter(String filter);

}
