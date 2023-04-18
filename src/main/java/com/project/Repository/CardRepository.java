package com.project.Repository;

import com.project.Entity.Card;
import com.project.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    public void deleteByStudentId(int id);
}
