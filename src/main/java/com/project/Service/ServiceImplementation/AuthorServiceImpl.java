package com.project.Service.ServiceImplementation;

import com.project.Entity.Author;
import com.project.Repository.AuthorRepository;
import com.project.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addAuthor(Author author) {

        authorRepository.save(author);
        return "Author added successfully";
    }
}
