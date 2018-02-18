CREATE DATABASE  IF NOT EXISTS `hostel_system` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `hostel_system`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hostel_system
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
  `banned_reason_id` int(11) NOT NULL COMMENT 'ID причины блокировки',
  `lang_name` char(2) NOT NULL COMMENT 'язык',
  `reason_type` varchar(45) NOT NULL COMMENT 'причина',
  PRIMARY KEY (`banned_reason_id`,`lang_name`),
  KEY `fk_ban_reason_lang1_idx` (`lang_name`),
  CONSTRAINT `fk_ban_reason_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `start_of_ban` date NOT NULL COMMENT 'Дата блокировки',
  PRIMARY KEY (`id_reason`,`user_id`),
  UNIQUE KEY `user_id_user_UNIQUE` (`user_id`),
  KEY `fk_banned_user_user1_idx` (`user_id`),
  CONSTRAINT `banned_reason_fk` FOREIGN KEY (`id_reason`) REFERENCES `ban_reason` (`banned_reason_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_banned_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица заблокированных пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_user`
--

LOCK TABLES `banned_user` WRITE;
/*!40000 ALTER TABLE `banned_user` DISABLE KEYS */;
INSERT INTO `banned_user` VALUES (1,8,'2018-01-25'),(1,11,'2017-11-30'),(1,12,'2018-10-14'),(1,30,'2018-02-04'),(1,36,'2018-02-05'),(2,6,'2018-02-07'),(2,20,'2018-02-04'),(2,37,'2018-02-21'),(3,5,'2018-02-12'),(3,21,'2018-01-04'),(4,14,'2018-02-09'),(4,22,'2016-03-31');
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
  CONSTRAINT `fk_city_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию о городах на нескольких языках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'en','Minsk','Belarus'),(1,'ru','Минск','Беларусь'),(2,'en','Grodno','Belarus'),(2,'ru','Гродно','Беларусь'),(3,'en','Moscow','Russia'),(3,'ru','Москва','Россия'),(4,'en','St. Petersburg','Russia'),(4,'ru','Санкт-Петербург','Россия'),(5,'en','Kiev','Ukraine'),(5,'ru','Киев','Украина'),(6,'en','Tallinn','Estonia'),(6,'ru','Таллин','Эстония'),(7,'en','Budapest','Hungary'),(7,'ru','Будапешт','Венгрия'),(8,'en','Paris','France'),(8,'ru','Париж','Франция'),(9,'en','Prague','Czech Republic'),(9,'ru','Прага','Чехия'),(10,'en','Vilnius','Lithuania'),(10,'ru','Вильнюс','Литва'),(11,'en','Riga','Latvia'),(11,'ru','Рига','Латвия'),(12,'en','Cracow','Poland'),(12,'ru','Краков','Польша'),(13,'en','Warsaw','Poland'),(13,'ru','Варшава','Польша'),(14,'en','Lviv','Ukraine'),(14,'ru','Львов','Украина'),(15,'en','Krasnodar','Russia'),(15,'ru','Краснодар','Россия'),(16,'en','New York City','USA'),(16,'ru','Нью-Йорк','США'),(17,'en','Chicago','USA'),(17,'ru','Чикаго','США'),(18,'en','San Francisco','USA'),(18,'ru','Сан-Франциско','США'),(19,'en','Berlin','Germany'),(19,'ru','Берлин','Германия'),(20,'en','Munich','Germany'),(20,'ru','Мюнхен','Германия'),(21,'en','Hamburg','Germany'),(21,'ru','Гамбург','Германия'),(22,'en','Rome','Italy'),(22,'ru','Рим','Италия'),(23,'en','Milan','Italy'),(23,'ru','Милан','Италия'),(24,'en','Palermo','Italy'),(24,'ru','Палермо','Италия'),(25,'en','Madrid','Spain'),(25,'ru','Мадрид','Испания'),(26,'en','Barcelona','Spain'),(26,'ru','Барселона','Испания'),(27,'en','Seville','Spain'),(27,'ru','Севилья','Испания'),(28,'en','Beijing','China'),(28,'ru','Пекин','Китай'),(29,'en','Shanghai','China'),(29,'ru','Шанхай','Китай'),(30,'en','Tianjin','China'),(30,'ru','Тяньцзинь','Китай'),(31,'en','Tokyo','Japan'),(31,'ru','Токио','Япония'),(32,'en','Kawasaki','Japan'),(32,'ru','Кавасаки','Япония'),(33,'en','Nagoya','Japan'),(33,'ru','Нагоя','Япония'),(34,'en','Cairo','Egypt'),(34,'ru','Каир','Египет'),(35,'en','Alexandria','Egypt'),(35,'ru','Александрия','Египет'),(36,'en','Port Said','Egypt'),(36,'ru','Порт-Саид','Египет'),(37,'en','London','England'),(37,'ru','Лондон','Англия'),(38,'en','Liverpool','England'),(38,'ru','Ливерпуль','Англия'),(39,'en','Manchester','England'),(39,'ru','Манчестер','Англия'),(40,'en','Toronto','Canada'),(40,'ru','Торонто','Канада');
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
  `hostel_email` varchar(45) NOT NULL COMMENT 'email хостела',
  PRIMARY KEY (`id_hostel`),
  UNIQUE KEY `id_hostel_UNIQUE` (`id_hostel`),
  KEY `hostel_city__fk` (`hostel_city`),
  CONSTRAINT `hostel_city__fk` FOREIGN KEY (`hostel_city`) REFERENCES `city` (`id_city`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию о комнатах в хостелах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES (1,1,'booking',10,'oladushka@gmail.com'),(2,1,'payment',20,'moj_kraj@gmail.com'),(3,2,'booking',25,'zakopatycha@gmail.com'),(4,3,'booking',15,'my_home@gmail.com'),(5,4,'booking',16,'sweet_day@gmail.com'),(6,5,'booking',30,'matrena@gmail.com'),(7,5,'booking',30,'doroga@gmail.com'),(8,6,'payment',15,'good_night@gmail.com'),(9,7,'payment',14,'sleepy_hallow@gmail.com'),(10,8,'payment',14,'hallow@gmail.com'),(11,9,'booking',19,'paradise@gmail.com'),(12,9,'payment',23,'winter.is.coming@gmail.com'),(13,10,'booking',20,'plutonium@gmail.com'),(14,10,'payment',24,'xenon@gmail.com'),(15,11,'booking',15,'station@gmail.com'),(16,12,'booking',10,'sleepy.cat@gmail.com'),(17,13,'booking',12,'Hill_Valley@gmail.com'),(18,14,'booking',12,'lisya.nora@gmail.com'),(19,14,'booking',15,'alissssa@mail.ru'),(20,15,'payment',20,'kin_dza_dza@gmail.com'),(21,16,'booking',15,'hostel_21@gmail.com'),(22,16,'payment',15,'hostel_22@gmail.com'),(23,16,'booking',15,'hostel_23@gmail.com'),(24,17,'booking',10,'hostel_24@gmail.com'),(25,18,'booking',10,'hostel_25@gmail.com'),(26,19,'booking',20,'hostel_26@gmail.com'),(27,19,'booking',23,'hostel_27@gmail.com'),(28,19,'payment',23,'hostel_28@gmail.com'),(29,20,'payment',25,'hostel_29@gmail.com'),(30,21,'payment',25,'hostel_30@gmail.com'),(31,21,'booking',25,'hostel_31@gmail.com'),(32,22,'payment',27,'hostel_32@gmail.com'),(33,23,'booking',28,'hostel_33@gmail.com'),(34,24,'payment',30,'hostel_34@gmail.com'),(35,25,'booking',33,'hostel_35@gmail.com'),(36,26,'booking',35,'hostel_36@gmail.com'),(37,27,'booking',60,'hostel_37@gmail.com'),(38,28,'booking',10,'hostel_38@gmail.com'),(39,29,'booking',10,'hostel_39@gmail.com'),(40,30,'payment',25,'hostel_40@gmail.com'),(41,33,'booking',20,'hostel_41@gmail.com'),(42,31,'payment',20,'hostel_42@gmail.com'),(43,32,'booking',20,'hostel_43@gmail.com'),(44,34,'booking',40,'hostel_44@gmail.com'),(45,35,'booking',15,'hostel_45@gmail.com'),(46,36,'booking',15,'hostel_46@gmail.com'),(47,37,'booking',15,'hostel_47@gmail.com'),(48,38,'payment',20,'hostel_48@gmail.com'),(49,39,'payment',20,'hostel_49@gmail.com'),(50,40,'payment',25,'hostel_50@gmail.com'),(51,40,'booking',25,'hostel_51@gmail.com'),(52,40,'payment',20,'hostel_52@gmail.com'),(53,25,'booking',17,'hostel_53@gmail.com'),(54,28,'payment',18,'hostel_54@gmail.com'),(55,31,'booking',30,'hostel_55@gmail.com'),(56,32,'booking',30,'hostel_56@gmail.com'),(57,33,'booking',15,'hostel_57@gmail.com'),(58,37,'booking',15,'hostel_58@gmail.com'),(59,37,'payment',15,'hostel_59@gmail.com'),(60,37,'payment',15,'hostel_60@gmail.com');
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
INSERT INTO `hostel_local` VALUES (1,'en','Oladushka','Gorkogo st., 24'),(1,'ru','Оладушка','Горького, 4'),(2,'en','Moj kraj','Minskaya st., 7'),(2,'ru','Мой край','Минская, 7'),(3,'en','Za Kopatycha','Sovetskaya st., 8'),(3,'ru','За Копатыча','Советская, 8'),(4,'en','My home','Bombom st., 7'),(4,'ru','My home','Bombom st., 7'),(5,'en','Sweet Day','Armax st., 9'),(5,'ru','Sweet Day','Armax st., 9'),(6,'en','Matrena','Nikakaya st., 76'),(6,'ru','Матрёна','Никакая, 76'),(7,'en','Na doroge','Almaznaya st., 90'),(7,'ru','На дороге','Алмазная, 90'),(8,'en','Good night','Krasp st., 64'),(8,'ru','Good night','Krasp st., 64'),(9,'en','Sleepy Hallow','Hallow st., 54'),(9,'ru','Sleepy Hallow','Hallow st., 54'),(10,'en','Hallow','Arkol st., 49'),(10,'ru','Hallow','Arkol st., 49'),(11,'en','Paradise','Kulman st., 97'),(11,'ru','Paradise','Kulman st., 97'),(12,'en','Winter is Coming','Westeros st., 47'),(12,'ru','Winter is Coming','Westeros st., 47'),(13,'en','Plutonium','Uran st., 97'),(13,'ru','Plutonium','Uran st., 97'),(14,'en','Xenon','Chem st., 44'),(14,'ru','Xenon','Chem st., 44'),(15,'en','Station','Air st, 43'),(15,'ru','Station','Air st, 43'),(16,'en','Sleepy cat','Purr-Purr st., 4'),(16,'ru','Sleepy cat','Purr-Purr st., 4'),(17,'en','Hill Valley','Kokly st., 99'),(17,'ru','Hill Valley','Kokly st., 99'),(18,'en','Lisya nora','Tverskaya st., 5'),(18,'ru','Лисья нора','Тверская, 5'),(19,'en','Alisa','Originalnaya st., 66'),(19,'ru','Алиса','Оригинальная, 66'),(20,'en','Kin-dza-dza','Gruzinskaya st., 64'),(20,'ru','Кин-дза-дза','Грузинская, 64'),(21,'en','Twenty One','Central st., 25'),(21,'ru','Twenty One','Central st., 25'),(22,'en','Twenty Two','Hallow st., 12'),(22,'ru','Twenty Two','Hallow st., 12'),(23,'en','Twenty Three','Arkol st., 34'),(23,'ru','Twenty Three','Arkol st., 34'),(24,'en','Miracle','Wondersteet st., 1'),(24,'ru','Miracle','Wondersteet st.'),(25,'en','Onotoly','Central st., 1'),(25,'ru','Onotoly','Central st., 1'),(26,'en','Anthology','Armani st., 101'),(26,'ru','Anthology','Armani st., 101'),(27,'en','Kawaii','Queue st., 2'),(27,'ru','Kawaii','Queue st., 2'),(28,'en','Samson','Samilio st., 27'),(28,'ru','Samson','Samilio st., 27'),(29,'en','Batman','Gotham st., 7'),(29,'ru','Batman','Gotham st., 7'),(30,'en','Lisa','Central st., 13'),(30,'ru','Lisa','Central st., 13'),(31,'en','Superman','Smallville st., 1'),(31,'ru','Superman','Smallville st., 1'),(32,'en','Kirill','Crop st., 25'),(32,'ru','Kirill','Crop st., 25'),(33,'en','Katalina','Kat st., 27'),(33,'ru','Katalina','Kat st., 27'),(34,'en','Chinar','Sheer st., 25'),(34,'ru','Chinar','Sheer st.'),(35,'en','Twilight','Forks st., 25'),(35,'ru','Twilight','Forks st., 25'),(36,'en','Garry','Usual st., 13'),(36,'ru','Garry','Usual st., 13'),(37,'en','Alissa','Crop st., 1'),(37,'ru','Alissa','Crop st., 1'),(38,'en','Olass','Starlak st., 12'),(38,'ru','Olass','Starlak st., 12'),(39,'en','Oazis','Alopha st., 12'),(39,'ru','Oazis','Alopha st., 12'),(40,'en','Ironman','New Street st., 13'),(40,'ru','Ironman','New Street st.'),(41,'en','Qwentio','Central st., 4'),(41,'ru','Qwentio','Central st., 4'),(42,'en','Silensio','Lake st., 16'),(42,'ru','Silensio','Lake st., 16'),(43,'en','Croptown','Cursio st., 17'),(43,'ru','Croptown','Cursio st.'),(44,'en','Karagang','Usual st., 10'),(44,'ru','Karagang','Usual st., 10'),(45,'en','Unagi','Kopkop st., 10'),(45,'ru','Unagi','Kopkop st.'),(46,'en','Olola','Sorry st., 12'),(46,'ru','Olola','Sorry st., 12'),(47,'en','Lilibo','Tired st., 16'),(47,'ru','Lilibo','Tired st., 16'),(48,'en','Hostelio','Hasteli st., 15'),(48,'ru','Hostelio','Hasteli st., 15'),(49,'en','Chirs','Chris st., 10'),(49,'ru','Chirs','Chris st., 10'),(50,'en','Coco','Crop st., 26'),(50,'ru','Coco','Crop st., 26'),(51,'en','Untak','Takiun st., 16'),(51,'ru','Untak','Takiun st., 16'),(52,'en','Takida','Central st., 25'),(52,'ru','Takida','Central st., 25'),(53,'en','Europe','Camping st., 13a'),(53,'ru','Europe','Camping st., 13a'),(54,'en','Catarsi','Usual st., 6'),(54,'ru','Catarsi','Usual st., 6'),(55,'en','Century','Fox st., 1'),(55,'ru','Century','Fox st., 1'),(56,'en','Chemistry','Mendel st., 1'),(56,'ru','Chemistry','Mendel st., 1'),(57,'en','Tokyo','Usual st., 12'),(57,'ru','Tokyo','Usual st., 12'),(58,'en','Kashim','Central st., 20'),(58,'ru','Kashim','Central st., 20'),(59,'en','Ursula','Ocean st., 26'),(59,'ru','Ursula','Ocean st., 26'),(60,'en','Snow White','Central st., 25'),(60,'ru','Snow White','Central st., 25');
/*!40000 ALTER TABLE `hostel_local` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hostel_room`
--

DROP TABLE IF EXISTS `hostel_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hostel_room` (
  `hostel_id` int(10) unsigned NOT NULL COMMENT 'ID хостела',
  `room` int(10) unsigned NOT NULL COMMENT 'номер комнаты',
  PRIMARY KEY (`hostel_id`,`room`),
  CONSTRAINT `hostel_room_hostel_id_hostel_fk` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id_hostel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая соответствтие хостелов и их номеров';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel_room`
--

LOCK TABLES `hostel_room` WRITE;
/*!40000 ALTER TABLE `hostel_room` DISABLE KEYS */;
INSERT INTO `hostel_room` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(2,2),(2,3),(2,4),(2,5),(3,1),(3,2),(3,3),(3,4),(3,5),(4,1),(4,2),(4,3),(4,4),(4,5),(5,1),(5,2),(5,3),(5,4),(5,5),(6,1),(6,2),(6,3),(6,4),(6,5),(7,1),(7,2),(7,3),(7,4),(7,5),(8,1),(8,2),(8,3),(8,4),(8,5),(9,1),(9,2),(9,3),(9,4),(9,5),(10,1),(10,2),(10,3),(10,4),(10,5),(11,1),(11,2),(11,3),(11,4),(11,5),(12,1),(12,2),(12,3),(12,4),(12,5),(13,1),(13,2),(13,3),(13,4),(13,5),(14,1),(14,2),(14,3),(14,4),(14,5),(15,1),(15,2),(15,3),(15,4),(15,5),(16,1),(16,2),(16,3),(16,4),(16,5),(17,1),(17,2),(17,3),(17,4),(17,5),(18,1),(18,2),(18,3),(18,4),(18,5),(19,1),(19,2),(19,3),(19,4),(19,5),(20,1),(20,2),(20,3),(20,4),(20,5),(21,1),(21,2),(21,3),(21,4),(21,5),(21,6),(21,7),(22,1),(22,2),(22,3),(22,4),(22,5),(22,6),(22,7),(23,1),(23,2),(23,3),(23,4),(23,5),(23,6),(23,7),(24,1),(24,2),(24,3),(24,4),(24,5),(24,6),(24,7),(25,1),(25,2),(25,3),(25,4),(25,5),(25,6),(25,7),(25,8),(26,1),(26,2),(26,3),(26,4),(26,5),(26,6),(26,7),(26,8),(26,9),(26,10),(27,1),(27,2),(27,3),(27,4),(27,5),(27,6),(27,7),(28,1),(28,2),(28,3),(28,4),(28,5),(28,6),(28,7),(28,8),(29,1),(29,2),(29,3),(29,4),(29,5),(29,6),(29,7),(30,1),(30,2),(30,3),(30,4),(30,5),(30,6),(30,7),(30,8),(30,9),(30,10),(31,1),(31,2),(31,3),(31,4),(31,5),(31,6),(31,7),(32,1),(32,2),(32,3),(32,4),(32,5),(32,6),(32,7),(32,8),(32,9),(33,1),(33,2),(33,3),(33,4),(33,5),(33,6),(33,7),(33,8),(33,9),(33,10),(34,1),(34,2),(34,3),(34,4),(34,5),(34,6),(34,7),(35,1),(35,2),(35,3),(35,4),(35,5),(35,6),(35,7),(35,8),(36,1),(36,2),(36,3),(36,4),(36,5),(36,6),(36,7),(36,8),(37,1),(37,2),(37,3),(37,4),(37,5),(37,6),(37,7),(37,8),(38,1),(38,2),(38,3),(38,4),(38,5),(39,1),(39,2),(39,3),(39,4),(39,5),(39,6),(39,7),(39,8),(40,1),(40,2),(40,3),(40,4),(40,5),(40,6),(40,7),(41,1),(41,2),(41,3),(41,4),(41,5),(41,6),(41,7),(41,8),(42,1),(42,2),(42,3),(42,4),(42,5),(42,6),(42,7),(42,8),(43,1),(43,2),(43,3),(43,4),(43,5),(43,6),(43,7),(43,8),(43,9),(44,1),(44,2),(44,3),(44,4),(44,5),(44,6),(44,7),(44,8),(44,9),(44,10),(44,11),(44,12),(45,1),(45,2),(45,3),(45,4),(45,5),(45,6),(45,7),(45,8),(45,9),(46,1),(46,2),(46,3),(46,4),(46,5),(46,6),(47,1),(47,2),(47,3),(47,4),(47,5),(47,6),(47,7),(47,8),(47,9),(47,10),(48,1),(48,2),(48,3),(48,4),(48,5),(48,6),(48,7),(49,1),(49,2),(49,3),(49,4),(49,5),(49,6),(49,7),(50,1),(50,2),(50,3),(50,4),(50,5),(50,6),(50,7),(50,8),(50,9),(51,1),(51,2),(51,3),(51,4),(51,5),(51,6),(51,7),(51,8),(51,9),(51,10),(52,1),(52,2),(52,3),(52,4),(52,5),(52,6),(52,7),(52,8),(53,1),(53,2),(53,3),(53,4),(53,5),(53,6),(53,7),(54,1),(54,2),(54,3),(54,4),(54,5),(54,6),(54,7),(54,8),(55,1),(55,2),(55,3),(55,4),(55,5),(55,6),(55,7),(55,8),(56,1),(56,2),(56,3),(56,4),(56,5),(56,6),(56,7),(56,8),(57,1),(57,2),(57,3),(57,4),(57,5),(57,6),(57,7),(57,8),(58,1),(58,2),(58,3),(58,4),(58,5),(58,6),(58,7),(59,1),(59,2),(59,3),(59,4),(59,5),(59,6),(59,7),(59,8),(60,1),(60,2),(60,3),(60,4),(60,5),(60,6),(60,7),(60,8);
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
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT 'ID пользователя',
  `hostel_id` int(10) unsigned NOT NULL COMMENT 'ID хостела',
  `type` enum('booking','payment') NOT NULL COMMENT 'тип заявки: бронь, оплата',
  `room_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT 'количество номеров',
  `days_number` int(11) unsigned NOT NULL DEFAULT '1' COMMENT 'количество дней',
  `cost` int(11) unsigned NOT NULL COMMENT 'стоимость (с учётом скидки)',
  `status` enum('processing','approved','denied','deleted') NOT NULL DEFAULT 'processing' COMMENT 'статус заявки: в процессе, одобрено, отказано',
  `reservation_date` date NOT NULL COMMENT 'дата бронирования',
  PRIMARY KEY (`id_request`,`user_id`,`hostel_id`),
  UNIQUE KEY `id_request_UNIQUE` (`id_request`),
  KEY `fk_request_hostel1_idx` (`hostel_id`),
  KEY `fk_request_user1_idx` (`user_id`),
  CONSTRAINT `fk_request_hostel1` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id_hostel`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию завках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,3,1,'payment',3,1,30,'approved','2017-01-22'),(2,5,2,'payment',1,1,20,'approved','2017-01-22'),(3,6,3,'booking',1,1,25,'approved','2017-02-22'),(4,7,4,'booking',1,2,30,'approved','2017-03-21'),(5,8,1,'payment',1,5,50,'denied','2017-04-21'),(6,10,1,'payment',1,5,50,'approved','2017-04-22'),(7,4,8,'payment',1,4,60,'approved','2017-05-22'),(8,10,9,'payment',1,3,42,'approved','2017-05-21'),(9,7,15,'booking',5,7,500,'denied','2017-05-22'),(10,10,14,'payment',1,1,23,'approved','2017-07-21'),(11,10,8,'payment',1,2,28,'denied','2017-07-22'),(12,9,8,'payment',4,1,60,'approved','2017-07-22'),(13,10,14,'payment',1,1,20,'approved','2017-09-22'),(14,9,1,'booking',3,1,30,'denied','2017-09-22'),(15,10,1,'payment',1,2,17,'approved','2017-10-20'),(16,9,9,'payment',2,2,56,'denied','2017-11-22'),(17,10,10,'payment',1,3,30,'approved','2017-11-22'),(18,3,11,'booking',2,2,73,'approved','2017-12-22'),(19,2,2,'payment',1,1,25,'approved','2018-01-29'),(20,3,2,'payment',1,1,18,'approved','2017-12-30'),(21,2,1,'payment',1,1,5,'deleted','2018-01-30'),(22,17,1,'payment',1,1,0,'denied','2018-02-01'),(23,17,1,'booking',1,1,10,'approved','2018-01-31'),(24,10,2,'payment',2,2,56,'deleted','2018-02-02'),(25,10,2,'payment',5,10,700,'deleted','2018-02-04'),(26,10,2,'payment',1,1,14,'deleted','2018-02-03'),(27,10,7,'payment',2,2,84,'approved','2018-02-17'),(28,10,1,'payment',1,1,7,'denied','2018-01-29'),(29,18,1,'booking',1,1,5,'approved','2018-02-07'),(30,18,2,'payment',1,1,10,'denied','2018-02-16'),(31,45,2,'booking',1,20,380,'approved','2018-02-09'),(32,46,1,'booking',4,1,40,'denied','2018-02-23'),(33,47,1,'booking',1,1,10,'deleted','2018-02-08'),(34,48,1,'booking',1,1,10,'deleted','2018-02-09'),(35,49,1,'booking',2,2,40,'deleted','2018-02-10'),(36,10,2,'payment',2,2,56,'approved','2018-02-18'),(37,49,11,'booking',1,1,19,'denied','2018-02-28'),(55,49,1,'booking',1,1,10,'approved','2018-02-15'),(56,49,1,'booking',1,1,10,'approved','2018-02-28'),(57,49,15,'booking',1,1,15,'denied','2018-02-15'),(58,49,7,'booking',1,1,30,'approved','2018-03-08'),(59,49,15,'booking',1,1,15,'approved','2018-02-19');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request_room`
--

DROP TABLE IF EXISTS `request_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request_room` (
  `hostel` int(10) unsigned NOT NULL COMMENT 'ID хостела',
  `room` int(10) unsigned NOT NULL COMMENT 'номер комнаты',
  `begin_date` date NOT NULL COMMENT 'дата начала брони',
  `end_date` date NOT NULL COMMENT 'дата конца брони',
  KEY `request_room_hostel_room_hostel_id_room_fk` (`hostel`,`room`),
  CONSTRAINT `request_room_hostel_room_hostel_id_room_fk` FOREIGN KEY (`hostel`, `room`) REFERENCES `hostel_room` (`hostel_id`, `room`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица забронированных номеров в хостелах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request_room`
--

LOCK TABLES `request_room` WRITE;
/*!40000 ALTER TABLE `request_room` DISABLE KEYS */;
INSERT INTO `request_room` VALUES (1,1,'2018-01-24','2018-01-25'),(1,2,'2018-01-23','2018-01-27'),(1,3,'2018-01-28','2018-01-29'),(1,1,'2018-01-26','2018-01-30'),(1,1,'2018-01-31','2018-02-01'),(2,1,'2018-01-01','2018-01-01'),(2,2,'2018-01-01','2018-01-13'),(2,3,'2018-01-01','2018-01-06'),(2,4,'2018-01-01','2018-01-03'),(2,5,'2018-01-01','2018-01-09'),(2,1,'2018-01-03','2018-01-04'),(1,3,'2018-01-30','2018-01-31'),(1,4,'2018-02-01','2018-02-02'),(1,1,'2018-01-31','2018-02-01'),(2,2,'2018-02-02','2018-02-04'),(2,4,'2018-02-02','2018-02-04'),(2,1,'2018-02-04','2018-02-14'),(2,3,'2018-02-04','2018-02-14'),(2,2,'2018-02-04','2018-02-14'),(2,5,'2018-02-04','2018-02-14'),(2,4,'2018-02-04','2018-02-14'),(2,4,'2018-02-03','2018-02-04'),(7,4,'2018-02-17','2018-02-19'),(7,1,'2018-02-17','2018-02-19'),(1,4,'2018-01-29','2018-01-30'),(1,2,'2018-02-07','2018-02-08'),(2,2,'2018-02-16','2018-02-17'),(2,4,'2018-02-09','2018-03-01'),(1,4,'2018-02-23','2018-02-24'),(1,5,'2018-02-23','2018-02-24'),(1,3,'2018-02-23','2018-02-24'),(1,2,'2018-02-23','2018-02-24'),(1,1,'2018-02-08','2018-02-09'),(1,2,'2018-02-09','2018-02-10'),(1,5,'2018-02-10','2018-02-12'),(1,3,'2018-02-10','2018-02-12'),(2,4,'2018-02-18','2018-02-20'),(2,1,'2018-02-18','2018-02-20'),(11,2,'2018-02-28','2018-03-01'),(1,3,'2018-02-22','2018-02-23'),(1,4,'2018-02-28','2018-03-01'),(2,2,'2018-02-27','2018-03-01'),(2,3,'2018-02-27','2018-03-01'),(1,5,'2018-03-14','2018-03-15'),(1,2,'2018-02-15','2018-02-16'),(1,1,'2018-02-28','2018-03-01'),(15,4,'2018-02-15','2018-02-16'),(7,2,'2018-03-08','2018-03-09'),(15,3,'2018-02-19','2018-02-20'),(13,1,'2018-02-22','2018-02-24'),(13,5,'2018-02-22','2018-02-24'),(4,3,'2018-02-23','2018-02-25'),(3,1,'2018-02-04','2018-02-14'),(4,1,'2018-02-03','2018-02-04'),(5,1,'2018-02-17','2018-02-19'),(6,1,'2018-02-17','2018-02-19'),(7,1,'2018-01-29','2018-01-30'),(7,2,'2018-02-07','2018-02-08'),(8,3,'2018-02-16','2018-02-17'),(9,1,'2018-02-09','2018-03-01'),(10,1,'2018-02-23','2018-02-24'),(11,1,'2018-02-23','2018-02-24'),(12,1,'2018-02-23','2018-02-24'),(12,1,'2018-02-23','2018-02-24'),(13,1,'2018-02-08','2018-02-09'),(14,5,'2018-02-09','2018-02-10'),(14,5,'2018-02-10','2018-02-12'),(15,5,'2018-02-10','2018-02-12'),(16,4,'2018-02-18','2018-02-20'),(17,4,'2018-02-18','2018-02-20'),(18,3,'2018-02-28','2018-03-01'),(19,2,'2018-02-22','2018-02-23'),(20,2,'2018-02-28','2018-03-01'),(21,1,'2018-02-27','2018-03-01'),(22,1,'2018-02-27','2018-03-01'),(23,1,'2018-03-14','2018-03-15'),(24,1,'2018-02-15','2018-02-16'),(25,2,'2018-02-28','2018-03-01'),(26,3,'2018-02-04','2018-02-14'),(27,3,'2018-02-03','2018-02-04'),(28,3,'2018-02-17','2018-02-19'),(29,3,'2018-02-17','2018-02-19'),(30,4,'2018-01-29','2018-01-30'),(31,4,'2018-02-07','2018-02-08'),(32,5,'2018-02-16','2018-02-17'),(33,5,'2018-02-09','2018-03-01'),(34,5,'2018-02-23','2018-02-24'),(35,1,'2018-02-23','2018-02-24'),(36,2,'2018-02-23','2018-02-24'),(37,3,'2018-02-23','2018-02-24'),(38,4,'2018-02-08','2018-02-09'),(39,4,'2018-02-09','2018-02-10'),(40,3,'2018-02-10','2018-02-12'),(41,3,'2018-02-10','2018-02-12'),(42,3,'2018-02-18','2018-02-20'),(43,2,'2018-02-18','2018-02-20'),(44,5,'2018-02-28','2018-03-01'),(45,1,'2018-02-22','2018-02-23'),(46,2,'2018-02-28','2018-03-01'),(47,3,'2018-02-27','2018-03-01'),(48,1,'2018-02-27','2018-03-01'),(49,2,'2018-03-14','2018-03-15'),(50,3,'2018-02-15','2018-02-16'),(51,5,'2018-02-28','2018-03-01'),(52,1,'2018-02-22','2018-02-23'),(53,2,'2018-02-28','2018-03-01'),(54,3,'2018-02-27','2018-03-01'),(55,4,'2018-02-27','2018-03-01'),(56,2,'2018-03-14','2018-03-15'),(57,5,'2018-02-15','2018-02-16'),(58,3,'2018-02-28','2018-03-01'),(59,2,'2018-02-08','2018-02-09'),(60,1,'2018-02-09','2018-02-10'),(1,1,'2018-02-25','2018-02-27'),(1,2,'2018-02-25','2018-02-27'),(1,3,'2018-02-25','2018-02-27'),(1,4,'2018-02-25','2018-02-27'),(1,5,'2018-02-24','2018-02-25'),(1,5,'2018-02-26','2018-02-27'),(1,5,'2018-02-28','2018-03-04');
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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='Таблица пользователей, содержащая основную информацию о них';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@tut.by','21232f297a57a5a743894a0e4a801fc3','Администратор','Администратор','',50,'admin',0,'c4ca4238a0b923820dcc509a6f75849b'),(2,'maribo','maribo@gmail.com','0634274ff93437b3561bfbf32bb79e1a','Мария','Богумильчик','Сергеевна',50,'user',105,'c81e728d9d4c2f636f067f89cc14862c'),(3,'alexx','alexander@mail.ru','fa5ffebe019d6e9fec589bfe2bafe6fe','Alex','Doe','',10,'user',200,'eccbc87e4b5ce2fe28308fd9f2a7baf3'),(4,'alexey','shushun@gmail.com','92b84a202ebeff6c6ebfc7a2da8990bf','Alexei','Smith','Li',5,'user',210,'a87ff679a2f3e71d9181a67b7542122c'),(5,'pikabu','pikabu@pikabu.ru','db58ecff7870106a18724533e3cb4fb8','Ка','Пи','Бу',0,'banned',23,'e4da3b7fbbce2345d7772b0674a318d5'),(6,'l4ever','l4ever@pikabu.ru','970e1d7fe2aa854a4836d363809edac1','Самый','Тот','Чел',3,'banned',64,'1679091c5a880faf6fb5e6087eb1b2dc'),(7,'ashotik','ashort@gogo.kz','e87c26e11586d37dff5788fdfc95b793','Ашот','Рахмед','Мухаммедыч',5,'user',98,'8f14e45fceea167a5a36dedd4bea2543'),(8,'hostel_king','kkkkking@mail.ru','082d7d5ed6ed878fba712826eb6d0ff6','Сергей','Веремеев','Петрович',10,'banned',534,'c9f0f895fb98ab9159f51fd0297e236d'),(9,'kukareku','petuh@gmail.com','d5cbfe9ff07fef1ecc93861ce5dd4f3b','Павел','Синий','Михайлович',25,'user',254,'45c48cce2e2d7fbdea1afc51c7c6ad26'),(10,'pierce.k','petrova.k@gmail.com','d3209a0fd9a5bfca7592135b9b3eab96','Катерина','Петрова','',30,'user',2194,'d3d9446802a44259755d38e6d163e820'),(11,'tempuser','abasbc@gmail.com','1aed32a49704ffbad7a0f6a342ae41e6','Ivan','Ivanov','Ivanovich',0,'banned',0,'6512bd43d9caa6e02c990b0a82652dca'),(12,'orlando','orlick@mail.ru','fef7185dd6e69d561bc286f3fe6e391f','Orlando','Smith','',0,'banned',210,'c20ad4d76fe97759aa27a0c99bff6710'),(13,'michael','mich123@rambler.ru','0acf4539a14b3aa27deeb4cbdf6e989f','Mich','Ael','',0,'user',0,'c51ce410c124a10e0db5e4b97fc2af39'),(14,'Chimichanga','chimichanga@gmail.com','617a7cb7c9ceedca2ed65fcda51e5b43','Chi','Chang','Mich',0,'banned',100,'aab3238922bcc25a6f606eb525ffdc56'),(15,'ponds','ponds@gmail.com','3c06b3c17c953973544ec27b006594e0','Romelia','Pond','',0,'user',0,'9bf31c7ff062936a96d3c8bd1f8f2ff3'),(16,'lolec','lolec@kek.com','72b539c7a3e2391537445b2b876e2320','Lola','Arson','Li',0,'user',52,'c74d97b01eae257e44aa9d5bade97baf'),(17,'Alice','alice_in@wonderland.com','4cecaff2b30bbe75ce7322109164cfb5','Alisa','Wonder','',0,'user',0,'70efdf2ec9b086079795c442636b55fb'),(18,'doctor_who','doctor_who@gallifrey.un','b22fc010ca02e1ea5ecebf2b2180eeb0','Doctor','Who','',50,'user',15,'6f4922f45568161a8cdf4ad2299f6d23'),(19,'kitty_cat','wiskas@olop.com','c91ccd06be2c046c4e9aab764bdeb5f3','Katherine','Pierce','',25,'user',62,'1f0e3dad99908345f7439f8ffabdffc4'),(20,'fantasy','world4@unit.com','7a1c07ff60f9c07ffe8da34ecbf4edc2','Anna','Bolleyn','',0,'banned',120,'98f13708210194c475687be6106a3b84'),(21,'sophie','sophi777@mail.ru','6988ec3aba1eaddf2435141bf10487ca','Sophie','Clarke','',15,'banned',145,'3c59dc048e8850243be8079a5c74d079'),(22,'karry6','narniya@mail.ru','144fca3dd9d5cd2456367f65c205c2a7','Karry','Willson','',0,'banned',0,'b6d767d2f8ed5d21a44b0e5886680cb9'),(23,'winchester','d.winchester@devil.com','c7e7571e424d5ef643057d73c9b01ba9','Dean','Winchester','',5,'user',124,'37693cfc748049e45d87b8c7d8b9aacd'),(24,'holliwell','charmed@young.tv','b790c2bebe09a5c37f7fd976003f10bf','Phoebe','Halliwell','',0,'user',0,'1ff1de774005f8da13f42943881c655f'),(25,'power3','alisss@young.tv','6110b9c5906e15e06867562c18fd1c46','Prudence','Halliwell','',5,'user',145,'8e296a067a37563370ded05f5a3bf3ec'),(26,'witches','dark.magic@gmail.com','9a504f72b9c3deea3581441b984959bb','Александра','Иванова','Артёмовна',5,'user',489,'4e732ced3463d06de0ca9a15b6153677'),(27,'game_of_hostel','daenerys@got.com','74e4530c6c99d8cdc349f47977375e6c','Денис','Петров','Ильич',0,'user',5,'02e74f10e0327ad868d138f2b4fdd6f0'),(29,'darkness','dark_swan@gmail.com','9b7d722b58370498cd39104b2d971978','Саша','Галыгина','Петровна',0,'user',0,'6ea9ab1baa0efb9e19094440c317e21b'),(30,'lolita','lolita_mil@yandex.ru','1ac0d72d238c86b2029c8419ba2574a2','Лолита','Милая','',5,'banned',13,'34173cb38f07f89ddbebc2ac9128303f'),(31,'spiderman','spiderman@gmail.mu','9f05aa4202e4ce8d6a72511dc735cce9','Peter','Parker','',5,'user',0,'c16a5320fa475530d9583c34fd356ef5'),(32,'batman','batman@gmail.dc','ec0e2603172c73a8b644bb9456c1ff6e','Bruce','Wayne','',0,'user',13,'6364d3f0f495b6ab9dcf8d3b5c6e0b01'),(34,'superman','superman@gmail.dc','84d961568a65073a3bcf0eb216b2a576','Clark','Kent','',5,'user',0,'e369853df766fa44e1ed0ff613f563bd'),(35,'twilight','sunshine@gmail.com','f567cb85556dea700061be2153b0da0d','Алина','Хлыст','Аркадьевна',5,'user',0,'1c383cd30b7c298ab50293adfecb7b18'),(36,'anna1978','anna1978@gmail.com','494e091d28dfe3aad47538d17d67de7b','Анна','Казиева','Ивановна',0,'banned',0,'19ca14e7ea6328a42e0eb13d585e4c22'),(37,'pikachu','pikachu@gmail.com','9ce44f88a25272b6d9cbb430ebbcfcf1','Николай','Пикач','Алексндрович',0,'banned',12,'a5bfc9e07964f8dddeb95fc584cd965d'),(39,'ironman','ironman@gmail.mu','0d94d92e3dc096f64213a5b34fa9d098','Tony','Stark','',5,'user',11,'d67d8ab4f4c10bf22aa353e27879133c'),(40,'harry.pot','still.alive@gmail.com','75d08de1199c6c1b31aeb87f9f4528c8','Harry','Potter','',0,'user',0,'d645920e395fedad7bbbed0eca3fe2e0'),(42,'nikasan','nikasan@gmail.com','69b48e78776e159812f0a2ccc88b3751','Nika','Sunny','Li',0,'user',0,'a1d0c6e83f027327d8461063f4ac58a6'),(43,'barbarson','barbara@gmail.com','b5515ecb5a50c7e4ede93f1899b91786','Barbara','Iron','',0,'user',0,'17e62166fc8586dfa4d1bc0e1742c08b'),(44,'jakki.milk','jakki.milk@iiit.ru','4942bff2c33cb3f450fa4f755469ee22','Jack','Milk','',0,'user',25,'f7177163c833dff4b38fc8d2872f1ec6'),(45,'login','login@login.cop','5f4dcc3b5aa765d61d8327deb882cf99','Temp','Temp','Temp',5,'user',0,'6c8349cc7260ae62e3b1396831a8398f'),(49,'vanity','vanity@gmail.us','20351d6633b8ce0dd8f3dc7d881a7638','Ivan','Cox','',0,'user',3,'f457c545a9ded88f18ecee47145a72c0'),(50,'alexxxxander','alexxx@gmail.com','5bbdbf8aebfea41254e74f2e5170770c','Александр','Иванов','',0,'user',0,'c0c7c76d30bd3dcaefc96f40275bdc0a');
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

-- Dump completed on 2018-02-19  0:37:25
