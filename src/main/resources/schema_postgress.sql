CREATE DATABASE vehicle_svc

SET search_path = public;

CREATE TABLE IF NOT EXISTS app_user (  
  name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  is_locked BOOLEAN NULL DEFAULT FALSE,
  is_expired BOOLEAN NULL DEFAULT FALSE,
  is_enabled BOOLEAN NULL DEFAULT TRUE,
  PRIMARY KEY (name));
  
CREATE TABLE IF NOT EXISTS role (
  id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (name));
  
CREATE TABLE IF NOT EXISTS user_role (
  id INT NOT NULL,
  name VARCHAR(45) NOT NULL,
  role INT NOT NULL,
   FOREIGN KEY (name) REFERENCES app_user(name),
   FOREIGN KEY (role) REFERENCES role(id)
 );
 
 CREATE TABLE IF NOT EXISTS dealer_detail(
  id INT NULL,
  name VARCHAR(45) NOT NULL,
  address VARCHAR(200) NOT NULL,
  phone CHAR(10) NOT NULL,
  dealer_id VARCHAR(45) NULL,
  PRIMARY KEY (id),
  UNIQUE (dealer_id),
  FOREIGN KEY (dealer_id) REFERENCES app_user (name)
  ON DELETE CASCADE
  ON UPDATE NO ACTION);