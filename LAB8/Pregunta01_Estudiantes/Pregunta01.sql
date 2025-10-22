DROP PROCEDURE IF EXISTS LISTAR_PROPIETARIOS_SIN_VEHICULO;
DROP PROCEDURE IF EXISTS LISTAR_VEHICULOS_SIN_PROPIETARIO;
DROP PROCEDURE IF EXISTS REGISTRAR_VEHICULO_PROPIETARIO;
DROP PROCEDURE IF EXISTS LISTAR_PROPIETARIOS_CON_VEHICULO;
DROP PROCEDURE IF EXISTS LISTAR_VEHICULOS_X_ID_PROPIETARIO;
DELIMITER $
CREATE PROCEDURE LISTAR_PROPIETARIOS_SIN_VEHICULO()
BEGIN
    SELECT p.id, p.dni, p.nombres, p.apellidos, p.direccion
    FROM propietario p
    WHERE NOT EXISTS (
        SELECT 1 
        FROM vehiculo_propietario vp
        WHERE vp.id_propietario = p.id
    )
    ORDER BY p.apellidos, p.nombres;
END$
DELIMITER $
CREATE PROCEDURE LISTAR_VEHICULOS_SIN_PROPIETARIO()
BEGIN
    SELECT v.id, v.placa, v.marca, v.modelo, v.anho
    FROM vehiculo v
    WHERE NOT EXISTS (
        SELECT 1
        FROM vehiculo_propietario vp
        WHERE vp.id_vehiculo = v.id
    );
END$
DELIMITER $
CREATE PROCEDURE REGISTRAR_VEHICULO_PROPIETARIO(
	OUT _id INT,
    IN _id_vehiculo INT,
    IN _id_propietario INT
)
BEGIN
    INSERT INTO vehiculo_propietario(id_vehiculo,id_propietario) VALUES(_id_vehiculo,_id_propietario);
    SET _id = @@last_insert_id;
END$
DELIMITER $
CREATE PROCEDURE LISTAR_PROPIETARIOS_CON_VEHICULO()
BEGIN
    SELECT 
        p.id,
        p.dni,
        p.nombres,
        p.apellidos,
        p.direccion
    FROM propietario p
    INNER JOIN vehiculo_propietario vp ON vp.id_propietario = p.id
    INNER JOIN vehiculo v ON v.id = vp.id_vehiculo
    GROUP BY p.id, p.dni, p.nombres, p.apellidos, p.direccion
    ORDER BY p.apellidos, p.nombres;
END$
DELIMITER $
CREATE PROCEDURE LISTAR_VEHICULOS_X_ID_PROPIETARIO(
    IN p_id_propietario INT
)
BEGIN
    SELECT 
        v.id,
        v.placa,
        v.marca,
        v.modelo,
        v.anho
    FROM vehiculo v
    INNER JOIN vehiculo_propietario vp ON v.id = vp.id_vehiculo
    INNER JOIN propietario p           ON p.id = vp.id_propietario
    WHERE p.id = p_id_propietario
    ORDER BY v.marca, v.modelo, v.anho;
END$
DELIMITER ;

INSERT INTO propietario(dni, nombres, apellidos, direccion) VALUES
('45678902', 'Carlos', 'Ramírez Soto', 'Av. Arequipa 1234, Lince, Lima'),
('48751234', 'María Fernanda', 'López Paredes', 'Jr. Las Magnolias 876, San Borja, Lima'),
('51239876', 'Jorge Luis', 'Castro Quispe', 'Av. La Marina 540, Pueblo Libre, Lima'),
('49871234', 'Ana Paula', 'Gutiérrez León', 'Calle Las Palmeras 120, Miraflores, Lima'),
('50123487', 'Luis Alberto', 'Mendoza Vargas', 'Mz. B Lt. 8 Urb. Los Jardines, Trujillo'),
('47895612', 'Verónica', 'Salazar Huamán', 'Av. Grau 932, Piura'),
('53214789', 'Ricardo', 'Pérez Rojas', 'Calle San Martín 214, Cusco'),
('49637854', 'Gabriela', 'Campos Torres', 'Jr. Ayacucho 780, Arequipa'),
('47651289', 'Daniel', 'Morales Córdova', 'Av. Universitaria 234, San Miguel, Lima'),
('48235697', 'Patricia', 'Núñez Poma', 'Calle Los Álamos 412, Chiclayo'),
('46891237', 'José Antonio', 'Huamán Chávez', 'Av. Próceres 1102, San Juan de Lurigancho, Lima'),
('47562314', 'Carla', 'Villanueva Ramos', 'Jr. Junín 213, Huancayo'),
('45874123', 'Rodrigo', 'Sánchez Flores', 'Av. América Norte 421, Trujillo'),
('46239875', 'Lucía', 'Reyes Delgado', 'Calle Los Olivos 152, San Isidro, Lima'),
('45981234', 'Miguel Ángel', 'Paredes Ruiz', 'Av. Circunvalación 645, Iquitos'),
('46327894', 'Natalia', 'Ortiz Silva', 'Calle Los Geranios 300, Barranco, Lima'),
('46678231', 'Eduardo', 'Cáceres Valdivia', 'Av. Los Incas 854, Cusco'),
('47289316', 'Valeria', 'Peña Castillo', 'Calle Los Jazmines 45, San Borja, Lima'),
('48623175', 'Andrés', 'Quispe Huerta', 'Av. Central 239, Juliaca'),
('49983214', 'Fiorella', 'Durand Espinoza', 'Calle Bolívar 721, Chimbote');

INSERT INTO vehiculo(placa, marca, modelo, anho) VALUES
('YZA-678', 'Subaru', 'Impreza', 2020),
('ZBC-789', 'Peugeot', '208', 2021),
('ACD-890', 'Citroën', 'C4 Cactus', 2019),
('BDE-901', 'Jeep', 'Compass', 2022),
('CEF-012', 'Chery', 'Tiggo 7', 2023),
('DFG-123', 'Chevrolet', 'Tracker', 2020),
('EGH-234', 'Nissan', 'Kicks', 2022),
('FHI-345', 'Ford', 'EcoSport', 2018),
('GIJ-456', 'Toyota', 'RAV4', 2021),
('HJK-567', 'Hyundai', 'Santa Fe', 2022),
('IKL-678', 'Mazda', 'CX-30', 2023),
('JLM-789', 'Renault', 'Koleos', 2020),
('KMN-890', 'Volkswagen', 'Tiguan', 2021),
('LNO-901', 'Mitsubishi', 'L200', 2019),
('MOP-012', 'Kia', 'Seltos', 2022),
('NPQ-123', 'Suzuki', 'Vitara', 2021),
('OQR-234', 'Honda', 'CR-V', 2020),
('PRS-345', 'Mercedes-Benz', 'GLA 200', 2023),
('QST-456', 'BMW', 'X1', 2022),
('RTU-567', 'Audi', 'A4', 2021);