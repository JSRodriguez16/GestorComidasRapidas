DROP DATABASE IF EXISTS gestor_comidas_rapidas;

CREATE DATABASE gestor_comidas_rapidas;

CREATE TABLE cliente (
    id_cliente INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono INTEGER NOT NULL,
    direccion VARCHAR(255) NOT NULL
);

CREATE TABLE producto (
    id_producto INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio NUMERIC(10, 2) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);
