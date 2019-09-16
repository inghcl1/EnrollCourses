package com.hcl.enroll.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.CoursesDTO;
import com.hcl.enroll.dto.ResponseDto;
import com.hcl.enroll.entity.EnrollCourse;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.repository.EnrollCourseRepository;
import com.hcl.enroll.util.EnrollCoursesConstants;

@RunWith(MockitoJUnitRunner.class)
public class CourseEnrollmentServiceImplTest {

	@Mock
	EnrollCourseRepository enrollCourseRepository;
	@InjectMocks
	CourseEnrollmentServiceImpl courseEnrollmentServiceImpl;

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

		List<EnrollCourse> enrollCoursesList = new ArrayList<>();

		EnrollCourse enrollCourse = new EnrollCourse();
		enrollCourse.setUserId(1);
		enrollCourse.setCourseId(1);
		enrollCourse.setEnrollDate(LocalDate.now());
		enrollCoursesList.add(enrollCourse);

		EnrollCourse enrollCourse1 = new EnrollCourse();
		enrollCourse1.setUserId(1);
		enrollCourse1.setCourseId(2);
		enrollCourse1.setEnrollDate(LocalDate.now());
		enrollCoursesList.add(enrollCourse1);

		Mockito.when(enrollCourseRepository.saveAll(Mockito.any())).thenReturn(enrollCoursesList);

		ResponseDto responseDTO = new ResponseDto();
		responseDTO.setStatusCode(EnrollCoursesConstants.COURSE_ENROLL_STATUSCODE);

		ResponseDto actualValue = courseEnrollmentServiceImpl.courseEnrollment(courseEnrollmentDTO);
		assertEquals(responseDTO.getStatusCode(), actualValue.getStatusCode());

	}

	@Test(expected = EnrollCoursesException.class)
	public void testCourseEnrollmentException() {
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

		List<EnrollCourse> enrollCoursesList = new ArrayList<>();

		EnrollCourse enrollCourse = new EnrollCourse();
		enrollCourse.setUserId(1);
		enrollCourse.setCourseId(1);
		enrollCourse.setEnrollDate(LocalDate.now());
		enrollCoursesList.add(enrollCourse);

		EnrollCourse enrollCourse1 = new EnrollCourse();
		enrollCourse1.setUserId(1);
		enrollCourse1.setCourseId(2);
		enrollCourse1.setEnrollDate(LocalDate.now());
		enrollCoursesList.add(enrollCourse1);

		courseEnrollmentServiceImpl.courseEnrollment(courseEnrollmentDTO);

	}

}
