SET foreign_key_checks = 0;

ALTER TABLE usuario MODIFY codigo BIGINT(20) AUTO_INCREMENT;

SET foreign_key_checks = 1;  