package com.example.java_exam_project.controller;

import com.example.java_exam_project.entity.Student;
import com.example.java_exam_project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/students")
    public ResponseEntity<Long> saveEmployee(Student student) {
        Student newStudent = (Student) studentRepository.save(student);
        return new ResponseEntity<Long>(newStudent.getStudentId(), HttpStatus.OK);
    }

    @PutMapping("/students/{id}")
    ResponseEntity<Long> updateStudent(@RequestBody Student newStudent, @PathVariable Long studentId) {
        return studentRepository.findById(studentId).map(student -> {
            student.setStudentId(newStudent.getStudentId());
            student.setName(newStudent.getName());
            student.setAge(newStudent.getAge());
            student.setAverageMark(newStudent.getAverageMark());
            student.setAbsenteeism(newStudent.getAbsenteeism());
            studentRepository.save(student);
            return new ResponseEntity<Long>(student.getStudentId(), HttpStatus.OK);
        }).orElseGet(() -> {
            newStudent.setStudentId(studentId);
            studentRepository.save(newStudent);
            return new ResponseEntity<Long>(newStudent.getStudentId(), HttpStatus.OK);
        });
    }

    @DeleteMapping("/students/{id}")
    ResponseEntity<Long> deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    ResponseEntity<Student> getStudentsById(@PathVariable Long id) {
        return new ResponseEntity<Student>(studentRepository.findById(id).get(), HttpStatus.OK);
    }

    @RequestMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = (List<Student>) studentRepository.findAll();
        return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
    }
}
