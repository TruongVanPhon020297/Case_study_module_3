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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `url_image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(12,0) DEFAULT '0',
  `quantity` decimal(12,0) DEFAULT '0',
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `stop_selling` int DEFAULT NULL,
  `id_category` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_category` (`id_category`),
  CONSTRAINT `fk_id_category` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Bánh Ngọt Nhiều Màu','0ed6a3b1-2b4e-499d-94b1-8b5e00fc3a79.jpg',20000,500,'2022-06-20 07:59:12','2022-06-24 10:09:58',1,3),(2,'Bánh Ngọt Socola Nhân','1c6a3aa1-fd4c-4d02-b1b9-181b0f0fc99d.jpg',25000,20,'2022-06-20 08:02:17','2022-06-24 13:54:53',1,1),(3,'Bánh Ngọt Trà Xanh','4ec10fb7-23b6-4a76-b874-41afe74ee015.jpg',15000,50,'2022-06-20 08:38:02','2022-06-20 18:09:46',0,1),(4,'Bánh Ngọt Vị Dâu','87797548-25fe-4a0d-aca6-93d4d6e64f4f.jpg',19000,20,'2022-06-20 10:32:14','2022-06-20 18:10:35',0,1),(5,'Bánh Ngọt Nhiều Vị','67d2821b-f609-433a-96fb-5cc140811018.jpg',10000,20,'2022-06-20 18:11:07',NULL,0,1),(6,'Bánh Rau Câu Hoa Hồng','ce6a31de-cecd-4ffb-8587-c8a55943f0cb.jpg',300000,10,'2022-06-20 18:13:02',NULL,0,2),(7,'Bánh Rau Câu Sinh Nhật','61a66485-b6b9-4d6f-aca4-94abadef711b.jpg',400000,10,'2022-06-20 18:17:29',NULL,0,2),(8,'Bánh Rau Câu  Ngon Lành','190f164a-db28-4d28-8531-d12cd6e0417e.jpg',20,56,'2022-06-21 10:22:45',NULL,0,2),(9,'Bánh Rau Câu Nhân ','ed146e26-da09-4710-ad3d-22930da1d536.jpg',23333,32,'2022-06-21 10:26:57',NULL,0,2),(10,'Bánh Rau Câu Nhân Chocolate','0d263b92-ba68-4a7f-86cc-819ed732e351.jpg',232323,32,'2022-06-21 10:27:58',NULL,0,2),(11,'Bánh Rau Câu Nhân Bậy Bạ','a1e9b1e0-1e17-4e72-aa34-1d0800553fdc.jpg',33232,23,'2022-06-21 10:28:21',NULL,1,2),(12,'Bánh Rau Câu Hạnh Phúc','8716ed67-070d-44cb-b3b0-44d171a9801e.jpg',323232,12,'2022-06-21 10:28:46',NULL,0,2),(13,'Bánh Rau Câu Nhân ABC','422c953a-1e87-466e-8f6e-d268e94741fd.jpg',23456,34,'2022-06-21 10:29:06',NULL,0,2),(21,'Bánh Kem Sinh Nhật','0d3381fb-5472-42f2-84f7-126779abe4f6.jpg',39,912,'2022-06-22 09:45:03',NULL,0,3),(22,'Bánh Sinh Nhật Nhiều Kem','7988bce3-4ae6-43d3-99a0-f6d03f9ae062.jpg',400,24,'2022-06-22 10:00:06',NULL,0,3),(23,'jifelopit@mailinator.com','1806c4ec-3fe4-40cb-ab8d-6e15c963c415',735,398,'2022-06-22 15:32:32',NULL,0,4),(24,'runyhuzal@mailinator.com','eae4e3a7-a106-4c71-bbb9-5329692b9d12',311,358,'2022-06-22 15:33:42',NULL,0,3),(25,'tabu@mailinator.com','1ca38882-a069-4a56-b09a-77af1934fbc1',536,77,'2022-06-22 15:37:42',NULL,0,4),(26,'byjucoxe@mailinator.com','3758c483-a6ff-4782-80e6-07901655353f.jpg',102,210,'2022-06-22 15:39:08',NULL,0,1),(27,'Bánh ','6cf9c20e-4647-42cb-8677-3b627eb60c7a.jpg',35435435,12,'2022-06-22 15:48:47',NULL,0,1),(28,'Bánh Flan Nhiều Trứng','4cfa8c82-588a-4a80-89a0-afcf381f90ef.jpg',10000,50,'2022-06-24 17:12:13',NULL,0,4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-24 17:29:21
