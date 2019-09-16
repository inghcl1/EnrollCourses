package com.hcl.enroll.service;

import java.util.List;

import com.hcl.enroll.dto.EnrolledCoursesDto;

public interface EnrolledCoursesService {
	
public List<EnrolledCoursesDto> enrolledCourses(int userId);
	
}
