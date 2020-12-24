DROP TABLE IF EXISTS CreditCard;
DROP TABLE IF EXISTS Notification;
DROP TABLE IF EXISTS SplitNotification;
DROP TABLE IF EXISTS Relation_FriendGroup_User;
DROP TABLE IF EXISTS FriendGroup;
DROP TABLE IF EXISTS Friends;
DROP TABLE IF EXISTS Bill;
DROP TABLE IF EXISTS Relation_NormalUser_BankAccount;
DROP TABLE IF EXISTS Relation_StoreOwner_BankAccount;
DROP TABLE IF EXISTS BankAccount;
DROP TABLE IF EXISTS StoreOwner;
DROP TABLE IF EXISTS NormalUser;

CREATE TABLE `NormalUser` (
 `normal_user_pk` int NOT NULL AUTO_INCREMENT,
 `email` varchar(255) NOT NULL,
 `phone` varchar(255) NOT NULL,
 `nickname` varchar(255) NOT NULL,
 `password` varchar(255) NOT NULL,
 `balance` float NOT NULL,
 `firstName` varchar(255) NOT NULL,
 `lastName` varchar(255) NOT NULL,
 PRIMARY KEY (`normal_user_pk`)
);

INSERT INTO `NormalUser` (`email`, `phone`, `nickname`, `password`, `balance`, `firstName`, `lastName`)
VALUES  ('test@test.com', '0767865931', 'testUser', 'splitpay', '17.53', 'John', 'Doe'),
        ('bertha@hotmail.com', '0611223344', 'BerthaS', 'splitpay', '0', 'Bertha', 'Smith'),
        ('jaden@gmail.com', '0711223344', 'JadenT', 'splitpay', '53.21', 'Jaden', 'Tune'),
        ('selena@orange.fr', '0711223355', 'SelenaG', 'splitpay', '0.67', 'Selena', 'Gui');

CREATE TABLE `CreditCard` (
 `credit_card_pk` int NOT NULL AUTO_INCREMENT,
 `number` varchar(255) NOT NULL,
 `cardName` varchar(255) NOT NULL,
 `date` date NOT NULL,
 `cvv` varchar(255) NOT NULL,
 `normal_user_fk` int NOT NULL,
 PRIMARY KEY (`credit_card_pk`),
 KEY `normal_user_fk` (`normal_user_fk`),
 CONSTRAINT `FK_NormalUser_CreditCard`
    FOREIGN KEY (`normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO `CreditCard` (`number`, `cardName`, `date`, `cvv`, `normal_user_fk`)
VALUES  ('2342394023948237', 'First card', '2021-11-19', '276', '1'),
        ('7247984029732378', 'Orange card', '2022-12-23', '276', '1'),
        ('9302932472974283', 'Visa', '2022-12-23', '276', '2'),
        ('8723829242380098', 'mastercard', '2022-12-23', '276', '3');

CREATE TABLE `Friends` (
 `added_normal_user_fk` int NOT NULL,
 `adder_normal_user_fk` int NOT NULL,
 KEY `added_normal_user_fk` (`added_normal_user_fk`),
 KEY `adder_normal_user_fk` (`adder_normal_user_fk`),
 CONSTRAINT `fk_NormalUser_Friends_added`
    FOREIGN KEY (`added_normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT,
 CONSTRAINT `fk_NormalUser_Friends_adder`
    FOREIGN KEY (`adder_normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

INSERT INTO `Friends` (`added_normal_user_fk`, `adder_normal_user_fk`)
VALUES  ('2', '1'),
        ('3', '1'),
        ('4', '1');

CREATE TABLE `FriendGroup` (
 `friend_group_pk` int NOT NULL AUTO_INCREMENT,
 `label` varchar(50) NOT NULL,
 `normal_user_fk` int NOT NULL,
 PRIMARY KEY (`friend_group_pk`),
 KEY `normal_user_fk` (`normal_user_fk`),
 CONSTRAINT `FK_NormalUser_FriendGroup`
    FOREIGN KEY (`normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE `Relation_FriendGroup_User` (
 `friend_group_fk` int NOT NULL,
 `normal_user_fk` int NOT NULL,
 KEY `friend_group_fk` (`friend_group_fk`),
 KEY `normal_user_fk` (`normal_user_fk`),
 CONSTRAINT `friend_group_fk`
    FOREIGN KEY (`friend_group_fk`)
    REFERENCES `FriendGroup` (`friend_group_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT,
 CONSTRAINT `normal_user_fk`
    FOREIGN KEY (`normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE `Notification` (
 `notification_pk` int NOT NULL AUTO_INCREMENT,
 `label` varchar(255) NOT NULL,
 `message` varchar(255) NOT NULL,
 `normal_user_fk` int NOT NULL,
 `dateCreated` timestamp NOT NULL,
 PRIMARY KEY (`notification_pk`),
 KEY `normal_user_fk` (`normal_user_fk`),
 CONSTRAINT `FK_NormalUser_Notification`
    FOREIGN KEY (`normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE `SplitNotification` (
 `split_notification_pk` int NOT NULL AUTO_INCREMENT,
 `label` varchar(255) NOT NULL,
 `message` varchar(255) NOT NULL,
 `splitCode` varchar(255) NOT NULL,
 `normal_user_fk` int NOT NULL,
  `dateCreated` timestamp NOT NULL,
 PRIMARY KEY (`split_notification_pk`),
 KEY `normal_user_fk` (`normal_user_fk`),
 CONSTRAINT `FK_NormalUser_SplitNotification`
    FOREIGN KEY (`normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE `StoreOwner` (
 `store_owner_pk` int NOT NULL AUTO_INCREMENT,
 `email` varchar(255) NOT NULL,
 `phone` varchar(255) NOT NULL,
 `nickname` varchar(255) NOT NULL,
 `password` varchar(255) NOT NULL,
 `balance` float NOT NULL,
 `companyName` varchar(255) NOT NULL,
 `address` varchar(255) NOT NULL,
 PRIMARY KEY (`store_owner_pk`)
);

CREATE TABLE `Bill` (
 `bill_pk` int NOT NULL AUTO_INCREMENT,
 `label` varchar(255) NOT NULL,
 `content` text NOT NULL,
 `store_owner_fk` int NOT NULL,
 PRIMARY KEY (`bill_pk`),
 KEY `store_owner_fk` (`store_owner_fk`),
 CONSTRAINT `FK_StoreOwner_Bill`
    FOREIGN KEY (`store_owner_fk`)
    REFERENCES `StoreOwner` (`store_owner_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE `BankAccount` (
 `bank_account_pk` int NOT NULL AUTO_INCREMENT,
 `label` varchar(255) NOT NULL,
 `bic` varchar(255) NOT NULL,
 `iban` varchar(255) NOT NULL,
 `ownerFirstName` varchar(255) NOT NULL,
 `ownerLastName` varchar(255) NOT NULL,
 PRIMARY KEY (`bank_account_pk`)
);

CREATE TABLE `Relation_NormalUser_BankAccount` (
 `normal_user_fk` int NOT NULL,
 `bank_account_fk` int NOT NULL,
 KEY `normal_user_fk` (`normal_user_fk`),
 KEY `bank_account_fk` (`bank_account_fk`),
 CONSTRAINT `fk_bank_account`
    FOREIGN KEY (`bank_account_fk`)
    REFERENCES `BankAccount` (`bank_account_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT,
 CONSTRAINT `fk_normal_user`
    FOREIGN KEY (`normal_user_fk`)
    REFERENCES `NormalUser` (`normal_user_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE `Relation_StoreOwner_BankAccount` (
 `store_owner_fk` int NOT NULL,
 `bank_account_fk` int NOT NULL,
 KEY `store_owner_fk` (`store_owner_fk`),
 KEY `bank_account_fk` (`bank_account_fk`),
 CONSTRAINT `bank_account_fk`
    FOREIGN KEY (`bank_account_fk`)
    REFERENCES `BankAccount` (`bank_account_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT,
 CONSTRAINT `store_owner_fk`
    FOREIGN KEY (`store_owner_fk`)
    REFERENCES `StoreOwner` (`store_owner_pk`)
    ON DELETE CASCADE ON UPDATE RESTRICT
);