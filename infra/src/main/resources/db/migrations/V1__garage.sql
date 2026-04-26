CREATE SCHEMA IF NOT EXISTS garage;

-- -----------------------------------------------------------------------------
-- TABLE: garage.authority
-- -----------------------------------------------------------------------------
CREATE TABLE garage.authority (
                                  id UUID NOT NULL,
                                  authority VARCHAR(20) NOT NULL,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP,
                                  CONSTRAINT pk_authority PRIMARY KEY (id)
);

COMMENT ON COLUMN garage.authority.id IS 'Authorization id. Owner: db';
COMMENT ON COLUMN garage.authority.authority IS 'Authorization name. Owner: self';
COMMENT ON COLUMN garage.authority.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.authority.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.users
-- -----------------------------------------------------------------------------
CREATE TABLE garage.users (
                              id UUID NOT NULL,
                              username VARCHAR(255) NOT NULL,
                              password VARCHAR(60) NOT NULL,
                              name VARCHAR(255) NOT NULL,
                              email VARCHAR(255) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              updated_at TIMESTAMP,
                              CONSTRAINT pk_users PRIMARY KEY (id),
                              CONSTRAINT uk_users_username UNIQUE (username),
                              CONSTRAINT uk_users_email UNIQUE (email)
);

COMMENT ON COLUMN garage.users.id IS 'User id. Owner: db';
COMMENT ON COLUMN garage.users.username IS 'Username. Owner: self';
COMMENT ON COLUMN garage.users.password IS 'User password. Owner: self';
COMMENT ON COLUMN garage.users.name IS 'User name. Owner: self';
COMMENT ON COLUMN garage.users.email IS 'User e-mail. Owner: self';
COMMENT ON COLUMN garage.users.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.users.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.users_authority
-- -----------------------------------------------------------------------------
CREATE TABLE garage.users_authority (
                                        user_id UUID NOT NULL,
                                        authority_id UUID NOT NULL,
                                        CONSTRAINT pk_users_authority PRIMARY KEY (user_id, authority_id),
                                        CONSTRAINT fk_users_authority_user FOREIGN KEY (user_id) REFERENCES garage.users (id),
                                        CONSTRAINT fk_users_authority_authority FOREIGN KEY (authority_id) REFERENCES garage.authority (id)
);

-- No specific entity comments provided for this join table.


-- -----------------------------------------------------------------------------
-- TABLE: garage.customer (JOINED inheritance from users)
-- -----------------------------------------------------------------------------
CREATE TABLE garage.customer (
                                 id UUID NOT NULL,
                                 document VARCHAR(14) NOT NULL,
                                 CONSTRAINT pk_customer PRIMARY KEY (id),
                                 CONSTRAINT fk_customer_users FOREIGN KEY (id) REFERENCES garage.users (id) ON DELETE CASCADE
);

COMMENT ON COLUMN garage.customer.document IS 'Customer document (CPF/CNPJ). Owner: self';


-- -----------------------------------------------------------------------------
-- TABLE: garage.employee (JOINED inheritance from users)
-- -----------------------------------------------------------------------------
CREATE TABLE garage.employee (
                                 id UUID NOT NULL,
                                 cpf VARCHAR(11) NOT NULL,
                                 CONSTRAINT pk_employee PRIMARY KEY (id),
                                 CONSTRAINT fk_employee_users FOREIGN KEY (id) REFERENCES garage.users (id) ON DELETE CASCADE
);

COMMENT ON COLUMN garage.employee.cpf IS 'Employee cpf. Owner: self';


-- -----------------------------------------------------------------------------
-- TABLE: garage.vehicle
-- -----------------------------------------------------------------------------
CREATE TABLE garage.vehicle (
                                id UUID NOT NULL,
                                make VARCHAR(100) NOT NULL,
                                model VARCHAR(100) NOT NULL,
                                license_plate VARCHAR(10) NOT NULL,
                                manufacture_year INTEGER NOT NULL,
                                customer_id UUID,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP,
                                CONSTRAINT pk_vehicle PRIMARY KEY (id),
                                CONSTRAINT fk_vehicle_customer FOREIGN KEY (customer_id) REFERENCES garage.customer (id)
);

COMMENT ON COLUMN garage.vehicle.id IS 'Vehicle id. Owner: db';
COMMENT ON COLUMN garage.vehicle.make IS 'Vehicle make. Owner: self';
COMMENT ON COLUMN garage.vehicle.model IS 'Vehicle model. Owner: self';
COMMENT ON COLUMN garage.vehicle.license_plate IS 'Vehicle license plate. Owner: self';
COMMENT ON COLUMN garage.vehicle.manufacture_year IS 'Vehicle manufacture year. Owner: self';
COMMENT ON COLUMN garage.vehicle.customer_id IS 'Customer id. Owner: db';
COMMENT ON COLUMN garage.vehicle.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.vehicle.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.material
-- -----------------------------------------------------------------------------
CREATE TABLE garage.material (
                                 id UUID NOT NULL,
                                 type VARCHAR(55) NOT NULL,
                                 name VARCHAR(255) NOT NULL,
                                 description TEXT,
                                 cost DECIMAL(19, 2) NOT NULL,
                                 created_at TIMESTAMP NOT NULL,
                                 updated_at TIMESTAMP,
                                 CONSTRAINT pk_material PRIMARY KEY (id)
);

COMMENT ON COLUMN garage.material.id IS 'Material id. Owner: db';
COMMENT ON COLUMN garage.material.type IS 'Material type. Owner: self';
COMMENT ON COLUMN garage.material.name IS 'Material name. Owner: self';
COMMENT ON COLUMN garage.material.description IS 'Material description. Owner: self';
COMMENT ON COLUMN garage.material.cost IS 'Material cost. Owner: self';
COMMENT ON COLUMN garage.material.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.material.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.inventory_material (@MapsId maps id directly to material)
-- -----------------------------------------------------------------------------
CREATE TABLE garage.inventory_material (
                                           id UUID NOT NULL,
                                           quantity_in_stock INTEGER NOT NULL,
                                           reserved_quantity INTEGER NOT NULL DEFAULT 0,
                                           created_at TIMESTAMP NOT NULL,
                                           updated_at TIMESTAMP,
                                           CONSTRAINT pk_inventory_material PRIMARY KEY (id),
                                           CONSTRAINT fk_inventory_material_material FOREIGN KEY (id) REFERENCES garage.material (id) ON DELETE CASCADE
);

COMMENT ON COLUMN garage.inventory_material.id IS 'Inventory id. Owner: db';
COMMENT ON COLUMN garage.inventory_material.quantity_in_stock IS 'Inventory quantity in stock. Owner: self';
COMMENT ON COLUMN garage.inventory_material.reserved_quantity IS 'Inventory reserved quantity. Owner: self';
COMMENT ON COLUMN garage.inventory_material.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.inventory_material.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.service
-- -----------------------------------------------------------------------------
CREATE TABLE garage.service (
                                id UUID NOT NULL,
                                name VARCHAR(255) NOT NULL,
                                description TEXT,
                                cost DECIMAL(19, 2) NOT NULL,
                                created_at TIMESTAMP NOT NULL,
                                updated_at TIMESTAMP,
                                CONSTRAINT pk_service PRIMARY KEY (id)
);

COMMENT ON COLUMN garage.service.id IS 'Service id. Owner: db';
COMMENT ON COLUMN garage.service.name IS 'Service name. Owner: self';
COMMENT ON COLUMN garage.service.description IS 'Service description. Owner: self';
COMMENT ON COLUMN garage.service.cost IS 'Service cost. Owner: self';
COMMENT ON COLUMN garage.service.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.service.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.service_inventory_material
-- -----------------------------------------------------------------------------
CREATE TABLE garage.service_inventory_material (
                                                   service_id UUID NOT NULL,
                                                   inventory_material_id UUID NOT NULL,
                                                   CONSTRAINT pk_service_inventory_material PRIMARY KEY (service_id, inventory_material_id),
                                                   CONSTRAINT fk_sim_service FOREIGN KEY (service_id) REFERENCES garage.service (id),
                                                   CONSTRAINT fk_sim_inventory_material FOREIGN KEY (inventory_material_id) REFERENCES garage.inventory_material (id)
);

-- No specific entity comments provided for this join table.


-- -----------------------------------------------------------------------------
-- TABLE: garage.work_order
-- -----------------------------------------------------------------------------
CREATE TABLE garage.work_order (
                                   id UUID NOT NULL,
                                   status VARCHAR(50) DEFAULT 'RECEIVED',
                                   total_amount DECIMAL(19, 2) NOT NULL,
                                   vehicle_id UUID,
                                   employee_id UUID,
                                   created_at TIMESTAMP NOT NULL,
                                   updated_at TIMESTAMP,
                                   CONSTRAINT pk_work_order PRIMARY KEY (id),
                                   CONSTRAINT fk_work_order_vehicle FOREIGN KEY (vehicle_id) REFERENCES garage.vehicle (id),
                                   CONSTRAINT fk_work_order_employee FOREIGN KEY (employee_id) REFERENCES garage.employee (id)
);

COMMENT ON COLUMN garage.work_order.id IS 'Work Order id. Owner: db';
COMMENT ON COLUMN garage.work_order.status IS 'Work Order status. Owner: self';
COMMENT ON COLUMN garage.work_order.total_amount IS 'Work Order total amount estimation. Owner: self';
COMMENT ON COLUMN garage.work_order.vehicle_id IS 'Vehicle id. Owner: db';
COMMENT ON COLUMN garage.work_order.employee_id IS 'Employee id. Owner: db';
COMMENT ON COLUMN garage.work_order.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.work_order.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.estimated_service
-- -----------------------------------------------------------------------------
CREATE TABLE garage.estimated_service (
                                          id UUID NOT NULL,
                                          work_order_id UUID NOT NULL,
                                          name VARCHAR(255) NOT NULL,
                                          description TEXT,
                                          cost DECIMAL(19, 2) NOT NULL,
                                          finished_at TIMESTAMP,
                                          created_at TIMESTAMP NOT NULL,
                                          updated_at TIMESTAMP,
                                          CONSTRAINT pk_estimated_service PRIMARY KEY (id),
                                          CONSTRAINT fk_estimated_service_work_order FOREIGN KEY (work_order_id) REFERENCES garage.work_order (id) ON DELETE CASCADE
);

COMMENT ON COLUMN garage.estimated_service.id IS 'Estimated Service id. Owner: db';
COMMENT ON COLUMN garage.estimated_service.work_order_id IS 'Work Order id. Owner: db';
COMMENT ON COLUMN garage.estimated_service.name IS 'Estimated Service name. Owner: self';
COMMENT ON COLUMN garage.estimated_service.description IS 'Estimated Service description. Owner: self';
COMMENT ON COLUMN garage.estimated_service.cost IS 'Estimated Service cost. Owner: self';
COMMENT ON COLUMN garage.estimated_service.finished_at IS 'Estimated Service finished at. Owner: self';
COMMENT ON COLUMN garage.estimated_service.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.estimated_service.updated_at IS 'Register updated at. Owner: db';


-- -----------------------------------------------------------------------------
-- TABLE: garage.estimated_material
-- -----------------------------------------------------------------------------
CREATE TABLE garage.estimated_material (
                                           id UUID NOT NULL,
                                           estimated_service_id UUID NOT NULL,
                                           type VARCHAR(55) NOT NULL,
                                           name VARCHAR(55) NOT NULL,
                                           description TEXT,
                                           cost DECIMAL(19, 2) NOT NULL,
                                           created_at TIMESTAMP NOT NULL,
                                           updated_at TIMESTAMP,
                                           CONSTRAINT pk_estimated_material PRIMARY KEY (id),
                                           CONSTRAINT fk_estimated_material_service FOREIGN KEY (estimated_service_id) REFERENCES garage.estimated_service (id) ON DELETE CASCADE
);

COMMENT ON COLUMN garage.estimated_material.id IS 'Estimated Material id. Owner: db';
COMMENT ON COLUMN garage.estimated_material.type IS 'Estimated Material type. Owner: self';
COMMENT ON COLUMN garage.estimated_material.name IS 'Estimated Material name. Owner: self';
COMMENT ON COLUMN garage.estimated_material.description IS 'Estimated Material description. Owner: self';
COMMENT ON COLUMN garage.estimated_material.cost IS 'Estimated Material cost. Owner: self';
COMMENT ON COLUMN garage.estimated_material.created_at IS 'Register created at. Owner: db';
COMMENT ON COLUMN garage.estimated_material.updated_at IS 'Register updated at. Owner: db';