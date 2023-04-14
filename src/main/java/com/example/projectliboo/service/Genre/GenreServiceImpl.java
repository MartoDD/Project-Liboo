package com.example.projectliboo.service.Genre;

import com.example.projectliboo.model.dtos.GenreCreateDto;
import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.enums.GenreEnum;
import com.example.projectliboo.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }



    @Override
    public void addGenre(GenreCreateDto genreCreateDto) {

        Genre genre = new Genre();
        genre.setGenreName(genreCreateDto.getGenreName());
        genreRepository.save(genre);

    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAllByIdTrue();
    }

    @Override
    public Optional<Genre> findGenreByName(String name) {
        return genreRepository.findGenreByGenreName(name);
    }
}
