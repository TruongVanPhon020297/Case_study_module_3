CREATE TABLE category(
	id INT AUTO_INCREMENT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id)
);

CREATE TABLE product(
	id INT AUTO_INCREMENT NOT NULL,
    title NVARCHAR(255) NOT NULL,
    url_image NVARCHAR(255) NOT NULL,
    price DECIMAL(12,0) DEFAULT 0,
    quantity DECIMAL(12,0) DEFAULT 0,
    created_at DATETIME NOT NULL,
    updated_at DATETIME,
    stop_selling INT(1),
    id_category INT,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_id_category FOREIGN KEY(id_category) REFERENCES category(id)
);

CREATE TABLE _user(
	id INT AUTO_INCREMENT NOT NULL,
    full_name NVARCHAR(255) NOT NULL,
    mobile NVARCHAR(10),
    email NVARCHAR(255) NOT NULL,
    password_user NVARCHAR(255) NOT NULL,
    registered_at DATETIME NOT NULL,
    updated_at DATETIME,
    _admin BIT(1) NOT NULL,
    _status BIT(1) NOT NULL,
    url_image NVARCHAR(255),
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT uq_email UNIQUE(email),
    CONSTRAINT uq_mobile UNIQUE (mobile)
);

CREATE TABLE _order(
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
    id_user INT,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_id_user FOREIGN KEY(id_user) REFERENCES _user(id)
);

CREATE TABLE oder_item(
	id INT AUTO_INCREMENT NOT NULL,
	id_order INT NOT NULL,
    id_product INT NOT NULL,
    quantity DECIMAL(12,0) DEFAULT 0,
    price DECIMAL(12,0) DEFAULT 0,
    created_at DATETIME NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_id_order FOREIGN KEY(id_order) REFERENCES _order(id),
    CONSTRAINT fk_id_product FOREIGN KEY(id_product) REFERENCES product(id)
);






















