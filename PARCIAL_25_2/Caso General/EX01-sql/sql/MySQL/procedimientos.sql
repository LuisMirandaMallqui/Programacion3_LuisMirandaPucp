use transitsoft;

DROP PROCEDURE IF EXISTS listarCapturas;
DROP PROCEDURE IF EXISTS modificarEstadoCaptura;

DELIMITER //

CREATE PROCEDURE listarCapturas()
BEGIN
    SELECT
        c.id,
        c.placa,
        c.velocidad,
        c.fecha_captura,
        c.estado,
        c.id_camara,
        ca.modelo AS camara_modelo, 
        ca.codigo_serie AS camara_codigo_serie, 
        ca.latitud AS camara_latitud, 
        ca.longitud AS camara_longitud, 
        p.id as id_propietario, 
        p.dni AS propietario_dni, 
        p.nombres AS propietario_nombres, 
        p.apellidos AS propietario_apellidos, 
        p.direccion AS propietario_direccion,
        v.id AS id_vehiculo,
        v.placa AS vehiculo_placa, 
        v.marca AS vehiculo_marca, 
        v.modelo AS vehiculo_modelo, 
        v.anho AS vehiculo_anho 
    FROM captura AS c
    INNER JOIN camara as ca 
		ON c.id_camara = ca.id
    INNER JOIN vehiculo AS v
        ON v.placa = c.placa
    INNER JOIN vehiculo_propietario AS vp
        ON vp.id_vehiculo = v.id
	INNER JOIN propietario AS p
		ON vp.id_propietario = p.id;
END //

CREATE PROCEDURE modificarEstadoCaptura(IN p_estado VARCHAR(15), IN p_id INT)
BEGIN
	UPDATE captura 
    SET estado = 'PROCESADO'
    WHERE id = p_id;
END //