-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: farmacia
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bodega`
--

DROP TABLE IF EXISTS `bodega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bodega` (
  `codBodega` bigint NOT NULL AUTO_INCREMENT,
  `cantidadIngresada` int NOT NULL,
  `fechaIngreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `fechaCaducidad` date NOT NULL,
  `fechaCuandoCaduca` date NOT NULL,
  `codigoBarra` varchar(250) DEFAULT NULL,
  `codProducto` int NOT NULL,
  PRIMARY KEY (`codBodega`),
  UNIQUE KEY `codigobarra_UNIQUE` (`codigoBarra`),
  KEY `pwd_producto_bodega` (`codProducto`),
  CONSTRAINT `pwd_producto_bodega` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bodega`
--

LOCK TABLES `bodega` WRITE;
/*!40000 ALTER TABLE `bodega` DISABLE KEYS */;
INSERT INTO `bodega` VALUES (1,1,'2023-08-05 05:07:37','2023-12-10','2023-09-11','0000000000001',2),(2,35,'2023-08-05 05:07:37','2023-10-13','2023-07-15','0000000000002',3),(3,45,'2023-08-05 05:07:37','2023-10-11','2023-07-13','0000000000003',9),(4,2,'2023-08-05 05:18:19','2025-12-11','2025-09-12','0000000000004',12),(5,1,'2023-08-05 05:07:37','2023-09-06','2023-06-08','0000000000005',13),(6,0,'2023-08-05 05:07:37','2023-09-14','2023-06-16','0000000000006',14),(7,3,'2023-08-05 05:07:37','2025-10-30','2025-08-01','0000000000007',15),(8,3,'2023-08-05 05:07:37','2026-02-01','2025-11-03','0000000000008',16),(9,3,'2023-08-05 05:07:37','2025-06-01','2025-03-03','0000000000009',17),(10,6,'2023-08-05 05:07:37','2025-03-01','2024-12-01','0000000000010',18),(11,7,'2023-08-05 05:07:37','2024-03-01','2023-12-02','0000000000011',19),(12,7,'2023-08-05 05:07:37','2024-04-01','2024-01-02','0000000000012',20),(13,4,'2023-08-05 05:07:37','2026-01-31','2025-11-02','0000000000013',21),(14,89,'2023-08-05 05:07:37','2026-02-01','2025-11-03','0000000000014',22),(15,45,'2023-08-05 05:07:38','2025-03-30','2024-12-30','0000000000015',23),(16,87,'2023-08-05 05:07:38','2026-02-01','2025-11-03','0000000000016',24),(17,36,'2023-08-05 05:07:38','2025-05-01','2025-01-31','0000000000017',25),(18,50,'2023-08-05 05:07:38','2025-05-01','2025-01-31','0000000000018',26),(19,64,'2023-08-05 05:07:38','2025-05-01','2025-01-31','0000000000019',27),(20,115,'2023-08-05 05:07:38','2025-03-01','2024-12-01','0000000000020',28),(21,15,'2023-08-05 05:07:38','2024-07-31','2024-05-02','0000000000021',29),(22,100,'2023-08-05 05:07:38','2025-05-01','2025-01-31','0000000000022',30),(23,40,'2023-08-05 05:07:38','2025-11-30','2025-09-01','0000000000023',31),(24,6,'2023-08-05 05:07:38','2026-07-01','2026-04-02','0000000000024',32),(25,0,'2023-08-05 05:07:38','2024-07-01','2024-04-02','0000000000025',33),(26,490,'2023-08-05 05:07:38','2024-10-01','2024-07-03','0000000000026',34),(27,104,'2023-08-05 05:07:38','2025-02-28','2024-11-30','0000000000027',35),(28,7,'2023-08-05 05:07:38','2027-11-01','2027-08-03','0000000000028',36),(29,5,'2023-08-05 05:07:38','2025-09-01','2025-06-03','0000000000029',37),(31,5,'2023-08-07 07:00:09','2023-09-08','2023-06-10','0000000000031',17);
/*!40000 ALTER TABLE `bodega` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `bodega_insertBodegaAProducto` AFTER INSERT ON `bodega` FOR EACH ROW BEGIN

UPDATE producto SET producto.cantidad=producto.cantidad+NEW.cantidadIngresada WHERE  producto.codProducto=NEW.codProducto;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tgr_actualizarBodega` AFTER UPDATE ON `bodega` FOR EACH ROW BEGIN
IF (NEW.cantidadIngresada>OLD.cantidadIngresada) THEN 
SET @cantidad=NEW.cantidadIngresada-OLD.cantidadIngresada;
UPDATE `farmacia`.`producto` SET producto.cantidad=producto.cantidad+@cantidad WHERE producto.codProducto =NEW.codProducto;
ELSEIF (NEW.cantidadIngresada<OLD.cantidadIngresada) THEN
SET @cantidad=OLD.cantidadIngresada-NEW.cantidadIngresada;
UPDATE `farmacia`.`producto` SET producto.cantidad=producto.cantidad-@cantidad WHERE producto.codProducto =NEW.codProducto;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tgr_despuesEliminarBodega` AFTER DELETE ON `bodega` FOR EACH ROW BEGIN
UPDATE `farmacia`.`producto`
SET producto.cantidad =producto.cantidad-OLD.cantidadIngresada
WHERE producto.codProducto =OLD.codProducto;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `codCategoria` int NOT NULL AUTO_INCREMENT,
  `nombreCategoria` varchar(200) NOT NULL,
  `disposicion` char(2) DEFAULT '1',
  PRIMARY KEY (`codCategoria`),
  UNIQUE KEY `nombreCategoria` (`nombreCategoria`),
  UNIQUE KEY `codCategoria_UNIQUE` (`codCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (15,'Antibiotico','1'),(16,'Quinolonas  ','1'),(17,'Analgesicos','1'),(18,'Antitusigenos','1'),(19,'Expectorante','1'),(20,'Antirreumaticos','1'),(21,'Antigripales','1'),(22,'Protector Gatrico','1'),(23,'Vitaminas','1'),(24,'Antiespasmodicos','1'),(25,'Antihistaminico','1'),(26,'Antiinflamatorio','1'),(27,'Corticoides','1');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `cod` bigint NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `title` varchar(45) CHARACTER SET utf8mb3 NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
  `colour` varchar(45) CHARACTER SET utf8mb3 DEFAULT NULL,
  PRIMARY KEY (`cod`),
  UNIQUE KEY `cod_UNIQUE` (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_hungarian_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle`
--

DROP TABLE IF EXISTS `detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle` (
  `codCarrito` int NOT NULL AUTO_INCREMENT,
  `cantidad` int NOT NULL,
  `codProducto` int NOT NULL,
  `v_total` decimal(6,2) NOT NULL DEFAULT '0.00',
  `codFactura` int NOT NULL,
  PRIMARY KEY (`codCarrito`),
  UNIQUE KEY `codCarrito_UNIQUE` (`codCarrito`),
  KEY `fk_Carrito_Factura1_idx` (`codFactura`),
  KEY `fk_Detalle_Producto_idx` (`codProducto`),
  CONSTRAINT `fk_Detalle_Factura1` FOREIGN KEY (`codFactura`) REFERENCES `factura` (`codFactura`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Detalle_Producto` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle`
--

LOCK TABLES `detalle` WRITE;
/*!40000 ALTER TABLE `detalle` DISABLE KEYS */;
INSERT INTO `detalle` VALUES (47,3,2,21.00,15),(48,12,2,84.00,16),(50,2,3,11.40,17),(51,11,2,77.00,17),(52,7,13,23.80,17),(53,3,13,10.20,16);
/*!40000 ALTER TABLE `detalle` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `trigger_restarProducto` AFTER INSERT ON `detalle` FOR EACH ROW BEGIN
UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada-NEW.cantidad WHERE  bodega.codProducto=NEW.codProducto;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tgr_updateDetalleProducto` AFTER UPDATE ON `detalle` FOR EACH ROW BEGIN
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
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tgr_eliminarDetalle` AFTER DELETE ON `detalle` FOR EACH ROW BEGIN

UPDATE bodega SET bodega.cantidadIngresada=bodega.cantidadIngresada+OLD.cantidad WHERE  bodega.codProducto=OLD.codProducto;

UPDATE factura SET 
factura.total=factura.total-OLD.v_total WHERE
factura.codFactura=OLD.codFactura;

SET @cantidad= (SELECT factura.total FROM factura WHERE factura.codFactura=OLD.codFactura);
IF @cantidad=0 THEN 
DELETE FROM factura WHERE factura.codFactura=OLD.codFactura;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `codFactura` int NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ruc` varchar(20) DEFAULT 'xxxxxxxxxx',
  `cedula` varchar(12) DEFAULT 'xxxxxxxxxx',
  `n_cliente` varchar(250) DEFAULT 'xxxxxxxxx',
  `observacion` varchar(250) DEFAULT 'SIn observacions',
  `subtotal` decimal(9,2) DEFAULT '0.00',
  `total` decimal(9,2) DEFAULT '0.00',
  `codUsuario` int NOT NULL,
  PRIMARY KEY (`codFactura`),
  UNIQUE KEY `codFactura_UNIQUE` (`codFactura`),
  KEY `fk_Usuario_Factura_idx` (`codUsuario`),
  CONSTRAINT `fk_Usuario_Factura` FOREIGN KEY (`codUsuario`) REFERENCES `usuario` (`codUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
INSERT INTO `factura` VALUES (15,'2023-07-18 03:44:37','','','','',0.00,21.00,2),(16,'2023-07-18 08:25:58','','','','',0.00,94.20,2),(17,'2023-07-20 03:23:53','','','','',0.00,112.20,3);
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `codProducto` int NOT NULL AUTO_INCREMENT,
  `nombreProducto` varchar(250) NOT NULL,
  `precioCompra` decimal(9,2) NOT NULL,
  `precioVenta` decimal(9,2) NOT NULL,
  `cantidad` int NOT NULL,
  `unidadMedida` varchar(100) NOT NULL,
  `presentacion` decimal(9,2) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `fechaRegistro` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `observaciones` varchar(250) DEFAULT NULL,
  `formaFarmaceutica` varchar(100) NOT NULL,
  `codCategoria` int NOT NULL,
  `codProveedor` int NOT NULL,
  `disposicion` char(2) DEFAULT '1',
  PRIMARY KEY (`codProducto`),
  KEY `fk_Producto_Categoria1_idx` (`codCategoria`),
  KEY `fk_Producto_Proveedor1_idx` (`codProveedor`),
  CONSTRAINT `fk_Producto_Categoria1` FOREIGN KEY (`codCategoria`) REFERENCES `categoria` (`codCategoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Producto_Proveedor1` FOREIGN KEY (`codProveedor`) REFERENCES `proveedor` (`codProveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (2,'Amoxicilina',4.60,7.00,1,'mg',500.00,'pfizer','2023-07-02 06:30:07','','pastilla',15,10,'0'),(3,'Ciprofloxacino',4.50,5.70,35,'mg',100.00,'bayer','2023-07-02 06:34:24','','pastillas',16,10,'0'),(9,'mebocaina',4.50,5.60,45,'mg',500.00,'pfizer','2023-07-02 06:22:03','','pastillas',15,10,'0'),(12,'apronax',0.20,0.30,2,'mg',550.00,'bayer','2023-07-09 15:16:28','','tableta',15,10,'0'),(13,'minoxidil',0.60,3.40,1,'ml',500.00,'pfizer','2023-07-10 01:17:53','','pastilla',17,11,'0'),(14,'Aciclovir',7.90,8.91,0,'mg',200.00,'Laboratorio Chile','2023-07-14 04:46:39','','comprimidos',17,11,'0'),(15,'Notusin Infantil ',6.27,9.00,3,'ML',180.00,'Gramon Millet','2023-07-29 18:30:44','','Jarabe',15,11,'1'),(16,'Notusin Infantil',4.62,6.50,3,'ml',100.00,'Gramon Millet','2023-07-29 22:11:45','','Jarabe',18,11,'1'),(17,'Notusin Adulto',6.27,9.00,7,'ml',180.00,'Gramon Millet','2023-07-29 22:13:48','','Jarabe',19,11,'1'),(18,'Loratadina Ambroxol',2.81,6.00,6,'ml',100.00,'Labovida','2023-07-29 22:19:09','','Jarabe',19,11,'1'),(19,'Bemin',2.08,2.54,7,'ml',120.00,'Life','2023-07-29 23:59:49','','Jarabe',18,11,'1'),(20,'Bemin Flux',5.52,7.00,7,'ml',120.00,'Life','2023-07-30 00:01:41','','Jarabe',19,11,'1'),(21,'Abrilar',6.94,9.00,4,'ml',100.00,'Megalabs Pharma','2023-07-30 00:07:16','','Jarabe',19,11,'1'),(22,'Adorlan',0.23,0.27,89,'mg',50.00,'Grunenthal','2023-07-30 00:11:00','','Comprimidos',17,11,'1'),(23,'Condrosol Trio',1.28,1.80,45,'mg',0.00,'BioGenet','2023-07-30 00:13:22','','Sobres ',20,11,'1'),(24,'Neogripal',0.51,0.70,87,'mg',0.00,'Chalver','2023-07-30 00:20:28','','Tabletas',21,11,'1'),(25,'Tusseg ',1.69,2.00,36,'mg',40.00,'Life','2023-07-30 00:22:43','','Sobres',22,11,'1'),(26,'Cefulam',1.18,1.40,50,'mg',500.00,'Lamosan','2023-07-30 00:24:29','','Tabletas',15,11,'1'),(27,'Omeprazol ',0.13,0.25,64,'mg',20.00,'La Sante','2023-07-30 00:27:10','','Tabletas',22,11,'1'),(28,'Omeprazol',0.44,0.60,115,'ml',40.00,'La Sante','2023-07-30 00:30:56','','Tabletas ',22,11,'1'),(29,'Dololuvit',3.04,3.60,15,'ml',0.00,'Pharma Brand','2023-07-30 00:34:56','','Solucion Inyectable',17,11,'1'),(30,'Cemin',0.39,0.50,100,'mg',500.00,'Life','2023-07-30 00:41:17','','Solucion Inyectable',23,11,'1'),(31,'Sertal Compuesto',0.31,0.45,40,'mg',2.50,'Megalabs','2023-07-30 00:44:24','','Tabletas ',24,11,'1'),(32,'Degraler',14.04,15.25,6,'ml',100.00,'Bago','2023-07-30 00:46:35','','Jarabe',25,11,'1'),(33,'Prenavit',9.90,13.00,0,'mg',0.00,'Siegfried','2023-07-30 00:51:46','','Tabletas',23,11,'1'),(34,'Complejo B ',0.03,0.05,490,'mg',0.00,'MK','2023-07-30 00:53:12','','Tabletas',23,11,'1'),(35,'Oralsept',0.21,0.25,104,'mg',0.00,'Dr Bjarner','2023-07-30 00:56:11','','Tabletas para Chupar',17,11,'1'),(36,'Hirudoid',9.75,11.00,7,'gr',40.00,'Cifarma','2023-07-30 01:06:59','','Gel',26,11,'1'),(37,'Betametasona ',2.51,3.10,5,'gr',40.00,'MK','2023-07-30 01:09:12','','CREMA',27,11,'1');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `codProveedor` int NOT NULL AUTO_INCREMENT,
  `nombreEmpresa` varchar(250) NOT NULL,
  `representante` varchar(250) DEFAULT 'sin representante',
  `direccion` varchar(250) DEFAULT 'sin direcciÃƒÂ³n',
  `celular` varchar(20) DEFAULT '0000000000',
  `telefono` varchar(20) DEFAULT '0000000',
  `ruc` varchar(45) DEFAULT '00000000000',
  `disposicion` char(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codProveedor`),
  UNIQUE KEY `nombreEmpresa_UNIQUE` (`nombreEmpresa`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (10,'Difarmes','','QF3H+MJP, 1, Quito 170111','','','1234567890123','1'),(11,'Comfare','','','','','','1'),(12,'Gramon Millet','COMFARE','GUAMANI SAN FERNANDO','','','','1');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `codUsuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) NOT NULL,
  `password` varchar(255) NOT NULL,
  `estado` int DEFAULT '0',
  `administrador` int DEFAULT '1',
  `cedula` varchar(45) NOT NULL,
  `disposicion` char(2) NOT NULL DEFAULT '1',
  PRIMARY KEY (`codUsuario`),
  UNIQUE KEY `codUsuario_UNIQUE` (`codUsuario`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  UNIQUE KEY `cedulaa_UNIQUE` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Andres Garcia','d760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1',0,1,'1010293939','0'),(2,'Eloy Alfaro ','d760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1',0,0,'3301233312','0'),(3,'Arturito','d760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1',0,1,'1234567890','1'),(4,'Eddy Granillo','9d7fb60c0a60b0fd6d30e79d0a4dbdcb525e8d05cd1795bc570b26b86d9a5c26c78623b77f4ad937202238b9790267f430f4534a921e66f55f7a02e1c47e7d11',0,1,'1104731482','1'),(5,'Mayra Vinueza ','1e5412fd7ed6f8f49c8c37177421a11dc7e828428fb0716d13b8d4912217246793974adb2cc94ee0041930df958c5b155dbb82a0a2686a2abd305478fd6058bd',0,1,'1724392582','1');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `view_bodegafecha`
--

DROP TABLE IF EXISTS `view_bodegafecha`;
/*!50001 DROP VIEW IF EXISTS `view_bodegafecha`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_bodegafecha` AS SELECT 
 1 AS `fechaCaducidad`,
 1 AS `codBodega`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_bodegasearchid`
--

DROP TABLE IF EXISTS `view_bodegasearchid`;
/*!50001 DROP VIEW IF EXISTS `view_bodegasearchid`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_bodegasearchid` AS SELECT 
 1 AS `codBodega`,
 1 AS `cantidadIngresada`,
 1 AS `fechaCaducidad`,
 1 AS `codigoBarra`,
 1 AS `codProducto`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `view_cantidadbodega`
--

DROP TABLE IF EXISTS `view_cantidadbodega`;
/*!50001 DROP VIEW IF EXISTS `view_cantidadbodega`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `view_cantidadbodega` AS SELECT 
 1 AS `codBodega`,
 1 AS `codigoBarra`,
 1 AS `cantidadIngresada`,
 1 AS `fechaIngreso`,
 1 AS `fechaCaducidad`,
 1 AS `fechaCuandoCaduca`,
 1 AS `codProducto`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_productos`
--

DROP TABLE IF EXISTS `vista_productos`;
/*!50001 DROP VIEW IF EXISTS `vista_productos`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `vista_productos` AS SELECT 
 1 AS `codProducto`,
 1 AS `nombreProducto`,
 1 AS `precioCompra`,
 1 AS `precioVenta`,
 1 AS `cantidad`,
 1 AS `unidadMedida`,
 1 AS `presentacion`,
 1 AS `marca`,
 1 AS `fechaRegistro`,
 1 AS `observaciones`,
 1 AS `formaFarmaceutica`,
 1 AS `nombreCategoria`,
 1 AS `nombreEmpresa`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping events for database 'farmacia'
--

--
-- Dumping routines for database 'farmacia'
--
/*!50003 DROP PROCEDURE IF EXISTS `proc_actualizarBodega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_actualizarBodega`(IN codBodegaIn int,IN cantidadIngresadaIn int,IN fechaCaducidadIn date,OUT respuesta char)
BEGIN

IF NOT exists( select * FROM view_bodegaFecha WHERE fechaCaducidad=fechaCaducidadIn AND codBodega=codBodegaIn) THEN
UPDATE `farmacia`.`bodega`
SET
bodega.cantidadIngresada = cantidadIngresadaIn,
bodega.fechaCaducidad = fechaCaducidadIn,
bodega.fechaCuandoCaduca =date_sub( fechaCaducidadIn, INTERVAL 90 DAY)
WHERE bodega.codBodega = codBodegaIn;
SET respuesta='1';
ELSE
SET respuesta='0';
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_actualizarDetalle` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_agregarBodega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_agregarNuevoDetalleFactura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_agregarRepetidoDetalleFactura` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_eliminarBodega` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_eliminarBodega`(IN codBodegaIn int, OUT respuesta char)
BEGIN
DELETE FROM `farmacia`.`bodega`
WHERE bodega.codBodega=codBodegaIn;
SET respuesta='1';

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `view_bodegafecha`
--

/*!50001 DROP VIEW IF EXISTS `view_bodegafecha`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_bodegafecha` AS select `bodega`.`fechaCaducidad` AS `fechaCaducidad`,`bodega`.`codBodega` AS `codBodega` from `bodega` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_bodegasearchid`
--

/*!50001 DROP VIEW IF EXISTS `view_bodegasearchid`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_bodegasearchid` AS select `bodega`.`codBodega` AS `codBodega`,`bodega`.`cantidadIngresada` AS `cantidadIngresada`,`bodega`.`fechaCaducidad` AS `fechaCaducidad`,`bodega`.`codigoBarra` AS `codigoBarra`,`bodega`.`codProducto` AS `codProducto` from `bodega` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `view_cantidadbodega`
--

/*!50001 DROP VIEW IF EXISTS `view_cantidadbodega`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `view_cantidadbodega` AS select `bodega`.`codBodega` AS `codBodega`,`bodega`.`codigoBarra` AS `codigoBarra`,`bodega`.`cantidadIngresada` AS `cantidadIngresada`,`bodega`.`fechaIngreso` AS `fechaIngreso`,`bodega`.`fechaCaducidad` AS `fechaCaducidad`,`bodega`.`fechaCuandoCaduca` AS `fechaCuandoCaduca`,`bodega`.`codProducto` AS `codProducto` from `bodega` where (`bodega`.`cantidadIngresada` > 0) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_productos`
--

/*!50001 DROP VIEW IF EXISTS `vista_productos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_productos` AS select `producto`.`codProducto` AS `codProducto`,`producto`.`nombreProducto` AS `nombreProducto`,`producto`.`precioCompra` AS `precioCompra`,`producto`.`precioVenta` AS `precioVenta`,`producto`.`cantidad` AS `cantidad`,`producto`.`unidadMedida` AS `unidadMedida`,`producto`.`presentacion` AS `presentacion`,`producto`.`marca` AS `marca`,`producto`.`fechaRegistro` AS `fechaRegistro`,`producto`.`observaciones` AS `observaciones`,`producto`.`formaFarmaceutica` AS `formaFarmaceutica`,`categoria`.`nombreCategoria` AS `nombreCategoria`,`proveedor`.`nombreEmpresa` AS `nombreEmpresa` from ((`producto` join `categoria`) join `proveedor`) where ((`producto`.`codCategoria` = `categoria`.`codCategoria`) and (`producto`.`codProveedor` = `proveedor`.`codProveedor`) and (`producto`.`disposicion` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-07 15:27:19
