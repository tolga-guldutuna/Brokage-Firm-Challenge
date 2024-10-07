# Brokerage Firm Challenge - Order Management API

This project is a backend API developed for a brokerage firm to manage customer stock orders. Employees can create, list, and cancel orders, as well as deposit and withdraw money. The system is built using **Spring Boot** and integrates with **PostgreSQL** or **H2** for database management. **BCrypt** is used for secure password storage.

---

## Features

- **Create Order:** Allows employees to create stock orders (BUY/SELL) for customers.
- **List Orders:** List customer stock orders with filters like date range.
- **Cancel Order:** Cancel pending orders only.
- **Deposit Money:** Deposit TRY for a customer.
- **Withdraw Money:** Withdraw TRY for a customer using IBAN.
- **List Assets:** List customer assets.

---

## Installation & Setup

### Prerequisites:
- **Java 17**
- **PostgreSQL** or **H2 Database**
- **Maven**

### Steps to Run:

1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/brokerage-firm-challenge.git
    cd brokerage-firm-challenge
    ```

2. **Configure Database (PostgreSQL or H2):**

   For **PostgreSQL**, update `application.yml` with your PostgreSQL credentials:
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/your_database_name
        username: your_username
        password: your_password
        driver-class-name: org.postgresql.Driver
      jpa:
        hibernate:
          ddl-auto: update
        properties:
          hibernate:
            dialect: org.hibernate.dialect.PostgreSQLDialect
    ```

   For **H2**, you can use the default configuration:
    ```yaml
    spring:
      datasource:
        url: jdbc:h2:mem:testdb
        driver-class-name: org.h2.Driver
        username: sa
        password: password
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
    ```

3. **Run the project:**
    ```bash
    mvn spring-boot:run
    ```

4. **Access API Documentation (Swagger):**
    ```url
    http://localhost:8585/OrderManagement/swagger-ui/index.html
    ```

5. **Access H2 Console (if using H2):**
    ```url
    http://localhost:8585/OrderManagement/h2-console
    ```

---

## Security

Passwords in the system are securely stored using **BCrypt** hashing. Below is an example of some users with their hashed passwords:

| User           | Plain Password | BCrypt Hashed Password                                            |
|----------------|----------------|------------------------------------------------------------------|
| Ahmet Yılmaz   | password1      | `$2a$10$DCgXaPzFfrsQbe.PiBbQfue2CtDUpQzYQkGp5mdLf8CrpFcZQGm1m`  |
| Ayşe Demir     | password2      | `$2a$10$BtxqGJCIJ3XLpIDmTh0A7OofEsyj7kIzt1DPybBB.t9Ug1/nr.jDa`  |
| Mehmet Kara    | password3      | `$2a$10$sIcduL6Gv7Gyt6DrhbzAG.m1qfDLWDUiPyXjFHf5wsL3Es5isW/R2`  |
| Fatma Şahin    | password4      | `$2a$10$L1AfIjXMIhVqBfInhy9kHuGqufiD1ykT1soNBZPVavppizjtrjzC2`  |
| Hasan Özkan    | password5      | `$2a$10$yScUSJvaySAnNimNoY8GNOShXEdqwdkZSRPY.BSQt57sSgrzUlqPO`  |

---

## Database Schema

### Tables:
- **Customers:** Stores customer details with hashed passwords.
- **Assets:** Stores customer assets like TRY.
- **Orders:** Stores customer stock orders including BUY/SELL transactions.

### Sample Insert Data:

You can use the following data to populate your database:

#### Customers Table:

```sql
INSERT INTO customers (uid, name, email, password) VALUES
('123e4567-e89b-12d3-a456-426614174001', 'Ahmet Yılmaz', 'ahmet.yilmaz@hotmail.com', '$2a$10$DCgXaPzFfrsQbe.PiBbQfue2CtDUpQzYQkGp5mdLf8CrpFcZQGm1m'),
('123e4567-e89b-12d3-a456-426614174002', 'Ayşe Demir', 'ayse.demir@hotmail.com', '$2a$10$BtxqGJCIJ3XLpIDmTh0A7OofEsyj7kIzt1DPybBB.t9Ug1/nr.jDa');
