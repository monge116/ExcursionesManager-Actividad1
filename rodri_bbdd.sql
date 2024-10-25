create database rodri_bbdd;
use rodri_bbdd;
CREATE TABLE excursiones (
	id INT NOT NULL AUTO_INCREMENT primary key,
	descripcion varchar(99999),
	origen varchar(255),
    destino varchar(255),
    fecha_excursion date,
    duracion int,
    check(estado in('cancelado', 'terminado', 'creado')),
    check (destacado in("S", "N")),
    aforoMaximo int,
    minimoAsistentes int,
    precioUnitario double,
    imagen varchar(255),
    fechaAlta Date,
    PRIMARY KEY (id)
)
;