CREATE DATABASE  IF NOT EXISTS `comics` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `comics`;
-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: comics
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Autor`
--

DROP TABLE IF EXISTS `Autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Autor` (
  `idAutor` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidos` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAutor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Autor`
--

LOCK TABLES `Autor` WRITE;
/*!40000 ALTER TABLE `Autor` DISABLE KEYS */;
INSERT INTO `Autor` VALUES (1,'Guilles','Chailet'),(2,'Tom','King'),(3,'Paco','Roca'),(4,'Sean','Philips'),(5,'Edmon','Baudoin'),(6,'Quim','Bou'),(7,'Fran','Jarata'),(8,'Sheink','Gon'),(9,'Jeff','Lemire'),(10,'Steward','Green'),(11,'Hanna','Barbera'),(12,'Barber','Villhaneli'),(13,'Marguerite','Bennet'),(14,'Craig','Thompson'),(15,'Shigueru','Mizuki'),(16,'Silas','Corey'),(17,'Yoshinobu','Akita'),(18,'Vitti','Bermejo'),(19,'David','Cruz'),(20,'Stuart','Inmonen'),(21,'Peter','Green'),(22,'Cullen','Bun'),(23,'Larry','Hama'),(24,'Larry ','Hama'),(25,'Jodorowsky','Fructus'),(26,'Vivora','Comics'),(27,'Istin','Colar'),(28,'Miville','Rumberg'),(29,'Frank','Miller'),(30,'Jennifer','Strauss'),(31,'Daniel','Collins'),(32,'Jared','Waters'),(33,'Calvin','Golliat'),(34,'Daniel','Way'),(35,'Rick','Remender');
/*!40000 ALTER TABLE `Autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comic`
--

DROP TABLE IF EXISTS `Comic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comic` (
  `idComic` int(11) NOT NULL,
  `costo` double DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `fechaPublicacion` date DEFAULT NULL,
  `existencias` int(11) DEFAULT NULL,
  `idSaga` int(11) DEFAULT NULL,
  PRIMARY KEY (`idComic`),
  KEY `idSaga` (`idSaga`),
  CONSTRAINT `Comic_ibfk_1` FOREIGN KEY (`idSaga`) REFERENCES `Saga` (`idSaga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comic`
--

LOCK TABLES `Comic` WRITE;
/*!40000 ALTER TABLE `Comic` DISABLE KEYS */;
INSERT INTO `Comic` VALUES (1,20,'Los escudos de Marte 3 - Sémiramis','2015-01-23',35,1),(2,30,'Los escudos de Marte 2','2015-01-25',16,1),(3,20,'Escudos de Marte 1 - Casus Belli','2015-01-30',200,1),(4,30,'Batman 5 - Yo soy Gotham 5','2014-03-24',562,2),(5,50,'Arrugas','2013-05-13',344,3),(6,100,'Kill or be Killed 1','2012-12-25',5443,4),(7,34,'Arleri','2011-02-14',123,5),(8,65,'Cercanías','2010-06-18',1234,6),(9,234,'A reventar!!','2001-01-01',345,7),(10,534,'De sangre y ron mi Cuba','2002-02-02',123,8),(11,65,'Watashitachi no Shiawase na Jikan','2003-03-03',435,9),(12,98,'Green Arrow 4 - Rompiendo tus lazos','2004-04-04',324,10),(13,65,'Ruta a Doom 5','2005-05-05',76,11),(14,455,'The Flintstones 1 - Borron y cuenta nueva','2014-02-13',64,12),(15,500,'Action Man 1 - El botín para los victoriosos','2006-06-06',354,13),(16,45,'Animosity 1 - El despertar','2007-07-07',436,14),(17,33,'Blankets','2008-08-08',658,15),(18,22,'Calle de los Misterios','2009-09-09',567,16),(19,45,'Silas Corey 2 - El Testamento de Zarkof 2ª pa','2010-10-10',234,17),(20,35,'Silas Corey 2 - El Testamento de Zarkof 1ª pa','2011-11-11',87,17),(21,34,'Silas Corey 1 - La Red Aquila','2012-12-12',987,17),(22,45,'Sorcerous Stabber Orphen 6','2013-01-13',675,18),(23,45,'Sorcerous Stabber Orphen 5','2014-02-14',589,18),(24,78,'Sorcerous Stabber Orphen 4','2015-03-15',24,18),(25,65,'Sorcerous Stabber Orphen 3','2016-04-16',525,18),(26,76,'Sorcerous Stabber Orphen 2','1995-05-17',575,18),(27,45,'Sorcerous Stabber Orphen 1','1996-06-18',75,18),(28,46,'Suiciders - Kings of HelL.A. 5 - Hijo de aguj','1997-07-19',456,19),(29,86,'Micronauts 1','1998-08-20',4567,20),(30,46,'Supermanes de America 6 - Al máximo','1999-09-21',356,21),(31,86,'Supermanes de America 5 - Lo inimaginable pas','2000-10-22',23,21),(32,86,'Supermanes de America 4 - A luchar por la jus','2001-11-23',1234,21),(33,35,'Supermanes de America 3 - Un poco de acción','2002-12-24',4445,21),(34,87,'Supermanes de America 2 - Punto de ebullición','2003-01-25',123,21),(35,68,'Supermanes de America 1 - En un mundo perfect','2004-02-26',213,21),(36,68,'Conan - The Slayer 1 - Sangre en su camino 1','2005-03-27',311,22),(37,54,'Excalibur 1','2006-04-28',2456,23),(38,57,'Spiderman - Negocios Familiares','2007-05-29',435,24),(39,76,'Showman Killer 2 - El niño de oro','2008-06-16',345,25),(40,87,'Turno de noche','2009-07-30',33,26),(41,45,'Templario 1 - En los muros de Tyr','2010-08-23',345,27),(42,97,'Reconquistas 2 - La trampa hitita','2011-09-14',65,28),(43,34,'Reconquistas 1 - La horda de los vivientes','2012-10-12',656,28),(44,87,'Ahoha Rhidor','2013-11-13',765,29),(45,567,'ALL STAR BATMAN & ROBIN, The boy wonder #2','2014-12-14',2014,34),(46,753,'ALL STAR BATMAN & ROBIN, The boy wonder #3','2015-01-12',2015,23),(47,56,'BATMAN #45','2016-02-18',345,4),(48,12,'SUPERMAN #37','1991-03-20',656,31),(49,21,'CIVIL WAR II #0','1993-04-22',24,32),(50,42,'INVINCIBLE IRON MAN #5','1994-05-09',234,33),(51,23,'VENOM: PROTECTOR LETAL','1995-09-27',188,34),(52,285,'ALL STAR BATMAN & ROBIN, The boy wonder #4','2015-01-20',1996,23),(53,43,'CHOOSING SIDES #1','1997-02-08',843,35),(54,54,'JUSTICE LEAGUE #47','1999-09-10',284,36);
/*!40000 ALTER TABLE `Comic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ComicAutor`
--

DROP TABLE IF EXISTS `ComicAutor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ComicAutor` (
  `idComic` int(11) NOT NULL,
  `idAutor` int(11) NOT NULL,
  PRIMARY KEY (`idComic`,`idAutor`),
  KEY `fk_ComicAutor_Autor1_idx` (`idAutor`),
  CONSTRAINT `fk_ComicAutor_Autor1` FOREIGN KEY (`idAutor`) REFERENCES `Autor` (`idAutor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComicAutor_Comic1` FOREIGN KEY (`idComic`) REFERENCES `Comic` (`idComic`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ComicAutor`
--

LOCK TABLES `ComicAutor` WRITE;
/*!40000 ALTER TABLE `ComicAutor` DISABLE KEYS */;
INSERT INTO `ComicAutor` VALUES (1,1),(2,1),(3,1),(4,2),(5,3),(6,4),(7,5),(8,6),(9,6),(10,7),(11,8),(12,9),(13,10),(14,11),(15,12),(16,13),(17,14),(18,15),(19,16),(20,16),(21,16),(22,17),(23,17),(24,17),(25,17),(26,17),(27,17),(28,18),(29,19),(30,20),(31,20),(32,20),(33,21),(34,21),(35,21),(36,22),(37,23),(38,24),(39,25),(40,26),(41,27),(42,28),(43,28),(44,29),(45,29),(46,29),(47,29),(48,30),(49,31),(50,32),(51,33),(52,34),(53,34),(54,35);
/*!40000 ALTER TABLE `ComicAutor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ComicTema`
--

DROP TABLE IF EXISTS `ComicTema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ComicTema` (
  `idTema` int(11) NOT NULL,
  `idComic` int(11) NOT NULL,
  PRIMARY KEY (`idTema`,`idComic`),
  KEY `fk_ComicTema_Comic1_idx` (`idComic`),
  CONSTRAINT `fk_ComicTema_Comic1` FOREIGN KEY (`idComic`) REFERENCES `Comic` (`idComic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComicTema_Tema1` FOREIGN KEY (`idTema`) REFERENCES `Tema` (`idTema`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ComicTema`
--

LOCK TABLES `ComicTema` WRITE;
/*!40000 ALTER TABLE `ComicTema` DISABLE KEYS */;
INSERT INTO `ComicTema` VALUES (1,1),(1,2),(1,3),(1,4),(2,5),(3,6),(2,7),(3,8),(3,9),(2,10),(2,11),(1,12),(1,13),(4,14),(1,15),(5,16),(6,17),(5,18),(7,19),(7,20),(7,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(3,28),(3,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(3,37),(1,38),(5,39),(5,40),(1,41),(1,42),(1,43),(8,44),(2,45),(3,46),(5,47),(9,48),(10,49),(3,50),(1,51),(1,52),(11,53),(1,54);
/*!40000 ALTER TABLE `ComicTema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ComicVenta`
--

DROP TABLE IF EXISTS `ComicVenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ComicVenta` (
  `idComic` int(11) NOT NULL,
  `idVenta` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  PRIMARY KEY (`idComic`,`idVenta`),
  KEY `fk_ComicVenta_Venta1_idx` (`idVenta`),
  CONSTRAINT `fk_ComicVenta_Comic1` FOREIGN KEY (`idComic`) REFERENCES `Comic` (`idComic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ComicVenta_Venta1` FOREIGN KEY (`idVenta`) REFERENCES `Venta` (`idVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ComicVenta`
--

LOCK TABLES `ComicVenta` WRITE;
/*!40000 ALTER TABLE `ComicVenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `ComicVenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Empleado`
--

DROP TABLE IF EXISTS `Empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Empleado` (
  `idEmpleado` int(11) NOT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `contrasena` varchar(45) DEFAULT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellidoP` varchar(45) DEFAULT NULL,
  `apellidoM` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idEmpleado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Empleado`
--

LOCK TABLES `Empleado` WRITE;
/*!40000 ALTER TABLE `Empleado` DISABLE KEYS */;
INSERT INTO `Empleado` VALUES (1,'maria1','123456','MARIA CRISTINA ','LIMON','LASCURAIN'),(2,'gildarda1','123456','GILDARDA ','BECERRIL','CONTRERAS'),(3,'rebeca1','123456','REBECA ','ACEVES','GARCIA'),(4,'laura1','123456','LAURA ','AGUILAR','FERNANDEZ'),(5,'silvano1','123456','SILVANO FROYLAN ','MORALES','ESTRADA'),(6,'guillermo1','123456','GUILLERMO ','SANTIN','GARCIA'),(7,'eduardo1','123456','EDUARDO ','HERNANDEZ','SANCHEZ'),(8,'porfirio1','123456','PORFIRIO ','HERNANDEZ','GONZALEZ'),(9,'adan1','123456','ADAN ','LOPEZ','VARELA'),(10,'guadalupe1','123456','GUADALUPE GONZALEZ ','FRANCO','BARRADAS'),(11,'jorge1','123456','JORGE ','ROJAS','GUTIERREZ'),(12,'lorenzo1','123456','LORENZO JUAN JOSE ','SERVITJE',''),(13,'maria2','123456','MARIA TERESA ','MARTIN','SANCHEZ'),(14,'jose1','123456','JOSE ','MENDEZ','RODRIGUEZ'),(15,'maria3','123456','MARIA ','ESCOBAR','LOMELI'),(16,'carlota1','123456','CARLOTA ','CREEL','ALGARA'),(17,'luis1','123456','LUIS ','CORTES','ACEVEDO'),(18,'jesus1','123456','JESUS ','GALVAN','MORENO'),(19,'juana1','123456','JUANA ','HERNANDEZ','HERNANDEZ'),(20,'herlinda1','123456','HERLINDA ','CRUZ','CRUZ');
/*!40000 ALTER TABLE `Empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Pedido`
--

DROP TABLE IF EXISTS `Pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Pedido` (
  `idPedido` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `fechaPedido` date DEFAULT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `monto` double DEFAULT NULL,
  `idProveedor` int(11) NOT NULL,
  `idComic` int(11) NOT NULL,
  PRIMARY KEY (`idPedido`),
  KEY `fk_Pedido_Proveedor1_idx` (`idProveedor`),
  KEY `fk_Pedido_Comic1_idx` (`idComic`),
  CONSTRAINT `fk_Pedido_Comic1` FOREIGN KEY (`idComic`) REFERENCES `Comic` (`idComic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Proveedor1` FOREIGN KEY (`idProveedor`) REFERENCES `Proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Pedido`
--

LOCK TABLES `Pedido` WRITE;
/*!40000 ALTER TABLE `Pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `Pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Proveedor`
--

DROP TABLE IF EXISTS `Proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Proveedor` (
  `idProveedor` int(11) NOT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `cp` int(11) DEFAULT NULL,
  `colonia` varchar(45) DEFAULT NULL,
  `municipio` varchar(45) DEFAULT NULL,
  `calle` varchar(45) DEFAULT NULL,
  `noExt` int(11) DEFAULT NULL,
  `noInt` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Proveedor`
--

LOCK TABLES `Proveedor` WRITE;
/*!40000 ALTER TABLE `Proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `Proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Saga`
--

DROP TABLE IF EXISTS `Saga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Saga` (
  `idSaga` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `editorial` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSaga`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Saga`
--

LOCK TABLES `Saga` WRITE;
/*!40000 ALTER TABLE `Saga` DISABLE KEYS */;
INSERT INTO `Saga` VALUES (1,'Los escudos de Marte','DC Comics México'),(2,'Batman','DC Comics México'),(3,'Arrugas','Image Comics México'),(4,'Kill or be Killed','Image Comics México'),(5,'Arleri','Image Comics México'),(6,'Cercanias','Image Comics México'),(7,'A reventar!!','Image Comics México'),(8,'De sangre y ron mi Cuba','Image Comics México'),(9,'Watashitashi no Shiawase na Jikan','Image Comics México'),(10,'Green Arrow','DC Comics México'),(11,'Ruta a Doom','DC Comics México'),(12,'The Flintstones','Image Comics México'),(13,'Action Man','DC Comics México'),(14,'Animosity','Panini Comics México - Manga'),(15,'Blankets','Panini Comics México - Manga'),(16,'Calle de los misterios','Panini Comics México - Manga'),(17,'Silas Corey','Panini Comics México - Manga'),(18,'Sorcerous Stabber Orphen','DC Comics México'),(19,'Suiciders','Editorial Vid'),(20,'Micronauts','Editorial Vid'),(21,'Supermanes de America','DC Comics México'),(22,'Conan the Slayer','DC Comics México'),(23,'Excalibur','Image Comics México'),(24,'Spiderman','Marvel Comics México'),(25,'Showman Killer','Panini Comics México – Manga'),(26,'Turno de noche','Panini Comics México – Manga'),(27,'Templario','DC Comics México'),(28,'Reconquistas','DC Comics México'),(29,'Ahoha Rhidor','Image Comics México'),(30,'ALL STAR BATMAN & ROBIN','Image Comics México'),(31,'SUPERMAN','DC Comics México'),(32,'CIVIL WAR II','Marvel Comics México'),(33,'INVINCIBLE IRON MAN','Marvel Comics México'),(34,'VENOM','Marvel Comics México'),(35,'CHOOSING SIDES','Marvel Comics México'),(36,'JUSTICE LEAGUE','DC Comics México');
/*!40000 ALTER TABLE `Saga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tema`
--

DROP TABLE IF EXISTS `Tema`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tema` (
  `idTema` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTema`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tema`
--

LOCK TABLES `Tema` WRITE;
/*!40000 ALTER TABLE `Tema` DISABLE KEYS */;
INSERT INTO `Tema` VALUES (1,'Acción'),(2,'Romance'),(3,'Ciencia ficción'),(4,'Comedia'),(5,'Terror'),(6,'Novela'),(7,'Misterio'),(8,'Aventura'),(9,'Suspenso'),(10,'Drama'),(11,'Aventura');
/*!40000 ALTER TABLE `Tema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Venta`
--

DROP TABLE IF EXISTS `Venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Venta` (
  `idVenta` int(11) NOT NULL,
  `monto` double DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `Empleado_idEmpleado` int(11) NOT NULL,
  PRIMARY KEY (`idVenta`),
  KEY `fk_Venta_Empleado1_idx` (`Empleado_idEmpleado`),
  CONSTRAINT `fk_Venta_Empleado1` FOREIGN KEY (`Empleado_idEmpleado`) REFERENCES `Empleado` (`idEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Venta`
--

LOCK TABLES `Venta` WRITE;
/*!40000 ALTER TABLE `Venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `Venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-26 18:17:41
