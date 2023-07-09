package com.example.student_management.service.student;

import com.example.student_management.dto.SingleStudentDto;
import com.example.student_management.dto.StudentDto;
import com.example.student_management.model.Student;
import com.example.student_management.model.User;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.repository.UserRepo;
import com.example.student_management.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public GeneralResponse addStudent(StudentDto studentDto, Long userId) {
        GeneralResponse response = new GeneralResponse();
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            Student student = new Student();
            student.setName(studentDto.getName());
            student.setFatherName(studentDto.getFatherName());
            student.setMotherName(studentDto.getMotherName());
            student.setUser(optionalUser.get());
            studentRepository.save(student);
            response.setMessage("Student created.");
            response.setData(HttpStatus.CREATED);
        } else {
            response.setMessage("User not exist!");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream().map(Student::getStudentDto).collect(Collectors.toList());
    }

    @Override
    public GeneralResponse updateStudent(Long studentId, StudentDto studentDto) {
        GeneralResponse response = new GeneralResponse();
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setMotherName(studentDto.getMotherName());
            student.setFatherName(studentDto.getFatherName());
            student.setName(studentDto.getName());
            studentRepository.save(student);
            response.setStatus(HttpStatus.OK);
            response.setMessage("Student updated successfully!");
        } else {
            response.setMessage("Student not found");
            response.setStatus(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public SingleStudentDto getStudentById(Long studentId) {
        SingleStudentDto singleStudentDto = new SingleStudentDto();
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
            singleStudentDto.setStudentDto(optionalStudent.get().getStudentDto());
        }
        return singleStudentDto;
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public List<StudentDto> searchStudentByName(String title) {
        return studentRepository.findAllByNameContaining(title).stream().map(Student::getStudentDto).collect(Collectors.toList());
    }

}
