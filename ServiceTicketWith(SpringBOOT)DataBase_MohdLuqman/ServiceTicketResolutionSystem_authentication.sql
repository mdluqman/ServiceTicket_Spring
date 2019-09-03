-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: ServiceTicketResolutionSystem
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `authentication`
--

DROP TABLE IF EXISTS `authentication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authentication` (
  `username` varchar(255) NOT NULL,
  `passwords` varchar(255) DEFAULT NULL,
  `userTypeId` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK53on39sh4i9mmva61oo2s3qqa` (`userTypeId`),
  CONSTRAINT `FK53on39sh4i9mmva61oo2s3qqa` FOREIGN KEY (`userTypeId`) REFERENCES `usertypeinfo` (`userTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authentication`
--

LOCK TABLES `authentication` WRITE;
/*!40000 ALTER TABLE `authentication` DISABLE KEYS */;
INSERT INTO `authentication` VALUES ('Admin','Admin@007',1),('Logan','Logan@786',2),('Luqman','Luqman@786',2),('Maneesh','Maneesh@123',3),('Peter','Peter@786',2),('Sapna','Sapna@123',3),('Thor','Thor@123',2),('Tony','Tony@786',2),('USER-DELETED','USER-DELETEDa1',1);
/*!40000 ALTER TABLE `authentication` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-30 18:00:52
