package com.system.management.university.service.abstracts;

import com.system.management.university.dtos.LessonDTO;
import com.system.management.university.dtos.StudentDTO;

import java.util.List;

public interface StudentService {

    List<StudentDTO> getAll();
    StudentDTO getById(Long id);

    void add(LessonDTO lessonDTO);

    void update(Long id, StudentDTO studentDTO);

    void delete(Long id);
}
