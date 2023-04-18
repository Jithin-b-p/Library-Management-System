package com.project.Service.ServiceImplementation;

import com.project.Dto.RequestDto.*;
import com.project.Dto.ResponseDto.*;
import com.project.Entity.Card;
import com.project.Entity.Student;
import com.project.Enums.CardStatus;
import com.project.Exceptions.StudentNotFoundException;
import com.project.Repository.CardRepository;
import com.project.Repository.StudentRepository;
import com.project.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {

        //converting studentDto to student entity.
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setGender(studentRequestDto.getGender());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobNo(studentRequestDto.getMobNo());
        student.setEmail(studentRequestDto.getEmail());
        student.setSportsQuota(studentRequestDto.isSportsQuota());

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

        //deleting student will also delete card because student is the parent of card.
        studentRepository.deleteById(studentDeleteRequestDto.getId());
        return "Student deleted successfully";
    }

    @Override
    public StudentGetResponseDto getStudentById(StudentGetRequestDto studentGetRequestDto) throws StudentNotFoundException {

        Student student;
        try{
            student = studentRepository.findById(studentGetRequestDto.getId()).get();

            Card card = student.getCard();

            CardResponseDto cardResponseDto = new CardResponseDto(
                    card.getId(),
                    card.getIssueDate(),
                    card.getLastUpdated(),
                    card.getCardStatus(),
                    card.getValidTill()
            );
            //prepare the response Dto.
            StudentGetResponseDto studentGetResponseDto = new StudentGetResponseDto(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getGender(),
                    student.getDepartment(),
                    student.getMobNo(),
                    student.getEmail(),
                    cardResponseDto
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

    @Override
    public List<StudentGetResponseDto> getAllStudents() throws Exception {

        List<Student> studentList;
        try{

            studentList = studentRepository.findAll();
            List<StudentGetResponseDto> studentResponseList = new ArrayList<>();

            for(Student student: studentList){

                Card card = student.getCard();
                CardResponseDto cardResponseDto = new CardResponseDto(
                        card.getId(),
                        card.getIssueDate(),
                        card.getLastUpdated(),
                        card.getCardStatus(),
                        card.getValidTill()
                );
                StudentGetResponseDto studentGetResponseDto = new StudentGetResponseDto(
                        student.getId(),
                        student.getName(),
                        student.getAge(),
                        student.getGender(),
                        student.getDepartment(),
                        student.getMobNo(),
                        student.getEmail(),
                        cardResponseDto
                );

                studentResponseList.add(studentGetResponseDto);

            }
            return studentResponseList;

        }catch(Exception e){

            throw new Exception("Empty Student list");
        }
    }

    @Override
    public StudentGenderUpdateResponseDto updateStudentGender(StudentGenderUpdateRequestDto studentGenderUpdateRequestDto) throws StudentNotFoundException {

        Student student;
        try{
            student = studentRepository.findById(studentGenderUpdateRequestDto.getStudentId()).get();
            student.setGender(studentGenderUpdateRequestDto.getGender());

            studentRepository.save(student);

            StudentGenderUpdateResponseDto studentGenderUpdateResponseDto = new StudentGenderUpdateResponseDto(

                    student.getName(),
                    student.getGender(),
                    student.getEmail()

            );

            return studentGenderUpdateResponseDto;
        }catch(Exception e){

            throw new StudentNotFoundException("Invalid student Id");
        }
    }

    @Override
    public String deleteAllStudents() {

        studentRepository.deleteAll();
        return "All students are deleted";

    }
}
