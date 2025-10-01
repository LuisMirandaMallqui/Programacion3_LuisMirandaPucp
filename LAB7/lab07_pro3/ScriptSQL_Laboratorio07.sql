DROP SCHEMA IF EXISTS lab08_2025_1;
CREATE SCHEMA lab08_2025_1;
USE lab08_2025_1;
-- Creación de Tablas
CREATE TABLE videojuego_NN(
	id_videojuego INT AUTO_INCREMENT,
    nombre_videojuego VARCHAR(100),
    fecha_lanzamiento DATE,
    precio_aprox_mercado DECIMAL(10,2),
    num_jugadores_mc INT,
    id_genero INT,
    descripcion_genero VARCHAR(100),
    id_categoria CHAR,
    descripcion_categoria VARCHAR(100),
    PRIMARY KEY(id_videojuego)
)ENGINE=InnoDB;
CREATE TABLE genero(
	id_genero INT AUTO_INCREMENT,
    descripcion_genero VARCHAR(100),
    PRIMARY KEY(id_genero)
)ENGINE=InnoDB;
CREATE TABLE categoria(
	id_categoria CHAR,
    descripcion_categoria VARCHAR(100),
    PRIMARY KEY(id_categoria)
)ENGINE=InnoDB;
CREATE TABLE videojuego(
	id_videojuego INT AUTO_INCREMENT,
    fid_genero INT,
    fid_categoria CHAR,
    nombre_videojuego VARCHAR(100),
    fecha_lanzamiento DATE,
    precio_aprox_mercado DECIMAL(10,2),
    num_jugadores_mc INT,
    PRIMARY KEY(id_videojuego),
    FOREIGN KEY(fid_genero) REFERENCES genero(id_genero),
    FOREIGN KEY(fid_categoria) REFERENCES categoria(id_categoria)
)ENGINE=InnoDB;
-- Registros
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES("DOOM: THE DARK AGES","2025-05-13",250.00,4,1,"AVENTURA",'M',"MATURE");
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES("DEATH STRANDING 2","2025-06-24",300.00,1,1,"AVENTURA",'T',"TEEN");
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES("MARIO KART WORLD","2025-06-05",269.99,4,2,"CARRERAS",'E',"EVERYONE");
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES("FBC: FIREBREAK","2025-07-17",150.50,2,3,"SHOOTER",'E',"EVERYONE");
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES("GHOST OF YOTEI","2025-10-02",288.50,1,1,"AVENTURA",'T',"TEEN");
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES("GRAN TURISMO 7","2025-06-01",330.99,4,2,"CARRERAS",'M',"MATURE");
INSERT INTO videojuego_nn(nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc,id_genero,descripcion_genero,id_categoria,descripcion_categoria) VALUES
("BORDERLANDS 4","2025-09-12",371.99,2,3,"SHOOTER",'M',"MATURE"),
("METROID PRIME 4: BEYOND","2025-12-20",400.00,1,1,"AVENTURA",'E',"EVERYONE"),
("CIVILIZATION VII","2026-02-11",240.50,2,4,"ESTRATEGIA",'E',"EVERYONE"),
("METAL GEAR SOLID: SNAKE EATER","2025-08-28",322.19,2,3,"SHOOTER",'A',"ADULTS"),
("LEYENDAS POKEMON Z-A","2025-12-25",380.00,1,5,"ROL",'E',"EVERYONE"),
("FIFA 2026","2025-10-19",350.99,2,6,"DEPORTES",'E',"EVERYONE"),
("FINAL FANTASY XIV","2025-11-27",399.99,1,5,"ROL",'T',"TEEN"),
("LITTLE NIGHTMARES 3","2025-10-30",274.90,1,1,"AVENTURA",'T',"TEEN"),
("CRIMSON DESERT","2025-10-03",320.00,1,7,"MUNDO ABIERTO",'E',"EVERYONE"),
("THE FIRST BERSERKER: KHAZAN","2025-09-17",221.90,1,5,"ROL",'E',"EVERYONE"),
("DIRECTIVE 8020","2025-10-02",340.50,1,8,"TERROR",'A',"ADULTS"),
("DYING LIGHT: THE BEAST","2025-12-06",288.50,1,8,"TERROR",'M',"MATURE"),
("ELDEN RING NIGHTREIGN","2025-05-30",253.10,1,5,"ROL",'T',"TEEN"),
("AVOWED","2025-05-08",170.99,1,1,"AVENTURA",'E',"EVERYONE");
-- Procedimientos Almacenados
DROP PROCEDURE IF EXISTS LISTAR_VIDEOJUEGOS;
DROP PROCEDURE IF EXISTS LISTAR_GENEROS;
DROP PROCEDURE IF EXISTS LISTAR_CATEGORIAS;
DROP PROCEDURE IF EXISTS INSERTAR_GENERO;
DROP PROCEDURE IF EXISTS INSERTAR_CATEGORIA;
DROP PROCEDURE IF EXISTS INSERTAR_VIDEOJUEGO;
DELIMITER $
CREATE PROCEDURE LISTAR_VIDEOJUEGOS()
BEGIN
	SELECT id_videojuego, nombre_videojuego, fecha_lanzamiento, precio_aprox_mercado, num_jugadores_mc, id_genero, id_categoria FROM videojuego_NN;
END$
CREATE PROCEDURE LISTAR_GENEROS()
BEGIN
	SELECT id_genero, descripcion_genero FROM videojuego_NN;
END$
CREATE PROCEDURE LISTAR_CATEGORIAS()
BEGIN
	SELECT id_categoria, descripcion_categoria FROM videojuego_NN;
END$
CREATE PROCEDURE INSERTAR_GENERO(
	IN _id_genero INT,
    IN _descripcion_genero VARCHAR(100)
)
BEGIN
	INSERT INTO genero(id_genero,descripcion_genero) VALUES(_id_genero,_descripcion_genero);
END$
CREATE PROCEDURE INSERTAR_CATEGORIA(
	IN _id_categoria CHAR,
    IN _descripcion_categoria VARCHAR(100)
)
BEGIN
	INSERT INTO categoria(id_categoria,descripcion_categoria) VALUES(_id_categoria,_descripcion_categoria);
END$
CREATE PROCEDURE INSERTAR_videojuego(
	IN _id_videojuego INT,
    IN _fid_genero INT,
    IN _fid_categoria CHAR,
    IN _nombre_videojuego VARCHAR(100),
    IN _fecha_lanzamiento DATE,
    IN _precio_aprox_mercado DECIMAL(10,2),
    IN _num_jugadores_mc INT
)BEGIN
	INSERT INTO videojuego(id_videojuego,fid_genero,fid_categoria,nombre_videojuego,fecha_lanzamiento,precio_aprox_mercado,num_jugadores_mc) VALUES(_id_videojuego,_fid_genero,_fid_categoria,_nombre_videojuego,_fecha_lanzamiento,_precio_aprox_mercado,_num_jugadores_mc);
END$