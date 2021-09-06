-- -----------------------------------------------------
-- Schema MUEBLERIA
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MUEBLERIA` DEFAULT CHARACTER SET utf8 ;
USE `MUEBLERIA` ;

-- -----------------------------------------------------
-- Table `MUEBLERIA`.`USUARIO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`USUARIO` (
  `nombre` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `tipo` INT NOT NULL,
  `estado` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`nombre`));


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`MUEBLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`MUEBLE` (
  `nombre` VARCHAR(45) NOT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`nombre`));


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`TIPO_PIEZA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`TIPO_PIEZA` (
  `nombre` VARCHAR(45) NOT NULL,
  `stock` INT NULL DEFAULT 0,
  PRIMARY KEY (`nombre`))
;


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`PIEZA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`PIEZA` (
  `id_pieza` INT NOT NULL AUTO_INCREMENT,
  `tipo_piezas` VARCHAR(45) NOT NULL,
  `costo` DOUBLE NOT NULL,
  `utilizado` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id_pieza`),
  CONSTRAINT `fk_PIEZA_TIPO_PIEZA`
    FOREIGN KEY (`tipo_piezas`)
    REFERENCES `MUEBLERIA`.`TIPO_PIEZA` (`nombre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`ENSAMBLAR_MUEBLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`ENSAMBLAR_MUEBLE` (
  `codigo_ensamblar_mueble` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(45) NOT NULL,
  `mueble` VARCHAR(45) NOT NULL,
  `costo` DOUBLE NOT NULL,
  `fecha` DATETIME NOT NULL,
  PRIMARY KEY (`codigo_ensamblar_mueble`, `usuario`, `mueble`),
  CONSTRAINT `fk_ENSAMBLAR_MUEBLE_USUARIO`
    FOREIGN KEY (`usuario`)
    REFERENCES `MUEBLERIA`.`USUARIO` (`nombre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ENSAMBLAR_MUEBLE_MUEBLE`
    FOREIGN KEY (`mueble`)
    REFERENCES `MUEBLERIA`.`MUEBLE` (`nombre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`CLIENTE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`CLIENTE` (
  `nit` VARCHAR(13) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(145) NOT NULL,
  `municipio` VARCHAR(45) NULL,
  `departamento` VARCHAR(45) NULL,
  PRIMARY KEY (`nit`))
;


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`FACTURA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`FACTURA` (
  `no_factura` INT NOT NULL AUTO_INCREMENT,
  `nit_cliente` VARCHAR(13) NOT NULL,
  `fecha` DATETIME NOT NULL,
  PRIMARY KEY (`no_factura`),
   CONSTRAINT `fk_FACTURA_CLIENTE`
    FOREIGN KEY (`nit_cliente`)
    REFERENCES `MUEBLERIA`.`CLIENTE` (`nit`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`DETALLE_FACTURA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`DETALLE_FACTURA` (
  `no_factura` INT NOT NULL,
  `mueble` VARCHAR(45) NOT NULL,
  `cantidad` INT NULL DEFAULT 0,
  PRIMARY KEY (`no_factura`, `mueble`),
  CONSTRAINT `fk_DETALLE_FACTURA_FACTURA`
    FOREIGN KEY (`no_factura`)
    REFERENCES `MUEBLERIA`.`FACTURA` (`no_factura`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_DETALLE_FACTURA_MUEBLE`
    FOREIGN KEY (`mueble`)
    REFERENCES `MUEBLERIA`.`MUEBLE` (`nombre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`DEVOLUCION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`DEVOLUCION` (
  `no_devolucion` INT NOT NULL AUTO_INCREMENT,
  `no_factura` INT NOT NULL,
  `fecha` DATETIME NOT NULL,
  PRIMARY KEY (`no_devolucion`),
  CONSTRAINT `fk_DEVOLUCION_FACTURA`
    FOREIGN KEY (`no_factura`)
    REFERENCES `MUEBLERIA`.`FACTURA` (`no_factura`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
;


-- -----------------------------------------------------
-- Table `MUEBLERIA`.`ENSAMBLE_PIEZAS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MUEBLERIA`.`ENSAMBLE_PIEZAS` (
  `mueble_nombre` VARCHAR(45) NOT NULL,
  `tipo_pieza` VARCHAR(45) NOT NULL,
  `cantidad` VARCHAR(45) NULL,
  PRIMARY KEY (`mueble_nombre`, `tipo_pieza`),
  CONSTRAINT `fk_ENSAMBLE_PIEZAS_MUEBLE`
    FOREIGN KEY (`mueble_nombre`)
    REFERENCES `MUEBLERIA`.`MUEBLE` (`nombre`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ENSAMBLE_PIEZAS_TIPO_PIEZA`
    FOREIGN KEY (`tipo_pieza`)
    REFERENCES `MUEBLERIA`.`TIPO_PIEZA` (`nombre`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Usuarios Iniciales
-- -----------------------------------------------------

 INSERT INTO
  `MUEBLERIA`.`USUARIO` (
     `nombre`,
  `password`,
  `tipo`
  )
  VALUES
  (
    'root',
    '123',
    '1'
  ); 
  
  INSERT INTO
  `MUEBLERIA`.`USUARIO` (
     `nombre`,
  `password`,
  `tipo`
  )
  VALUES
  (
    'root2',
    '123',
    '2'
  );
  
  INSERT INTO
  `MUEBLERIA`.`USUARIO` (
     `nombre`,
  `password`,
  `tipo`
  )
  VALUES
  (
    'root3',
    '123',
    '3'
  );
