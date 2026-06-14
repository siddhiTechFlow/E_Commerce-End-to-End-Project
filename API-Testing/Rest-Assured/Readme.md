# REST Assured API Automation Testing

## Project Overview

This project contains API automation test cases developed using REST Assured, Java, Maven, and TestNG.

The objective of this project was to automate the APIs that were previously tested manually using Postman. The APIs belong to an E-Commerce application and cover modules such as Authentication, Products, Cart, Wishlist, Orders, Coupons, User Profile, and Address Management.

The project includes both real API testing using DummyJSON APIs and mock API testing using Postman Mock Server.

---

## Tools & Technologies Used

* Java
* REST Assured
* Maven
* TestNG
* Hamcrest
* Extent Reports
* Postman Mock Server
* Git & GitHub

---

## Framework Design

The framework is designed using a layered approach to keep the code organized and maintainable.

### Base Layer

* BaseTest
* Base URL Initialization
* Environment Configuration

### Endpoint Layer

This layer contains all API endpoints separated according to request type.

* GET_Request
* POST_Request
* PUT_Request
* DELETE_Request

### Test Layer

This layer contains all the automated test cases.

* GET_RequestTest
* POST_RequestTest
* PUT_RequestTest
* DELETE_RequestTest

### Utilities

* Token Manager
* Extent Report Utility
* Screenshot Utility

### Reporting

* Extent Reports
* TestNG Reports

---

## APIs Automated

### Authentication

* User Registration
* User Login
* Send Reset Password Email
* Get Current User Profile

### Products

* List All Products
* Get Product By ID
* List Categories

### Cart

* Add Item To Cart
* Get Current User Cart
* Update Cart Quantity
* Remove Item From Cart

### Wishlist

* Add Product To Wishlist
* Get Wishlist

### Orders

* Place Order
* Get All Orders
* Get Order Details
* Cancel Order

### Coupons

* Validate Coupon

### User Management

* Update User Profile
* Add Delivery Address

---

## Validations Performed

The following validations were implemented using REST Assured and Hamcrest Assertions:

* Status Code Validation
* Response Body Validation
* JSON Path Validation
* Data Verification
* Positive Scenario Validation
* Negative Scenario Validation
* Business Logic Validation

---

## Author

Siddhi More



