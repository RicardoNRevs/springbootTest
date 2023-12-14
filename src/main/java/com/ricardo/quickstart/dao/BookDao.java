package com.ricardo.quickstart.dao;

import com.ricardo.quickstart.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    void create(Book book);

    Optional<Book> findBook(String isbn);

    List<Book> findManyBook();

    void update(String isbn, Book book);

    void delete(String s);
}
