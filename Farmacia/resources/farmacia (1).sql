-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 20-07-2023 a las 05:51:05
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_actualizarDetalle` (IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, IN `codCarritoIn` INT, OUT `respuesta` INT)   BEGIN 
SET @cantidadP=(SELECT cantidad FROM producto WHERE codProducto=codProductoIn);
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarNuevoDetalleFactura` (IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, OUT `respuesta` INT)   BEGIN 
SET @cantidadP=(SELECT cantidad FROM producto WHERE codProducto=codProductoIn);
SET @valor=(SELECT  total FROM factura WHERE codFactura=codFacturaIn);

IF (cantidadIn>0 AND cantidadIn<=@cantidadP) THEN
INSERT INTO detalle( cantidad, codProducto, v_total, codFactura) VALUES (cantidadIn,codProductoIn,v_totalIn,codFacturaIn);
UPDATE factura SET total=(@valor+v_totalIn) WHERE codFactura=codFacturaIn;
SET respuesta=1;
ELSE 
SET respuesta=0;
END IF ;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_agregarRepetidoDetalleFactura` (IN `cantidadIn` INT, IN `codProductoIn` INT, IN `v_totalIn` DECIMAL(6,2), IN `codFacturaIn` INT, IN `codCarritoIn` INT, OUT `respuesta` INT)   BEGIN 
SET @cantidadP=(SELECT cantidad FROM producto WHERE codProducto=codProductoIn);
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bodega`
--

CREATE TABLE `bodega` (
  `codigo` bigint(20) NOT NULL,
  `cantidadIngresada` int(11) NOT NULL,
  `fechaIngreso` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fechaCaducidad` date NOT NULL,
  `fechaCuandoCaduca` date NOT NULL,
  `codProducto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `bodega`
--

INSERT INTO `bodega` (`codigo`, `cantidadIngresada`, `fechaIngreso`, `fechaCaducidad`, `fechaCuandoCaduca`, `codProducto`) VALUES
(1, 5, '2023-07-10 01:17:53', '2023-09-12', '2023-12-11', 13),
(2, 3, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(3, 3, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(4, 5, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(5, 2, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(6, 4, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(7, 1, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(8, 2, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(9, 2, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(10, 3, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(11, 4, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(12, 5, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(13, 3, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(14, 6, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(15, 2, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(16, 4, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(17, 23, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(18, 2, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(19, 3, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(20, 3, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(21, 4, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(22, 1, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(23, 1, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(24, 1, '2023-07-02 06:22:03', '2023-10-11', '2024-01-09', 9),
(25, 1, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(26, 1, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(27, 1, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(28, 1, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(29, 1, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(30, 1, '2023-07-02 06:22:03', '2023-10-11', '2024-01-09', 9),
(31, 1, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(32, 1, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(33, 1, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(34, 2, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(35, 2, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(36, 2, '2023-07-02 06:22:03', '2023-10-11', '2024-01-09', 9),
(37, 2, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(38, 2, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(39, 2, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(40, 1, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(41, 1, '2023-07-02 06:34:24', '2023-10-13', '2024-01-11', 3),
(42, 1, '2023-07-02 06:22:03', '2023-10-11', '2024-01-09', 9),
(43, 1, '2023-07-09 15:16:28', '2025-12-12', '2026-03-12', 12),
(44, 1, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(45, 1, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(46, 3, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(47, 2, '2023-07-10 01:17:53', '2023-09-11', '2023-12-10', 13),
(48, 2, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(49, 1, '2023-07-10 01:17:53', '2023-09-08', '2023-12-07', 13),
(50, 1, '2023-07-10 01:17:53', '2023-09-08', '2023-12-07', 13),
(51, 2, '2023-07-02 06:30:07', '2023-12-13', '2024-03-12', 2),
(52, 1, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(53, 1, '2023-07-14 04:46:39', '2023-09-14', '2023-12-13', 14),
(54, 1, '2023-07-02 06:30:07', '2023-12-12', '2024-03-11', 2),
(55, 2, '2023-07-02 06:30:07', '2023-12-11', '2024-03-10', 2),
(56, 4, '2023-07-02 06:30:07', '2023-12-11', '2024-03-10', 2),
(57, 7, '2023-07-02 06:30:07', '2023-12-10', '2024-03-09', 2),
(58, 4, '2023-07-10 01:17:53', '2023-09-07', '2023-12-06', 13),
(59, 2, '2023-07-10 01:17:53', '2023-09-06', '2023-12-05', 13),
(60, 2, '2023-07-10 01:17:53', '2023-09-06', '2023-12-05', 13),
(61, 1, '2023-07-10 01:17:53', '2023-09-06', '2023-12-05', 13),
(62, 3, '2023-07-10 01:17:53', '2023-09-06', '2023-12-05', 13);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `codCategoria` int(11) NOT NULL,
  `nombreCategoria` varchar(200) NOT NULL,
  `disposicion` char(2) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`codCategoria`, `nombreCategoria`, `disposicion`) VALUES
(15, 'Antibiotico', '1'),
(16, 'Quinolonas  ', '1'),
(17, 'analgesico', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `codCarrito` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `codProducto` int(11) NOT NULL,
  `v_total` decimal(6,2) NOT NULL DEFAULT 0.00,
  `codFactura` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `detalle`
--

INSERT INTO `detalle` (`codCarrito`, `cantidad`, `codProducto`, `v_total`, `codFactura`) VALUES
(1, 3, 9, 16.80, 1),
(38, 3, 2, 21.00, 11),
(39, 3, 2, 21.00, 12),
(40, 4, 2, 28.00, 13),
(41, 9, 2, 63.00, 14),
(42, 9, 3, 51.30, 13),
(44, 3, 3, 17.10, 14),
(45, 4, 14, 35.64, 12),
(46, 5, 14, 44.55, 14),
(47, 3, 2, 21.00, 15),
(48, 12, 2, 84.00, 16),
(50, 2, 3, 11.40, 17),
(51, 11, 2, 77.00, 17),
(52, 7, 13, 23.80, 17),
(53, 3, 13, 10.20, 16);

--
-- Disparadores `detalle`
--
DELIMITER $$
CREATE TRIGGER `tgr_eliminarDetalle` AFTER DELETE ON `detalle` FOR EACH ROW BEGIN

UPDATE producto SET producto.cantidad=producto.cantidad+OLD.cantidad WHERE  producto.codProducto=OLD.codProducto;

UPDATE factura SET 
factura.total=factura.total-OLD.v_total WHERE
factura.codFactura=OLD.codFactura;

SET @cantidad= (SELECT factura.total FROM factura WHERE factura.codFactura=OLD.codFactura);
IF @cantidad=0 THEN 
DELETE FROM factura WHERE factura.codFactura=OLD.codFactura;
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tgr_updateDetalleProducto` AFTER UPDATE ON `detalle` FOR EACH ROW BEGIN
/* cuando agregamos cantidad al detalle
*/
 IF NEW.cantidad>OLD.cantidad AND OLD.cantidad>0  THEN
set @cantidad =NEW.cantidad-OLD.cantidad;
set @valor=NEW.v_total-OLD.v_total;
UPDATE producto SET producto.cantidad=producto.cantidad-@cantidad WHERE producto.codProducto=NEW.codProducto;

 UPDATE factura SET 
factura.total=factura.total+@valor WHERE
factura.codFactura=NEW.codFactura;
/* cuando quitamos cantidad al detalle
*/
 ELSEIF NEW.cantidad<OLD.cantidad AND NEW.cantidad>0 THEN
 set @cantidad =OLD.cantidad-NEW.cantidad;
 set @valor=OLD.v_total-NEW.v_total;
 UPDATE producto SET producto.cantidad=producto.cantidad+@cantidad WHERE producto.codProducto=NEW.codProducto;

UPDATE factura SET 
factura.total=factura.total-@valor WHERE
factura.codFactura=NEW.codFactura;
 END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trigger_restarProducto` AFTER INSERT ON `detalle` FOR EACH ROW BEGIN
UPDATE producto SET producto.cantidad=producto.cantidad-NEW.cantidad WHERE  producto.codProducto=NEW.codProducto;


END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `codFactura` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `ruc` varchar(20) DEFAULT 'xxxxxxxxxx',
  `cedula` varchar(12) DEFAULT 'xxxxxxxxxx',
  `n_cliente` varchar(250) DEFAULT 'xxxxxxxxx',
  `observacion` varchar(250) DEFAULT 'SIn observacions',
  `subtotal` decimal(9,2) DEFAULT 0.00,
  `total` decimal(9,2) DEFAULT 0.00,
  `codUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`codFactura`, `fecha`, `ruc`, `cedula`, `n_cliente`, `observacion`, `subtotal`, `total`, `codUsuario`) VALUES
(1, '2023-07-15 08:30:21', '', '', '', '', 0.00, 163.60, 1),
(11, '2023-07-15 08:30:21', '', '', '', '', 0.00, 35.00, 3),
(12, '2023-07-15 15:50:04', '', '', '', '', 0.00, 56.64, 3),
(13, '2023-07-15 15:42:45', '', '', '', '', 0.00, 79.30, 3),
(14, '2023-07-15 17:01:54', '', '', '', '', 0.00, 124.65, 3),
(15, '2023-07-18 03:44:37', '', '', '', '', 0.00, 21.00, 2),
(16, '2023-07-18 08:25:58', '', '', '', '', 0.00, 94.20, 2),
(17, '2023-07-20 03:23:53', '', '', '', '', 0.00, 112.20, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codProducto` int(11) NOT NULL,
  `nombreProducto` varchar(250) NOT NULL,
  `codigobarra` varchar(250) DEFAULT NULL,
  `precioCompra` decimal(9,2) NOT NULL,
  `precioVenta` decimal(9,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `unidadMedida` varchar(100) NOT NULL,
  `presentacion` decimal(9,2) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `fechaRegistro` timestamp NULL DEFAULT current_timestamp(),
  `fechaCaduca` date NOT NULL,
  `observaciones` varchar(250) DEFAULT NULL,
  `formaFarmaceutica` varchar(100) NOT NULL,
  `codCategoria` int(11) NOT NULL,
  `codProveedor` int(11) NOT NULL,
  `disposicion` char(2) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codProducto`, `nombreProducto`, `codigobarra`, `precioCompra`, `precioVenta`, `cantidad`, `unidadMedida`, `presentacion`, `marca`, `fechaRegistro`, `fechaCaduca`, `observaciones`, `formaFarmaceutica`, `codCategoria`, `codProveedor`, `disposicion`) VALUES
(2, 'Amoxicilina', '0000000000002', 4.60, 7.00, 1, 'mg', 500.00, 'pfizer', '2023-07-02 06:30:07', '2023-12-10', '', 'pastilla', 15, 10, '1'),
(3, 'Ciprofloxacino', '0000000000003', 4.50, 5.70, 35, 'mg', 100.00, 'bayer', '2023-07-02 06:34:24', '2023-10-13', '', 'pastillas', 16, 10, '1'),
(9, 'mebocaina', '0000000000009', 4.50, 5.60, 45, 'mg', 500.00, 'pfizer', '2023-07-02 06:22:03', '2023-10-11', '', 'pastillas', 15, 10, '0'),
(12, 'apronax', '0000000000012', 0.20, 0.30, -2, 'mg', 550.00, 'bayer', '2023-07-09 15:16:28', '2025-12-11', '', 'tableta', 15, 10, '0'),
(13, 'minoxidil', '0000000000013', 0.60, 3.40, 1, 'ml', 500.00, 'pfizer', '2023-07-10 01:17:53', '2023-09-06', '', 'pastilla', 17, 11, '1'),
(14, 'Aciclovir', '0000000000014', 7.90, 8.91, 0, 'mg', 200.00, 'Laboratorio Chile', '2023-07-14 04:46:39', '2023-09-14', '', 'comprimidos', 17, 11, '1');

--
-- Disparadores `producto`
--
DELIMITER $$
CREATE TRIGGER `tgr_insertProductoBodega` AFTER INSERT ON `producto` FOR EACH ROW BEGIN
INSERT INTO bodega(cantidadIngresada,fechaIngreso, fechaCaducidad, fechaCuandoCaduca, codProducto) VALUES(NEW.cantidad,NEW.fechaRegistro,NEW.fechaCaduca,DATE_ADD(NEW.fechaCaduca, INTERVAL 90 DAY),NEW.codProducto);
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `tgr_updateProductoBodega` AFTER UPDATE ON `producto` FOR EACH ROW BEGIN


 IF NEW.cantidad>=OLD.cantidad AND OLD.cantidad>0 THEN
    set @cantidad =  NEW.cantidad-OLD.cantidad;
    INSERT INTO bodega(cantidadIngresada,fechaIngreso, fechaCaducidad, fechaCuandoCaduca, codProducto)VALUES(@cantidad,NEW.fechaRegistro,NEW.fechaCaduca,DATE_ADD(NEW.fechaCaduca, INTERVAL 90 DAY),NEW.codProducto);
    ELSEIF NEW.cantidad>OLD.cantidad AND OLD.cantidad=0 THEN
    set @cantidad =  NEW.cantidad;
    INSERT INTO bodega(cantidadIngresada,fechaIngreso, fechaCaducidad, fechaCuandoCaduca, codProducto)VALUES(@cantidad,NEW.fechaRegistro,NEW.fechaCaduca,DATE_ADD(NEW.fechaCaduca, INTERVAL 90 DAY),NEW.codProducto);
  END IF;
  

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `codProveedor` int(11) NOT NULL,
  `nombreEmpresa` varchar(250) NOT NULL,
  `representante` varchar(250) DEFAULT 'sin representante',
  `direccion` varchar(250) DEFAULT 'sin direcciÃ³n',
  `celular` varchar(20) DEFAULT '0000000000',
  `telefono` varchar(20) DEFAULT '0000000',
  `ruc` varchar(45) DEFAULT '00000000000',
  `disposicion` char(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`codProveedor`, `nombreEmpresa`, `representante`, `direccion`, `celular`, `telefono`, `ruc`, `disposicion`) VALUES
(10, 'Difarmes', '', 'QF3H+MJP, 1, Quito 170111', '', '', '1234567890123', '1'),
(11, 'comfare', '', '', '', '', '', '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `codUsuario` int(11) NOT NULL,
  `nombre` varchar(250) NOT NULL,
  `password` varchar(255) NOT NULL,
  `estado` int(11) DEFAULT 0,
  `administrador` int(11) DEFAULT 1,
  `cedula` varchar(45) NOT NULL,
  `disposicion` char(2) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`codUsuario`, `nombre`, `password`, `estado`, `administrador`, `cedula`, `disposicion`) VALUES
(1, 'Andres Garcia', 'd760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1', 0, 1, '1010293939', '1'),
(2, 'Eloy Alfaro ', 'd760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1', 0, 0, '3301233312', '1'),
(3, 'Arturito', 'd760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1', 0, 1, '1234567890', '1'),
(4, 'Eddy', 'd760688da522b4dc3350e6fb68961b0934f911c7d0ff337438cabf4608789ba94ce70b6601d7e08a279ef088716c4b1913b984513fea4c557d404d0598d4f2f1', 0, 0, '1104731482', '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bodega`
--
ALTER TABLE `bodega`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `pwd_producto_bodega` (`codProducto`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`codCategoria`),
  ADD UNIQUE KEY `nombreCategoria` (`nombreCategoria`),
  ADD UNIQUE KEY `codCategoria_UNIQUE` (`codCategoria`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`codCarrito`),
  ADD UNIQUE KEY `codCarrito_UNIQUE` (`codCarrito`),
  ADD KEY `fk_Carrito_Factura1_idx` (`codFactura`),
  ADD KEY `fk_Detalle_Producto_idx` (`codProducto`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`codFactura`),
  ADD UNIQUE KEY `codFactura_UNIQUE` (`codFactura`),
  ADD KEY `fk_Usuario_Factura_idx` (`codUsuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codProducto`),
  ADD UNIQUE KEY `codigobarra_UNIQUE` (`codigobarra`),
  ADD KEY `fk_Producto_Categoria1_idx` (`codCategoria`),
  ADD KEY `fk_Producto_Proveedor1_idx` (`codProveedor`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`codProveedor`),
  ADD UNIQUE KEY `nombreEmpresa_UNIQUE` (`nombreEmpresa`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`codUsuario`),
  ADD UNIQUE KEY `codUsuario_UNIQUE` (`codUsuario`),
  ADD UNIQUE KEY `nombre_UNIQUE` (`nombre`),
  ADD UNIQUE KEY `cedulaa_UNIQUE` (`cedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bodega`
--
ALTER TABLE `bodega`
  MODIFY `codigo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `codCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `codCarrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT de la tabla `factura`
--
ALTER TABLE `factura`
  MODIFY `codFactura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `codProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `codProveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `codUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `bodega`
--
ALTER TABLE `bodega`
  ADD CONSTRAINT `pwd_producto_bodega` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `fk_Detalle_Factura1` FOREIGN KEY (`codFactura`) REFERENCES `factura` (`codFactura`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Detalle_Producto` FOREIGN KEY (`codProducto`) REFERENCES `producto` (`codProducto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `fk_Usuario_Factura` FOREIGN KEY (`codUsuario`) REFERENCES `usuario` (`codUsuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_Producto_Categoria1` FOREIGN KEY (`codCategoria`) REFERENCES `categoria` (`codCategoria`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_Producto_Proveedor1` FOREIGN KEY (`codProveedor`) REFERENCES `proveedor` (`codProveedor`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
