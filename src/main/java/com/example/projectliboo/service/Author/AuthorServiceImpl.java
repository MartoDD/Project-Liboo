package com.example.projectliboo.service.Author;

import com.example.projectliboo.model.dtos.AuthorCreateDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAuthor(AuthorCreateDto authorCreateDto) {

        Author author = modelMapper.map(authorCreateDto, Author.class);
        authorRepository.save(author);
    }




    @Override
    public Optional<Author> findAuthorByName(String name) {
        return authorRepository.findAuthorByName(name);
    }
}
