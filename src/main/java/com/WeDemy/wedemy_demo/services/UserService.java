package com.WeDemy.wedemy_demo.services;

import com.WeDemy.wedemy_demo.entity.Course;
import com.WeDemy.wedemy_demo.entity.MyUser;
import com.WeDemy.wedemy_demo.repository.CourseRepository;
import com.WeDemy.wedemy_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public MyUser addUser(MyUser myUser){
        Optional<MyUser> user=findUserByUserName(myUser.getUserName());
        if(user.isPresent())
            return null;
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        return userRepository.save(myUser);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Optional<MyUser> findUserByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }
    public ResponseEntity<?> purchaseCourse(String userId, String courseId){
        Optional<Course> course=courseRepository.findById(courseId);
        Optional<MyUser> user= userRepository.findById(userId);
        if(course.isEmpty())
            return new ResponseEntity<>("Course not found", HttpStatus.NOT_FOUND);
        else if(user.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        else{
            MyUser currMyUser =user.get();
            currMyUser.getPurchasedCourses().add(course.get());
            userRepository.save(currMyUser);

            return new ResponseEntity<>(currMyUser,HttpStatus.OK);
        }
    }

    public ResponseEntity<?> getAllPurchasedCourses(String userId){
        Optional<MyUser> user= userRepository.findById(userId);
        if(user.isEmpty())
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(user.get().getPurchasedCourses(),HttpStatus.OK);
    }
}
