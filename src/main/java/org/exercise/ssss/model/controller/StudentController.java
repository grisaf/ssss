package org.exercise.ssss.model.controller;

import java.util.List;

import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.model.StudentEntity;
import org.exercise.ssss.model.dao.StudentRepository;
import org.exercise.ssss.model.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController extends SsssController<StudentEntity, Long, StudentRepository, StudentService> {

    @RequestMapping(value = "/{id}/classes", method = RequestMethod.GET)
    public ResponseEntity<List<ClassEntity>> getClasses(@PathVariable Long id) {
        return ResponseEntity.ok(getService().get(id).getClasses());
    }

}
