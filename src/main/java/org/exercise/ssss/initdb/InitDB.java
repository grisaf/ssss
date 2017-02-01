package org.exercise.ssss.initdb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.exercise.ssss.model.ClassEntity;
import org.exercise.ssss.model.StudentEntity;
import org.exercise.ssss.service.ClassService;
import org.exercise.ssss.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger log = LoggerFactory.getLogger(InitDB.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassService classService;

    private List<StudentEntity> students;
    private List<ClassEntity> classes;

    public void populateStudents() {
        students = Arrays.asList(
                new StudentEntity("A", "Afirst", "Alast"),
                new StudentEntity("B", "Bfirst", "Blast"),
                new StudentEntity("C", "Cfirst", "Clast"),
                new StudentEntity("D", "Dfirst", "Dlast"),
                new StudentEntity("E", "Efirst", "Elast"));
        students.stream().forEach(student -> studentService.save(student));
        log.info("Students populated");
    }

    public void populateClasses() {
        classes = Arrays.asList(
                new ClassEntity("Acode", "Atitle", "Adescription"),
                new ClassEntity("Bcode", "Btitle", "Bdescription"),
                new ClassEntity("Ccode", "Ctitle", "Cdescription"),
                new ClassEntity("Dcode", "Dtitle", "Ddescription"),
                new ClassEntity("Ecode", "Etitle", "Edescription"));
        classes.stream().forEach(classEntity -> classService.save(classEntity));
        log.info("Classes populated");
    }

    public void assignStudentsToClasses() {
        classes.stream().forEach(classEntity -> {
            students.stream()
                .filter(student -> classEntity.getCode().charAt(0) <= student.getStudentId().charAt(0))
                .forEach(student -> {
                    if (classEntity.getStudents() == null) {
                        classEntity.setStudents(new ArrayList<>());
                    }
                    classEntity.getStudents().add(student);
                    classService.save(classEntity);
                });
        });
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        populateStudents();
        populateClasses();
        assignStudentsToClasses();
    }

}
