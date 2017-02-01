package org.exercise.ssss.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exercise.ssss.model.ClassEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClassesRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void addListSearchUpdateGetDelete() {
        String basePath = "/api/classes/";

        String classCode = "Cl Code";
        String classTitle = "Cl Title";
        String classDescription = "Cl Description";
        ClassEntity classParam = new ClassEntity(classCode, classTitle, classDescription);
        ClassEntity newClass = restTemplate.postForObject(basePath, classParam, ClassEntity.class);
        assertNotNull(newClass.getId());
        assertEquals(classCode, newClass.getCode());
        assertEquals(classTitle, newClass.getTitle());
        assertEquals(classDescription, newClass.getDescription());

        List<Map<String, Object>> classesResponse = restTemplate.getForObject(basePath, List.class);
        List<ClassEntity> classes = new ArrayList<>();
        for (Map<String, Object> classResponse : classesResponse) {
            ClassEntity classEntity = new ClassEntity();
            classEntity.setId(((Integer)classResponse.get("id")).longValue());
            classEntity.setCode(classResponse.get("code").toString());
            classEntity.setTitle(classResponse.get("title").toString());
            classEntity.setDescription(classResponse.get("description").toString());
            classes.add(classEntity);
        }
        assertThat(classes.size() != 0);
        assertThat(classes.contains(newClass));

        String keyword = "cl";
        classesResponse = restTemplate.getForObject(basePath + "?keyword" + keyword, List.class);
        classes = new ArrayList<>();
        for (Map<String, Object> classResponse : classesResponse) {
            ClassEntity classEntity = new ClassEntity();
            classEntity.setId(((Integer)classResponse.get("id")).longValue());
            classEntity.setCode(classResponse.get("code").toString());
            classEntity.setTitle(classResponse.get("title").toString());
            classEntity.setDescription(classResponse.get("description").toString());
            classes.add(classEntity);
        }
        assertThat(classes.size() != 0);
        assertThat(classes.contains(newClass));

        String entityPath = basePath + "/{id}";

        String newClassCode = "Cl Code New";
        String newClassTitle = "Cl Title New";
        String newClassDescription = "Cl Description New";
        Long id = newClass.getId();
        newClass.setCode(newClassCode);
        newClass.setTitle(newClassTitle);
        newClass.setDescription(newClassDescription);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        HttpEntity<ClassEntity> ingredientEntity = new HttpEntity<ClassEntity>(newClass);
        newClass = restTemplate.exchange(entityPath, HttpMethod.PUT, ingredientEntity, ClassEntity.class, param).getBody();
        assertEquals(id, newClass.getId());
        assertEquals(newClassCode, newClass.getCode());
        assertEquals(newClassTitle, newClass.getTitle());
        assertEquals(newClassDescription, newClass.getDescription());

        ClassEntity getClass = restTemplate.getForObject(entityPath, ClassEntity.class, param);
        assertEquals(id, getClass.getId());
        assertEquals(newClassCode, getClass.getCode());
        assertEquals(newClassTitle, getClass.getTitle());
        assertEquals(newClassDescription, getClass.getDescription());

        restTemplate.delete(entityPath, param);
        ClassEntity deletedClass = restTemplate.getForObject(entityPath, ClassEntity.class, param);
        assertNull(deletedClass);
    }

}
