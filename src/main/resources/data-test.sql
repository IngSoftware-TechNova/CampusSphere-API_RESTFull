-- 1. Insertar en la tabla 'categorias'
INSERT INTO categorias (id, nombre)
VALUES (1, 'Tecnología'),
       (2, 'Marketing'),
       (3, 'Emprendimiento');

-- 2. Insertar en la tabla 'tarifarios'
INSERT INTO tarifarios (id, descripcion, precio)
VALUES (1, 'Tarifa Estándar', 50.00),
       (2, 'Tarifa Premium', 100.00);

-- 3. Insertar en la tabla 'ubicaciones'
INSERT INTO ubicaciones (id, ciudad, direccion, pais)
VALUES (1, 'Lima', 'Av. Perú 123', 'Perú'),
       (2, 'Cusco', 'Calle Comercio 456', 'Perú'),
       (3, 'Arequipa', 'Plaza de Armas 789', 'Perú');

-- 4. Insertar en la tabla 'eventos'
INSERT INTO eventos (id, capacidad, descripcion, nombre, categoria_id, tarifario_id, ubicacion_id)
VALUES (1, 100, 'Tech Expo 2024', 'Tech Expo', 1, 1, 1),
       (2, 50, 'Marketing 101', 'Conferencia de Marketing', 2, 2, 2),
       (3, 200, 'Feria de Emprendedores', 'Emprende 2024', 3, 1, 3);

-- 5. Insertar en la tabla 'estudiantes'
INSERT INTO estudiantes (id, carrera, contraseña, email, nombre)
VALUES (1, 'Ingeniería de Sistemas', 'contraseña123', 'juan.perez@correo.com', 'Juan Pérez'),
       (2, 'Marketing', 'contraseña456', 'ana.lopez@correo.com', 'Ana López'),
       (3, 'Emprendimiento', 'contraseña789', 'pedro.garcia@correo.com', 'Pedro García');

-- 6. Insertar en la tabla 'horarios'
INSERT INTO horarios (id, hora_inicio, hora_fin)
VALUES (1, '2024-09-25 09:00:00', '2024-09-25 12:00:00'),
       (2, '2024-09-25 14:00:00', '2024-09-25 17:00:00'),
       (3, '2024-09-25 18:00:00', '2024-09-25 20:00:00');

-- 7. Insertar en la tabla 'programaciones_eventos'
INSERT INTO programaciones_eventos (fecha_inicio, fecha_fin, evento_id, horario_id)
VALUES ('2024-10-01', '2024-10-01', 1, 1),
       ('2024-10-05', '2024-10-05', 2, 2),
       ('2024-10-10', '2024-10-10', 3, 3);

-- 8. Insertar en la tabla 'comentarios'
INSERT INTO comentarios (id, fecha_comentar, estudiante_id, evento_id, comentario)
VALUES (1, '2024-09-25', 1, 1, 'Evento muy interesante.'),
       (2, '2024-09-26', 2, 2, 'Buena organización.'),
       (3, '2024-09-27', 3, 3, 'Gran oportunidad de networking.');

-- 9. Insertar en la tabla 'puntuaciones'
INSERT INTO puntuaciones (id, puntuacion, estudiante_id, evento_id)
VALUES (1, 5, 1, 1),
       (2, 4, 2, 2),
       (3, 3, 3, 3);

-- 10. Insertar en la tabla 'inscripciones'
INSERT INTO inscripciones (id, fecha_inscripcion, inscripcion_status, estudiante_id, evento_id)
VALUES (1, '2024-09-20', 'confirmada', 1, 1),
       (2, '2024-09-21', 'pendiente', 2, 2),
       (3, '2024-09-22', 'cancelada', 3, 3);
