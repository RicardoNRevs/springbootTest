package com.ricardo.quickstart.dao;

import com.ricardo.quickstart.domain.Author;

import java.util.Optional;
import java.util.List;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> findManyAuthor();

    void update(long id, Author author);

    void delete(Long id);
}
