package org.exercise.ssss.model.dao;

import java.util.List;

import org.exercise.ssss.model.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {

    public List<ClassEntity> findAllByCodeContainsIgnoreCaseOrTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(String code, String title, String description);

}
