use farmacia;
ALTER TABLE bodega RENAME COLUMN codigo TO codBodega;
ALTER TABLE `farmacia`.`bodega` 
CHANGE COLUMN `codBodega` `codBodega` INT NOT NULL AUTO_INCREMENT ;
ALTER TABLE bodega  add codigoBarra varchar(250) DEFAULT NULL after fechaCuandoCaduca;
ALTER TABLE bodega ADD UNIQUE INDEX `codigobarra_UNIQUE` (`codigobarra` ASC) VISIBLE;
TRUNCATE TABLE bodega;


DELIMITER $$
CREATE PROCEDURE proc_actulizarBodega()
BEGIN
 DECLARE cursor_producto_isdone  BOOLEAN DEFAULT FALSE;
  

DECLARE cur_cantidadIngresada int;
DECLARE cur_codProducto int;
DECLARE cur_fechaCaducidad date;
DECLARE cur_fechaCuandoCaduca date;

 DECLARE cursor_producto CURSOR FOR SELECT  producto.codProducto,  producto.cantidad,
  producto.fechaCaduca,date_sub( producto.fechaCaduca, INTERVAL 90 DAY) FROM producto;
 


 DECLARE CONTINUE HANDLER FOR NOT FOUND SET cursor_producto_isdone = TRUE; 
 
 
  OPEN cursor_producto;
    loop_List: LOOP
    FETCH cursor_producto INTO cur_codProducto,cur_cantidadIngresada, cur_fechaCaducidad, cur_fechaCuandoCaduca;
      IF cursor_producto_isdone THEN
         LEAVE loop_List;
      END IF;
      /*
      aqui va codigo
      */
      INSERT INTO bodega(codProducto,cantidadIngresada,fechaCaducidad, fechaCuandoCaduca)
VALUES(cur_codProducto, cur_cantidadIngresada,cur_fechaCaducidad,cur_fechaCuandoCaduca);
      
       /*
      */
    END LOOP loop_List;
  CLOSE cursor_producto;
  

  
END
$$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE proc_actulizarBodegaCodBarras()
BEGIN
 DECLARE cur_codBodega int; 
DECLARE cursor_bodega_isdone  BOOLEAN DEFAULT FALSE;
DECLARE cursor_bodega CURSOR FOR SELECT bodega.codBodega FROM bodega;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET cursor_bodega_isdone = TRUE;
 
    OPEN cursor_bodega;
    loop_List: LOOP
    FETCH cursor_bodega INTO cur_codBodega;
      IF cursor_bodega_isdone THEN
         LEAVE loop_List;
      END IF;
      /*
      aqui va codigo
      */
      
      UPDATE bodega SET bodega.codigoBarra = LPAD(CONVERT(cur_codBodega,CHAR),13,'0') 
         WHERE bodega.codBodega = cur_codBodega;
       /*
      */
    END LOOP loop_List;
  CLOSE cursor_bodega;
  
  
 
END
$$

CALL proc_actulizarBodega();
CALL proc_actulizarBodegaCodBarras();
DROP PROCEDURE IF EXISTS proc_actulizarBodega;
DROP PROCEDURE IF EXISTS proc_actulizarBodegaCodBarras;
DROP TRIGGER IF EXISTS `farmacia`.`trigger_restarProducto`;
DROP TRIGGER IF EXISTS `farmacia`.`bodega_insertBodegaAProducto`;

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER = CURRENT_USER TRIGGER `farmacia`.`bodega_insertBodegaAProducto` AFTER INSERT ON `bodega` FOR EACH ROW
BEGIN
UPDATE bodega SET bodega.codigoBarra = LPAD(CONVERT(NEW.codBodega,CHAR),13,'0') 
         WHERE bodega.codBodega = NEW.codBodega;
UPDATE producto SET producto.cantidad=producto.cantidad+NEW.cantidadIngresada WHERE  producto.codProducto=NEW.codProducto;
END$$
DELIMITER ;

ALTER TABLE producto DROP COLUMN fechaCaduca ;
ALTER TABLE producto DROP COLUMN codigoBarra ;
DROP TRIGGER IF EXISTS `farmacia`.`trigger_restarProducto`;

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `trigger_restarProducto` AFTER INSERT ON `detalle` FOR EACH ROW BEGIN
UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada-NEW.cantidad WHERE  bodega.codProducto=NEW.codProducto;


END$$
DELIMITER ;

DROP TRIGGER IF EXISTS `farmacia`.`tgr_updateDetalleProducto`;

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `tgr_updateDetalleProducto` AFTER UPDATE ON `detalle` FOR EACH ROW BEGIN
/* cuando agregamos cantidad al detalle
*/
 IF NEW.cantidad>OLD.cantidad AND OLD.cantidad>0  THEN
set @cantidad =NEW.cantidad-OLD.cantidad;
set @valor=NEW.v_total-OLD.v_total;
UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada-@cantidad WHERE bodega.codProducto=NEW.codProducto;

 UPDATE factura SET 
factura.total=factura.total+@valor WHERE
factura.codFactura=NEW.codFactura;
/* cuando quitamos cantidad al detalle
*/
 ELSEIF NEW.cantidad<OLD.cantidad AND NEW.cantidad>0 THEN
 set @cantidad =OLD.cantidad-NEW.cantidad;
 set @valor=OLD.v_total-NEW.v_total;
 UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada+@cantidad WHERE bodega.codProducto=NEW.codProducto;

UPDATE factura SET 
factura.total=factura.total-@valor WHERE
factura.codFactura=NEW.codFactura;
 END IF;
END$$
DELIMITER ;

DROP TRIGGER IF EXISTS `farmacia`.`tgr_eliminarDetalle`;

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `tgr_eliminarDetalle` AFTER DELETE ON `detalle` FOR EACH ROW BEGIN

UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada+OLD.cantidad WHERE  bodega.codProducto=OLD.codProducto;

UPDATE factura SET 
factura.total=factura.total-OLD.v_total WHERE
factura.codFactura=OLD.codFactura;

SET @cantidad= (SELECT factura.total FROM factura WHERE factura.codFactura=OLD.codFactura);
IF @cantidad=0 THEN 
DELETE FROM factura WHERE factura.codFactura=OLD.codFactura;
END IF;
END$$
DELIMITER ;
USE `farmacia`;
DROP procedure IF EXISTS `proc_agregarRepetidoDetalleFactura`;

USE `farmacia`;
DROP procedure IF EXISTS `farmacia`.`proc_agregarRepetidoDetalleFactura`;
;

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
;


USE `farmacia`;
DROP procedure IF EXISTS `proc_agregarNuevoDetalleFactura`;

USE `farmacia`;
DROP procedure IF EXISTS `farmacia`.`proc_agregarNuevoDetalleFactura`;
;

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarNuevoDetalleFactura`(IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, OUT `respuesta` INT)
BEGIN 
SET @cantidadP=(SELECT cantidadIngresada FROM bodega WHERE codProducto=codProductoIn);
SET @valor=(SELECT  total FROM factura WHERE codFactura=codFacturaIn);

IF (cantidadIn>0 AND cantidadIn<=@cantidadP) THEN
INSERT INTO detalle( cantidad, codProducto, v_total, codFactura) VALUES (cantidadIn,codProductoIn,v_totalIn,codFacturaIn);
UPDATE factura SET total=(@valor+v_totalIn) WHERE codFactura=codFacturaIn;
SET respuesta=1;
ELSE 
SET respuesta=0;
END IF ;


END$$

DELIMITER ;
;

USE `farmacia`;
DROP procedure IF EXISTS `proc_actualizarDetalle`;

USE `farmacia`;
DROP procedure IF EXISTS `farmacia`.`proc_actualizarDetalle`;
;

DELIMITER $$
USE `farmacia`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_actualizarDetalle`(IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, IN `codCarritoIn` INT, OUT `respuesta` INT)
BEGIN 
SET @cantidadP=(SELECT cantidadIngresada FROM bodega WHERE codProducto=codProductoIn);
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
;


DROP PROCEDURE IF EXISTS proc_agregarRepetidoDetalleFacturatgr_updateDetalleProducto;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarRepetidoDetalleFactura`(IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, IN `codCarritoIn` INT, OUT `respuesta` INT)
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
ELSE 
SET respuesta=0;
END IF ;


END$$
ALTER TABLE `farmacia`.`detalle` 
ADD COLUMN `codBodega` INT NULL AFTER `codFactura`;

ALTER TABLE `farmacia`.`detalle` 
DROP FOREIGN KEY `fk_Detalle_Producto`;
ALTER TABLE `farmacia`.`detalle` 
DROP COLUMN `codProducto`,
CHANGE COLUMN `codBodega` `codBodega` INT NULL DEFAULT NULL AFTER `cantidad`,
ADD INDEX `fk_Detalle_Factura_idx` (`codBodega` ASC) VISIBLE,
DROP INDEX `fk_Detalle_Producto_idx` ;
;
ALTER TABLE `farmacia`.`detalle` 
ADD CONSTRAINT `fk_Detalle_Factura`
  FOREIGN KEY (`codBodega`)
  REFERENCES `farmacia`.`bodega` (`codBodega`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;
  
  
  USE `farmacia`;

DELIMITER $$

USE `farmacia`$$
DROP TRIGGER IF EXISTS `farmacia`.`tgr_updateProductoBodega` $$
DELIMITER ;


CREATE VIEW 
vista_productos AS 
SELECT codProducto,
nombreProducto,
precioCompra,
precioVenta,
cantidad,
unidadMedida,
presentacion,
marca,
fechaRegistro,
observaciones,
formaFarmaceutica,
nombreCategoria,
nombreEmpresa
 FROM Producto,Categoria,Proveedor
WHERE Producto.codCategoria=Categoria.codCategoria 
AND Producto.codProveedor=Proveedor.codProveedor AND Producto.disposicion=1;