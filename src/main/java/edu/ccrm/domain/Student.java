package edu.ccrm.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a student in the system.
 */
public class Student extends Person {
    private final String regNo;
    private boolean active;
    private final List<Enrollment> enrollments;

    public Student(String id, String regNo, String fullName, String email) {
        super(id, fullName, email);
        this.regNo = regNo;
        this.active = true;
        this.enrollments = new ArrayList<>();
    }

    public String getRegNo() {
        return regNo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public void addEnrollment(Enrollment enrollment) {
        if (enrollment != null && !enrollments.contains(enrollment)) {
            enrollments.add(enrollment);
        }
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return String.format("%s [RegNo: %s, Status: %s]", 
            super.toString(),
            regNo,
            active ? "Active" : "Inactive");
    }
}