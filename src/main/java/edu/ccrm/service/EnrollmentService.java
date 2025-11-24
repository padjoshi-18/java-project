package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import java.util.List;

/**
 * Service interface for managing enrollments.
 */
public interface EnrollmentService {
    Enrollment enrollStudent(Student student, Course course);
    void unenrollStudent(Student student, Course course);
    void recordGrade(Student student, Course course, Grade grade);
    List<Enrollment> getEnrollmentsForStudent(Student student);
    List<Enrollment> getEnrollmentsForCourse(Course course);
    double calculateGPA(Student student);
}