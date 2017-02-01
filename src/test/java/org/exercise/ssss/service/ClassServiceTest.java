package org.exercise.ssss.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.service.ClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassServiceTest {

    @Autowired
    private ClassService classService;

    @Test
    public void addListSearchUpdateGetDelete() {
        String classCode = "Cl Code";
        String classTitle = "Cl Title";
        String classDescription = "Cl Description";

        ClassEntity classEntity = new ClassEntity(classCode, classTitle, classDescription);
        classEntity = classService.add(classEntity);
        assertNotNull(classEntity.getCode());
        assertEquals(classCode, classEntity.getCode());
        assertEquals(classTitle, classEntity.getTitle());
        assertEquals(classDescription, classEntity.getDescription());

        List<ClassEntity> classes = classService.list();
        assertThat(classes.contains(classEntity));

        String keyword = "cl";
        classes = classService.search(keyword);
        assertThat(classes.contains(classEntity));

        String newClassCode = "Cl Code New";
        String newClassTitle = "Cl Title New";
        String newClassDescription = "Cl Description New";
        Long id = classEntity.getId();
        classEntity.setCode(newClassCode);
        classEntity.setTitle(newClassTitle);
        classEntity.setDescription(newClassDescription);
        classEntity = classService.update(id, classEntity);
        assertEquals(id, classEntity.getId());
        assertEquals(newClassCode, classEntity.getCode());
        assertEquals(newClassTitle, classEntity.getTitle());
        assertEquals(newClassDescription, classEntity.getDescription());

        classEntity = classService.get(id);
        assertEquals(id, classEntity.getId());
        assertEquals(newClassCode, classEntity.getCode());
        assertEquals(newClassTitle, classEntity.getTitle());
        assertEquals(newClassDescription, classEntity.getDescription());

        classService.delete(id);
        classEntity = classService.get(id);
        assertNull(classEntity);
    }

}
