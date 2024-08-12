package com.WeDemy.wedemy_demo.controllers;

import com.WeDemy.wedemy_demo.entity.Admin;
import com.WeDemy.wedemy_demo.entity.Course;
import com.WeDemy.wedemy_demo.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/singUp")
    public ResponseEntity<?> signUp(@RequestBody Admin admin){
        Admin added=adminService.addAmin(admin);
        if(added==null)
            return new ResponseEntity<>("Admin already exist",HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>(added,HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(){
        return new ResponseEntity<>(adminService.getAllCourses(),HttpStatus.OK);
    }

    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        return new ResponseEntity<>(adminService.addCourse(course),HttpStatus.OK);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody Course course, @PathVariable String id){
        return adminService.updateCourse(id,course);
    }
}
