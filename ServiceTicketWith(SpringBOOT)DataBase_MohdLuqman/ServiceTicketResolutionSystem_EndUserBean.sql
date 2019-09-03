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
-- Table structure for table `EndUserBean`
--

DROP TABLE IF EXISTS `EndUserBean`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EndUserBean` (
  `ticketId` varchar(255) NOT NULL,
  `dateOfAction` varchar(255) DEFAULT NULL,
  `dateOfCompletion` varchar(255) DEFAULT NULL,
  `dateOfIssue` varchar(255) DEFAULT NULL,
  `requestedEndDAte` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `ticketPriority` varchar(255) DEFAULT NULL,
  `ticketStatus` varchar(255) DEFAULT NULL,
  `workStation` varchar(255) DEFAULT NULL,
  `dept_deptNo` int(11) DEFAULT NULL,
  `serviceengineer_ServiceEngineerId` varchar(255) DEFAULT NULL,
  `customerUsername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticketId`),
  KEY `FKlfd58wvn7by8cb2ybkir9s8fp` (`dept_deptNo`),
  KEY `FK29qjbmt63rv20lrythuj3kakx` (`serviceengineer_ServiceEngineerId`),
  KEY `FKa2vpyo2l0pfcjcjx973ccgkj5` (`customerUsername`),
  CONSTRAINT `FK29qjbmt63rv20lrythuj3kakx` FOREIGN KEY (`serviceengineer_ServiceEngineerId`) REFERENCES `ServiceEngineerBean` (`ServiceEngineerId`),
  CONSTRAINT `FKa2vpyo2l0pfcjcjx973ccgkj5` FOREIGN KEY (`customerUsername`) REFERENCES `authentication` (`username`),
  CONSTRAINT `FKlfd58wvn7by8cb2ybkir9s8fp` FOREIGN KEY (`dept_deptNo`) REFERENCES `deptInfo` (`deptNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EndUserBean`
--

LOCK TABLES `EndUserBean` WRITE;
/*!40000 ALTER TABLE `EndUserBean` DISABLE KEYS */;
INSERT INTO `EndUserBean` VALUES ('TKTID01010','2019-08-30','2019-08-30','2019-08-30','2020-10-10','  \r\nExplain/Mention your t  \r\n','3','Completed','202',1,'serviceEngineer-Deleted','Sapna'),('TKTID03822','2019-08-29','2019-08-29','2019-08-29','2019-10-10','  \r\nExplain/Mentio \r\n','3','Completed','84',1,'serviceEngineer-Deleted','USER-DELETED'),('TKTID42923','2019-08-30','--','2019-08-30','2019-09-01','  \r\nExplain/  \r\n','3','WorkInProgress','1',4,'seid13002','Maneesh'),('TKTID64596','2019-08-29','2019-08-29','2019-08-29','2020-10-10','  \r\nExplain/Mention your requirement  \r\n','3','Completed','45',1,'serviceEngineer-Deleted','USER-DELETED'),('TKTID94970','--','--','2019-08-30','2019-09-10','  \r\nExplain/Mention  \r\n','3','Waiting','2',4,'seid13002','Maneesh');
/*!40000 ALTER TABLE `EndUserBean` ENABLE KEYS */;
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
