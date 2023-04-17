package com.example.projectliboo.service.Author;

import com.example.projectliboo.model.dtos.AuthorCreateDto;
import com.example.projectliboo.model.dtos.AuthorEditDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.model.view.AuthorView;
import com.example.projectliboo.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Author> getAllAuthors() {
        return authorRepository.findAllByIdTrue();
    }


    @Override
    public Optional<Author> findAuthorByName(String name) {
        return authorRepository.findAuthorByName(name);
    }

    @Override
    public AuthorView findAuthorById(Long id) {

        Author author = authorRepository.findById(id).orElse(null);

        return modelMapper.map(author, AuthorView.class);
    }

    @Override
    public void editAuthor(Long id, AuthorEditDto authorEditDto) {

        Author author = authorRepository.findById(id).orElse(null);
        author.setName(authorEditDto.getName());
        author.setInformation(authorEditDto.getInformation());
        author.setProfilePicture(authorEditDto.getProfilePicture());
        authorRepository.save(author);

    }

    @Override
    public List<AuthorView> getAllAuthors(String keyword) {
        if (keyword == null) {

            return authorRepository.findAll().stream().map(this::map).collect(Collectors.toList());
        }

        return authorRepository.getAuthorByNameContaining(keyword).stream().map(this::map).collect(Collectors.toList());

    }

    @Override
    public AuthorView map(Author author) {

        AuthorView authorView = new AuthorView();
        authorView.setName(author.getName());
        authorView.setInformation(author.getInformation());
        authorView.setProfilePicture(author.getProfilePicture());
        authorView.setId(author.getId());


        return authorView;
    }

    @Override
    public AuthorEditDto getAuthorByIdForEdit(Long id) {
        Author author=authorRepository.findById(id).orElse(null);

        return mapForEdit(author);
    }

    @Override
    public AuthorEditDto mapForEdit(Author author) {

        AuthorEditDto authorEditDto=new AuthorEditDto();
        authorEditDto.setName(author.getName());
        authorEditDto.setInformation(author.getInformation());
        authorEditDto.setProfilePicture(author.getProfilePicture());

        return authorEditDto;
    }


}
