package edu.ccrm.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a student's enrollment in a course.
 */
public class Enrollment {
    private final Student student;
    private final Course course;
    private final LocalDateTime enrollmentDate;
    private Grade grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDateTime.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getEnrollmentDate() {
        return enrollmentDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment that)) return false;
        return Objects.equals(student.getRegNo(), that.student.getRegNo()) &&
               Objects.equals(course.getCode(), that.course.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(student.getRegNo(), course.getCode());
    }

    @Override
    public String toString() {
        return String.format("Enrollment: %s in %s [Grade: %s]",
            student.getRegNo(),
            course.getCode(),
            grade != null ? grade : "Not Graded");
    }
}