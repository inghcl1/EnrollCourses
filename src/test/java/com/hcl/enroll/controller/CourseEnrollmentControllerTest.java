package com.hcl.enroll.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.CoursesDTO;
import com.hcl.enroll.dto.ResponseDto;
import com.hcl.enroll.service.CourseEnrollmentServiceImpl;
import com.hcl.enroll.util.EnrollCoursesConstants;

@RunWith(MockitoJUnitRunner.class)
public class CourseEnrollmentControllerTest {

	@Mock
	CourseEnrollmentServiceImpl courseEnrollmentServiceImpl;

	@InjectMocks
	CourseEnrollmentController courseEnrollmentController;

	@Test
	public void testCourseEnrollment() {

		List<CoursesDTO> courseDTOList = new ArrayList<>();
		CoursesDTO courseDTO = new CoursesDTO();
		courseDTO.setCourseId(1);
		courseDTOList.add(courseDTO);
		CoursesDTO courseDTO1 = new CoursesDTO();
		courseDTO1.setCourseId(1);
		courseDTOList.add(courseDTO1);

		CourseEnrollmentDTO courseEnrollmentDTO = new CourseEnrollmentDTO();

		courseEnrollmentDTO.setUserId(1);
		courseEnrollmentDTO.setCourses(courseDTOList);
		ResponseDto responseDTO = new ResponseDto();
		responseDTO.setStatusCode(EnrollCoursesConstants.COURSE_ENROLL_STATUSCODE);

		Mockito.when(courseEnrollmentServiceImpl.courseEnrollment(courseEnrollmentDTO)).thenReturn(responseDTO);
		ResponseEntity<ResponseDto> actualValue = courseEnrollmentController.courseEnrollment(courseEnrollmentDTO);

		assertEquals(responseDTO.getStatusCode(), actualValue.getBody().getStatusCode());

	}

}
