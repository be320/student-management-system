CREATE TABLE Student (
    student_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    username VARCHAR2(50) UNIQUE NOT NULL,
    password VARCHAR2(256) NOT NULL
);
