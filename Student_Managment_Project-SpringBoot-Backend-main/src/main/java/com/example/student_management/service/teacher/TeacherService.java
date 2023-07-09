package com.example.student_management.service.teacher;

import com.example.student_management.dto.SingleTeacherDto;
import com.example.student_management.dto.TeacherDto;
import com.example.student_management.response.GeneralResponse;

import java.util.List;

public interface TeacherService {
    GeneralResponse addTeacher(Long userId, TeacherDto teacherDto);

    List<TeacherDto> getAllTeachers();

    void deleteTeacher(Long teacherId);

    GeneralResponse updateTeacher(Long teacherId, TeacherDto teacherDto);

    SingleTeacherDto getTeacherById(Long teacherId);
}
