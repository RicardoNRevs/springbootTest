package com.ricardo.quickstart.dao.impl;

import com.ricardo.quickstart.dao.BookDao;
import com.ricardo.quickstart.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.List;

@Component
public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @param book
     */
    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn,title, author_id) VALUES (?,?,?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findBook(String isbn) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1",
                new BookRowMapper(),
                isbn
        );

        return results.stream().findFirst();
    }

    @Override
    public List<Book> findManyBook() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookRowMapper()
        );
    }


    @Override
    public void update(String isbn, Book book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn=?, title=?, author_id=? WHERE isbn=?",
                book.getIsbn(), book.getTitle(), book.getAuthorId(), isbn
        );

    }

    /**
     * @param s
     */
    @Override
    public void delete(String s) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn=?",
                s
        );

    }

    public static class BookRowMapper implements RowMapper<Book>{
        /**
         * @param rs
         * @param rowNum
         * @return
         * @throws SQLException
         */
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                .isbn(rs.getString("isbn"))
                .title(rs.getString("title"))
                .authorId(rs.getLong("author_id"))
                .build();
        }
    }
}
