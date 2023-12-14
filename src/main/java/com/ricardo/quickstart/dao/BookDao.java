package com.ricardo.quickstart.dao;

import com.ricardo.quickstart.domain.Book;

import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findBook(String isbn);
}
