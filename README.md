# Laboratory Problem: Student Management System

A teacher manages information about students, lab problems, and assignments. This application allows the following operations:

## Features

- [✓] **Perform CRUD operations** on students and lab problems
- [✓] **Perform CRUD operations** on assignments
  - [✓] **Assign problems** to students
  - [✓] **Assign grades**
- [?] **Filter entities** based on various criteria:
  - [✓] Filter students without assignments
  - [✗] Filter lab problems by the number of students assigned
  - [✗] Filter students who have received a grade for all assigned problems
- [?] **Generate reports**, such as:
  - [✓] Finding the most assigned lab problem
  - [✗] Finding the student with the **highest** average grade
  - [✗] Finding the student with the **lowest** average grade
- [✗] **Implement validation & throw exceptions** for students, lab problems, and assignments

## Tech Stack
- **Programming Language**: Java
- **Build Tool**: Maven
- **Version Control**: Git/GitHub for version management

## Interesting Options and Features

- **Filtering**: Ability to filter students and lab problems based on various criteria such as grades or number of assigned problems.
- **Dynamic Reports**: Generate custom reports, such as finding the most assigned lab problem or the student with the highest/lowest average grade.
- **Validation**: Implement validation rules for students (e.g., valid email), lab problems (e.g., max score), and assignments (e.g., valid grades).
- **Exceptions**: Handle exceptions and provide detailed error messages for invalid operations (e.g., invalid problem assignment or grade).


## Feature Status Legend

- [✓] Feature/Task implemented
- [✗] Feature/Task not implemented
- [?] Feature/Task partially implemented
