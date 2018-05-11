/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     01/03/2018 09:01:38 p. m.                    */
/*==============================================================*/


drop table if exists PROGRAMAOFERTADO;

drop table if exists PROGRAMA;

drop table if exists FACULTAD;

drop table if exists PERIODO;

/*==============================================================*/
/* Table: FACULTAD                                              */
/*==============================================================*/
create table FACULTAD
(
   FACID                numeric(3,0) not null,
   FACNOMBRE            varchar(100) not null,
   primary key (FACID)
);

/*==============================================================*/
/* Table: PROGRAMA                                              */
/*==============================================================*/
create table PROGRAMA
(
   PROID                numeric(3,0) not null,
   FACID                numeric(3,0) not null,
   PRONOMBRE            varchar(100) not null,
   PROSEDE				varchar(15)  not null,
   primary key (PROID)
);

/*==============================================================*/
/* Table: PERIODO                                               */
/*==============================================================*/
create table PERIODO
(
	PERID				varchar(6) not null,
	PERMINPOP			numeric(3,0) not null,
	PERMINREG			numeric(3,0) not null,
	PERVIGENTE			numeric(1,0) not null,
	primary key (PERID)
);

/*==============================================================*/
/* Table: PROGRAMAOFERTADO                                      */
/*==============================================================*/
create table PROGRAMAOFERTADO
(
   POFID				numeric(3,0) not null,
   PROID                numeric(3,0) not null,
   PERID		        varchar(6) not null,
   POFCUPOSREG          numeric(2,0) not null,
   POFCUPOSZONAMARGINAL numeric(2,0) not null,
   POFCUPOSDIFICILACCESO numeric(2,0) not null,
   POFCUPOSNORMALISTA   numeric(2,0) not null,
   POFCUPOSINDIGENA     numeric(2,0) not null,
   POFCUPOSAFRODESCENDIENTE numeric(2,0) not null,
   POFCUPOSCOSTAPACIFICA numeric(2,0) not null,
   POFPONDLECTURACRITICA numeric(2,0) not null,
   POFPONDMATEMATICAS   numeric(2,0) not null,
   POFPONDCSOCIALES     numeric(2,0) not null,
   POFPONDCNATURALES    numeric(2,0) not null,
   POFPONDINGLES        numeric(2,0) not null,
   POFPONDPRUEBAAD      numeric(2,0),
   POFLISTAESPERA 		numeric(2,0) not null,
   POFTOTALCUPOS 		numeric(2,0) not null,
   primary key (POFID)
);

alter table PROGRAMA add constraint FK_TIENE foreign key (FACID)
      references FACULTAD (FACID) on delete restrict on update restrict;

alter table PROGRAMAOFERTADO add constraint FK_OFERTADO foreign key (PROID)
      references PROGRAMA (PROID) on delete restrict on update restrict;
	  
alter table PROGRAMAOFERTADO add constraint FK_PERIODO foreign key (PERID)
      references PERIODO (PERID) on delete restrict on update restrict;	  


INSERT INTO PERIODO VALUES ('2017.2', 165, 160, 1);	  
	  
INSERT INTO FACULTAD VALUES (100, 'Facultad de Artes');
INSERT INTO FACULTAD VALUES (110, 'Facultad de Ciencias Agrarias');
INSERT INTO FACULTAD VALUES (120, 'Facultad de Ciencias de la Salud');
INSERT INTO FACULTAD VALUES (130, 'Facultad de Ciencias Contables, Economicas y Administrativas');
INSERT INTO FACULTAD VALUES (140, 'Facultad de Ciencias Humanas y Sociales');
INSERT INTO FACULTAD VALUES (150, 'Facultad de Ciencias Naturales, Exactas y de la Educacion');
INSERT INTO FACULTAD VALUES (160, 'Facultad de Derecho, Ciencias Politicas y Sociales');
INSERT INTO FACULTAD VALUES (170, 'Facultad de Ingenieria Civil');
INSERT INTO FACULTAD VALUES (180, 'Facultad de Ingenieria Electronica y Telecomunicaciones');

INSERT INTO PROGRAMA VALUES (100, 180, 'Ingenieria de Sistemas', 'Popayán');
INSERT INTO PROGRAMA VALUES (101, 180, 'Ingenieria en Automatica Industrial', 'Regionalización');
INSERT INTO PROGRAMA VALUES (102, 180, 'Ingenieria Electronica y Telecomunicaciones', 'Popayán');
INSERT INTO PROGRAMA VALUES (103, 180, 'Tecnologia en Telematica', 'Popayán');

INSERT INTO PROGRAMA VALUES (104, 100, 'Artes Plásticas', 'Popayán');
INSERT INTO PROGRAMA VALUES (105, 100, 'Diseño Gráfico', 'Popayán');
INSERT INTO PROGRAMA VALUES (106, 100, 'Dirección de Banda', 'Regionalización');

INSERT INTO PROGRAMAOFERTADO VALUES (1,100, '2017.2', 54, 1, 1, 1, 1, 1, 1, 25, 35, 10, 20, 10, -1, 10, 60);
INSERT INTO PROGRAMAOFERTADO VALUES (2,101, '2017.2', 46, 1, 1, 1, 1, 1, 1, 25, 35, 10, 20, 10, -1, 8, 52);
INSERT INTO PROGRAMAOFERTADO VALUES (3,102, '2017.2', 56, 1, 1, 1, 1, 1, 1, 25, 35, 10, 20, 10, -1, 5, 62);
INSERT INTO PROGRAMAOFERTADO VALUES (4,103, '2017.2', 30, 1, 1, 1, 1, 1, 1, 25, 35, 10, 20, 10, -1, 12, 36);

INSERT INTO PROGRAMAOFERTADO VALUES (5,104, '2017.2', 30, 1, 1, 1, 1, 1, 1, 40, 10, 30, 10, 10, 40, 12, 36);
INSERT INTO PROGRAMAOFERTADO VALUES (6,105, '2017.2', 56, 1, 1, 1, 1, 1, 1, 40, 20, 20, 10, 10, 35, 5, 62);
INSERT INTO PROGRAMAOFERTADO VALUES (7,106, '2017.2', 30, 1, 1, 1, 1, 1, 1, 40, 10, 30, 10, 10, 35, 12, 36);