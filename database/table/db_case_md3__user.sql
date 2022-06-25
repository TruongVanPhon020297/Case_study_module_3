-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: db_case_md3
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `_user`
--

DROP TABLE IF EXISTS `_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `mobile` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `password_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `registered_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `_admin` bit(1) NOT NULL,
  `_status` bit(1) NOT NULL,
  `url_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_email` (`email`),
  UNIQUE KEY `mobile_UNIQUE` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_user`
--

LOCK TABLES `_user` WRITE;
/*!40000 ALTER TABLE `_user` DISABLE KEYS */;
INSERT INTO `_user` VALUES (1,'Trương Văn Phôn','0961654999','truongvanphon020297@gmail.com','0961654996aA','2022-06-21 18:39:01','2022-06-24 08:37:21',_binary '',_binary '','143086968_2856368904622192_1959732218791162458_n.png','Phú Vang, Thừa Thiên Huế'),(2,'Trương Công Mừng','0961654991','Mung@gmail.com','0961654996aA','2022-06-22 08:41:21','2022-06-23 17:10:39',_binary '\0',_binary '','143086968_2856368904622192_1959732218791162458_n.png','Thừa Thiên Héo'),(3,'Hoang Anh','0983770691','anh@gmail.com','0961654999a','2022-06-22 10:03:44',NULL,_binary '\0',_binary '','143086968_2856368904622192_1959732218791162458_n.png',NULL),(4,'Hoang Ngoc Anh','0983770692','anh123@gmail.com','0961654999a','2022-06-22 10:04:17',NULL,_binary '\0',_binary '','143086968_2856368904622192_1959732218791162458_n.png',NULL),(5,'Hoang Ngoc A','0983770699','anh123ngoc@gmail.com','0961654999a','2022-06-22 10:04:52',NULL,_binary '\0',_binary '','143086968_2856368904622192_1959732218791162458_n.png',NULL),(6,'Brielle Carver','0961654992','vaqybotico@mailinator.com','Pa$$w0rd!','2022-06-22 13:49:17',NULL,_binary '\0',_binary '','143086968_2856368904622192_1959732218791162458_n.png','Thừa Thiên Huế'),(7,'Kirestin King','0338243045','davym@mailinator.com','Pa$$w0rd!','2022-06-23 15:01:45',NULL,_binary '',_binary '','143086968_2856368904622192_1959732218791162458_n.png','Quo impedit sit cul');
/*!40000 ALTER TABLE `_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-24 17:29:23
