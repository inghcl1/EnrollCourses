package com.hcl.enroll.controller;

import static org.junit.Assert.assertEquals;

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

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.CoursesDTO;
import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.dto.ResponseDto;
import com.hcl.enroll.service.CourseEnrollmentServiceImpl;
import com.hcl.enroll.service.EnrolledCoursesService;
import com.hcl.enroll.util.EnrollCoursesConstants;

@RunWith(MockitoJUnitRunner.class)
public class CourseEnrollmentControllerTest {
	@Mock
	EnrolledCoursesService enrolledCoursesService;
	@InjectMocks
	CourseEnrollmentController courseEnrollmentController;
	EnrolledCoursesDto enrolledCoursesDto;
	List<EnrolledCoursesDto> enrolledCoursesDtoList;
	@Mock
	CourseEnrollmentServiceImpl courseEnrollmentServiceImpl;

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
