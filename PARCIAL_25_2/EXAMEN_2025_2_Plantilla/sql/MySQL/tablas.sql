use transitsoft;

create table if not exists vehiculo(
    id int auto_increment primary key not null,
    placa char(7) not null unique,
    marca varchar(20) not null,
    modelo varchar(20) not null,
    anho int not null
);

create table if not exists propietario(
    id int auto_increment primary key not null,
    dni char(8) not null unique,
    nombres varchar(50) not null,
    apellidos varchar(50) not null,
    direccion varchar(100) not null
);

create table if not exists vehiculo_propietario(
    id int auto_increment primary key not null,
    id_vehiculo int not null,
    id_propietario int not null,
    constraint fk_vehiculo_propietario_vehiculo foreign key (id_vehiculo) references vehiculo(id),
    constraint fk_vehiculo_propietario_propietario foreign key (id_propietario) references propietario(id)
);

create table if not exists camara(
    id int auto_increment primary key not null,
    modelo varchar(20) not null,
    codigo_serie varchar(20) not null unique,
    latitud int not null,
    longitud int not null
);

create table if not exists captura(
    id int auto_increment primary key not null,
    id_camara int not null,
    placa char(7) not null,
    velocidad double not null,
    fecha_captura datetime not null,
    estado varchar(15) not null,
    constraint fk_captura_camara foreign key (id_camara) references camara(id)
);

create table if not exists infraccion(
    id int auto_increment primary key not null,
    
    -- datos de la captura
    placa char(7) not null,
    velocidad double not null,
    limite double not null,
    exceso double not null,
    
    -- datos del vehiculo
    vehiculo_marca varchar(20),
    vehiculo_modelo varchar(20),
    vehiculo_anho int,
    
    -- datos del propietario
    propietario_dni char(8),
    propietario_nombres varchar(50),
    propietario_apellidos varchar(50),
    propietario_direccion varchar(100),
    
    -- datos de la camara
    id_camara int,
    camara_modelo varchar(20),
    camara_codigo_serie varchar(20),
    camara_latitud int,
    camara_longitud int,
    
    -- datos de la infracción
    monto decimal(10,2) not null,
    fecha_captura datetime not null,
    fecha_registro datetime not null,
    
    index idx_infraccion_placa (placa),
    index idx_infraccion_propietario_dni (propietario_dni),
    index idx_infraccion_fecha (fecha_registro)
);
