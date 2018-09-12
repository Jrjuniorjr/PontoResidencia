-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_sistemaponto
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
-- Table structure for table `tbl_alunos`
--

DROP TABLE IF EXISTS `tbl_alunos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tbl_alunos` (
  `aluno_matr` char(10) DEFAULT NULL,
  `aluno_id` int(11) NOT NULL AUTO_INCREMENT,
  `aluno_nome` varchar(70) NOT NULL,
  `aluno_senha` varchar(20) NOT NULL,
  `prof_id` int(11) NOT NULL,
  PRIMARY KEY (`aluno_id`),
  UNIQUE KEY `aluno_matr` (`aluno_matr`),
  KEY `prof_id` (`prof_id`),
  CONSTRAINT `tbl_alunos_ibfk_1` FOREIGN KEY (`prof_id`) REFERENCES `tbl_professor` (`prof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_alunos`
--

LOCK TABLES `tbl_alunos` WRITE;
/*!40000 ALTER TABLE `tbl_alunos` DISABLE KEYS */;
INSERT INTO `tbl_alunos` VALUES ('2014205378',1,'Lucas Rafaell do Nascimento Santos','112358',1),('2015180353',2,'Pedro Henrique Araújo','134512',1),('2015114563',3,'Avelino Gomez A. Júnior','3412151',1),('2015108904',4,'Natalia Bartholo Pinto','1234',1),('2014205300',5,'Lucas Matheus','112352',1),('2015115865',6,'Glauber Freitas','123456',1),('2016107812',7,'Pedro Ribeiro','1234568',1),('2015111111',10,'Rafael','123452131',1);
/*!40000 ALTER TABLE `tbl_alunos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-11 23:16:53
