package com.example.student_management.service.student;


import com.example.student_management.dto.SingleStudentDto;
import com.example.student_management.dto.StudentDto;
import com.example.student_management.response.GeneralResponse;

import java.util.List;

public interface StudentService {
    GeneralResponse addStudent(StudentDto studentDto,Long userId);

    List<StudentDto> getAllStudents();

    GeneralResponse updateStudent(Long studentId, StudentDto studentDto);

    SingleStudentDto getStudentById(Long studentId);

    void deleteStudent(Long studentId);

    List<StudentDto> searchStudentByName(String title);
}
