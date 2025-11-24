package edu.ccrm.service.impl;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Student;
import edu.ccrm.service.EnrollmentService;
import edu.ccrm.service.TranscriptService;
import java.util.List;

/**
 * Default implementation of TranscriptService.
 */
public class DefaultTranscriptService implements TranscriptService {
    private final EnrollmentService enrollmentService;

    public DefaultTranscriptService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @Override
    public String generateTranscript(Student student) {
        StringBuilder transcript = new StringBuilder();
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(student);
        double gpa = enrollmentService.calculateGPA(student);

        transcript.append("ACADEMIC TRANSCRIPT\n");
        transcript.append("==================\n\n");
        transcript.append(String.format("Student: %s (%s)\n", student.getFullName(), student.getRegNo()));
        transcript.append("------------------\n\n");

        // Group by semester and sort
        enrollments.stream()
            .sorted((e1, e2) -> {
                int semesterCompare = e1.getCourse().getSemester()
                    .compareTo(e2.getCourse().getSemester());
                if (semesterCompare == 0) {
                    return e1.getCourse().getCode().compareTo(e2.getCourse().getCode());
                }
                return semesterCompare;
            })
            .forEach(enrollment -> {
                transcript.append(String.format("%s: %s\n",
                    enrollment.getCourse().getCode(),
                    enrollment.getCourse().getTitle()));
                transcript.append(String.format("Credits: %d  Grade: %s\n",
                    enrollment.getCourse().getCredits(),
                    enrollment.getGrade() != null ? enrollment.getGrade() : "Not Graded"));
                transcript.append("\n");
            });

        transcript.append("------------------\n");
        transcript.append(String.format("Cumulative GPA: %.2f\n", gpa));

        return transcript.toString();
    }

    @Override
    public void printTranscript(Student student) {
        System.out.println(generateTranscript(student));
    }

    @Override
    public byte[] generateTranscriptPDF(Student student) {
        // TODO: Implement PDF generation in future version
        throw new UnsupportedOperationException("PDF generation not yet implemented");
    }
}