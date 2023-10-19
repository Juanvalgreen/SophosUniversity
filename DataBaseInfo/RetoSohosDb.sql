CREATE TABLE "courses" (
  "course_id" serial UNIQUE PRIMARY KEY ,
  "teacher_id" integer,
  "course_name" varchar(50),
  "amount_credits" integer,
  "available_places" integer,
  "course_student_monitor_id" integer
);

CREATE TABLE "users" (
  "user_id" serial UNIQUE PRIMARY KEY ,
  "user_mail" varchar(50),
  "user_password" varchar(50),
  "full_name" varchar(50)
);

CREATE TABLE "teachers" (
  "teacher_id" serial UNIQUE PRIMARY KEY ,
  "teacher_full_name" varchar(50),
  "max_degree" varchar(30),
  "experience_years" integer
);

CREATE TABLE "students" (
  "student_id" serial UNIQUE PRIMARY KEY ,
  "student_full_name" varchar(50),
  "faculty" varchar(50),
  "available_credits" integer,
  "enrolled_credits" integer,
  "student_email" varchar(255),
  "student_phone" varchar(10)
);

CREATE TABLE "enrollments" (
  "enrollment_id" serial UNIQUE PRIMARY KEY ,
  "student_id" integer,
  "course_id" integer,
  "enrollment_date" date
);

CREATE TABLE "course_prerequisites" (
  "prerequisite_id" serial UNIQUE PRIMARY KEY ,
  "course_id" integer,
  "prerequisite_course_id" integer
);

CREATE TABLE "history_approved_courses" (
  "history_courses_id" serial UNIQUE PRIMARY KEY ,
  "student_id" integer,
  "course_id" integer
);

ALTER TABLE "courses" ADD FOREIGN KEY ("teacher_id") REFERENCES "teachers" ("teacher_id");

ALTER TABLE "courses" ADD FOREIGN KEY ("course_student_monitor_id") REFERENCES "students" ("student_id");

ALTER TABLE "enrollments" ADD FOREIGN KEY ("student_id") REFERENCES "students" ("student_id");

ALTER TABLE "enrollments" ADD FOREIGN KEY ("course_id") REFERENCES "courses" ("course_id");

ALTER TABLE "course_prerequisites" ADD FOREIGN KEY ("course_id") REFERENCES "courses" ("course_id");

ALTER TABLE "course_prerequisites" ADD FOREIGN KEY ("prerequisite_course_id") REFERENCES "courses" ("course_id");

ALTER TABLE "history_approved_courses" ADD FOREIGN KEY ("student_id") REFERENCES "students" ("student_id");

ALTER TABLE "history_approved_courses" ADD FOREIGN KEY ("course_id") REFERENCES "courses" ("course_id");




ALTER SEQUENCE students_student_id_seq RESTART WITH 30000;

ALTER SEQUENCE teachers_teacher_id_seq RESTART WITH 50000;

ALTER SEQUENCE enrollments_enrollment_id_seq RESTART WITH 10000;

ALTER SEQUENCE courses_course_id_seq RESTART WITH 70000;

ALTER SEQUENCE history_approved_courses_history_courses_id_seq RESTART WITH 90000;

ALTER SEQUENCE course_prerequisites_prerequisite_id_seq RESTART WITH 40000;