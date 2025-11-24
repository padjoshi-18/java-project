package edu.ccrm.util;

/**
 * Exception thrown when a student exceeds their maximum credit limit.
 */
public class MaxCreditLimitExceededException extends RuntimeException {
    public MaxCreditLimitExceededException(String message) {
        super(message);
    }
}