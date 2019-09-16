package com.hcl.enroll.service;

import org.springframework.stereotype.Service;

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.ResponseDto;

@Service
@FunctionalInterface
public interface CourseEnrollmentService {

	public ResponseDto courseEnrollment(CourseEnrollmentDTO courseEnrollmentDTO);

}
