DROP DATABASE IF EXISTS MUNKEY;
CREATE DATABASE MUNKEY;

USE MUNKEY;

CREATE TABLE usuario (
 username    		VARCHAR(20),
 nombre           VARCHAR(20) NOT NULL,
 correo           VARCHAR(20) NOT NULL,
 tipo		         VARCHAR(50) NOT NULL,
 foto					VARCHAR(100),
 passwd				VARCHAR (100) NOT NULL, -- Se encripta en la aplicación de java
 CONSTRAINT USUARIO_PK PRIMARY KEY (username)
);

CREATE TABLE obra (
 obraID		         smallint UNSIGNED,
 titulo              VARCHAR(45)  NOT NULL,
 Fsubida          	DATE,
 portada					VARCHAR(100) NOT NULL,
 pdf				VARCHAR(100) NOT NULL,
 genero              VARCHAR(45)  NOT NULL,
 idioma              VARCHAR(45)  NOT NULL,
 autor		         VARCHAR(20)  NOT NULL,
 CONSTRAINT OBRA_PK PRIMARY KEY (obraID),
 CONSTRAINT OBRA_AUTOR_FK FOREIGN KEY (autor) REFERENCES usuario (username)
);

CREATE TABLE meGusta (
 Fsubida          	DATE,
 autor		         VARCHAR(20) NOT NULL,
 obraID		         SMALLINT UNSIGNED NOT NULL,
 CONSTRAINT MEGUSTA_AUTOR_FK FOREIGN KEY (autor) REFERENCES usuario (username),
 CONSTRAINT MEGUSTA_OBRA_FK FOREIGN KEY (obraID) REFERENCES obra (obraID),
 CONSTRAINT MEGUSTA_PK PRIMARY KEY (autor, obraID, Fsubida)
);

CREATE TABLE comentario (
 Fsubida          	DATE,
 descripcion			VARCHAR(300) NOT NULL,
 autor		         VARCHAR(20) NOT NULL,
 obraID		         SMALLINT UNSIGNED NOT NULL,
 CONSTRAINT COMENTARIO_AUTOR_FK FOREIGN KEY (autor) REFERENCES usuario (username),
 CONSTRAINT COMENTARIO_OBRA_FK FOREIGN KEY (obraID) REFERENCES obra (obraID),
 CONSTRAINT COMENTARIO_PK PRIMARY KEY (obraID, autor, Fsubida)
);

CREATE TABLE conversacion (
 conversacionID		SMALLINT    UNSIGNED,
 usuario1		      VARCHAR(20) NOT NULL,
 usuario2	         VARCHAR(20) NOT NULL,
 CONSTRAINT CONVERSACION_USER1_FK FOREIGN KEY (usuario1) REFERENCES usuario (username),
 CONSTRAINT CONVERSACION_USER2_FK FOREIGN KEY (usuario2) REFERENCES usuario (username),
 CONSTRAINT COMENTARIO_PK PRIMARY KEY (conversacionID)
);

CREATE TABLE mensaje (
 Fsubida          	DATETIME,
 autor		         VARCHAR(20) NOT NULL,
 conversacionID		SMALLINT UNSIGNED NOT NULL,
 descripcion			VARCHAR(300) NOT NULL,
 CONSTRAINT MENSAJE_ENVIAR_FK FOREIGN KEY (autor) REFERENCES usuario (username),
 CONSTRAINT MENSAJE_CONVERSACION_FK FOREIGN KEY (conversacionID) REFERENCES conversacion (conversacionID),
 CONSTRAINT COMENTARIO_PK PRIMARY KEY (autor,conversacionID, Fsubida)
);

