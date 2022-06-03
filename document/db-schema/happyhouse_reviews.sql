CREATE DATABASE  IF NOT EXISTS `happyhouse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `happyhouse`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: happyhouse
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_no` int NOT NULL AUTO_INCREMENT,
  `store_no` int DEFAULT NULL,
  `writer_email` varchar(50) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`review_no`),
  KEY `store_no_FK` (`store_no`),
  KEY `writer_email_FK` (`writer_email`),
  CONSTRAINT `store_no_FK` FOREIGN KEY (`store_no`) REFERENCES `sport` (`no`) ON DELETE SET NULL,
  CONSTRAINT `writer_email_FK` FOREIGN KEY (`writer_email`) REFERENCES `users` (`email`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,3157392,NULL,'제목','내용','2022-05-24 07:27:17'),(3,3157392,NULL,'ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹ','ㅁㄴㅇㄻㄴㅇㄻㄴㅇㄹ','2022-05-24 16:22:39'),(6,3240799,NULL,'asdfadsf','ㅁㄴㅇㄻㄴㅇㄹ','2022-05-25 01:22:04'),(7,3240799,'11@11.11','asdfadsf','ㅁㄴㅇㄻㄴㅇㄹ','2022-05-25 01:22:22'),(8,3240799,'emaill','asdfadsf','ㅁㄴㅇㄻㄴㅇㄹ','2022-05-25 01:22:31'),(10,11753777,'kimam@naver.com','이건 좀;','3대 400이하 출입금지는 좀;;;','2022-05-25 18:12:53'),(12,7836404,'kimam@naver.com','amazing interior','I can do this all day!','2022-05-25 18:13:02'),(13,10360459,'e@mail.c','좋네요...','너무 좋네요....','2022-05-25 18:13:02'),(14,7836404,'ssafy@ssafy.com','시설이 완전 쌔거에요','기구들이 새거라서 너무 좋아요, 인테리어도 좋았어요!!','2022-05-25 18:13:02'),(15,4614060,'hunu_cho@naver.com','골프장이 너무 좋아요','스크린이 커서 좋았어요! :)','2022-05-25 18:13:06'),(19,4614060,'11@11.11','123','123','2022-05-26 10:44:01'),(21,17745549,'hunu_cho@naver.com','드디어 완성','완성됬네요','2022-05-26 10:52:41'),(22,17745549,'hunu_cho@naver.com','이제 추가 삭제도 된다','진짜? ㄹㅇ','2022-05-26 11:46:53');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-26 22:29:49
