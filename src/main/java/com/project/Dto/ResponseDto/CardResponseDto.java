package com.project.Dto.ResponseDto;

import com.project.Enums.CardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardResponseDto {

    private int id;

    private LocalDateTime issueDate;

    private LocalDateTime lastUpdated;

    private CardStatus cardStatus;

    private LocalDateTime validTill;
}
