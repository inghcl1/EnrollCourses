package com.hcl.enroll.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.ResponseDto;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.service.CourseEnrollmentService;

/**
 * 
 * @author shiva
 * @version 1.0
 *
 */
@RestController
public class CourseEnrollmentController {

	private static final Logger lOGGER = LoggerFactory.getLogger(CourseEnrollmentController.class);

	@Autowired
	CourseEnrollmentService courseEnrollmentService;

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
	@PostMapping("/elearning/enroll")
	public ResponseEntity<ResponseDto> courseEnrollment(@RequestBody CourseEnrollmentDTO courseEnrollmentDTO) {
		lOGGER.info(
				"Inside CourseEnrollmentController class courseEnrollment(CourseEnrollmentDTO courseEnrollmentDTO)");
		lOGGER.debug("UserId:{userId}", courseEnrollmentDTO.getUserId());

		return new ResponseEntity<>(courseEnrollmentService.courseEnrollment(courseEnrollmentDTO), HttpStatus.CREATED);

	}

}
