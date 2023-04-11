package com.example.projectliboo.service.Author;

import com.example.projectliboo.model.dtos.AuthorCreateDto;
import com.example.projectliboo.model.entity.Author;
import com.example.projectliboo.repository.AuthorRepository;
import com.example.projectliboo.util.ImgUtil;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addAuthor(AuthorCreateDto authorCreateDto) {

        Author author = new Author();
        author.setName(authorCreateDto.getName());
        author.setInformation(authorCreateDto.getInformation());
        author.setProfilePicture(ImgUtil.compressImage(authorCreateDto.getProfilePicture()));

        authorRepository.save(author);

    }

    @Override
    public AuthorCreateDto getAllAuthors() {

        Author author =authorRepository.getAuthorByName("Steven King");
        AuthorCreateDto displayAuthor = new AuthorCreateDto();
        displayAuthor.setName(author.getName());
        displayAuthor.setInformation(author.getInformation());
        String imageBase64 = Base64.getEncoder().encodeToString(author.getProfilePicture());
        displayAuthor.setProfilePicture(ImgUtil.decompressImage(author.getProfilePicture()));


        return displayAuthor;
    }
}
