package com.WeDemy.wedemy_demo.repository;

import com.WeDemy.wedemy_demo.entity.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<MyUser, String> {
    Optional<MyUser> findUserByUserName(String userName);
}
