package com.example.projectliboo.service.Genre;

import com.example.projectliboo.model.entity.Genre;
import com.example.projectliboo.model.enums.GenreEnum;
import com.example.projectliboo.repository.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void createGenre(GenreEnum genreEnum) {

        Genre genre = new Genre();
        genre.setGenreName(genreEnum);
        genreRepository.save(genre);

    }
}
