--1
CREATE  TABLE TOUR AS SELECT * FROM DOCENCIA.TOUR2017;
--2
--Se crean con el SQL developer
--Se insertan los datos
INSERT INTO INFO_PERSONAL
SELECT ID, NAME, NATIONALITY FROM TOUR;

INSERT INTO INFO_PROFESIONAL
SELECT ID, TEAM, COUNTRY, CATEGORY FROM TOUR;

COMMIT;

--3
CREATE OR REPLACE VIEW TOUR_VIEW AS
SELECT PE.ID, PR.EQUIPO, PR.PAIS, PE.NOMBRE, PE.NACIONALIDAD, PR.CATEGORIA 
FROM INFO_PERSONAL PE, INFO_PROFESIONAL PR WHERE PE.ID=PR.ID;

--4
CREATE OR REPLACE TRIGGER TR_INSERTA_CICLISTA
INSTEAD OF 
INSERT ON TOUR_VIEW
FOR EACH ROW
BEGIN
    INSERT INTO INFO_PERSONAL VALUES (:NEW.ID, :NEW.NOMBRE, :NEW.NACIONALIDAD);
    INSERT INTO INFO_PROFESIONAL VALUES(:NEW.ID,:NEW.EQUIPO,:NEW.PAIS,:NEW.CATEGORIA);
    COMMIT;
END TR_INSERTA_CICLISTA;

--adicional
CREATE OR REPLACE TRIGGER TR_INSERTA_CICLISTA_T
AFTER
INSERT ON TOUR_VIEW
FOR EACH ROW
BEGIN
    INSERT INTO INFO_PERSONAL VALUES (:NEW.ID, :NEW.NAME, :NEW.NATIONALITY);
    INSERT INTO INFO_PROFESIONAL VALUES(:NEW.ID,:NEW.TEAN,:NEW.COUNTRY,:NEW.CATEGORY);
    COMMIT;
END TR_INSERTA_CICLISTA;


--5 

CREATE TABLE LOG_INSERCION (
    Usuario VARCHAR2(30),
    Fecha DATE
)

CREATE OR REPLACE TRIGGER TR_LOG_INSERCION_TOUR 
AFTER INSERT ON TOUR 
BEGIN 
    INSERT INTO LOG_INSERCION VALUES (USER, SYSDATE);
END TR_LOG_INSERCION_TOUR;

INSERT INTO TOUR VALUES (500,'UMA', 'Spain','Pablo Nieto', 'ESP', 'BJJ');
COMMIT;

--6 
Volvamos a la tabla TOUR. Cree una vista llamada TOUR_SPAIN con los ciclistas españoles Y
una vista llamada TOUR_ITALY con los ciclistas italianos (Nacionalidad).

CREATE OR REPLACE VIEW TOUR_SPAIN AS
SELECT * FROM TOUR WHERE UPPER(NATIONALITY) = 'ESP';

SELECT * FROM TOUR_SPAIN;

CREATE OR REPLACE VIEW TOUR_ITALY AS
SELECT * FROM TOUR WHERE UPPER(NATIONALITY) = 'ITA';

SELECT * FROM TOUR_ITALY;

--7 --8 --9
CREATE OR REPLACE VIEW TOUR_SPAIN AS
SELECT * FROM TOUR WHERE UPPER(NATIONALITY) = 'ESP'
WITH CHECK OPTION;

CREATE OR REPLACE VIEW TOUR_ITALY AS
SELECT * FROM TOUR WHERE UPPER(NATIONALITY) = 'ITA'
WITH CHECK OPTION;;