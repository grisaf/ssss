package org.exercise.ssss.dao;

import java.util.List;

import org.exercise.ssss.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    public List<StudentEntity> findAllByStudentIdContainsIgnoreCaseOrLastNameContainsIgnoreCaseOrFirstNameContainsIgnoreCase(String studentId, String lastName, String firstName);

}
