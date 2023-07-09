package com.example.student_management.service.teacher;

import com.example.student_management.dto.SingleTeacherDto;
import com.example.student_management.dto.TeacherDto;
import com.example.student_management.model.Teacher;
import com.example.student_management.model.User;
import com.example.student_management.repository.TeacherRepository;
import com.example.student_management.repository.UserRepo;
import com.example.student_management.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public GeneralResponse addTeacher(Long userId, TeacherDto teacherDto) {
        GeneralResponse response = new GeneralResponse();
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            Teacher teacher = new Teacher();
            teacher.setName(teacherDto.getName());
            teacher.setDepartment(teacherDto.getDepartment());
            teacher.setUser(optionalUser.get());
            teacherRepository.save(teacher);
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Teacher is created.");
        } else {
            response.setMessage("User not exist");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    @Override
    public GeneralResponse updateTeacher(Long teacherId, TeacherDto teacherDto) {
        GeneralResponse response = new GeneralResponse();
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setDepartment(teacherDto.getDepartment());
            teacher.setName(teacherDto.getName());
            teacherRepository.save(teacher);
            response.setMessage("Teacher updated successfully!");
            response.setStatus(HttpStatus.OK);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Teacher not found!");
        }
        return response;
    }

    @Override
    public SingleTeacherDto getTeacherById(Long teacherId) {
        SingleTeacherDto singleTeacherDto = new SingleTeacherDto();
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            singleTeacherDto.setTeacherDto(optionalTeacher.get().getTeacherDto());
        }
        return singleTeacherDto;
    }


}
