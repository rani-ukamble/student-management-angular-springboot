package com.example.student_management.service.fee;

import com.example.student_management.dto.FeeDto;
import com.example.student_management.response.GeneralResponse;

public interface FeeService {
    GeneralResponse payFee(Long studentId, FeeDto feeDto);
}
