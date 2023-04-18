package com.project.Service.ServiceImplementation;

import com.project.Dto.RequestDto.AuthorDeleteRequestDto;
import com.project.Dto.RequestDto.AuthorRequestDto.AuthorUpdateRequestDto;
import com.project.Entity.Author;
import com.project.Exceptions.AuthorNotFoundException;
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

    @Override
    public String updateAuthor(AuthorUpdateRequestDto authorUpdateRequestDto) throws AuthorNotFoundException {

        Author author;
        try{

            author = authorRepository.findById(authorUpdateRequestDto.getId()).get();
            author.setName(authorUpdateRequestDto.getName());
            author.setAge(authorUpdateRequestDto.getAge());
            author.setEmail(authorUpdateRequestDto.getEmail());

            authorRepository.save(author);
            return "Author updated SuccessFully";
        }catch(Exception e){

            throw new AuthorNotFoundException("Author not Found !!");
        }


    }

    @Override
    public String deleteAuthorById(AuthorDeleteRequestDto authorDeleteRequestDto) {

        authorRepository.deleteById(authorDeleteRequestDto.getAuthorId());
        return "Deleted author successfully";
    }

    @Override
    public String deleteAllAuthor() {
        authorRepository.deleteAll();
        return "Successfully deleted all author";
    }
}
