CREATE DATABASE IF NOT EXISTS students_grades_manager;

USE students_grades_manager;

CREATE TABLE students (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE subjects (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  coefficient INT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE marks (
  id INT NOT NULL AUTO_INCREMENT,
  student_id INT NOT NULL,
  subject_id INT NOT NULL,
  score DOUBLE NOT NULL,
  CONSTRAINT unique_mark UNIQUE (student_id, subject_id),
  PRIMARY KEY (id),
  FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
       ON UPDATE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subjects(id) ON DELETE CASCADE
       ON UPDATE CASCADE
);