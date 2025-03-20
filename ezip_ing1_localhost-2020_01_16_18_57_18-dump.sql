-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 14 mars 2025 à 00:50,
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `reseausocial`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
                         `id_admin` int(11) NOT NULL,
                         `nom` varchar(100) NOT NULL,
                         `prenom` varchar(100) NOT NULL,
                         `poste` varchar(100) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `mot_de_passe` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_admin`, `nom`, `prenom`, `poste`, `email`, `mot_de_passe`) VALUES
    (1, 'admin', 'admin', 'professeur', 'admin@admin.com', ' admin');

-- --------------------------------------------------------

--
-- Structure de la table `ajouter`
--

CREATE TABLE `ajouter` (
                           `id_offre` int(11) NOT NULL,
                           `id_favoris` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `annonces`
--

CREATE TABLE `annonces` (
                            `id_annonce` int(11) NOT NULL,
                            `titre` varchar(150) NOT NULL,
                            `description` varchar(150) NOT NULL,
                            `prix` decimal(10,2) NOT NULL,
                            `date_creation` date NOT NULL,
                            `date_expiration` date NOT NULL,
                            `localisation` varchar(255) DEFAULT NULL,
                            `id_calendrier` int(11) NOT NULL,
                            `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
                           `id_article` int(11) NOT NULL,
                           `titre` varchar(255) NOT NULL,
                           `description` text NOT NULL,
                           `prix` decimal(10,2) NOT NULL,
                           `type_transaction` varchar(50) NOT NULL,
                           `date_publication` date NOT NULL,
                           `date_expiration` date NOT NULL,
                           `statut` varchar(50) DEFAULT NULL,
                           `ville` varchar(100) DEFAULT NULL,
                           `id_etudiant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `avis_etudiant`
--

CREATE TABLE `avis_etudiant` (
                                 `id_avis` int(11) NOT NULL,
                                 `note` int(11) NOT NULL,
                                 `commentaire` varchar(50) DEFAULT NULL,
                                 `date_avis` date NOT NULL,
                                 `id_commerce` int(11) NOT NULL,
                                 `id_etudiant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `avoir`
--

CREATE TABLE `avoir` (
                         `id_annonce` int(11) NOT NULL,
                         `id_participation` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `calculer`
--

CREATE TABLE `calculer` (
                            `id_candidature` int(11) NOT NULL,
                            `id_stat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `calendrier`
--

CREATE TABLE `calendrier` (
                              `id_calendrier` int(11) NOT NULL,
                              `nom` varchar(100) NOT NULL,
                              `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `candidature`
--

CREATE TABLE `candidature` (
                               `id_candidature` int(11) NOT NULL,
                               `nom` varchar(100) NOT NULL,
                               `prenom` varchar(100) NOT NULL,
                               `cv` varchar(255) NOT NULL,
                               `email` varchar(50) NOT NULL,
                               `adresse` varchar(50) NOT NULL,
                               `lettre_de_motivation` varchar(255) NOT NULL,
                               `autre_fichier` varchar(255) NOT NULL,
                               `date_candidature` date DEFAULT NULL,
                               `id_offre` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `candidature`
--

INSERT INTO `candidature` (`id_candidature`, `nom`, `prenom`, `cv`, `email`, `adresse`, `lettre_de_motivation`, `autre_fichier`, `date_candidature`, `id_offre`) VALUES
                                                                                                                                                                     (31, 'jj', 'jj', 'jjj', 'kkk', 'Analyste de données IT.pdf', 'Analyste de données IT.pdf', 'Analyste de données IT.pdf', NULL, 15),
                                                                                                                                                                     (34, 'trj', 'trj', 'kjy', 'yj', 'Analyste de données IT.pdf', 'Data Analyst.pdf', 'Data Analyst.pdf', NULL, 15),
                                                                                                                                                                     (35, 'jhtf', 'trh', 'jt', 'jtj', 'Analyste de données IT.pdf', 'Data Analyst.pdf', 'Data Analyst.pdf', NULL, 16);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
                             `id_categorie` int(11) NOT NULL,
                             `nom_categorie` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `classer`
--

CREATE TABLE `classer` (
                           `id_annonce` int(11) NOT NULL,
                           `id_categorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commerce`
--

CREATE TABLE `commerce` (
                            `id_commerce` int(11) NOT NULL,
                            `nom` varchar(150) NOT NULL,
                            `description` text NOT NULL,
                            `adresse` varchar(50) DEFAULT NULL,
                            `horaire` text DEFAULT NULL,
                            `promotions` text DEFAULT NULL,
                            `contact` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `deposer`
--

CREATE TABLE `deposer` (
                           `id_etudiant` int(11) NOT NULL,
                           `id_candidature` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
                            `id_etudiant` int(11) NOT NULL,
                            `nom` varchar(100) NOT NULL,
                            `prenom` varchar(100) NOT NULL,
                            `matricule` varchar(50) NOT NULL,
                            `email` varchar(100) NOT NULL,
                            `mot_de_passe` varchar(255) NOT NULL,
                            `date_naissance` date NOT NULL,
                            `accepte` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `favoris`
--

CREATE TABLE `favoris` (
                           `id_favoris` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `offres_stages`
--

CREATE TABLE `offres_stages` (
                                 `id_offre` int(11) NOT NULL,
                                 `titre` varchar(150) NOT NULL,
                                 `description` text NOT NULL,
                                 `domaine` varchar(100) NOT NULL,
                                 `niveau_etude` varchar(100) NOT NULL,
                                 `duree` int(11) NOT NULL,
                                 `date_publication` date DEFAULT NULL,
                                 `id_admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `offres_stages`
--

INSERT INTO `offres_stages` (`id_offre`, `titre`, `description`, `domaine`, `niveau_etude`, `duree`, `date_publication`, `id_admin`) VALUES
                                                                                                                                         (15, 'Stage Développeur .NET \r\n', 'Les objectifs du stage:\r\n\r\nRésoudre les incidents dans les délais impartis en fonction de la priorité du problème signalé.\r\nRépondre aux demandes de service, y compris les demandes d\'accès, d\'analyse, d\'extraction de données et de modification de données.\r\nEntreprendre des demandes de résolution de problèmes sur la base de l\'analyse des incidents reçus.\r\nEffectuer des évolutions et des corrections d\'anomalies\r\nConcevoir les composants de la solution en fonction des histoires d\'utilisateurs et de l\'intégration des composants dans le cadre de l\'architecture approuvée.\r\nConcevoir une architecture de processus pour le système, en suggérant la meilleure approche possible, parmi les alternatives disponibles.\r\nEffectuer les tests du système, y compris les tests unitaires, de système et d\'intégration du système, l\'exécution et la vérification des résultats des tests.\r\nDocumenter les documents relatifs aux exigences, à la conception et à la valida', 'IT', 'Licence', 4, NULL, 1),
                                                                                                                                         (16, 'Alternant Développeur BI/ Décisionnel H/F \r\n', 'Vos missions\r\n\r\n\r\nAnalyse des besoins utilisateurs\r\nParticipation aux réunions d\'analyse fonctionnelle\r\nDéfinition des spécifications techniques\r\nRédaction du cahier des charges techniques\r\nDéveloppement BI & Tests\r\nRéalisation des développements BI (alimentation, transformation, tableaux de bord…)\r\nRéalisation de maquettes et de prototypes\r\nRéalisation des tests d\'intégration\r\nAnimation des UAT (User Acceptance Tests)\r\nMise en production et support\r\nRéalisation de la livraison et des paramétrages sur les environnements de production\r\nElaboration de procédures et de guides utilisateurs\r\nParticipation à la mise en place du support utilisateur\r\nEnvironnement technologique\r\nAzure Data Services / DevOps\r\nPower BI\r\nLangage DAX/MDX\r\nSuite Microsoft BI (SSIS/SSAS/SSRS)\r\nVous préparez un diplôme en école d\'ingénieur ou cursus informatique.\r\nVous avez une appétence pour le développement BI, l\'', 'IT', 'licence', 3, NULL, 1);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
                                 `id_participation` int(11) NOT NULL,
                                 `statut` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `statistiques`
--

CREATE TABLE `statistiques` (
                                `id_stat` int(11) NOT NULL,
                                `nombre_offre` int(11) DEFAULT 0,
                                `nombre_candidature` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
    ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `ajouter`
--
ALTER TABLE `ajouter`
    ADD PRIMARY KEY (`id_offre`,`id_favoris`),
  ADD KEY `id_favoris` (`id_favoris`);

--
-- Index pourr la table `annonces`
--
ALTER TABLE `annonces`
    ADD PRIMARY KEY (`id_annonce`),
  ADD KEY `id_calendrier` (`id_calendrier`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Index pour la table `article`
--
ALTER TABLE `article`
    ADD PRIMARY KEY (`id_article`),
  ADD KEY `id_etudiant` (`id_etudiant`);

--
-- Index pour la table `avis_etudiant`
--
ALTER TABLE `avis_etudiant`
    ADD PRIMARY KEY (`id_avis`),
  ADD KEY `id_commerce` (`id_commerce`),
  ADD KEY `id_etudiant` (`id_etudiant`);

--
-- Index pour la table `avoir`
--
ALTER TABLE `avoir`
    ADD PRIMARY KEY (`id_annonce`,`id_participation`),
  ADD KEY `id_participation` (`id_participation`);

--
-- Index pour la table `calculer`
--
ALTER TABLE `calculer`
    ADD PRIMARY KEY (`id_candidature`,`id_stat`),
  ADD KEY `id_stat` (`id_stat`);

--
-- Index pour la table `calendrier`
--
ALTER TABLE `calendrier`
    ADD PRIMARY KEY (`id_calendrier`);

--
-- Index pour la table `candidature`
--
ALTER TABLE `candidature`
    ADD PRIMARY KEY (`id_candidature`),
  ADD KEY `id_offre` (`id_offre`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
    ADD PRIMARY KEY (`id_categorie`),
  ADD UNIQUE KEY `nom_categorie` (`nom_categorie`);

--
-- Index pour la table `classer`
--
ALTER TABLE `classer`
    ADD PRIMARY KEY (`id_annonce`,`id_categorie`),
  ADD KEY `id_categorie` (`id_categorie`);

--
-- Index pour la table `commerce`
--
ALTER TABLE `commerce`
    ADD PRIMARY KEY (`id_commerce`);

--
-- Index pour la table `deposer`
--
ALTER TABLE `deposer`
    ADD PRIMARY KEY (`id_etudiant`,`id_candidature`),
  ADD KEY `id_candidature` (`id_candidature`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
    ADD PRIMARY KEY (`id_etudiant`);

--
-- Index pour la table `favoris`
--
ALTER TABLE `favoris`
    ADD PRIMARY KEY (`id_favoris`);

--
-- Index pour la table `offres_stages`
--
ALTER TABLE `offres_stages`
    ADD PRIMARY KEY (`id_offre`),
  ADD KEY `id_admin` (`id_admin`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
    ADD PRIMARY KEY (`id_participation`);

--
-- Index pour la table `statistiques`
--
ALTER TABLE `statistiques`
    ADD PRIMARY KEY (`id_stat`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
    MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `annonces`
--
ALTER TABLE `annonces`
    MODIFY `id_annonce` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
    MODIFY `id_article` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `avis_etudiant`
--
ALTER TABLE `avis_etudiant`
    MODIFY `id_avis` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `calendrier`
--
ALTER TABLE `calendrier`
    MODIFY `id_calendrier` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `candidature`
--
ALTER TABLE `candidature`
    MODIFY `id_candidature` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
    MODIFY `id_categorie` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `commerce`
--
ALTER TABLE `commerce`
    MODIFY `id_commerce` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
    MODIFY `id_etudiant` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `favoris`
--
ALTER TABLE `favoris`
    MODIFY `id_favoris` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `offres_stages`
--
ALTER TABLE `offres_stages`
    MODIFY `id_offre` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
    MODIFY `id_participation` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `statistiques`
--
ALTER TABLE `statistiques`
    MODIFY `id_stat` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ajouter`
--
ALTER TABLE `ajouter`
    ADD CONSTRAINT `ajouter_ibfk_1` FOREIGN KEY (`id_offre`) REFERENCES `offres_stages` (`id_offre`) ON DELETE CASCADE,
  ADD CONSTRAINT `ajouter_ibfk_2` FOREIGN KEY (`id_favoris`) REFERENCES `favoris` (`id_favoris`) ON DELETE CASCADE;

--
-- Contraintes pour la table `annonces`
--
ALTER TABLE `annonces`
    ADD CONSTRAINT `annonces_ibfk_1` FOREIGN KEY (`id_calendrier`) REFERENCES `calendrier` (`id_calendrier`) ON DELETE CASCADE,
  ADD CONSTRAINT `annonces_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE;

--
-- Contraintes pour la table `article`
--
ALTER TABLE `article`
    ADD CONSTRAINT `article_ibfk_1` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id_etudiant`) ON DELETE CASCADE;

--
-- Contraintes pour la table `avis_etudiant`
--
ALTER TABLE `avis_etudiant`
    ADD CONSTRAINT `avis_etudiant_ibfk_1` FOREIGN KEY (`id_commerce`) REFERENCES `commerce` (`id_commerce`) ON DELETE CASCADE,
  ADD CONSTRAINT `avis_etudiant_ibfk_2` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id_etudiant`) ON DELETE CASCADE;

--
-- Contraintes pour la table `avoir`
--
ALTER TABLE `avoir`
    ADD CONSTRAINT `avoir_ibfk_1` FOREIGN KEY (`id_annonce`) REFERENCES `annonces` (`id_annonce`) ON DELETE CASCADE,
  ADD CONSTRAINT `avoir_ibfk_2` FOREIGN KEY (`id_participation`) REFERENCES `participation` (`id_participation`) ON DELETE CASCADE;

--
-- Contraintes pour la table `calculer`
--
ALTER TABLE `calculer`
    ADD CONSTRAINT `calculer_ibfk_1` FOREIGN KEY (`id_candidature`) REFERENCES `candidature` (`id_candidature`) ON DELETE CASCADE,
  ADD CONSTRAINT `calculer_ibfk_2` FOREIGN KEY (`id_stat`) REFERENCES `statistiques` (`id_stat`) ON DELETE CASCADE;

--
-- Contraintes pour la table `candidature`
--
ALTER TABLE `candidature`
    ADD CONSTRAINT `candidature_ibfk_1` FOREIGN KEY (`id_offre`) REFERENCES `offres_stages` (`id_offre`) ON DELETE CASCADE;

--
-- Contraintes pour la table `classer`
--
ALTER TABLE `classer`
    ADD CONSTRAINT `classer_ibfk_1` FOREIGN KEY (`id_annonce`) REFERENCES `annonces` (`id_annonce`) ON DELETE CASCADE,
  ADD CONSTRAINT `classer_ibfk_2` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`) ON DELETE CASCADE;

--
-- Contraintes pour la table `deposer`
--
ALTER TABLE `deposer`
    ADD CONSTRAINT `deposer_ibfk_1` FOREIGN KEY (`id_etudiant`) REFERENCES `etudiant` (`id_etudiant`) ON DELETE CASCADE,
  ADD CONSTRAINT `deposer_ibfk_2` FOREIGN KEY (`id_candidature`) REFERENCES `candidature` (`id_candidature`) ON DELETE CASCADE;

--
-- Contraintes pour la table `offres_stages`
--
ALTER TABLE `offres_stages`
    ADD CONSTRAINT `offres_stages_ibfk_1` FOREIGN KEY (`id_admin`) REFERENCES `admin` (`id_admin`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
