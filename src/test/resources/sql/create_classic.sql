CREATE TABLE CUSTOMERS
(
    CUSTOMERNUMBER INT,
    CUSTOMERNAME VARCHAR(50),
    CONTACTLASTNAME VARCHAR(50),
    CONTACTFIRSTNAME VARCHAR(50),
    PHONE VARCHAR(50),
    ADDRESSLINE1 VARCHAR(50),
    ADDRESSLINE2 VARCHAR(50),
    CITY VARCHAR(50),
    STATE VARCHAR(50),
    POSTALCODE VARCHAR(15),
    COUNTRY VARCHAR(50),
    SALESREPEMPLOYEENUMBER INT,
    CREDITLIMIT FLOAT(52)
);
CREATE TABLE EMPLOYEES
(
    EMPLOYEENUMBER INT,
    LASTNAME VARCHAR(50),
    FIRSTNAME VARCHAR(50),
    EXTENSION VARCHAR(10),
    EMAIL VARCHAR(100),
    OFFICECODE VARCHAR(10),
    REPORTSTO INT,
    JOBTITLE VARCHAR(50)
);
CREATE TABLE OFFICES
(
    OFFICECODE VARCHAR(10),
    CITY VARCHAR(50),
    PHONE VARCHAR(50),
    ADDRESSLINE1 VARCHAR(50),
    ADDRESSLINE2 VARCHAR(50),
    STATE VARCHAR(50),
    COUNTRY VARCHAR(50),
    POSTALCODE VARCHAR(15),
    TERRITORY VARCHAR(10)
);
CREATE TABLE ORDERDETAILS
(
    ORDERNUMBER INT,
    PRODUCTCODE VARCHAR(15),
    QUANTITYORDERED INT,
    PRICEEACH FLOAT(52),
    ORDERLINENUMBER SMALLINT
);
CREATE TABLE ORDERS
(
    ORDERNUMBER INT,
    ORDERDATE DATE,
    REQUIREDDATE DATE,
    SHIPPEDDATE DATE,
    STATUS VARCHAR(15),
    COMMENTS LONG VARCHAR,
    CUSTOMERNUMBER INT
);
CREATE TABLE PAYMENTS
(
    CUSTOMERNUMBER INT,
    CHECKNUMBER VARCHAR(50),
    PAYMENTDATE DATE,
    AMOUNT FLOAT(52)
);
CREATE TABLE PRODUCTLINES
(
    PRODUCTLINE VARCHAR(50),
    TEXTDESCRIPTION VARCHAR(4000),
    HTMLDESCRIPTION CLOB(1073741823),
    IMAGE BLOB(1073741823)
);
CREATE TABLE PRODUCTS
(
    PRODUCTCODE VARCHAR(15),
    PRODUCTNAME VARCHAR(70),
    PRODUCTLINE VARCHAR(50),
    PRODUCTSCALE VARCHAR(10),
    PRODUCTVENDOR VARCHAR(50),
    PRODUCTDESCRIPTION LONG VARCHAR,
    QUANTITYINSTOCK INT,
    BUYPRICE FLOAT(52),
    MSRP FLOAT(52)
);
CREATE UNIQUE INDEX CUSTOMERS_PK ON CUSTOMERS (CUSTOMERNUMBER);
CREATE UNIQUE INDEX EMPLOYEES_PK ON EMPLOYEES (EMPLOYEENUMBER);
CREATE UNIQUE INDEX OFFICES_PK ON OFFICES (OFFICECODE);
CREATE UNIQUE INDEX ORDERDETAILS_PK ON ORDERDETAILS (ORDERNUMBER, PRODUCTCODE);
CREATE UNIQUE INDEX ORDERS_PK ON ORDERS (ORDERNUMBER);
CREATE INDEX ORDERS_CUTOMER ON ORDERS (CUSTOMERNUMBER);
CREATE UNIQUE INDEX PAYMENTS_PK ON PAYMENTS (CUSTOMERNUMBER, CHECKNUMBER);
CREATE UNIQUE INDEX PRODUCTLINES_PK ON PRODUCTLINES (PRODUCTLINE);
CREATE UNIQUE INDEX PRODUCTS_PK ON PRODUCTS (PRODUCTCODE);
