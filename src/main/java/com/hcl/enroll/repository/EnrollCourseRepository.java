package com.hcl.enroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.enroll.entity.EnrollCourse;

@Repository
public interface EnrollCourseRepository extends JpaRepository<EnrollCourse, Integer> {

	Optional<List<EnrollCourse>> findByUserId(Integer userid);

}
