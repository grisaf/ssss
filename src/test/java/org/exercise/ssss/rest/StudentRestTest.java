package org.exercise.ssss.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.exercise.ssss.model.StudentEntity;
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
public class StudentRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @Test
    public void addListSearchUpdateGetDelete() {
        String basePath = "/api/students/";

        String studentId = "StID";
        String studentLastName = "St Last Name";
        String studentFirstName = "St First Name";
        StudentEntity studentParam = new StudentEntity(studentId, studentLastName, studentFirstName);
        StudentEntity newStudent = restTemplate.postForObject(basePath, studentParam, StudentEntity.class);
        assertNotNull(newStudent.getId());
        assertEquals(studentId, newStudent.getStudentId());
        assertEquals(studentLastName, newStudent.getLastName());
        assertEquals(studentFirstName, newStudent.getFirstName());

        List<Map<String, Object>> studentsResponse = restTemplate.getForObject(basePath, List.class);
        List<StudentEntity> students = new ArrayList<>();
        for (Map<String, Object> studentResponse : studentsResponse) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(((Integer)studentResponse.get("id")).longValue());
            studentEntity.setStudentId(studentResponse.get("studentId").toString());
            studentEntity.setLastName(studentResponse.get("lastName").toString());
            studentEntity.setFirstName(studentResponse.get("firstName").toString());
            students.add(studentEntity);
        }
        assertThat(students.size() != 0);
        assertThat(students.contains(newStudent));

        String keyword = "st";
        studentsResponse = restTemplate.getForObject(basePath + "?keyword" + keyword, List.class);
        students = new ArrayList<>();
        for (Map<String, Object> studentResponse : studentsResponse) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setId(((Integer)studentResponse.get("id")).longValue());
            studentEntity.setStudentId(studentResponse.get("studentId").toString());
            studentEntity.setLastName(studentResponse.get("lastName").toString());
            studentEntity.setFirstName(studentResponse.get("firstName").toString());
            students.add(studentEntity);
        }
        assertThat(students.size() != 0);
        assertThat(students.contains(newStudent));

        String entityPath = basePath + "/{id}";

        String newStudentId = "StID New";
        String newStudentLastName = "St Last Name New";
        String newStudentFirstName = "St First Name New";
        Long id = newStudent.getId();
        newStudent.setStudentId(newStudentId);
        newStudent.setLastName(newStudentLastName);
        newStudent.setFirstName(newStudentFirstName);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        HttpEntity<StudentEntity> ingredientEntity = new HttpEntity<StudentEntity>(newStudent);
        newStudent = restTemplate.exchange(entityPath, HttpMethod.PUT, ingredientEntity, StudentEntity.class, param).getBody();
        assertEquals(id, newStudent.getId());
        assertEquals(newStudentId, newStudent.getStudentId());
        assertEquals(newStudentLastName, newStudent.getLastName());
        assertEquals(newStudentFirstName, newStudent.getFirstName());

        StudentEntity getStudent = restTemplate.getForObject(entityPath, StudentEntity.class, param);
        assertEquals(id, getStudent.getId());
        assertEquals(newStudentId, getStudent.getStudentId());
        assertEquals(newStudentLastName, getStudent.getLastName());
        assertEquals(newStudentFirstName, getStudent.getFirstName());

        restTemplate.delete(entityPath, param);
        StudentEntity deletedStudent = restTemplate.getForObject(entityPath, StudentEntity.class, param);
        assertNull(deletedStudent);
    }

}
