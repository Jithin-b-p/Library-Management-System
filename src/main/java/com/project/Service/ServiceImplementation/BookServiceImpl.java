package com.project.Service.ServiceImplementation;

import com.project.Entity.Author;
import com.project.Entity.Book;
import com.project.Repository.AuthorRepository;
import com.project.Repository.BookRepository;
import com.project.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public String addBook(Book book) throws Exception {

        Author author;
        try{

            author = authorRepository.findById(book.getAuthor().getId()).get();

        }catch (Exception e){
            throw new Exception("Author not present");
        }

        //add book to author
        author.getBooks().add(book);
        //setting author in book.
        book.setAuthor(author);

        authorRepository.save(author);
        return "Book added successfully";
    }
}
