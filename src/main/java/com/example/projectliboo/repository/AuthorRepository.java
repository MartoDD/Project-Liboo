package com.example.projectliboo.repository;

import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findAuthorByName(String name);
    List<Author> findAllByIdTrue();
    List<Author> getAuthorByNameContaining(String name);

}
