package com.example.projectliboo.service.Genre;

import com.example.projectliboo.model.dtos.GenreCreateDto;
import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.enums.GenreEnum;

import java.util.List;
import java.util.Optional;

public interface GenreService {


    void addGenre(GenreCreateDto genreCreateDto);

    List<Genre> getAllGenres();
    List<Genre>getAllGenresByKeyword(String keyword);

    Optional<Genre> findGenreByName(String name);
    Genre findGenreById(Long id);

}
