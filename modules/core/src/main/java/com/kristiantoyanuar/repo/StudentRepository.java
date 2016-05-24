package com.kristiantoyanuar.repo;

import com.kristiantoyanuar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kristianto Yanuar on 5/23/16.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}
