CREATE SEQUENCE seq_autor START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE seq_libro START WITH 1 INCREMENT BY 1;

CREATE TABLE autores (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(50) NULL,
    fecha_nacimiento DATE NULL
)

CREATE TABLE libros (
    id INT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    isbn VARCHAR(20) NOT NULL UNIQUE,
    año_publicacion INT NULL,
    genero VARCHAR(50) NULL,
    autor_id INT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES autores(id)
)

CREATE OR REPLACE TRIGGER autores_trigger
BEFORE INSERT ON autores
FOR EACH ROW
BEGIN 
    SELECT seq_autor.NEXTVAL INTO :new.id FROM dual;
END;

CREATE OR REPLACE TRIGGER libros_trigger
BEFORE INSERT ON libros
FOR EACH ROW
BEGIN 
    SELECT seq_libro.NEXTVAL INTO :new.id FROM dual;
END;

INSERT INTO autores (nombre, apellido, nacionalidad, fecha_nacimiento) VALUES (
    'Miguel', 'Cervantes', 'España', '18/02/1700'
);
INSERT INTO autores (nombre, apellido, nacionalidad, fecha_nacimiento) VALUES (
    'Julia', 'de Paula', 'Argentina', '18/02/1900'
);
SELECT * FROM autores;

INSERT INTO libros (titulo, isbn, año_publicacion, genero, autor_id) VALUES (
    'Don Quijote', '1234', 1700, 'Novela Caballeresca', 1
);
INSERT INTO libros (titulo, isbn, año_publicacion, genero, autor_id) VALUES (
    'Química Física', '9874', 200, 'Química Física', 2
);
COMMIT;

SELECT * FROM libros;