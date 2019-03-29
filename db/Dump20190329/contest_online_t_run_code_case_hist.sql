-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: contest_online
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `t_run_code_case_hist`
--

DROP TABLE IF EXISTS `t_run_code_case_hist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_run_code_case_hist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_id` int(11) NOT NULL,
  `result` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `code_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `case_id` (`case_id`),
  KEY `code_id` (`code_id`),
  CONSTRAINT `t_run_code_case_hist_ibfk_1` FOREIGN KEY (`case_id`) REFERENCES `t_problem_cases` (`id`),
  CONSTRAINT `t_run_code_case_hist_ibfk_2` FOREIGN KEY (`code_id`) REFERENCES `t_code_hist` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_run_code_case_hist`
--

LOCK TABLES `t_run_code_case_hist` WRITE;
/*!40000 ALTER TABLE `t_run_code_case_hist` DISABLE KEYS */;
INSERT INTO `t_run_code_case_hist` VALUES (5,50,'success',7),(6,53,'success',7),(7,42,'success',8),(8,43,'success',8),(9,51,'success',8),(10,52,'success',8),(11,42,'success',9),(12,43,'failure',9),(13,51,'success',9),(14,52,'success',9),(15,42,'overtime',10),(16,43,'overtime',10),(17,51,'overtime',10),(18,52,'overtime',10),(19,42,'overtime',11),(20,43,'overtime',11),(21,51,'overtime',11),(22,52,'overtime',11),(23,42,'overtime',12),(24,43,'overtime',12),(25,51,'overtime',12),(26,52,'overtime',12),(27,50,'failure',13),(28,53,'failure',13),(29,50,'success',14),(30,53,'success',14),(31,50,'success',15),(32,53,'success',15),(33,42,'success',16),(34,43,'failure',16),(35,51,'success',16),(36,52,'success',16),(37,50,'success',17),(38,53,'success',17),(39,42,'success',18),(40,43,'failure',18),(41,51,'success',18),(42,52,'success',18),(43,42,'success',19),(44,43,'failure',19),(45,51,'success',19),(46,52,'success',19),(47,42,'success',20),(48,43,'failure',20),(49,51,'success',20),(50,52,'success',20),(51,42,'success',21),(52,43,'failure',21),(53,51,'success',21),(54,52,'success',21),(55,42,'success',22),(56,43,'failure',22),(57,51,'success',22),(58,52,'success',22),(59,42,'success',23),(60,43,'failure',23),(61,51,'success',23),(62,52,'success',23),(63,42,'success',24),(64,43,'failure',24),(65,51,'success',24),(66,52,'success',24),(67,42,'success',25),(68,43,'failure',25),(69,51,'success',25),(70,52,'success',25),(71,42,'success',26),(72,43,'failure',26),(73,51,'success',26),(74,52,'success',26),(75,42,'success',27),(76,43,'failure',27),(77,51,'success',27),(78,52,'success',27),(79,42,'success',28),(80,43,'failure',28),(81,51,'success',28),(82,52,'success',28),(83,42,'success',29),(84,43,'failure',29),(85,51,'success',29),(86,52,'success',29),(87,42,'success',30),(88,43,'failure',30),(89,51,'success',30),(90,52,'success',30),(91,42,'success',31),(92,43,'failure',31),(93,42,'success',32),(94,51,'success',31),(95,43,'failure',32),(96,52,'success',31),(97,51,'success',32),(98,52,'success',32),(99,42,'success',33),(100,43,'failure',33),(101,51,'success',33),(102,52,'success',33),(103,42,'success',34),(104,42,'success',36),(105,43,'failure',34),(106,42,'success',37),(107,42,'success',35),(108,43,'failure',37),(109,43,'failure',36),(110,51,'success',34),(111,42,'success',38),(112,43,'failure',35),(113,51,'success',37),(114,43,'failure',38),(115,52,'success',34),(116,51,'success',35),(117,51,'success',36),(118,52,'success',37),(119,51,'success',38),(120,52,'success',36),(121,52,'success',35),(122,52,'success',38),(123,42,'success',39),(124,43,'failure',39),(125,51,'success',39),(126,52,'success',39),(127,42,'success',40),(128,43,'failure',40),(129,51,'success',40),(130,52,'success',40),(131,42,'success',41),(132,43,'failure',41),(133,51,'success',41),(134,52,'success',41),(135,42,'success',42),(136,43,'failure',42),(137,51,'success',42),(138,52,'success',42),(139,42,'success',43),(140,43,'failure',43),(141,51,'success',43),(142,52,'success',43),(143,42,'success',44),(144,43,'failure',44),(145,51,'success',44),(146,52,'success',44),(147,42,'success',45),(148,43,'failure',45),(149,51,'success',45),(150,52,'success',45),(151,42,'success',46),(152,43,'failure',46),(153,51,'success',46),(154,52,'success',46),(155,42,'success',47),(156,43,'failure',47),(157,51,'success',47),(158,52,'success',47),(159,42,'success',48),(160,43,'failure',48),(161,51,'success',48),(162,52,'success',48),(163,42,'success',49),(164,43,'failure',49),(165,51,'success',49),(166,52,'success',49),(167,42,'success',50),(168,43,'failure',50),(169,51,'success',50),(170,52,'success',50);
/*!40000 ALTER TABLE `t_run_code_case_hist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-29 13:16:56
