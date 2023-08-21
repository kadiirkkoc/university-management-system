package com.system.management.university.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "year")
    private String semester;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "students")
    private List<Lesson>lessons;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
