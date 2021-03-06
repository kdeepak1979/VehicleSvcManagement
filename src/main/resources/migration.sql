CREATE TABLE IF NOT EXISTS vehicle_manufacturer (
  ID SERIAL NOT NULL,
  NAME VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (ID));
  
CREATE TABLE IF NOT EXISTS vehicle (
  MODEL_ID VARCHAR(20) NOT NULL,
  MODEL_NAME VARCHAR(20) NULL DEFAULT NULL,
  MANUFACTURER_ID INT NOT NULL,
  SEATING_CAPACITY INT NOT NULL,
  FUEL_TYPE VARCHAR(20) NOT NULL,
  CLASS VARCHAR(20) NOT NULL,
  CHASSIS_NO VARCHAR(20) NOT NULL,
  ENGINE_NO VARCHAR(20) NOT NULL,
  PRIMARY KEY (CHASSIS_NO),
  FOREIGN KEY (MANUFACTURER_ID) REFERENCES vehicle_manufacturer (ID));
  
  CREATE TABLE IF NOT EXISTS customer (
  ID SERIAL NOT NULL,
  FIRST_NAME VARCHAR(45) NOT NULL,
  MIDDLE_NAME VARCHAR(45) NULL DEFAULT NULL,
  LAST_NAME VARCHAR(45) NULL DEFAULT NULL,
  DOB DATE NOT NULL,
  AADHAR_NO INT NULL DEFAULT NULL,
  PAN_NO VARCHAR(20) NOT NULL,
  PHONE VARCHAR(20) NOT NULL,
  EMAIL_ID VARCHAR(20) NOT NULL,
  PRIMARY KEY (ID));
  
  CREATE TABLE IF NOT EXISTS sales (
  INVOICE_ID VARCHAR(45) NOT NULL,
  INVOICE_DATE DATE NOT NULL,
  CHASSIS_NO VARCHAR(20) NOT NULL,
  PRICE FLOAT NOT NULL,  
  CUSTOMER_ID INT NOT NULL,
  PRIMARY KEY (INVOICE_ID),
  FOREIGN KEY (CUSTOMER_ID) REFERENCES customer(ID),  
  FOREIGN KEY (CHASSIS_NO) REFERENCES vehicle (CHASSIS_NO));
  
  CREATE TABLE IF NOT EXISTS vehicle_waranty (
  ID SERIAL NOT NULL,
  CHASSIS_NO VARCHAR(20) NOT NULL,  
  WARANTY_CARD BYTEA NOT NULL,
  PRIMARY KEY (ID),  
  FOREIGN KEY (CHASSIS_NO) REFERENCES vehicle (CHASSIS_NO));
  
  CREATE TABLE IF NOT EXISTS service_record (
  ID SERIAL NOT NULL,
  CHASSIS_NO VARCHAR(20) NOT NULL,
  SERVICE_DATE DATE NOT NULL,
  SERVICE_CENTRE VARCHAR(50),
  KM_RUN INT NOT NULL DEFAULT 0,	  
  PRIMARY KEY (ID),
  FOREIGN KEY (CHASSIS_NO) REFERENCES vehicle (CHASSIS_NO));