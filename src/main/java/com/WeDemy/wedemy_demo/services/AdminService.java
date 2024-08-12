package com.WeDemy.wedemy_demo.services;

import com.WeDemy.wedemy_demo.entity.Admin;
import com.WeDemy.wedemy_demo.entity.Course;
import com.WeDemy.wedemy_demo.entity.MyUser;
import com.WeDemy.wedemy_demo.repository.AdminRepository;
import com.WeDemy.wedemy_demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public Admin addAmin(Admin admin){
        Optional<Admin> storedAdmin= findAdminByUserName(admin.getUserName());
        if(storedAdmin.isPresent())
            return null;
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Optional<Admin> findAdminByUserName(String userName){
        return adminRepository.findAdminByUserName(userName);
    }
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course addCourse(Course course){
       return courseRepository.save(course);
    }

    public ResponseEntity<?> updateCourse(String id, Course course){
        Optional<Course> toUpdate = courseRepository.findById(id);
        if(toUpdate.isEmpty())
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        else{
            course.setId(id);
            courseRepository.save(course);
            return  new ResponseEntity<>(course, HttpStatus.OK);
        }
    }
}
