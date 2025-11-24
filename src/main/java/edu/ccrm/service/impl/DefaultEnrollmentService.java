package edu.ccrm.service.impl;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Semester;
import edu.ccrm.domain.Student;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.util.DuplicateEnrollmentException;
import edu.ccrm.util.MaxCreditLimitExceededException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Default implementation of EnrollmentService.
 */
public class DefaultEnrollmentService implements EnrollmentService {
    private static final int MAX_CREDITS_PER_SEMESTER = 21;
    private final List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public Enrollment enrollStudent(Student student, Course course) {
        Objects.requireNonNull(student, "Student cannot be null");
        Objects.requireNonNull(course, "Course cannot be null");

        // Check for duplicate enrollment
        if (isEnrolled(student, course)) {
            throw new DuplicateEnrollmentException(
                "Student " + student.getRegNo() + " is already enrolled in course " + course.getCode());
        }

        // Check credit limit
        int currentCredits = calculateCurrentCredits(student, course.getSemester());
        if (currentCredits + course.getCredits() > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException(
                "Enrolling in " + course.getCode() + " would exceed the maximum credit limit of " 
                + MAX_CREDITS_PER_SEMESTER);
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollments.add(enrollment);
        student.addEnrollment(enrollment);
        return enrollment;
    }

    @Override
    public void unenrollStudent(Student student, Course course) {
        enrollments.removeIf(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
        student.removeEnrollment(
            student.getEnrollments().stream()
                .filter(e -> e.getCourse().equals(course))
                .findFirst()
                .orElse(null)
        );
    }

    @Override
    public void recordGrade(Student student, Course course, Grade grade) {
        Objects.requireNonNull(grade, "Grade cannot be null");
        
        enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && e.getCourse().equals(course))
            .findFirst()
            .ifPresent(enrollment -> enrollment.setGrade(grade));
    }

    @Override
    public List<Enrollment> getEnrollmentsForStudent(Student student) {
        return enrollments.stream()
            .filter(e -> e.getStudent().equals(student))
            .collect(Collectors.toList());
    }

    @Override
    public List<Enrollment> getEnrollmentsForCourse(Course course) {
        return enrollments.stream()
            .filter(e -> e.getCourse().equals(course))
            .collect(Collectors.toList());
    }

    @Override
    public double calculateGPA(Student student) {
        List<Enrollment> studentEnrollments = getEnrollmentsForStudent(student);
        
        if (studentEnrollments.isEmpty()) {
            return 0.0;
        }

        double totalPoints = 0.0;
        int totalCredits = 0;

        for (Enrollment enrollment : studentEnrollments) {
            Grade grade = enrollment.getGrade();
            if (grade != null) {
                int credits = enrollment.getCourse().getCredits();
                totalPoints += grade.getGradePoints() * credits;
                totalCredits += credits;
            }
        }

        return totalCredits > 0 ? totalPoints / totalCredits : 0.0;
    }

    private boolean isEnrolled(Student student, Course course) {
        return enrollments.stream()
            .anyMatch(e -> e.getStudent().equals(student) && e.getCourse().equals(course));
    }

    private int calculateCurrentCredits(Student student, Semester semester) {
        return enrollments.stream()
            .filter(e -> e.getStudent().equals(student) && 
                        e.getCourse().getSemester() == semester)
            .mapToInt(e -> e.getCourse().getCredits())
            .sum();
    }
}