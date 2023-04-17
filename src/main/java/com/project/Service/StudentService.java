package com.project.Service;

import com.project.Dto.RequestDto.*;
import com.project.Dto.ResponseDto.StudentGenderUpdateResponseDto;
import com.project.Dto.ResponseDto.StudentGetByEmailResponseDto;
import com.project.Dto.ResponseDto.StudentGetResponseDto;
import com.project.Dto.ResponseDto.StudentUpdateMobResponseDto;
import com.project.Entity.Student;
import com.project.Exceptions.StudentNotFoundException;

import java.util.List;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public String updateStudentWithId(int studentId, Student student);

    public StudentUpdateMobResponseDto updateMobNo(StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException;

    public String deleteStudentById(StudentDeleteRequestDto studentDeleteRequestDto);

    public StudentGetResponseDto getStudentById(StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException;

    public StudentGetByEmailResponseDto getStudentByEmail(StudentGetByEmailRequestDto studentGetByEmailRequestDto) throws StudentNotFoundException;

    public List<StudentGetResponseDto> getAllStudents() throws Exception;

    public StudentGenderUpdateResponseDto updateStudentGender(StudentGenderUpdateRequestDto studentGenderUpdateRequestDto) throws StudentNotFoundException;

    public String deleteAllStudents();
}
