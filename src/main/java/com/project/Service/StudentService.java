package com.project.Service;

import com.project.Dto.RequestDto.*;
import com.project.Dto.ResponseDto.StudentGetByEmailResponseDto;
import com.project.Dto.ResponseDto.StudentGetResponseDto;
import com.project.Dto.ResponseDto.StudentUpdateMobResponseDto;
import com.project.Entity.Student;
import com.project.Exceptions.StudentNotFoundException;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public String updateStudentWithId(int studentId, Student student);

    public StudentUpdateMobResponseDto updateMobNo(StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException;

    public String deleteStudentById(StudentDeleteRequestDto studentDeleteRequestDto);

    public StudentGetResponseDto getStudent(StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException;

    public StudentGetByEmailResponseDto getStudentByEmail(StudentGetByEmailRequestDto studentGetByEmailRequestDto) throws StudentNotFoundException;
}
