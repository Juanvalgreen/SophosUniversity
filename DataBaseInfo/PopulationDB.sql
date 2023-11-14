--Poblacion datos




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

-- Insertar estudiantes
INSERT INTO students (student_full_name, faculty, available_credits, enrolled_credits, student_email, student_phone) VALUES
('Camilo Echeverry', 'Ingeniería', 12, 1, NULL, NULL),
('María Rodríguez', 'Ciencias', 19, 3, 'marodri@gmail.com', '3185698754'),
('Julia Vargas', 'Ciencias', 17, 3, NULL, '3125678227'),
('Elena Sánchez', 'Ciencias', 22, 6, 'sanchelena@hotmail.com', '3105279064'),
('Jorge Ramírez', 'Ingeniería', 15, 6, 'Jorgeram@outlook.com', NULL),
('Sofía Morales', 'Ciencias', 16, 3, NULL, '3196575859'),
('Diego Ruiz', 'Ciencias', 12, 5, NULL, '3126546596'),
('Javier Martínez', 'Ingeniería', 15, 3, 'nezvija28@gmail.com', '3007582121'),
('Laura Fernández', 'Ingeniería', 14, 1, 'lausof12fer@hotmail.com', '3132562529'),
('Marta García', 'Ingeniería', 12, 3, NULL, '3152455831'),
('Carmen Castro', 'Ingeniería', 17, 6, 'carmencitafiufiu@hotmail.com', NULL),
('Pedro Díaz', 'Ingeniería', 19, 2, 'pedrodiaz345@gmail.com', '3126544798'),
('Luis Torres', 'Ciencias', 16, 3, 'Towerluis911@outlook.com', '3185271798'),
('Carlos López', 'Ciencias', 12, 4, 'carpez36@outlook.com', NULL),
('Ana Pérez', 'Ciencias', 17, 5, 'perana@gmail.com', '3125279898');

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

