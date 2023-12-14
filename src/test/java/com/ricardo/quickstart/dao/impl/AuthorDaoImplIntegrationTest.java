package com.ricardo.quickstart.dao.impl;

import com.ricardo.quickstart.TestDataUtil;
import com.ricardo.quickstart.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // This annotation resets the database after each test method
public class AuthorDaoImplIntegrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntegrationTest(AuthorDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled(){

        Author author = TestDataUtil.createTestAuthor();

        underTest.create(author);
        underTest.findOne(author.getId());
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.createTestAuthorB();
        underTest.create(authorB);
        Author authorC = TestDataUtil.createTestAuthorC();
        underTest.create(authorC);

        List<Author> result = underTest.findManyAuthor();
        assertThat(result)
                .hasSize(3)
                .containsExactlyInAnyOrder(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        authorA.setName("UPDATED");
        underTest.update(authorA.getId(), authorA);
        Optional<Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author authorA = TestDataUtil.createTestAuthorA();
        underTest.create(authorA);
        underTest.delete(authorA.getId());
        Optional<Author> result = underTest.findOne(authorA.getId());
        assertThat(result).isEmpty();
    }
}
