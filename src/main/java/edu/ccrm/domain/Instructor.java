package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents an instructor in the system.
 */
public class Instructor extends Person {
    private String department;
    private final List<Course> courses;

    public Instructor(String id, String fullName, String email, String department) {
        super(id, fullName, email);
        this.department = department;
        this.courses = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public void addCourse(Course course) {
        if (course != null && !courses.contains(course)) {
            courses.add(course);
        }
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String toString() {
        return String.format("%s [Department: %s]",
            super.toString(),
            department);
    }
}