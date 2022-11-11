package com.mycompany.bookauthor.repository;

import com.mycompany.bookauthor.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByEmail(String email);
}
