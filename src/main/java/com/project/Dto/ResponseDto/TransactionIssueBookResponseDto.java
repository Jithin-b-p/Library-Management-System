package com.project.Dto.ResponseDto;

import com.project.Enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionIssueBookResponseDto {

    private String transactionNumber;

    private TransactionStatus transactionStatus;

    private String bookTitle;
}
