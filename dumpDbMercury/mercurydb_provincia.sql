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
-- Table structure for table `provincia`
--

DROP TABLE IF EXISTS `provincia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provincia` (
  `idProvincia` int NOT NULL AUTO_INCREMENT,
  `nomeProvincia` varchar(45) NOT NULL,
  `regionefk` int NOT NULL,
  PRIMARY KEY (`idProvincia`),
  KEY `regionefk_idx` (`regionefk`),
  CONSTRAINT `regionefk` FOREIGN KEY (`regionefk`) REFERENCES `regioni` (`idRegioni`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provincia`
--

LOCK TABLES `provincia` WRITE;
/*!40000 ALTER TABLE `provincia` DISABLE KEYS */;
INSERT INTO `provincia` VALUES (1,'Torino',1),(2,'Vercelli',1),(3,'Novara',1),(4,'Cuneo',1),(5,'Asti',1),(6,'Alessandria',1),(7,'Valle d-Aosta',2),(8,'Imperia',7),(9,'Savona',7),(10,'Genova',7),(11,'La Spezia',7),(12,'Varese',3),(13,'Como',3),(14,'Sondrio',3),(15,'Milano',3),(16,'Bergamo',3),(17,'Brescia',3),(18,'Pavia',3),(19,'Cremona',3),(20,'Mantova',3),(21,'Bolzano',4),(22,'Trento',4),(23,'Verona',5),(24,'Vicenza',5),(25,'Belluno',5),(26,'Treviso',5),(27,'Venezia',5),(28,'Padova',5),(29,'Rovigo',5),(30,'Udine',6),(31,'Gorizia',6),(32,'Trieste',6),(33,'Piacenza',8),(34,'Parma',8),(35,'Reggio Emilia',8),(36,'Modena',8),(37,'Bologna',8),(38,'Ferrara',8),(39,'Ravenna',8),(40,'Forlì-Cesena',8),(41,'Pesaro e Urbino',11),(42,'Ancona',11),(43,'Macerata',11),(44,'Ascoli Piceno',11),(45,'Massa-Carrara',9),(46,'Lucca',9),(47,'Pistoia',9),(48,'Firenze',9),(49,'Livorno',9),(50,'Pisa',9),(51,'Arezzo',9),(52,'Siena',9),(53,'Grosseto',9),(54,'Perugia',10),(55,'Terni',10),(56,'Viterbo',12),(57,'Rieti',12),(58,'Roma',12),(59,'Latina',12),(60,'Frosinone',12),(61,'Caserta',15),(62,'Benevento',15),(63,'Napoli',15),(64,'Avellino',15),(65,'Salerno',15),(66,'L-Aquila',13),(67,'Teramo',13),(68,'Pescara',13),(69,'Chieti',13),(70,'Campobasso',14),(71,'Foggia',16),(72,'Bari',16),(73,'Taranto',16),(74,'Brindisi',16),(75,'Lecce',16),(76,'Potenza',17),(77,'Matera',17),(78,'Cosenza',18),(79,'Catanzaro',18),(80,'Reggio Calabria',18),(81,'Trapani',19),(82,'Palermo',19),(83,'Messina',19),(84,'Agrigento',19),(85,'Caltanissetta',19),(86,'Enna',19),(87,'Catania',19),(88,'Ragusa',19),(89,'Siracusa',19),(90,'Sassari',20),(91,'Nuoro',20),(92,'Cagliari',20),(93,'Pordenone',6),(94,'Isernia',14),(95,'Oristano',20),(96,'Biella',1),(97,'Lecco',3),(98,'Lodi',3),(99,'Rimini',8),(100,'Prato',9),(101,'Crotone',18),(102,'Vibo Valentia',18),(103,'Verbano-Cusio-Ossola',1),(108,'Monza e della Brianza',3),(109,'Fermo',11),(110,'Barletta-Andria-Trani',16),(111,'Sud Sardegna',20);
/*!40000 ALTER TABLE `provincia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-16 10:35:09
