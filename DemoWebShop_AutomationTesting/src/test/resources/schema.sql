DROP TABLE IF EXISTS login_data;
DROP TABLE IF EXISTS registration_data;
DROP TABLE IF EXISTS product_data;
DROP TABLE IF EXISTS product_search;
DROP TABLE IF EXISTS checkout_data;

CREATE TABLE login_data (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100),
    password VARCHAR(100),
    expected_result VARCHAR(50)
);

INSERT INTO login_data(email, password, expected_result)
VALUES
('siddhi24104@gmail.com', 'Sid@DWS', 'Valid'),
('sid42@gmail.com', 'Sidm#', 'Invalid');

CREATE TABLE registration_data (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    password VARCHAR(100),
    confirm_password VARCHAR(100),
    expected_result VARCHAR(50)
);

INSERT INTO registration_data(first_name, last_name, email, password, confirm_password, expected_result)
VALUES
('Siddhi', 'More', 'sidtest001@gmail.com', 'Sid@123', 'Sid@123', 'Valid'),
('', 'More', 'sidtest002@gmail.com', 'Sid@123', 'Sid@123', 'Invalid');

CREATE TABLE product_data (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(200),
    category_name VARCHAR(100)
);

INSERT INTO product_data(product_name, category_name)
VALUES
('Computing and Internet', 'Books'),
('14.1-inch Laptop', 'Computers'),
('50''s Rockabilly Polka Dot Top JR Plus Size', 'Apparel & Shoes'),
('Diamond Tennis Bracelet', 'Jewelry'),
('Blue and green Sneaker', 'Apparel & Shoes'),
('Blue Jeans', 'Apparel & Shoes'),
('Denim Short with Rhinestones', 'Apparel & Shoes');

CREATE TABLE product_search (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(100),
    expected_value VARCHAR(20)
);

INSERT INTO product_search(product_name, expected_value)
VALUES
('jeans', 'Valid'),
('computer', 'Valid'),
('xyzabc', 'Invalid'),
('marketplace', 'Invalid');

CREATE TABLE checkout_data (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    country VARCHAR(100),
    city VARCHAR(100),
    address1 VARCHAR(200),
    zip_code VARCHAR(20),
    phone VARCHAR(20)
);

INSERT INTO checkout_data(first_name, last_name, email, country, city, address1, zip_code, phone)
VALUES
('Siddhi', 'More', 'siddhi24104@gmail.com', 'India', 'Mumbai', '45, Rose Villa, Bandra East', '412005', '1234567899');