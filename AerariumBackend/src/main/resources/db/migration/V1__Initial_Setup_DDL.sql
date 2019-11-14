/*
 * Initial Setup - Standard Schema
 * DDL Script for tables creation
 */

-- Access Management - Companies table creation
CREATE TABLE IF NOT EXISTS company (
    co_id SERIAL,
    co_name VARCHAR(50) NOT NULL,
    CONSTRAINT co_pk PRIMARY KEY (co_id)
);

-- Access Management - Users table creation
CREATE TABLE IF NOT EXISTS user_account (
    us_id SERIAL,
    us_co_id INTEGER NOT NULL,
    us_name VARCHAR(50) NOT NULL,
    us_email VARCHAR(60) NOT NULL UNIQUE,
    us_password VARCHAR(200) NOT NULL,
    us_phone VARCHAR(20),
    us_active BOOLEAN,
    CONSTRAINT us_pk PRIMARY KEY (us_id),
    CONSTRAINT us_co_fk FOREIGN KEY (us_co_id) REFERENCES company (co_id)
);

-- Access Management - Roles (authorities) table creation
CREATE TABLE IF NOT EXISTS application_role (
    ro_id SERIAL,
    ro_name VARCHAR(20),
    ro_description TEXT,
    CONSTRAINT ro_pk PRIMARY KEY (ro_id)
);

-- Access Management - User authorities table creation
CREATE TABLE IF NOT EXISTS user_role (
    ur_us_id INTEGER NOT NULL,
    ur_ro_id INTEGER NOT NULL,
    CONSTRAINT ur_pk PRIMARY KEY (ur_us_id, ur_ro_id),
    CONSTRAINT us_fk FOREIGN KEY (ur_us_id) REFERENCES user_account (us_id),
    CONSTRAINT ro_fk FOREIGN KEY (ur_ro_id) REFERENCES application_role (ro_id)
);

-- Master Data - Banks table creation
CREATE TABLE IF NOT EXISTS bank (
    ba_id SERIAL,
    ba_name VARCHAR(50) UNIQUE,
    ba_description TEXT,
    CONSTRAINT ba_pk PRIMARY KEY (ba_id)
);

-- Master Data - Cost types table creation
CREATE TABLE IF NOT EXISTS cost_type (
    ct_id SERIAL,
    ct_name VARCHAR(50) UNIQUE,
    ct_description TEXT,
    CONSTRAINT ct_pk PRIMARY KEY (ct_id)
);

-- Master Data - Specifications table creation
CREATE TABLE IF NOT EXISTS specification (
    sp_id SERIAL,
    sp_name VARCHAR(50) UNIQUE,
    sp_description TEXT,
    CONSTRAINT sp_pk PRIMARY KEY (sp_id)
);

-- Master Data - Installments table creation
CREATE TABLE IF NOT EXISTS installment (
    in_id SERIAL,
    in_name VARCHAR(50) UNIQUE,
    in_description TEXT,
    CONSTRAINT in_pk PRIMARY KEY (in_id)
);

-- Master Data - Bill Statuses table creation
CREATE TABLE IF NOT EXISTS bill_status (
    bs_id SERIAL,
    bs_name VARCHAR(50) UNIQUE,
    bs_description TEXT,
    CONSTRAINT bs_pk PRIMARY KEY (bs_id)
);

-- Master Data - Payment Types table creation
CREATE TABLE IF NOT EXISTS payment_type (
    pt_id SERIAL,
    pt_name VARCHAR(50) UNIQUE,
    pt_description TEXT,
    CONSTRAINT pt_pk PRIMARY KEY (pt_id)
);

-- Transactional - Expenses table creation
CREATE TABLE IF NOT EXISTS expense (
    ex_id SERIAL,
    ex_co_id INTEGER NOT NULL,
    ex_date DATE NOT NULL,
    ex_name VARCHAR(60) UNIQUE,
    ex_value NUMERIC(10,2),
    ex_description TEXT,
    CONSTRAINT ex_pk PRIMARY KEY (ex_id),
    CONSTRAINT ex_co_fk FOREIGN KEY (ex_co_id) REFERENCES company (co_id)
);

-- Transactional - Investments table creation
CREATE TABLE IF NOT EXISTS investment (
    iv_id SERIAL,
    iv_co_id INTEGER NOT NULL,
    iv_date DATE NOT NULL,
    iv_name VARCHAR(60) UNIQUE,
    iv_value NUMERIC(10,2),
    iv_description TEXT,
    CONSTRAINT iv_pk PRIMARY KEY (iv_id),
    CONSTRAINT iv_co_fk FOREIGN KEY (iv_co_id) REFERENCES company (co_id)
);

-- Transactional - Sales Management table creation
CREATE TABLE IF NOT EXISTS sale (
    sa_id SERIAL,
    sa_co_id INTEGER NOT NULL,
    sa_pt_id INTEGER NOT NULL,
    sa_in_id INTEGER NOT NULL,
    sa_date DATE NOT NULL,
    sa_vendor VARCHAR(60),
    sa_customer VARCHAR(60),
    sa_purchase_value NUMERIC(10,2),
    sa_installment_value NUMERIC(10,2),
    sa_budget_number NUMERIC(10,2),
    sa_architect VARCHAR(60),
    sa_commission NUMERIC(10,2),
    sa_comment TEXT,
    CONSTRAINT sa_pk PRIMARY KEY (sa_id),
    CONSTRAINT sa_co_fk FOREIGN KEY (sa_co_id) REFERENCES company (co_id),
    CONSTRAINT sa_pt_fk FOREIGN KEY (sa_pt_id) REFERENCES payment_type (pt_id),
    CONSTRAINT sa_in_fk FOREIGN KEY (sa_in_id) REFERENCES installment (in_id)
);

-- Transactional - Accounts Receivable table creation
CREATE TABLE IF NOT EXISTS accounts_receivable (
    ar_id SERIAL,
    ar_co_id INTEGER NOT NULL,
    ar_ba_id INTEGER NOT NULL,
    ar_pt_id INTEGER NOT NULL,
    ar_bs_id INTEGER NOT NULL,
    ar_customer VARCHAR(60),
    ar_document VARCHAR(60),
    ar_receiving_date DATE,
    ar_value NUMERIC(10,2),
    ar_net_value NUMERIC(10,2),
    ar_rate NUMERIC(10,2),
    ar_due_date DATE NOT NULL,
    ar_destination VARCHAR(60),
    ar_description TEXT,
    ar_comment TEXT,
    CONSTRAINT ar_pk PRIMARY KEY (ar_id),
    CONSTRAINT ar_co_fk FOREIGN KEY (ar_co_id) REFERENCES company (co_id),
    CONSTRAINT ar_ba_fk FOREIGN KEY (ar_ba_id) REFERENCES bank (ba_id),
    CONSTRAINT ar_pt_fk FOREIGN KEY (ar_pt_id) REFERENCES payment_type (pt_id),
    CONSTRAINT ar_bs_fk FOREIGN KEY (ar_bs_id) REFERENCES bill_status (bs_id)
);

-- Transactional - Accounts Payable table creation
CREATE TABLE IF NOT EXISTS accounts_payable (
    ap_id SERIAL,
    ap_ba_id INTEGER NOT NULL,
    ap_co_id INTEGER NOT NULL,
    ap_ct_id INTEGER NOT NULL,
    ap_pt_id INTEGER NOT NULL,
    ap_sp_id INTEGER NOT NULL,
    ap_vendor VARCHAR(60),
    ap_document VARCHAR(60),
    ap_payment_date DATE,
    ap_value NUMERIC(10,2),
    ap_rate NUMERIC(10,2),
    ap_due_date DATE NOT NULL,
    ap_destination VARCHAR(60),
    ap_description TEXT,
    ap_comment TEXT,
    CONSTRAINT ap_pk PRIMARY KEY (ap_id),
    CONSTRAINT ap_ba_fk FOREIGN KEY (ap_ba_id) REFERENCES bank (ba_id),
    CONSTRAINT ap_co_fk FOREIGN KEY (ap_co_id) REFERENCES company (co_id),
    CONSTRAINT ap_ct_fk FOREIGN KEY (ap_ct_id) REFERENCES cost_type (ct_id),
    CONSTRAINT ap_pt_fk FOREIGN KEY (ap_pt_id) REFERENCES payment_type (pt_id),
    CONSTRAINT ap_sp_fk FOREIGN KEY (ap_sp_id) REFERENCES specification (sp_id)
);