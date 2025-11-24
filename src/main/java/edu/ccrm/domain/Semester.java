package edu.ccrm.domain;

/**
 * Represents academic semesters in the academic year.
 */
public enum Semester {
    SPRING("Spring Semester"),
    SUMMER("Summer Semester"),
    FALL("Fall Semester");

    private final String description;

    Semester(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}