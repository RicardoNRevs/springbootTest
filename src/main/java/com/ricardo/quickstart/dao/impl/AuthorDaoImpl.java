package com.ricardo.quickstart.dao.impl;

import com.ricardo.quickstart.dao.AuthorDao;
import com.ricardo.quickstart.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDaoImpl implements AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    public AuthorDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param author
     */
    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(), author.getName(), author.getAge());
    }

    /**
     * @param authorID
     */
    @Override
    public Optional<Author> findOne(long authorID) {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id=? LIMIT 1",
                new AuthorRowMapper(), authorID
        );

        return results.stream().findFirst();
    }

    @Override
    public List<Author> findManyAuthor() {
        return jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorRowMapper()
        );
    }

    @Override
    public void update(long id, Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET id=?, name=?, age=? WHERE id=?",
                author.getId(), author.getName(), author.getAge(), id
        );
    }

    /**
     * @param id
     */
    @Override
    public void delete(Long id) {
        jdbcTemplate.update(
                "DELETE FROM authors WHERE id=?",
                id
        );

    }


    public static class AuthorRowMapper implements RowMapper<Author>{

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException{
            return Author.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
