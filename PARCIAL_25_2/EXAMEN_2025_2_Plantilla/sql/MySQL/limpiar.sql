use transitsoft;

SET SQL_SAFE_UPDATES = 0;

DELETE FROM infraccion;
DELETE FROM captura;
DELETE FROM vehiculo_propietario;
DELETE FROM vehiculo;
DELETE FROM propietario;
DELETE FROM camara;

-- Reiniciar auto_increment
ALTER TABLE infraccion AUTO_INCREMENT = 1;
ALTER TABLE captura AUTO_INCREMENT = 1;
ALTER TABLE vehiculo_propietario AUTO_INCREMENT = 1;
ALTER TABLE vehiculo AUTO_INCREMENT = 1;
ALTER TABLE propietario AUTO_INCREMENT = 1;
ALTER TABLE camara AUTO_INCREMENT = 1;
