package com.library.librarymanagementsystem.service.serviceInterface;

import com.library.librarymanagementsystem.entity.Books;

import java.util.HashMap;

public interface BookService {

    public HashMap<?, ?> bookSummary();

    public boolean removeBook(Books book, String email);
}
