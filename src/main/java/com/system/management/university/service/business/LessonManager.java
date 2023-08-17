package com.system.management.university.service.business;

import com.system.management.university.dtos.LessonDTO;
import com.system.management.university.dtos.StudentDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Lesson;
import com.system.management.university.model.Student;
import com.system.management.university.repository.LessonRepository;
import com.system.management.university.repository.StudentRepository;
import com.system.management.university.service.abstracts.LessonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LessonManager implements LessonService {

    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;

    private final ModelMapperService modelMapperService;

    public LessonManager(LessonRepository lessonRepository, StudentRepository studentRepository, ModelMapperService modelMapperService) {
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<LessonDTO> getAll() {
        Optional<List<Lesson>> lessons = Optional.of(lessonRepository.findAll());
        if (!lessons.isPresent()){
            throw new NotFoundExceptions("there is no lesson present");
        }
        List<LessonDTO> lessonDTOS = new ArrayList<>();
        lessons.get().forEach(lesson -> {
            LessonDTO lessonDTO = new LessonDTO();
            lessonDTO.setId(lesson.getId());
            lessonDTO.setName(lesson.getName());
            lessonDTO.setCredit(lesson.getCredit());
            lessonDTO.setSemester(lesson.getSemester());
            List<Student> students = (List<Student>) studentRepository.findAllByLessonId(lesson.getId());
            lessonDTO.setStudents(
                    students.stream().map(student ->
                            modelMapperService.forResponse().map(student,StudentDTO.class)).collect(Collectors.toList()));
                    lessonDTOS.add(lessonDTO);
        });
            return lessonDTOS;
    }

    @Override
    public LessonDTO getById(Long id) {

        Optional<Lesson> lesson = lessonRepository.findById(id);
        LessonDTO lessonDTO = new LessonDTO();
        lessonDTO.setId(lesson.get().getId());
        lessonDTO.setName(lesson.get().getName());
        lessonDTO.setCredit(lesson.get().getCredit());
        lessonDTO.setSemester(lesson.get().getSemester());
        List<Student> students = (List<Student>) studentRepository.findAllByLessonId(lesson.get().getId());
        lessonDTO.setStudents(students.stream().map(
                student -> modelMapperService.forResponse().map(student,StudentDTO.class)).collect(Collectors.toList())
        );
        return lessonDTO;
    }

    @Override
    public void addLesson(LessonDTO lessonDTO) {

    }

    @Override
    public void update(Long id, LessonDTO lessonDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
