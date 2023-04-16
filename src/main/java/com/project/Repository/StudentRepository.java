package com.project.Repository;

import com.project.Entity.Student;
import com.project.Enums.Department;
import com.project.Enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    //custom search criteria
    public Student findByEmail(String email);

    public List<Student> findByGenderAndDepartment(Gender gender, Department department);
}
