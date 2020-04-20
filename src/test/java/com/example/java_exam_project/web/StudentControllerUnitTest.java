package com.example.java_exam_project.web;

import com.example.java_exam_project.controller.StudentController;
import com.example.java_exam_project.entity.Student;
import com.example.java_exam_project.repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentRepository studentRepository;

    @Test
    public void saveEmployee() throws Exception {
        Student testStudent = new Student(8L, "Dima", 19, 4.5, 65);
        mockMvc.perform(post("/students", testStudent)).andExpect(status().isOk());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void updateStudent() throws Exception {
        Student testStudent = new Student(1L, "Alexa", 29, 7.5, 15);
        mockMvc.perform(put("/students/" + testStudent.getStudentId(), testStudent)).andExpect(status().isOk());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void deleteStudent() throws Exception {
        long testId = 1L;
        mockMvc.perform(put("/students/" + testId)).andExpect(status().isOk());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void getStudentsById() throws Exception {
        long testId = 2L;
        mockMvc.perform(get("/students/" + testId)).andExpect(status().isOk());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void getAllStudents() throws Exception {
        mockMvc.perform(get("/students")).andExpect(status().isOk());
        verify(studentRepository, times(1)).findAll();
    }

}
