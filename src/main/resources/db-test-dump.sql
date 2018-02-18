CREATE DATABASE  IF NOT EXISTS `hostel_system_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hostel_system_test`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hostel_system_test
-- ------------------------------------------------------
-- Server version	5.5.57

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
-- Table structure for table `ban_reason`
--

DROP TABLE IF EXISTS `ban_reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ban_reason` (
  `banned_reason_id` int(11) NOT NULL,
  `lang_name` char(2) NOT NULL,
  `reason_type` varchar(45) NOT NULL,
  PRIMARY KEY (`banned_reason_id`,`lang_name`),
  KEY `fk_ban_reason_lang1_idx` (`lang_name`),
  CONSTRAINT `fk_ban_reason_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица причин блокироваки пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban_reason`
--

LOCK TABLES `ban_reason` WRITE;
/*!40000 ALTER TABLE `ban_reason` DISABLE KEYS */;
INSERT INTO `ban_reason` VALUES (1,'en','Cheating'),(1,'ru','Мошенничество'),(2,'en','Insult'),(2,'ru','Оскорбление'),(3,'en','Residence rules violation'),(3,'ru','Нарушение правил проживания'),(4,'en','Information theft'),(4,'ru','Хищение информации');
/*!40000 ALTER TABLE `ban_reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banned_user`
--

DROP TABLE IF EXISTS `banned_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banned_user` (
  `id_reason` int(11) NOT NULL COMMENT 'ID причины блокировки',
  `user_id` int(11) NOT NULL COMMENT 'ID пользователя',
  `start_of_ban` date NOT NULL,
  PRIMARY KEY (`id_reason`,`user_id`),
  UNIQUE KEY `user_id_user_UNIQUE` (`user_id`),
  KEY `fk_banned_user_user1_idx` (`user_id`),
  CONSTRAINT `banned_reason_fk` FOREIGN KEY (`id_reason`) REFERENCES `ban_reason` (`banned_reason_id`) ON DELETE CASCADE,
  CONSTRAINT `fk_banned_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица заблокированных пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_user`
--

LOCK TABLES `banned_user` WRITE;
/*!40000 ALTER TABLE `banned_user` DISABLE KEYS */;
INSERT INTO `banned_user` VALUES (2,2,'2018-02-01'),(3,4,'2017-02-08');
/*!40000 ALTER TABLE `banned_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id_city` int(10) unsigned NOT NULL COMMENT 'ID города',
  `lang_name` char(2) NOT NULL COMMENT 'язык',
  `city` varchar(45) NOT NULL COMMENT 'город',
  `country` varchar(45) NOT NULL COMMENT 'страна',
  PRIMARY KEY (`id_city`,`lang_name`),
  UNIQUE KEY `city_UNIQUE` (`city`),
  KEY `fk_city_lang1_idx` (`lang_name`),
  CONSTRAINT `fk_city_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию о городах на нескольких языках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'en','Minsk','Belarus'),(1,'ru','Минск','Беларусь'),(2,'en','Grodno','Belarus'),(2,'ru','Гродно','Беларусь'),(3,'en','Moscow','Russia'),(3,'ru','Москва','Россия'),(4,'en','St. Petersburg','Russia'),(4,'ru','Санкт-Петербург','Россия'),(5,'en','Kiev','Ukraine'),(5,'ru','Киев','Украина');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel`
--

DROP TABLE IF EXISTS `hostel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel` (
  `id_hostel` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID хостела',
  `hostel_city` int(10) unsigned NOT NULL COMMENT 'ID города',
  `booking_possibility` enum('booking','payment') NOT NULL COMMENT 'возможность бронирования',
  `room_cost` int(11) unsigned NOT NULL COMMENT 'стоимость номера',
  `hostel_email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_hostel`),
  UNIQUE KEY `id_hostel_UNIQUE` (`id_hostel`),
  KEY `hostel_city__fk` (`hostel_city`),
  CONSTRAINT `hostel_city__fk` FOREIGN KEY (`hostel_city`) REFERENCES `city` (`id_city`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию о комнатах в хостелах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES (1,1,'payment',10,'oladushka@gmail.com'),(2,1,'booking',20,'moj_kraj@gmail.com'),(3,2,'booking',25,'zakopatycha@gmail.com'),(4,3,'booking',15,'my_home@gmail.com'),(5,4,'booking',16,'sweet_day@gmail.com');
/*!40000 ALTER TABLE `hostel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel_local`
--

DROP TABLE IF EXISTS `hostel_local`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel_local` (
  `hostel_id` int(10) unsigned NOT NULL COMMENT 'ID хостела',
  `lang_name` char(2) NOT NULL COMMENT 'язык',
  `hostel_name` varchar(45) NOT NULL COMMENT 'название хостела',
  `address` varchar(100) DEFAULT NULL COMMENT 'адрес',
  PRIMARY KEY (`hostel_id`,`lang_name`),
  KEY `fk_hostel_local_lang1_idx` (`lang_name`),
  KEY `fk_hostel_local_hostel1_idx` (`hostel_id`),
  CONSTRAINT `fk_hostel_local_hostel1` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id_hostel`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_hostel_local_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая названия доступных хостелов на разных языках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel_local`
--

LOCK TABLES `hostel_local` WRITE;
/*!40000 ALTER TABLE `hostel_local` DISABLE KEYS */;
INSERT INTO `hostel_local` VALUES (1,'en','Oladushka','Gorkogo st., 24'),(1,'ru','Оладушка','Горького, 4'),(2,'en','Moj kraj','Minskaya st., 7'),(2,'ru','Мой край','Минская, 7'),(3,'en','Za Kopatycha','Sovetskaya st., 8'),(3,'ru','За Копатыча','Советская, 8'),(4,'en','My home','Bombom st., 7'),(4,'ru','My home','Bombom st., 7'),(5,'en','Sweet Day','Armax st., 9'),(5,'ru','Sweet Day','Armax st., 9');
/*!40000 ALTER TABLE `hostel_local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel_room`
--

DROP TABLE IF EXISTS `hostel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel_room` (
  `hostel_id` int(10) unsigned NOT NULL,
  `room` int(10) unsigned NOT NULL,
  PRIMARY KEY (`hostel_id`,`room`),
  CONSTRAINT `hostel_room_hostel_id_hostel_fk` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id_hostel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel_room`
--

LOCK TABLES `hostel_room` WRITE;
/*!40000 ALTER TABLE `hostel_room` DISABLE KEYS */;
INSERT INTO `hostel_room` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(2,2),(2,3),(2,4),(2,5),(3,1),(3,2),(3,3),(3,4),(3,5),(4,1),(4,2),(4,3),(4,4),(4,5),(5,1),(5,2),(5,3),(5,4),(5,5);
/*!40000 ALTER TABLE `hostel_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lang`
--

DROP TABLE IF EXISTS `lang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lang` (
  `name` char(2) NOT NULL COMMENT 'язык (сокращение)',
  `language` varchar(20) NOT NULL COMMENT 'язык',
  PRIMARY KEY (`name`),
  UNIQUE KEY `lang_name_UNIQUE` (`name`),
  UNIQUE KEY `language_UNIQUE` (`language`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица языков';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lang`
--

LOCK TABLES `lang` WRITE;
/*!40000 ALTER TABLE `lang` DISABLE KEYS */;
INSERT INTO `lang` VALUES ('en','English'),('ru','Russian');
/*!40000 ALTER TABLE `lang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `id_request` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID заявки',
  `user_id` int(11) NOT NULL COMMENT 'ID пользователя',
  `hostel_id` int(10) unsigned NOT NULL COMMENT 'ID хостела',
  `type` enum('booking','payment') NOT NULL COMMENT 'тип заявки: бронь, оплата',
  `room_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT 'количество номеров',
  `days_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT 'количество дней',
  `cost` int(11) unsigned NOT NULL COMMENT 'стоимость (с учётом скидки)',
  `status` enum('processing','approved','denied','deleted') NOT NULL DEFAULT 'processing' COMMENT 'статус заявки: в процессе, одобрено, отказано',
  `reservation_date` date NOT NULL,
  PRIMARY KEY (`id_request`,`user_id`,`hostel_id`),
  UNIQUE KEY `id_request_UNIQUE` (`id_request`),
  KEY `fk_request_hostel1_idx` (`hostel_id`),
  KEY `fk_request_user1_idx` (`user_id`),
  CONSTRAINT `request_hostel_id_hostel_fk` FOREIGN KEY (`hostel_id`) REFERENCES `hostel_system`.`hostel` (`id_hostel`) ON UPDATE CASCADE,
  CONSTRAINT `request_user_id_user_fk` FOREIGN KEY (`user_id`) REFERENCES `hostel_system`.`user` (`id_user`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию завках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,5,1,'payment',3,1,30,'deleted','2017-01-22'),(2,2,2,'payment',1,1,20,'approved','2017-01-22'),(3,2,3,'booking',1,1,25,'approved','2017-02-22'),(4,5,4,'booking',1,2,30,'denied','2017-03-21'),(5,5,1,'payment',1,5,50,'denied','2017-04-21'),(6,3,3,'booking',1,1,20,'processing','2018-02-25');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_room`
--

DROP TABLE IF EXISTS `request_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_room` (
  `hostel` int(10) unsigned NOT NULL,
  `room` int(10) unsigned NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  KEY `request_room_hostel_room_hostel_id_room_fk` (`hostel`,`room`),
  CONSTRAINT `request_room_hostel_room_hostel_id_room_fk` FOREIGN KEY (`hostel`, `room`) REFERENCES `hostel_room` (`hostel_id`, `room`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_room`
--

LOCK TABLES `request_room` WRITE;
/*!40000 ALTER TABLE `request_room` DISABLE KEYS */;
INSERT INTO `request_room` VALUES (1,1,'2018-01-24','2018-01-25'),(1,2,'2018-01-23','2018-01-27'),(1,3,'2018-01-28','2018-01-29'),(1,1,'2018-01-26','2018-01-30'),(1,1,'2018-01-31','2018-02-01'),(2,1,'2018-01-01','2018-01-01'),(2,2,'2018-01-01','2018-01-13'),(2,3,'2018-01-01','2018-01-06'),(2,4,'2018-01-01','2018-01-03'),(2,5,'2018-01-01','2018-01-09'),(2,1,'2018-01-03','2018-01-04'),(1,3,'2018-01-30','2018-01-31'),(1,4,'2018-02-01','2018-02-02'),(1,1,'2018-01-31','2018-02-01'),(2,2,'2018-02-02','2018-02-04'),(2,4,'2018-02-02','2018-02-04'),(2,1,'2018-02-04','2018-02-14'),(2,3,'2018-02-04','2018-02-14'),(2,2,'2018-02-04','2018-02-14'),(2,5,'2018-02-04','2018-02-14'),(2,4,'2018-02-04','2018-02-14'),(2,4,'2018-02-03','2018-02-04'),(1,4,'2018-01-29','2018-01-30');
/*!40000 ALTER TABLE `request_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID пользователя',
  `username` varchar(20) NOT NULL COMMENT 'логин',
  `email` varchar(45) NOT NULL COMMENT 'электронная почта',
  `password` varchar(32) NOT NULL COMMENT 'пароль',
  `name` varchar(45) NOT NULL COMMENT 'имя',
  `surname` varchar(45) NOT NULL COMMENT 'фамилия',
  `lastname` varchar(45) DEFAULT NULL COMMENT 'отчество',
  `discount` int(11) NOT NULL DEFAULT '0' COMMENT 'скидка',
  `status` enum('admin','user','banned') NOT NULL DEFAULT 'user' COMMENT 'статус: администратор, пользователь, заблокированный пользователь',
  `balance` int(11) NOT NULL DEFAULT '0' COMMENT 'баланс',
  `account` varchar(32) NOT NULL DEFAULT 'unknown',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='Таблица пользователей, содержащая основную информацию о них';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@tut.by','21232f297a57a5a743894a0e4a801fc3','Иван','Иванов','Иванович',50,'admin',7,'c4ca4238a0b923820dcc509a6f75849b'),(2,'alexx','alexander@mail.ru','fa5ffebe019d6e9fec589bfe2bafe6fe','Alex','Doe','',10,'banned',200,'eccbc87e4b5ce2fe28308fd9f2a7baf3'),(3,'ashotik','ashort@gogo.kz','e87c26e11586d37dff5788fdfc95b793','Ашот','Рахмед','Мухаммедыч',5,'user',98,'8f14e45fceea167a5a36dedd4bea2543'),(4,'kukareku','petuh@gmail.com','d5cbfe9ff07fef1ecc93861ce5dd4f3b','Павел','Синий','Михайлович',25,'banned',254,'45c48cce2e2d7fbdea1afc51c7c6ad26'),(5,'pierce.k','petrova.k@gmail.com','d3209a0fd9a5bfca7592135b9b3eab96','Катерина','Петрова','',30,'user',2409,'d3d9446802a44259755d38e6d163e820');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-19  0:37:34
