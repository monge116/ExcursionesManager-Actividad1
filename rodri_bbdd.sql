create database rodri_bbdd;
use rodri_bbdd;
CREATE TABLE excursiones (
	id INT NOT NULL AUTO_INCREMENT,
	descripcion varchar(9999),
	origen varchar(255),
    destino varchar(255),
    fecha_excursion date,
    duracion int,
    estado varchar(20),
    check(estado in('cancelado', 'terminado', 'creado')),
    destacado varchar(1),
    check (destacado in('S', 'N')),
    aforoMaximo int,
    minimoAsistentes int,
    precioUnitario double,
    imagen varchar(255),
    fecha_alta timestamp,
    PRIMARY KEY (id)
)
;


INSERT INTO excursiones (descripcion, origen, destino, fecha_excursion, duracion, estado, destacado, aforoMaximo, minimoAsistentes, precioUnitario, imagen, fecha_alta) VALUES
('Excursión a la playa', 'Madrid', 'Playa de la Malvarrosa', '2024-07-15', 8, 'creado', 'S', 50, 10, 35.50, 'playa_malvarrosa.jpg', '2024-01-10'),
('Ruta de senderismo', 'Barcelona', 'Montserrat', '2024-05-20', 6, 'terminado', 'N', 30, 5, 25.00, 'montserrat.jpg', '2024-01-15'),
('Visita a la ciudad', 'Sevilla', 'Córdoba', '2024-06-10', 10, 'cancelado', 'S', 40, 8, 50.00, 'cordoba.jpg', '2024-01-20'),
('Excursión a la nieve', 'Madrid', 'Sierra de Guadarrama', '2024-12-15', 12, 'creado', 'N', 20, 2, 75.00, 'sierra_guadarrama.jpg', '2024-02-01'),
('Tour gastronómico', 'Valencia', 'Valencia', '2024-09-10', 5, 'terminado', 'S', 25, 5, 40.00, 'tour_gastronomico.jpg', '2024-03-05');