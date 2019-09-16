package com.hcl.enroll.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.entity.EnrollCourse;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.repository.EnrollCourseRepository;


@RunWith(MockitoJUnitRunner.class)
public class EnrolledCoursesServiceImplTest {

	@InjectMocks
	EnrolledCoursesServiceImpl EnrolledCoursesService;
	@Mock
	EnrollCourseRepository enrollCourseRepository;
	@Mock
	RestTemplate restTemplate;
	EnrollCourse enrollCourse;
	List<EnrollCourse> enrollCourses;
	
	EnrolledCoursesDto  enrolledCoursesDto;

	@Before
	public void setup() {
		enrollCourse = new EnrollCourse();
		enrollCourse.setCourseId(1);
		enrollCourse.setEnrollCourseId(1);
		enrollCourse.setEnrollDate(LocalDate.now());
		enrollCourse.setUserId(1);

		enrollCourses = new ArrayList<>();
		enrollCourses.add(enrollCourse);
		
		enrolledCoursesDto=new EnrolledCoursesDto();
		enrolledCoursesDto.setCourseDescription("discription");
		enrolledCoursesDto.setCourseDuration(40);
		enrolledCoursesDto.setCourseFee(1000d);
		enrolledCoursesDto.setCourseId(1);
		enrolledCoursesDto.setCourseName("java");
		
				
	}

	@Test
	public void testEnrolledCourses() {

	Mockito.when(enrollCourseRepository.findByUserId(1)).thenReturn(Optional.of(enrollCourses));
	Mockito.when( restTemplate
		.getForEntity(Mockito.anyString(), EnrolledCoursesDto.class)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(enrolledCoursesDto));
	
	List<EnrolledCoursesDto> actual = EnrolledCoursesService.enrolledCourses(1);
	Assert.assertEquals(1, actual.size());
	
	}
	
	@Test(expected = EnrollCoursesException.class)
	public void testEnrolledCoursesNegative() {

	 EnrolledCoursesService.enrolledCourses(1);
	
	}


}
