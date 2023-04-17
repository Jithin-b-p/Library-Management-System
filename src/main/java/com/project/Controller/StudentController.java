package com.project.Controller;

import com.project.Dto.RequestDto.*;
import com.project.Dto.ResponseDto.StudentGenderUpdateResponseDto;
import com.project.Dto.ResponseDto.StudentGetByEmailResponseDto;
import com.project.Dto.ResponseDto.StudentGetResponseDto;
import com.project.Dto.ResponseDto.StudentUpdateMobResponseDto;
import com.project.Entity.Student;
import com.project.Exceptions.StudentNotFoundException;
import com.project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    //building add student REST API.
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequestDto studentRequestDto){

        String response = studentService.addStudent(studentRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    //building update student REST API.
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudentWithId(@PathVariable("id") int studentId, @RequestBody Student student){

        String response = studentService.updateStudentWithId(studentId, student);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //update mobile no of a student REST API.
    @PutMapping("/update-mobNo")
    public ResponseEntity<StudentUpdateMobResponseDto> updateMobNo(@RequestBody StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {

        StudentUpdateMobResponseDto studentUpdateMobResponseDto = studentService.updateMobNo(studentUpdateMobRequestDto);
        return new ResponseEntity<>(studentUpdateMobResponseDto, HttpStatus.OK);

    }

    //delete student by Id REST API.
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudentById(@RequestBody StudentDeleteRequestDto studentDeleteRequestDto){

        String response = studentService.deleteStudentById(studentDeleteRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //delete all students REST API.
    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllStudents(){

        String response = studentService.deleteAllStudents();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //find student by id REST API.
    @GetMapping("/get-student")
    public ResponseEntity<StudentGetResponseDto> getStudent(@RequestBody StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException {

        StudentGetResponseDto getStudent = studentService.getStudentById(studentGetRequestDto);
        return new ResponseEntity<>(getStudent, HttpStatus.OK);

    }

    //create a REST API to get student by Email.
    @GetMapping("/get-student-byEmail")
    public ResponseEntity<StudentGetByEmailResponseDto> getStudentByEmail(@RequestBody StudentGetByEmailRequestDto studentGetByEmailRequestDto) throws StudentNotFoundException {

        StudentGetByEmailResponseDto studentGetByEmailResponseDto = studentService.getStudentByEmail(studentGetByEmailRequestDto);
        return new ResponseEntity<>(studentGetByEmailResponseDto, HttpStatus.OK);

    }

    //build get all students REST API.
    @GetMapping("/get-all-students")
    public ResponseEntity<List<StudentGetResponseDto>> getAllStudent() throws Exception {

        List<StudentGetResponseDto> studentGetResponseDtoList = studentService.getAllStudents();
        return new ResponseEntity<>(studentGetResponseDtoList, HttpStatus.OK);

    }

    //build a REST API to update gender By id.
    @PutMapping("/update-gender")
    public ResponseEntity<StudentGenderUpdateResponseDto> updateStudentGender(@RequestBody StudentGenderUpdateRequestDto studentGenderUpdateRequestDto) throws StudentNotFoundException {

        StudentGenderUpdateResponseDto studentGenderUpdateResponseDto = studentService.updateStudentGender(studentGenderUpdateRequestDto);
        return new ResponseEntity<>(studentGenderUpdateResponseDto, HttpStatus.OK);

    }


}
