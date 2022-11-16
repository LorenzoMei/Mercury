-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: mercurydb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `idEvento` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descrizione` varchar(400) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `dataInizio` date NOT NULL,
  `dataFine` date NOT NULL,
  `zonaFK` int NOT NULL,
  `enteFk` int NOT NULL,
  PRIMARY KEY (`idEvento`),
  KEY `zonaFk_idx` (`zonaFK`),
  KEY `enteFk_idx` (`enteFk`),
  CONSTRAINT `enteFk` FOREIGN KEY (`enteFk`) REFERENCES `ente` (`idEnte`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `zonaFk1` FOREIGN KEY (`zonaFK`) REFERENCES `zona` (`idZona`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (1,'Sagra','Sagra a roma','sagra','2022-11-14','2022-11-15',1,2),(2,'Concerto','Cesare Cremonini','concerto','2023-02-12','2023-02-12',1,2),(3,'Il Marchese del Grillo','Compagnia teatrale Opera','teatro','2022-12-09','2022-12-09',1,1),(4,'Zucchero','Concerto Live','concerto','2022-11-16','2022-11-16',1,1),(5,'Concerto Laura Pausini','Concerto Live \r\nLaura Pausini\r\nPiazza Plebiscito\r\nore 21:00','concerto','2022-11-16','2022-11-16',3,1),(6,'Film in Piazza','Film in piazza Carlo Magno\r\nore 21:00','film','2022-11-25','2022-12-25',3,1);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-16 10:35:10
