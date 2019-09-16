package com.hcl.enroll.dto;

import java.io.Serializable;

import java.util.List;

public class CourseEnrollmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;

	private List<CoursesDTO> courses;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<CoursesDTO> getCourses() {
		return courses;
	}

	public void setCourses(List<CoursesDTO> courses) {
		this.courses = courses;
	}

	}
