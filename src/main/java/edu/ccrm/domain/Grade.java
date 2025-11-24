package edu.ccrm.domain;

/**
 * Represents academic grades with associated grade points.
 */
public enum Grade {
    S(10.0, "Outstanding"),
    A(9.0, "Excellent"),
    B(8.0, "Very Good"),
    C(7.0, "Good"),
    D(6.0, "Fair"),
    E(5.0, "Pass"),
    F(0.0, "Fail");

    private final double gradePoints;
    private final String description;

    Grade(double gradePoints, String description) {
        this.gradePoints = gradePoints;
        this.description = description;
    }

    public double getGradePoints() {
        return gradePoints;
    }

    public String getDescription() {
        return description;
    }
}