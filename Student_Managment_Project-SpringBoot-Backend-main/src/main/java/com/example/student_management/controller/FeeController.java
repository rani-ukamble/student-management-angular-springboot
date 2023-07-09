package com.example.student_management.controller;

import com.example.student_management.dto.FeeDto;
import com.example.student_management.response.GeneralResponse;
import com.example.student_management.service.fee.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FeeController {

    @Autowired
    private FeeService feeService;

    @PostMapping("/fee/{studentId}")
    public GeneralResponse payFee(@PathVariable Long studentId, @RequestBody FeeDto feeDto){
        GeneralResponse response = new GeneralResponse();
        try {
            return feeService.payFee(studentId,feeDto);
        } catch (Exception e){
            response.setStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Something went wrong!");
        }
        return response;
    }

}
