package com.project.Dto.ResponseDto;

import com.project.Enums.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentGetByEmailResponseDto {

    private String name;

    private int age;

    private Department department;

    private String mobNo;

}
