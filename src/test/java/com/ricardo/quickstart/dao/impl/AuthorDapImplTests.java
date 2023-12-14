package com.ricardo.quickstart.dao.impl;

import com.ricardo.quickstart.TestDataUtil;
import com.ricardo.quickstart.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuthorDapImplTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1L), eq("Ricardo Nain"), eq(32)
        );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql(){
        underTest.findOne(1L);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id=? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L)
        );
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql(){
        underTest.findManyAuthor();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any()
        );
    }

    @Test
    public void testThatUpdateGeneratesTheCorrectSql(){
        Author author = TestDataUtil.createTestAuthor();
        underTest.update(author.getId(), author);
        verify(jdbcTemplate).update(
                "UPDATE authors SET id=?, name=?, age=? WHERE id=?", 1L, "Ricardo Nain", 32,1L
        );
    }

    @Test
    public void testThatDeleteGenerateTheCorrectSql(){
        underTest.delete(1L);
        verify(jdbcTemplate).update(
                "DELETE FROM authors WHERE id=?", 1L
        );
    }
}
