package com.example.java_exam_project.repository;


import com.example.java_exam_project.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
