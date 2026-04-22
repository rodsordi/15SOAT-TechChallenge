CREATE SCHEMA IF NOT EXISTS garage;

-- ==========================================
-- Table: garage.employee
-- ==========================================
CREATE TABLE garage.employee (
                                 id UUID PRIMARY KEY,
                                 name VARCHAR(55) NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.employee.id IS 'Employee id. Owner: postgres';
COMMENT ON COLUMN garage.employee.name IS 'Employee name. Owner: self';
COMMENT ON COLUMN garage.employee.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.employee.updated_at IS 'Register updated at. Owner: postgres';

-- ==========================================
-- Table: garage.estimate
-- ==========================================
CREATE TABLE garage.estimate (
                                 id UUID PRIMARY KEY,
                                 amount NUMERIC NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.estimate.id IS 'Estimate id. Owner: postgres';
COMMENT ON COLUMN garage.estimate.amount IS 'Estimate amount. Owner: self';
COMMENT ON COLUMN garage.estimate.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.estimate.updated_at IS 'Register updated at. Owner: postgres';

-- ==========================================
-- Table: garage.inventory
-- ==========================================
CREATE TABLE garage.inventory (
                                  id UUID PRIMARY KEY,
                                  name VARCHAR(55) NOT NULL,
                                  price NUMERIC NOT NULL,
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

-- ==========================================
-- Table: garage.shop_supply
-- ==========================================
CREATE TABLE garage.shop_supply (
                                    id UUID PRIMARY KEY,
                                    CONSTRAINT fk_shop_supply_inventory FOREIGN KEY (id) REFERENCES garage.inventory(id)
);

-- ==========================================
-- Table: garage.spare_part
-- ==========================================
CREATE TABLE garage.spare_part (
                                   id UUID PRIMARY KEY,
                                   CONSTRAINT fk_spare_part_inventory FOREIGN KEY (id) REFERENCES garage.inventory(id)
);

-- ==========================================
-- Table: garage.owner
-- ==========================================
CREATE TABLE garage.owner (
                              id UUID PRIMARY KEY,
                              name VARCHAR(55) NOT NULL,
                              email VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.owner.id IS 'Owner id. Owner: postgres';
COMMENT ON COLUMN garage.owner.name IS 'Owner name. Owner: self';
COMMENT ON COLUMN garage.owner.email IS 'Owner e-mail. Owner: self';
COMMENT ON COLUMN garage.owner.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.owner.updated_at IS 'Register updated at. Owner: postgres';

-- ==========================================
-- Table: garage.vehicle
-- ==========================================
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

-- ==========================================
-- Table: garage.work_order
-- ==========================================
CREATE TABLE garage.work_order (
                                   id UUID PRIMARY KEY,
                                   status VARCHAR(255),
                                   estimate_id UUID,
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP,
                                   CONSTRAINT fk_work_order_estimate FOREIGN KEY (estimate_id) REFERENCES garage.estimate(id)
);

COMMENT ON COLUMN garage.work_order.id IS 'WorkOrder id. Owner: postgres';
COMMENT ON COLUMN garage.work_order.status IS 'WorkOrder status. Owner: self';
COMMENT ON COLUMN garage.work_order.estimate_id IS 'Estimate id. Owner: postgres';
COMMENT ON COLUMN garage.work_order.created_at IS 'Register created at. Owner: postgres';
COMMENT ON COLUMN garage.work_order.updated_at IS 'Register updated at. Owner: postgres';