/*
programmer Jakub Wawak
all rights reserved
kubawawak@gmail.com
version (from schema) v1.0
sql script makes tables for shoplifter database
*/

-- Table for stoing info about users.
-- DEPENDENCES: -
CREATE TABLE USER_INFO
(
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_login VARCHAR(30),
    user_password VARCHAR(30)
);
-- Table for stoing categories (in this schema categories are made on the startup.
-- DEPENDENCES: -
CREATE TABLE CATEGORY
(
	category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(30),
    category_note VARCHAR(30)
);
-- Table working as a contener for all of the categories and elements.
-- DEPENDENCES: user
CREATE TABLE DICTIONARY_USER
(
	dictionary_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    dictionary_date VARCHAR(30),
    CONSTRAINT fk_dictionary FOREIGN KEY (user_id) REFERENCES USER_INFO(user_id)
);
-- Table of settings for users
-- DEPENDENCES: user
CREATE TABLE SETTINGS
(
	settings_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    setting1 VARCHAR(100),
    setting2 VARCHAR(100),
    setting3 VARCHAR(20),
    CONSTRAINT fk_setting FOREIGN KEY (user_id) REFERENCES USER_INFO(user_id)
);
-- Table of shop list elements
-- DEPENDENCES: category,user,dictionary
CREATE TABLE ELEMENT
(
	element_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT,
    user_id INT,
    dictionary_id INT,
    element_name VARCHAR(30),
    element_note VARCHAR(100),
    element_valid INT,
    CONSTRAINT fk_element0 FOREIGN KEY (user_id) REFERENCES USER_INFO(user_id),
    CONSTRAINT fk_element1 FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id),
    CONSTRAINT fk_element2 FOREIGN KEY (dictionary_id) REFERENCES DICTIONARY_USER(dictionary_id)
);
-- Table of shop lists shared by users
-- DEPENDENCES: user, dictionary
CREATE TABLE SHOP_LIST
(
	shop_list_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    dictionary_id INT,
    element_ids VARCHAR(300),
    shop_list_note VARCHAR(100),
    CONSTRAINT fk_shop_list0 FOREIGN KEY (user_id) REFERENCES USER_INFO(user_id)
);
    
    
    
