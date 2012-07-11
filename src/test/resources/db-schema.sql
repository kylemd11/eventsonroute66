-- -----------------------------------------------------

-- Table `route66Db`.`authorities`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `authorities` ;



CREATE  TABLE IF NOT EXISTS `authorities` (

  `username` VARCHAR(50) NOT NULL ,

  `authority` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`username`) ,

  UNIQUE INDEX `ix_auth_username` (`username` ASC, `authority` ASC) );





-- -----------------------------------------------------

-- Table `route66Db`.`users`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `users` ;



CREATE  TABLE IF NOT EXISTS `users` (

  `username` VARCHAR(50) NOT NULL ,

  `password` VARCHAR(50) NOT NULL ,

  `enabled` INT(11) NOT NULL DEFAULT '0' ,

  PRIMARY KEY (`username`) );





-- -----------------------------------------------------

-- Table `route66Db`.`user_account`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `user_account` ;



CREATE  TABLE IF NOT EXISTS `user_account` (

  `username` VARCHAR(50) NOT NULL ,

  `first_name` VARCHAR(64) NULL DEFAULT NULL ,

  `last_name` VARCHAR(64) NULL DEFAULT NULL ,

  `email_addr` VARCHAR(128) NOT NULL ,

  `street_addr1` VARCHAR(128) NULL DEFAULT NULL ,

  `street_addr2` VARCHAR(128) NULL DEFAULT NULL ,

  `city` VARCHAR(128) NULL DEFAULT NULL ,

  `state` VARCHAR(45) NULL DEFAULT NULL ,

  `zip` VARCHAR(45) NULL DEFAULT NULL ,

  `create_dtg` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

  `last_update_dtg` TIMESTAMP NULL ,

  PRIMARY KEY (`username`));
  

  CREATE INDEX `user_account_fk` on user_account (`username`);

 -- CREATE CONSTRAINT `user_account_fk`

 --   FOREIGN KEY (`username` )

 --   REFERENCES `users` (`username` )

 --   ON DELETE CASCADE

 --   ON UPDATE NO ACTION);





-- -----------------------------------------------------

-- Table `route66Db`.`user_account_request`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `user_account_request` ;



CREATE  TABLE IF NOT EXISTS `user_account_request` (

  `username` VARCHAR(50) NOT NULL ,

  `requestId` VARCHAR(255) NOT NULL ,

  `expiration_date` DATE NOT NULL ,

  `create_dtg` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

  PRIMARY KEY (`username`));

  CREATE INDEX `user_account_request_fk` on user_account_request (`username`);

  CREATE INDEX `user_account_request_fk2` on user_account_request (`username`);

  -- CONSTRAINT `user_account_request_fk`

  --   FOREIGN KEY (`username` )

  --   REFERENCES `route66Db`.`users` (`username` )

  --   ON DELETE NO ACTION

  --   ON UPDATE NO ACTION,

  -- CONSTRAINT `user_account_request_fk2`

  --   FOREIGN KEY (`username` )

  --   REFERENCES `route66Db`.`user_account` (`username` )

  --   ON DELETE NO ACTION

  --   ON UPDATE NO ACTION);





-- -----------------------------------------------------

-- Table `route66Db`.`state`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `state` ;



CREATE  TABLE IF NOT EXISTS `state` (

  `code` VARCHAR(2) NOT NULL ,

  `name` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`code`) );





-- -----------------------------------------------------

-- Table `route66Db`.`event_type`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `event_type` ;



CREATE  TABLE IF NOT EXISTS `event_type` (

  `code` VARCHAR(2) NOT NULL ,

  `description` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`code`) );





-- -----------------------------------------------------

-- Table `route66Db`.`event`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `event` ;



CREATE  TABLE IF NOT EXISTS `event` (

  `event_seq_id` INT NOT NULL AUTO_INCREMENT ,

  `username` VARCHAR(50) NOT NULL ,

  `title` VARCHAR(256) NOT NULL ,

  `start_dtg` DATETIME NOT NULL ,

  `end_dtg` DATETIME NOT NULL ,

  `address_1` VARCHAR(256) NULL ,

  `address_2` VARCHAR(256) NULL ,

  `city` VARCHAR(128) NOT NULL ,

  `state_cd` VARCHAR(2) NOT NULL ,

  `zip_code` VARCHAR(10) NOT NULL ,

  `create_dtg` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

  `update_dtg` TIMESTAMP NULL ,

  `content` LONGTEXT NOT NULL ,

  `event_type_cd` VARCHAR(2) NOT NULL DEFAULT 'PU' ,

  `latitude` DOUBLE NOT NULL ,

  `longitude` DOUBLE NOT NULL ,

  `event_status_cd` VARCHAR(2) NOT NULL DEFAULT 'P' ,

  `is_new` BIT(1) NOT NULL DEFAULT 0 ,

  `lckd_by` VARCHAR(50) NULL ,

  `lckd_dtg` TIMESTAMP NULL ,

  `all_day` BIT(1) NULL DEFAULT 0 ,

  `summary` VARCHAR(200) NOT NULL ,

  PRIMARY KEY (`event_seq_id`));

  CREATE INDEX `event_state_fk` on event (`state_cd`);

  CREATE INDEX `event_type_fk` on event (`event_type_cd`);

  -- CONSTRAINT `event_state_fk`

  --   FOREIGN KEY (`state_cd` )

  --   REFERENCES `route66Db`.`state` (`code` )

  --   ON DELETE NO ACTION

 --    ON UPDATE NO ACTION,

 --  CONSTRAINT `event_user_fk`

 --    FOREIGN KEY (`username` )

 --    REFERENCES `user_account` (`username` ),

 --  CONSTRAINT `event_type_fk`

 --    FOREIGN KEY (`event_type_cd` )

 --    REFERENCES `event_type` (`code` )

 --    ON DELETE NO ACTION

 --    ON UPDATE NO ACTION);





-- -----------------------------------------------------

-- Table `route66Db`.`comment`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `comment` ;



CREATE  TABLE IF NOT EXISTS `comment` (

  `comment_seq_id` INT NOT NULL AUTO_INCREMENT ,

  `username` VARCHAR(50) NOT NULL ,

  `create_dtg` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

  `content` LONGTEXT NOT NULL ,

  `event_seq_id` INT NOT NULL ,

  `enabled` INT NOT NULL DEFAULT 1 ,

  `update_dtg` TIMESTAMP NULL ,

  PRIMARY KEY (`comment_seq_id`));

  CREATE INDEX `comment_fk1` on comment (`username`);

  CREATE INDEX `comment_fk2` on comment (`event_seq_id`);

  -- CONSTRAINT `comment_fk1`

 --    FOREIGN KEY (`username` )

  --   REFERENCES `user_account` (`username` )

  --   ON DELETE NO ACTION
-- 
  --   ON UPDATE NO ACTION,

 --  CONSTRAINT `comment_fk2`

  --   FOREIGN KEY (`event_seq_id` )

  --   REFERENCES `event` (`event_seq_id` )

 --    ON DELETE NO ACTION

  --   ON UPDATE NO ACTION);





-- -----------------------------------------------------

-- Table `route66Db`.`event_status`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `event_status` ;



CREATE  TABLE IF NOT EXISTS `event_status` (

  `code` VARCHAR(2) NOT NULL ,

  `description` VARCHAR(45) NOT NULL ,

  PRIMARY KEY (`code`) );





-- -----------------------------------------------------

-- Table `route66Db`.`article_image`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `article_image` ;



CREATE  TABLE IF NOT EXISTS `article_image` (

  `article_image_seq_id` INT NOT NULL AUTO_INCREMENT ,

  `name` VARCHAR(256) NOT NULL ,

  `size` MEDIUMTEXT  NOT NULL ,

  `data` LONGBLOB NOT NULL ,

  `create_dtg` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,

  `username` VARCHAR(50) NOT NULL ,

  PRIMARY KEY (`article_image_seq_id`) );





-- -----------------------------------------------------

-- Table `route66Db`.`event_article_image`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `event_article_image` ;



CREATE  TABLE IF NOT EXISTS `event_article_image` (

  `event_article_image_seq_id` INT NOT NULL AUTO_INCREMENT ,

  `event_seq_id` INT NOT NULL ,

  `article_image_seq_id` INT NOT NULL ,

  PRIMARY KEY (`event_article_image_seq_id`) );





-- -----------------------------------------------------

-- Table `route66Db`.`link`

-- -----------------------------------------------------

DROP TABLE IF EXISTS `link` ;



CREATE  TABLE IF NOT EXISTS `link` (

  `link_seq_id` INT NOT NULL AUTO_INCREMENT ,

  `title` VARCHAR(256) NOT NULL ,

  `url` VARCHAR(256) NOT NULL ,

  `summary` LONGTEXT NULL ,

  PRIMARY KEY (`link_seq_id`) );











-- -----------------------------------------------------

-- Data for table `route66Db`.`authorities`

-- -----------------------------------------------------


INSERT INTO `authorities` (`username`, `authority`) VALUES ('admin', 'ADMINISTRATOR');

INSERT INTO `authorities` (`username`, `authority`) VALUES ('moderator', 'MODERATOR');

INSERT INTO `authorities` (`username`, `authority`) VALUES ('user', 'USER');



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`users`

-- -----------------------------------------------------


INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 1);

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('moderator', '79f52b5b92498b00cb18284f1dcb466bd40ad559', 1);

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('user', '12dea96fec20593566ab75692c9949596833adc9', 1);



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`user_account`

-- -----------------------------------------------------


INSERT INTO `user_account` (`username`, `first_name`, `last_name`, `email_addr`, `street_addr1`, `street_addr2`, `city`, `state`, `zip`, `create_dtg`, `last_update_dtg`) VALUES ('admin', 'Administrator', 'ONe', 'kyle.delap@gmail.com', NULL, NULL, NULL, NULL, NULL, sysdate, NULL);

INSERT INTO `user_account` (`username`, `first_name`, `last_name`, `email_addr`, `street_addr1`, `street_addr2`, `city`, `state`, `zip`, `create_dtg`, `last_update_dtg`) VALUES ('moderator', 'Moderator', 'Two', 'the.delaps@gmail.com', NULL, NULL, NULL, NULL, NULL, sysdate, NULL);

INSERT INTO `user_account` (`username`, `first_name`, `last_name`, `email_addr`, `street_addr1`, `street_addr2`, `city`, `state`, `zip`, `create_dtg`, `last_update_dtg`) VALUES ('user', 'User', 'Three', 'kylemd@hotmail.com', NULL, NULL, NULL, NULL, NULL, sysdate, NULL);



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`state`

-- -----------------------------------------------------


INSERT INTO `state` (`code`, `name`) VALUES ('IL', 'Illinois');

INSERT INTO `state` (`code`, `name`) VALUES ('MO', 'Missouri');

INSERT INTO `state` (`code`, `name`) VALUES ('CA', 'California');

INSERT INTO `state` (`code`, `name`) VALUES ('TX', 'Texas');

INSERT INTO `state` (`code`, `name`) VALUES ('KS', 'Kansas');

INSERT INTO `state` (`code`, `name`) VALUES ('NM', 'New Mexico');

INSERT INTO `state` (`code`, `name`) VALUES ('AZ', 'Arizona');

INSERT INTO `state` (`code`, `name`) VALUES ('OK', 'Oklahoma');



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`event_type`

-- -----------------------------------------------------


INSERT INTO `event_type` (`code`, `description`) VALUES ('PU', 'Public');

INSERT INTO `event_type` (`code`, `description`) VALUES ('CL', 'Club');

INSERT INTO `event_type` (`code`, `description`) VALUES ('PR', 'Private');



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`event`

-- -----------------------------------------------------


INSERT INTO `event` (`event_seq_id`, `username`, `title`, `start_dtg`, `end_dtg`, `address_1`, `address_2`, `city`, `state_cd`, `zip_code`, `create_dtg`, `update_dtg`, `content`, `event_type_cd`, `latitude`, `longitude`, `event_status_cd`, `is_new`, `lckd_by`, `lckd_dtg`, `all_day`, `summary`) VALUES (NULL, 'user', 'test title', DATE '2004-12-31', DATE '2004-12-31', '9305 Marbarry Dr', NULL, 'Fairview Heights', 'IL', '62208', sysdate, NULL, 'test content', 'PU', 38.613843, -90.024706, 'A', 0, NULL, NULL, 0, 'test summary');

INSERT INTO `event` (`event_seq_id`, `username`, `title`, `start_dtg`, `end_dtg`, `address_1`, `address_2`, `city`, `state_cd`, `zip_code`, `create_dtg`, `update_dtg`, `content`, `event_type_cd`, `latitude`, `longitude`, `event_status_cd`, `is_new`, `lckd_by`, `lckd_dtg`, `all_day`, `summary`) VALUES (NULL, 'user', 'title 2', DATE '2004-12-31', DATE '2004-12-31', '9305 Marbarry Dr', NULL, 'Fairview Heights', 'IL', '62208', sysdate, NULL, 'test content', 'CL', 38.613843, -90.024706, 'A', 0, NULL, NULL, 1, 'ha ha ha');



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`event_status`

-- -----------------------------------------------------


INSERT INTO `event_status` (`code`, `description`) VALUES ('P', 'Pending');

INSERT INTO `event_status` (`code`, `description`) VALUES ('A', 'Approved');

INSERT INTO `event_status` (`code`, `description`) VALUES ('D', 'Denied');

INSERT INTO `event_status` (`code`, `description`) VALUES ('R', 'Revoked');



COMMIT;



-- -----------------------------------------------------

-- Data for table `route66Db`.`link`

-- -----------------------------------------------------


INSERT INTO `link` (`link_seq_id`, `title`, `url`, `summary`) VALUES (1, 'test title', 'http://www.google.com', 'Google');

INSERT INTO `link` (`link_seq_id`, `title`, `url`, `summary`) VALUES (2, 'Blah Blah', 'http://www.cnn.com', 'CNN');



COMMIT;

 