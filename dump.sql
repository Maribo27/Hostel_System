-- MySQL dump 10.13  Distrib 5.5.57, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: hostel_system
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
  CONSTRAINT `fk_ban_reason_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `start_of_ban` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `end_of_ban` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_reason`,`user_id`),
  UNIQUE KEY `user_id_user_UNIQUE` (`user_id`),
  KEY `fk_banned_user_user1_idx` (`user_id`),
  CONSTRAINT `banned_reason_fk` FOREIGN KEY (`id_reason`) REFERENCES `ban_reason` (`banned_reason_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_banned_user_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица заблокированных пользователей';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banned_user`
--

LOCK TABLES `banned_user` WRITE;
/*!40000 ALTER TABLE `banned_user` DISABLE KEYS */;
INSERT INTO `banned_user` VALUES (2,6,'2017-09-24 14:10:15','2018-01-21 05:15:39'),(2,11,'2018-01-15 09:14:40','2018-01-19 00:14:26'),(3,5,'2015-06-21 17:14:45','2018-12-21 14:14:56');
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
  CONSTRAINT `fk_city_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию о городах на нескольких языках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'en','Minsk','Belarus'),(1,'ru','Минск','Беларусь'),(2,'en','Grodno','Belarus'),(2,'ru','Гродно','Беларусь'),(3,'en','Moscow','Russia'),(3,'ru','Москва','Россия'),(4,'en','St. Petersburg','Russia'),(4,'ru','Санкт-Петербург','Россия'),(5,'en','Kiev','Ukraine'),(5,'ru','Киев','Украина'),(6,'en','Tallinn','Estonia'),(6,'ru','Таллин','Эстония'),(7,'en','Budapest','Hungary'),(7,'ru','Будапешт','Венгрия'),(8,'en','Paris','France'),(8,'ru','Париж','Франция'),(9,'en','Prague','Czech Republic'),(9,'ru','Прага','Чехия'),(10,'en','Vilnius','Lithuania'),(10,'ru','Вильнюс','Литва'),(11,'en','Riga','Latvia'),(11,'ru','Рига','Латвия'),(12,'en','Cracow','Poland'),(12,'ru','Краков','Польша'),(13,'en','Warsaw','Poland'),(13,'ru','Варшава','Польша'),(14,'en','Lviv','Ukraine'),(14,'ru','Львов','Украина'),(15,'en','Krasnodar','Russia'),(15,'ru','Краснодар','Россия');
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
  `free_room_number` int(11) NOT NULL DEFAULT '0' COMMENT 'количество свободных номеров',
  `booking_possibility` tinyint(1) NOT NULL COMMENT 'возможность бронирования',
  `room_cost` int(11) unsigned NOT NULL COMMENT 'стоимость номера',
  `hostel_email` varchar(45) NOT NULL,
  PRIMARY KEY (`id_hostel`),
  UNIQUE KEY `id_hostel_UNIQUE` (`id_hostel`),
  KEY `hostel_city__fk` (`hostel_city`),
  CONSTRAINT `hostel_city__fk` FOREIGN KEY (`hostel_city`) REFERENCES `city` (`id_city`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию о комнатах в хостелах';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel`
--

LOCK TABLES `hostel` WRITE;
/*!40000 ALTER TABLE `hostel` DISABLE KEYS */;
INSERT INTO `hostel` VALUES (1,1,9,1,10,'oladushka@gmail.com'),(2,1,25,0,20,'moj_kraj@gmail.com'),(3,2,20,1,25,'zakopatycha@gmail.com'),(4,3,0,1,15,'my_home@gmail.com'),(5,4,0,1,16,'sweet_day@gmail.com'),(6,5,8,1,30,'matrena@gmail.com'),(7,5,9,1,30,'doroga@gmail.com'),(8,6,13,0,15,'good_night@gmail.com'),(9,7,14,0,14,'sleepy_hallow@gmail.com'),(10,8,2,0,14,'hallow@gmail.com'),(11,9,15,1,19,'paradise@gmail.com'),(12,9,25,0,23,'winter.is.coming@gmail.com'),(13,10,28,1,20,'plutonium@gmail.com'),(14,10,10,0,24,'xenon@gmail.com'),(15,11,2,1,15,'station@gmail.com'),(16,12,11,1,10,'sleepy.cat@gmail.com'),(17,13,5,1,12,'Hill_Valley@gmail.com'),(18,14,5,1,12,'lisya.nora@gmail.com'),(19,14,0,1,15,'alissssa@mail.ru'),(20,15,0,0,20,'kin_dza_dza@gmail.com');
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
  CONSTRAINT `fk_hostel_local_hostel1` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id_hostel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_hostel_local_lang1` FOREIGN KEY (`lang_name`) REFERENCES `lang` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая названия доступных хостелов на разных языках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hostel_local`
--

LOCK TABLES `hostel_local` WRITE;
/*!40000 ALTER TABLE `hostel_local` DISABLE KEYS */;
INSERT INTO `hostel_local` VALUES (1,'en','Oladushka','Gorkogo st., 24'),(1,'ru','Оладушка','Горького, 4'),(2,'en','Moj kraj','Minskaya st., 7'),(2,'ru','Мой край','Минская, 7'),(3,'en','Za Kopatycha','Sovetskaya st., 8'),(3,'ru','За Копатыча','Советская, 8'),(4,'en','My home','Bombom st., 7'),(5,'en','Sweet Day','Armax st., 9'),(6,'en','Matrena','Nikakaya st., 76'),(6,'ru','Матрёна','Никакая, 76'),(7,'en','Na doroge','Almaznaya st., 90'),(7,'ru','На дороге','Алмазная, 90'),(8,'en','Good night','Krasp st., 64'),(9,'en','Sleepy Hallow','Hallow st., 54'),(10,'en','Hallow','Arkol st., 49'),(11,'en','Paradise','Kulman st., 97'),(12,'en','Winter is Coming','Westeros st., 47'),(13,'en','Plutonium','Uran st., 97'),(14,'en','Xenon','Chem st., 44'),(15,'en','Station','Air st, 43'),(16,'en','Sleepy cat','Purr-Purr st., 4'),(17,'en','Hill Valley','Kokly st., 99'),(18,'en','Lisya nora','Tverskaya st., 5'),(18,'ru','Лисья нора','Тверская, 5'),(19,'en','Alisa','Originalnaya st., 66'),(19,'ru','Алиса','Оригинальная, 66'),(20,'en','Kin-dza-dza','Gruzinskaya st., 64'),(20,'ru','Кин-дза-дза','Грузинская, 64');
/*!40000 ALTER TABLE `hostel_local` ENABLE KEYS */;
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
  `status` enum('in process','approved','denied') NOT NULL DEFAULT 'in process' COMMENT 'статус заявки: в процессе, одобрено, отказано',
  `reservation_date` date NOT NULL,
  PRIMARY KEY (`id_request`,`user_id`,`hostel_id`),
  UNIQUE KEY `id_request_UNIQUE` (`id_request`),
  KEY `fk_request_hostel1_idx` (`hostel_id`),
  KEY `fk_request_user1_idx` (`user_id`),
  CONSTRAINT `fk_request_hostel1` FOREIGN KEY (`hostel_id`) REFERENCES `hostel` (`id_hostel`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='Таблица, содержащая информацию завках';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,3,1,'payment',3,1,30,'approved','2017-01-22'),(2,5,2,'payment',1,1,20,'approved','2017-01-22'),(3,6,3,'booking',1,1,25,'approved','2017-02-22'),(4,7,4,'booking',1,2,30,'approved','2017-03-21'),(5,8,1,'payment',1,5,50,'denied','2017-04-21'),(6,10,1,'payment',1,5,50,'approved','2017-04-22'),(7,4,8,'payment',1,4,60,'approved','2017-05-22'),(8,10,9,'payment',1,3,42,'approved','2017-05-21'),(9,7,15,'booking',5,7,500,'denied','2017-05-22'),(10,10,14,'payment',1,1,23,'approved','2017-07-21'),(11,10,8,'payment',1,2,28,'denied','2017-07-22'),(12,9,8,'payment',4,1,60,'approved','2017-07-22'),(13,10,14,'payment',1,1,20,'approved','2017-09-22'),(14,9,1,'booking',3,1,30,'denied','2017-09-22'),(15,10,1,'payment',1,2,17,'approved','2017-10-20'),(16,9,9,'payment',2,2,56,'denied','2017-11-22'),(17,10,10,'payment',1,3,30,'approved','2017-11-22'),(18,3,11,'booking',2,2,73,'in process','2017-12-22'),(19,2,1,'booking',3,1,30,'denied','2017-12-22'),(20,3,2,'payment',1,1,18,'in process','2017-12-30');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
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
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `id_user_UNIQUE` (`id_user`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='Таблица пользователей, содержащая основную информацию о них';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin@tut.by','21232f297a57a5a743894a0e4a801fc3','Иван','Иванов','Иванович',50,'admin',0),(2,'maribo','maribo@gmail.com','0634274ff93437b3561bfbf32bb79e1a','Мария','Богумильчик','Сергеевна',50,'user',100),(3,'alexx','alexander@mail.ru','fa5ffebe019d6e9fec589bfe2bafe6fe','Alex','Doe','',10,'user',200),(4,'alexey','shushun@gmail.com','92b84a202ebeff6c6ebfc7a2da8990bf','Alexei','Smith','Li',0,'user',210),(5,'pikabu','pikabu@pikabu.ru','db58ecff7870106a18724533e3cb4fb8','Ка','Пи','Бу',0,'banned',235),(6,'l4ever','l4ever@pikabu.ru','970e1d7fe2aa854a4836d363809edac1','Самый','Тот','Чел',3,'banned',64),(7,'ashotik','ashort@gogo.kz','e87c26e11586d37dff5788fdfc95b793','Ашот','Рахмед','Мухаммедыч',5,'user',98),(8,'hostel_king','kkkkking@mail.ru','082d7d5ed6ed878fba712826eb6d0ff6','Сергей','Веремеев','Петрович',10,'user',534),(9,'kukareku','petuh@gmail.com','d5cbfe9ff07fef1ecc93861ce5dd4f3b','Павел','Синий','Михайлович',25,'user',254),(10,'pierce.k','petrova.k@gmail.com','d3209a0fd9a5bfca7592135b9b3eab96','Катерина','Петрова','',30,'user',2500),(11,'tempuser','abasbc@gmail.com','1aed32a49704ffbad7a0f6a342ae41e6','Ivan','Ivanov','Ivanovich',0,'banned',0),(12,'','','d41d8cd98f00b204e9800998ecf8427e','','','',0,'user',0);
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

-- Dump completed on 2018-01-19 22:07:54
