package com.hcl.enroll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.entity.EnrollCourse;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.repository.EnrollCourseRepository;
import com.hcl.enroll.util.EnrollCoursesConstants;
import com.hcl.enroll.util.GlobalProperties;

/**
 * 
 * @author sairam
 *
 */

@Service
@PropertySource("classpath:application.properties")
public class EnrolledCoursesServiceImpl implements EnrolledCoursesService {

	@Autowired
	EnrollCourseRepository enrollCourseRepository;
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	GlobalProperties globalProperties;

	private static final Logger LOGGER = LoggerFactory.getLogger(EnrolledCoursesServiceImpl.class);

	/**
	 * 
	 * @param userId is not null
	 * 
	 * @return List<EnrolledCoursesDto> will give the list of courses
	 */
	@Override
	public List<EnrolledCoursesDto> enrolledCourses(int userId) {
		LOGGER.info("EnrolledCoursesServiceImpl--->enrolledCourses");
		LOGGER.debug("enrolledCourses --> userId:{}", userId);

		Optional<List<EnrollCourse>> enrollCourses = enrollCourseRepository.findByUserId(userId);
		if (!enrollCourses.isPresent())
			throw new EnrollCoursesException(EnrollCoursesConstants.NO_ENROLL_COURSES);

		List<EnrolledCoursesDto> enrolledCoursesDtoList = new ArrayList<>();
		enrollCourses.get().forEach(enrollCours -> {
			try {

				LOGGER.debug("url:{} ", globalProperties.getUrl() + "/" + enrollCours.getCourseId());
				ResponseEntity<EnrolledCoursesDto> enrolledCoursesDto = restTemplate.getForEntity(
						globalProperties.getUrl() + "/" + enrollCours.getCourseId(), EnrolledCoursesDto.class);

				enrolledCoursesDtoList.add(enrolledCoursesDto.getBody());
			} catch (Exception e) {
				LOGGER.error("enrolledCourses --> exception:{} ", e.getMessage());
			}

		});

		return enrolledCoursesDtoList;
	}

}
