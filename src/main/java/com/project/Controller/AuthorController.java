package com.project.Controller;

import com.project.Dto.RequestDto.AuthorDeleteRequestDto;
import com.project.Dto.RequestDto.AuthorRequestDto.AuthorUpdateRequestDto;
import com.project.Entity.Author;
import com.project.Exceptions.AuthorNotFoundException;
import com.project.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody Author author){

        String response = authorService.addAuthor(author);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    //build am author update REST API
    @PutMapping("/update")
    public ResponseEntity<String> updateAuthor(@RequestBody AuthorUpdateRequestDto authorUpdateRequestDto) throws AuthorNotFoundException {

        String message = authorService.updateAuthor(authorUpdateRequestDto);
        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @DeleteMapping("/delete-by-id")
    public ResponseEntity<String> deleteAuthorById(@RequestBody AuthorDeleteRequestDto authorDeleteRequestDto){

        String response = authorService.deleteAuthorById(authorDeleteRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllAuthor(){

        String response = authorService.deleteAllAuthor();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
