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
-- Table structure for table `ServiceEngineerBean`
--

DROP TABLE IF EXISTS `ServiceEngineerBean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ServiceEngineerBean` (
  `ServiceEngineerId` varchar(255) NOT NULL,
  `currentHighPrioityTicketId` varchar(255) DEFAULT NULL,
  `pending` int(11) NOT NULL,
  `totalTickets` int(11) NOT NULL,
  `SEusername` varchar(255) DEFAULT NULL,
  `deptNo` int(11) DEFAULT NULL,
  PRIMARY KEY (`ServiceEngineerId`),
  KEY `FKbqw35rvyj2gtqt4ox6tu6ig35` (`SEusername`),
  KEY `FK9qgxgaqev3acjh0sd7nfilq53` (`deptNo`),
  CONSTRAINT `FK9qgxgaqev3acjh0sd7nfilq53` FOREIGN KEY (`deptNo`) REFERENCES `deptInfo` (`deptNo`),
  CONSTRAINT `FKbqw35rvyj2gtqt4ox6tu6ig35` FOREIGN KEY (`SEusername`) REFERENCES `authentication` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ServiceEngineerBean`
--

LOCK TABLES `ServiceEngineerBean` WRITE;
/*!40000 ALTER TABLE `ServiceEngineerBean` DISABLE KEYS */;
INSERT INTO `ServiceEngineerBean` VALUES ('seid10001','TKTID11658',3,0,'Luqman',1),('seid10002','0',0,0,'Peter',1),('seid11001','0',0,0,'Logan',2),('seid12001','0',0,0,'Tony',3),('seid13002','TKTID94970',1,0,'Thor',4),('serviceEngineer-Deleted','empty-tikt',0,0,'USER-DELETED',4);
/*!40000 ALTER TABLE `ServiceEngineerBean` ENABLE KEYS */;
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
