package com.project.Service.ServiceImplementation;

import com.project.Dto.RequestDto.*;
import com.project.Dto.ResponseDto.StudentGetByEmailResponseDto;
import com.project.Dto.ResponseDto.StudentGetResponseDto;
import com.project.Dto.ResponseDto.StudentUpdateMobResponseDto;
import com.project.Entity.Card;
import com.project.Entity.Student;
import com.project.Enums.CardStatus;
import com.project.Exceptions.StudentNotFoundException;
import com.project.Repository.StudentRepository;
import com.project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        //converting studentDto to student entity.
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());
        student.setEmail(studentRequestDto.getEmail());

        ///generate a new card for student.
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);

        card.setStudent(student);

        //Set card to the student.
        student.setCard(card);

        studentRepository.save(student);
        LocalDateTime issuedDate = card.getIssueDate();
        card.setValidTill(issuedDate.plusYears(4));
        studentRepository.save(student);
        return "Student added SuccessFully";

    }

    @Override
    public String updateStudentWithId(int studentId, Student student) {

        Student updateStudent = studentRepository.findById(studentId).get();
        updateStudent.setName(student.getName());
        updateStudent.setAge(student.getAge());
        updateStudent.setDepartment(student.getDepartment());
        updateStudent.setMobNo(student.getMobNo());
        
        studentRepository.save(updateStudent);
        return "student updated successfully";
    }


    @Override
    public StudentUpdateMobResponseDto updateMobNo(StudentUpdateMobRequestDto studentUpdateMobRequestDto) throws StudentNotFoundException {

        Student student;
        try{

            student = studentRepository.findById(studentUpdateMobRequestDto.getId()).get();
            student.setMobNo(studentUpdateMobRequestDto.getMobNo());
            Student updatedStudent = studentRepository.save(student);

            //preparing response Dto
            StudentUpdateMobResponseDto studentUpdateMobResponseDto = new StudentUpdateMobResponseDto(
                    updatedStudent.getName(),
                    updatedStudent.getMobNo()
            );

            return studentUpdateMobResponseDto;

        }catch(Exception e){

            throw new StudentNotFoundException("Invalid student ID");
        }



    }

    @Override
    public String deleteStudentById(StudentDeleteRequestDto studentDeleteRequestDto) {

        studentRepository.deleteById(studentDeleteRequestDto.getId());
        return "Student deleted successfully";
    }

    @Override
    public StudentGetResponseDto getStudent(StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException {

        Student student;
        try{
            student = studentRepository.findById(studentGetRequestDto.getId()).get();

            //prepare the response Dto.
            StudentGetResponseDto studentGetResponseDto = new StudentGetResponseDto(
                    student.getName(),
                    student.getAge(),
                    student.getDepartment(),
                    student.getMobNo(),
                    student.getEmail()
            );

            return studentGetResponseDto;

        }catch(Exception e){

            throw new StudentNotFoundException("Student Not Found");
        }


    }

    @Override
    public StudentGetByEmailResponseDto getStudentByEmail(StudentGetByEmailRequestDto studentGetByEmailRequestDto) throws StudentNotFoundException {

        Student student;
        try{
            student = studentRepository.findByEmail(studentGetByEmailRequestDto.getEmail());

            //prepare the response Dto
            StudentGetByEmailResponseDto studentGetByEmailResponseDto = new StudentGetByEmailResponseDto(
                    student.getName(),
                    student.getAge(),
                    student.getDepartment(),
                    student.getMobNo()
            );

            return studentGetByEmailResponseDto;

        }catch (Exception e){

            throw new StudentNotFoundException("No student with given EmailId");
        }
    }
}
