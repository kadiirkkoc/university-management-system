package com.system.management.university.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {

    private Long id;
    private String facultyName;
    private String campus;
}
