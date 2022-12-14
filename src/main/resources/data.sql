DROP DATABASE IF EXISTS customerschema;
CREATE DATABASE IF NOT EXISTS customerschema;
USE customerschema;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS customer;

CREATE TABLE customerschema.customer(
	ID INT(11) NULL DEFAULT NULL,
	CUSTOMERID VARCHAR(12) NOT NULL,
	DATE_OF_BIRTH DATETIME NULL DEFAULT NULL,
	FIRST_NAME VARCHAR(100) NULL DEFAULT NULL,
	LAST_NAME VARCHAR(100) NULL DEFAULT NULL,
    PRIMARY KEY (CUSTOMERID));
    
CREATE TABLE customerschema.authority(
	ID INT(11) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(45) NULL,
	PRIMARY KEY (ID));

CREATE TABLE customerschema.user(
	ID INT(11) NOT NULL,
	USERNAME VARCHAR(50) NULL DEFAULT NULL,
	PASSWORD VARCHAR(255) NULL DEFAULT NULL,
	FIRSTNAME VARCHAR(45) NULL DEFAULT NULL,
	LASTNAME VARCHAR(45) NULL DEFAULT NULL,
	EMAIL VARCHAR(45) NULL DEFAULT NULL,
	ENABLED BIT(1) NULL DEFAULT NULL,
	PRIMARY KEY (ID));

CREATE TABLE customerschema.user_authority( 
	ID INT(11) NOT NULL AUTO_INCREMENT,
	USER_ID INT(11) NOT NULL,
	AUTHORITY_ID INT(11) NOT NULL,
	PRIMARY KEY (ID));

INSERT INTO customerschema.user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1);
INSERT INTO customerschema.user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1);
INSERT INTO customerschema.user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0);

INSERT INTO customerschema.authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO customerschema.authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO customerschema.user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO customerschema.user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO customerschema.user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO customerschema.user_authority (USER_ID, AUTHORITY_ID) VALUES (3, 1);

INSERT INTO customerschema.customer (ID, CUSTOMERID, DATE_OF_BIRTH, FIRST_NAME, LAST_NAME) VALUES (1, 'CUST-000001', '1980-01-05 00:00:00', 'Jack', 'Hermoso');
