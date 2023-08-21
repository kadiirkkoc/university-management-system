package com.system.management.university.service.business;


import com.system.management.university.dtos.LessonDTO;
import com.system.management.university.dtos.StudentDTO;
import com.system.management.university.model.Student;
import com.system.management.university.repository.LessonRepository;
import com.system.management.university.repository.StudentRepository;
import com.system.management.university.service.abstracts.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentManager implements StudentService {
    private final StudentRepository studentRepository;

    public StudentManager(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAll() {
        Optional<List<Student>> students = Optional.of(studentRepository.findAll());
        List<StudentDTO> studentDTOS = new ArrayList<>();

        students.get().forEach(student -> {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setFirstName(student.getFirstName());
            studentDTO.setLastName(student.getLastName());
        });

        return null;
    }

    @Override
    public StudentDTO getById(Long id) {
        return null;
    }

    @Override
    public void add(LessonDTO lessonDTO) {

    }

    @Override
    public void update(Long id, StudentDTO studentDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
