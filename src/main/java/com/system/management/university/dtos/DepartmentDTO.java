package com.system.management.university.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
        private Long id;
        private String name;
        private Long facultyId;
        private List<LessonDTO> lessons;
}
