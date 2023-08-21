package com.system.management.university.controller;

import com.system.management.university.dtos.DepartmentDTO;
import com.system.management.university.dtos.LessonDTO;
import com.system.management.university.service.abstracts.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lesson")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(lessonService.getAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable("id") Long id){
        return ResponseEntity.ok(lessonService.getById(id));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody LessonDTO lessonDTO){
        lessonService.addLesson(lessonDTO);
        return ResponseEntity.ok("created");
    }
}
