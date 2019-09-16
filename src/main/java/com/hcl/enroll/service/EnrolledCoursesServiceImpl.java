package com.hcl.enroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.entity.EnrollCourse;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.repository.EnrollCourseRepository;
import com.hcl.enroll.util.EnrollCoursesConstants;

@Service
public class EnrolledCoursesServiceImpl implements EnrolledCoursesService {

	@Autowired
	EnrollCourseRepository enrollCourseRepository;
	@Autowired
	RestTemplate restTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(EnrolledCoursesServiceImpl.class);

	@Override
	public List<EnrolledCoursesDto> enrolledCourses(int userId) {
		LOGGER.info("EnrolledCoursesServiceImpl--->enrolledCourses");
		LOGGER.debug("enrolledCourses --> userId:{}", userId);
		Properties prop = new Properties();

		Optional<List<EnrollCourse>> enrollCourses = enrollCourseRepository.findByUserId(userId);
		if (!enrollCourses.isPresent())
			throw new EnrollCoursesException(EnrollCoursesConstants.NO_ENROLL_COURSES);

		List<EnrolledCoursesDto> enrolledCoursesDtoList = new ArrayList<>();
		enrollCourses.get().forEach(enrollCours -> {
			try {

				LOGGER.info("url:{} ", prop.getProperty("course.url") + "/" + enrollCours.getCourseId());
				ResponseEntity<EnrolledCoursesDto> enrolledCoursesDto = restTemplate.getForEntity(
						"http://localhost:8087/hcl/api/elearning/courses" + "/" + enrollCours.getCourseId(),
						EnrolledCoursesDto.class);
				enrolledCoursesDtoList.add(enrolledCoursesDto.getBody());
			} catch (Exception e) {
				LOGGER.error("enrolledCourses --> exception:{} ", e.getMessage());
			}

		});

		return enrolledCoursesDtoList;
	}

}
