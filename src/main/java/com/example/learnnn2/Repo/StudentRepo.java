package com.example.learnnn2.Repo;

import com.example.learnnn2.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student,Long> {
 Optional<Student> findByEmail(String email);
}
