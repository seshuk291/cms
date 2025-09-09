CREATE TABLE IF NOT EXISTS roles(
    id  BIGSERIAL PRIMARY KEY,
    name VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS users(
    id BIGSERIAL PRIMARY KEY,
    username varchar(50) UNIQUE NOT NULL,
    firstname varchar(50) NOT NULL,
    lastname varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    password_hash varchar,
    created_at DATE,
    updated_at DATE,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    role_id_fk bigint REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS products(
    id BIGSERIAL PRIMARY KEY,
    name varchar(50) NOT NULL,
    description varchar,
    price double precision NOT NULL,
    stock_quantity int DEFAULT 0,
    created_at DATE,
    updated_at DATE
);

CREATE TABLE IF NOT EXISTS categories(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    created_at DATE,
    updated_at DATE,
    parent_category_id_fk BIGINT REFERENCES categories(id),
    sub_category_id_fk BIGINT REFERENCES categories(id),
    is_active BOOLEAN DEFAULT TRUE,
    seo_metadata JSONB
);

CREATE TABLE IF NOT EXISTS product_categories (
    id BIGSERIAL PRIMARY KEY,
    product_id_fk BIGINT REFERENCES products(id),
    category_id_fk BIGINT REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS address(
    id BIGSERIAL PRIMARY KEY,
    address1 VARCHAR(255),
    address2 VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    zip VARCHAR(50),
    country VARCHAR(50),
    is_active BOOLEAN DEFAULT FALSE,
    user_id_fk BIGINT REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS payment_methods(
    id BIGSERIAL PRIMARY KEY,
    payment_type VARCHAR(50),
    payment_processor VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS transactions(
    id BIGSERIAL PRIMARY KEY,
    status BOOLEAN,
    payment_method_id_fk BIGINT REFERENCES payment_methods(id),
    total_amount DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS orders(
    id BIGSERIAL PRIMARY KEY,
    user_id_fk BIGINT REFERENCES users(id),
    order_date DATE NOT NULL,
    status VARCHAR(50),
    total_amount DOUBLE PRECISION NOT NULL,
    shipping_address_id_fk BIGINT REFERENCES address(id),
    billing_address_id_fk BIGINT REFERENCES address(id),
    created_at DATE,
    updated_at DATE,
    transaction_id_fk BIGINT REFERENCES transactions(id)
);

CREATE TABLE IF NOT EXISTS order_items(
    id BIGSERIAL PRIMARY KEY,
    order_id_fk BIGINT REFERENCES orders(id),
    product_id_fk BIGINT REFERENCES orders(id),
    quantity INT NOT NULL,
    unit_price DOUBLE PRECISION,
    total DOUBLE PRECISION,
    created_at DATE,
    updated_at DATE
);