package com.project.Dto.ResponseDto;

import com.project.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentGenderUpdateResponseDto {

    private String studentName;

    private Gender gender;

    private String email;

}
