package com.project.Controller;

import com.project.Dto.RequestDto.StudentDeleteRequestDto;
import com.project.Dto.RequestDto.StudentGetRequestDto;
import com.project.Dto.RequestDto.StudentRequestDto;
import com.project.Dto.RequestDto.UpdateStudentMobRequestDto;
import com.project.Dto.ResponseDto.StudentGetResponseDto;
import com.project.Dto.ResponseDto.UpdateStudentMobResponseDto;
import com.project.Entity.Student;
import com.project.Exceptions.StudentNotFoundException;
import com.project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<UpdateStudentMobResponseDto> updateMobNo(@RequestBody UpdateStudentMobRequestDto updateStudentMobRequestDto) throws StudentNotFoundException {

        UpdateStudentMobResponseDto updateStudentMobResponseDto = studentService.updateMobNo(updateStudentMobRequestDto);
        return new ResponseEntity<>(updateStudentMobResponseDto, HttpStatus.OK);

    }

    //delete student by Id REST API.
    @DeleteMapping("delete")
    public ResponseEntity<String> deleteStudentById(@RequestBody StudentDeleteRequestDto studentDeleteRequestDto){

        String response = studentService.deleteStudentById(studentDeleteRequestDto);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //find student by id.
    @GetMapping("/get-student")
    public ResponseEntity<StudentGetResponseDto> getStudent(@RequestBody StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException {

        StudentGetResponseDto getStudent = studentService.getStudent(studentGetRequestDto);
        return new ResponseEntity<>(getStudent, HttpStatus.OK);

    }


}
