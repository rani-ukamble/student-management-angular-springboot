package com.example.student_management.controller;

import com.example.student_management.dto.TeacherDto;
import com.example.student_management.response.GeneralResponse;
import com.example.student_management.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/teacher/{userId}")
    public GeneralResponse addTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long userId) {
        GeneralResponse response = new GeneralResponse();
        try {
            return teacherService.addTeacher(userId, teacherDto);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong!");
        }
        return response;
    }

    @GetMapping("/teachers")
    public GeneralResponse getAllTeacher() {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(teacherService.getAllTeachers());
            response.setStatus(HttpStatus.OK);
            response.setMessage("Teachers fetched successfully!");
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong!");
        }
        return response;
    }

    @DeleteMapping("teacher/{teacherId}")
    public GeneralResponse deleteTeacher(@PathVariable Long teacherId) {
        GeneralResponse response = new GeneralResponse();
        try {
            teacherService.deleteTeacher(teacherId);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Teacher deleted successfully!");
        } catch (Exception e) {
            response.setMessage("Something went wrong");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping("/getTeacherById/{teacherId}")
    public GeneralResponse getTeacherById(@PathVariable Long teacherId) {
        GeneralResponse response = new GeneralResponse();
        try {
            response.setData(teacherService.getTeacherById(teacherId));
            response.setStatus(HttpStatus.OK);
            response.setMessage("Teacher fetched successfully!");
        } catch (Exception e) {
            response.setMessage("Something went wrong");
            response.setStatus(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PutMapping("/teacher/{teacherId}")
    public GeneralResponse updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDto teacherDto) {
        GeneralResponse response = new GeneralResponse();
        try {
            return teacherService.updateTeacher(teacherId, teacherDto);
        } catch (Exception e) {
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong");
        }
        return response;
    }

}
