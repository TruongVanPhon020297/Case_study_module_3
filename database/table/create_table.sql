CREATE TABLE categories(
	id INT AUTO_INCREMENT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id)
);

CREATE TABLE products(
	id INT AUTO_INCREMENT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    url_image NVARCHAR(255) NOT NULL,
    price DECIMAL(12,0) DEFAULT 0,
    quantity DECIMAL(12,0) DEFAULT 0,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    stop_selling INT(1),
    category_id INT,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_category_id FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE users(
	id INT AUTO_INCREMENT NOT NULL,
    full_name NVARCHAR(255) NOT NULL,
    mobile NVARCHAR(10),
    email NVARCHAR(255) NOT NULL,
    address NVARCHAR(255) NOT NULL,
    password_user NVARCHAR(255) NOT NULL,
    registered_at DATETIME NOT NULL,
    updated_at DATETIME,
    is_admin TINYINT(1) NOT NULL,
    is_active TINYINT(1) NOT NULL,
    url_image NVARCHAR(255),
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT uq_email UNIQUE(email),
    CONSTRAINT uq_mobile UNIQUE (mobile)
);

CREATE TABLE orders(
	id INT AUTO_INCREMENT NOT NULL,
    full_name NVARCHAR(255) NOT NULL,
    email NVARCHAR(255) NOT NULL,
    mobile BIGINT(10),
    order_date DATETIME NOT NULL,
    delivery_date DATETIME NOT NULL,
    delivery_address NVARCHAR(255) NOT NULL,
    grand_total DECIMAL(12) DEFAULT 0,
    created_at DATETIME,
    updated_at DATETIME,
    user_id INT,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE oder_items(
	id INT AUTO_INCREMENT NOT NULL,
	order_id INT NOT NULL,
    product_id INT NOT NULL,
    price DECIMAL(12,0) DEFAULT 0,
    quantity DECIMAL(12,0) DEFAULT 0,
    total_price DECIMAL(12,0) DEFAULT 0,
    created_at DATETIME NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_order_id FOREIGN KEY(order_id) REFERENCES orders(id),
    CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES products(id)
);

CREATE TABLE carts(
    id INT AUTO_INCREMENT NOT NULL,
    product_id INT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    price DECIMAL(12,0) DEFAULT 0,
    quantity DECIMAL(12,0) DEFAULT 0,
    total_price DECIMAL(12,0) DEFAULT 0,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_product_id FOREIGN KEY(product_id) REFERENCES products(id)
);






















