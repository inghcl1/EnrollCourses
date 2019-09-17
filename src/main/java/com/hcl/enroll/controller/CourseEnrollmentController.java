package com.hcl.enroll.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.enroll.dto.CourseEnrollmentDTO;
import com.hcl.enroll.dto.EnrolledCoursesDto;
import com.hcl.enroll.dto.ResponseDto;
import com.hcl.enroll.exception.EnrollCoursesException;
import com.hcl.enroll.service.CourseEnrollmentService;
import com.hcl.enroll.service.EnrolledCoursesService;

/**
 * 
 * @author shiva
 * @author sairam
 * @version 1.0
 *
 */
@RestController
@RequestMapping("/users")
public class CourseEnrollmentController {

	private static final Logger lOGGER = LoggerFactory.getLogger(CourseEnrollmentController.class);

	@Autowired
	CourseEnrollmentService courseEnrollmentService;

	@Autowired
	EnrolledCoursesService enrolledCoursesService;

	/**
	 * @param CourseEnrollmentDTO contains userId,list of course objects
	 * @return responseDTO with appropriate success/failure message
	 * @throws EnrollCoursesException
	 * 
	 *                                This method has the logic to enroll
	 *                                (mulitple/single)courses for the given userId
	 * 
	 * 
	 */
	@PostMapping("/elearning/enrollments")
	public ResponseEntity<ResponseDto> courseEnrollment(@RequestBody CourseEnrollmentDTO courseEnrollmentDTO) {
		lOGGER.info(
				"Inside CourseEnrollmentController class courseEnrollment(CourseEnrollmentDTO courseEnrollmentDTO)");
		lOGGER.debug("UserId:{}", courseEnrollmentDTO.getUserId());

		return new ResponseEntity<>(courseEnrollmentService.courseEnrollment(courseEnrollmentDTO), HttpStatus.CREATED);

	}
/**
 * 
 * @Param  userId not null 
@return ResponseEntity<List<EnrolledCoursesDto>> list of enrolled courses along with status code
@Throws  EnrollCoursesException enrolled courses entry
 */
	@GetMapping("/{userId}/enrollments")
	public ResponseEntity<List<EnrolledCoursesDto>> enrolledCourses(@PathVariable("userId") int userId) {
		
		lOGGER.info("enterd enrolledCourses with userId:{}",userId);
		return ResponseEntity.status(HttpStatus.OK).body(enrolledCoursesService.enrolledCourses(userId));
	}

}
