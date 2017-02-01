package org.exercise.ssss.model.controller;

import java.util.List;

import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.model.StudentEntity;
import org.exercise.ssss.model.dao.ClassRepository;
import org.exercise.ssss.model.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/classes")
public class ClassController extends SsssController<ClassEntity, Long, ClassRepository, ClassService> {

    @RequestMapping(value = "/{id}/students", method = RequestMethod.GET)
    public ResponseEntity<List<StudentEntity>> getStudents(@PathVariable Long id) {
        return ResponseEntity.ok(getService().get(id).getStudents());
    }

}
