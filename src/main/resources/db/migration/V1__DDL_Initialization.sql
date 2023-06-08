DROP TABLE IF EXISTS "user_roles" CASCADE ;
DROP TABLE IF EXISTS "order_detail" CASCADE ;
DROP TABLE IF EXISTS "shop_order" CASCADE ;
DROP TABLE IF EXISTS "order_status" CASCADE ;
DROP TABLE IF EXISTS "cart_item" CASCADE ;
DROP TABLE IF EXISTS "site_user" CASCADE ;
DROP TABLE IF EXISTS "user_role" CASCADE ;
DROP TABLE IF EXISTS "product" CASCADE ;
DROP TABLE IF EXISTS "gender" CASCADE ;
DROP TABLE IF EXISTS "category" CASCADE ;

CREATE TABLE IF NOT EXISTS "gender"
(
    "id"        BIGSERIAL,
    "gender"    VARCHAR(6) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "category"
(
    "id"          BIGSERIAL,
    "category"    VARCHAR(64) NOT NULL,
    "description" TEXT,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "product"
(
    "id"                BIGSERIAL,
    "name"              VARCHAR(255) NOT NULL,
    "description"       TEXT,
    "image"             VARCHAR(500),
    "quantity_in_stock" INTEGER,
    "is_adult"          BOOLEAN,
    "unit_price"        MONEY,
    "category_id"       BIGSERIAL,
    "gender_id"         BIGSERIAL,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_product.category_id"
        FOREIGN KEY ("category_id")
            REFERENCES "category" ("id"),
    CONSTRAINT "FK_product.gender_id"
        FOREIGN KEY ("gender_id")
            REFERENCES "gender" ("id")
);

CREATE TABLE IF NOT EXISTS "user_role"
(
    "id"   BIGSERIAL,
    "role" VARCHAR(32) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "order_detail"
(
    "id"                BIGSERIAL,
    "order_id"          BIGSERIAL,
    "product_id"        BIGSERIAL,
    "quantity"          INTEGER,
    "total_price"       MONEY,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_order_detail.product_id"
        FOREIGN KEY ("product_id")
            REFERENCES "product" ("id")
);

CREATE TABLE IF NOT EXISTS "order_status"
(
    "id"     BIGSERIAL,
    "status" VARCHAR(32),
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "site_user"
(
    "id"                BIGSERIAL,
    "first_name"        VARCHAR(64)  NOT NULL,
    "last_name"         VARCHAR(64)  NOT NULL,
    "email"             VARCHAR(255) NOT NULL,
    "password"          VARCHAR(255) NOT NULL,
    "phone_number"      VARCHAR(32),
    "date_of_birth"     DATE,
    "registration_date" DATE,
    "is_active"         BOOLEAN,
    PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS "cart_item"
(
    "id"                BIGSERIAL,
    "product_id"        BIGSERIAL,
    "quantity"          INTEGER,
    "user_id"           BIGSERIAL,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_cart_item.user_id"
        FOREIGN KEY ("user_id")
            REFERENCES "site_user" ("id"),
    CONSTRAINT "FK_cart_item.product_id"
        FOREIGN KEY ("product_id")
            REFERENCES "product" ("id")
);

CREATE TABLE IF NOT EXISTS "shop_order"
(
    "id"          BIGSERIAL,
    "customer_id" BIGSERIAL,
    "status_id"   BIGSERIAL,
    "created_at"  TIMESTAMP,
    "order_price" MONEY,
    "address"     TEXT,
    PRIMARY KEY ("id"),
    CONSTRAINT "FK_shop_order.status_id"
        FOREIGN KEY ("status_id")
            REFERENCES "order_status" ("id"),
    CONSTRAINT "FK_shop_order.customer_id"
        FOREIGN KEY ("customer_id")
            REFERENCES "site_user" ("id")
);

CREATE TABLE IF NOT EXISTS "user_roles"
(
    "user_id" BIGSERIAL,
    "role_id" BIGSERIAL,
    CONSTRAINT "FK_user_roles.user_id"
        FOREIGN KEY ("user_id")
            REFERENCES "site_user" ("id"),
    CONSTRAINT "FK_user_roles.role_id"
        FOREIGN KEY ("role_id")
            REFERENCES "user_role" ("id")
);
