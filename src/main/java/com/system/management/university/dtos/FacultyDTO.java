package com.system.management.university.dtos;

import com.system.management.university.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {

    private Long id;
    private String facultyName;
    private String campus;
    private Set<DepartmentDTO>departments;
}
