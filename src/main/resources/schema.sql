-- Create employees table
CREATE TABLE employees (
                           uid VARCHAR(36) PRIMARY KEY,
                           name VARCHAR(255),
                           email VARCHAR(255) UNIQUE,
                           password VARCHAR(255)
);

-- Create customers table
CREATE TABLE customers (
                           uid VARCHAR(36) PRIMARY KEY,
                           name VARCHAR(255),
                           email VARCHAR(255) UNIQUE,
                           password VARCHAR(255)
);

-- Create assets table
CREATE TABLE assets (
                        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        uid VARCHAR(36) UNIQUE NOT NULL,
                        customer_uid VARCHAR(36) NOT NULL,
                        asset_name VARCHAR(255),
                        size DECIMAL(19, 2),
                        usable_size DECIMAL(19, 2),
                        CONSTRAINT fk_customer_uid FOREIGN KEY (customer_uid) REFERENCES customers(uid)
);

-- Create orders table
CREATE TABLE orders (
                        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        uid VARCHAR(36) UNIQUE NOT NULL,
                        customer_uid VARCHAR(36) NOT NULL,
                        asset_name VARCHAR(255),
                        order_side VARCHAR(10), -- BUY or SELL
                        size DECIMAL(19, 2),
                        price DECIMAL(19, 2),
                        status VARCHAR(10), -- PENDING, MATCHED, CANCELED
                        create_date TIMESTAMP,
                        CONSTRAINT fk_customer_order_uid FOREIGN KEY (customer_uid) REFERENCES customers(uid)
);
