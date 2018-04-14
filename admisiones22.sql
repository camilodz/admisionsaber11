-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 31-03-2018 a las 00:14:45
-- Versión del servidor: 10.1.30-MariaDB
-- Versión de PHP: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `admisiones2`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CASOSESPECIALES`
--

CREATE TABLE `CASOSESPECIALES` (
  `ESPID` decimal(3,0) NOT NULL,
  `ESPNOMBRE` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `CASOSESPECIALES`
--

INSERT INTO `CASOSESPECIALES` (`ESPID`, `ESPNOMBRE`) VALUES
('250', 'Difícil acceso'),
('251', 'Normalistas'),
('252', 'Indígena'),
('253', 'Zona Marginal'),
('254', 'Afrodescendiente'),
('255', 'Costa Pacífica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `COMPONENTESICFES`
--

CREATE TABLE `COMPONENTESICFES` (
  `COMPID` decimal(3,0) NOT NULL,
  `COMPNOMBRE` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `COMPONENTESICFES`
--

INSERT INTO `COMPONENTESICFES` (`COMPID`, `COMPNOMBRE`) VALUES
('160', 'Lectura crítica'),
('161', 'Matemáticas'),
('162', 'Sociales y ciudadanas'),
('163', 'Ciencias naturales'),
('164', 'Inglés');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FACULTAD`
--

CREATE TABLE `FACULTAD` (
  `FACID` decimal(3,0) NOT NULL,
  `FACNOMBRE` varchar(100) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `FACULTAD`
--

INSERT INTO `FACULTAD` (`FACID`, `FACNOMBRE`) VALUES
('100', 'Facultad de Artes'),
('110', 'Facultad de Ciencias Agrarias'),
('120', 'Facultad de Ciencias de la Salud'),
('130', 'Facultad de Ciencias Contables, Económicas y Administrativas'),
('140', 'Facultad de Ciencias Humanas y Sociales'),
('150', 'Facultad de Ciencias Naturales, Exactas y de la Educación'),
('160', 'Facultad de Derecho, Ciencias Políticas y Sociales'),
('170', 'Facultad de Ingeniería Civil'),
('180', 'Facultad de Ingeniería Electrónica y Telecomunicaciones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PERIODOACADEMICO`
--

CREATE TABLE `PERIODOACADEMICO` (
  `PERID` decimal(5,1) NOT NULL,
  `PERPUNTAJEMINPOPAYAN` decimal(3,0) NOT NULL,
  `PERPUNTAJEMINREGIONALIZACION` decimal(3,0) NOT NULL,
  `PERESTADO` decimal(1,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PERIODOACADEMICO`
--

INSERT INTO `PERIODOACADEMICO` (`PERID`, `PERPUNTAJEMINPOPAYAN`, `PERPUNTAJEMINREGIONALIZACION`, `PERESTADO`) VALUES
('2017.2', '160', '140', '1'),
('2018.1', '155', '138', '0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROGRAMA`
--

CREATE TABLE `PROGRAMA` (
  `PROID` decimal(3,0) NOT NULL,
  `FACID` decimal(3,0) NOT NULL,
  `PRONOMBRE` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `PROPRUEBAAD` decimal(1,0) NOT NULL,
  `PROSEDE` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PROGRAMA`
--

INSERT INTO `PROGRAMA` (`PROID`, `FACID`, `PRONOMBRE`, `PROPRUEBAAD`, `PROSEDE`) VALUES
('200', '100', 'Artes Plásticas', '1', 'Popayán'),
('201', '100', 'Música', '1', 'Popayán'),
('202', '100', 'Diseño Gráfico', '0', 'Popayán'),
('300', '120', 'Medicina', '0', 'Popayán'),
('301', '120', 'Enfermería', '0', 'Popayán'),
('302', '120', 'Fisioterapia', '0', 'Popayán'),
('303', '120', 'Fonoaudiología', '0', 'Popayán'),
('400', '130', 'Economía', '0', 'Popayán'),
('401', '130', 'Administración de Empresas', '0', 'Popayán'),
('402', '130', 'Turismo', '0', 'Popayán'),
('403', '130', 'Contaduría Pública', '0', 'Popayán'),
('404', '130', 'Administración Financiera', '0', 'Santader de Quilichao'),
('500', '140', 'Antropología', '0', 'Popayán'),
('501', '140', 'Filosofía', '0', 'Popayán'),
('502', '140', 'Geografía', '0', 'Popayán'),
('503', '140', 'Historia', '0', 'Miranda'),
('504', '140', 'Licenciatura en Español y Literatura', '0', 'Popayán'),
('505', '140', 'Licenciatura en Etnoeducación', '0', 'Santader de Quilichao'),
('506', '140', 'Licenciatura en Lenguas Modernas Inglés-Francés', '0', 'Santader de Quilichao'),
('600', '150', 'Matemáticas', '0', 'Popayán'),
('700', '160', 'Ciencia Política', '0', 'Popayán'),
('701', '160', 'Comunicación Social', '0', 'Popayán'),
('702', '160', 'Derecho y Extensiones', '0', 'Popayán'),
('703', '160', 'Derecho Cobertura', '0', 'Miranda'),
('800', '170', 'Geotecnología', '0', 'Miranda'),
('801', '170', 'Ingeniería Ambiental', '0', 'Popayán'),
('802', '170', 'Ingeniería Civil', '0', 'Popayán'),
('900', '180', 'Ingeniería Electrónica y Telecomunicaciones', '0', 'Popayán'),
('901', '180', 'Ingeniería en Automática Industrial', '0', 'Popayán'),
('902', '180', 'Ingeniería de Sistemas', '0', 'Popayán'),
('903', '180', 'Tecnología en Telemática', '0', 'Popayán');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROGRAMACASOS`
--

CREATE TABLE `PROGRAMACASOS` (
  `ESPID` decimal(3,0) NOT NULL,
  `PROID` decimal(3,0) NOT NULL,
  `PERID` decimal(5,1) NOT NULL,
  `PROGCASOSCUPOS` decimal(2,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PROGRAMACASOS`
--

INSERT INTO `PROGRAMACASOS` (`ESPID`, `PROID`, `PERID`, `PROGCASOSCUPOS`) VALUES
('250', '400', '2017.2', '5'),
('250', '401', '2017.2', '5'),
('250', '600', '2017.2', '5'),
('250', '703', '2017.2', '5'),
('251', '302', '2017.2', '5'),
('251', '502', '2017.2', '5'),
('251', '503', '2017.2', '5'),
('251', '703', '2017.2', '5'),
('251', '801', '2017.2', '5'),
('252', '200', '2017.2', '5'),
('252', '301', '2017.2', '5'),
('252', '404', '2017.2', '5'),
('252', '800', '2017.2', '5'),
('253', '200', '2017.2', '5'),
('253', '301', '2017.2', '5'),
('253', '504', '2017.2', '5'),
('253', '900', '2017.2', '5'),
('253', '902', '2017.2', '5'),
('253', '903', '2017.2', '5'),
('254', '200', '2017.2', '5'),
('254', '202', '2017.2', '5'),
('254', '301', '2017.2', '5'),
('254', '302', '2017.2', '5'),
('254', '303', '2017.2', '5'),
('254', '400', '2017.2', '5'),
('254', '401', '2017.2', '5'),
('254', '403', '2017.2', '5'),
('254', '404', '2017.2', '5'),
('254', '500', '2017.2', '5'),
('254', '501', '2017.2', '5'),
('254', '502', '2017.2', '5'),
('254', '700', '2017.2', '5'),
('254', '701', '2017.2', '5'),
('254', '703', '2017.2', '5'),
('254', '900', '2017.2', '5'),
('254', '901', '2017.2', '5'),
('255', '400', '2017.2', '5'),
('255', '401', '2017.2', '5'),
('255', '402', '2017.2', '5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROGRAMACOMPONENTES`
--

CREATE TABLE `PROGRAMACOMPONENTES` (
  `PROID` decimal(3,0) NOT NULL,
  `PERID` decimal(5,1) NOT NULL,
  `COMPID` decimal(3,0) NOT NULL,
  `PROGCOMPPONDERADO` decimal(2,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PROGRAMACOMPONENTES`
--

INSERT INTO `PROGRAMACOMPONENTES` (`PROID`, `PERID`, `COMPID`, `PROGCOMPPONDERADO`) VALUES
('200', '2017.2', '162', '40'),
('202', '2017.2', '160', '25'),
('202', '2017.2', '161', '25'),
('202', '2017.2', '162', '45'),
('202', '2017.2', '163', '25'),
('202', '2017.2', '164', '40'),
('301', '2017.2', '160', '25'),
('301', '2017.2', '161', '25'),
('301', '2017.2', '162', '34'),
('301', '2017.2', '163', '25'),
('301', '2017.2', '164', '25'),
('401', '2017.2', '160', '45'),
('401', '2017.2', '161', '25'),
('401', '2017.2', '162', '45'),
('401', '2017.2', '163', '20'),
('401', '2017.2', '164', '30'),
('404', '2017.2', '160', '25'),
('404', '2017.2', '161', '35'),
('404', '2017.2', '162', '25'),
('404', '2017.2', '163', '25'),
('500', '2017.2', '161', '50'),
('500', '2017.2', '163', '40'),
('500', '2017.2', '164', '40'),
('600', '2017.2', '160', '25'),
('600', '2017.2', '161', '60'),
('600', '2017.2', '162', '25'),
('600', '2017.2', '163', '25'),
('600', '2017.2', '164', '25'),
('902', '2017.2', '160', '25'),
('902', '2017.2', '161', '25'),
('902', '2017.2', '162', '25'),
('902', '2017.2', '163', '35'),
('902', '2017.2', '164', '35');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PROGRAMAOFERTADO`
--

CREATE TABLE `PROGRAMAOFERTADO` (
  `PROID` decimal(3,0) NOT NULL,
  `PERID` decimal(5,1) NOT NULL,
  `PROGOFCUPOSREGULARES` decimal(2,0) NOT NULL,
  `PROGOFLISTADEESPERA` decimal(2,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PROGRAMAOFERTADO`
--

INSERT INTO `PROGRAMAOFERTADO` (`PROID`, `PERID`, `PROGOFCUPOSREGULARES`, `PROGOFLISTADEESPERA`) VALUES
('200', '2017.2', '54', '10'),
('201', '2017.2', '40', '12'),
('202', '2017.2', '60', '10'),
('300', '2017.2', '40', '12'),
('301', '2017.2', '60', '10'),
('302', '2017.2', '60', '10'),
('303', '2017.2', '65', '10'),
('400', '2017.2', '50', '12'),
('401', '2017.2', '60', '10'),
('402', '2017.2', '55', '12'),
('403', '2017.2', '45', '12'),
('404', '2017.2', '55', '12'),
('500', '2017.2', '43', '12'),
('501', '2017.2', '55', '12'),
('502', '2017.2', '56', '12'),
('503', '2017.2', '50', '10'),
('504', '2017.2', '45', '12'),
('505', '2017.2', '50', '10'),
('506', '2017.2', '65', '10'),
('600', '2017.2', '50', '10'),
('700', '2017.2', '60', '10'),
('701', '2017.2', '60', '12'),
('702', '2017.2', '45', '10'),
('703', '2017.2', '56', '10'),
('800', '2017.2', '45', '10'),
('801', '2017.2', '50', '10'),
('802', '2017.2', '45', '10'),
('900', '2017.2', '46', '10'),
('901', '2017.2', '50', '10'),
('902', '2017.2', '40', '10'),
('903', '2017.2', '45', '12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRUEBAADICIONAL`
--

CREATE TABLE `PRUEBAADICIONAL` (
  `PROID` decimal(3,0) NOT NULL,
  `PERID` decimal(5,1) NOT NULL,
  `PRUEBAPONDERADO` decimal(2,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PRUEBAADICIONAL`
--

INSERT INTO `PRUEBAADICIONAL` (`PROID`, `PERID`, `PRUEBAPONDERADO`) VALUES
('200', '2017.2', '35'),
('201', '2017.2', '40');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `CASOSESPECIALES`
--
ALTER TABLE `CASOSESPECIALES`
  ADD PRIMARY KEY (`ESPID`);

--
-- Indices de la tabla `COMPONENTESICFES`
--
ALTER TABLE `COMPONENTESICFES`
  ADD PRIMARY KEY (`COMPID`);

--
-- Indices de la tabla `FACULTAD`
--
ALTER TABLE `FACULTAD`
  ADD PRIMARY KEY (`FACID`);

--
-- Indices de la tabla `PERIODOACADEMICO`
--
ALTER TABLE `PERIODOACADEMICO`
  ADD PRIMARY KEY (`PERID`);

--
-- Indices de la tabla `PROGRAMA`
--
ALTER TABLE `PROGRAMA`
  ADD PRIMARY KEY (`PROID`),
  ADD KEY `FACID` (`FACID`);

--
-- Indices de la tabla `PROGRAMACASOS`
--
ALTER TABLE `PROGRAMACASOS`
  ADD PRIMARY KEY (`ESPID`,`PROID`,`PERID`),
  ADD KEY `ESPID` (`ESPID`),
  ADD KEY `PROID` (`PROID`),
  ADD KEY `PERID` (`PERID`);

--
-- Indices de la tabla `PROGRAMACOMPONENTES`
--
ALTER TABLE `PROGRAMACOMPONENTES`
  ADD PRIMARY KEY (`PROID`,`PERID`,`COMPID`),
  ADD KEY `PROID` (`PROID`),
  ADD KEY `PERID` (`PERID`),
  ADD KEY `COMPID` (`COMPID`);

--
-- Indices de la tabla `PROGRAMAOFERTADO`
--
ALTER TABLE `PROGRAMAOFERTADO`
  ADD PRIMARY KEY (`PROID`,`PERID`),
  ADD KEY `PERID` (`PERID`),
  ADD KEY `PROID` (`PROID`);

--
-- Indices de la tabla `PRUEBAADICIONAL`
--
ALTER TABLE `PRUEBAADICIONAL`
  ADD PRIMARY KEY (`PROID`,`PERID`),
  ADD KEY `PROID` (`PROID`),
  ADD KEY `PERID` (`PERID`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `PROGRAMA`
--
ALTER TABLE `PROGRAMA`
  ADD CONSTRAINT `PROGRAMA_ibfk_1` FOREIGN KEY (`FACID`) REFERENCES `FACULTAD` (`FACID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `PROGRAMACASOS`
--
ALTER TABLE `PROGRAMACASOS`
  ADD CONSTRAINT `PROGRAMACASOS_ibfk_2` FOREIGN KEY (`ESPID`) REFERENCES `CASOSESPECIALES` (`ESPID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PROGRAMACASOS_ibfk_5` FOREIGN KEY (`PROID`) REFERENCES `PROGRAMA` (`PROID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PROGRAMACASOS_ibfk_6` FOREIGN KEY (`PERID`) REFERENCES `PERIODOACADEMICO` (`PERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `PROGRAMACOMPONENTES`
--
ALTER TABLE `PROGRAMACOMPONENTES`
  ADD CONSTRAINT `PROGRAMACOMPONENTES_ibfk_1` FOREIGN KEY (`COMPID`) REFERENCES `COMPONENTESICFES` (`COMPID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PROGRAMACOMPONENTES_ibfk_2` FOREIGN KEY (`PROID`) REFERENCES `PROGRAMA` (`PROID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PROGRAMACOMPONENTES_ibfk_3` FOREIGN KEY (`PERID`) REFERENCES `PERIODOACADEMICO` (`PERID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `PROGRAMAOFERTADO`
--
ALTER TABLE `PROGRAMAOFERTADO`
  ADD CONSTRAINT `PROGRAMAOFERTADO_ibfk_1` FOREIGN KEY (`PERID`) REFERENCES `PERIODOACADEMICO` (`PERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PROGRAMAOFERTADO_ibfk_2` FOREIGN KEY (`PROID`) REFERENCES `PROGRAMA` (`PROID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `PRUEBAADICIONAL`
--
ALTER TABLE `PRUEBAADICIONAL`
  ADD CONSTRAINT `PRUEBAADICIONAL_ibfk_1` FOREIGN KEY (`PERID`) REFERENCES `PERIODOACADEMICO` (`PERID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PRUEBAADICIONAL_ibfk_2` FOREIGN KEY (`PROID`) REFERENCES `PROGRAMA` (`PROID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
