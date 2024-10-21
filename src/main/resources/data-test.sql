/*
INSERT INTO roles (id, name) VALUES
(1, 'ADMIN'),
(2, 'STUDENT');


INSERT INTO users (id, role_id, email, password) VALUES
(1, 2, 'admin@example.com', 'admin123'),
(2, 2, 'organizador@example.com', 'org123'),
(3, 2, 'student1@example.com', 'stud123'),
(4, 2, 'student2@example.com', 'stud123'),
(5, 2, 'ponente@example.com', 'ponente123'),
(6, 2, 'moderador@example.com', 'mod123'),
(7, 2, 'participante@example.com', 'part123'),
(8, 2, 'invitado@example.com', 'inv123'),
(9, 2, 'voluntario@example.com', 'vol123'),
(10, 2, 'patrocinador@example.com', 'pat123');



INSERT INTO students (id, user_id, first_name, last_name, career, created_at, updated_at) VALUES
(1, 3, 'Juan', 'Pérez', 'Ingeniería', '2023-01-15', '2024-04-10'),
(2, 4, 'María', 'López', 'Medicina', '2023-02-18', NULL),
(3, 5, 'Carlos', 'Gómez', 'Derecho', '2022-11-25', '2024-03-12'),
(4, 6, 'Ana', 'Martínez', 'Psicología', '2023-05-30', NULL),
(5, 7, 'Luis', 'Sánchez', 'Administración', '2022-09-08', '2024-06-19'),
(6, 8, 'Carmen', 'Díaz', 'Arquitectura', '2023-03-10', '2023-10-05'),
(7, 9, 'José', 'Fernández', 'Economía', '2023-01-05', NULL),
(8, 10, 'Lucía', 'Torres', 'Diseño', '2023-07-14', '2024-01-20'),
(9, 11, 'Pedro', 'Ramírez', 'Computación', '2023-06-22', NULL),
(10, 12, 'Elena', 'Vega', 'Ciencias Sociales', '2023-04-02', '2024-02-17');


INSERT INTO locations (id, city, country, location) VALUES
(1, 'Lima', 'Perú', 'Auditorio Central'),
(2, 'Cusco', 'Perú', 'Sala 1'),
(3, 'Arequipa', 'Perú', 'Centro de Convenciones'),
(4, 'Trujillo', 'Perú', 'Salón Principal'),
(5, 'Piura', 'Perú', 'Campus Norte'),
(6, 'Lima', 'Perú', 'Sala de Conferencias'),
(7, 'Iquitos', 'Perú', 'Aula Magna'),
(8, 'Chiclayo', 'Perú', 'Teatro Municipal'),
(9, 'Huancayo', 'Perú', 'Centro Cultural'),
(10, 'Tacna', 'Perú', 'Auditorio Regional');


INSERT INTO categories (id, name) VALUES
(1, 'Conferencia'),
(2, 'Seminario'),
(3, 'Taller'),
(4, 'Congreso'),
(5, 'Charla Motivacional'),
(6, 'Foro'),
(7, 'Panel de Discusión'),
(8, 'Hackathon'),
(9, 'Exposición'),
(10, 'Feria');

 */
 /*
INSERT INTO prices (id, price, description) VALUES
(1, 0.00, 'Gratuito'),
(2, 10.00, 'Entrada Estándar'),
(3, 20.00, 'Entrada Premium'),
(4, 50.00, 'VIP'),
(5, 5.00, 'Descuento Estudiante'),
(6, 100.00, 'Pase Completo'),
(7, 15.00, 'Entrada con Almuerzo'),
(8, 30.00, 'Workshop Adicional'),
(9, 25.00, 'Taller Avanzado'),
(10, 0.00, 'Invitado Especial');


INSERT INTO events (id, name, description, capacity, category_id, location_id, price_id, created_at, updated_at) VALUES
(1, 'Concert', 'Live music concert', 500, 1, 1, 1, NOW(), NOW()),
(2, 'Art Workshop', 'Painting and drawing class', 30, 2, 2, 2, NOW(), NOW()),
(3, 'Tech Conference', 'Latest trends in technology', 300, 3, 3, 3, NOW(), NOW()),
(4, 'Marathon', 'Running competition', 1000, 4, 4, 4, NOW(), NOW()),
(5, 'Cooking Class', 'Learn to cook Italian food', 20, 6, 5, 5, NOW(), NOW()),
(6, 'Business Seminar', 'How to start a business', 150, 7, 6, 6, NOW(), NOW()),
(7, 'Yoga Session', 'Relaxation and wellness', 40, 8, 7, 7, NOW(), NOW()),
(8, 'Travel Talk', 'Travel tips and tricks', 80, 9, 8, 8, NOW(), NOW()),
(9, 'Science Fair', 'Showcase of scientific projects', 200, 10, 9, 9, NOW(), NOW()),
(10, 'Football Match', 'Local teams competing', 10000, 4, 10, 10, NOW(), NOW());


INSERT INTO inscriptions (id, event_id, user_id, inscription_date, inscription_status) VALUES
(1, 1, 3, '2024-03-10', 'PAID'),
(2, 2, 4, '2024-03-11', 'PENDING'),
(3, 3, 5, '2024-03-15', 'PAID'),
(4, 4, 6, '2024-03-18', 'PAID'),
(5, 5, 7, '2024-03-20', 'PENDING'),
(6, 6, 8, '2024-03-21', 'PAID'),
(7, 7, 9, '2024-03-25', 'PENDING'),
(8, 8, 10, '2024-03-30', 'PAID'),
(9, 9, 11, '2024-04-01', 'PENDING'),
(10, 10, 12, '2024-04-05', 'PAID');

INSERT INTO ratings (id, rate, event_id, student_id, created_at, updated_at) VALUES
(1, 5, 1, 1, '2024-04-10', '2024-04-11'),
(2, 4, 2, 2, '2024-04-12', NULL),
(3, 3, 3, 3, '2024-04-13', NULL),
(4, 5, 4, 4, '2024-04-14', '2024-04-15'),
(5, 2, 5, 5, '2024-04-16', NULL),
(6, 5, 6, 6, '2024-04-17', '2024-04-18'),
(7, 3, 7, 7, '2024-04-19', NULL),
(8, 4, 8, 8, '2024-04-20', '2024-04-21'),
(9, 5, 9, 9, '2024-04-22', NULL),
(10, 4, 10, 10, '2024-04-23', '2024-04-24');



  */
/*
INSERT INTO student_event_interests (id_event_interest, id_student_interest) VALUES
                                                                              (1, 1),
                                                                              (2, 1),
                                                                              (1, 2),
                                                                              (3, 2),
                                                                              (3, 3);


/*
-- Añadir datos a la tabla schedules
INSERT INTO schedules (id, start_hour, end_hour, description) VALUES
(1, '09:00', '11:00', 'Primera sesión'),
(2, '11:00', '13:00', 'Segunda sesión'),
(3, '14:00', '16:00', 'Tercera sesión'),
(4, '16:00', '18:00', 'Cuarta sesión'),
(5, '18:00', '20:00', 'Quinta sesión'),
(6, '08:00', '10:00', 'Apertura'),
(7, '10:00', '12:00', 'Presentación principal'),
(8, '12:00', '14:00', 'Mesa redonda'),
(9, '15:00', '17:00', 'Sesión de cierre'),
(10, '17:00', '19:00', 'Networking');

 */
funcion de postgre
CREATE OR REPLACE FUNCTION fn_list_inscriptions_per_event_report()
RETURNS TABLE(
	event_name VARCHAR,
	total_inscriptions INT
) AS $$
BEGIN
	RETURN QUERY
	SELECT e.name AS event_name, COUNT(i.id)::int AS total_inscriptions
	FROM events e
	LEFT JOIN inscriptions i ON e.id = i.event_id
	GROUP BY e.name
	ORDER BY total_inscriptions DESC;
END; $$
LANGUAGE plpgsql;
 */