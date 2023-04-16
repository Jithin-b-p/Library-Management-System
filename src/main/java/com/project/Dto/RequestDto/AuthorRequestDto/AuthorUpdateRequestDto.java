package com.project.Dto.RequestDto.AuthorRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorUpdateRequestDto {

    private int id;

    private String name;

    private int age;

    private String email;

}
