package com.system.management.university.service.business;

import com.system.management.university.dtos.FacultyDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Faculty;
import com.system.management.university.repository.FacultyRepository;
import com.system.management.university.service.abstracts.FacultyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyManager implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapperService modelMapperService;

    public FacultyManager(FacultyRepository facultyRepository, ModelMapperService modelMapperService) {
        this.facultyRepository = facultyRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<FacultyDTO> getAll() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = faculties.stream()
                .map(faculty -> modelMapperService.forResponse()
                        .map(faculty,FacultyDTO.class)).collect(Collectors.toList());

        return facultyDTOS;
    }

    @Override
    public FacultyDTO getFacultyById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.get().getId());
        facultyDTO.setFacultyName(faculty.get().getName());
        facultyDTO.setCampus(faculty.get().getCampus());
        return facultyDTO;
    }

    @Override
    public void addFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getFacultyName());
        faculty.setCampus(facultyDTO.getCampus());
        facultyRepository.save(faculty);
    }

    @Override
    public void updateFaculty(Long id,FacultyDTO facultyDTO) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (facultyOptional.isPresent()) {
            Faculty faculty = facultyOptional.get();
            faculty.setName(facultyDTO.getFacultyName());
            faculty.setCampus(facultyDTO.getCampus());
            facultyRepository.save(faculty);
        }
        else {
            throw new NotFoundExceptions("faculty not found with id you provide");
        }
    }

    @Override
    public void deleteFaculty(Long id) {

    }
}
