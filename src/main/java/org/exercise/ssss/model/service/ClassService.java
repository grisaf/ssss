package org.exercise.ssss.model.service;

import java.util.List;

import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.model.dao.ClassRepository;
import org.springframework.stereotype.Component;

@Component
public class ClassService extends SsssService<ClassEntity, Long, ClassRepository> {

    @Override
    public void copyValues(ClassEntity fromClass, ClassEntity toClass) {
        toClass.setCode(fromClass.getCode());
        toClass.setTitle(fromClass.getTitle());
        toClass.setDescription(fromClass.getDescription());
    }

    @Override
    public List<ClassEntity> search(String keyword) {
        return getRepository().findAllByCodeContainsIgnoreCaseOrTitleContainsIgnoreCaseOrDescriptionContainsIgnoreCase(keyword, keyword, keyword);
    }

}
