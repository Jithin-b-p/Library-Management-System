package com.project.Entity;

import com.project.Enums.Department;
import com.project.Enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int age;

    @Enumerated(EnumType.STRING)
    private Department department;

    private String mobNo;

    private boolean sportsQuota;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    Card card;

}
