package com.hcl.enroll.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EnrollCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer enrollCourseId;
	private Integer courseId;
	private Integer userId;
	private LocalDate enrollDate;

	public Integer getEnrollCourseId() {
		return enrollCourseId;
	}

	public void setEnrollCourseId(Integer enrollCourseId) {
		this.enrollCourseId = enrollCourseId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDate getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(LocalDate enrollDate) {
		this.enrollDate = enrollDate;
	}

}
