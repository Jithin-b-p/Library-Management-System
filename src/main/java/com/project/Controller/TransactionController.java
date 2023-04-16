package com.project.Controller;

import com.project.Dto.RequestDto.TransactionIssueBookDto;
import com.project.Dto.RequestDto.TransactionReturnBookRequestDto;
import com.project.Dto.ResponseDto.TransactionIssueBookResponseDto;
import com.project.Dto.ResponseDto.TransactionReturnBookResponseDto;
import com.project.Exceptions.BookNotFoundException;
import com.project.Exceptions.CardNotFoundException;
import com.project.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/issueBook")
    //build REST API for issuing book.
    public ResponseEntity<TransactionIssueBookResponseDto> issueBook(@RequestBody TransactionIssueBookDto transactionIssueBookDto) throws BookNotFoundException, CardNotFoundException {

        TransactionIssueBookResponseDto transactionIssueBookResponseDto = transactionService.issueBook(transactionIssueBookDto);
        return new ResponseEntity<>(transactionIssueBookResponseDto, HttpStatus.OK);

    }

    //build REST API for return book.
    @PutMapping("/returnBook")
    public ResponseEntity<TransactionReturnBookResponseDto> returnBook(@RequestBody TransactionReturnBookRequestDto transactionReturnBookRequestDto) throws Exception {

        TransactionReturnBookResponseDto transactionReturnBookResponseDto = transactionService.returnBook(transactionReturnBookRequestDto);
        return new ResponseEntity<>(transactionReturnBookResponseDto, HttpStatus.OK);

    }


}
