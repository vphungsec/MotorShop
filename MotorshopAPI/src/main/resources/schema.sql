/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  hungh
 * Created: Jun 6, 2021
 */

--DROP TABLE IF EXISTS depart;

/*
PRAGMA encoding = "UTF-8";

CREATE TABLE IF NOT EXISTS depart (    
    depart_id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL UNIQUE    
);

CREATE TABLE IF NOT EXISTS staff (
    staff_id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL,    
    phone VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    created_date VARCHAR NOT NULL,
    depart_id VARCHAR NOT NULL REFERENCES depart (depart_id) ON DELETE CASCADE ON UPDATE CASCADE    
);

CREATE TABLE IF NOT EXISTS customer (
    customer_id VARCHAR PRIMARY KEY NOT NULL,
    identity_id VARCHAR NOT NULL UNIQUE,
    name VARCHAR NOT NULL,
    address VARCHAR NOT NULL,
    phone VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    created_date VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS brand (
    brand_id VARCHAR PRIMARY KEY NOT NULL,
    name VARCHAR NOT NULL UNIQUE,
    address VARCHAR NOT NULL,
    phone VARCHAR NOT NULL UNIQUE,
    email VARCHAR NOT NULL UNIQUE,
    logo BLOB NULL
);

CREATE TABLE IF NOT EXISTS motor (
    motor_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL UNIQUE,
    amount INTEGER NOT NULL,
    price INTEGER NOT NULL,
    warranty_period INTEGER NOT NULL,
    brand_id VARCHAR NOT NULL REFERENCES brand (brand_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS motor_info (
    motor_info_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS motor_detail (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    motor_id INTEGER NOT NULL REFERENCES motor (motor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    motor_info_id INTEGER NOT NULL REFERENCES motor_info (motor_info_id) ON DELETE CASCADE ON UPDATE CASCADE,
    content VARCHAR NOT NULL,
    UNIQUE (motor_id, motor_info_id)
);

CREATE TABLE IF NOT EXISTS accessory (
    accessory_id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR NOT NULL UNIQUE,
    amount INTEGER NOT NULL,
    price INTEGER NOT NULL,
    warranty_period INTEGER NOT NULL,
    brand_id VARCHAR NOT NULL REFERENCES brand (brand_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS accessory_detail (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    accessory_id INTEGER NOT NULL REFERENCES accessory (accessory_id) ON DELETE CASCADE ON UPDATE CASCADE,
    motor_id INTEGER NOT NULL REFERENCES motor (motor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    price INTEGER NOT NULL,
    UNIQUE (accessory_id, motor_id)
);

CREATE TABLE IF NOT EXISTS image (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,    
    motor_id INTEGER REFERENCES motor (motor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    accessory_id INTEGER REFERENCES accessory (accessory_id) ON DELETE CASCADE ON UPDATE CASCADE,
    image BLOB NOT NULL,
    UNIQUE (motor_id, accessory_id, image)
);

CREATE TABLE IF NOT EXISTS bill (
    bill_id INTEGER PRIMARY KEY AUTOINCREMENT,
    created_date VARCHAR NOT NULL,
    customer_id VARCHAR NOT NULL REFERENCES customer (customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
    staff_id VARCHAR NOT NULL REFERENCES staff (staff_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS bill_detail (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    bill_id INTEGER NOT NULL REFERENCES bill (bill_id) ON DELETE CASCADE ON UPDATE CASCADE,
    motor_id INTEGER REFERENCES motor (motor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    accessory_id INTEGER REFERENCES accessory (accessory_id) ON DELETE CASCADE ON UPDATE CASCADE,
    amount INTEGER NOT NULL,
    price INTEGER NOT NULL,
    UNIQUE (bill_id, motor_id, accessory_id)
);

CREATE TABLE IF NOT EXISTS warranty (
    warranty_id INTEGER PRIMARY KEY AUTOINCREMENT,
    bill_id INTEGER NOT NULL REFERENCES bill (bill_id) ON DELETE CASCADE ON UPDATE CASCADE,
    created_date VARCHAR NOT NULL,
    staff_id VARCHAR NOT NULL REFERENCES staff (staff_id) ON DELETE CASCADE ON UPDATE CASCADE    
);

CREATE TABLE IF NOT EXISTS warranty_detail (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    warranty_id INTEGER NOT NULL REFERENCES warranty (warranty_id) ON DELETE CASCADE ON UPDATE CASCADE,
    motor_id INTEGER REFERENCES motor (motor_id) ON DELETE CASCADE ON UPDATE CASCADE,
    accessory_id INTEGER REFERENCES accessory (accessory_id) ON DELETE CASCADE ON UPDATE CASCADE,
    content VARCHAR NOT NULL,
    price INTEGER NOT NULL,    
    UNIQUE (warranty_id, motor_id, accessory_id, content)
);
*/