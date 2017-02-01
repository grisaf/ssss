package org.exercise.ssss.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.exercise.ssss.model.StudentEntity;
import org.exercise.ssss.service.StudentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void addListSearchUpdateGetDelete() {
        String studentId = "StID";
        String studentLastName = "St Last Name";
        String studentFirstName = "St First Name";

        StudentEntity student = new StudentEntity(studentId, studentLastName, studentFirstName);
        student = studentService.add(student);
        assertNotNull(student.getId());
        assertEquals(studentId, student.getStudentId());
        assertEquals(studentLastName, student.getLastName());
        assertEquals(studentFirstName, student.getFirstName());

        List<StudentEntity> students = studentService.list();
        assertThat(students.contains(student));

        String keyword = "st";
        students = studentService.search(keyword);
        assertThat(students.contains(student));

        String newStudentId = "StID New";
        String newStudentLastName = "St Last Name New";
        String newStudentFirstName = "St First Name New";
        Long id = student.getId();
        student.setStudentId(newStudentId);
        student.setLastName(newStudentLastName);
        student.setFirstName(newStudentFirstName);
        student = studentService.update(id, student);
        assertEquals(id, student.getId());
        assertEquals(newStudentId, student.getStudentId());
        assertEquals(newStudentLastName, student.getLastName());
        assertEquals(newStudentFirstName, student.getFirstName());

        student = studentService.get(id);
        assertEquals(id, student.getId());
        assertEquals(newStudentId, student.getStudentId());
        assertEquals(newStudentLastName, student.getLastName());
        assertEquals(newStudentFirstName, student.getFirstName());

        studentService.delete(id);
        student = studentService.get(id);
        assertNull(student);
    }

}
