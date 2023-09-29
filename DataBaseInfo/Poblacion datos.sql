--Poblacion datos
-- Insertar cursos
INSERT INTO courses ("teacher_id", "course_name", "amount_credits", "available_places", "course_student_monitor_id")
VALUES
  (50003, 'Matemáticas', 3, 30, 30003),
  (50000, 'Historia', 2, 25, NULL),
  (50001, 'Física', 3, 20, 30001),
  (50002, 'Inglés', 1, 40, 30004),
  (50004, 'Logica', 3, 40, 30008),
  (50006, 'Comunicación', 2, 40, NULL),
  (50005, 'Programación', 3, 35, 30013),
  (50003, 'Programación II', 3, 35, 30012);

-- Insertar estudiantes
INSERT INTO students (monitor_course_id, student_full_name, faculty, available_credits, enrolled_credits) 
VALUES
  (NULL, 'Ana Pérez', 'Ciencias', 17, 0),
  (NULL, 'María Rodríguez', 'Ciencias', 19, 0),
  (NULL, 'Julia Vargas', 'Ciencias', 17, 0),
  (NULL, 'Elena Sánchez', 'Ciencias', 22, 0),
  (NULL, 'Luis Torres', 'Ciencias', 16, 0),
  (NULL, 'Jorge Ramírez', 'Ingeniería', 15, 0),
  (NULL, 'Sofía Morales', 'Ciencias', 16, 0),
  (NULL, 'Diego Ruiz', 'Ciencias', 12, 0),
  (70002, 'Carlos López', 'Ciencias', 12, 0),
  (70000, 'Javier Martínez', 'Ingeniería', 15, 0),
  (70003, 'Laura Fernández', 'Ingeniería', 14, 0),
  (70004, 'Marta García', 'Ingeniería', 12, 0),
  (70007, 'Carmen Castro', 'Ingeniería', 17, 0),
  (70006, 'Pedro Díaz', 'Ingeniería', 19, 0),
  (NULL, 'Camilo Echeverry', 'Ingeniería', 12, 0);

-- Insertar profesores
INSERT INTO teachers (teacher_full_name, max_degree, experience_years) 
VALUES
  ('Alberto Martínez', 'Doctorado', 10),
  ('Elisa González', 'Maestría', 5),
  ('Antonio López', 'Doctorado', 8),
  ('Beatriz Sánchez', 'Maestría', 6),
  ('Juan Ramírez', 'Doctorado', 12),
  ('Maribel Amu', 'Maestría', 7),
  ('Victor Hurtado', 'Doctorado', 9);




-- Insertar inscripciones (Estudiantes en cursos)
INSERT INTO enrollments (student_id, course_id, enrollment_date)
VALUES
  (30000, 70000, '2023-09-16'),--
  (30000, 70001, '2023-09-17'),--
  (30001, 70002, '2023-09-18'),--
  (30001, 70003, '2023-09-16'),--
  (30002, 70001, '2023-09-17'),--
  (30002, 70003, '2023-09-19'),--
  (30003, 70002, '2023-09-18'),--
  (30004, 70003, '2023-09-19'),--
  (30005, 70004, '2023-09-20'),--
  (30006, 70000, '2023-09-16'),--
  (30006, 70004, '2023-09-20'),--
  (30007, 70001, '2023-09-17'),--
  (30007, 70003, '2023-09-19'),--
  (30008, 70000, '2023-09-16'),--
  (30009, 70002, '2023-09-13'),--
  (30009, 70004, '2023-09-14'),--
  (30010, 70000, '2023-09-15'),--
  (30011, 70002, '2023-09-18'),--
  (30011, 70001, '2023-09-11'),--
  (30012, 70000, '2023-09-16'),--
  (30012, 70004, '2023-09-18'),--
  (30013, 70001, '2023-09-13'),--
  (30014, 70003, '2023-09-12');--
  

-- Insertar prerequisitos (Requisitos de cursos)
INSERT INTO course_prerequisites ("course_id", "prerequisite_course_id")
VALUES
  (70006, 70007),
  (70002, 70000),
  (70007, 70004),
  (70003, 70005);



-- Insertar historial de cursos vistos
INSERT INTO history_approved_courses ("student_id", "course_id")
VALUES
  (30001,70000),
  (30001,70005),
  (30002,70005),
  (30003,70000),
  (30004,70005),
  (30007,70005),
  (30009,70000),
  (30011,70000),
  (30014,70005);

