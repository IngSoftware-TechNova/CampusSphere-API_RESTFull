/*

INSERT INTO roles (id, name) VALUES
(1, 'ADMIN'),
(2, 'STUDENT');


INSERT INTO users (id, role_id, email, password) VALUES
(1, 1, 'admin@example.com', '$2a$10$PzUVO/510BcY7LtZiRV/.uXMRGXiFJuiO0gtfwrB9J5NJoRieabn6'),
(2, 2, 'student1@example.com', '$2a$10$plESYmrgNN3fIK2nHmqXlOehFndDlJ8fXuyWzVhNT4kmfESIOykmW'),
(3, 2, 'student2@example.com', '$2a$10$CeqxuJX/CkC8UVxAiDz2eOwgV0b/VmWKmvm88YTvV4DvRGQIbFo5i'),
(4, 2, 'student3@example.com', '$2a$10$bYvnpX8Wct8KjNFHW2DPBOKmN.W14ncg7QtU1mMf8ln0wmQ9ZA4zi'),
(5, 2, 'student4@example.com', '$2a$10$wXHLojkUxfStLZAMgOf12ekxmi9Hfs9/NRH.M9SPmYwSQkmPgVzSq'),
(6, 2, 'student5@example.com', '$2a$10$ME.w0p.LNZ6MOng1/YxXT.wfiiH6r/7eSvXlHa98Ugf3CXHjll7hS'),
(7, 2, 'student6@example.com', '$2a$10$jD78n6IHGcPEfiXrKFAXxehiGDgrgol4MLDglDTnhukInE78hJZM6'),
(8, 2, 'student7@example.com', '$2a$10$CRb9QFmOnIFhn8dnnX5Aj.jNILVUeKayoMMFLj0nYg1gEpbFt2Mu6'),
(9, 2, 'student8@example.com', '$2a$10$WULz1TnsCFz43Usnk0Mj4O3WUScbuz57m92t0Il80wofSriTpIUkq'),
(10, 2, 'student9@example.com', '$2a$10$efsbLTLDkZkiKpCur.2HFe0/y3P36D0Eqg12tsHInmfOGuVUFbdZi');



INSERT INTO students (id, user_id, first_name, last_name, career, created_at, updated_at) VALUES
(1, 1, 'Technova', 'Student', 'Admin', '2024-10-20 14:13:31.168949', '2024-04-10'),
(2, 2, 'Jorge', 'López', 'Medicina', '2023-02-18 14:10:31.168949', NULL),
(3, 3, 'Carlos', 'Gómez', 'Derecho', '2022-11-25 11:13:31.168949', '2024-03-12'),
(4, 4, 'Ana', 'Martínez', 'Psicología', '2023-05-30 13:13:31.168949', NULL),
(5, 5, 'Luis', 'Sánchez', 'Administración', '2022-09-08 15:13:31.168949', '2024-06-19 14:13:31.168949'),
(6, 6, 'Carmen', 'Díaz', 'Arquitectura', '2023-03-10 14:14:39.168949', '2023-10-05 14:13:31.168949'),
(7, 7, 'José', 'Fernández', 'Economía', '2023-01-05 10:13:31.168949', NULL),
(8, 8, 'Lucía', 'Torres', 'Diseño', '2023-07-14 14:17:31.168949', '2024-01-20 14:13:31.168949'),
(9, 9, 'Pedro', 'Ramírez', 'Computación', '2023-06-22 14:11:30.168949', NULL),
(10, 10, 'Elena', 'Vega', 'Ciencias Sociales', '2023-04-02 14:19:31.168949', '2024-02-17 14:13:31.168949');


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

INSERT INTO events (id, name, description, capacity, category_id, location_id, price_id, created_at) VALUES
(1, 'Concert', 'Live music concert', 500, 1, 1, 1, NOW()),
(2, 'Art Workshop', 'Painting and drawing class', 30, 2, 2, 2, NOW()),
(3, 'Tech Conference', 'Latest trends in technology', 300, 3, 3, 3, NOW()),
(4, 'Marathon', 'Running competition', 1000, 4, 4, 4, NOW()),
(5, 'Cooking Class', 'Learn to cook Italian food', 20, 6, 5, 5, NOW()),
(6, 'Business Seminar', 'How to start a business', 150, 7, 6, 6, NOW()),
(7, 'Yoga Session', 'Relaxation and wellness', 40, 8, 7, 7, NOW()),
(8, 'Travel Talk', 'Travel tips and tricks', 80, 9, 8, 8, NOW()),
(9, 'Science Fair', 'Showcase of scientific projects', 200, 10, 9, 9, NOW()),
(10, 'Football Match', 'Local teams competing', 10000, 4, 10, 10, NOW());


INSERT INTO inscriptions (id, event_id, user_id, inscription_date, inscription_status) VALUES
(1, 1, 3, NOW(), 'PAID'),
(2, 2, 4, NOW(), 'PENDING'),
(3, 3, 5, NOW(), 'PAID'),
(4, 4, 6, NOW(), 'PAID'),
(5, 5, 7, NOW(), 'PENDING'),
(6, 6, 8, NOW(), 'PAID'),
(7, 7, 9, NOW(), 'PENDING'),
(8, 8, 10, NOW(), 'PAID'),
(9, 9, 11, NOW(), 'PENDING'),
(10, 10, 12, NOW(), 'PAID');

INSERT INTO ratings (id, rate, event_id, user_id, created_at) VALUES
(1, 5, 1, 2, NOW()),
(2, 4, 2, 2, NOW()),
(3, 3, 3, 3, NOW()),
(4, 5, 4, 4, NOW()),
(5, 2, 5, 5, NOW()),
(6, 5, 6, 6, NOW()),
(7, 3, 7, 7, NOW()),
(8, 4, 8, 8, NOW()),
(9, 5, 9, 9, NOW()),
(10, 4, 10, 10, NOW());

*/
/*
INSERT INTO student_event_interests (id_event_interest, id_student_interest) VALUES
                                                                              (1, 1),
                                                                              (2, 1),
                                                                              (1, 2),
                                                                              (3, 2),
                                                                              (3, 3);

 */

/*
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
