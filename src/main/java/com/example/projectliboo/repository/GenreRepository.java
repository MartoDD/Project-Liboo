package com.example.projectliboo.repository;

import com.example.projectliboo.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre>findAllByIdTrue();
    Optional<Genre>findGenreByGenreName(String name);
    List<Genre>findAllByGenreNameContaining(String keyword);

}
