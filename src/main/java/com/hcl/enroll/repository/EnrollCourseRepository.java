package com.hcl.enroll.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.enroll.entity.EnrollCourse;

@Repository
public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, Integer> {

}
