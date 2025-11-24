package edu.ccrm;

import edu.ccrm.cli.CCRMCommandLineInterface;
import edu.ccrm.config.AppConfig;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.CsvImportExportService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import edu.ccrm.service.impl.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Main entry point for the Campus Course & Records Manager (CCRM) application.
 */
public class CCRMApplication {
    public static void main(String[] args) {
        try {
            // Initialize configuration
            AppConfig config = AppConfig.getInstance();
            
            // Ensure directories exist
            Files.createDirectories(config.getDataDirectory());
            Files.createDirectories(config.getBackupDirectory());

            // Initialize services
            StudentService studentService = new DefaultStudentService();
            CourseService courseService = new DefaultCourseService();
            EnrollmentService enrollmentService = new DefaultEnrollmentService();
            TranscriptService transcriptService = new DefaultTranscriptService(enrollmentService);
            
            ImportExportService importExportService = new CsvImportExportService(
                studentService, courseService);
            
            BackupService backupService = new BackupService(
                config.getDataDirectory(),
                config.getBackupDirectory(),
                importExportService);

            // Print welcome message and system info
            System.out.println("Welcome to Campus Course & Records Manager (CCRM)");
            System.out.println("==============================================");
            
            // Print Java platform information
            System.out.println("\nJava Platform Information:");
            System.out.println("------------------------");
            System.out.println("Java SE: Standard Edition for desktop applications");
            System.out.println("Java EE: Enterprise Edition for large-scale server applications");
            System.out.println("Java ME: Micro Edition for mobile and embedded devices");
            
            // Print system configuration
            config.printSystemInfo();

            // Start CLI
            CCRMCommandLineInterface cli = new CCRMCommandLineInterface(
                studentService,
                courseService,
                enrollmentService,
                transcriptService,
                importExportService,
                backupService
            );
            
            cli.start();
        } catch (Exception e) {
            System.err.println("Error starting application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}