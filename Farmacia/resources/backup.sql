-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema farmacia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema farmacia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `farmacia` DEFAULT CHARACTER SET utf8mb3 ;
USE `farmacia` ;

-- -----------------------------------------------------
-- Table `farmacia`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`categoria` (
  `codCategoria` INT NOT NULL AUTO_INCREMENT,
  `nombreCategoria` VARCHAR(200) NOT NULL,
  `disposicion` CHAR(2) NULL DEFAULT '1',
  PRIMARY KEY (`codCategoria`),
  UNIQUE INDEX `nombreCategoria` (`nombreCategoria` ASC) VISIBLE,
  UNIQUE INDEX `codCategoria_UNIQUE` (`codCategoria` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 28
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmacia`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`proveedor` (
  `codProveedor` INT NOT NULL AUTO_INCREMENT,
  `nombreEmpresa` VARCHAR(250) NOT NULL,
  `representante` VARCHAR(250) NULL DEFAULT 'sin representante',
  `direccion` VARCHAR(250) NULL DEFAULT 'sin direcciÃƒÂ³n',
  `celular` VARCHAR(20) NULL DEFAULT '0000000000',
  `telefono` VARCHAR(20) NULL DEFAULT '0000000',
  `ruc` VARCHAR(45) NULL DEFAULT '00000000000',
  `disposicion` CHAR(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codProveedor`),
  UNIQUE INDEX `nombreEmpresa_UNIQUE` (`nombreEmpresa` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmacia`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`producto` (
  `codProducto` INT NOT NULL AUTO_INCREMENT,
  `nombreProducto` VARCHAR(250) NOT NULL,
  `precioCompra` DECIMAL(9,2) NOT NULL,
  `precioVenta` DECIMAL(9,2) NOT NULL,
  `cantidad` INT NOT NULL,
  `unidadMedida` VARCHAR(100) NOT NULL,
  `presentacion` DECIMAL(9,2) NOT NULL,
  `marca` VARCHAR(45) NOT NULL,
  `fechaRegistro` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `observaciones` VARCHAR(250) NULL DEFAULT NULL,
  `formaFarmaceutica` VARCHAR(100) NOT NULL,
  `codCategoria` INT NOT NULL,
  `codProveedor` INT NOT NULL,
  `disposicion` CHAR(2) NULL DEFAULT '1',
  PRIMARY KEY (`codProducto`),
  INDEX `fk_Producto_Categoria1_idx` (`codCategoria` ASC) VISIBLE,
  INDEX `fk_Producto_Proveedor1_idx` (`codProveedor` ASC) VISIBLE,
  CONSTRAINT `fk_Producto_Categoria1`
    FOREIGN KEY (`codCategoria`)
    REFERENCES `farmacia`.`categoria` (`codCategoria`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Producto_Proveedor1`
    FOREIGN KEY (`codProveedor`)
    REFERENCES `farmacia`.`proveedor` (`codProveedor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 38
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmacia`.`bodega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`bodega` (
  `codBodega` INT NOT NULL AUTO_INCREMENT,
  `cantidadIngresada` INT NOT NULL,
  `fechaIngreso` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fechaCaducidad` DATE NOT NULL,
  `fechaCuandoCaduca` DATE NOT NULL,
  `codigoBarra` VARCHAR(250) NULL DEFAULT NULL,
  `codProducto` INT NOT NULL,
  PRIMARY KEY (`codBodega`),
  UNIQUE INDEX `codigobarra_UNIQUE` (`codigoBarra` ASC) VISIBLE,
  INDEX `pwd_producto_bodega` (`codProducto` ASC) VISIBLE,
  CONSTRAINT `pwd_producto_bodega`
    FOREIGN KEY (`codProducto`)
    REFERENCES `farmacia`.`producto` (`codProducto`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmacia`.`cita`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`cita` (
  `cod` BIGINT NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `title` VARCHAR(45) CHARACTER SET 'utf8mb3' NOT NULL,
  `description` VARCHAR(255) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  `colour` VARCHAR(45) CHARACTER SET 'utf8mb3' NULL DEFAULT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE INDEX `cod_UNIQUE` (`cod` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `farmacia`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`usuario` (
  `codUsuario` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `estado` INT NULL DEFAULT '0',
  `administrador` INT NULL DEFAULT '1',
  `cedula` VARCHAR(45) NOT NULL,
  `disposicion` CHAR(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codUsuario`),
  UNIQUE INDEX `codUsuario_UNIQUE` (`codUsuario` ASC) VISIBLE,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `cedulaa_UNIQUE` (`cedula` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmacia`.`factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`factura` (
  `codFactura` INT NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ruc` VARCHAR(20) NULL DEFAULT 'xxxxxxxxxx',
  `cedula` VARCHAR(12) NULL DEFAULT 'xxxxxxxxxx',
  `n_cliente` VARCHAR(250) NULL DEFAULT 'xxxxxxxxx',
  `observacion` VARCHAR(250) NULL DEFAULT 'SIn observacions',
  `subtotal` DECIMAL(9,2) NULL DEFAULT '0.00',
  `total` DECIMAL(9,2) NULL DEFAULT '0.00',
  `codUsuario` INT NOT NULL,
  PRIMARY KEY (`codFactura`),
  UNIQUE INDEX `codFactura_UNIQUE` (`codFactura` ASC) VISIBLE,
  INDEX `fk_Usuario_Factura_idx` (`codUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Factura`
    FOREIGN KEY (`codUsuario`)
    REFERENCES `farmacia`.`usuario` (`codUsuario`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 20
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `farmacia`.`detalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`detalle` (
  `codCarrito` INT NOT NULL AUTO_INCREMENT,
  `cantidad` INT NOT NULL,
  `codBodega` INT NOT NULL,
  `v_total` DECIMAL(6,2) NOT NULL DEFAULT '0.00',
  `codFactura` INT NOT NULL,
  PRIMARY KEY (`codCarrito`),
  UNIQUE INDEX `codCarrito_UNIQUE` (`codCarrito` ASC) VISIBLE,
  INDEX `fk_Carrito_Factura1_idx` (`codFactura` ASC) VISIBLE,
  INDEX `fk_Detalle_Bodega_idx` (`codBodega` ASC) VISIBLE,
  CONSTRAINT `fk_Detalle_Bodega`
    FOREIGN KEY (`codBodega`)
    REFERENCES `farmacia`.`bodega` (`codBodega`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Detalle_Factura1`
    FOREIGN KEY (`codFactura`)
    REFERENCES `farmacia`.`factura` (`codFactura`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 56
DEFAULT CHARACTER SET = utf8mb3;

USE `farmacia` ;

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_bodegafecha`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_bodegafecha` (`fechaCaducidad` INT, `codProducto` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_bodegasearchid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_bodegasearchid` (`codBodega` INT, `cantidadIngresada` INT, `fechaCaducidad` INT, `codigoBarra` INT, `codProducto` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_cantidadbodega`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_cantidadbodega` (`codBodega` INT, `codigoBarra` INT, `cantidadIngresada` INT, `fechaIngreso` INT, `fechaCaducidad` INT, `fechaCuandoCaduca` INT, `codProducto` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_cantidadproductoid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_cantidadproductoid` (`cantidadIngresada` INT, `codBodega` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_listardetallesid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_listardetallesid` (`codCarrito` INT, `cantidadD` INT, `nombreProducto` INT, `v_total` INT, `precioVenta` INT, `codFactura` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_productoporcodigo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_productoporcodigo` (`codBodega` INT, `nombreProducto` INT, `precioVenta` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_searchdetalleid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_searchdetalleid` (`codCarrito` INT, `cantidad` INT, `codBodega` INT, `v_total` INT, `codFactura` INT, `codProducto` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`view_searchproductoid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`view_searchproductoid` (`codProducto` INT, `nombreProducto` INT, `precioCompra` INT, `precioVenta` INT, `cantidad` INT, `unidadMedida` INT, `presentacion` INT, `marca` INT, `fechaRegistro` INT, `observaciones` INT, `formaFarmaceutica` INT, `codCategoria` INT, `codProveedor` INT);

-- -----------------------------------------------------
-- Placeholder table for view `farmacia`.`vista_productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `farmacia`.`vista_productos` (`codProducto` INT, `nombreProducto` INT, `precioCompra` INT, `precioVenta` INT, `cantidad` INT, `unidadMedida` INT, `presentacion` INT, `marca` INT, `fechaRegistro` INT, `observaciones` INT, `formaFarmaceutica` INT, `nombreCategoria` INT, `nombreEmpresa` INT);

-- -----------------------------------------------------
-- procedure proc_actualizarBodega
-- -----------------------------------------------------

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_actualizarBodega`(IN codBodegaIn int,IN cantidadIngresadaIn int,IN fechaCaducidadIn date,OUT respuesta char)
BEGIN


UPDATE `farmacia`.`bodega`
SET
bodega.cantidadIngresada = cantidadIngresadaIn,
bodega.fechaCaducidad = fechaCaducidadIn,
bodega.fechaCuandoCaduca =date_sub( fechaCaducidadIn, INTERVAL 90 DAY)
WHERE bodega.codBodega = codBodegaIn;
SET respuesta='1';

END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_actualizarDetalle
-- -----------------------------------------------------

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_actualizarDetalle`(IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, IN `codCarritoIn` INT, OUT `respuesta` INT)
BEGIN 
SET @cantidadP=(SELECT cantidadIngresada FROM bodega WHERE codBodega=codProductoIn);
SET @cantidadD=(SELECT cantidad FROM detalle WHERE codCarrito=codCarritoIn);

SET @residuo=0;
IF cantidadIn>@cantidadD THEN
SET @residuo=cantidadIn-@cantidadD;
END IF ;

IF (cantidadIn>0 AND @residuo<=@cantidadP AND @cantidadP>0 AND cantidadIn>@cantidadD) THEN
UPDATE detalle SET v_total=v_totalIn,cantidad=cantidadIn WHERE codCarrito=codCarritoIn;
SET respuesta=1;

ELSEIF (cantidadIn>0   AND cantidadIn<@cantidadD) THEN
UPDATE detalle SET v_total=v_totalIn,cantidad=cantidadIn WHERE  codCarrito=codCarritoIn;
SET respuesta=1;

ELSE 
SET respuesta=0;
END IF ;


END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_agregarBodega
-- -----------------------------------------------------

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarBodega`(IN cantidadIngresadaIn int,IN fechaCaducidadIn date, IN codProductoIn int,OUT respuesta char)
BEGIN

INSERT INTO bodega (`cantidadIngresada`,
`fechaCaducidad`,
`fechaCuandoCaduca`,
`codProducto`)
SELECT * FROM (SELECT cantidadIngresadaIn,
fechaCaducidadIn,
date_sub( fechaCaducidadIn, INTERVAL 90 DAY),
codProductoIn) AS tmp
WHERE NOT EXISTS (
    SELECT fechaCaducidad,codProducto FROM  bodega WHERE fechaCaducidad = fechaCaducidadIn AND codProducto=codProductoIn
) LIMIT 1;



/*
INSERT INTO `bodega`
(`cantidadIngresada`,
`fechaCaducidad`,
`fechaCuandoCaduca`,
`codProducto`)
SELECT 
cantidadIngresadaIn,
fechaCaducidadIn,
date_sub( fechaCaducidadIn, INTERVAL 90 DAY),
codProductoIn
WHERE NOT EXISTS(SELECT * FROM bodega WHERE bodega.codProducto=codProductoIn AND bodega.fechaCaducidad=fechaCaducidadIn)LIMIT 1;*/

UPDATE bodega SET bodega.codigoBarra = LPAD(CONVERT(@@identity,CHAR),13,'0') 
         WHERE bodega.codBodega = @@identity;
SET respuesta='1';
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_agregarNuevoDetalleFactura
-- -----------------------------------------------------

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarNuevoDetalleFactura`(IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, OUT `respuesta` INT)
BEGIN 
SET @cantidadP=(SELECT cantidadIngresada FROM bodega WHERE codBodega=codProductoIn);
SET @valor=(SELECT  total FROM factura WHERE codFactura=codFacturaIn);

IF (cantidadIn>0 AND cantidadIn<=@cantidadP) THEN
INSERT INTO detalle( cantidad, codBodega, v_total, codFactura) VALUES (cantidadIn,codProductoIn,v_totalIn,codFacturaIn);
UPDATE factura SET total=(@valor+v_totalIn) WHERE codFactura=codFacturaIn;
SET respuesta=1;
ELSE 
SET respuesta=0;
END IF ;


END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_agregarRepetidoDetalleFactura
-- -----------------------------------------------------

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarRepetidoDetalleFactura`(IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, IN `codCarritoIn` INT, OUT `respuesta` INT)
BEGIN 
SET @cantidadP=(SELECT cantidadIn FROM bodega WHERE codBodega=codProductoIn);
SET @cantidadD=(SELECT cantidad FROM detalle WHERE codCarrito=codCarritoIn);

SET @residuo=0;
IF cantidadIn>@cantidadD THEN
SET @residuo=cantidadIn-@cantidadD;
END IF ;

IF (cantidadIn>0 AND @residuo<=@cantidadP AND @cantidadP>0 AND cantidadIn>@cantidadD) THEN

UPDATE detalle SET v_total=v_totalIn,cantidad=cantidadIn WHERE codCarrito=codCarritoIn;
SET respuesta=1;
ELSE 
SET respuesta=0;
END IF ;


END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure proc_eliminarBodega
-- -----------------------------------------------------

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_eliminarBodega`(IN codBodegaIn int, OUT respuesta char)
BEGIN
DELETE FROM `farmacia`.`bodega`
WHERE bodega.codBodega=codBodegaIn;
SET respuesta='1';

END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `farmacia`.`view_bodegafecha`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_bodegafecha`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_bodegafecha` AS select `farmacia`.`bodega`.`fechaCaducidad` AS `fechaCaducidad`,`farmacia`.`bodega`.`codProducto` AS `codProducto` from `farmacia`.`bodega`;

-- -----------------------------------------------------
-- View `farmacia`.`view_bodegasearchid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_bodegasearchid`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_bodegasearchid` AS select `farmacia`.`bodega`.`codBodega` AS `codBodega`,`farmacia`.`bodega`.`cantidadIngresada` AS `cantidadIngresada`,`farmacia`.`bodega`.`fechaCaducidad` AS `fechaCaducidad`,`farmacia`.`bodega`.`codigoBarra` AS `codigoBarra`,`farmacia`.`bodega`.`codProducto` AS `codProducto` from `farmacia`.`bodega`;

-- -----------------------------------------------------
-- View `farmacia`.`view_cantidadbodega`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_cantidadbodega`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_cantidadbodega` AS select `farmacia`.`bodega`.`codBodega` AS `codBodega`,`farmacia`.`bodega`.`codigoBarra` AS `codigoBarra`,`farmacia`.`bodega`.`cantidadIngresada` AS `cantidadIngresada`,`farmacia`.`bodega`.`fechaIngreso` AS `fechaIngreso`,`farmacia`.`bodega`.`fechaCaducidad` AS `fechaCaducidad`,`farmacia`.`bodega`.`fechaCuandoCaduca` AS `fechaCuandoCaduca`,`farmacia`.`bodega`.`codProducto` AS `codProducto` from `farmacia`.`bodega` where (`farmacia`.`bodega`.`cantidadIngresada` > 0);

-- -----------------------------------------------------
-- View `farmacia`.`view_cantidadproductoid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_cantidadproductoid`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_cantidadproductoid` AS select `farmacia`.`bodega`.`cantidadIngresada` AS `cantidadIngresada`,`farmacia`.`bodega`.`codBodega` AS `codBodega` from (`farmacia`.`bodega` join `farmacia`.`producto`) where ((`farmacia`.`producto`.`codProducto` = `farmacia`.`bodega`.`codProducto`) and (`farmacia`.`producto`.`disposicion` = 1));

-- -----------------------------------------------------
-- View `farmacia`.`view_listardetallesid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_listardetallesid`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_listardetallesid` AS select `farmacia`.`detalle`.`codCarrito` AS `codCarrito`,`farmacia`.`detalle`.`cantidad` AS `cantidadD`,`farmacia`.`producto`.`nombreProducto` AS `nombreProducto`,`farmacia`.`detalle`.`v_total` AS `v_total`,`farmacia`.`producto`.`precioVenta` AS `precioVenta`,`farmacia`.`detalle`.`codFactura` AS `codFactura` from ((`farmacia`.`detalle` join `farmacia`.`producto`) join `farmacia`.`bodega`) where ((`farmacia`.`detalle`.`codBodega` = `farmacia`.`bodega`.`codBodega`) and (`farmacia`.`bodega`.`codProducto` = `farmacia`.`producto`.`codProducto`));

-- -----------------------------------------------------
-- View `farmacia`.`view_productoporcodigo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_productoporcodigo`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_productoporcodigo` AS select `farmacia`.`bodega`.`codBodega` AS `codBodega`,`farmacia`.`producto`.`nombreProducto` AS `nombreProducto`,`farmacia`.`producto`.`precioVenta` AS `precioVenta` from (`farmacia`.`bodega` join `farmacia`.`producto`) where ((`farmacia`.`producto`.`codProducto` = `farmacia`.`bodega`.`codProducto`) and (`farmacia`.`producto`.`disposicion` = 1) and (`farmacia`.`bodega`.`cantidadIngresada` > 0));

-- -----------------------------------------------------
-- View `farmacia`.`view_searchdetalleid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_searchdetalleid`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_searchdetalleid` AS select `farmacia`.`detalle`.`codCarrito` AS `codCarrito`,`farmacia`.`detalle`.`cantidad` AS `cantidad`,`farmacia`.`detalle`.`codBodega` AS `codBodega`,`farmacia`.`detalle`.`v_total` AS `v_total`,`farmacia`.`detalle`.`codFactura` AS `codFactura`,`farmacia`.`producto`.`codProducto` AS `codProducto` from ((`farmacia`.`detalle` join `farmacia`.`producto`) join `farmacia`.`bodega`) where ((`farmacia`.`detalle`.`codBodega` = `farmacia`.`bodega`.`codBodega`) and (`farmacia`.`bodega`.`codProducto` = `farmacia`.`producto`.`codProducto`) and (`farmacia`.`producto`.`disposicion` = 1));

-- -----------------------------------------------------
-- View `farmacia`.`view_searchproductoid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`view_searchproductoid`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`view_searchproductoid` AS select `farmacia`.`producto`.`codProducto` AS `codProducto`,`farmacia`.`producto`.`nombreProducto` AS `nombreProducto`,`farmacia`.`producto`.`precioCompra` AS `precioCompra`,`farmacia`.`producto`.`precioVenta` AS `precioVenta`,`farmacia`.`producto`.`cantidad` AS `cantidad`,`farmacia`.`producto`.`unidadMedida` AS `unidadMedida`,`farmacia`.`producto`.`presentacion` AS `presentacion`,`farmacia`.`producto`.`marca` AS `marca`,`farmacia`.`producto`.`fechaRegistro` AS `fechaRegistro`,`farmacia`.`producto`.`observaciones` AS `observaciones`,`farmacia`.`producto`.`formaFarmaceutica` AS `formaFarmaceutica`,`farmacia`.`producto`.`codCategoria` AS `codCategoria`,`farmacia`.`producto`.`codProveedor` AS `codProveedor` from `farmacia`.`producto` where (`farmacia`.`producto`.`disposicion` = 1);

-- -----------------------------------------------------
-- View `farmacia`.`vista_productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `farmacia`.`vista_productos`;
USE `farmacia`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `farmacia`.`vista_productos` AS select `farmacia`.`producto`.`codProducto` AS `codProducto`,`farmacia`.`producto`.`nombreProducto` AS `nombreProducto`,`farmacia`.`producto`.`precioCompra` AS `precioCompra`,`farmacia`.`producto`.`precioVenta` AS `precioVenta`,`farmacia`.`producto`.`cantidad` AS `cantidad`,`farmacia`.`producto`.`unidadMedida` AS `unidadMedida`,`farmacia`.`producto`.`presentacion` AS `presentacion`,`farmacia`.`producto`.`marca` AS `marca`,`farmacia`.`producto`.`fechaRegistro` AS `fechaRegistro`,`farmacia`.`producto`.`observaciones` AS `observaciones`,`farmacia`.`producto`.`formaFarmaceutica` AS `formaFarmaceutica`,`farmacia`.`categoria`.`nombreCategoria` AS `nombreCategoria`,`farmacia`.`proveedor`.`nombreEmpresa` AS `nombreEmpresa` from ((`farmacia`.`producto` join `farmacia`.`categoria`) join `farmacia`.`proveedor`) where ((`farmacia`.`producto`.`codCategoria` = `farmacia`.`categoria`.`codCategoria`) and (`farmacia`.`producto`.`codProveedor` = `farmacia`.`proveedor`.`codProveedor`) and (`farmacia`.`producto`.`disposicion` = 1));
USE `farmacia`;

DELIMITER $$
USE `farmacia`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `farmacia`.`bodega_insertBodegaAProducto`
AFTER INSERT ON `farmacia`.`bodega`
FOR EACH ROW
BEGIN

UPDATE producto SET producto.cantidad=producto.cantidad+NEW.cantidadIngresada WHERE  producto.codProducto=NEW.codProducto;
END$$

USE `farmacia`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `farmacia`.`tgr_actualizarBodega`
AFTER UPDATE ON `farmacia`.`bodega`
FOR EACH ROW
BEGIN
IF (NEW.cantidadIngresada>OLD.cantidadIngresada) THEN 
SET @cantidad=NEW.cantidadIngresada-OLD.cantidadIngresada;
UPDATE `farmacia`.`producto` SET producto.cantidad=producto.cantidad+@cantidad WHERE producto.codProducto =NEW.codProducto;
ELSEIF (NEW.cantidadIngresada<OLD.cantidadIngresada) THEN
SET @cantidad=OLD.cantidadIngresada-NEW.cantidadIngresada;
UPDATE `farmacia`.`producto` SET producto.cantidad=producto.cantidad-@cantidad WHERE producto.codProducto =NEW.codProducto;
END IF;
END$$

USE `farmacia`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `farmacia`.`tgr_despuesEliminarBodega`
AFTER DELETE ON `farmacia`.`bodega`
FOR EACH ROW
BEGIN
UPDATE `farmacia`.`producto`
SET producto.cantidad =producto.cantidad-OLD.cantidadIngresada
WHERE producto.codProducto =OLD.codProducto;
END$$

USE `farmacia`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `farmacia`.`tgr_eliminarDetalle`
AFTER DELETE ON `farmacia`.`detalle`
FOR EACH ROW
BEGIN

UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada+OLD.cantidad WHERE  bodega.codBodega=OLD.codBodega;

UPDATE factura SET 
factura.total=factura.total-OLD.v_total WHERE
factura.codFactura=OLD.codFactura;

SET @cantidad= (SELECT factura.total FROM factura WHERE factura.codFactura=OLD.codFactura);
IF @cantidad=0 THEN 
DELETE FROM factura WHERE factura.codFactura=OLD.codFactura;
END IF;
END$$

USE `farmacia`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `farmacia`.`tgr_updateDetalleProducto`
AFTER UPDATE ON `farmacia`.`detalle`
FOR EACH ROW
BEGIN
/* cuando agregamos cantidad al detalle
*/
 IF NEW.cantidad>OLD.cantidad AND OLD.cantidad>0  THEN
set @cantidad =NEW.cantidad-OLD.cantidad;
set @valor=NEW.v_total-OLD.v_total;
UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada-@cantidad WHERE bodega.codBodega=NEW.codBodega;

 UPDATE factura SET 
factura.total=factura.total+@valor WHERE
factura.codFactura=NEW.codFactura;
/* cuando quitamos cantidad al detalle
*/
 ELSEIF NEW.cantidad<OLD.cantidad AND NEW.cantidad>0 THEN
 set @cantidad =OLD.cantidad-NEW.cantidad;
 set @valor=OLD.v_total-NEW.v_total;
 UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada+@cantidad WHERE bodega.codBodega=NEW.codBodega;

UPDATE factura SET 
factura.total=factura.total-@valor WHERE
factura.codFactura=NEW.codFactura;
 END IF;
END$$

USE `farmacia`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `farmacia`.`trigger_restarProducto`
AFTER INSERT ON `farmacia`.`detalle`
FOR EACH ROW
BEGIN
UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada-NEW.cantidad WHERE  bodega.codBodega=NEW.codBodega;


END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
