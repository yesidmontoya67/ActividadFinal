#!/bin/bash
set -e

POSTGRES="psql --username ${POSTGRES_USER} -v ON_ERROR_STOP=1"

$POSTGRES --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE keycloak;
    CREATE DATABASE orders;
EOSQL

$POSTGRES --dbname "orders" <<-EOSQL
    CREATE TABLE PRODUCTS(
        id SERIAL PRIMARY KEY,
        name VARCHAR NOT NULL,
        description VARCHAR NOT NULL,
		images bytea,
        base_price DECIMAL NOT NULL,
        tax_rate DECIMAL NOT NULL,
        status VARCHAR NOT NULL,
        inventory_quantity INT NOT NULL
    );
	
	CREATE TABLE ORDERS(
		id SERIAL PRIMARY KEY,
        products VARCHAR NOT NULL,
        client VARCHAR NOT NULL,
        total DECIMAL NOT NULL,
		discount INT NOT NULL ,
        status VARCHAR  NOT NULL
       
    );
EOSQL