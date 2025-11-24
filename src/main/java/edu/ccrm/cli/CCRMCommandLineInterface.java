package edu.ccrm.cli;

import edu.ccrm.domain.*;
import edu.ccrm.service.*;
import edu.ccrm.io.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Command Line Interface for the CCRM application.
 */
public class CCRMCommandLineInterface {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final TranscriptService transcriptService;
    private final ImportExportService importExportService;
    private final BackupService backupService;
    private boolean running;

    public CCRMCommandLineInterface(
            StudentService studentService,
            CourseService courseService,
            EnrollmentService enrollmentService,
            TranscriptService transcriptService,
            ImportExportService importExportService,
            BackupService backupService) {
        this.scanner = new Scanner(System.in);
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
        this.transcriptService = transcriptService;
        this.importExportService = importExportService;
        this.backupService = backupService;
    }

    public void start() {
        running = true;
        while (running) {
            displayMainMenu();
            processMainMenuChoice();
        }
    }

    private void displayMainMenu() {
        System.out.println("\nCampus Course & Records Manager (CCRM)");
        System.out.println("====================================");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments");
        System.out.println("4. Generate Reports");
        System.out.println("5. Import/Export Data");
        System.out.println("6. Backup Operations");
        System.out.println("0. Exit");
        System.out.print("\nEnter your choice: ");
    }

    private void processMainMenuChoice() {
        switch (scanner.nextLine().trim()) {
            case "1" -> manageStudents();
            case "2" -> manageCourses();
            case "3" -> manageEnrollments();
            case "4" -> generateReports();
            case "5" -> manageData();
            case "6" -> manageBackups();
            case "0" -> {
                System.out.println("Thank you for using CCRM!");
                running = false;
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void manageStudents() {
        while (true) {
            System.out.println("\nStudent Management");
            System.out.println("1. Add Student");
            System.out.println("2. List Students");
            System.out.println("3. Update Student");
            System.out.println("4. Deactivate Student");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nEnter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addStudent();
                case "2" -> listStudents();
                case "3" -> updateStudent();
                case "4" -> deactivateStudent();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageCourses() {
        while (true) {
            System.out.println("\nCourse Management");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Search Courses");
            System.out.println("4. Update Course");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nEnter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> addCourse();
                case "2" -> listCourses();
                case "3" -> searchCourses();
                case "4" -> updateCourse();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageEnrollments() {
        while (true) {
            System.out.println("\nEnrollment Management");
            System.out.println("1. Enroll Student in Course");
            System.out.println("2. Unenroll Student from Course");
            System.out.println("3. Record Grade");
            System.out.println("4. List Enrollments");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nEnter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> enrollStudent();
                case "2" -> unenrollStudent();
                case "3" -> recordGrade();
                case "4" -> listEnrollments();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void generateReports() {
        while (true) {
            System.out.println("\nReport Generation");
            System.out.println("1. Generate Student Transcript");
            System.out.println("2. Calculate Student GPA");
            System.out.println("3. Course Enrollment Report");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nEnter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> generateTranscript();
                case "2" -> calculateGPA();
                case "3" -> courseEnrollmentReport();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageData() {
        while (true) {
            System.out.println("\nData Import/Export");
            System.out.println("1. Import Data");
            System.out.println("2. Export Data");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nEnter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> importData();
                case "2" -> exportData();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageBackups() {
        while (true) {
            System.out.println("\nBackup Operations");
            System.out.println("1. Create Backup");
            System.out.println("2. Show Backup Size");
            System.out.println("3. List Backup Contents");
            System.out.println("0. Back to Main Menu");
            System.out.print("\nEnter your choice: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> createBackup();
                case "2" -> showBackupSize();
                case "3" -> listBackupContents();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Implementation of menu actions...
    private void addStudent() {
        System.out.print("Enter registration number: ");
        String regNo = scanner.nextLine().trim();
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();

        try {
            Student student = studentService.createStudent(regNo, fullName, email);
            System.out.println("Student added successfully: " + student);
        } catch (Exception e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }

    private void listStudents() {
        System.out.println("\nAll Students:");
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private void updateStudent() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();
        
        studentService.getStudent(regNo).ifPresentOrElse(
            student -> {
                System.out.println("Current details: " + student);
                System.out.print("Enter new email (or press Enter to skip): ");
                String email = scanner.nextLine().trim();
                if (!email.isEmpty()) {
                    student.setEmail(email);
                }
                studentService.updateStudent(student);
                System.out.println("Student updated successfully.");
            },
            () -> System.out.println("Student not found.")
        );
    }

    private void deactivateStudent() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();
        
        try {
            studentService.deactivateStudent(regNo);
            System.out.println("Student deactivated successfully.");
        } catch (Exception e) {
            System.out.println("Error deactivating student: " + e.getMessage());
        }
    }

    private void addCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter course title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter credits: ");
        int credits = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter instructor ID: ");
        String instructor = scanner.nextLine().trim();
        System.out.print("Enter semester (SPRING/SUMMER/FALL): ");
        Semester semester = Semester.valueOf(scanner.nextLine().trim().toUpperCase());
        System.out.print("Enter department: ");
        String department = scanner.nextLine().trim();

        try {
            Course course = courseService.createCourse(code, title, credits, 
                instructor, semester, department);
            System.out.println("Course added successfully: " + course);
        } catch (Exception e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private void listCourses() {
        System.out.println("\nAll Courses:");
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            courses.forEach(System.out::println);
        }
    }

    private void searchCourses() {
        System.out.println("\nSearch Courses by:");
        System.out.println("1. Department");
        System.out.println("2. Semester");
        System.out.println("3. Instructor");
        System.out.print("\nEnter your choice: ");

        switch (scanner.nextLine().trim()) {
            case "1" -> {
                System.out.print("Enter department: ");
                String department = scanner.nextLine().trim();
                courseService.getCoursesByDepartment(department)
                    .forEach(System.out::println);
            }
            case "2" -> {
                System.out.print("Enter semester (SPRING/SUMMER/FALL): ");
                try {
                    Semester semester = Semester.valueOf(
                        scanner.nextLine().trim().toUpperCase());
                    courseService.getCoursesBySemester(semester)
                        .forEach(System.out::println);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid semester.");
                }
            }
            case "3" -> {
                System.out.print("Enter instructor ID: ");
                String instructorId = scanner.nextLine().trim();
                courseService.getCoursesByInstructor(instructorId)
                    .forEach(System.out::println);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void updateCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine().trim();
        
        courseService.getCourse(code).ifPresentOrElse(
            course -> {
                System.out.println("Current details: " + course);
                System.out.print("Enter new instructor ID (or press Enter to skip): ");
                String instructor = scanner.nextLine().trim();
                if (!instructor.isEmpty()) {
                    course.setInstructor(instructor);
                }
                courseService.updateCourse(course);
                System.out.println("Course updated successfully.");
            },
            () -> System.out.println("Course not found.")
        );
    }

    private void enrollStudent() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();

        try {
            studentService.getStudent(regNo).ifPresent(student ->
                courseService.getCourse(courseCode).ifPresent(course -> {
                    enrollmentService.enrollStudent(student, course);
                    System.out.println("Enrollment successful.");
                })
            );
        } catch (Exception e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }

    private void unenrollStudent() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();

        try {
            studentService.getStudent(regNo).ifPresent(student ->
                courseService.getCourse(courseCode).ifPresent(course -> {
                    enrollmentService.unenrollStudent(student, course);
                    System.out.println("Unenrollment successful.");
                })
            );
        } catch (Exception e) {
            System.out.println("Error unenrolling student: " + e.getMessage());
        }
    }

    private void recordGrade() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();
        System.out.print("Enter grade (S/A/B/C/D/E/F): ");
        String gradeStr = scanner.nextLine().trim();

        try {
            Grade grade = Grade.valueOf(gradeStr.toUpperCase());
            studentService.getStudent(regNo).ifPresent(student ->
                courseService.getCourse(courseCode).ifPresent(course -> {
                    enrollmentService.recordGrade(student, course, grade);
                    System.out.println("Grade recorded successfully.");
                })
            );
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid grade.");
        } catch (Exception e) {
            System.out.println("Error recording grade: " + e.getMessage());
        }
    }

    private void listEnrollments() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();

        studentService.getStudent(regNo).ifPresentOrElse(
            student -> {
                List<Enrollment> enrollments = enrollmentService
                    .getEnrollmentsForStudent(student);
                if (enrollments.isEmpty()) {
                    System.out.println("No enrollments found.");
                } else {
                    enrollments.forEach(System.out::println);
                }
            },
            () -> System.out.println("Student not found.")
        );
    }

    private void generateTranscript() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();

        studentService.getStudent(regNo).ifPresentOrElse(
            student -> transcriptService.printTranscript(student),
            () -> System.out.println("Student not found.")
        );
    }

    private void calculateGPA() {
        System.out.print("Enter student registration number: ");
        String regNo = scanner.nextLine().trim();

        studentService.getStudent(regNo).ifPresentOrElse(
            student -> {
                double gpa = enrollmentService.calculateGPA(student);
                System.out.printf("GPA: %.2f%n", gpa);
            },
            () -> System.out.println("Student not found.")
        );
    }

    private void courseEnrollmentReport() {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine().trim();

        courseService.getCourse(courseCode).ifPresentOrElse(
            course -> {
                List<Enrollment> enrollments = enrollmentService
                    .getEnrollmentsForCourse(course);
                System.out.println("\nEnrollments for " + course.getCode() + 
                    " - " + course.getTitle());
                if (enrollments.isEmpty()) {
                    System.out.println("No enrollments found.");
                } else {
                    enrollments.forEach(System.out::println);
                }
            },
            () -> System.out.println("Course not found.")
        );
    }

    private void importData() {
        try {
            System.out.print("Enter path to students CSV file: ");
            String studentsPath = scanner.nextLine().trim();
            importExportService.importStudents(Path.of(studentsPath));

            System.out.print("Enter path to courses CSV file: ");
            String coursesPath = scanner.nextLine().trim();
            importExportService.importCourses(Path.of(coursesPath));

            System.out.print("Enter path to enrollments CSV file: ");
            String enrollmentsPath = scanner.nextLine().trim();
            importExportService.importEnrollments(Path.of(enrollmentsPath));

            System.out.println("Data imported successfully.");
        } catch (IOException e) {
            System.out.println("Error importing data: " + e.getMessage());
        }
    }

    private void exportData() {
        try {
            System.out.print("Enter export directory path: ");
            String dirPath = scanner.nextLine().trim();
            importExportService.exportAllData(Path.of(dirPath));
            System.out.println("Data exported successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting data: " + e.getMessage());
        }
    }

    private void createBackup() {
        try {
            Path backupPath = backupService.createBackup();
            System.out.println("Backup created successfully at: " + backupPath);
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }

    private void showBackupSize() {
        try {
            Path latestBackup = backupService.getLatestBackup();
            if (latestBackup != null) {
                long size = backupService.calculateDirectorySize(latestBackup);
                System.out.printf("Backup size: %d bytes%n", size);
            } else {
                System.out.println("No backups found.");
            }
        } catch (IOException e) {
            System.out.println("Error calculating backup size: " + e.getMessage());
        }
    }

    private void listBackupContents() {
        try {
            Path latestBackup = backupService.getLatestBackup();
            if (latestBackup != null) {
                System.out.println("Backup contents:");
                backupService.listDirectoryContents(latestBackup);
            } else {
                System.out.println("No backups found.");
            }
        } catch (IOException e) {
            System.out.println("Error listing backup contents: " + e.getMessage());
        }
    }
}