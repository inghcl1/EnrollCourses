package com.hcl.enroll.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.service.EnrolledCoursesService;

@RunWith(MockitoJUnitRunner.class)
public class CourseEnrollmentControllerTest {
	@Mock
	EnrolledCoursesService enrolledCoursesService;
	@InjectMocks
	CourseEnrollmentController courseEnrollmentController;
	EnrolledCoursesDto enrolledCoursesDto;
	List<EnrolledCoursesDto> enrolledCoursesDtoList;

	@Before
	public void setup() {
		enrolledCoursesDto = new EnrolledCoursesDto();
		enrolledCoursesDto.setCourseDescription("");
		enrolledCoursesDto.setCourseDuration(40);
		enrolledCoursesDto.setCourseFee(20000d);
		enrolledCoursesDto.setCourseId(1);
		enrolledCoursesDto.setCourseName("java");

		enrolledCoursesDtoList = new ArrayList<>();
		enrolledCoursesDtoList.add(enrolledCoursesDto);
	}

	@Test
	public void test() {
		Mockito.when(enrolledCoursesService.enrolledCourses(Mockito.anyInt())).thenReturn(enrolledCoursesDtoList);
		ResponseEntity<List<EnrolledCoursesDto>> actual = courseEnrollmentController.enrolledCourses(Mockito.anyInt());

		Assert.assertEquals(enrolledCoursesDtoList.size(), actual.getBody().size());
	}

}
