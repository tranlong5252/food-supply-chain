CREATE TABLE IF NOT EXISTS `users` (
                                       `id` int(11) NOT NULL AUTO_INCREMENT,
                                       `username` varchar(255) NOT NULL,
                                       `password` varchar(255) NOT NULL,
                                       `email` varchar(255) NOT NULL,
                                       `created_at` datetime NOT NULL,
                                       `updated_at` datetime NOT NULL,
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `username` (`username`),
                                       UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- int id, String name, int level, double value, int potential, int development;
CREATE TABLE IF NOT EXISTS `status` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `name` varchar(255) NOT NULL,
                                        `level` int(11) NOT NULL,
                                        `value` double NOT NULL,
                                        `potential` int(11) NOT NULL,
                                        `development` int(11) NOT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;

-- region_status table
CREATE TABLE IF NOT EXISTS `region_status` (
                                              `id` int(11) NOT NULL AUTO_INCREMENT,
                                              `region_id` int(11) NOT NULL,
                                              `status_id` int(11) NOT NULL,
                                              PRIMARY KEY (`id`),
                                              FOREIGN KEY `region_id` (`region_id`) REFERENCES `region` (`id`),
                                              FOREIGN KEY `status_id` (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--     private int id, String name, double distribution, int migration, int urbanization;, NatureStatus natureStatus;
CREATE TABLE IF NOT EXISTS `region` (
                                        `id` int(11) NOT NULL AUTO_INCREMENT,
                                        `name` varchar(255) NOT NULL,
                                        `distribution` double NOT NULL,
                                        `migration` int(11) NOT NULL,
                                        `urbanization` int(11) NOT NULL,
                                        `agriculture_land` double NOT NULL,
                                        `forest_land` double NOT NULL,
                                        `disaster` varchar(255) NOT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--     private int id, String name, String taxCode, Region region, String specification;
CREATE TABLE IF NOT EXISTS `client_company` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `name` varchar(255) NOT NULL,
                                      `tax_code` varchar(255) NOT NULL,
                                      `region_id` int(11) NOT NULL,
                                      `specification` varchar(255) NOT NULL,
                                      PRIMARY KEY (`id`),
                                      FOREIGN KEY `region_id` (`region_id`) REFERENCES `region` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- int id is key, String username, String password
CREATE TABLE IF NOT EXISTS `account` (
                                      `id` int(11) NOT NULL AUTO_INCREMENT,
                                      `username` varchar(255) NOT NULL,
                                      `password` varchar(255) NOT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;