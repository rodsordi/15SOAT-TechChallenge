-- Schema Creation
CREATE SCHEMA IF NOT EXISTS garage;

---
--- Table: authority
---
CREATE TABLE garage.authority (
                                  id UUID PRIMARY KEY,
                                  authority VARCHAR(20) NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.authority.id IS 'Authorization id. Owner: postgres';
COMMENT ON COLUMN garage.authority.authority IS 'Authorization name. Owner: self';
COMMENT ON COLUMN garage.authority.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.authority.updated_at IS 'Register updated at. Owner: postgres';

---
--- Table: user (Base table for JOINED inheritance)
---
CREATE TABLE garage.users (
                               id UUID PRIMARY KEY,
                               username VARCHAR(255) NOT NULL UNIQUE,
                               password VARCHAR(255) NOT NULL,
                               name VARCHAR(55) NOT NULL,
                               email VARCHAR(255) NOT NULL UNIQUE,
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.users.id IS 'User id. Owner: postgres';
COMMENT ON COLUMN garage.users.username IS 'User e-mail. Owner: self';
COMMENT ON COLUMN garage.users.password IS 'User password. Owner: self';
COMMENT ON COLUMN garage.users.name IS 'User name. Owner: self';
COMMENT ON COLUMN garage.users.email IS 'User e-mail. Owner: self';
COMMENT ON COLUMN garage.users.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.users.updated_at IS 'Register updated at. Owner: postgres';

---
--- Table: employee (JOINED subclass of user)
---
CREATE TABLE garage.employee (
                                 id UUID PRIMARY KEY REFERENCES garage.users(id),
                                 cpf VARCHAR(11) NOT NULL
);

---
--- Table: customer (JOINED subclass of user)
---
CREATE TABLE garage.customer (
                              id UUID PRIMARY KEY REFERENCES garage.users(id),
                              document VARCHAR(14) NOT NULL
);

---
--- Table: users_authority (ManyToMany Link)
---
CREATE TABLE garage.users_authority (
                                       user_id UUID NOT NULL REFERENCES garage.users(id),
                                       authority_id UUID NOT NULL REFERENCES garage.authority(id),
                                       PRIMARY KEY (user_id, authority_id)
);

---
--- Table: inventory (Base table for JOINED inheritance)
---
CREATE TABLE garage.inventory (
                                  id UUID PRIMARY KEY,
                                  name VARCHAR(55) NOT NULL,
                                  price DECIMAL(19,2) NOT NULL,
                                  quantity_in_stock INTEGER NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.inventory.id IS 'Material id. Owner: postgres';
COMMENT ON COLUMN garage.inventory.name IS 'Inventory name. Owner: self';
COMMENT ON COLUMN garage.inventory.price IS 'Inventory price. Owner: self';
COMMENT ON COLUMN garage.inventory.quantity_in_stock IS 'Inventory quantity in stock. Owner: self';
COMMENT ON COLUMN garage.inventory.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.inventory.updated_at IS 'Register updated at. Owner: postgres';

---
--- Table: shop_supply (JOINED subclass of inventory)
---
CREATE TABLE garage.shop_supply (
                                    id UUID PRIMARY KEY REFERENCES garage.inventory(id)
);

---
--- Table: spare_part (JOINED subclass of inventory)
---
CREATE TABLE garage.spare_part (
                                   id UUID PRIMARY KEY REFERENCES garage.inventory(id)
);

---
--- Table: estimate
---
CREATE TABLE garage.estimate (
                                 id BIGSERIAL PRIMARY KEY,
                                 amount DECIMAL(19,2) NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.estimate.id IS 'Estimate id. Owner: postgres';
COMMENT ON COLUMN garage.estimate.amount IS 'Estimate amount. Owner: self';
COMMENT ON COLUMN garage.estimate.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.estimate.updated_at IS 'Register updated at. Owner: postgres';

---
--- Table: vehicle
---
CREATE TABLE garage.vehicle (
                                id UUID PRIMARY KEY,
                                make VARCHAR(55) NOT NULL,
                                model VARCHAR(55) NOT NULL,
                                license_plate VARCHAR(10) NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.vehicle.id IS 'Vehicle id. Owner: postgres';
COMMENT ON COLUMN garage.vehicle.make IS 'Vehicle make. Owner: self';
COMMENT ON COLUMN garage.vehicle.model IS 'Vehicle model. Owner: self';
COMMENT ON COLUMN garage.vehicle.license_plate IS 'Vehicle license plate. Owner: self';
COMMENT ON COLUMN garage.vehicle.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.vehicle.updated_at IS 'Register updated at. Owner: postgres';

---
--- Table: work_order
---
CREATE TABLE garage.work_order (
                                   id UUID PRIMARY KEY,
                                   status VARCHAR(50),
                                   estimate_id BIGINT REFERENCES garage.estimate(id),
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.work_order.id IS 'WorkOrder id. Owner: postgres';
COMMENT ON COLUMN garage.work_order.status IS 'WorkOrder status. Owner: self';
COMMENT ON COLUMN garage.work_order.estimate_id IS 'Estimate id. Owner: postgres';
COMMENT ON COLUMN garage.work_order.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.work_order.updated_at IS 'Register updated at. Owner: postgres';