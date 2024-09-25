-- Añadir datos a la tabla estudiantes
INSERT INTO estudiantes (id, name, email, career, password, created_at, updated_at) VALUES
                                                                     (1, 'Carlos Pérez', 'cperez@mail.com', 'Ingeniería de Sistemas', '1234', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                     (2, 'Ana López', 'alopez@mail.com', 'Administración', '5432', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                     (3, 'José Torres', 'jtorres@mail.com', 'Marketing', 'password', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

