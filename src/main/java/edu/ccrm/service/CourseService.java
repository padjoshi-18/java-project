package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for managing courses.
 */
public interface CourseService {
    Course createCourse(String code, String title, int credits, String instructor, 
                       Semester semester, String department);
    Optional<Course> getCourse(String code);
    List<Course> getAllCourses();
    List<Course> getCoursesByDepartment(String department);
    List<Course> getCoursesBySemester(Semester semester);
    List<Course> getCoursesByInstructor(String instructorId);
    void updateCourse(Course course);
    void deactivateCourse(String code);
}