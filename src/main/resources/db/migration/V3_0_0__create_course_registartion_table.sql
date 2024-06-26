CREATE TABLE Course_Registration (
    registration_id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    student_id NUMBER NOT NULL,
    course_id NUMBER NOT NULL,
    CONSTRAINT fk_student
        FOREIGN KEY (student_id)
        REFERENCES Student(student_id),
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
        REFERENCES Course(course_id)
);

CREATE INDEX idx_student_id ON Course_Registration(student_id);
CREATE INDEX idx_course_id ON Course_Registration(course_id);
