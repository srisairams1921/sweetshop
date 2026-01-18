
# 🍬 Sweet Shop Management System – Backend

A **RESTful backend application** for managing a Sweet Shop, built using **Spring Boot**.
The system provides **secure authentication**, **role-based authorization**, and **inventory management APIs** using **JWT**.

---

## 📌 Project Overview

This backend application acts as the core service for a Sweet Shop Management System.
It supports user registration and login, secure API access using JWT, and full CRUD operations for sweets inventory.

---

## 🛠 Tech Stack

* **Java**
* **Spring Boot**
* **Spring Security**
* **JWT (JSON Web Token)**
* **JPA / Hibernate**
* **MySQL**
* **Maven**
* **JUnit & Mockito**
* **Git**

---

## 🔐 Authentication & Authorization

* **JWT-based authentication**
* **Role-based access control**

  * `ROLE_USER`
  * `ROLE_ADMIN`
* Protected endpoints secured using Spring Security filters

---

## 🔗 API Endpoints

### Auth (Public)

```
POST /api/auth/register
POST /api/auth/login
```

### Sweets (Protected)

```
GET    /api/sweets
GET    /api/sweets/search
POST   /api/sweets                (ADMIN only)
PUT    /api/sweets/{id}           (ADMIN only)
DELETE /api/sweets/{id}           (ADMIN only)
POST   /api/sweets/{id}/purchase
POST   /api/sweets/{id}/restock   (ADMIN only)
```

---

## 🗂 Project Structure

```
sweetshop-backend
├── src
│   └── main
│       ├── java
│       │   └── com.sai.sweetshop
│       │       ├── SweetShopApplication.java
│       │       ├── controller
│       │       │   ├── AuthController.java
│       │       │   └── SweetController.java
│       │       ├── service
│       │       │   ├── AuthService.java
│       │       │   └── SweetService.java
│       │       ├── repository
│       │       │   ├── UserRepository.java
│       │       │   └── SweetRepository.java
│       │       ├── model
│       │       │   ├── User.java
│       │       │   └── Sweet.java
│       │       ├── dto
│       │       │   ├── LoginRequest.java
│       │       │   ├── RegisterRequest.java
│       │       │   ├── AuthResponse.java
│       │       │   └── QuantityRequest.java
│       │       └── security
│       │           ├── JwtUtil.java
│       │           ├── JwtFilter.java
│       │           └── SecurityConfig.java
│       └── resources
│           ├── application.properties
│           └── application.yml
│
├── src
│   └── test
│       └── java
│           └── com.sai.sweetshop
│               └── service
│                   └── SweetServiceTest.java
│
├── pom.xml
└── README.md
```

---

## ⚙️ How to Run Locally

### Prerequisites

* Java 17+
* MySQL
* Maven

### Steps

1. Clone the repository:

   ```bash
   git clone <your-github-repo-url>
   ```
2. Configure database in `application.properties`
3. Run the application:

   ```bash
   mvn spring-boot:run
   ```
4. Server starts at:

   ```
   http://localhost:8080
   ```

---

## 🧪 Testing

* Unit tests written using **JUnit 5** and **Mockito**
* Service layer tests cover:

  * Purchase logic
  * Restock logic
  * Delete validation
  * Search functionality
* Tests can be run using:

  ```bash
  mvn test
  ```

---



### Challenges Faced

* Implementing JWT authentication correctly
* Resolving repeated authorization issues
* Understanding Spring Security filter chains
* Writing meaningful unit tests for service logic

These challenges strengthened my backend development and debugging skills.

---

## 📎 Git & Version Control

* Git used for version control
* Frequent commits with descriptive messages
* AI co-authorship added where applicable as per policy

---

## 🚀 Project Status

* ✅ Backend fully implemented
* ✅ Secure authentication & authorization
* ✅ Unit testing completed
* 🚧 Deployment planned

---

## 👤 Author

**SRI SAI RAM S**

---


