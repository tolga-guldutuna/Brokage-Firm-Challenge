# Brokerage Firm Challenge - Order Management API

This project is a backend API developed for a brokerage firm, allowing employees to manage stock orders for their customers. The system includes features for creating, listing, and deleting stock orders, as well as depositing and withdrawing money for customers. The API is built using **Spring Boot** and **PostgreSQL**, with security enhancements such as **BCrypt** for password storage.

---

## Features

- **Create Order:** Allows employees to create new stock orders for customers (BUY/SELL).
- **List Orders:** Employees can list stock orders for a customer with filters such as date range.
- **Cancel Order:** Employees can cancel pending orders. Orders with other statuses cannot be canceled.
- **Deposit Money:** Deposit money (TRY) for a customer.
- **Withdraw Money:** Withdraw money (TRY) for a customer using their IBAN.
- **List Assets:** List all assets of a customer.

## Installation & Setup

### Prerequisites:
- **Java 17**
- **PostgreSQL**
- **Maven**

### Steps to Run:

1. **Clone the repository:**

    ```bash
    git clone https://github.com/yourusername/brokerage-firm-challenge.git
    cd brokerage-firm-challenge
    ```

2. **Configure PostgreSQL Database:**

    Create a PostgreSQL database and configure the `application.yml` file with your PostgreSQL credentials.

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
        show-sql: true
    ```

3. **Run the project using Maven:**

    ```bash
    mvn spring-boot:run
    ```

4. **Access the API via Swagger UI:**

    After starting the application, you can access the API documentation and test endpoints at:

    ```
    http://localhost:8585/OrderManagement/swagger-ui/index.html
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
