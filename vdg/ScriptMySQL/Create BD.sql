CREATE DATABASE IF NOT EXISTS vdg;

USE vdg;

DROP TABLE IF EXISTS FotoIdentificacion;
DROP TABLE IF EXISTS RestriccionPerimetral;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Direccion;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS RolDeUsuario;
DROP TABLE IF EXISTS Incidencia;
DROP TABLE IF EXISTS TipoIncidencia;


CREATE TABLE RolDeUsuario (
	idRol INT AUTO_INCREMENT,
	nombre VARCHAR(50),
	PRIMARY KEY (idRol)
);


CREATE TABLE Usuario (
	idUsuario INT AUTO_INCREMENT,
    email VARCHAR(64) NOT NULL,
   contrasena VARCHAR (64) NOT NULL,
   rolDeUsuario VARCHAR(25),
   PRIMARY KEY (idUsuario)
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


CREATE TABLE Persona (
	idPersona INT AUTO_INCREMENT,
	nombre VARCHAR(50),
   apellido VARCHAR (50),
   DNI VARCHAR (20),
   telefono VARCHAR (50),
   fechaNacimiento DATE,
   idDireccion INT,
   idUsuario INT,
   PRIMARY KEY (idPersona),
   FOREIGN KEY (idDireccion) REFERENCES Direccion(idDireccion),
   FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);


CREATE TABLE FotoIdentificacion (
	idFoto INT AUTO_INCREMENT,
	foto MEDIUMBLOB,
	idPersona INT,
	PRIMARY KEY (idFoto),
	FOREIGN KEY (idPersona) REFERENCES Persona(idPersona)
);


CREATE TABLE RestriccionPerimetral (
	idRestriccion INT AUTO_INCREMENT,
	idUsuario INT,
	idDamnificada INT,
	idVictimario INT,
	distancia INT,
    fechaSentencia DATE,
	PRIMARY KEY (idRestriccion),
	FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
	FOREIGN KEY (idDamnificada) REFERENCES Persona(idPersona),
	FOREIGN KEY (idVictimario) REFERENCES Persona(idPersona)
);

CREATE TABLE Incidencia(
	idIncidencia INT AUTO_INCREMENT,
	fecha Timestamp,
	descripcion VARCHAR(500),
	topico VARCHAR (50),
    idRestriccion int,
	PRIMARY KEY (idIncidencia),
	FOREIGN KEY (idRestriccion) REFERENCES RestriccionPerimetral(idRestriccion)
);

CREATE TABLE TipoIncidencia (
	idTipoIncidencia INT AUTO_INCREMENT,
	tipoIncidencia VARCHAR(50),
	PRIMARY KEY (idTipoIncidencia)
);

INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('VictimarioIlocalizable');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('DamnificadaIlocalizable');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('PruebaDeVidaFallida');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('InfraccionDeRestriccion');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('FueraDeRutina');

INSERT INTO RolDeUsuario (nombre) VALUES ('ADMINISTRATIVO');
INSERT INTO RolDeUsuario (nombre) VALUES ('SUPERVISOR');
INSERT INTO RolDeUsuario (nombre) VALUES ('AGRESOR');
INSERT INTO RolDeUsuario (nombre) VALUES ('DAMNIFICADA');

INSERT INTO Direccion (calle, altura, idLocalidad) VALUES ('Argerich', '1767', 2156);
INSERT INTO Usuario (email, contrasena, rolDeUsuario) VALUES ("admin@admin.com", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "SUPERVISOR");
INSERT INTO vdg.Incidencia (fecha, descripcion, topico, idRestriccion) VALUES ("2019-09-20 20:42:35.677", "Esta es una incidencia de prueba", "FueraDeRutina", 1);