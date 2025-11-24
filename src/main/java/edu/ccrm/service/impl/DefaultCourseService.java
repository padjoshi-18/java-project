package edu.ccrm.service.impl;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Semester;
import edu.ccrm.service.CourseService;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Default implementation of CourseService.
 */
public class DefaultCourseService implements CourseService {
    private final Map<String, Course> courses = new HashMap<>();

    @Override
    public Course createCourse(String code, String title, int credits,
                             String instructor, Semester semester, String department) {
        Objects.requireNonNull(code, "Course code cannot be null");
        Objects.requireNonNull(title, "Title cannot be null");
        Objects.requireNonNull(semester, "Semester cannot be null");
        Objects.requireNonNull(department, "Department cannot be null");

        if (courses.containsKey(code)) {
            throw new IllegalArgumentException("Course with code " + code + " already exists");
        }

        Course course = new Course.Builder(code)
                .title(title)
                .credits(credits)
                .instructor(instructor)
                .semester(semester)
                .department(department)
                .build();

        courses.put(code, course);
        return course;
    }

    @Override
    public Optional<Course> getCourse(String code) {
        return Optional.ofNullable(courses.get(code));
    }

    @Override
    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    @Override
    public List<Course> getCoursesByDepartment(String department) {
        return courses.values().stream()
                .filter(course -> Objects.equals(course.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesBySemester(Semester semester) {
        return courses.values().stream()
                .filter(course -> course.getSemester() == semester)
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructorId) {
        return courses.values().stream()
                .filter(course -> Objects.equals(course.getInstructor(), instructorId))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCourse(Course course) {
        Objects.requireNonNull(course, "Course cannot be null");
        if (!courses.containsKey(course.getCode())) {
            throw new IllegalArgumentException("Course not found");
        }
        courses.put(course.getCode(), course);
    }

    @Override
    public void deactivateCourse(String code) {
        courses.remove(code);
    }
}