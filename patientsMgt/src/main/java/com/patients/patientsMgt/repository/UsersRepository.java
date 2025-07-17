package com.patients.patientsMgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patients.patientsMgt.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByRole(String role);
    List<Users> findByEmail(String email);
    List<Users> findByUserName(String userName);
    List<Users> findByUserId(Long userId);

}
