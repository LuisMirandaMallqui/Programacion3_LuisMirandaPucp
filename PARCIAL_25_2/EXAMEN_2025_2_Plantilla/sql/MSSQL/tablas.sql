USE transitsoft;
GO

-- Vehiculo
IF OBJECT_ID('vehiculo', 'U') IS NULL
CREATE TABLE vehiculo(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    placa CHAR(7) NOT NULL UNIQUE,
    marca VARCHAR(20) NOT NULL,
    modelo VARCHAR(20) NOT NULL,
    anho INT NOT NULL
);
GO

-- Propietario
IF OBJECT_ID('propietario', 'U') IS NULL
CREATE TABLE propietario(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    nombres VARCHAR(50) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    direccion VARCHAR(100) NOT NULL
);
GO

-- Vehiculo_Propietario
IF OBJECT_ID('vehiculo_propietario', 'U') IS NULL
CREATE TABLE vehiculo_propietario(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    id_vehiculo INT NOT NULL,
    id_propietario INT NOT NULL,
    CONSTRAINT fk_vehiculo_propietario_vehiculo 
        FOREIGN KEY (id_vehiculo) REFERENCES vehiculo(id),
    CONSTRAINT fk_vehiculo_propietario_propietario 
        FOREIGN KEY (id_propietario) REFERENCES propietario(id)
);
GO

-- Camara
IF OBJECT_ID('camara', 'U') IS NULL
CREATE TABLE camara(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    modelo VARCHAR(20) NOT NULL,
    codigo_serie VARCHAR(20) NOT NULL UNIQUE,
    latitud INT NOT NULL,
    longitud INT NOT NULL
);
GO

-- Captura
IF OBJECT_ID('captura', 'U') IS NULL
CREATE TABLE captura(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    id_camara INT NOT NULL,
    placa CHAR(7) NOT NULL,
    velocidad FLOAT NOT NULL,
    fecha_captura DATETIME NOT NULL,
    estado VARCHAR(15) NOT NULL,
    CONSTRAINT fk_captura_camara FOREIGN KEY (id_camara) REFERENCES camara(id)
);
GO

-- Infraccion (desnormalizada)
IF OBJECT_ID('infraccion', 'U') IS NULL
CREATE TABLE infraccion(
    id INT IDENTITY(1,1) PRIMARY KEY NOT NULL,
    
    -- datos de la captura
    placa CHAR(7) NOT NULL,
    velocidad FLOAT NOT NULL,
    limite FLOAT NOT NULL,
    exceso FLOAT NOT NULL,
    
    -- datos del vehiculo
    vehiculo_marca VARCHAR(20),
    vehiculo_modelo VARCHAR(20),
    vehiculo_anho INT,
    
    -- datos del propietario
    propietario_dni CHAR(8),
    propietario_nombres VARCHAR(50),
    propietario_apellidos VARCHAR(50),
    propietario_direccion VARCHAR(100),
    
    -- datos de la camara
    camara_modelo VARCHAR(20),
    camara_codigo_serie VARCHAR(20),
    camara_latitud INT,
    camara_longitud INT,
    
    -- datos de la infracción
    monto DECIMAL(10,2) NOT NULL,
    fecha_captura DATETIME NOT NULL,
    fecha_registro DATETIME NOT NULL DEFAULT GETDATE()
);
GO

-- Índices (se crean aparte en T-SQL)
CREATE INDEX idx_infraccion_placa ON infraccion(placa);
CREATE INDEX idx_infraccion_propietario_dni ON infraccion(propietario_dni);
CREATE INDEX idx_infraccion_fecha ON infraccion(fecha_registro);
GO
