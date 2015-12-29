CREATE DATABASE  IF NOT EXISTS `allstar` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `allstar`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: allstar
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `aform`
--

DROP TABLE IF EXISTS `aform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aform` (
  `ano` int(11) NOT NULL AUTO_INCREMENT,
  `m_id` varchar(45) DEFAULT NULL,
  `cre_dt` date DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `introduce` text,
  PRIMARY KEY (`ano`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aform`
--

LOCK TABLES `aform` WRITE;
/*!40000 ALTER TABLE `aform` DISABLE KEYS */;
/*!40000 ALTER TABLE `aform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `bno` int(11) NOT NULL AUTO_INCREMENT,
  `bevent` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` text,
  `cre_dt` date DEFAULT NULL,
  `bphoto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition`
--

DROP TABLE IF EXISTS `competition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `competition` (
  `cno` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) DEFAULT NULL,
  `cevent` varchar(45) DEFAULT NULL,
  `venue` varchar(45) DEFAULT NULL,
  `cname` varchar(45) DEFAULT NULL,
  `team_num` int(11) DEFAULT NULL,
  `join_cost` int(11) DEFAULT NULL,
  `sdt` date DEFAULT NULL,
  `edt` date DEFAULT NULL,
  `rsdt` date DEFAULT NULL,
  `redt` date DEFAULT NULL,
  `content` text,
  `cphoto` varchar(255) DEFAULT NULL,
  `host_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition`
--

LOCK TABLES `competition` WRITE;
/*!40000 ALTER TABLE `competition` DISABLE KEYS */;
/*!40000 ALTER TABLE `competition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `freeboard`
--

DROP TABLE IF EXISTS `freeboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `freeboard` (
  `fno` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `content` text,
  `cre_dt` date DEFAULT NULL,
  `views` int(11) DEFAULT '0',
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `freeboard`
--

LOCK TABLES `freeboard` WRITE;
/*!40000 ALTER TABLE `freeboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `freeboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `joincomp`
--

DROP TABLE IF EXISTS `joincomp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `joincomp` (
  `tno` int(11) DEFAULT NULL,
  `cno` int(11) DEFAULT NULL,
  KEY `tno` (`tno`),
  KEY `cno` (`cno`),
  CONSTRAINT `joincomp_ibfk_1` FOREIGN KEY (`tno`) REFERENCES `team` (`tno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `joincomp_ibfk_2` FOREIGN KEY (`cno`) REFERENCES `competition` (`cno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `joincomp`
--

LOCK TABLES `joincomp` WRITE;
/*!40000 ALTER TABLE `joincomp` DISABLE KEYS */;
/*!40000 ALTER TABLE `joincomp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jointeam`
--

DROP TABLE IF EXISTS `jointeam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jointeam` (
  `tno` int(11) DEFAULT NULL,
  `m_id` varchar(50) DEFAULT NULL,
  KEY `tno` (`tno`),
  KEY `m_id` (`m_id`),
  CONSTRAINT `jointeam_ibfk_1` FOREIGN KEY (`tno`) REFERENCES `team` (`tno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `jointeam_ibfk_2` FOREIGN KEY (`m_id`) REFERENCES `member` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jointeam`
--

LOCK TABLES `jointeam` WRITE;
/*!40000 ALTER TABLE `jointeam` DISABLE KEYS */;
/*!40000 ALTER TABLE `jointeam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `id` varchar(50) NOT NULL,
  `pwd` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `mphoto` varchar(255) DEFAULT NULL,
  `zipcode` varchar(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `mevent` varchar(50) DEFAULT NULL,
  `introduce` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply` (
  `rno` int(11) NOT NULL AUTO_INCREMENT,
  `repler` varchar(45) DEFAULT NULL,
  `rephoto` varchar(255) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`rno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `tname` varchar(45) DEFAULT NULL,
  `captain` varchar(45) DEFAULT NULL,
  `tno` int(11) NOT NULL AUTO_INCREMENT,
  `tevent` varchar(45) DEFAULT NULL,
  `hometown` varchar(45) DEFAULT NULL,
  `total_num` int(11) DEFAULT NULL,
  `win` int(11) DEFAULT NULL,
  `draw` int(11) DEFAULT NULL,
  `lose` int(11) DEFAULT NULL,
  `join_num` int(11) DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `meet_day` varchar(45) DEFAULT NULL,
  `introduce` text,
  `tphoto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-29 11:38:44
