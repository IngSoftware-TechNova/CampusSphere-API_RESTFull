/*
INSERT INTO roles (id, name) VALUES
(1, 'ADMIN'),
(2, 'STUDENT');


INSERT INTO users (id, role_id, email, password, profile_pic_path) VALUES
(1, 1, 'admin@example.com', '$2a$10$PzUVO/510BcY7LtZiRV/.uXMRGXiFJuiO0gtfwrB9J5NJoRieabn6', ddc51626-7acb-4468-990a-2011c649e72b.jpg),
(2, 2, 'student1@example.com', '$2a$10$plESYmrgNN3fIK2nHmqXlOehFndDlJ8fXuyWzVhNT4kmfESIOykmW', 3f4fb12d-eb55-4106-9fbe-09fe97bbcd5f.jpg),
(3, 2, 'student2@example.com', '$2a$10$CeqxuJX/CkC8UVxAiDz2eOwgV0b/VmWKmvm88YTvV4DvRGQIbFo5i', b6ae07b5-58e9-41f6-a06f-a77fdd53ee6e.jpg),
(4, 2, 'student3@example.com', '$2a$10$bYvnpX8Wct8KjNFHW2DPBOKmN.W14ncg7QtU1mMf8ln0wmQ9ZA4zi', f874b43e-072f-44c5-bfcd-062d5d37d9ad.jpg),
(5, 2, 'student4@example.com', '$2a$10$wXHLojkUxfStLZAMgOf12ekxmi9Hfs9/NRH.M9SPmYwSQkmPgVzSq', af07f0bc-e410-459b-9387-6417b50b4ef2.jpg),
(6, 2, 'student5@example.com', '$2a$10$ME.w0p.LNZ6MOng1/YxXT.wfiiH6r/7eSvXlHa98Ugf3CXHjll7hS', 37328ba1-0830-4b9f-8522-a20a907af260.png),
(7, 2, 'student6@example.com', '$2a$10$jD78n6IHGcPEfiXrKFAXxehiGDgrgol4MLDglDTnhukInE78hJZM6', ee777b7c-51f7-4ce7-90e7-169b83fef521.png),
(8, 2, 'student7@example.com', '$2a$10$CRb9QFmOnIFhn8dnnX5Aj.jNILVUeKayoMMFLj0nYg1gEpbFt2Mu6', be6621fb-31c1-4e9a-9208-ed6e64c232d3.png),
(9, 2, 'student8@example.com', '$2a$10$WULz1TnsCFz43Usnk0Mj4O3WUScbuz57m92t0Il80wofSriTpIUkq', 3a26a86f-5be4-46e8-9c38-7e14abeb7f51.png),
(10, 2, 'student9@example.com', '$2a$10$efsbLTLDkZkiKpCur.2HFe0/y3P36D0Eqg12tsHInmfOGuVUFbdZi', 572cd96e-ac2e-461d-b4a3-a98c11646274.png);



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

INSERT INTO events (id, name, description, capacity, category_id, location_id, price_id, created_at, image_path) VALUES
(1, 'Concert', 'Live music concert', 500, 1, 1, 1, NOW(), a40e9107-4b09-4ade-aada-abed09ee91a1.png),
(2, 'Art Workshop', 'Painting and drawing class', 30, 2, 2, 2, NOW(), 65f55bd2-33e0-4727-a0f9-24525d657fc5.jpg),
(3, 'Tech Conference', 'Latest trends in technology', 300, 3, 3, 3, NOW(), bcdec13c-c1d1-4964-a1db-c9968b4d06d3.jpg),
(4, 'Marathon', 'Running competition', 1000, 4, 4, 4, NOW(), da0ec08a-7cec-4155-a0ed-db939e1880a1.jpg),
(5, 'Cooking Class', 'Learn to cook Italian food', 20, 6, 5, 5, NOW(), a408b4e2-005f-4f54-89a3-16053e8ace3c.png),
(6, 'Business Seminar', 'How to start a business', 150, 7, 6, 6, NOW(), 40f17fb5-f196-41fe-a8b7-86c287ad464d.png),
(7, 'Yoga Session', 'Relaxation and wellness', 40, 8, 7, 7, NOW(), ec912dbe-bb48-454a-9170-6d1426af9dff.jpg),
(8, 'Travel Talk', 'Travel tips and tricks', 80, 9, 8, 8, NOW(), 669363a0-5084-4134-ae6b-01d1327dc9ee.jpg),
(9, 'Science Fair', 'Showcase of scientific projects', 200, 10, 9, 9, NOW(), 96dbfb6c-acda-4d5c-80c0-945c7a638e4b.png),
(10, 'Football Match', 'Local teams competing', 10000, 4, 10, 10, NOW(), 269ef701-adfb-4147-9e32-3c6a8259aa5d.jpg);


 */
/*
INSERT INTO inscriptions (id, event_id, user_id, inscription_date, inscription_status) VALUES
(1, 1, 3, NOW(), 'PAID'),
(2, 2, 4, NOW(), 'PENDING'),
(3, 3, 5, NOW(), 'PAID'),
(4, 4, 6, NOW(), 'PAID'),
(5, 5, 7, NOW(), 'PENDING'),
(6, 6, 8, NOW(), 'PAID'),
(7, 7, 9, NOW(), 'PENDING'),
(8, 8, 10, NOW(), 'PAID');

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
