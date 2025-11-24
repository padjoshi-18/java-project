package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing students.
 */
public interface StudentService {
    Student createStudent(String regNo, String fullName, String email);
    Optional<Student> getStudent(String regNo);
    List<Student> getAllStudents();
    List<Student> getActiveStudents();
    void updateStudent(Student student);
    void deactivateStudent(String regNo);
}