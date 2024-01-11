/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table food_supply_chain.account
CREATE TABLE IF NOT EXISTS `account` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `username` varchar(255) NOT NULL,
                                         `password` tinytext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                         `role` int NOT NULL DEFAULT '0',
                                         `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.client_account
CREATE TABLE IF NOT EXISTS `client_account` (
                                                `company_id` int NOT NULL,
                                                `account_id` int NOT NULL,
                                                UNIQUE KEY `company_id` (`company_id`) USING BTREE,
                                                KEY `client_account_account_id_fk` (`account_id`),
                                                CONSTRAINT `client_account_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
                                                CONSTRAINT `client_account_client_company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `client_company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.client_company
CREATE TABLE IF NOT EXISTS `client_company` (
                                                `id` int NOT NULL AUTO_INCREMENT,
                                                `name` varchar(255) NOT NULL,
                                                `tax_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                                `specification` varchar(255) NOT NULL,
                                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.company_region
CREATE TABLE IF NOT EXISTS `company_region` (
                                                `company_id` int DEFAULT NULL,
                                                `region_id` int DEFAULT NULL,
                                                UNIQUE KEY `company_id` (`company_id`) USING BTREE,
                                                KEY `company_region_region_id_fk` (`region_id`),
                                                CONSTRAINT `company_region_client_company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `client_company` (`id`),
                                                CONSTRAINT `company_region_region_id_fk` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.product
CREATE TABLE IF NOT EXISTS `product` (
                                         `id` int NOT NULL AUTO_INCREMENT,
                                         `name` varchar(200) NOT NULL,
                                         `price` decimal(10,0) NOT NULL,
                                         `quantity` int NOT NULL DEFAULT '0',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.product_company
CREATE TABLE IF NOT EXISTS `product_company` (
                                                 `product_id` int NOT NULL,
                                                 `company_id` int NOT NULL,
                                                 UNIQUE KEY `product_id` (`product_id`,`company_id`) USING BTREE,
                                                 KEY `product_company_client_company_id_fk` (`company_id`),
                                                 CONSTRAINT `product_company_client_company_id_fk` FOREIGN KEY (`company_id`) REFERENCES `client_company` (`id`),
                                                 CONSTRAINT `product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.region
CREATE TABLE IF NOT EXISTS `region` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `name` varchar(255) NOT NULL,
                                        `distribution` double NOT NULL,
                                        `migration` int NOT NULL,
                                        `urbanization` int NOT NULL,
                                        `agriculture_land` double NOT NULL,
                                        `forest_land` double NOT NULL,
                                        `disaster` varchar(255) NOT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.region_status
CREATE TABLE IF NOT EXISTS `region_status` (
                                               `region_id` int NOT NULL,
                                               `status_id` int NOT NULL,
                                               UNIQUE KEY `region_id_status_id` (`region_id`,`status_id`) USING BTREE,
                                               KEY `FK_region_status_status` (`status_id`),
                                               KEY `region_id` (`region_id`),
                                               CONSTRAINT `FK_region_status_region` FOREIGN KEY (`region_id`) REFERENCES `region` (`id`),
                                               CONSTRAINT `FK_region_status_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table food_supply_chain.status
CREATE TABLE IF NOT EXISTS `status` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `name` varchar(255) NOT NULL,
                                        `level` int NOT NULL,
                                        `value` double NOT NULL,
                                        `potential` int NOT NULL,
                                        `development` int NOT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
