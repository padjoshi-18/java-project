package edu.ccrm.util;

/**
 * Exception thrown when attempting to create a duplicate enrollment.
 */
public class DuplicateEnrollmentException extends RuntimeException {
    public DuplicateEnrollmentException(String message) {
        super(message);
    }
}