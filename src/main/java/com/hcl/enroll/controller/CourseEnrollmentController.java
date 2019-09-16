package com.hcl.enroll.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.service.EnrolledCoursesService;

@RestController
@RequestMapping("/user")
public class CourseEnrollmentController {

	@Autowired
	EnrolledCoursesService enrolledCoursesService;

	@GetMapping("/{userId}/enroll")
	public ResponseEntity<List<EnrolledCoursesDto>> enrolledCourses(@PathVariable("userId") int userId) {
		return ResponseEntity.status(HttpStatus.OK).body(enrolledCoursesService.enrolledCourses(userId));
	}
}
