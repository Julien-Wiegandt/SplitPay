INSERT INTO `NormalUser` (`email`, `phone`, `nickname`, `password`, `balance`, `firstName`, `lastName`)
VALUES  ('test@test.com', '0767865931', 'testUser', 'splitpay', '17.53', 'John', 'Doe'),
        ('bertha@hotmail.com', '0611223344', 'BerthaS', 'splitpay', '0', 'Bertha', 'Smith'),
        ('jaden@gmail.com', '0711223344', 'JadenT', 'splitpay', '53.21', 'Jaden', 'Tune'),
        ('selena@orange.fr', '0711223355', 'SelenaG', 'splitpay', '0.67', 'Selena', 'Gui');

INSERT INTO `CreditCard` (`number`, `cardName`, `date`, `cvv`, `normal_user_fk`)
VALUES  ('2342394023948237', 'First card', '2021-11-19', '276', '1'),
        ('7247984029732378', 'Orange card', '2022-12-23', '276', '1'),
        ('9302932472974283', 'Visa', '2022-12-23', '276', '2'),
        ('8723829242380098', 'mastercard', '2022-12-23', '276', '3');

INSERT INTO `Friends` (`added_normal_user_fk`, `adder_normal_user_fk`)
VALUES  ('2', '1'),
        ('3', '1'),
        ('4', '1');

INSERT INTO `FriendGroup` (`label`, `normal_user_fk`)
VALUES  ('SplitTeam', '1');

INSERT INTO `Relation_FriendGroup_User` (`friend_group_fk`, `normal_user_fk`)
VALUES  ('1', '2'),
        ('1', '3'),
        ('1', '4');

INSERT INTO `Notification` (`label`, `message`, `normal_user_fk`, `dateCreated`)
VALUES ('Welcome', 'Hi welcome to our app', '1', '2020-12-24 17:19:43');

INSERT INTO `StoreOwner` (`email`, `phone`, `nickname`, `password`, `balance`, `companyName`, `address`, `siret`)
VALUES ('delarte34@hotmail.com', '0767342312', 'delarte34080', 'delarte34', '743.67', 'Del arte restaurant', '145 Rue Alphonse Beau de Rochas, 34170 Castelnau', '362 521 879 00034');

