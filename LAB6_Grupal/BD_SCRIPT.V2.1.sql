-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`color` (
  `ID_COLOR` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_COLOR`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`condicion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`condicion` (
  `ID_CONDICION` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_CONDICION`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estado_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estado_item` (
  `ID_ESTADOITEM` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_ESTADOITEM`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`facultad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`facultad` (
  `ID_FACULTAD` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_FACULTAD`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`formato`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`formato` (
  `ID_FORMATO` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_FORMATO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`categoria` (
  `ID_CATEGORIA` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_CATEGORIA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`subcategoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`subcategoria` (
  `ID_SUBCATEGORIA` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  `CATEGORIA_ID` INT NOT NULL,
  PRIMARY KEY (`ID_SUBCATEGORIA`),
  INDEX `fk_SUBCATEGORIA_CATEGORIA1_idx` (`CATEGORIA_ID` ASC) VISIBLE,
  CONSTRAINT `fk_SUBCATEGORIA_CATEGORIA1`
    FOREIGN KEY (`CATEGORIA_ID`)
    REFERENCES `mydb`.`categoria` (`ID_CATEGORIA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`tamano`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`tamano` (
  `ID_TAMANO` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_TAMANO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`item` (
  `ID_ITEM` INT NOT NULL AUTO_INCREMENT,
  `PRECIO` DECIMAL(10,2) NOT NULL,
  `NOMBRE` VARCHAR(100) NOT NULL,
  `DESCRIPCION` VARCHAR(300) NOT NULL,
  `ES_VENTA` TINYINT(3) UNSIGNED ZEROFILL NOT NULL,
  `SUBCATEGORIA_ID` INT NOT NULL,
  `COLOR_ID` INT NOT NULL,
  `FACULTAD_ID` INT NOT NULL,
  `ESTADO_ITEM_ID` INT NOT NULL,
  `CONDICION_ID` INT NOT NULL,
  `TAMANO_ID` INT NOT NULL,
  `FORMATO_ID` INT NOT NULL,
  PRIMARY KEY (`ID_ITEM`),
  INDEX `fk_ITEM_SUBCATEGORIA_idx` (`SUBCATEGORIA_ID` ASC) VISIBLE,
  INDEX `fk_ITEM_COLOR1_idx` (`COLOR_ID` ASC) VISIBLE,
  INDEX `fk_ITEM_FACULTAD1_idx` (`FACULTAD_ID` ASC) VISIBLE,
  INDEX `fk_ITEM_ESTADO_ITEM1_idx` (`ESTADO_ITEM_ID` ASC) VISIBLE,
  INDEX `fk_ITEM_CONDICION1_idx` (`CONDICION_ID` ASC) VISIBLE,
  INDEX `fk_ITEM_TAMANO1_idx` (`TAMANO_ID` ASC) VISIBLE,
  INDEX `fk_ITEM_FORMATO1_idx` (`FORMATO_ID` ASC) VISIBLE,
  CONSTRAINT `fk_ITEM_COLOR1`
    FOREIGN KEY (`COLOR_ID`)
    REFERENCES `mydb`.`color` (`ID_COLOR`),
  CONSTRAINT `fk_ITEM_CONDICION1`
    FOREIGN KEY (`CONDICION_ID`)
    REFERENCES `mydb`.`condicion` (`ID_CONDICION`),
  CONSTRAINT `fk_ITEM_ESTADO_ITEM1`
    FOREIGN KEY (`ESTADO_ITEM_ID`)
    REFERENCES `mydb`.`estado_item` (`ID_ESTADOITEM`),
  CONSTRAINT `fk_ITEM_FACULTAD1`
    FOREIGN KEY (`FACULTAD_ID`)
    REFERENCES `mydb`.`facultad` (`ID_FACULTAD`),
  CONSTRAINT `fk_ITEM_FORMATO1`
    FOREIGN KEY (`FORMATO_ID`)
    REFERENCES `mydb`.`formato` (`ID_FORMATO`),
  CONSTRAINT `fk_ITEM_SUBCATEGORIA`
    FOREIGN KEY (`SUBCATEGORIA_ID`)
    REFERENCES `mydb`.`subcategoria` (`ID_SUBCATEGORIA`),
  CONSTRAINT `fk_ITEM_TAMANO1`
    FOREIGN KEY (`TAMANO_ID`)
    REFERENCES `mydb`.`tamano` (`ID_TAMANO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`persona` (
  `ID_PERSONA` INT NOT NULL AUTO_INCREMENT,
  `NOMBRES` VARCHAR(100) NOT NULL,
  `PRIMER_APELLIDO` VARCHAR(100) NOT NULL,
  `SEGUNDO_APELLIDO` VARCHAR(100) NOT NULL,
  `CODIGO` VARCHAR(8) NOT NULL,
  `CORREO` VARCHAR(100) NOT NULL,
  `CONTRASEÑA` VARCHAR(20) NOT NULL,
  `ES_ADMIN` TINYINT NOT NULL,
  `ESTA_ACTIVO` TINYINT NOT NULL,
  PRIMARY KEY (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`alquiler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`alquiler` (
  `PERSONA_ID` INT NOT NULL,
  `ITEM_ID` INT NOT NULL,
  `FECHA_INICIO` DATETIME NOT NULL,
  `FECHA_FIN` DATETIME NULL DEFAULT NULL,
  `MONTO` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`PERSONA_ID`, `ITEM_ID`),
  INDEX `fk_PERSONA_has_ITEM_ITEM1_idx` (`ITEM_ID` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_ITEM_PERSONA1_idx` (`PERSONA_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PERSONA_has_ITEM_ITEM1`
    FOREIGN KEY (`ITEM_ID`)
    REFERENCES `mydb`.`item` (`ID_ITEM`),
  CONSTRAINT `fk_PERSONA_has_ITEM_PERSONA1`
    FOREIGN KEY (`PERSONA_ID`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`calificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`calificacion` (
  `CALIFICACION_ID` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CALIFICACION_ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`chat` (
  `ID_CHAT` INT NOT NULL AUTO_INCREMENT,
  `FECHA_CREACION` DATETIME NOT NULL,
  PRIMARY KEY (`ID_CHAT`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`comprobante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`comprobante` (
  `ID_COMPROBANTE` INT NOT NULL AUTO_INCREMENT,
  `MONTO` DECIMAL(10,2) NOT NULL,
  `TRANSACCION_ID` VARCHAR(45) NOT NULL,
  `FECHA_EMISION` DATETIME NOT NULL,
  `PERSONA_ID_PERSONA` INT NOT NULL,
  `ITEM_ID_ITEM` INT NOT NULL,
  `ALQUILER_PERSONA_ID` INT NULL DEFAULT NULL,
  `ALQUILER_ITEM_ID_ITEM` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ID_COMPROBANTE`),
  INDEX `fk_COMPROBANTE_PERSONA1_idx` (`PERSONA_ID_PERSONA` ASC) VISIBLE,
  INDEX `fk_COMPROBANTE_ITEM1_idx` (`ITEM_ID_ITEM` ASC) VISIBLE,
  INDEX `fk_COMPROBANTE_ALQUILER1_idx` (`ALQUILER_PERSONA_ID` ASC, `ALQUILER_ITEM_ID_ITEM` ASC) VISIBLE,
  CONSTRAINT `fk_COMPROBANTE_ALQUILER1`
    FOREIGN KEY (`ALQUILER_PERSONA_ID` , `ALQUILER_ITEM_ID_ITEM`)
    REFERENCES `mydb`.`alquiler` (`PERSONA_ID` , `ITEM_ID`),
  CONSTRAINT `fk_COMPROBANTE_ITEM1`
    FOREIGN KEY (`ITEM_ID_ITEM`)
    REFERENCES `mydb`.`item` (`ID_ITEM`),
  CONSTRAINT `fk_COMPROBANTE_PERSONA1`
    FOREIGN KEY (`PERSONA_ID_PERSONA`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estado_chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estado_chat` (
  `ID_ESTADOCHAT` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_ESTADOCHAT`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estado_msj`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estado_msj` (
  `ID_ESTADOMSJ` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_ESTADOMSJ`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estado_persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estado_persona` (
  `ID_ESTADOPERSONA` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_ESTADOPERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`estado_publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`estado_publicacion` (
  `ID_ESTADOPUBLI` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ID_ESTADOPUBLI`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`forma_pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`forma_pago` (
  `ID_FORMAPAGO` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_FORMAPAGO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`publicacion` (
  `ID_PUBLICACION` INT NOT NULL AUTO_INCREMENT,
  `FECHA_ALTA` DATETIME NOT NULL,
  `FECHA_BAJA` DATETIME NULL DEFAULT NULL,
  `ESTADO_PUBLICACION_ID` INT NOT NULL,
  `ITEM_ID_ITEM` INT NOT NULL,
  `PERSONA_ID` INT NOT NULL,
  `CALIFICACION_ID` INT NOT NULL,
  PRIMARY KEY (`ID_PUBLICACION`),
  INDEX `fk_PUBLICACION_ESTADO_PUBLICACION1_idx` (`ESTADO_PUBLICACION_ID` ASC) VISIBLE,
  INDEX `fk_PUBLICACION_ITEM1_idx` (`ITEM_ID_ITEM` ASC) VISIBLE,
  INDEX `fk_PUBLICACION_PERSONA1_idx` (`PERSONA_ID` ASC) VISIBLE,
  INDEX `fk_PUBLICACION_CALIFICACION1_idx` (`CALIFICACION_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PUBLICACION_CALIFICACION1`
    FOREIGN KEY (`CALIFICACION_ID`)
    REFERENCES `mydb`.`calificacion` (`CALIFICACION_ID`),
  CONSTRAINT `fk_PUBLICACION_ESTADO_PUBLICACION1`
    FOREIGN KEY (`ESTADO_PUBLICACION_ID`)
    REFERENCES `mydb`.`estado_publicacion` (`ID_ESTADOPUBLI`),
  CONSTRAINT `fk_PUBLICACION_ITEM1`
    FOREIGN KEY (`ITEM_ID_ITEM`)
    REFERENCES `mydb`.`item` (`ID_ITEM`),
  CONSTRAINT `fk_PUBLICACION_PERSONA1`
    FOREIGN KEY (`PERSONA_ID`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`historia_publicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`historia_publicacion` (
  `ID_HISTORIAPUBLI` INT NOT NULL AUTO_INCREMENT,
  `FECHA_MOD` DATETIME NOT NULL,
  `PUBLICACION_ID` INT NOT NULL,
  PRIMARY KEY (`ID_HISTORIAPUBLI`),
  INDEX `fk_HISTORIA_PUBLI_PUBLICACION1_idx` (`PUBLICACION_ID` ASC) VISIBLE,
  CONSTRAINT `fk_HISTORIA_PUBLI_PUBLICACION1`
    FOREIGN KEY (`PUBLICACION_ID`)
    REFERENCES `mydb`.`publicacion` (`ID_PUBLICACION`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`notificacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`notificacion` (
  `NOTIFICACION_ID` INT NOT NULL AUTO_INCREMENT,
  `FECHA` DATETIME NOT NULL,
  `MENSAJE` VARCHAR(255) NOT NULL,
  `PERSONA_ID` INT NOT NULL,
  PRIMARY KEY (`NOTIFICACION_ID`),
  INDEX `fk_NOTIFIFACION_PERSONA1_idx` (`PERSONA_ID` ASC) VISIBLE,
  CONSTRAINT `fk_NOTIFIFACION_PERSONA1`
    FOREIGN KEY (`PERSONA_ID`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`incidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`incidencia` (
  `INCIDENCIA_ID` INT NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` VARCHAR(45) NOT NULL,
  `FECHA_CREACION` DATETIME NOT NULL,
  `FECHA_SOLUCION` DATETIME NULL DEFAULT NULL,
  `NOTIFIFACION_ID` INT NOT NULL,
  `PERSONA_ID` INT NOT NULL,
  PRIMARY KEY (`INCIDENCIA_ID`),
  INDEX `fk_INCIDENCIA_NOTIFIFACION1_idx` (`NOTIFIFACION_ID` ASC) VISIBLE,
  INDEX `fk_INCIDENCIA_PERSONA1_idx` (`PERSONA_ID` ASC) VISIBLE,
  CONSTRAINT `fk_INCIDENCIA_NOTIFIFACION1`
    FOREIGN KEY (`NOTIFIFACION_ID`)
    REFERENCES `mydb`.`notificacion` (`NOTIFICACION_ID`),
  CONSTRAINT `fk_INCIDENCIA_PERSONA1`
    FOREIGN KEY (`PERSONA_ID`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`mensaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mensaje` (
  `ID_MENSAJE` INT NOT NULL AUTO_INCREMENT,
  `CHAT_ID_CHAT` INT NOT NULL,
  `FECHA_ENVIO` DATETIME NOT NULL,
  `FECHA_LEIDO` DATETIME NULL DEFAULT NULL,
  `MENSAJE` TEXT NOT NULL,
  `ESTADO_MSJ_ID` INT NOT NULL,
  `PERSONA_ID` INT NOT NULL,
  PRIMARY KEY (`ID_MENSAJE`, `CHAT_ID_CHAT`),
  INDEX `fk_MENSAJE_CHAT1_idx` (`CHAT_ID_CHAT` ASC) VISIBLE,
  INDEX `fk_MENSAJE_PERSONA1_idx` (`PERSONA_ID` ASC) VISIBLE,
  INDEX `fk_MENSAJE_ESTADO_MSJ1_idx` (`ESTADO_MSJ_ID` ASC) VISIBLE,
  CONSTRAINT `fk_MENSAJE_CHAT1`
    FOREIGN KEY (`CHAT_ID_CHAT`)
    REFERENCES `mydb`.`chat` (`ID_CHAT`),
  CONSTRAINT `fk_MENSAJE_ESTADO_MSJ1`
    FOREIGN KEY (`ESTADO_MSJ_ID`)
    REFERENCES `mydb`.`estado_msj` (`ID_ESTADOMSJ`),
  CONSTRAINT `fk_MENSAJE_PERSONA1`
    FOREIGN KEY (`PERSONA_ID`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`moneda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`moneda` (
  `ID_MONEDA` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_MONEDA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`motivo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`motivo` (
  `ID_MOTIVO` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`ID_MOTIVO`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`persona_chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`persona_chat` (
  `PERSONA_ID` INT NOT NULL,
  `CHAT_ID` INT NOT NULL,
  `ULTIMA_ACTIVIDAD` DATETIME NOT NULL,
  PRIMARY KEY (`PERSONA_ID`, `CHAT_ID`),
  INDEX `fk_PERSONA_has_CHAT_CHAT1_idx` (`CHAT_ID` ASC) VISIBLE,
  INDEX `fk_PERSONA_has_CHAT_PERSONA1_idx` (`PERSONA_ID` ASC) VISIBLE,
  CONSTRAINT `fk_PERSONA_has_CHAT_CHAT1`
    FOREIGN KEY (`CHAT_ID`)
    REFERENCES `mydb`.`chat` (`ID_CHAT`),
  CONSTRAINT `fk_PERSONA_has_CHAT_PERSONA1`
    FOREIGN KEY (`PERSONA_ID`)
    REFERENCES `mydb`.`persona` (`ID_PERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`rol_persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`rol_persona` (
  `ID_ROLPERSONA` INT NOT NULL AUTO_INCREMENT,
  `NOMBRE` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_ROLPERSONA`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
