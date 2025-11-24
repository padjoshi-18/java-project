package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service for managing data backups.
 */
public class BackupService {
    private static final DateTimeFormatter BACKUP_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    
    private final Path dataDirectory;
    private final Path backupDirectory;
    private final ImportExportService importExportService;

    public BackupService(Path dataDirectory, Path backupDirectory, 
                        ImportExportService importExportService) {
        this.dataDirectory = dataDirectory;
        this.backupDirectory = backupDirectory;
        this.importExportService = importExportService;
    }

    /**
     * Creates a timestamped backup of all data.
     *
     * @return The path to the backup directory
     * @throws IOException if backup fails
     */
    public Path createBackup() throws IOException {
        String timestamp = LocalDateTime.now().format(BACKUP_FORMATTER);
        Path backupPath = backupDirectory.resolve("backup_" + timestamp);
        
        // Create backup directory
        Files.createDirectories(backupPath);
        
        // Export current data to backup directory
        importExportService.exportAllData(backupPath);
        
        return backupPath;
    }

    /**
     * Recursively calculates the total size of a directory.
     *
     * @param directory The directory to calculate size for
     * @return Total size in bytes
     * @throws IOException if an I/O error occurs
     */
    public long calculateDirectorySize(Path directory) throws IOException {
        AtomicLong size = new AtomicLong(0);
        
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                size.addAndGet(attrs.size());
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                return FileVisitResult.CONTINUE;
            }
        });
        
        return size.get();
    }

    /**
     * Recursively lists all files in a directory, showing directory structure.
     *
     * @param directory The directory to list
     * @throws IOException if an I/O error occurs
     */
    public void listDirectoryContents(Path directory) throws IOException {
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println(directory.relativize(dir) + "/");
                return FileVisitResult.CONTINUE;
            }
            
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println(directory.relativize(file));
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Gets the latest backup directory.
     *
     * @return The path to the latest backup, or null if no backups exist
     * @throws IOException if an I/O error occurs
     */
    public Path getLatestBackup() throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(backupDirectory, "backup_*")) {
            Path latest = null;
            for (Path path : stream) {
                if (latest == null || Files.getLastModifiedTime(path)
                    .compareTo(Files.getLastModifiedTime(latest)) > 0) {
                    latest = path;
                }
            }
            return latest;
        }
    }
}