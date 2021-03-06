package org.exercise.ssss.service;

import java.util.List;

import org.exercise.ssss.dao.StudentRepository;
import org.exercise.ssss.model.StudentEntity;
import org.springframework.stereotype.Component;

@Component
public class StudentService extends SsssService<StudentEntity, Long, StudentRepository> {

    @Override
    public void copyValues(StudentEntity fromStudent, StudentEntity toStudent) {
        toStudent.setStudentId(fromStudent.getStudentId());
        toStudent.setLastName(fromStudent.getLastName());
        toStudent.setFirstName(fromStudent.getFirstName());
    }

    @Override
    public List<StudentEntity> search(String keyword) {
        return getRepository().findAllByStudentIdContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrFirstNameContainsIgnoreCase(keyword, keyword, keyword);
    }

}
