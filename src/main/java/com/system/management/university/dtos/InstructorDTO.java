package com.system.management.university.dtos;

import com.system.management.university.model.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<LessonDTO>lessons;
}
