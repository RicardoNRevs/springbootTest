package com.ricardo.quickstart.dao.impl;

import com.ricardo.quickstart.TestDataUtil;
import com.ricardo.quickstart.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title, author_id) VALUES (?,?,?)"),
                eq("978-1-2345-6789-0"),
                eq("The Shadow in the Attic"),
                eq(1L)
        );
    }

    @Test
    public void testThatFindOneBookGeneratesTheCorrectSql(){
        underTest.findBook("978-1-2345-6789-0");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("978-1-2345-6789-0")
        );
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        underTest.findManyBook();
        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql(){
        Book book = TestDataUtil.createTestBookA();
        underTest.update("978-8-4395-9621-9",book);
        verify(jdbcTemplate).update(
                "UPDATE books SET isbn=?, title=?, author_id=? WHERE isbn=?",
                "978-8-4395-9621-9", "Robert Foster", 1L, "978-8-4395-9621-9"
        );
    }

    @Test
    public void testThatDeleteGeneratesTheCorrectSql(){
        underTest.delete("978-8-4395-9621-9");
        verify(jdbcTemplate).update(
                "DELETE FROM books WHERE isbn=?",
                "978-8-4395-9621-9"
        );
    }
}
