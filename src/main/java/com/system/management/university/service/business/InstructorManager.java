package com.system.management.university.service.business;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.dtos.InstructorDTO;
import com.system.management.university.dtos.LessonDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Department;
import com.system.management.university.model.Instructor;
import com.system.management.university.model.Lesson;
import com.system.management.university.repository.DepartmentRepository;
import com.system.management.university.repository.InstructorRepository;
import com.system.management.university.repository.LessonRepository;
import com.system.management.university.service.abstracts.InstructorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InstructorManager implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final LessonRepository lessonRepository;
    private final ModelMapperService modelMapperService;

    public InstructorManager(InstructorRepository instructorRepository, LessonRepository lessonRepository, ModelMapperService modelMapperService, DepartmentRepository departmentRepository) {
        this.instructorRepository = instructorRepository;
        this.lessonRepository = lessonRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<InstructorDTO> getAll() {
        Optional<List<Instructor>> instructors = Optional.of(instructorRepository.findAll());
        if (!instructors.isPresent()){
            throw new NotFoundExceptions("there is no faculty present");
        }
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        instructors.get().forEach(instructor -> {
            InstructorDTO instructorDTO = new InstructorDTO();
            instructorDTO.setId(instructor.getId());
            instructorDTO.setFirstName(instructor.getFirstName());
            instructorDTO.setLastName(instructor.getLastName());
            instructorDTO.setEmail(instructor.getEmail());
            List<Lesson> lessons = lessonRepository.findAllByInstructorId(instructor.getId());
            instructorDTO.setLessons(lessons.stream().map(lesson -> modelMapperService.forResponse().map(lesson,LessonDTO.class)).collect(Collectors.toList()));
            instructorDTOS.add(instructorDTO);
        });
        return instructorDTOS;
    }

    @Override
    public InstructorDTO getById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        InstructorDTO instructorDTO = modelMapperService.forResponse().map(instructor,InstructorDTO.class);
        return instructorDTO;
    }

    @Override
    public void add (InstructorDTO instructorDTO) {
        List<Instructor> instructors = instructorRepository.findAll();

        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorDTO.getFirstName());
        instructor.setLastName(instructorDTO.getLastName());
        instructor.setEmail(instructorDTO.getEmail());
        instructorRepository.save(instructor);
    }

    @Override
    public void update(Long id, InstructorDTO instructorDTO) {
        Optional<Instructor> instructorOptional = instructorRepository.findById(id);
        if (!instructorOptional.isPresent()){
            throw new NotFoundExceptions("instructor not found with id you provide");
        }
        Instructor instructor = instructorOptional.get();
        instructor.setEmail(instructorDTO.getEmail());
        instructorRepository.save(instructor);
    }

    @Override
    public void delete(Long id) {



    }
}
