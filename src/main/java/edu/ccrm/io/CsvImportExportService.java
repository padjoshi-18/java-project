package edu.ccrm.io;

import edu.ccrm.domain.*;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSV-based implementation of ImportExportService.
 */
public class CsvImportExportService implements ImportExportService {
    private static final String DELIMITER = ",";
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final StudentService studentService;
    private final CourseService courseService;

    public CsvImportExportService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public List<Student> importStudents(Path file) throws IOException {
        List<Student> students = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        
        // Skip header
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(DELIMITER);
            if (parts.length >= 4) {
                String regNo = parts[0].trim();
                String fullName = parts[1].trim();
                String email = parts[2].trim();
                boolean isActive = Boolean.parseBoolean(parts[3].trim());

                Student student = studentService.createStudent(regNo, fullName, email);
                student.setActive(isActive);
                students.add(student);
            }
        }
        return students;
    }

    @Override
    public List<Course> importCourses(Path file) throws IOException {
        List<Course> courses = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        
        // Skip header
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(DELIMITER);
            if (parts.length >= 6) {
                String code = parts[0].trim();
                String title = parts[1].trim();
                int credits = Integer.parseInt(parts[2].trim());
                String instructor = parts[3].trim();
                Semester semester = Semester.valueOf(parts[4].trim());
                String department = parts[5].trim();

                Course course = courseService.createCourse(code, title, credits, 
                    instructor, semester, department);
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public List<Enrollment> importEnrollments(Path file) throws IOException {
        List<Enrollment> enrollments = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        
        // Skip header
        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(DELIMITER);
            if (parts.length >= 4) {
                String studentRegNo = parts[0].trim();
                String courseCode = parts[1].trim();
                String gradeStr = parts[3].trim();

                studentService.getStudent(studentRegNo).ifPresent(student -> 
                    courseService.getCourse(courseCode).ifPresent(course -> {
                        Enrollment enrollment = new Enrollment(student, course);
                        if (!gradeStr.isEmpty()) {
                            enrollment.setGrade(Grade.valueOf(gradeStr));
                        }
                        enrollments.add(enrollment);
                    })
                );
            }
        }
        return enrollments;
    }

    @Override
    public void exportStudents(List<Student> students, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("regNo,fullName,email,status");
        
        lines.addAll(students.stream()
            .map(s -> String.join(DELIMITER,
                s.getRegNo(),
                s.getFullName(),
                s.getEmail(),
                String.valueOf(s.isActive())))
            .collect(Collectors.toList()));

        Files.write(file, lines);
    }

    @Override
    public void exportCourses(List<Course> courses, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("code,title,credits,instructor,semester,department");
        
        lines.addAll(courses.stream()
            .map(c -> String.join(DELIMITER,
                c.getCode(),
                c.getTitle(),
                String.valueOf(c.getCredits()),
                c.getInstructor(),
                c.getSemester().name(),
                c.getDepartment()))
            .collect(Collectors.toList()));

        Files.write(file, lines);
    }

    @Override
    public void exportEnrollments(List<Enrollment> enrollments, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("studentRegNo,courseCode,enrollmentDate,grade");
        
        lines.addAll(enrollments.stream()
            .map(e -> String.join(DELIMITER,
                e.getStudent().getRegNo(),
                e.getCourse().getCode(),
                e.getEnrollmentDate().format(DATE_FORMATTER),
                e.getGrade() != null ? e.getGrade().name() : ""))
            .collect(Collectors.toList()));

        Files.write(file, lines);
    }

    @Override
    public void exportAllData(Path directory) throws IOException {
        Files.createDirectories(directory);
        
        exportStudents(studentService.getAllStudents(), 
            directory.resolve("students.csv"));
        exportCourses(courseService.getAllCourses(), 
            directory.resolve("courses.csv"));
        // Note: This assumes we have a way to get all enrollments
        // exportEnrollments(enrollmentService.getAllEnrollments(), 
        //    directory.resolve("enrollments.csv"));
    }
}