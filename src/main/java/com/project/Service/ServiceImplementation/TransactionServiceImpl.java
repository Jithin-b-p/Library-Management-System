package com.project.Service.ServiceImplementation;

import com.project.Dto.RequestDto.TransactionIssueBookDto;
import com.project.Dto.ResponseDto.TransactionIssueBookResponseDto;
import com.project.Entity.Book;
import com.project.Entity.Card;
import com.project.Entity.Transaction;
import com.project.Enums.CardStatus;
import com.project.Enums.TransactionStatus;
import com.project.Exceptions.BookNotFoundException;
import com.project.Exceptions.CardNotFoundException;
import com.project.Repository.BookRepository;
import com.project.Repository.CardRepository;
import com.project.Repository.TransactionRepository;
import com.project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Override
    public TransactionIssueBookResponseDto issueBook(TransactionIssueBookDto transactionIssueBookDto) throws CardNotFoundException, BookNotFoundException {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        //check the card is valid or not.
        Card card;
        try{

            card = cardRepository.findById(transactionIssueBookDto.getCardId()).get();

        }catch(Exception e){

            transaction.setTransactionStatus(TransactionStatus.FAILED);

            //store transaction.
            transactionRepository.save(transaction);

            throw new CardNotFoundException("Card Invalid");
        }

        Book book;
        try{
            book = bookRepository.findById(transactionIssueBookDto.getBookId()).get();

        }catch(Exception e){

            transaction.setTransactionStatus(TransactionStatus.FAILED);

            //store transaction.
            transactionRepository.save(transaction);

            throw new BookNotFoundException("Book Invalid");
        }

        //card is not activated.
        if(card.getCardStatus() != CardStatus.ACTIVATED){

            transaction.setTransactionStatus(TransactionStatus.FAILED);

            //store transaction.
            transactionRepository.save(transaction);

            throw new BookNotFoundException("Card is not Active..!!");


        }

        //book is already issued.
        if(book.isIssued()){

            transaction.setTransactionStatus(TransactionStatus.FAILED);

            //store transaction.
            transactionRepository.save(transaction);

            throw new BookNotFoundException("Book is already issued..!!");
        }

        transaction.setCard(card);
        transaction.setBook(book);
        book.setIssued(true);

        //adding card and transaction.
        book.setCard(card);
        book.getTransactionList().add(transaction);

        //adding book and transaction.
        card.getBookList().add(book);
        card.getTransactionList().add(transaction);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //optimal way of saving (Card is the parent of book and transaction.)
        cardRepository.save(card);

        //Prepare Response Dto.
        TransactionIssueBookResponseDto transactionIssueBookResponseDto = new TransactionIssueBookResponseDto(
                transaction.getTransactionNumber(),
                transaction.getTransactionStatus(),
                book.getTitle()
        );

        return transactionIssueBookResponseDto;
    }
}
