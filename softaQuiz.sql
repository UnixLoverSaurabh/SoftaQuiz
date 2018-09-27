-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 26, 2018 at 09:30 AM
-- Server version: 10.1.32-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `softaQuiz`
--

-- --------------------------------------------------------

--
-- Table structure for table `multipleCorr`
--

CREATE TABLE `multipleCorr` (
  `anotherId` int(11) NOT NULL,
  `newId` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `option1` varchar(255) NOT NULL,
  `option2` varchar(255) NOT NULL,
  `option3` varchar(255) NOT NULL,
  `option4` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `singleCorr`
--

CREATE TABLE `singleCorr` (
  `anotherId` int(11) NOT NULL,
  `newId` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `option1` varchar(255) NOT NULL,
  `option2` varchar(255) NOT NULL,
  `option3` varchar(255) NOT NULL,
  `option4` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `topics`
--

CREATE TABLE `topics` (
  `newId` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `topicDate` varchar(255) NOT NULL,
  `single` int(11) NOT NULL,
  `singleTime` int(11) NOT NULL,
  `multiple` int(11) NOT NULL,
  `multipleTime` int(11) NOT NULL,
  `trueFalse` int(11) NOT NULL,
  `trueFalseTime` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--

--
-- Table structure for table `trueFalseCorr`
--

CREATE TABLE `trueFalseCorr` (
  `anotherId` int(11) NOT NULL,
  `newId` int(11) NOT NULL,
  `question` varchar(255) NOT NULL,
  `option1` varchar(255) NOT NULL,
  `option2` varchar(255) NOT NULL,
  `option3` varchar(255) NOT NULL,
  `option4` varchar(255) NOT NULL,
  `answer` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL DEFAULT 'Enter your name',
  `gender` varchar(255) NOT NULL DEFAULT 'N/A',
  `dob` varchar(255) NOT NULL DEFAULT 'Date Of Birth',
  `contact` varchar(255) NOT NULL DEFAULT '+91',
  `qualification` varchar(255) NOT NULL DEFAULT 'I am a',
  `college` varchar(255) NOT NULL DEFAULT 'MNNIT Allahabad',
  `optional` varchar(255) NOT NULL DEFAULT 'N/A'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Indexes for table `multipleCorr`
--
ALTER TABLE `multipleCorr`
  ADD PRIMARY KEY (`anotherId`);

--
-- Indexes for table `singleCorr`
--
ALTER TABLE `singleCorr`
  ADD PRIMARY KEY (`anotherId`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`username`,`subject`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indexes for table `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`newId`),
  ADD UNIQUE KEY `topics` (`id`,`topic`);

--
-- Indexes for table `trueFalseCorr`
--
ALTER TABLE `trueFalseCorr`
  ADD PRIMARY KEY (`anotherId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `multipleCorr`
--
ALTER TABLE `multipleCorr`
  MODIFY `anotherId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `singleCorr`
--
ALTER TABLE `singleCorr`
  MODIFY `anotherId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `topics`
--
ALTER TABLE `topics`
  MODIFY `newId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `trueFalseCorr`
--
ALTER TABLE `trueFalseCorr`
  MODIFY `anotherId` int(11) NOT NULL AUTO_INCREMENT;
--

--
-- Constraints for table `teachers`
--
ALTER TABLE `teachers`
  ADD CONSTRAINT `FOREIGN KEY` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
