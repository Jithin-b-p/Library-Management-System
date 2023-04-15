package com.project.Service;

import com.project.Dto.RequestDto.StudentDeleteRequestDto;
import com.project.Dto.RequestDto.StudentGetRequestDto;
import com.project.Dto.RequestDto.StudentRequestDto;
import com.project.Dto.RequestDto.UpdateStudentMobRequestDto;
import com.project.Dto.ResponseDto.StudentGetResponseDto;
import com.project.Dto.ResponseDto.UpdateStudentMobResponseDto;
import com.project.Entity.Student;
import com.project.Exceptions.StudentNotFoundException;

public interface StudentService {

    public String addStudent(StudentRequestDto studentRequestDto);

    public String updateStudentWithId(int studentId, Student student);

    public UpdateStudentMobResponseDto updateMobNo(UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException;

    public String deleteStudentById(StudentDeleteRequestDto studentDeleteRequestDto);

    public StudentGetResponseDto getStudent(StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException;
}
