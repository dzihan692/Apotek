-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2019 at 03:58 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbapotek`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `level`) VALUES
('admin', 'admin', 'Admin'),
('dzihan', 'dzihan', 'Pemilik');

-- --------------------------------------------------------

--
-- Table structure for table `tbkategori`
--

CREATE TABLE `tbkategori` (
  `kode_kategori` char(8) NOT NULL,
  `obat` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbobat`
--

CREATE TABLE `tbobat` (
  `kode_obat` char(8) NOT NULL,
  `kode_kategori` char(8) NOT NULL,
  `obat` varchar(13) NOT NULL,
  `stok_obat` int(5) NOT NULL,
  `harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbpetugas`
--

CREATE TABLE `tbpetugas` (
  `Id_petugas` varchar(8) NOT NULL,
  `Nm_Petugas` char(13) NOT NULL,
  `Alamat` varchar(13) NOT NULL,
  `No_telp` varchar(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbtransaksi`
--

CREATE TABLE `tbtransaksi` (
  `kode_bayar` char(11) NOT NULL,
  `obat` varchar(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `bayar` int(11) NOT NULL,
  `kembali` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `tbkategori`
--
ALTER TABLE `tbkategori`
  ADD PRIMARY KEY (`kode_kategori`);

--
-- Indexes for table `tbobat`
--
ALTER TABLE `tbobat`
  ADD PRIMARY KEY (`kode_obat`),
  ADD UNIQUE KEY `kode_kategori_7` (`kode_kategori`),
  ADD KEY `kode_kategori` (`kode_kategori`),
  ADD KEY `kode_kategori_2` (`kode_kategori`),
  ADD KEY `kode_kategori_3` (`kode_kategori`),
  ADD KEY `kode_kategori_4` (`kode_kategori`),
  ADD KEY `kode_kategori_5` (`kode_kategori`),
  ADD KEY `kode_kategori_6` (`kode_kategori`),
  ADD KEY `kode_kategori_8` (`kode_kategori`);

--
-- Indexes for table `tbpetugas`
--
ALTER TABLE `tbpetugas`
  ADD PRIMARY KEY (`Id_petugas`);

--
-- Indexes for table `tbtransaksi`
--
ALTER TABLE `tbtransaksi`
  ADD PRIMARY KEY (`kode_bayar`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbobat`
--
ALTER TABLE `tbobat`
  ADD CONSTRAINT `tbobat_ibfk_1` FOREIGN KEY (`kode_kategori`) REFERENCES `tbkategori` (`kode_kategori`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
