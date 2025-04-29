-- Brisanje postojećih podataka (opcionalno)
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `rentalDB`.`promotion`;
TRUNCATE TABLE `rentalDB`.`post`;
TRUNCATE TABLE `rentalDB`.`marketing_content`;
TRUNCATE TABLE `rentalDB`.`invoice`;
TRUNCATE TABLE `rentalDB`.`client_documents`;
TRUNCATE TABLE `rentalDB`.`rental`;
TRUNCATE TABLE `rentalDB`.`malfunction`;
TRUNCATE TABLE `rentalDB`.`e_scooter`;
TRUNCATE TABLE `rentalDB`.`e_bike`;
TRUNCATE TABLE `rentalDB`.`car`;
TRUNCATE TABLE `rentalDB`.`vehicle`;
TRUNCATE TABLE `rentalDB`.`manufacturer`;
TRUNCATE TABLE `rentalDB`.`location`;
TRUNCATE TABLE `rentalDB`.`administrator`;
TRUNCATE TABLE `rentalDB`.`operator`;
TRUNCATE TABLE `rentalDB`.`manager`;
TRUNCATE TABLE `rentalDB`.`client`;
TRUNCATE TABLE `rentalDB`.`person`;
SET FOREIGN_KEY_CHECKS = 1;

-- Popunjavanje tabele manufacturer
INSERT INTO `rentalDB`.`manufacturer` (`name`, `state`, `address`, `phone`, `fax`, `email`) VALUES
('Toyota', 'Japan', '1 Toyota-Cho, Toyota City', '+81 565 28 2121', '+81 565 28 2200', 'contact@toyota.co.jp'),
('Tesla', 'USA', '3500 Deer Creek Road, Palo Alto', '+1 650 681 5000', NULL, 'support@tesla.com'),
('Xiaomi', 'China', 'No.33, 3rd Haidian Road, Beijing', '+86 10 60606666', '+86 10 60607878', 'service.global@xiaomi.com');

-- Popunjavanje tabele location
INSERT INTO `rentalDB`.`location` (`latitude`, `longitude`) VALUES
(44.769965, 17.188757),  
(44.774022, 17.197533),  
(44.766712, 17.179845),  
(44.778899, 17.214567);


-- Popunjavanje tabele vehicle
INSERT INTO `rentalDB`.`vehicle` (`id_vehicle`, `id_manufacturer`, `model`, `purchase_price`, `is_rented`, `is_broken`, `image`) VALUES
-- Auti
('CAR01', 1, 'Corolla', 25000, 0, 0, 'corolla.jpg'),
('CAR02', 1, 'Prius', 30000, 1, 0, 'prius.jpg'),
('CAR03', 2, 'Model 3', 45000, 0, 1, 'model3.jpg'),
-- E-bikeovi
('BIK01', 3, 'Mi Electric Bike', 1200, 0, 0, 'mibike.jpg'),
('BIK02', 3, 'Pro Electric Bike', 1500, 1, 0, 'probike.jpg'),
('BIK03', 3, 'Lite Electric Bike', 1000, 0, 0, 'litebike.jpg'),
-- E-scooteri
('SCO01', 3, 'Mi Scooter 3', 500, 0, 0, 'miscooter.jpg'),
('SCO02', 3, 'Pro Scooter', 700, 1, 0, 'proscooter.jpg'),
('SCO03', 3, 'Lite Scooter', 400, 0, 1, 'litescooter.jpg');

-- Popunjavanje tabele car
INSERT INTO `rentalDB`.`car` (`id_vehicle`, `purchase_date`, `description`) VALUES
('CAR01', '2020-05-15', 'Toyota Corolla 2020, crvena'),
('CAR02', '2021-02-20', 'Toyota Prius 2021, plava'),
('CAR03', '2022-01-10', 'Tesla Model 3 2022, crna');

-- Popunjavanje tabele e_bike
INSERT INTO `rentalDB`.`e_bike` (`id_vehicle`, `autonomy`) VALUES
('BIK01', 60),
('BIK02', 80),
('BIK03', 45);

-- Popunjavanje tabele e_scooter
INSERT INTO `rentalDB`.`e_scooter` (`id_vehicle`, `max_speed`) VALUES
('SCO01', 25),
('SCO02', 30),
('SCO03', 20);

-- Popunjavanje tabele malfunction
INSERT INTO `rentalDB`.`malfunction` (`id_vehicle`, `description`, `date_time`) VALUES
('CAR03', 'Motor ne radi', '2023-01-15 10:30:00'),
('SCO03', 'Pukla guma', '2023-02-20 14:45:00'),
('BIK02', 'Problemi s baterijom', '2023-03-10 09:15:00');



