package com.example.student_management.controller;

import com.example.student_management.dto.StudentDto;
import com.example.student_management.response.GeneralResponse;
import com.example.student_management.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student/{userId}")
    public GeneralResponse addStudent(@RequestBody StudentDto studentDto, @PathVariable Long userId) {
        GeneralResponse response = new GeneralResponse();
        try {
            return studentService.addStudent(studentDto, userId);
        } catch (Exception e) {
            response.setMessage("Sorry something went wrong!");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/students")
    public GeneralResponse getAllStudents() {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(studentService.getAllStudents());
            response.setMessage("Students fetched successfully.");
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong!");
        }
        return response;
    }

    @GetMapping("studentById/{studentId}")
    public GeneralResponse getStudentById(@PathVariable Long studentId) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(studentService.getStudentById(studentId));
            response.setStatus(HttpStatus.OK);
            response.setMessage("Student fetched successfully");
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong");
        }
        return response;
    }

    @PutMapping("/student/{studentId}")
    public GeneralResponse updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return studentService.updateStudent(studentId, studentDto);
        } catch (Exception e) {
            response.setMessage("Something went wrong");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @DeleteMapping("student/{studentId}")
    public GeneralResponse deleteStudent(@PathVariable Long studentId) {
        GeneralResponse response = new GeneralResponse();
        try {
            studentService.deleteStudent(studentId);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Student deleted successfully");
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong");
        }
        return response;
    }

    @GetMapping("/search/{title}")
    public GeneralResponse searchStudentByName(@PathVariable String title){
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(studentService.searchStudentByName(title));
            response.setMessage("Students fetched successfully");
            response.setStatus(HttpStatus.OK);
        } catch (Exception e){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong");
        }
        return response;
    }

}
