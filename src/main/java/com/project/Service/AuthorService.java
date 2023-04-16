package com.project.Service;

import com.project.Dto.RequestDto.AuthorRequestDto.AuthorUpdateRequestDto;
import com.project.Entity.Author;
import com.project.Exceptions.AuthorNotFoundException;

public interface AuthorService {

    public String addAuthor(Author author);

    public String updateAuthor(AuthorUpdateRequestDto authorUpdateRequestDto) throws AuthorNotFoundException;
}
