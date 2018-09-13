-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: db_sistemaponto
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_ponto`
--

DROP TABLE IF EXISTS `tbl_ponto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_ponto` (
  `aluno_id` int(3) DEFAULT NULL,
  `prof_id` int(3) DEFAULT NULL,
  `hora_ent` datetime NOT NULL,
  `hora_sai` datetime DEFAULT NULL,
  `relatorio_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`relatorio_id`),
  KEY `aluno_id` (`aluno_id`),
  KEY `prof_id` (`prof_id`),
  CONSTRAINT `tbl_ponto_ibfk_1` FOREIGN KEY (`aluno_id`) REFERENCES `tbl_alunos` (`aluno_id`),
  CONSTRAINT `tbl_ponto_ibfk_2` FOREIGN KEY (`prof_id`) REFERENCES `tbl_professor` (`prof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ponto`
--

LOCK TABLES `tbl_ponto` WRITE;
/*!40000 ALTER TABLE `tbl_ponto` DISABLE KEYS */;
INSERT INTO `tbl_ponto` VALUES (13,NULL,'2018-09-13 00:16:14',NULL,22);
/*!40000 ALTER TABLE `tbl_ponto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-12 17:30:05
