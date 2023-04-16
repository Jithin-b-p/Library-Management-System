package com.project.Service;

import com.project.Dto.RequestDto.TransactionIssueBookDto;
import com.project.Dto.ResponseDto.TransactionIssueBookResponseDto;
import com.project.Exceptions.BookNotFoundException;
import com.project.Exceptions.CardNotFoundException;

public interface TransactionService {

    public TransactionIssueBookResponseDto issueBook(TransactionIssueBookDto transactionIssueBookDto) throws CardNotFoundException, BookNotFoundException;
}
