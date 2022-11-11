package com.mycompany.bookauthor.repository;

import com.mycompany.bookauthor.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllByAuthorId(Long authorId);
    List<BookEntity> findByTitleContains(String title);
}
