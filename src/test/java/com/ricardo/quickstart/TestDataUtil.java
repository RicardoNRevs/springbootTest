package com.ricardo.quickstart;

import com.ricardo.quickstart.domain.Author;
import com.ricardo.quickstart.domain.Book;

public final class TestDataUtil {
    private TestDataUtil(){

    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Ricardo Nain")
                .age(32)
                .build();
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(2L)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(3L)
                .name("Thomas Cronin")
                .age(44)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(4L)
                .name("Jesse A Casey")
                .age(24)
                .build();
    }

    public static Book createTestBook() {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookA() {
        return Book.builder()
                .isbn("978-8-4395-9621-9")
                .title("Robert Foster")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookB() {
        return Book.builder()
                .isbn("978-1-2145-6789-0")
                .title("Shit this f")
                .authorId(1L)
                .build();
    }

    public static Book createTestBookC() {
        return Book.builder()
                .isbn("887-1-2345-6789-0")
                .title("Holly crap")
                .authorId(1L)
                .build();
    }
}
