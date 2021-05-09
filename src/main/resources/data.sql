INSERT INTO `vehiclesvc_default`.`user`(`name`,`password`) VALUES('dkumar','pass');
INSERT INTO `vehiclesvc_default`.`user` (`name`,`password`) values('snishad','pass');

INSERT INTO `vehiclesvc_default`.`role`(`id`,`name`) VALUES(1,'ADMIN');
INSERT INTO `vehiclesvc_default`.`role` (`id`,`name`) values(2,'USER');

INSERT INTO `vehiclesvc_default`.`user_role`(`name`,`role`) VALUES('dkumar',1);
INSERT INTO `vehiclesvc_default`.`user_role` (`name`,`role`) values('dkumar',2);
INSERT INTO `vehiclesvc_default`.`user_role` (`name`,`role`) values('snishad',2);


