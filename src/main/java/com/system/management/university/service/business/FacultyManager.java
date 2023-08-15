package com.system.management.university.service.business;

import com.system.management.university.dtos.FacultyDTO;
import com.system.management.university.exceptions.NotFoundExceptions;
import com.system.management.university.exceptions.ResourceAlreadyExistsException;
import com.system.management.university.mapper.ModelMapperService;
import com.system.management.university.model.Department;
import com.system.management.university.model.Faculty;
import com.system.management.university.repository.DepartmentRepository;
import com.system.management.university.repository.FacultyRepository;
import com.system.management.university.service.abstracts.FacultyService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FacultyManager implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final ModelMapperService modelMapperService;

    private final DepartmentRepository departmentRepository;

    public FacultyManager(FacultyRepository facultyRepository, ModelMapperService modelMapperService, DepartmentRepository departmentRepository) {
        this.facultyRepository = facultyRepository;
        this.modelMapperService = modelMapperService;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<FacultyDTO> getAll() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<FacultyDTO> facultyDTOS = new ArrayList<>();
        faculties.forEach(f-> {
            FacultyDTO  dto = new FacultyDTO();
            dto.setId(f.getId());
            dto.setCampus(f.getCampus());
            dto.setFacultyName(f.getName());
            dto.setDepartments(departmentRepository.findAllByFacultyId(f.getId()));
            facultyDTOS.add(dto);
        });
        return facultyDTOS;
    }

    @Override
    public FacultyDTO getById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(faculty.get().getId());
        facultyDTO.setFacultyName(faculty.get().getName());
        facultyDTO.setCampus(faculty.get().getCampus());
        return facultyDTO;
    }

    @Override
    public void addFaculty(FacultyDTO facultyDTO) {
        List<Faculty> faculties = facultyRepository.findAll();
        for (Faculty faculty : faculties){
            if ((faculty.getName()).equals(facultyDTO.getFacultyName())){
                throw new ResourceAlreadyExistsException("already there is an faculty with this name");
            }
        }
        Faculty faculty = new Faculty();
        faculty.setName(facultyDTO.getFacultyName());
        faculty.setCampus(facultyDTO.getCampus());
        facultyRepository.save(faculty);
    }

    @Override
    public void updateFaculty(Long id,FacultyDTO facultyDTO) {
        Optional<Faculty> facultyOptional = facultyRepository.findById(id);
        if (!facultyOptional.isPresent()) {
            throw new NotFoundExceptions("faculty not found with id you provide");
        }
        Faculty faculty = facultyOptional.get();
        faculty.setName(facultyDTO.getFacultyName());
        faculty.setCampus(facultyDTO.getCampus());
        facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        if (facultyRepository.findById(id).isPresent()){
            facultyRepository.deleteById(id);
        }
        else {
            throw new NotFoundExceptions("faculty not found with id you provide");
        }
    }
}
