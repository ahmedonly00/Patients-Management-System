package com.patients.patientsMgt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patients.patientsMgt.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByRole(String role);
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUserName(String userName);
    boolean existsByUserName(String userName);

}
