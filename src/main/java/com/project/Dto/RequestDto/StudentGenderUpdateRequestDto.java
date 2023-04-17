package com.project.Dto.RequestDto;

import com.project.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentGenderUpdateRequestDto {

    private int studentId;

    private Gender gender;
}
