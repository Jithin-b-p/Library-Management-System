package com.project.Controller;

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

}
