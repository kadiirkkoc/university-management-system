package com.system.management.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Student> students;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private List<Lesson> lessons;

}
