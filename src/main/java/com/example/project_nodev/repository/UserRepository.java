package com.example.project_nodev.repository;


import com.example.project_nodev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUseridAndPassword(String userid, String password);

}
