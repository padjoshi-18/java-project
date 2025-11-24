package edu.ccrm.service.impl;

import edu.ccrm.domain.Student;
import edu.ccrm.service.StudentService;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Default implementation of StudentService.
 */
public class DefaultStudentService implements StudentService {
    private final Map<String, Student> students = new HashMap<>();

    @Override
    public Student createStudent(String regNo, String fullName, String email) {
        Objects.requireNonNull(regNo, "Registration number cannot be null");
        Objects.requireNonNull(fullName, "Full name cannot be null");
        Objects.requireNonNull(email, "Email cannot be null");

        if (students.containsKey(regNo)) {
            throw new IllegalArgumentException("Student with registration number " + regNo + " already exists");
        }

        Student student = new Student(UUID.randomUUID().toString(), regNo, fullName, email);
        students.put(regNo, student);
        return student;
    }

    @Override
    public Optional<Student> getStudent(String regNo) {
        return Optional.ofNullable(students.get(regNo));
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @Override
    public List<Student> getActiveStudents() {
        return students.values().stream()
                .filter(Student::isActive)
                .collect(Collectors.toList());
    }

    @Override
    public void updateStudent(Student student) {
        Objects.requireNonNull(student, "Student cannot be null");
        if (!students.containsKey(student.getRegNo())) {
            throw new IllegalArgumentException("Student not found");
        }
        students.put(student.getRegNo(), student);
    }

    @Override
    public void deactivateStudent(String regNo) {
        Student student = students.get(regNo);
        if (student != null) {
            student.setActive(false);
            updateStudent(student);
        }
    }
}