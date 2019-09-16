package com.hcl.enroll.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.CoursesDTO;
import com.hcl.enroll.dto.ResponseDto;
import com.hcl.enroll.entity.EnrollCourse;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.repository.EnrollCourseRepository;
import com.hcl.enroll.util.EnrollCoursesConstants;

/**
 * 
 * @author shiva
 * @version 1.0
 *
 */
@Service
public class CourseEnrollmentServiceImpl implements CourseEnrollmentService {

	private static final Logger lOGGER = LoggerFactory.getLogger(CourseEnrollmentServiceImpl.class);

	@Autowired
	EnrollCourseRepository enrollCourseRepository;

	/**
	 * @param CourseEnrollmentDTO contains userId,list of course objects
	 * @return responseDTO with appropriate success/failure message
	 * @throws EnrollCoursesException
	 * 
	 * This method has the logic to enroll
	 * (mulitple/single)courses for the given userId
	 * 
	 * 
	 */
	@Override
	public ResponseDto courseEnrollment(CourseEnrollmentDTO courseEnrollmentDTO) {

		lOGGER.info(
				"Inside CourseEnrollmentServiceImpl class courseEnrollment(CourseEnrollmentDTO courseEnrollmentDTO)");
		ResponseDto responseDTO = null;
		Integer userId = courseEnrollmentDTO.getUserId();
		lOGGER.debug("UserId:{userId}", userId);
		List<CoursesDTO> courses = courseEnrollmentDTO.getCourses();
		List<EnrollCourse> enrollCoursesList = new ArrayList<>();

		courses.stream().forEach(c -> {
			EnrollCourse enrollCourse = new EnrollCourse();
			enrollCourse.setUserId(userId);
			enrollCourse.setCourseId(c.getCourseId());
			enrollCourse.setEnrollDate(LocalDate.now());
			enrollCoursesList.add(enrollCourse);

		});

		List<EnrollCourse> courseList = enrollCourseRepository.saveAll(enrollCoursesList);

		if (Objects.isNull(courseList) || courseList.isEmpty()) {
			throw new EnrollCoursesException(EnrollCoursesConstants.COURSE_ENROLL_FAILURE);
		}

		else {
			responseDTO = new ResponseDto();
			responseDTO.setMessage(EnrollCoursesConstants.COURSE_ENROLL_SUCCESS);
			responseDTO.setStatusCode(EnrollCoursesConstants.COURSE_ENROLL_STATUSCODE);

			return responseDTO;
		}

	}

}
