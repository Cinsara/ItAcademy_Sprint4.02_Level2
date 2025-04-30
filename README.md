# 4.02 Level 2 - CRUD with MySQL Database

## Overview

This project demonstrates the implementation of a simple **CRUD** application using **Spring Boot** and a **MySQL database**. The application performs operations on an entity called "Fruita" (Fruit), following the **MVC (Model-View-Controller)** design pattern.

---

## 📦 Project Setup

Generate the Spring Boot project using (https://start.spring.io/) with the following configuration:

- **Project**: Maven or Gradle
- **Language**: Java
- **Spring Boot Version**: Latest stable release
- **Group**: `cat.itacademy.s04.t02.n02`
- **Artifact**: `S04T02N02`
- **Name**: `S04T02N02`
- **Description**: `S04T02N02`
- **Package Name**: `cat.itacademy.s04.t02.n02`
- **Packaging**: Jar
- **Java Version**: 11 (minimum)

### Dependencies

- Spring Web
- Spring Boot DevTools
- Spring Data JPA
- MySQL Driver

---

## ⚙️ Configuration

In the `application.properties` file (located in `src/main/resources`), add the following configuration for the MySQL database:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
Make sure to replace your_database_name, your_username, and your_password with your actual MySQL database credentials.

---

## 📂 Project Structure

The project follows the MVC pattern, and the following package structure is used:

```
cat.itacademy.s04.t02.n02
├── controllers
├── model
├── services
├── repository
└── exception
```

- Controllers: `cat.itacademy.s04.t02.n02.controllers`
- Model: `cat.itacademy.s04.t02.n02.model`
- Services: `cat.itacademy.s04.t02.n02.services`
- Repository: `cat.itacademy.s04.t02.n02.repository`
- Exceptions: `cat.itacademy.s04.t02.n02.exception`

---

## 🛠️ CRUD Operations

The application exposes the following endpoints for CRUD operations:

### 1. Add a new fruit

- Method: `POST`
- URL: `/fruita/add`

This endpoint accepts a POST request to add a new fruit to the database.

### 2. Update a fruit
   
- Method: `PUT`
- URL: `/fruita/update`

This endpoint accepts a PUT request to update the details of an existing fruit.

### 3. Delete a fruit

- Method: `DELETE`
- URL: `/fruita/delete/{id}`

This endpoint accepts a DELETE request to remove a fruit from the database, where {id} is the ID of the fruit to delete.

### 4. Get a fruit by ID

- Method: `GET`
- URL: `/fruita/getOne/{id}`

This endpoint accepts a GET request to retrieve a specific fruit by its ID.

### 5. Get all fruits

- Method: `GET`
- URL: `/fruita/getAll`
