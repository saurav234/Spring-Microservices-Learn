package com.rest.webservices.springwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rest.webservices.springwebservices.vo.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
