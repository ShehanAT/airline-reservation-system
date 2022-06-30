-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 16 Oca 2021, 21:05:52
-- Sunucu sürümü: 10.4.13-MariaDB
-- PHP Sürümü: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `hawkeye`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reply`
--

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL,
  `message_id` int(11) NOT NULL,
  `review_icerik` text NOT NULL,
  `review_title` varchar(500) NOT NULL,
  `review_tarih` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `company`
--

CREATE TABLE `company` (
  `company_id` int(11) NOT NULL,
  `company_name` varchar(100) NOT NULL,
  `company_logo` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `airport`
--

CREATE TABLE `airport` (
  `airport_id` int(11) NOT NULL,
  `airport_country_id` int(11) NOT NULL,
  `airport_city_id` int(11) NOT NULL,
  `airport_name` varchar(100) NOT NULL,
  `airport_code` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `airport_city`
--

CREATE TABLE `airport_city` (
  `airport_city_id` int(11) NOT NULL,
  `airport_city_name` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `havaalani_ulke`
--

CREATE TABLE `havaalani_ulke` (
  `airport_country_id` int(11) NOT NULL,
  `airport_country_name` varchar(500) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `kullanicilar`
--

CREATE TABLE `kullanicilar` (
  `user_id` int(11) NOT NULL,
  `user_ad` varchar(100) DEFAULT NULL,
  `user_soyad` varchar(100) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_sifre` varchar(100) DEFAULT NULL,
  `user_authorization` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `kullanicilar`
--

INSERT INTO `kullanicilar` (`user_id`, `user_ad`, `user_soyad`, `user_email`, `user_sifre`, `user_authorization`) VALUES
(3, 'Kullanıcı', 'Admin', 'admin@hawkeye.com', '123456', 2),
(4, 'Kullanıcı', 'Üye', 'user@hawkeye.com', '123456', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `message_surname` varchar(500) NOT NULL,
  `message_email` varchar(500) NOT NULL,
  `message_konu` varchar(500) NOT NULL,
  `message_icerik` text NOT NULL,
  `message_tarih` timestamp NOT NULL DEFAULT current_timestamp(),
  `message_notRead` int(2) NOT NULL DEFAULT 0,
  `message_review` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation`
--

CREATE TABLE `reservation` (
  `rezervasyon_id` int(11) NOT NULL,
  `rezervasyon_tarih` timestamp NOT NULL DEFAULT current_timestamp(),
  `flight_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `pnr_no` varchar(11) NOT NULL DEFAULT '45645',
  `yolcu_ad` varchar(500) NOT NULL,
  `yolcu_soyad` varchar(500) NOT NULL,
  `yolcu_email` varchar(500) NOT NULL,
  `yolcu_tel` varchar(11) NOT NULL,
  `yolcu_tc` varchar(11) NOT NULL,
  `yolcu_tip` int(5) NOT NULL,
  `yolcu_tarih` varchar(20) NOT NULL,
  `yolcu_ucret` decimal(6,2) NOT NULL,
  `koltuk_no` varchar(10) NOT NULL,
  `situation` int(2) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `ucak`
--

CREATE TABLE `ucak` (
  `plane_id` int(11) NOT NULL,
  `ucak_ad` varchar(500) NOT NULL,
  `ucak_koltuk` int(10) NOT NULL,
  `company_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `flight`
--

CREATE TABLE `flight` (
  `flight_id` int(11) NOT NULL,
  `flight_departure_id` int(11) NOT NULL,
  `end_heir_id` int(11) NOT NULL,
  `flight_date` date NOT NULL,
  `flight_hour` time NOT NULL,
  `flight_time` varchar(50) NOT NULL,
  `company_id` int(11) NOT NULL,
  `plane_id` int(11) NOT NULL,
  `flight_fare` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_id`);

--
-- Tablo için indeksler `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`company_id`);

--
-- Tablo için indeksler `airport`
--
ALTER TABLE `havaalani`
  ADD PRIMARY KEY (`airport_id`);

--
-- Tablo için indeksler `airport_city`
--
ALTER TABLE `airport_city`
  ADD PRIMARY KEY (`airport_city_id`);

--
-- Tablo için indeksler `havaalani_ulke`
--
ALTER TABLE `havaalani_ulke`
  ADD PRIMARY KEY (`airport_country_id`);

--
-- Tablo için indeksler `kullanicilar`
--
ALTER TABLE `kullanicilar`
  ADD PRIMARY KEY (`user_id`);

--
-- Tablo için indeksler `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`);

--
-- Tablo için indeksler `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Tablo için indeksler `ucak`
--
ALTER TABLE `ucak`
  ADD PRIMARY KEY (`plane_id`);

--
-- Tablo için indeksler `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flight_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `review`
--
ALTER TABLE `review`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Tablo için AUTO_INCREMENT değeri `company`
--
ALTER TABLE `company`
  MODIFY `company_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- Tablo için AUTO_INCREMENT değeri `havaalani`
--
ALTER TABLE `havaalani`
  MODIFY `airport_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `airport_city`
--
ALTER TABLE `airport_city`
  MODIFY `airport_city_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Tablo için AUTO_INCREMENT değeri `havaalani_ulke`
--
ALTER TABLE `havaalani_ulke`
  MODIFY `airport_country_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `kullanicilar`
--
ALTER TABLE `kullanicilar`
  MODIFY `kullanici_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Tablo için AUTO_INCREMENT değeri `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Tablo için AUTO_INCREMENT değeri `ucak`
--
ALTER TABLE `ucak`
  MODIFY `plane_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `flight`
--
ALTER TABLE `flight`
  MODIFY `flight_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
