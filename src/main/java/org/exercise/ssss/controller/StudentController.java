package org.exercise.ssss.controller;

import java.util.List;

import org.exercise.ssss.dao.StudentRepository;
import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.model.StudentEntity;
import org.exercise.ssss.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

    @Override
    public boolean validateAdd(StudentEntity entity) {
        if (entity.getId() != null) {
            return false;
        }
        return validateEmptyFields(entity);
    }

    @Override
    public boolean validateUpdate(StudentEntity entity) {
        return validateEmptyFields(entity);
    }

    @Override
    public boolean validateDelete(Long id) {
        return super.validateDelete(id) && validateEmptyCollection(id);
    }

    private boolean validateEmptyFields(StudentEntity entity) {
        if (entity.getStudentId() == null || StringUtils.isEmpty(entity.getStudentId())) {
            return false;
        }
        if (entity.getLastName() == null || StringUtils.isEmpty(entity.getLastName())) {
            return false;
        }
        if (entity.getFirstName() == null || StringUtils.isEmpty(entity.getFirstName())) {
            return false;
        }
        return true;
    }

    private boolean validateEmptyCollection(Long id) {
        StudentEntity entity = getService().get(id);
        if (entity != null) {
            if (entity.getClasses().size() == 0) {
                return true;
            }
        }
        return false;
    }

}
