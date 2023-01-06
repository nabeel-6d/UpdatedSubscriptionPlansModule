package com.example.subscriptions.repository.subscriber;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.subscriptions.entities.subscriber.UserDetailsFromDb;

public interface UserDetailsDbRepository extends JpaRepository<UserDetailsFromDb,Integer>{
   UserDetailsFromDb findByUsername(String username);
}
