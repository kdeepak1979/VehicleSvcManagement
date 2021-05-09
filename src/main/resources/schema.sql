CREATE SCHEMA IF NOT EXISTS `vehiclesvc_default`;

use vehiclesvc_default;

CREATE TABLE IF NOT EXISTS `vehiclesvc_default`.`user` (  
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `is_locked` BOOLEAN NULL DEFAULT 0,
  `is_expired` BOOLEAN NULL DEFAULT 0,
  `is_enabled` BOOLEAN NULL DEFAULT 0,
  PRIMARY KEY (`name`));
 
 CREATE TABLE IF NOT EXISTS `vehiclesvc_default`.`role` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`name`));
  
  CREATE TABLE IF NOT EXISTS `vehiclesvc_default`.`user_role` (
  `name` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
   FOREIGN KEY (`name`) REFERENCES `vehiclesvc_default`.`user` (`name`),
   FOREIGN KEY (`role`) REFERENCES `vehiclesvc_default`.`role` (`id`)
 );