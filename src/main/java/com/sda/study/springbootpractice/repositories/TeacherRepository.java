package com.sda.study.springbootpractice.repositories;

import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tiina Raudla
 * @Date
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByName(String name);
}
