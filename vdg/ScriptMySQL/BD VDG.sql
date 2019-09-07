CREATE DATABASE IF NOT EXISTS vdg;

USE vdg;

DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS RolDeUsuario;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Localidad;
DROP TABLE IF EXISTS Direccion;
DROP TABLE IF EXISTS FotoIdentificacion;
DROP TABLE IF EXISTS RestriccionPerimetral;




CREATE TABLE RolDeUsuario (
	idRol INT AUTO_INCREMENT,
	nombre VARCHAR(50),
	PRIMARY KEY (idRol)
);


CREATE TABLE Usuario (
	idUsuario INT AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL,
   contraseña VARCHAR (50) NOT NULL,
   idRol INT,
   PRIMARY KEY (idUsuario),
   FOREIGN KEY (idRol) REFERENCES RolDeUsuario(idRol)
);


CREATE TABLE Localidad (
	idLocalidad INT AUTO_INCREMENT,
	nombre VARCHAR (50),
	codigoPostal VARCHAR (50),
	PRIMARY KEY (idLocalidad)
);


CREATE TABLE Direccion (
	idDireccion INT AUTO_INCREMENT,
	calle VARCHAR (50),
	altura VARCHAR (50),
	piso VARCHAR (50),
	departamento VARCHAR (50),
	idLocalidad INT,
	PRIMARY KEY (idDireccion),
	FOREIGN KEY (idLocalidad) REFERENCES Localidad(idLocalidad)
);


CREATE TABLE persona (
	idPersona INT AUTO_INCREMENT,
	nombre VARCHAR(50),
   apellido VARCHAR (50),
   DNI VARCHAR (20),
   telefono VARCHAR (50),
   fechaNacimiento DATE,
   idDireccion INT,
   idUsuario INT,
   PRIMARY KEY (idPersona),
   FOREIGN KEY (idDireccion) REFERENCES Direccion(idDireccion)
);


CREATE TABLE FotoIdentificacion (
	idFoto INT AUTO_INCREMENT,
	foto BLOB,
	idPersona INT,
	PRIMARY KEY (idFoto),
	FOREIGN KEY (idPersona) REFERENCES Persona(idPersona)
);


CREATE TABLE RestriccionPerimetral (
	idRestriccion INT AUTO_INCREMENT,
	idUsuarioAdministrativo INT,
	idUsuarioDamnificada INT,
	idUsuarioVictimario INT,
	distancia INT,
	PRIMARY KEY (idRestriccion),
	FOREIGN KEY (idUsuarioAdministrativo) REFERENCES Persona(idPersona),
	FOREIGN KEY (idUsuarioDamnificada) REFERENCES Persona(idPersona),
	FOREIGN KEY (idUsuarioVictimario) REFERENCES Persona(idPersona)


);


INSERT INTO RolDeUsuario (nombre) VALUES ('ADMINISTRATIVO');
INSERT INTO RolDeUsuario (nombre) VALUES ('SUPERVISOR');
INSERT INTO RolDeUsuario (nombre) VALUES ('AGRESOR');
INSERT INTO RolDeUsuario (nombre) VALUES ('VICTIMA');
