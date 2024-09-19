/*
-- Añadir datos a la tabla ubicaciones
INSERT INTO ubicaciones (id, direccion, ciudad, pais) VALUES
                                                          (1, '123 Calle Falsa', 'Lima', 'Perú'),
                                                          (2, '456 Avenida Siempreviva', 'Arequipa', 'Perú'),
                                                          (3, '789 Calle Pino', 'Cusco', 'Perú');

-- Añadir datos a la tabla categorias
INSERT INTO categorias (id, nombre) VALUES
                                        (1, 'Deportes'),
                                        (2, 'Música'),
                                        (3, 'Tecnología');

-- Añadir datos a la tabla tarifarios
INSERT INTO tarifarios (id, precio, descripcion) VALUES
                                                     (1, 50.00, 'Precio general para eventos deportivos'),
                                                     (2, 100.00, 'Precio para conciertos y eventos musicales'),
                                                     (3, 150.00, 'Eventos tecnológicos de alto perfil');
*/
-- Añadir datos a la tabla estudiantes
INSERT INTO students (id, name, email, career, password) VALUES
                                                                     (1, 'Carlos Pérez', 'cperez@mail.com', 'Ingeniería de Sistemas', '12345'),
                                                                     (2, 'Ana López', 'alopez@mail.com', 'Administración', '54321'),
                                                                     (3, 'José Torres', 'jtorres@mail.com', 'Marketing', 'password');
/*
-- Añadir datos a la tabla horarios
INSERT INTO horarios (id, hora_inicio, hora_fin) VALUES
                                                     (1, '2024-09-09 08:00:00.000000', '2024-09-09 12:00:00.000000'),
                                                     (2, '2024-09-09 14:00:00.000000', '2024-09-09 18:00:00.000000'),
                                                     (3, '2024-09-10 08:00:00.000000', '2024-09-10 12:00:00.000000');

-- Añadir datos a la tabla eventos
INSERT INTO eventos (id, nombre, capacidad, descripcion, ubicacion_id, categoria_id, tarifario_id) VALUES
                                                                                                       (1, 'Maratón de Lima', 500, 'Competencia anual de maratón', 1, 1, 1),
                                                                                                       (2, 'Concierto RockFest', 300, 'Concierto de bandas de rock', 2, 2, 2),
                                                                                                       (3, 'Tech Conference 2024', 200, 'Conferencia sobre avances en tecnología', 3, 3, 3);
*/
-- Añadir datos a la tabla inscripciones
INSERT INTO inscriptions (id, inscription_date, inscription_status, event_id, student_id) VALUES
                                                                                                    (1, '2024-09-09 08:00:00.000000', 'PAID', 1, 1),
                                                                                                    (2, '2024-09-09 08:30:00.000000', 'PENDING', 2, 2),
                                                                                                    (3, '2024-09-09 09:00:00.000000', 'PAID', 3, 3);
/*
-- Añadir datos a la tabla puntuaciones
INSERT INTO puntuaciones (id, puntuacion, evento_id, estudiante_id) VALUES
                                                                        (1, 5, 1, 1),
                                                                        (2, 4, 2, 2),
                                                                        (3, 3, 3, 3);

-- Añadir datos a la tabla comentarios
INSERT INTO comentarios (id, texto, fecha_comentar, evento_id, estudiante_id) VALUES
                                                                                  (1, 'Muy buen event!', '2024-09-09 10:00:00.000000', 1, 1),
                                                                                  (2, 'Estuvo divertido!', '2024-09-09 10:30:00.000000', 2, 2),
                                                                                  (3, 'Interesante, pero puede mejorar.', '2024-09-09 11:00:00.000000', 3, 3);

-- Añadir datos a la tabla programaciones_eventos
INSERT INTO programaciones_eventos (fecha_inicio, fecha_fin, horario_id, evento_id) VALUES
                                                                                        ('2024-09-09 08:00:00.000000', '2024-09-09 12:00:00.000000', 1, 1),
                                                                                        ('2024-09-09 14:00:00.000000', '2024-09-09 18:00:00.000000', 2, 2),
                                                                                        ('2024-09-10 08:00:00.000000', '2024-09-10 12:00:00.000000', 3, 3);
 */