package com.example.student_management.service.fee;

import com.example.student_management.dto.FeeDto;
import com.example.student_management.model.Fee;
import com.example.student_management.model.Student;
import com.example.student_management.repository.FeeRepository;
import com.example.student_management.repository.StudentRepository;
import com.example.student_management.response.GeneralResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeeServiceImpl implements FeeService{

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public GeneralResponse payFee(Long studentId, FeeDto feeDto) {
        GeneralResponse response = new GeneralResponse();
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()){
            Fee fee = new Fee();
            fee.setAmount(feeDto.getAmount());
            fee.setStudent(optionalStudent.get());
            fee.setUser(optionalStudent.get().getUser());
            feeRepository.save(fee);
            response.setMessage("Fee paid successfully!");
            response.setStatus(HttpStatus.CREATED);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND);
            response.setMessage("Student no found");
        }
        return response;
    }
}
