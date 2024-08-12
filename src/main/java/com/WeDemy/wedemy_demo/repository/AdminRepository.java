package com.WeDemy.wedemy_demo.repository;

import com.WeDemy.wedemy_demo.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<Admin,String> {
    Optional<Admin> findAdminByUserName(String userName);
}
