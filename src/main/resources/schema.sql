DROP TABLE  IF EXISTS customer;
CREATE  TABLE customer (
  cust_id INT AUTO_INCREMENT PRIMARY KEY ,
  cust_name VARCHAR (20),
  cust_email VARCHAR (50),
  cust_dob DATE
);