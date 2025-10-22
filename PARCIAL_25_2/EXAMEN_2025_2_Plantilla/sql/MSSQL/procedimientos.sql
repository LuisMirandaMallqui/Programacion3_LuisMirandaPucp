USE transitsoft
GO

IF OBJECT_ID('insertarInfraccion', 'P') IS NOT NULL
BEGIN
    DROP PROCEDURE insertarInfraccion;
END
GO

CREATE PROCEDURE insertarInfraccion(
    @p_placa CHAR(7),
    @p_velocidad FLOAT,
    @p_limite FLOAT,
    @p_exceso FLOAT,
    @p_vehiculo_marca VARCHAR(20),
    @p_vehiculo_modelo VARCHAR(20),
    @p_vehiculo_anho INT,
    @p_propietario_dni CHAR(8),
    @p_propietario_nombres VARCHAR(50),
    @p_propietario_apellidos VARCHAR(50),
    @p_propietario_direccion VARCHAR(100),
    @p_camara_modelo VARCHAR(20),
    @p_camara_codigo_serie VARCHAR(20),
    @p_camara_latitud INT,
    @p_camara_longitud INT,
    @p_monto DECIMAL(10,2),
    @p_fecha_captura DATETIME,
    @p_fecha_registro DATETIME,
    @p_id INT OUTPUT
)
AS
BEGIN
    INSERT INTO infraccion (
        placa,
        velocidad,
        limite,
        exceso,
        vehiculo_marca,
        vehiculo_modelo,
        vehiculo_anho,
        propietario_dni,
        propietario_nombres,
        propietario_apellidos,
        propietario_direccion,
        camara_modelo,
        camara_codigo_serie,
        camara_latitud,
        camara_longitud,
        monto,
        fecha_captura,
        fecha_registro
    )
    VALUES (
        @p_placa,
        @p_velocidad,
        @p_limite,
        @p_exceso,
        @p_vehiculo_marca,
        @p_vehiculo_modelo,
        @p_vehiculo_anho,
        @p_propietario_dni,
        @p_propietario_nombres,
        @p_propietario_apellidos,
        @p_propietario_direccion,
        @p_camara_modelo,
        @p_camara_codigo_serie,
        @p_camara_latitud,
        @p_camara_longitud,
        @p_monto,
        @p_fecha_captura,
        @p_fecha_registro
    );
    
    SET @p_id = SCOPE_IDENTITY();
END;
GO

IF OBJECT_ID('listarInfracciones', 'P') IS NOT NULL
BEGIN
    DROP PROCEDURE listarInfracciones;
END
GO

CREATE PROCEDURE listarInfracciones
AS
BEGIN 
    SELECT * FROM infraccion;
END;
GO