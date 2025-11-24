# CCRM Usage Guide

## Sample Commands

### Student Management
```
1. Add Student
   - Required: Registration Number, Full Name, Email
   Example: ADD-STUDENT S001 "John Doe" john.doe@example.com

2. List Students
   - List all: LIST-STUDENTS
   - Filter by status: LIST-STUDENTS ACTIVE

3. Update Student
   Example: UPDATE-STUDENT S001 EMAIL new.email@example.com
```

### Course Management
```
1. Add Course
   Example: ADD-COURSE CS101 "Introduction to Programming" 3 FALL CS

2. List Courses
   - All courses: LIST-COURSES
   - By department: LIST-COURSES CS
   - By semester: LIST-COURSES FALL
```

### Enrollment
```
1. Enroll Student
   Example: ENROLL S001 CS101

2. Record Grade
   Example: RECORD-GRADE S001 CS101 A
```

## Sample Data Files

### students.csv
```
regNo,fullName,email,status
S001,John Doe,john.doe@example.com,ACTIVE
S002,Jane Smith,jane.smith@example.com,ACTIVE
```

### courses.csv
```
code,title,credits,instructor,semester,department
CS101,Introduction to Programming,3,PROF001,FALL,CS
CS102,Data Structures,4,PROF002,SPRING,CS
```

### enrollments.csv
```
studentRegNo,courseCode,enrollmentDate,grade
S001,CS101,2023-09-01,A
S002,CS101,2023-09-01,B+
```