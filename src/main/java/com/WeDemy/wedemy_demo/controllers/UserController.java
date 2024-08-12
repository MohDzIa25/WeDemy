package com.WeDemy.wedemy_demo.controllers;

import com.WeDemy.wedemy_demo.entity.MyUser;
import com.WeDemy.wedemy_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/singUp")
    public ResponseEntity<?> addUser(@RequestBody MyUser myUser){
        MyUser user=userService.addUser(myUser);
        if(user==null)
            return new ResponseEntity<>("User already exist",HttpStatus.CONFLICT);
        else
            return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getAllCourses(){
        return new ResponseEntity<>(userService.getAllCourses(), HttpStatus.OK);
    }

    @GetMapping("/courses/{courseId}/{userId}")
    public ResponseEntity<?> purchaseCourse(@PathVariable String courseId,@PathVariable String userId){
        return userService.purchaseCourse( userId,courseId);
    }

    @GetMapping("/courses/purchasedCourses/{userId}")
    public ResponseEntity<?> getAllPurchasedCourses(@PathVariable String userId){
        return userService.getAllPurchasedCourses(userId);
    }
}
