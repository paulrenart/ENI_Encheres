CREATE DATABASE IF NOT EXISTS ENCHERES;
USE ENCHERES;

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

CREATE TABLE IF NOT EXISTS `ARTICLES_VENDUS` (
  `no_article` int NOT NULL AUTO_INCREMENT,
  `nom_article` varchar(30) NOT NULL,
  `description` varchar(300) NOT NULL,
  `date_debut_encheres` date NOT NULL,
  `date_fin_encheres` date NOT NULL,
  `prix_initial` int DEFAULT NULL,
  `prix_vente` int DEFAULT NULL,
  `no_utilisateur` int NOT NULL,
  `no_categorie` int NOT NULL,
  `image_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`no_article`),
  KEY `articles_vendus_categories_fk` (`no_categorie`),
  KEY `ventes_utilisateur_fk` (`no_utilisateur`),
  CONSTRAINT `articles_vendus_categories_fk` FOREIGN KEY (`no_categorie`) REFERENCES `CATEGORIES` (`no_categorie`),
  CONSTRAINT `encheres_utilisateur_fk` FOREIGN KEY (`no_utilisateur`) REFERENCES `UTILISATEURS` (`no_utilisateur`),
  CONSTRAINT `ventes_utilisateur_fk` FOREIGN KEY (`no_utilisateur`) REFERENCES `UTILISATEURS` (`no_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `CATEGORIES` (
  `no_categorie` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(30) NOT NULL,
  PRIMARY KEY (`no_categorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `CATEGORIES` (`no_categorie`, `libelle`) VALUES
(1,	'meubles'),
(2,	'informatique'),
(3,	'vetements');

CREATE TABLE IF NOT EXISTS`ENCHERES` (
  `no_utilisateur` int NOT NULL,
  `no_article` int NOT NULL,
  `date_enchere` datetime NOT NULL,
  `montant_enchere` int NOT NULL,
  KEY `encheres_articles_vendus_fk` (`no_article`),
  CONSTRAINT `encheres_articles_vendus_fk` FOREIGN KEY (`no_article`) REFERENCES `ARTICLES_VENDUS` (`no_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `RETRAITS` (
  `no_article` int NOT NULL,
  `rue` varchar(30) NOT NULL,
  `code_postal` varchar(15) NOT NULL,
  `ville` varchar(30) NOT NULL,
  PRIMARY KEY (`no_article`),
  CONSTRAINT `retraits_articles_vendus_fk` FOREIGN KEY (`no_article`) REFERENCES `ARTICLES_VENDUS` (`no_article`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `UTILISATEURS` (
  `no_utilisateur` int NOT NULL AUTO_INCREMENT,
  `pseudo` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `email` varchar(20) NOT NULL,
  `telephone` varchar(15) DEFAULT NULL,
  `rue` varchar(30) NOT NULL,
  `code_postal` varchar(10) NOT NULL,
  `ville` varchar(30) NOT NULL,
  `mot_de_passe` varchar(30) NOT NULL,
  `credit` int NOT NULL,
  `administrateur` tinyint(1) NOT NULL,
  PRIMARY KEY (`no_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `UTILISATEURS` (`no_utilisateur`, `pseudo`, `nom`, `prenom`, `email`, `telephone`, `rue`, `code_postal`, `ville`, `mot_de_passe`, `credit`, `administrateur`) VALUES
(1,	'admin',	'Renart',	'Paul',	'rp@gmail.com',	'0606060606',	'rue',	'44000',	'Nantes',	'admin',	891,	1);
