package com.system.management.university.dtos;

import com.system.management.university.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDTO {

    private Long id;
    private String name;
    private double credit;
    private String semester;
    private Long instructorId;
    private Long studentId;
    private List<StudentDTO> students;


}
