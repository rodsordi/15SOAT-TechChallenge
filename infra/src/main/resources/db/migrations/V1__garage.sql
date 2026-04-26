-- ==========================================
-- SCHEMA DEFINITION
-- ==========================================
CREATE SCHEMA IF NOT EXISTS garage;

-- ==========================================
-- TABLE: authority
-- ==========================================
CREATE TABLE garage.authority (
                                  id UUID PRIMARY KEY,
                                  authority VARCHAR(20) NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.authority.id IS 'Authorization id. Owner: db';
COMMENT ON COLUMN garage.authority.authority IS 'Authorization name. Owner: self';
COMMENT ON COLUMN garage.authority.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.authority.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: users
-- ==========================================
CREATE TABLE garage.users (
                              id UUID PRIMARY KEY,
                              username VARCHAR(255) NOT NULL UNIQUE,
                              password VARCHAR(255) NOT NULL,
                              name VARCHAR(55) NOT NULL,
                              email VARCHAR(255) NOT NULL UNIQUE,
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.users.id IS 'User id. Owner: db';
COMMENT ON COLUMN garage.users.username IS 'User e-mail. Owner: self';
COMMENT ON COLUMN garage.users.password IS 'User password. Owner: self';
COMMENT ON COLUMN garage.users.name IS 'User name. Owner: self';
COMMENT ON COLUMN garage.users.email IS 'User e-mail. Owner: self';
COMMENT ON COLUMN garage.users.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.users.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: users_authority (Join Table)
-- ==========================================
CREATE TABLE garage.users_authority (
                                        user_id UUID NOT NULL,
                                        authority_id UUID NOT NULL,
                                        PRIMARY KEY (user_id, authority_id),
                                        CONSTRAINT fk_users_authority_user FOREIGN KEY (user_id) REFERENCES garage.users(id),
                                        CONSTRAINT fk_users_authority_auth FOREIGN KEY (authority_id) REFERENCES garage.authority(id)
);

-- ==========================================
-- TABLE: customer
-- ==========================================
CREATE TABLE garage.customer (
                                 id UUID PRIMARY KEY,
                                 document VARCHAR(14) NOT NULL,
                                 CONSTRAINT fk_customer_user FOREIGN KEY (id) REFERENCES garage.users(id)
);

COMMENT ON COLUMN garage.customer.document IS 'Customer document. Owner: self';

-- ==========================================
-- TABLE: employee
-- ==========================================
CREATE TABLE garage.employee (
                                 id UUID PRIMARY KEY,
                                 cpf VARCHAR(11) NOT NULL,
                                 CONSTRAINT fk_employee_user FOREIGN KEY (id) REFERENCES garage.users(id)
);

COMMENT ON COLUMN garage.employee.cpf IS 'Customer document. Owner: self';

-- ==========================================
-- TABLE: material
-- ==========================================
CREATE TABLE garage.material (
                                 id UUID PRIMARY KEY,
                                 name VARCHAR(55) NOT NULL,
                                 type VARCHAR(55) NOT NULL,
                                 description TEXT,
                                 amount NUMERIC NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.material.id IS 'Inventory id. Owner: db';
COMMENT ON COLUMN garage.material.name IS 'Material name. Owner: self';
COMMENT ON COLUMN garage.material.type IS 'Material type. Owner: self';
COMMENT ON COLUMN garage.material.description IS 'Material description. Owner: self';
COMMENT ON COLUMN garage.material.amount IS 'Material amount. Owner: self';
COMMENT ON COLUMN garage.material.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.material.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: inventory_material
-- ==========================================
CREATE TABLE garage.inventory_material (
                                           id UUID PRIMARY KEY,
                                           quantity_in_stock INTEGER NOT NULL,
                                           reserved_quantity INTEGER NOT NULL,
                                           created_at TIMESTAMP NOT NULL,
                                           updated_at TIMESTAMP,
                                           CONSTRAINT fk_inventory_material_mat FOREIGN KEY (id) REFERENCES garage.material(id)
);

COMMENT ON COLUMN garage.inventory_material.id IS 'Inventory id. Owner: db';
COMMENT ON COLUMN garage.inventory_material.quantity_in_stock IS 'Inventory quantity in stock. Owner: self';
COMMENT ON COLUMN garage.inventory_material.reserved_quantity IS 'Inventory reserved quantity. Owner: self';
COMMENT ON COLUMN garage.inventory_material.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.inventory_material.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: service
-- ==========================================
CREATE TABLE garage.service (
                                id UUID PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                description TEXT,
                                amount NUMERIC NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.service.id IS 'Service id. Owner: db';
COMMENT ON COLUMN garage.service.name IS 'Service name. Owner: self';
COMMENT ON COLUMN garage.service.description IS 'Service description. Owner: self';
COMMENT ON COLUMN garage.service.amount IS 'Service amount. Owner: self';
COMMENT ON COLUMN garage.service.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.service.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: service_inventory_material (Join Table)
-- ==========================================
CREATE TABLE garage.service_inventory_material (
                                                   service_id UUID NOT NULL,
                                                   inventory_material_id UUID NOT NULL,
                                                   PRIMARY KEY (service_id, inventory_material_id),
                                                   CONSTRAINT fk_sim_service FOREIGN KEY (service_id) REFERENCES garage.service(id),
                                                   CONSTRAINT fk_sim_material FOREIGN KEY (inventory_material_id) REFERENCES garage.material(id)
);

-- ==========================================
-- TABLE: vehicle
-- ==========================================
CREATE TABLE garage.vehicle (
                                id UUID PRIMARY KEY,
                                make VARCHAR(55) NOT NULL,
                                model VARCHAR(55) NOT NULL,
                                license_plate VARCHAR(10) NOT NULL,
                                manufacture_year INTEGER NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP
);

COMMENT ON COLUMN garage.vehicle.id IS 'Vehicle id. Owner: db';
COMMENT ON COLUMN garage.vehicle.make IS 'Vehicle make. Owner: self';
COMMENT ON COLUMN garage.vehicle.model IS 'Vehicle model. Owner: self';
COMMENT ON COLUMN garage.vehicle.license_plate IS 'Vehicle license plate. Owner: self';
COMMENT ON COLUMN garage.vehicle.manufacture_year IS 'Vehicle manufacture year. Owner: self';
COMMENT ON COLUMN garage.vehicle.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.vehicle.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: work_order
-- ==========================================
CREATE TABLE garage.work_order (
                                   id UUID PRIMARY KEY,
                                   status VARCHAR(50),
                                   total_amount NUMERIC NOT NULL,
                                   customer_id UUID,
                                   employee_id UUID,
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP,
                                   CONSTRAINT fk_work_order_customer FOREIGN KEY (customer_id) REFERENCES garage.customer(id),
                                   CONSTRAINT fk_work_order_employee FOREIGN KEY (employee_id) REFERENCES garage.employee(id)
);

COMMENT ON COLUMN garage.work_order.id IS 'WorkOrder id. Owner: db';
COMMENT ON COLUMN garage.work_order.status IS 'WorkOrder status. Owner: self';
COMMENT ON COLUMN garage.work_order.total_amount IS 'Estimate total amount. Owner: self';
COMMENT ON COLUMN garage.work_order.customer_id IS 'Customer id. Owner: db';
COMMENT ON COLUMN garage.work_order.employee_id IS 'Customer id. Owner: db'; -- Preserved from your Java class comment overlap
COMMENT ON COLUMN garage.work_order.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.work_order.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: estimated_service
-- ==========================================
CREATE TABLE garage.estimated_service (
                                          id UUID PRIMARY KEY,
                                          name VARCHAR(255) NOT NULL,
                                          description TEXT,
                                          amount NUMERIC NOT NULL,
                                          finished_at TIMESTAMP,
                                          service_id UUID,
                                          work_order_id UUID NOT NULL,
                                          created_at TIMESTAMP NOT NULL,
                                          updated_at TIMESTAMP,
                                          CONSTRAINT fk_estimated_service_service FOREIGN KEY (service_id) REFERENCES garage.service(id),
                                          CONSTRAINT fk_estimated_service_wo FOREIGN KEY (work_order_id) REFERENCES garage.work_order(id)
);

COMMENT ON COLUMN garage.estimated_service.id IS 'Service id. Owner: db';
COMMENT ON COLUMN garage.estimated_service.name IS 'Service name. Owner: self';
COMMENT ON COLUMN garage.estimated_service.description IS 'Service description. Owner: self';
COMMENT ON COLUMN garage.estimated_service.amount IS 'Service amount. Owner: self';
COMMENT ON COLUMN garage.estimated_service.finished_at IS 'Estimated Service finished at. Owner: self';
COMMENT ON COLUMN garage.estimated_service.service_id IS 'Service id. Owner: db';
COMMENT ON COLUMN garage.estimated_service.work_order_id IS 'Work Order id. Owner: db';
COMMENT ON COLUMN garage.estimated_service.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.estimated_service.updated_at IS 'Register updated at. Owner: db';

-- ==========================================
-- TABLE: estimated_material
-- ==========================================
CREATE TABLE garage.estimated_material (
                                           id UUID PRIMARY KEY,
                                           name VARCHAR(55) NOT NULL,
                                           description TEXT,
                                           amount NUMERIC NOT NULL,
                                           material_id UUID,
                                           estimated_service_id UUID NOT NULL,
                                           created_at TIMESTAMP NOT NULL,
                                           updated_at TIMESTAMP,
                                           CONSTRAINT fk_estimated_material_mat FOREIGN KEY (material_id) REFERENCES garage.material(id),
                                           CONSTRAINT fk_estimated_material_es FOREIGN KEY (estimated_service_id) REFERENCES garage.estimated_service(id)
);

COMMENT ON COLUMN garage.estimated_material.id IS 'Estimated Material id. Owner: db';
COMMENT ON COLUMN garage.estimated_material.name IS 'Estimated Material name. Owner: self';
COMMENT ON COLUMN garage.estimated_material.description IS 'Estimated Material description. Owner: self';
COMMENT ON COLUMN garage.estimated_material.amount IS 'Estimated Material amount. Owner: self';
COMMENT ON COLUMN garage.estimated_material.material_id IS 'Material id. Owner: db';
COMMENT ON COLUMN garage.estimated_material.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.estimated_material.updated_at IS 'Register updated at. Owner: db';