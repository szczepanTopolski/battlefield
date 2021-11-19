--TODO CREATE TABLES, INSERT TEST DATA, OPTIONALY DISPLAY -LIMIT 1
DROP TABLE IF EXISTS ADDRESS_DETAILS;
DROP TABLE IF EXISTS GENDER;
DROP TABLE IF EXISTS ACCOUNT_DETAILS;
DROP TABLE IF EXISTS AUTHORITY;
DROP TABLE IF EXISTS ACCOUNT;
DROP TABLE IF EXISTS EMPLOYEE_DETAILS;
DROP TABLE IF EXISTS TASK;
DROP TABLE IF EXISTS LEVEL;
DROP TABLE IF EXISTS SPECIALIZATION;

CREATE TABLE ADDRESS_DETAILS(
    ID INT PRIMARY KEY NOT NULL,
    CITY VARCHAR(30) NOT NULL,
    STREET VARCHAR(50) NOT NULL,
    HOUSE_NUMBER INT NOT NULL,
    POST_CODE VARCHAR(30) NOT NULL);
CREATE TABLE GENDER(
    ID INT PRIMARY KEY NOT NULL,
    GENDER_NAME VARCHAR(30) NOT NULL);
CREATE TABLE AUTHORITY(
    ID INT PRIMARY KEY NOT NULL,
    AUTHORITY_NAME VARCHAR(30) NOT NULL);
CREATE TABLE ACCOUNT(
    ID INT PRIMARY KEY NOT NULL,
    AUTHORITY_ID INT NOT NULL UNIQUE,
    LOGIN VARCHAR(30) NOT NULL UNIQUE,
    PASSWORD VARCHAR(255) NOT NULL,
    FOREIGN KEY(AUTHORITY_ID) REFERENCES AUTHORITY(ID) ON DELETE CASCADE ON UPDATE NO ACTION);
CREATE TABLE ACCOUNT_DETAILS(
    ID INT PRIMARY KEY NOT NULL,
    EMAIL VARCHAR(30) NOT NULL UNIQUE,
    ADDRESS_ID INT NOT NULL UNIQUE,
    PHONE_NUMBER VARCHAR(20) NOT NULL UNIQUE,
    FIRST_NAME VARCHAR(50) NOT NULL,
    SURNAME VARCHAR(50) NOT NULL,
    BIRTHDATE VARCHAR(12) NOT NULL,
    GENDER_ID INT NOT NULL,
    ACCOUNT_ID INT NOT NULL,
    FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(ADDRESS_ID) REFERENCES ADDRESS_DETAILS(ID) ON DELETE CASCADE ON UPDATE NO ACTION,
    FOREIGN KEY(GENDER_ID) REFERENCES GENDER(ID) ON DELETE CASCADE ON UPDATE NO ACTION);
CREATE TABLE EMPLOYEE_DETAILS(
    ID INT PRIMARY KEY NOT NULL,
    EMPLOYEE_CODE VARCHAR(255) NOT NULL UNIQUE,
    NIP VARCHAR(255) NOT NULL UNIQUE,
    ACCOUNT_ID INT NOT NULL,
    FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID) ON DELETE CASCADE ON UPDATE NO ACTION);
CREATE TABLE TASK(
    ID INT PRIMARY KEY NOT NULL,
    STATUS VARCHAR(30) NOT NULL,
    TITLE VARCHAR(30) NOT NULL,
    DESCRIPTION VARCHAR(300) NOT NULL,
    DEADLINE VARCHAR(40) NOT NULL,
    GRADE VARCHAR(30) NOT NULL,
    ACCOUNT_ID INT NOT NULL,
    TAG VARCHAR(30) NOT NULL,
    FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID) ON DELETE CASCADE ON UPDATE NO ACTION);
CREATE TABLE LEVEL(
    ID INT PRIMARY KEY NOT NULL,
    LEVEL_NAME VARCHAR(40) NOT NULL,
    ACCOUNT_ID INT NOT NULL,
    FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID) ON DELETE CASCADE ON UPDATE NO ACTION);
CREATE TABLE SPECIALIZATION(
    ID INT PRIMARY KEY NOT NULL,
    SPECIALIZATION_NAME VARCHAR(40) NOT NULL,
    ACCOUNT_ID INT NOT NULL,
    FOREIGN KEY(ACCOUNT_ID) REFERENCES ACCOUNT(ID) ON DELETE CASCADE ON UPDATE NO ACTION);

INSERT INTO GENDER (ID,GENDER_NAME) VALUES(1, 'Male');
INSERT INTO GENDER (ID,GENDER_NAME) VALUES(2, 'Female');

INSERT INTO AUTHORITY(ID,AUTHORITY_NAME) VALUES(1,'Student');
INSERT INTO AUTHORITY(ID,AUTHORITY_NAME) VALUES(2,'Mentor');
INSERT INTO AUTHORITY(ID,AUTHORITY_NAME) VALUES(3, 'Office worker');
INSERT INTO AUTHORITY(ID,AUTHORITY_NAME) VALUES(4, 'Admin');

INSERT INTO ACCOUNT(ID,AUTHORITY_ID,LOGIN,PASSWORD) VALUES(1,1,'LOGIN1','123');
INSERT INTO ACCOUNT(ID,AUTHORITY_ID,LOGIN,PASSWORD) VALUES(2,2,'LOGIN2','123');
INSERT INTO ACCOUNT(ID,AUTHORITY_ID,LOGIN,PASSWORD) VALUES(3,3,'LOGIN3','123');
INSERT INTO ACCOUNT(ID,AUTHORITY_ID,LOGIN,PASSWORD) VALUES(4,4,'LOGIN4','123');

INSERT INTO ADDRESS_DETAILS (ID,CITY,STREET,HOUSE_NUMBER,POST_CODE) VALUES(1,'krakow','studencka',35,'31-116');
INSERT INTO ADDRESS_DETAILS (ID,CITY,STREET,HOUSE_NUMBER,POST_CODE) VALUES(2,'warszawa','lema',28,'35-156');
INSERT INTO ADDRESS_DETAILS (ID,CITY,STREET,HOUSE_NUMBER,POST_CODE) VALUES(3,'poznan','ulanow',15,'27-106');
INSERT INTO ADDRESS_DETAILS (ID,CITY,STREET,HOUSE_NUMBER,POST_CODE) VALUES(4,'gdansk','fiolkowa',7,'15-176');

INSERT INTO ACCOUNT_DETAILS(ID,EMAIL,ADDRESS_ID,PHONE_NUMBER,FIRST_NAME,SURNAME,BIRTHDATE,GENDER_ID, ACCOUNT_ID) VALUES(1,'Test1@gmail.com',1,'666-696-969','Sebastian','Koszcz','03-04-2002',1,1);
INSERT INTO ACCOUNT_DETAILS(ID,EMAIL,ADDRESS_ID,PHONE_NUMBER,FIRST_NAME,SURNAME,BIRTHDATE,GENDER_ID, ACCOUNT_ID) VALUES(2,'Test2@gmail.com',2,'666-696-961','Szczepan','Topol','27-05-1999',1,2);
INSERT INTO ACCOUNT_DETAILS(ID,EMAIL,ADDRESS_ID,PHONE_NUMBER,FIRST_NAME,SURNAME,BIRTHDATE,GENDER_ID, ACCOUNT_ID) VALUES(3,'Test3@gmail.com',3,'666-696-962','Martyna','Zjebczak','03-04-2002',2,3);
INSERT INTO ACCOUNT_DETAILS(ID,EMAIL,ADDRESS_ID,PHONE_NUMBER,FIRST_NAME,SURNAME,BIRTHDATE,GENDER_ID, ACCOUNT_ID) VALUES(4,'Test4@gmail.com',4,'666-696-963','Michal','Korzul','03-04-2002',1,4);

INSERT INTO EMPLOYEE_DETAILS(ID,EMPLOYEE_CODE,NIP,ACCOUNT_ID) VALUES(1,'123456','123456789',1);
INSERT INTO EMPLOYEE_DETAILS(ID,EMPLOYEE_CODE,NIP,ACCOUNT_ID) VALUES(2,'654321','987654321',2);
INSERT INTO EMPLOYEE_DETAILS(ID,EMPLOYEE_CODE,NIP,ACCOUNT_ID) VALUES(3,'324123','543612344',3);
INSERT INTO EMPLOYEE_DETAILS(ID,EMPLOYEE_CODE,NIP,ACCOUNT_ID) VALUES(4,'563864','123654345',4);

INSERT INTO TASK(ID,STATUS,TITLE,DESCRIPTION,DEADLINE,GRADE,ACCOUNT_ID,TAG) VALUES(1,'COMPLETED','SqliteDB','doit','tommorow','5','1','java');

INSERT INTO LEVEL(ID,LEVEL_NAME,ACCOUNT_ID) VALUES(1,'beginner',1);

INSERT INTO SPECIALIZATION(ID,SPECIALIZATION_NAME,ACCOUNT_ID) VALUES(1,'java',1);
INSERT INTO SPECIALIZATION(ID,SPECIALIZATION_NAME,ACCOUNT_ID) VALUES(2,'c#',2);

