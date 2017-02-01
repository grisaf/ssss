package org.exercise.ssss.controller;

import java.util.List;

import org.exercise.ssss.dao.ClassRepository;
import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.model.StudentEntity;
import org.exercise.ssss.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

    @Override
    public boolean validateAdd(ClassEntity entity) {
        if (entity.getId() != null) {
            return false;
        }
        return validateEmptyFields(entity);
    }

    @Override
    public boolean validateUpdate(ClassEntity entity) {
        return validateEmptyFields(entity);
    }

    @Override
    public boolean validateDelete(Long id) {
        return super.validateDelete(id) && validateEmptyCollection(id);
    }

    private boolean validateEmptyFields(ClassEntity entity) {
        if (entity.getCode() == null || StringUtils.isEmpty(entity.getCode())) {
            return false;
        }
        if (entity.getTitle() == null || StringUtils.isEmpty(entity.getTitle())) {
            return false;
        }
        if (entity.getDescription() == null || StringUtils.isEmpty(entity.getDescription())) {
            return false;
        }
        return true;
    }

    private boolean validateEmptyCollection(Long id) {
        ClassEntity entity = getService().get(id);
        if (entity != null) {
            if (entity.getStudents().size() == 0) {
                return true;
            }
        }
        return false;
    }

}
