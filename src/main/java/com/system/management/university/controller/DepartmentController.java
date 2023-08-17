package com.system.management.university.controller;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.service.abstracts.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(departmentService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(departmentService.getById(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        departmentService.addDepartment(departmentDTO);
        return ResponseEntity.ok("created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long id,@RequestBody DepartmentDTO departmentDTO){
        departmentService.update(id,departmentDTO);
        return ResponseEntity.ok("updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long id){
        departmentService.delete(id);
        return ResponseEntity.ok("deleted");
    }




}
