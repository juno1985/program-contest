DROP TABLE IF EXISTS `t_problem_difficulty`;
DROP TABLE IF EXISTS `t_problem_type`;
DROP TABLE IF EXISTS `t_problem_cases`;
DROP TABLE IF EXISTS `t_problems`;

SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;

CREATE TABLE `t_problems`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255) NOT NULL,
	`status` INT(11) NOT NULL, -- online/editing
	`description` LONGTEXT COLLATE utf8_bin,
	`input` TEXT COLLATE utf8_bin,
	`output` TEXT COLLATE utf8_bin,
	`explanation` TEXT COLLATE utf8_bin,
	PRIMARY KEY (`id`)
)ENGINE=INNODB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `t_problem_type`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`problem_type` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL, -- instruction/Strings/BigNumber/Data Structure/Object Oriented Programming/Exception Handling/Advanced
	`fid` INT(11),
	PRIMARY KEY (`id`),
	FOREIGN KEY (`fid`) REFERENCES `t_problems` (`id`)
	
)ENGINE=INNODB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `t_problem_difficulty`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`problem_difficulty` VARCHAR(255) COLLATE utf8_bin DEFAULT NULL, -- Easy/Medium/Hard
	`fid` INT(11),
	PRIMARY KEY (`id`),
	FOREIGN KEY (`fid`) REFERENCES `t_problems` (`id`)
	
)ENGINE=INNODB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



CREATE TABLE `t_problem_cases`(
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`input` LONGTEXT COLLATE utf8_bin,
	`output` LONGTEXT COLLATE utf8_bin,
	`fid` INT(11),
	PRIMARY KEY (`id`),
	FOREIGN KEY (`fid`) REFERENCES `t_problems` (`id`)
	
)ENGINE=INNODB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE `t_problems` AUTO_INCREMENT = 1;
ALTER TABLE `t_problem_type` AUTO_INCREMENT = 1;
ALTER TABLE `t_problem_difficulty` AUTO_INCREMENT = 1;