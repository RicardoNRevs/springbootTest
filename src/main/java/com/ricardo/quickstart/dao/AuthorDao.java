package com.ricardo.quickstart.dao;

import com.ricardo.quickstart.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);
}
