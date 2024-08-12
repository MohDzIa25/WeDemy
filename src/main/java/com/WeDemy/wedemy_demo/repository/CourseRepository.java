package com.WeDemy.wedemy_demo.repository;

import com.WeDemy.wedemy_demo.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
}
