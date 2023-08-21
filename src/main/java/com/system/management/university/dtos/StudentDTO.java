package com.system.management.university.dtos;

import com.system.management.university.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String semester;
    private Long lessonId;
    private Long departmentId;
    private List<LessonDTO> lessons;
}
