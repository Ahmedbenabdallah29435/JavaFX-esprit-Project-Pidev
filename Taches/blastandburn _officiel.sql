-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 17 fév. 2022 à 01:29
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `blastandburn`
--

-- --------------------------------------------------------

--
-- Structure de la table `activities`
--

CREATE TABLE `activities` (
  `id_activite` int(11) NOT NULL,
  `nom_activite` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `lieu` varchar(255) NOT NULL,
  `nom_coach` varchar(255) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `activities`
--

INSERT INTO `activities` (`id_activite`, `nom_activite`, `description`, `lieu`, `nom_coach`, `prix`) VALUES
(3, 'AAAA', 'sssss', 'tunis', 'oussama', 2000),
(4, 'Boxe', 'etre sportif c\'est etre equilibré', 'Radess', 'Arij', 75),
(5, 'Boxe', 'etre sportif c\'est etre equilibré', 'Radess', 'Arij', 75),
(6, 'Boxe', 'etre sportif c\'est etre equilibré', 'Radess', 'Arij', 75);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `title` varchar(255) CHARACTER SET latin1 NOT NULL,
  `description` varchar(255) CHARACTER SET latin1 NOT NULL,
  `start_date` varchar(20) DEFAULT NULL,
  `end_date` varchar(20) DEFAULT NULL,
  `min_users` int(11) NOT NULL,
  `max_users` int(11) NOT NULL,
  `location` varchar(255) CHARACTER SET latin1 NOT NULL,
  `type` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `price` double NOT NULL,
  `img_url` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT 0,
  `deleted_at` datetime DEFAULT NULL,
  `created_at` datetime DEFAULT current_timestamp(),
  `modified_at` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`event_id`, `u_id`, `title`, `description`, `start_date`, `end_date`, `min_users`, `max_users`, `location`, `type`, `price`, `img_url`, `is_deleted`, `deleted_at`, `created_at`, `modified_at`) VALUES
(16, 1, 'dunes', 'sables desert', '12-10-2004', '12-10-2004', 10, 50, 'tozeur', 'hamla', 110.1, 'hh', 0, NULL, '2022-02-17 00:46:56', '2022-02-17 00:46:56');

-- --------------------------------------------------------

--
-- Structure de la table `event_rate`
--

CREATE TABLE `event_rate` (
  `rate_id` int(11) NOT NULL,
  `event_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `planification`
--

CREATE TABLE `planification` (
  `id` int(11) NOT NULL,
  `id_activite` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `heure_debut` varchar(255) NOT NULL,
  `heure_fin` varchar(255) NOT NULL,
  `id_planuser` int(11) NOT NULL,
  `nb_invite` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `planification`
--

INSERT INTO `planification` (`id`, `id_activite`, `date_debut`, `date_fin`, `heure_debut`, `heure_fin`, `id_planuser`, `nb_invite`) VALUES
(0, 3, '2022-02-01', '2022-02-09', 'ARZE', '13', 1, 3),
(15, 1, '3923-01-15', '3923-01-15', '13h', '14h:30', 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `racipe_rate`
--

CREATE TABLE `racipe_rate` (
  `rate_id` int(11) NOT NULL,
  `recipe_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `rate`
--

CREATE TABLE `rate` (
  `rate_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET latin1 NOT NULL,
  `score` double NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `recipe`
--

CREATE TABLE `recipe` (
  `recipe_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `cat_id` int(11) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `ingredients` varchar(255) DEFAULT NULL,
  `steps` varchar(255) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `persons` int(11) DEFAULT NULL,
  `calories` int(11) DEFAULT NULL,
  `img_url` varchar(255) NOT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_at` timestamp NULL DEFAULT current_timestamp(),
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `recipe`
--

INSERT INTO `recipe` (`recipe_id`, `user_id`, `cat_id`, `title`, `description`, `ingredients`, `steps`, `duration`, `persons`, `calories`, `img_url`, `deleted_at`, `created_at`, `modified_at`, `is_deleted`) VALUES
(1, 0, 0, 'fraise', 'fraise', 'fraise', 'fraise', 20, 10, 1200, 'blabla', NULL, '2022-02-17 00:11:51', '2022-02-17 00:11:51', 0),
(2, 0, 6, 'fraise', 'fraise', 'fraise', 'fraise', 20, 10, 1200, 'blabla', NULL, '2022-02-17 00:14:19', '2022-02-17 00:14:19', 0);

-- --------------------------------------------------------

--
-- Structure de la table `recipe_category`
--

CREATE TABLE `recipe_category` (
  `cat_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `recipe_category`
--

INSERT INTO `recipe_category` (`cat_id`, `name`, `img_url`, `is_deleted`, `deleted_at`, `created_at`, `modified_at`) VALUES
(6, 'img', 'aaa', 0, NULL, '2022-02-17 00:13:24', '2022-02-17 00:13:24');

-- --------------------------------------------------------

--
-- Structure de la table `report`
--

CREATE TABLE `report` (
  `report_id` int(11) NOT NULL,
  `reporter_id` int(11) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `type` varchar(255) CHARACTER SET latin1 NOT NULL,
  `note` varchar(255) CHARACTER SET latin1 NOT NULL,
  `is_closed` tinyint(1) DEFAULT 0,
  `closed_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `report`
--

INSERT INTO `report` (`report_id`, `reporter_id`, `title`, `type`, `note`, `is_closed`, `closed_at`, `created_at`) VALUES
(2, 1, 'AHMED', 'TRABELSI', 'blabla', 0, NULL, '2022-02-16 22:17:30');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `age` int(11) NOT NULL,
  `poids` int(11) NOT NULL,
  `taille` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `etat` int(11) NOT NULL DEFAULT 0,
  `photo` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `nom`, `prenom`, `age`, `poids`, `taille`, `email`, `role`, `etat`, `photo`) VALUES
(1, 'SIRINE', 'abouda', 33, 65, 170, 'hjlkg', 'user', 145552, 'lklll'),
(2, 'fatma', 'bn', 20, 60, 170, 'lmpoiuytr', NULL, 0, 'photo1');

-- --------------------------------------------------------

--
-- Structure de la table `user_event`
--

CREATE TABLE `user_event` (
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `user_rate`
--

CREATE TABLE `user_rate` (
  `rate_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `activities`
--
ALTER TABLE `activities`
  ADD PRIMARY KEY (`id_activite`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `wwd` (`u_id`);

--
-- Index pour la table `event_rate`
--
ALTER TABLE `event_rate`
  ADD PRIMARY KEY (`rate_id`),
  ADD KEY `ssd` (`event_id`);

--
-- Index pour la table `planification`
--
ALTER TABLE `planification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `foreing_key` (`id_planuser`),
  ADD KEY `fk` (`id_activite`);

--
-- Index pour la table `racipe_rate`
--
ALTER TABLE `racipe_rate`
  ADD PRIMARY KEY (`rate_id`),
  ADD KEY `recipe_id` (`recipe_id`);

--
-- Index pour la table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`rate_id`);

--
-- Index pour la table `recipe`
--
ALTER TABLE `recipe`
  ADD PRIMARY KEY (`recipe_id`),
  ADD KEY `aad` (`cat_id`),
  ADD KEY `qqd` (`user_id`);

--
-- Index pour la table `recipe_category`
--
ALTER TABLE `recipe_category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Index pour la table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `ttf` (`reporter_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `user_event`
--
ALTER TABLE `user_event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `ddf` (`user_id`);

--
-- Index pour la table `user_rate`
--
ALTER TABLE `user_rate`
  ADD PRIMARY KEY (`rate_id`),
  ADD KEY `nnd` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `activities`
--
ALTER TABLE `activities`
  MODIFY `id_activite` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `event`
--
ALTER TABLE `event`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `event_rate`
--
ALTER TABLE `event_rate`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `planification`
--
ALTER TABLE `planification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `racipe_rate`
--
ALTER TABLE `racipe_rate`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `rate`
--
ALTER TABLE `rate`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `recipe`
--
ALTER TABLE `recipe`
  MODIFY `recipe_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `recipe_category`
--
ALTER TABLE `recipe_category`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `report`
--
ALTER TABLE `report`
  MODIFY `report_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user_event`
--
ALTER TABLE `user_event`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user_rate`
--
ALTER TABLE `user_rate`
  MODIFY `rate_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `user_event` (`event_id`),
  ADD CONSTRAINT `wwd` FOREIGN KEY (`u_id`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `event_rate`
--
ALTER TABLE `event_rate`
  ADD CONSTRAINT `ssd` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`);

--
-- Contraintes pour la table `planification`
--
ALTER TABLE `planification`
  ADD CONSTRAINT `fk` FOREIGN KEY (`id_activite`) REFERENCES `activities` (`id_activite`);

--
-- Contraintes pour la table `racipe_rate`
--
ALTER TABLE `racipe_rate`
  ADD CONSTRAINT `racipe_rate_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `recipe` (`recipe_id`);

--
-- Contraintes pour la table `recipe`
--
ALTER TABLE `recipe`
  ADD CONSTRAINT `aad` FOREIGN KEY (`cat_id`) REFERENCES `recipe_category` (`cat_id`),
  ADD CONSTRAINT `qqd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `ttf` FOREIGN KEY (`reporter_id`) REFERENCES `user` (`id_user`);

--
-- Contraintes pour la table `user_event`
--
ALTER TABLE `user_event`
  ADD CONSTRAINT `ddf` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `rrt` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`);

--
-- Contraintes pour la table `user_rate`
--
ALTER TABLE `user_rate`
  ADD CONSTRAINT `nnd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
