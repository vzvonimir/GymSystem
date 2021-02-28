-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2021 at 06:15 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gymsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `coach`
--

CREATE TABLE `coach` (
  `ID` int(11) NOT NULL,
  `Ime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Prezime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Vrsta` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Visina` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Tezina` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IDKorisnika` int(11) DEFAULT NULL,
  `IDUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `coach`
--

INSERT INTO `coach` (`ID`, `Ime`, `Prezime`, `Vrsta`, `Visina`, `Tezina`, `IDKorisnika`, `IDUser`) VALUES
(4, 'Marko', 'Maric', 'Crossfit', '187cm', '86kg', NULL, 25),
(5, 'Martina', 'Jukic', 'Fitnes', '172cm', '56kg', NULL, 29);

-- --------------------------------------------------------

--
-- Table structure for table `datum`
--

CREATE TABLE `datum` (
  `ID` int(11) NOT NULL,
  `datumuplate` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `period` varchar(30) COLLATE utf8mb4_unicode_ci NOT NULL,
  `IDUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `datum`
--

INSERT INTO `datum` (`ID`, `datumuplate`, `period`, `IDUser`) VALUES
(1, '2021-02-28', '1 mjesec', 1),
(2, '2021-02-09', '1 mjesec', 26),
(4, '2021-03-13', '2 mjeseca', 25),
(5, '2021-03-01', '1 mjesec', 28),
(6, '2021-03-04', '1 mjesec', 29);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID` int(11) NOT NULL,
  `Ime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Prezime` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Visina` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Tezina` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IDUser` int(11) DEFAULT NULL,
  `IDTrener` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ID`, `Ime`, `Prezime`, `Visina`, `Tezina`, `IDUser`, `IDTrener`) VALUES
(3, 'Ivan', 'Ivic12', '185', '83', 26, 4),
(5, 'Luka', 'Lukic', '179', '74kg', 28, 5);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `KorisnickoIme` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Email` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  `Password` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uloga` enum('Administrator','Trener','Korisnik') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'Korisnik'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `KorisnickoIme`, `Email`, `Password`, `uloga`) VALUES
(1, 'Zvonimir Vištica', 'zvone.vistica77@gmail.com', '12345678', 'Administrator'),
(25, 'Marko Maric', 'marko@gmail.com', 'marko1234', 'Trener'),
(26, 'Ivan Ivic', 'ivan@gmail.com', 'ivan1234', 'Korisnik'),
(28, 'Luka', 'luka@gmail.com', 'luka1234', 'Korisnik'),
(29, 'Martina', 'martina@gmail.com', 'martina1234', 'Trener');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDKorisnika` (`IDKorisnika`),
  ADD KEY `IDUser` (`IDUser`);

--
-- Indexes for table `datum`
--
ALTER TABLE `datum`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDUser` (`IDUser`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDUser` (`IDUser`),
  ADD KEY `IDTrener` (`IDTrener`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `coach`
--
ALTER TABLE `coach`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `datum`
--
ALTER TABLE `datum`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `coach`
--
ALTER TABLE `coach`
  ADD CONSTRAINT `coach_ibfk_1` FOREIGN KEY (`IDUser`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `coach_ibfk_2` FOREIGN KEY (`IDKorisnika`) REFERENCES `korisnik` (`ID`);

--
-- Constraints for table `datum`
--
ALTER TABLE `datum`
  ADD CONSTRAINT `datum_ibfk_1` FOREIGN KEY (`IDUser`) REFERENCES `user` (`ID`);

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`IDUser`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `korisnik_ibfk_2` FOREIGN KEY (`IDTrener`) REFERENCES `coach` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
