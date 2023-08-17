package com.system.management.university.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "credit")
    private double credit;

    @Column(name = "semester")
    private String semester;

    @ManyToMany
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


}
