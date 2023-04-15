package com.project.Dto.ResponseDto;

import com.project.Enums.Department;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentGetResponseDto {

    private String name;

    private int age;

    private Department department;

    private String mobNo;

    private String email;
}
