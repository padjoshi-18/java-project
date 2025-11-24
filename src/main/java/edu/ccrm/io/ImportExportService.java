package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Service interface for importing and exporting data.
 */
public interface ImportExportService {
    // Import methods
    List<Student> importStudents(Path file) throws IOException;
    List<Course> importCourses(Path file) throws IOException;
    List<Enrollment> importEnrollments(Path file) throws IOException;

    // Export methods
    void exportStudents(List<Student> students, Path file) throws IOException;
    void exportCourses(List<Course> courses, Path file) throws IOException;
    void exportEnrollments(List<Enrollment> enrollments, Path file) throws IOException;

    // Export all data
    void exportAllData(Path directory) throws IOException;
}