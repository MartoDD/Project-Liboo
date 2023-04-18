package com.example.projectliboo.service.Genre;

import com.example.projectliboo.model.dtos.GenreCreateDto;
import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.enums.GenreEnum;
import com.example.projectliboo.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Genre> getAllGenresByKeyword(String keyword) {
        if (keyword == null) {

            return new ArrayList<>(genreRepository.findAll());
        }

        return new ArrayList<>(genreRepository.findAllByGenreNameContaining(keyword));
    }

    @Override
    public Optional<Genre> findGenreByName(String name) {
        return genreRepository.findGenreByGenreName(name);
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
}
