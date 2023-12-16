DROP SCHEMA IF EXISTS bookstore CASCADE;

CREATE SCHEMA bookstore;

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS bookstore.bookstores CASCADE;

CREATE TABLE bookstore.bookstores
(
    id     uuid                                           NOT NULL,
    name   character varying COLLATE pg_catalog."default" NOT NULL,
    active boolean                                        NOT NULL,
    CONSTRAINT bookstores_pkey PRIMARY KEY (id)
);

DROP TYPE IF EXISTS approval_status;

CREATE TYPE approval_status AS ENUM ('APPROVED', 'REJECTED');

DROP TABLE IF EXISTS bookstore.order_approval CASCADE;

CREATE TABLE bookstore.order_approval
(
    id           uuid            NOT NULL,
    bookstore_id uuid            NOT NULL,
    order_id     uuid            NOT NULL,
    status       approval_status NOT NULL,
    CONSTRAINT order_approval_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS bookstore.products CASCADE;

CREATE TABLE bookstore.products
(
    id        uuid                                           NOT NULL,
    name      character varying COLLATE pg_catalog."default" NOT NULL,
    price     numeric(10, 2)                                 NOT NULL,
    available boolean                                        NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (id)
);

DROP TABLE IF EXISTS bookstore.bookstore_products CASCADE;

CREATE TABLE bookstore.bookstore_products
(
    id           uuid NOT NULL,
    bookstore_id uuid NOT NULL,
    product_id   uuid NOT NULL,
    CONSTRAINT bookstore_products_pkey PRIMARY KEY (id)
);

ALTER TABLE bookstore.bookstore_products
    ADD CONSTRAINT "FK_BOOKSTORE_ID" FOREIGN KEY (bookstore_id)
        REFERENCES bookstore.bookstores (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
    NOT VALID;

ALTER TABLE bookstore.bookstore_products
    ADD CONSTRAINT "FK_PRODUCT_ID" FOREIGN KEY (product_id)
        REFERENCES bookstore.products (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE RESTRICT
    NOT VALID;

DROP
MATERIALIZED VIEW IF EXISTS bookstore.order_bookstore_m_view;

CREATE
MATERIALIZED VIEW bookstore.order_bookstore_m_view
TABLESPACE pg_default
AS
SELECT r.id        AS bookstore_id,
       r.name      AS bookstore_name,
       r.active    AS bookstore_active,
       p.id        AS product_id,
       p.name      AS product_name,
       p.price     AS product_price,
       p.available AS product_available
FROM bookstore.bookstores r,
     bookstore.products p,
     bookstore.bookstore_products rp
WHERE r.id = rp.bookstore_id
  AND p.id = rp.product_id WITH DATA;

refresh
materialized VIEW bookstore.order_bookstore_m_view;

DROP function IF EXISTS bookstore.refresh_order_bookstore_m_view;

CREATE
OR replace function bookstore.refresh_order_bookstore_m_view()
returns trigger
AS '
BEGIN
    refresh materialized VIEW bookstore.order_bookstore_m_view;
    return null;
END;
'  LANGUAGE plpgsql;

DROP trigger IF EXISTS refresh_order_bookstore_m_view ON bookstore.bookstore_products;

CREATE trigger refresh_order_bookstore_m_view
    after INSERT OR
UPDATE OR
DELETE OR truncate
ON bookstore.bookstore_products FOR each statement
    EXECUTE PROCEDURE bookstore.refresh_order_bookstore_m_view();