-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rentalDB
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rentalDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rentalDB` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema vehiclerentalsystem
-- -----------------------------------------------------
USE `rentalDB` ;

-- -----------------------------------------------------
-- Table `rentalDB`.`manufacturer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`manufacturer` (
  `id_manufacturer` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(20) NULL,
  `fax` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id_manufacturer`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`vehicle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`vehicle` (
  `id_vehicle` VARCHAR(5) NOT NULL,
  `id_manufacturer` INT NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `purchase_price` INT NOT NULL,
  `is_rented` TINYINT NOT NULL DEFAULT 0,
  `is_broken` TINYINT NOT NULL DEFAULT 0,
  `image` VARCHAR(100) NULL,
  PRIMARY KEY (`id_vehicle`),
  INDEX `fk_Vehicle_Manufacturer1_idx` (`id_manufacturer` ASC) VISIBLE,
  CONSTRAINT `fk_Vehicle_Manufacturer1`
    FOREIGN KEY (`id_manufacturer`)
    REFERENCES `rentalDB`.`manufacturer` (`id_manufacturer`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`car`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`car` (
  `id_vehicle` VARCHAR(5) NOT NULL,
  `purchase_date` DATE NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_vehicle`),
  CONSTRAINT `fk_Car_Vehicle2`
    FOREIGN KEY (`id_vehicle`)
    REFERENCES `rentalDB`.`vehicle` (`id_vehicle`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`e_bike`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`e_bike` (
  `id_vehicle` VARCHAR(5) NOT NULL,
  `autonomy` INT NOT NULL,
  PRIMARY KEY (`id_vehicle`),
  CONSTRAINT `fk_EBike_Vehicle1`
    FOREIGN KEY (`id_vehicle`)
    REFERENCES `rentalDB`.`vehicle` (`id_vehicle`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`e_scooter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`e_scooter` (
  `id_vehicle` VARCHAR(5) NOT NULL,
  `max_speed` INT NOT NULL,
  PRIMARY KEY (`id_vehicle`),
  CONSTRAINT `fk_EScooter_Vehicle2`
    FOREIGN KEY (`id_vehicle`)
    REFERENCES `rentalDB`.`vehicle` (`id_vehicle`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`malfunction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`malfunction` (
  `id_malfunction` INT NOT NULL AUTO_INCREMENT,
  `id_vehicle` VARCHAR(5) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `date_time` DATETIME NOT NULL,
  PRIMARY KEY (`id_malfunction`),
  INDEX `fk_malfunction_vehicle1_idx` (`id_vehicle` ASC) VISIBLE,
  CONSTRAINT `fk_malfunction_vehicle1`
    FOREIGN KEY (`id_vehicle`)
    REFERENCES `rentalDB`.`vehicle` (`id_vehicle`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`location` (
  `id_location` INT NOT NULL AUTO_INCREMENT,
  `latitude` DECIMAL(8,6) NOT NULL,
  `longitude` DECIMAL(9,6) NOT NULL,
  PRIMARY KEY (`id_location`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`person` (
  `id_person` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(60) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_person`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`client` (
  `id_person` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `avatar` VARCHAR(45) NOT NULL,
  `is_blocked` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_person`),
  CONSTRAINT `fk_Client_Person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`person` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `rentalDB`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`manager` (
  `id_person` INT NOT NULL,
  PRIMARY KEY (`id_person`),
  CONSTRAINT `fk_Employee_Person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`person` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`rental`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`rental` (
  `id_rental` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `date_time` DATETIME NOT NULL,
  `renting_duration` INT NOT NULL,
  `pickup_location` INT NOT NULL,
  `dropoff_location` INT NOT NULL,
  `rental_price` INT NOT NULL,
  `id_vehicle` VARCHAR(5) NOT NULL,
  PRIMARY KEY (`id_rental`),
  INDEX `fk_Rental_Location1_idx` (`pickup_location` ASC) VISIBLE,
  INDEX `fk_Rental_Location2_idx` (`dropoff_location` ASC) VISIBLE,
  INDEX `fk_Rental_Client1_idx` (`id_person` ASC) VISIBLE,
  INDEX `fk_rental_vehicle1_idx` (`id_vehicle` ASC) VISIBLE,
  CONSTRAINT `fk_Rental_Location1`
    FOREIGN KEY (`pickup_location`)
    REFERENCES `rentalDB`.`location` (`id_location`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_Rental_Location2`
    FOREIGN KEY (`dropoff_location`)
    REFERENCES `rentalDB`.`location` (`id_location`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_Rental_Client1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`client` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade,
  CONSTRAINT `fk_rental_vehicle1`
    FOREIGN KEY (`id_vehicle`)
    REFERENCES `rentalDB`.`vehicle` (`id_vehicle`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`client_documents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`client_documents` (
  `id_person` INT NOT NULL,
  `id_card` VARCHAR(8) NOT NULL,
  `passport` VARCHAR(8) NULL,
  `driver_license_number` VARCHAR(8) NULL,
  PRIMARY KEY (`id_person`),
  CONSTRAINT `fk_client_documents_client1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`client` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `rentalDB`.`invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`invoice` (
  `id_invoice` INT NOT NULL AUTO_INCREMENT,
  `amount` INT NOT NULL,
  `id_rental` INT NOT NULL,
  INDEX `fk_invoice_rental1_idx` (`id_rental` ASC) VISIBLE,
  PRIMARY KEY (`id_invoice`),
  CONSTRAINT `fk_invoice_rental1`
    FOREIGN KEY (`id_rental`)
    REFERENCES `rentalDB`.`rental` (`id_rental`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`marketing_content`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`marketing_content` (
  `id_marketing_content` INT NOT NULL AUTO_INCREMENT,
  `id_person` INT NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_marketing_content`),
  INDEX `fk_marketing_content_manager1_idx` (`id_person` ASC) VISIBLE,
  CONSTRAINT `fk_marketing_content_manager1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`manager` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`administrator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`administrator` (
  `id_person` INT NOT NULL,
  PRIMARY KEY (`id_person`),
  CONSTRAINT `fk_table1_Person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`person` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`operator`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`operator` (
  `id_person` INT NOT NULL,
  PRIMARY KEY (`id_person`),
  CONSTRAINT `fk_table2_Person1`
    FOREIGN KEY (`id_person`)
    REFERENCES `rentalDB`.`person` (`id_person`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`post` (
  `id_post` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(150) NOT NULL,
  `id_marketing_content` INT NOT NULL,
  PRIMARY KEY (`id_post`),
  INDEX `fk_post_marketing_content1_idx` (`id_marketing_content` ASC) VISIBLE,
  CONSTRAINT `fk_post_marketing_content1`
    FOREIGN KEY (`id_marketing_content`)
    REFERENCES `rentalDB`.`marketing_content` (`id_marketing_content`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentalDB`.`promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `rentalDB`.`promotion` (
  `id_promotion` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(80) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `id_marketing_content` INT NOT NULL,
  PRIMARY KEY (`id_promotion`),
  INDEX `fk_promotion_marketing_content1_idx` (`id_marketing_content` ASC) VISIBLE,
  CONSTRAINT `fk_promotion_marketing_content1`
    FOREIGN KEY (`id_marketing_content`)
    REFERENCES `rentalDB`.`marketing_content` (`id_marketing_content`)
    ON DELETE cascade
    ON UPDATE cascade)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
