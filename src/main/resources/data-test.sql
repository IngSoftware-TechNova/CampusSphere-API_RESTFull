/*
INSERT INTO students (id, career, email, name,  password) VALUES
                                                                                         (1, 'John Doe', 'john.doe@example.com', 'Computer Science', 'password123'),
                                                                                         (2, 'Jane Smith', 'jane.smith@example.com', 'Engineering', 'password123'),
                                                                                         (3, 'Robert Brown', 'robert.brown@example.com', 'Mathematics', 'password123');


INSERT INTO locations (id, location, city, country) VALUES
                                                        (1, 'Main Hall', 'New York', 'USA'),
                                                        (2, 'Innovation Lab', 'San Francisco', 'USA'),
                                                        (3, 'Tech Building', 'Boston', 'USA');
INSERT INTO categories (id, name) VALUES
                                      (1, 'Technology'),
                                      (2, 'Programming'),
                                      (3, 'Data Science');

 */
 /*
INSERT INTO prices (id, description,price) VALUES
                                             (1, 'General Admission', 100.00),
                                             (2, 'Student Admission', 50.00),
                                             (3, 'Free Admission', 0.00);

INSERT INTO events (id, name, capacity, description, location_id, category_id, price_id) VALUES
                                                                                             (1, 'AI Conference', 200, 'Conference about the latest in AI', 1, 1, 1),
                                                                                             (2, 'Hackathon 2024', 100, '24-hour hackathon for students', 2, 2, 2),
                                                                                             (3, 'Data Science Workshop', 50, 'Hands-on workshop on data science tools', 3, 3, 3);


  */
/*
INSERT INTO student_event_interests (id_event_interest, id_student_interest) VALUES
                                                                              (1, 1),
                                                                              (2, 1),
                                                                              (1, 2),
                                                                              (3, 2),
                                                                              (3, 3);

=======
-- Añadir datos a la tabla estudiantes
/*
INSERT INTO estudiantes (id, name, email, career, password, created_at, updated_at) VALUES
                                                                     (1, 'Carlos Pérez', 'cperez@mail.com', 'Ingeniería de Sistemas', '1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                     (2, 'Ana López', 'alopez@mail.com', 'Administración', '5432', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                     (3, 'José Torres', 'jtorres@mail.com', 'Marketing', 'password', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

*/

/*
-- Añadir datos a la tabla schedules
INSERT INTO schedules (id, start_hour, end_hour, description) VALUES
                                                                  (1, '08:00:00', '12:00:00', 'Horario matutino temprano'),
                                                                  (2, '14:00:00', '18:00:00', 'Horario media tarde'),
                                                                  (3, '09:00:00', '11:00:00', 'Horario matutino avanzado');
*/
/*
INSERT INTO prices (id, price, description) VALUES
                                                 (1, 100.00, 'Tarifa estándar'),
                                                 (2, 50.00, 'Tarifa estudiante'),
                                                 (3, 150.00, 'Tarifa premium'),
                                                 (4, 200.00, 'Tarifa corporativa'),
                                                 (5, 75.00, 'Tarifa de promoción');
*/
/*
INSERT INTO locations (location, city, country) VALUES
                                                      ('Av. José Larco 123', 'Lima', 'Perú'),
                                                      ('Jr. Puno 456', 'Lima', 'Perú'),
                                                      ('Av. Arequipa 789', 'Lima', 'Perú'),
                                                      ('Av. Brasil 1010', 'Lima', 'Perú'),
                                                      ('Jr. Tacna 2020', 'Lima', 'Perú');



 */
/*
INSERT INTO categories (name) VALUES ('Tecnología');
INSERT INTO categories (name) VALUES ('Ciencia');
INSERT INTO categories (name) VALUES ('Arte');
INSERT INTO categories (name) VALUES ('Literatura');
INSERT INTO categories (name) VALUES ('Deportes');
*/