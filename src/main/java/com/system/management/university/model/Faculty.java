package com.system.management.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "faculty")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "campus")
    private String campus;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "faculty" , cascade = CascadeType.ALL )
    private Set<Department> departments;
}
