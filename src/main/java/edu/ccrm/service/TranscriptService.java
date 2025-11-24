package edu.ccrm.service;

import edu.ccrm.domain.Student;

/**
 * Service interface for generating transcripts.
 */
public interface TranscriptService {
    String generateTranscript(Student student);
    void printTranscript(Student student);
    byte[] generateTranscriptPDF(Student student); // For future implementation
}