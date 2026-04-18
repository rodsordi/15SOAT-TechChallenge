-- Create Schema
CREATE SCHEMA IF NOT EXISTS garage;

-- 1. Table: Employee
CREATE TABLE garage.employee (
                                 id UUID PRIMARY KEY,
                                 name VARCHAR(55) NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP
);

COMMENT ON TABLE garage.employee IS 'Table storing employee data';
COMMENT ON COLUMN garage.employee.id IS 'Employee id. Owner: postgres';
COMMENT ON COLUMN garage.employee.name IS 'Employee name. Owner: self';
COMMENT ON COLUMN garage.employee.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.employee.updated_at IS 'Register updated at. Owner: postgres';

---

-- 2. Table: Estimate
CREATE TABLE garage.estimate (
                                 id UUID PRIMARY KEY,
                                 amount NUMERIC(19, 2) NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP
);

COMMENT ON TABLE garage.estimate IS 'Table storing service estimates';
COMMENT ON COLUMN garage.estimate.id IS 'Estimate id. Owner: postgres';
COMMENT ON COLUMN garage.estimate.amount IS 'Estimate amount. Owner: self';
COMMENT ON COLUMN garage.estimate.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.estimate.updated_at IS 'Register updated at. Owner: postgres';

---

-- 3. Table: Owner
CREATE TABLE garage.owner (
                              id UUID PRIMARY KEY,
                              name VARCHAR(55) NOT NULL,
                              email VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP
);

COMMENT ON TABLE garage.owner IS 'Table storing vehicle owner data';
COMMENT ON COLUMN garage.owner.id IS 'Owner id. Owner: postgres';
COMMENT ON COLUMN garage.owner.name IS 'Owner name. Owner: self';
COMMENT ON COLUMN garage.owner.email IS 'Owner e-mail. Owner: self';
COMMENT ON COLUMN garage.owner.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.owner.updated_at IS 'Register updated at. Owner: postgres';

---

-- 4. Table: SparePartAndShopSupply
CREATE TABLE garage.spare_part_and_shop_supply (
                                                   id UUID PRIMARY KEY,
                                                   name VARCHAR(55) NOT NULL,
                                                   price NUMERIC(19, 2) NOT NULL,
                                                   inventory INTEGER NOT NULL
);

COMMENT ON TABLE garage.spare_part_and_shop_supply IS 'Table storing parts and supplies inventory';
COMMENT ON COLUMN garage.spare_part_and_shop_supply.id IS 'SparePartAndShopSupply id. Owner: postgres';
COMMENT ON COLUMN garage.spare_part_and_shop_supply.name IS 'SparePartAndShopSupply name. Owner: self';
COMMENT ON COLUMN garage.spare_part_and_shop_supply.price IS 'SparePartAndShopSupply price. Owner: self';
COMMENT ON COLUMN garage.spare_part_and_shop_supply.inventory IS 'SparePartAndShopSupply inventory. Owner: self';

---

-- 5. Table: Vehicle
CREATE TABLE garage.vehicle (
                                id UUID PRIMARY KEY,
                                make VARCHAR(55) NOT NULL,
                                model VARCHAR(55) NOT NULL,
                                license_plate VARCHAR(10) NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP
);

COMMENT ON TABLE garage.vehicle IS 'Table storing vehicle information';
COMMENT ON COLUMN garage.vehicle.id IS 'Vehicle id. Owner: postgres';
COMMENT ON COLUMN garage.vehicle.make IS 'Vehicle make. Owner: self';
COMMENT ON COLUMN garage.vehicle.model IS 'Vehicle model. Owner: self';
COMMENT ON COLUMN garage.vehicle.license_plate IS 'Vehicle license plate. Owner: self';
COMMENT ON COLUMN garage.vehicle.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.vehicle.updated_at IS 'Register updated at. Owner: postgres';

---

-- 6. Table: WorkOrder
CREATE TABLE garage.work_order (
                                   id UUID PRIMARY KEY,
                                   status VARCHAR(50) DEFAULT 'RECEIVED',
                                   estimate_id UUID,
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP,
                                   CONSTRAINT fk_work_order_estimate FOREIGN KEY (estimate_id) REFERENCES garage.estimate(id)
);

COMMENT ON TABLE garage.work_order IS 'Table storing service work orders';
COMMENT ON COLUMN garage.work_order.id IS 'WorkOrder id. Owner: postgres';
COMMENT ON COLUMN garage.work_order.status IS 'WorkOrder status. Owner: self';
COMMENT ON COLUMN garage.work_order.estimate_id IS 'Estimate id. Owner: postgres';
COMMENT ON COLUMN garage.work_order.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.work_order.updated_at IS 'Register updated at. Owner: postgres';