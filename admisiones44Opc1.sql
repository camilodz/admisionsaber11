/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     29/05/2018 07:45:11 a. m.                    */
/*==============================================================*/
drop table if exists RESULTADOICFES;

drop table if exists ASPIRANTE_NO_PONDERABLE;

drop table if exists ASPIRANTE_PONDERABLE;

drop table if exists LISTA_ADMITIDOS;

drop table if exists APLICA;

drop table if exists ASPIRANTE;

drop table if exists PROGRAMAOFERTADO;

drop table if exists PERIODO;

drop table if exists PROGRAMA;

drop table if exists FACULTAD;

/*==============================================================*/
/* Table: APLICA                                                */
/*==============================================================*/
create table APLICA
(
   APLIID               numeric(3,0) not null,
   POFID                numeric(3,0) not null,
   ASPID                numeric(6,0) not null,
   APLIPRIORIDAD        numeric(1,0) not null,
   APLISNP              varchar(15) not null,
   primary key (APLIID)
);

/*==============================================================*/
/* Table: ASPIRANTE                                             */
/*==============================================================*/
create table ASPIRANTE
(
   ASPID                numeric(6,0) not null,
   ASPTIPODOC           varchar(2) not null,
   ASPNOMBRE            varchar(50) not null,
   ASPFECHANAC          date not null,
   ASPTIPOINSCRITO      varchar(30) not null,
   ASPCORREO            varchar(40) not null,
   primary key (ASPID)
);

/*==============================================================*/
/* Table: ASPIRANTE_NO_PONDERABLE                               */
/*==============================================================*/
create table ASPIRANTE_NO_PONDERABLE
(
   ASPID                numeric(6,0) not null,
   ASPMOTIVO            varchar(20) not null,
   primary key (ASPID)
);

/*==============================================================*/
/* Table: ASPIRANTE_PONDERABLE                                  */
/*==============================================================*/
create table ASPIRANTE_PONDERABLE
(
   ASPID                numeric(6,0) not null,
   ASPTIPO              varchar(13) not null,
   ASPPONDTOTAL         numeric(3,0) not null,
   ASPLECTURACRITICA   numeric(3,0) not null,
   ASPMATEMATICAS       numeric(3,0) not null,
   ASPCSOCIALES         numeric(3,0) not null,
   ASPCNATURALES        numeric(3,0) not null,
   ASPINGLES            numeric(3,0) not null,
   ASPPRUEBAAD          numeric(3,0),
   primary key (ASPID)
);

/*==============================================================*/
/* Table: FACULTAD                                              */
/*==============================================================*/
create table FACULTAD
(
   FACID                numeric(3,0) not null,
   FACNOMBRE            varchar(40) not null,
   primary key (FACID)
);

/*==============================================================*/
/* Table: LISTA_ADMITIDOS                                       */
/*==============================================================*/
create table LISTA_ADMITIDOS
(
   APLIID               numeric(3,0) not null,
   LISTESTADO           varchar(15) not null,
   LISTTIPOADMITIDO     varchar(30) not null,
   primary key (APLIID)
);

/*==============================================================*/
/* Table: PERIODO                                               */
/*==============================================================*/
create table PERIODO
(
   PERID                varchar(6) not null,
   PERMINPOP            numeric(3,0) not null,
   PERMINREG            numeric(3,0) not null,
   PERVIGENTE           numeric(1,0) not null,
   primary key (PERID)
);

/*==============================================================*/
/* Table: PROGRAMA                                              */
/*==============================================================*/
create table PROGRAMA
(
   PROID                numeric(3,0) not null,
   FACID                numeric(3,0) not null,
   PRONOMBRE            varchar(40) not null,
   PROSEDE              varchar(15) not null,
   primary key (PROID)
);

/*==============================================================*/
/* Table: PROGRAMAOFERTADO                                      */
/*==============================================================*/
create table PROGRAMAOFERTADO
(
   POFID                numeric(3,0) not null,
   PROID                numeric(3,0) not null,
   PERID                varchar(6) not null,
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
   POFLISTAESPERA       numeric(2,0) not null,
   POFTOTALCUPOS        numeric(2,0) not null,
   primary key (POFID)
);

/*==============================================================*/
/* Table: RESULTADOICFES                                        */
/*==============================================================*/
create table RESULTADOICFES
(
   RESSNP               varchar(15) not null,
   RESPERIODO			varchar(6) not null,
   RESNOMBREEST         varchar(50) not null,
   RESTIPODOCEST        numeric(2,0) not null,
   RESIDEST             numeric(6,0) not null,
   RESGLOBAL            numeric(3,0) not null,
   RESLECTURACRITICA   numeric(3,0) not null,
   RESMATEMATICAS       numeric(3,0) not null,
   RESCSOCIALES         numeric(3,0) not null,
   RESCNATURALES        numeric(3,0) not null,
   RESINGLES            numeric(3,0) not null,
   primary key (RESSNP)
);

alter table APLICA add constraint FK_APLICA foreign key (ASPID)
      references ASPIRANTE (ASPID) on delete restrict on update restrict;

alter table APLICA add constraint FK_APLICAN foreign key (POFID)
      references PROGRAMAOFERTADO (POFID) on delete restrict on update restrict;

alter table ASPIRANTE_NO_PONDERABLE add constraint FK_INHERITANCE_2 foreign key (ASPID)
      references ASPIRANTE_PONDERABLE (ASPID) on delete restrict on update restrict;

alter table ASPIRANTE_PONDERABLE add constraint FK_INHERITANCE_1 foreign key (ASPID)
      references ASPIRANTE (ASPID) on delete restrict on update restrict;

alter table LISTA_ADMITIDOS add constraint FK_PRESENTA foreign key (APLIID)
      references APLICA (APLIID) on delete restrict on update restrict;

alter table PROGRAMA add constraint FK_TIENE foreign key (FACID)
      references FACULTAD (FACID) on delete restrict on update restrict;

alter table PROGRAMAOFERTADO add constraint FK_OFERTADO foreign key (PROID)
      references PROGRAMA (PROID) on delete restrict on update restrict;

alter table PROGRAMAOFERTADO add constraint FK_TIENE__ foreign key (PERID)
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


insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (1000, 'CC', 'Daria Lourenco', '9/8/1998', 'NORMAL', 'dlourenco0@omniture.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (2000, 'CC', 'Annmaria Dabney', '10/4/1997', 'NORMAL', 'adabney1@tiny.cc');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (3000, 'CC', 'Gerrilee McCobb', '7/22/1996', 'NORMAL', 'gmccobb2@ted.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (4000, 'CC', 'Frasco Guarin', '1/17/1998', 'NORMAL', 'fguarin3@yale.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (5000, 'CC', 'Valaree Butteris', '9/15/1998', 'NORMAL', 'vbutteris4@prweb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (6000, 'CC', 'Raynell Klausewitz', '8/10/2002', 'NORMAL', 'rklausewitz5@biglobe.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (7000, 'CC', 'Charlene Clinning', '7/13/2002', 'NORMAL', 'cclinning6@a8.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (8000, 'CC', 'Vernice Stansall', '1/3/1999', 'NORMAL', 'vstansall7@freewebs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (9000, 'CC', 'Hephzibah Dielhenn', '12/26/2004', 'NORMAL', 'hdielhenn8@bbb.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (10000, 'CC', 'Ebenezer Bullivent', '11/6/1996', 'NORMAL', 'ebullivent9@accuweather.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (11000, 'CC', 'Stormy Kosel', '3/31/2001', 'NORMAL', 'skosela@nbcnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (12000, 'CC', 'Carmel Doumenc', '2/8/1999', 'NORMAL', 'cdoumencb@ebay.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (13000, 'CC', 'Alberto Kernock', '4/20/1998', 'NORMAL', 'akernockc@npr.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (14000, 'CC', 'Vivia McAlpine', '11/8/1997', 'NORMAL', 'vmcalpined@wikipedia.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (15000, 'CC', 'Iris Balcombe', '10/14/2002', 'NORMAL', 'ibalcombee@wordpress.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (16000, 'CC', 'Eugen Biskup', '6/6/2002', 'NORMAL', 'ebiskupf@samsung.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (17000, 'CC', 'Alisander Tribell', '4/13/1996', 'NORMAL', 'atribellg@sfgate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (18000, 'CC', 'Alexis Dennerley', '6/7/1995', 'NORMAL', 'adennerleyh@sitemeter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (19000, 'CC', 'Avie Brocket', '3/24/1998', 'NORMAL', 'abrocketi@cnbc.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (20000, 'CC', 'Betti Gorick', '10/30/2001', 'NORMAL', 'bgorickj@china.com.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (21000, 'CC', 'Sheff Lamborn', '7/12/1996', 'NORMAL', 'slambornk@pinterest.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (22000, 'CC', 'Merl Sidney', '3/30/2004', 'NORMAL', 'msidneyl@springer.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (23000, 'CC', 'Alayne Slograve', '11/3/2004', 'NORMAL', 'aslogravem@cdc.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (24000, 'CC', 'Mickey Geeve', '6/13/2003', 'NORMAL', 'mgeeven@opensource.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (25000, 'CC', 'Caresa Emmitt', '4/9/2003', 'NORMAL', 'cemmitto@sohu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (26000, 'CC', 'Thibaud Bursnall', '1/2/1997', 'NORMAL', 'tbursnallp@weibo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (27000, 'CC', 'Elnore Gossage', '10/14/1999', 'NORMAL', 'egossageq@ox.ac.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (28000, 'CC', 'Colline Bickers', '7/27/2002', 'NORMAL', 'cbickersr@ow.ly');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (29000, 'CC', 'Lizette Duddell', '12/7/2004', 'NORMAL', 'lduddells@baidu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (30000, 'CC', 'Cora Miguel', '1/10/2002', 'NORMAL', 'cmiguelt@meetup.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (31000, 'CC', 'Charil Coweuppe', '4/23/2001', 'NORMAL', 'ccoweuppeu@unc.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (32000, 'CC', 'Jerry Aingell', '12/17/1999', 'NORMAL', 'jaingellv@amazon.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (33000, 'CC', 'Elvis Nolleau', '5/18/2003', 'NORMAL', 'enolleauw@businesswire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (34000, 'CC', 'Adolf Holdey', '4/17/2002', 'NORMAL', 'aholdeyx@eventbrite.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (35000, 'CC', 'Axel Garfath', '4/20/1996', 'NORMAL', 'agarfathy@bluehost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (36000, 'CC', 'Wright Humby', '6/30/2000', 'NORMAL', 'whumbyz@posterous.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (37000, 'CC', 'Martin Keson', '1/26/2000', 'NORMAL', 'mkeson10@icio.us');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (38000, 'CC', 'Bogart Harbertson', '5/5/1999', 'NORMAL', 'bharbertson11@cocolog-nifty.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (39000, 'CC', 'Winni Bertomier', '3/11/2004', 'NORMAL', 'wbertomier12@gov.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (40000, 'CC', 'Lawrence Thomas', '6/4/1995', 'NORMAL', 'lthomas13@buzzfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (41000, 'CC', 'Emory Timny', '6/29/1996', 'NORMAL', 'etimny14@friendfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (42000, 'CC', 'Alys McComiskie', '4/28/1998', 'NORMAL', 'amccomiskie15@hud.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (43000, 'CC', 'Stanly Burgyn', '5/13/2000', 'NORMAL', 'sburgyn16@noaa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (44000, 'CC', 'Lanny Dovidaitis', '9/13/2004', 'NORMAL', 'ldovidaitis17@squidoo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (45000, 'CC', 'Rowena Facchini', '4/19/2004', 'NORMAL', 'rfacchini18@shutterfly.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (46000, 'CC', 'Ediva Dogg', '12/23/1999', 'NORMAL', 'edogg19@xinhuanet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (47000, 'CC', 'Ileana Grange', '7/22/2003', 'NORMAL', 'igrange1a@china.com.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (48000, 'CC', 'Siana Landre', '4/16/1997', 'NORMAL', 'slandre1b@aol.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (49000, 'CC', 'Claudianus McCarrick', '11/26/1997', 'NORMAL', 'cmccarrick1c@github.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (50000, 'CC', 'Adolph Nequest', '7/31/1999', 'NORMAL', 'anequest1d@ft.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (51000, 'CC', 'Josy Cesco', '10/19/1996', 'NORMAL', 'jcesco1e@ocn.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (52000, 'CC', 'Clemmie Feirn', '7/2/2001', 'NORMAL', 'cfeirn1f@angelfire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (53000, 'CC', 'Isabella Pretorius', '5/7/1998', 'NORMAL', 'ipretorius1g@time.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (54000, 'CC', 'Adelle Josephi', '3/16/2000', 'NORMAL', 'ajosephi1h@princeton.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (55000, 'CC', 'Muriel Duddy', '6/11/2000', 'NORMAL', 'mduddy1i@zdnet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (56000, 'CC', 'Neilla Sunderland', '3/16/2005', 'NORMAL', 'nsunderland1j@sun.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (57000, 'CC', 'Inglebert Pilmoor', '3/19/2002', 'NORMAL', 'ipilmoor1k@amazon.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (58000, 'CC', 'Nettle Jiggens', '11/19/1996', 'NORMAL', 'njiggens1l@archive.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (59000, 'CC', 'Othella Blakeslee', '8/20/1995', 'NORMAL', 'oblakeslee1m@goodreads.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (60000, 'CC', 'Kimbra Heugh', '6/2/2004', 'NORMAL', 'kheugh1n@youtu.be');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (61000, 'CC', 'Evaleen Goodsir', '8/12/2000', 'NORMAL', 'egoodsir1o@dyndns.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (62000, 'CC', 'Spike Perutto', '6/18/2001', 'NORMAL', 'sperutto1p@networksolutions.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (63000, 'CC', 'Aubrey Ducarne', '11/6/1996', 'NORMAL', 'aducarne1q@bizjournals.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (64000, 'CC', 'Levey Merioth', '8/11/1997', 'NORMAL', 'lmerioth1r@ft.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (65000, 'CC', 'Gussi Pearsey', '4/23/2002', 'NORMAL', 'gpearsey1s@newyorker.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (66000, 'CC', 'Dick Robic', '10/9/2003', 'NORMAL', 'drobic1t@ebay.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (67000, 'CC', 'Kym Eastlake', '11/24/1999', 'NORMAL', 'keastlake1u@wix.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (68000, 'CC', 'Xaviera Carrick', '1/10/2000', 'NORMAL', 'xcarrick1v@bloglines.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (69000, 'CC', 'Sallyanne Lacaze', '1/9/2005', 'NORMAL', 'slacaze1w@hc360.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (70000, 'CC', 'Danyette Meynell', '9/5/1995', 'NORMAL', 'dmeynell1x@studiopress.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (71000, 'CC', 'Laurent Bartels', '11/13/2004', 'NORMAL', 'lbartels1y@prlog.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (72000, 'CC', 'Winnifred Cattle', '10/23/1996', 'NORMAL', 'wcattle1z@tinyurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (73000, 'CC', 'Jaquenette Muat', '4/30/1998', 'NORMAL', 'jmuat20@illinois.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (74000, 'CC', 'Des Risson', '3/7/2003', 'NORMAL', 'drisson21@hubpages.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (75000, 'CC', 'Charleen Milius', '6/30/1999', 'NORMAL', 'cmilius22@slideshare.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (76000, 'CC', 'Dalt Valentinetti', '3/24/2003', 'NORMAL', 'dvalentinetti23@marriott.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (77000, 'CC', 'Sydel Sabine', '11/26/2004', 'NORMAL', 'ssabine24@msn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (78000, 'CC', 'Bunny Bottomore', '5/6/1998', 'NORMAL', 'bbottomore25@mit.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (79000, 'CC', 'Kilian Downey', '2/2/2005', 'NORMAL', 'kdowney26@mayoclinic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (80000, 'CC', 'Arel Urwin', '3/23/1996', 'NORMAL', 'aurwin27@wufoo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (81000, 'CC', 'Christy MacAllan', '10/29/1998', 'NORMAL', 'cmacallan28@buzzfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (82000, 'CC', 'Claudine Guerrazzi', '9/6/1998', 'NORMAL', 'cguerrazzi29@netvibes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (83000, 'CC', 'Bernadene Hurry', '9/8/2001', 'NORMAL', 'bhurry2a@businesswire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (84000, 'CC', 'Wildon McMains', '6/28/1998', 'NORMAL', 'wmcmains2b@google.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (85000, 'CC', 'Lockwood Langsbury', '12/8/1998', 'NORMAL', 'llangsbury2c@smh.com.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (86000, 'CC', 'Basilius Flarity', '9/2/1996', 'NORMAL', 'bflarity2d@barnesandnoble.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (87000, 'CC', 'Orren McLurg', '8/4/1999', 'NORMAL', 'omclurg2e@barnesandnoble.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (88000, 'CC', 'Mylo Sewill', '7/23/1997', 'NORMAL', 'msewill2f@twitter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (89000, 'CC', 'Bobby China', '12/28/2002', 'NORMAL', 'bchina2g@arstechnica.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (90000, 'CC', 'Lisette Elcoux', '12/27/1997', 'NORMAL', 'lelcoux2h@wsj.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (91000, 'CC', 'Thayne Bend', '10/15/2002', 'NORMAL', 'tbend2i@mozilla.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (92000, 'CC', 'Jeff Butland', '6/15/1995', 'NORMAL', 'jbutland2j@dmoz.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (93000, 'CC', 'Allan Thoday', '1/30/2005', 'NORMAL', 'athoday2k@reverbnation.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (94000, 'CC', 'Franklin Garci', '1/11/1997', 'NORMAL', 'fgarci2l@washington.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (95000, 'CC', 'Raphael Andriulis', '3/15/2001', 'NORMAL', 'randriulis2m@washingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (96000, 'CC', 'Shellie Kellough', '8/9/2002', 'NORMAL', 'skellough2n@vkontakte.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (97000, 'CC', 'Mariann Petegre', '10/12/2002', 'NORMAL', 'mpetegre2o@booking.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (98000, 'CC', 'Valaria Kilbane', '10/2/1999', 'NORMAL', 'vkilbane2p@europa.eu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (99000, 'CC', 'Lisbeth Trainor', '4/25/2005', 'NORMAL', 'ltrainor2q@ocn.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (100000, 'CC', 'Jamesy Borit', '3/23/1996', 'NORMAL', 'jborit2r@usa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (101000, 'CC', 'Rivy Capin', '5/10/1996', 'NORMAL', 'rcapin2s@adobe.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (102000, 'CC', 'Babs Vinau', '3/21/2001', 'NORMAL', 'bvinau2t@nasa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (103000, 'CC', 'Dyan Choak', '5/24/2004', 'NORMAL', 'dchoak2u@icq.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (104000, 'CC', 'Mel Gudgion', '11/12/2000', 'NORMAL', 'mgudgion2v@cnbc.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (105000, 'CC', 'Nickolaus Romaine', '10/28/2002', 'NORMAL', 'nromaine2w@guardian.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (106000, 'CC', 'Karmen McGowran', '8/25/1997', 'NORMAL', 'kmcgowran2x@indiatimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (107000, 'CC', 'Emmanuel Learoyde', '11/5/1996', 'NORMAL', 'elearoyde2y@sohu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (108000, 'CC', 'Netti Myner', '1/4/2003', 'NORMAL', 'nmyner2z@ovh.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (109000, 'CC', 'Kerry Kevane', '6/3/1998', 'NORMAL', 'kkevane30@mozilla.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (110000, 'CC', 'Jillian Becker', '11/14/1998', 'NORMAL', 'jbecker31@google.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (111000, 'CC', 'Gardy Primo', '6/8/1997', 'NORMAL', 'gprimo32@narod.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (112000, 'CC', 'Pavlov Havik', '5/18/2004', 'NORMAL', 'phavik33@ustream.tv');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (113000, 'CC', 'Budd Dundridge', '7/26/1999', 'NORMAL', 'bdundridge34@free.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (114000, 'CC', 'Philly Ubsdell', '4/18/2000', 'NORMAL', 'pubsdell35@wikipedia.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (115000, 'CC', 'Nadine Middler', '10/22/2004', 'NORMAL', 'nmiddler36@utexas.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (116000, 'CC', 'Mickie Caisley', '4/30/1999', 'NORMAL', 'mcaisley37@cnn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (117000, 'CC', 'Denny Grafton-Herbert', '10/15/2002', 'NORMAL', 'dgraftonherbert38@twitter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (118000, 'CC', 'Pam Ilyunin', '10/15/1998', 'NORMAL', 'pilyunin39@wired.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (119000, 'CC', 'Silvana Di Franceschi', '9/10/2004', 'NORMAL', 'sdi3a@alexa.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (120000, 'CC', 'Lydon Antill', '1/17/2005', 'NORMAL', 'lantill3b@cnn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (121000, 'CC', 'Kare Fines', '2/24/1998', 'NORMAL', 'kfines3c@abc.net.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (122000, 'CC', 'Licha McCowan', '11/15/1996', 'NORMAL', 'lmccowan3d@nationalgeographic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (123000, 'CC', 'Jemie Satterfitt', '8/22/2004', 'NORMAL', 'jsatterfitt3e@goo.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (124000, 'CC', 'Irvin Ciobutaro', '12/10/1996', 'NORMAL', 'iciobutaro3f@chicagotribune.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (125000, 'CC', 'Ruben Radbone', '5/30/2002', 'NORMAL', 'rradbone3g@webs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (126000, 'CC', 'Collen Caplan', '7/27/2002', 'NORMAL', 'ccaplan3h@earthlink.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (127000, 'CC', 'Arabel Lubbock', '10/13/1995', 'NORMAL', 'alubbock3i@paginegialle.it');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (128000, 'CC', 'Lamond Facer', '8/16/1998', 'NORMAL', 'lfacer3j@fastcompany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (129000, 'CC', 'Fidela Tiebe', '9/13/2002', 'NORMAL', 'ftiebe3k@blog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (130000, 'CC', 'Greta Toop', '9/8/1998', 'NORMAL', 'gtoop3l@fc2.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (131000, 'CC', 'Moises Lynd', '4/30/1998', 'NORMAL', 'mlynd3m@cbsnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (132000, 'CC', 'Harwilll Dreini', '10/16/2000', 'NORMAL', 'hdreini3n@google.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (133000, 'CC', 'Truman Phant', '12/2/2002', 'NORMAL', 'tphant3o@paypal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (134000, 'CC', 'Fidelia Cobelli', '10/4/2003', 'NORMAL', 'fcobelli3p@ycombinator.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (135000, 'CC', 'Bennett D''Ambrogio', '1/23/2005', 'NORMAL', 'bdambrogio3q@whitehouse.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (136000, 'CC', 'Nikos Criple', '5/2/1997', 'NORMAL', 'ncriple3r@netlog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (137000, 'CC', 'Eachelle Stickells', '11/20/2003', 'NORMAL', 'estickells3s@economist.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (138000, 'CC', 'Tracie Baythrop', '7/16/2003', 'NORMAL', 'tbaythrop3t@bbc.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (139000, 'CC', 'Brenna Northrop', '10/14/2004', 'NORMAL', 'bnorthrop3u@tinypic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (140000, 'CC', 'Salem Lyford', '8/2/2002', 'NORMAL', 'slyford3v@fda.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (141000, 'CC', 'Melodee Colebourne', '3/8/2004', 'NORMAL', 'mcolebourne3w@chronoengine.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (142000, 'CC', 'Garvin Shelf', '3/29/1997', 'NORMAL', 'gshelf3x@behance.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (143000, 'CC', 'Freddy Giacomini', '11/19/2003', 'NORMAL', 'fgiacomini3y@elegantthemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (144000, 'CC', 'Serge Crisford', '6/27/2000', 'NORMAL', 'scrisford3z@about.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (145000, 'CC', 'Bjorn Oris', '1/9/2002', 'NORMAL', 'boris40@spotify.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (146000, 'CC', 'Addia Dunham', '12/12/1996', 'NORMAL', 'adunham41@chicagotribune.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (147000, 'CC', 'Joceline Thalmann', '1/14/1998', 'NORMAL', 'jthalmann42@edublogs.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (148000, 'CC', 'Sharline Purselow', '12/13/1997', 'NORMAL', 'spurselow43@time.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (149000, 'CC', 'Casi Diament', '6/20/1999', 'NORMAL', 'cdiament44@mayoclinic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (150000, 'CC', 'Katrinka Hurst', '2/2/2002', 'NORMAL', 'khurst45@tmall.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (151000, 'CC', 'Stephine Lamburn', '12/30/2000', 'NORMAL', 'slamburn46@e-recht24.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (152000, 'CC', 'Issiah Emma', '6/10/1999', 'NORMAL', 'iemma47@linkedin.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (153000, 'CC', 'Aprilette Ciciari', '3/12/2003', 'NORMAL', 'aciciari48@netscape.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (154000, 'CC', 'Lamond Saill', '6/29/2000', 'NORMAL', 'lsaill49@nps.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (155000, 'CC', 'Opalina Elvin', '8/26/2000', 'NORMAL', 'oelvin4a@liveinternet.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (156000, 'CC', 'Leah Larkby', '8/14/1997', 'NORMAL', 'llarkby4b@pen.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (157000, 'CC', 'Don Marcoolyn', '12/20/2002', 'NORMAL', 'dmarcoolyn4c@netvibes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (158000, 'CC', 'Demetris Pagel', '7/26/1999', 'NORMAL', 'dpagel4d@usatoday.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (159000, 'CC', 'Aurie Diamant', '12/13/2000', 'NORMAL', 'adiamant4e@acquirethisname.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (160000, 'CC', 'Woodrow Slocombe', '11/1/1996', 'NORMAL', 'wslocombe4f@google.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (161000, 'CC', 'Peirce Hacaud', '2/27/1997', 'NORMAL', 'phacaud4g@addtoany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (162000, 'CC', 'Lucio Duddy', '1/28/1999', 'NORMAL', 'lduddy4h@cam.ac.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (163000, 'CC', 'Rooney Braven', '1/12/2001', 'NORMAL', 'rbraven4i@globo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (164000, 'CC', 'Joleen Dressel', '11/6/1998', 'NORMAL', 'jdressel4j@yellowbook.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (165000, 'CC', 'Douglas Roberson', '7/28/1999', 'NORMAL', 'droberson4k@nih.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (166000, 'CC', 'Dorey Tompkiss', '1/7/2002', 'NORMAL', 'dtompkiss4l@smh.com.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (167000, 'CC', 'Vin Keirl', '9/22/1999', 'NORMAL', 'vkeirl4m@twitpic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (168000, 'CC', 'Delano Marjoram', '4/27/2000', 'NORMAL', 'dmarjoram4n@sun.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (169000, 'CC', 'Royce Zanetto', '1/10/1999', 'NORMAL', 'rzanetto4o@trellian.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (170000, 'CC', 'Imogen Pratton', '1/13/1999', 'NORMAL', 'ipratton4p@etsy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (171000, 'CC', 'Caleb Tape', '7/27/1995', 'NORMAL', 'ctape4q@ustream.tv');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (172000, 'CC', 'Annabella Zarb', '1/31/2003', 'NORMAL', 'azarb4r@engadget.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (173000, 'CC', 'Inness Cantera', '3/22/2002', 'NORMAL', 'icantera4s@google.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (174000, 'CC', 'Johnnie Kearford', '8/15/2002', 'NORMAL', 'jkearford4t@forbes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (175000, 'CC', 'Hamlin Klimushev', '11/12/1997', 'NORMAL', 'hklimushev4u@home.pl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (176000, 'CC', 'Florentia Kall', '6/26/2003', 'NORMAL', 'fkall4v@techcrunch.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (177000, 'CC', 'Rosalind Pughsley', '11/13/1998', 'NORMAL', 'rpughsley4w@marketwatch.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (178000, 'CC', 'Rockwell Londer', '1/12/1998', 'NORMAL', 'rlonder4x@seesaa.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (179000, 'CC', 'Paquito Mayor', '10/23/1995', 'NORMAL', 'pmayor4y@mapy.cz');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (180000, 'CC', 'Koren Sheppey', '9/27/2004', 'NORMAL', 'ksheppey4z@umich.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (181000, 'CC', 'Kay Fudge', '11/23/2003', 'NORMAL', 'kfudge50@webnode.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (182000, 'CC', 'Halie Sutherel', '3/1/2004', 'NORMAL', 'hsutherel51@wikimedia.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (183000, 'CC', 'Abran Wrixon', '7/21/1998', 'NORMAL', 'awrixon52@webmd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (184000, 'CC', 'Raffaello Nanni', '5/31/2004', 'NORMAL', 'rnanni53@delicious.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (185000, 'CC', 'Ludovico Baglin', '6/9/1999', 'NORMAL', 'lbaglin54@epa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (186000, 'CC', 'L;urette Keller', '8/19/1999', 'NORMAL', 'lkeller55@house.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (187000, 'CC', 'Wolfgang Sumpton', '12/5/1997', 'NORMAL', 'wsumpton56@edublogs.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (188000, 'CC', 'Kerby Sissons', '5/10/2001', 'NORMAL', 'ksissons57@free.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (189000, 'CC', 'Arney Duny', '2/9/2005', 'NORMAL', 'aduny58@fastcompany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (190000, 'CC', 'Lissi Littrell', '9/6/2001', 'NORMAL', 'llittrell59@woothemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (191000, 'CC', 'Bram Loseke', '1/27/2005', 'NORMAL', 'bloseke5a@theatlantic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (192000, 'CC', 'Kay Rosenshine', '6/16/2002', 'NORMAL', 'krosenshine5b@tuttocitta.it');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (193000, 'CC', 'Sheila-kathryn Guess', '9/18/2002', 'NORMAL', 'sguess5c@shareasale.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (194000, 'CC', 'Karin Hegley', '8/15/2002', 'NORMAL', 'khegley5d@umn.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (195000, 'CC', 'Consolata Pluvier', '4/20/1996', 'NORMAL', 'cpluvier5e@feedburner.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (196000, 'CC', 'Estel Blees', '8/13/2004', 'NORMAL', 'eblees5f@google.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (197000, 'CC', 'Dorry Beveridge', '5/22/2002', 'NORMAL', 'dbeveridge5g@seesaa.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (198000, 'CC', 'Maxy Joly', '8/15/2004', 'NORMAL', 'mjoly5h@csmonitor.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (199000, 'CC', 'Eleonore Moseby', '3/29/1997', 'NORMAL', 'emoseby5i@sciencedaily.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (200000, 'CC', 'Raymond Whitear', '3/1/1998', 'NORMAL', 'rwhitear5j@springer.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (201000, 'CC', 'Nannie Feron', '8/18/1999', 'NORMAL', 'nferon5k@ucsd.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (202000, 'CC', 'Clementia Dunderdale', '8/21/2004', 'NORMAL', 'cdunderdale5l@cbsnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (203000, 'CC', 'Neda Padmore', '8/1/1995', 'NORMAL', 'npadmore5m@fema.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (204000, 'CC', 'Damita Lancastle', '8/4/2000', 'NORMAL', 'dlancastle5n@woothemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (205000, 'CC', 'Leona Dell', '12/5/1999', 'NORMAL', 'ldell5o@friendfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (206000, 'CC', 'Angeli Borris', '11/17/2002', 'NORMAL', 'aborris5p@msu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (207000, 'CC', 'Archibold McIlmorie', '9/17/1997', 'NORMAL', 'amcilmorie5q@google.com.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (208000, 'CC', 'Meridel Luty', '9/1/1999', 'NORMAL', 'mluty5r@jiathis.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (209000, 'CC', 'Zollie Yakobowitch', '1/25/1997', 'NORMAL', 'zyakobowitch5s@bandcamp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (210000, 'CC', 'Celeste Waeland', '1/11/2001', 'NORMAL', 'cwaeland5t@plala.or.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (211000, 'CC', 'Lodovico Cordner', '2/8/1999', 'NORMAL', 'lcordner5u@domainmarket.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (212000, 'CC', 'Katlin Bazelle', '12/31/1996', 'NORMAL', 'kbazelle5v@google.nl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (213000, 'CC', 'Godart Woolcocks', '6/12/1998', 'NORMAL', 'gwoolcocks5w@discovery.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (214000, 'CC', 'Kalila Hanes', '11/7/2002', 'NORMAL', 'khanes5x@twitpic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (215000, 'CC', 'Hymie Pyke', '11/20/1999', 'NORMAL', 'hpyke5y@nhs.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (216000, 'CC', 'Alexandra Duxbarry', '5/26/2002', 'NORMAL', 'aduxbarry5z@gravatar.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (217000, 'CC', 'Cindee Trevethan', '7/25/1998', 'NORMAL', 'ctrevethan60@businesswire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (218000, 'CC', 'Henry Manketell', '12/13/1998', 'NORMAL', 'hmanketell61@blogtalkradio.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (219000, 'CC', 'Tristam Chezelle', '2/19/1997', 'NORMAL', 'tchezelle62@webnode.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (220000, 'CC', 'Maurise Dawe', '11/27/2000', 'NORMAL', 'mdawe63@skyrock.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (221000, 'CC', 'Natasha Witts', '8/30/2001', 'NORMAL', 'nwitts64@slate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (222000, 'CC', 'Adelaida Charrington', '10/26/1996', 'NORMAL', 'acharrington65@mayoclinic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (223000, 'CC', 'Evered Sellens', '3/20/2003', 'NORMAL', 'esellens66@twitter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (224000, 'CC', 'Berne Ofield', '3/24/1998', 'NORMAL', 'bofield67@live.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (225000, 'CC', 'Ronny Demkowicz', '10/14/2002', 'NORMAL', 'rdemkowicz68@umn.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (226000, 'CC', 'Ashton Gant', '5/4/2002', 'NORMAL', 'agant69@list-manage.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (227000, 'CC', 'Hoyt Heibl', '10/27/1997', 'NORMAL', 'hheibl6a@intel.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (228000, 'CC', 'Letti Sunock', '1/26/1996', 'NORMAL', 'lsunock6b@google.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (229000, 'CC', 'Eberhard Hoolaghan', '1/5/2005', 'NORMAL', 'ehoolaghan6c@fc2.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (230000, 'CC', 'Meg Copplestone', '3/10/1996', 'NORMAL', 'mcopplestone6d@intel.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (231000, 'CC', 'Wini Trewman', '7/13/1996', 'NORMAL', 'wtrewman6e@tinyurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (232000, 'CC', 'Isaak Armand', '3/5/2005', 'NORMAL', 'iarmand6f@economist.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (233000, 'CC', 'Klarika Tynan', '12/11/2004', 'NORMAL', 'ktynan6g@weibo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (234000, 'CC', 'Von Knath', '3/6/2000', 'NORMAL', 'vknath6h@arizona.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (235000, 'CC', 'Kingsley Hovenden', '1/26/2003', 'NORMAL', 'khovenden6i@elpais.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (236000, 'CC', 'Wang Howis', '10/25/1997', 'NORMAL', 'whowis6j@google.com.hk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (237000, 'CC', 'Marlyn Wolver', '3/12/2001', 'NORMAL', 'mwolver6k@dmoz.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (238000, 'CC', 'Bambie Loddon', '9/2/2002', 'NORMAL', 'bloddon6l@noaa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (239000, 'CC', 'Bethanne Mewrcik', '9/13/2001', 'NORMAL', 'bmewrcik6m@census.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (240000, 'CC', 'Billie Kmietsch', '6/20/2001', 'NORMAL', 'bkmietsch6n@symantec.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (241000, 'CC', 'Delmore O''Corrane', '12/12/2000', 'NORMAL', 'docorrane6o@earthlink.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (242000, 'CC', 'Jacques Shegog', '9/16/1999', 'NORMAL', 'jshegog6p@ebay.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (243000, 'CC', 'Livvy Stuer', '3/4/2004', 'NORMAL', 'lstuer6q@barnesandnoble.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (244000, 'CC', 'Uri Baird', '10/18/2001', 'NORMAL', 'ubaird6r@noaa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (245000, 'CC', 'Carena Guerola', '7/28/1997', 'NORMAL', 'cguerola6s@stanford.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (246000, 'CC', 'Alic O''Fogarty', '1/30/2001', 'NORMAL', 'aofogarty6t@answers.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (247000, 'CC', 'Norry Bigby', '6/10/2001', 'NORMAL', 'nbigby6u@wikispaces.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (248000, 'CC', 'Lucian Searsby', '12/7/2000', 'NORMAL', 'lsearsby6v@guardian.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (249000, 'CC', 'Liz Kernermann', '2/28/2001', 'NORMAL', 'lkernermann6w@hao123.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (250000, 'CC', 'Julina Lamburn', '12/16/1996', 'NORMAL', 'jlamburn6x@imdb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (251000, 'CC', 'Miles Seavers', '2/17/2003', 'NORMAL', 'mseavers6y@gmpg.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (252000, 'CC', 'Nap Stickland', '3/3/2004', 'NORMAL', 'nstickland6z@wsj.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (253000, 'CC', 'Jess Stranio', '6/18/1996', 'NORMAL', 'jstranio70@google.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (254000, 'CC', 'Alvy Brophy', '9/10/2002', 'NORMAL', 'abrophy71@blogger.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (255000, 'CC', 'Randi Shimman', '1/3/2005', 'NORMAL', 'rshimman72@163.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (256000, 'CC', 'Troy Castelijn', '11/28/2004', 'NORMAL', 'tcastelijn73@webeden.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (257000, 'CC', 'Rriocard Haskett', '5/21/2001', 'NORMAL', 'rhaskett74@zimbio.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (258000, 'CC', 'Powell Maultby', '6/21/1995', 'NORMAL', 'pmaultby75@redcross.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (259000, 'CC', 'Amie Mangion', '10/25/2002', 'NORMAL', 'amangion76@marriott.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (260000, 'CC', 'Cyndie Gohn', '6/7/1995', 'NORMAL', 'cgohn77@ibm.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (261000, 'CC', 'Philly Tolossi', '11/29/2002', 'NORMAL', 'ptolossi78@google.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (262000, 'CC', 'Chantalle Teresse', '7/8/2004', 'NORMAL', 'cteresse79@wordpress.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (263000, 'CC', 'Clint Dunstall', '4/6/2005', 'NORMAL', 'cdunstall7a@bloomberg.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (264000, 'CC', 'Noam Castangia', '11/1/1996', 'NORMAL', 'ncastangia7b@about.me');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (265000, 'CC', 'Marty Clapison', '1/13/2004', 'NORMAL', 'mclapison7c@4shared.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (266000, 'CC', 'Alfie Grist', '7/5/2002', 'NORMAL', 'agrist7d@gmpg.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (267000, 'CC', 'Nickolas Monk', '2/23/1996', 'NORMAL', 'nmonk7e@gizmodo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (268000, 'CC', 'Bernita Drysdell', '2/7/1997', 'NORMAL', 'bdrysdell7f@csmonitor.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (269000, 'CC', 'Darren MacKimmie', '2/1/2003', 'NORMAL', 'dmackimmie7g@twitpic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (270000, 'CC', 'Fidelio Gladstone', '2/25/2002', 'NORMAL', 'fgladstone7h@slideshare.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (271000, 'CC', 'Dorelia Bathoe', '5/13/2001', 'NORMAL', 'dbathoe7i@unicef.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (272000, 'CC', 'Godfrey Probbings', '1/21/2003', 'NORMAL', 'gprobbings7j@sina.com.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (273000, 'CC', 'Franklin Billison', '5/28/1997', 'NORMAL', 'fbillison7k@geocities.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (274000, 'CC', 'Ermentrude Gover', '3/26/2002', 'NORMAL', 'egover7l@cmu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (275000, 'CC', 'Constance Alcorn', '3/14/2002', 'NORMAL', 'calcorn7m@livejournal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (276000, 'CC', 'Nadia Sleep', '1/9/2003', 'NORMAL', 'nsleep7n@usatoday.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (277000, 'CC', 'Liliane Di Iorio', '12/17/2001', 'NORMAL', 'ldi7o@storify.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (278000, 'CC', 'Elana Wellard', '10/16/2001', 'NORMAL', 'ewellard7p@ucoz.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (279000, 'CC', 'Sibbie Link', '2/10/1997', 'NORMAL', 'slink7q@goodreads.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (280000, 'CC', 'Amye Tunkin', '11/7/2004', 'NORMAL', 'atunkin7r@engadget.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (281000, 'CC', 'Lebbie Vidineev', '5/4/2001', 'NORMAL', 'lvidineev7s@seattletimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (282000, 'CC', 'Ichabod Tripony', '10/3/2002', 'NORMAL', 'itripony7t@who.int');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (283000, 'CC', 'Seana Carlozzi', '8/22/2002', 'NORMAL', 'scarlozzi7u@goo.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (284000, 'CC', 'Barbara Cattermull', '8/19/1996', 'NORMAL', 'bcattermull7v@livejournal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (285000, 'CC', 'Sandye Klimpke', '1/26/2005', 'NORMAL', 'sklimpke7w@163.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (286000, 'CC', 'Garner Sebert', '5/14/2002', 'NORMAL', 'gsebert7x@gizmodo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (287000, 'CC', 'Kissie De Vaan', '10/9/2000', 'NORMAL', 'kde7y@etsy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (288000, 'CC', 'Claude Huc', '2/26/2005', 'NORMAL', 'chuc7z@geocities.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (289000, 'CC', 'Brigg Ottawell', '12/5/1998', 'NORMAL', 'bottawell80@meetup.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (290000, 'CC', 'Goldie Riatt', '2/5/1998', 'NORMAL', 'griatt81@icq.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (291000, 'CC', 'Earl Ruffey', '2/9/1996', 'NORMAL', 'eruffey82@t.co');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (292000, 'CC', 'Giorgi Jarmain', '1/3/2002', 'NORMAL', 'gjarmain83@multiply.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (293000, 'CC', 'Lindi Pettifor', '12/27/1996', 'NORMAL', 'lpettifor84@dropbox.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (294000, 'CC', 'Pablo Birkett', '10/7/2000', 'NORMAL', 'pbirkett85@cisco.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (295000, 'CC', 'Skylar Halworth', '4/20/1998', 'NORMAL', 'shalworth86@gov.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (296000, 'CC', 'Mischa Huniwall', '12/17/2002', 'NORMAL', 'mhuniwall87@webmd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (297000, 'CC', 'Celinda Naris', '9/11/1997', 'NORMAL', 'cnaris88@jigsy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (298000, 'CC', 'Quillan Freiberg', '1/13/2004', 'NORMAL', 'qfreiberg89@addtoany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (299000, 'CC', 'Mohandis Tow', '8/8/1997', 'NORMAL', 'mtow8a@boston.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (300000, 'CC', 'Stacia Maleham', '2/14/2000', 'NORMAL', 'smaleham8b@nifty.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (301000, 'CC', 'Darcy Lippiett', '9/1/1997', 'NORMAL', 'dlippiett8c@istockphoto.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (302000, 'CC', 'Junette Karmel', '5/28/1998', 'NORMAL', 'jkarmel8d@narod.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (303000, 'CC', 'Mata Oxbie', '9/3/2000', 'NORMAL', 'moxbie8e@vistaprint.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (304000, 'CC', 'Fredi Goldsworthy', '12/17/2004', 'NORMAL', 'fgoldsworthy8f@goo.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (305000, 'CC', 'Danella Lembrick', '1/10/1998', 'NORMAL', 'dlembrick8g@exblog.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (306000, 'CC', 'Kimmie Henrichs', '2/11/2002', 'NORMAL', 'khenrichs8h@tumblr.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (307000, 'CC', 'Natale Szymanowicz', '7/22/1996', 'NORMAL', 'nszymanowicz8i@oracle.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (308000, 'CC', 'Alena Ablitt', '4/26/1999', 'NORMAL', 'aablitt8j@behance.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (309000, 'CC', 'Bourke Piesing', '12/19/1999', 'NORMAL', 'bpiesing8k@java.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (310000, 'CC', 'Blondelle Alekhov', '9/28/1997', 'NORMAL', 'balekhov8l@reference.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (311000, 'CC', 'Vivienne Pepys', '2/26/1997', 'NORMAL', 'vpepys8m@arstechnica.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (312000, 'CC', 'Allianora Burchfield', '5/16/1999', 'NORMAL', 'aburchfield8n@123-reg.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (313000, 'CC', 'Gerhardt Scallan', '9/16/1996', 'NORMAL', 'gscallan8o@ted.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (314000, 'CC', 'Anderea Miroy', '8/9/2001', 'NORMAL', 'amiroy8p@comcast.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (315000, 'CC', 'Julianne Rustich', '9/12/1997', 'NORMAL', 'jrustich8q@time.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (316000, 'CC', 'Austen Massot', '7/16/2003', 'NORMAL', 'amassot8r@bloglovin.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (317000, 'CC', 'Rickie Bayston', '5/10/1997', 'NORMAL', 'rbayston8s@sfgate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (318000, 'CC', 'Alaric Ormrod', '1/10/2004', 'NORMAL', 'aormrod8t@google.nl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (319000, 'CC', 'Frances Deeth', '5/8/2001', 'NORMAL', 'fdeeth8u@tinyurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (320000, 'CC', 'Teddie Allett', '12/14/1996', 'NORMAL', 'tallett8v@imdb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (321000, 'CC', 'Maryrose Bolderoe', '9/13/2000', 'NORMAL', 'mbolderoe8w@tinyurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (322000, 'CC', 'Clive Bellenger', '12/29/2002', 'NORMAL', 'cbellenger8x@mozilla.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (323000, 'CC', 'Bourke Diggell', '6/26/2002', 'NORMAL', 'bdiggell8y@twitpic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (324000, 'CC', 'Dyanne Gauler', '1/31/2002', 'NORMAL', 'dgauler8z@omniture.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (325000, 'CC', 'Nikolai Lever', '12/27/1997', 'NORMAL', 'nlever90@comsenz.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (326000, 'CC', 'Algernon Godar', '9/15/2002', 'NORMAL', 'agodar91@123-reg.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (327000, 'CC', 'Thornton Di Roberto', '3/31/2003', 'NORMAL', 'tdi92@wired.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (328000, 'CC', 'Aylmer Gratten', '8/20/1998', 'NORMAL', 'agratten93@merriam-webster.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (329000, 'CC', 'Dee Ealden', '3/25/2001', 'NORMAL', 'dealden94@jiathis.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (330000, 'CC', 'Lurleen Sedman', '9/19/2001', 'NORMAL', 'lsedman95@hugedomains.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (331000, 'CC', 'Hermy Hugle', '8/22/1997', 'NORMAL', 'hhugle96@kickstarter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (332000, 'CC', 'Elbert Warre', '7/5/2003', 'NORMAL', 'ewarre97@tripadvisor.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (333000, 'CC', 'Kean Haysman', '4/20/1996', 'NORMAL', 'khaysman98@ucoz.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (334000, 'CC', 'Ebony Noor', '1/16/2005', 'NORMAL', 'enoor99@google.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (335000, 'CC', 'Ermentrude Courtese', '9/2/1995', 'NORMAL', 'ecourtese9a@wsj.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (336000, 'CC', 'Elisha Jameson', '2/1/2000', 'NORMAL', 'ejameson9b@mysql.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (337000, 'CC', 'Ralina Hinzer', '2/27/1997', 'NORMAL', 'rhinzer9c@google.com.br');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (338000, 'CC', 'Maryellen Croisier', '1/7/2005', 'NORMAL', 'mcroisier9d@netlog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (339000, 'CC', 'Tripp Cardenoza', '2/2/2002', 'NORMAL', 'tcardenoza9e@scribd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (340000, 'CC', 'Lorena Carleman', '12/23/2000', 'NORMAL', 'lcarleman9f@samsung.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (341000, 'CC', 'Kaitlynn Dight', '4/16/1998', 'NORMAL', 'kdight9g@drupal.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (342000, 'CC', 'Ramsay Blewis', '6/27/2004', 'NORMAL', 'rblewis9h@irs.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (343000, 'CC', 'Brandice Crisall', '1/26/1998', 'NORMAL', 'bcrisall9i@chicagotribune.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (344000, 'CC', 'Franny Lawton', '8/14/2002', 'NORMAL', 'flawton9j@census.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (345000, 'CC', 'Reynolds Phillpot', '10/16/1996', 'NORMAL', 'rphillpot9k@wunderground.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (346000, 'CC', 'Dorine Flemyng', '5/26/2001', 'NORMAL', 'dflemyng9l@livejournal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (347000, 'CC', 'Josy Tuite', '1/5/2004', 'NORMAL', 'jtuite9m@de.vu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (348000, 'CC', 'Boris Heintze', '3/2/1998', 'NORMAL', 'bheintze9n@paginegialle.it');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (349000, 'CC', 'Cob Golland', '12/27/2000', 'NORMAL', 'cgolland9o@blogger.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (350000, 'CC', 'Frankie Hand', '5/13/2000', 'NORMAL', 'fhand9p@npr.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (351000, 'CC', 'Bobbie Probetts', '8/12/1995', 'NORMAL', 'bprobetts9q@ask.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (352000, 'CC', 'Gwenora Stovine', '1/21/2004', 'NORMAL', 'gstovine9r@noaa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (353000, 'CC', 'Fancie Bozworth', '5/17/2000', 'NORMAL', 'fbozworth9s@nba.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (354000, 'CC', 'Saidee Geard', '8/28/2003', 'NORMAL', 'sgeard9t@google.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (355000, 'CC', 'Clotilda Caldwell', '10/3/2003', 'NORMAL', 'ccaldwell9u@studiopress.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (356000, 'CC', 'Chickie Skingle', '4/25/2004', 'NORMAL', 'cskingle9v@umich.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (357000, 'CC', 'Dalt Bednall', '5/21/1998', 'NORMAL', 'dbednall9w@npr.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (358000, 'CC', 'Fiann De L''Isle', '2/20/2003', 'NORMAL', 'fde9x@free.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (359000, 'CC', 'Ainslee Craw', '2/8/2003', 'NORMAL', 'acraw9y@wikispaces.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (360000, 'CC', 'Allina Cansdale', '5/15/2001', 'NORMAL', 'acansdale9z@360.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (361000, 'CC', 'Vance Bridgeland', '12/28/1996', 'NORMAL', 'vbridgelanda0@vkontakte.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (362000, 'CC', 'Emory Trengrouse', '4/5/2003', 'NORMAL', 'etrengrousea1@zdnet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (363000, 'CC', 'Cecile Braham', '1/15/2003', 'NORMAL', 'cbrahama2@digg.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (364000, 'CC', 'Geri Bramley', '1/27/1996', 'NORMAL', 'gbramleya3@tmall.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (365000, 'CC', 'Shirl Francombe', '10/28/1995', 'NORMAL', 'sfrancombea4@last.fm');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (366000, 'CC', 'Pia Mateev', '5/1/2004', 'NORMAL', 'pmateeva5@google.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (367000, 'CC', 'Gerald Harriott', '7/16/2000', 'NORMAL', 'gharriotta6@sphinn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (368000, 'CC', 'Osmund Schuricht', '2/8/2002', 'NORMAL', 'oschurichta7@vkontakte.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (369000, 'CC', 'Delainey Eymor', '8/2/1999', 'NORMAL', 'deymora8@nbcnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (370000, 'CC', 'Ashlen Marjanski', '5/12/2003', 'NORMAL', 'amarjanskia9@tripod.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (371000, 'CC', 'Cyrill Briant', '5/3/1997', 'NORMAL', 'cbriantaa@pcworld.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (372000, 'CC', 'Tobe Nathon', '12/30/1998', 'NORMAL', 'tnathonab@fotki.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (373000, 'CC', 'Mohandis Melluish', '9/12/2000', 'NORMAL', 'mmelluishac@privacy.gov.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (374000, 'CC', 'Collete Andren', '5/20/1996', 'NORMAL', 'candrenad@nyu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (375000, 'CC', 'Ketty Dinkin', '12/12/1996', 'NORMAL', 'kdinkinae@free.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (376000, 'CC', 'Eddy Binnion', '12/15/2000', 'NORMAL', 'ebinnionaf@diigo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (377000, 'CC', 'Maura Cutbirth', '4/2/2005', 'NORMAL', 'mcutbirthag@fastcompany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (378000, 'CC', 'Leone Gentsch', '2/12/2005', 'NORMAL', 'lgentschah@ycombinator.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (379000, 'CC', 'Bambie Parke', '11/15/2001', 'NORMAL', 'bparkeai@weather.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (380000, 'CC', 'Teddy Creffeild', '9/8/1997', 'NORMAL', 'tcreffeildaj@scribd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (381000, 'CC', 'Myrtice Zapatero', '8/2/2001', 'NORMAL', 'mzapateroak@google.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (382000, 'CC', 'Alistair Caplis', '9/20/2003', 'NORMAL', 'acaplisal@51.la');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (383000, 'CC', 'Horatio Antoniades', '3/18/2005', 'NORMAL', 'hantoniadesam@scribd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (384000, 'CC', 'Verge Mereweather', '8/15/2004', 'NORMAL', 'vmereweatheran@cpanel.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (385000, 'CC', 'Ivory Pindell', '4/15/1997', 'NORMAL', 'ipindellao@shareasale.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (386000, 'CC', 'Cyrus Stredwick', '6/28/2000', 'NORMAL', 'cstredwickap@ameblo.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (387000, 'CC', 'Aldwin Alchin', '7/19/1999', 'NORMAL', 'aalchinaq@webnode.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (388000, 'CC', 'Kippie Birmingham', '7/16/2003', 'NORMAL', 'kbirminghamar@google.es');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (389000, 'CC', 'Mozes Fisby', '9/15/1995', 'NORMAL', 'mfisbyas@com.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (390000, 'CC', 'Aileen Goggin', '1/5/1998', 'NORMAL', 'agogginat@nih.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (391000, 'CC', 'Haily Poon', '4/13/2001', 'NORMAL', 'hpoonau@imageshack.us');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (392000, 'CC', 'Gino Haselup', '8/31/1998', 'NORMAL', 'ghaselupav@ftc.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (393000, 'CC', 'Tracey Petrasso', '6/4/2000', 'NORMAL', 'tpetrassoaw@prweb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (394000, 'CC', 'Tarra Rodgerson', '4/28/2004', 'NORMAL', 'trodgersonax@dot.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (395000, 'CC', 'Elena Jeannequin', '3/21/2001', 'NORMAL', 'ejeannequinay@jimdo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (396000, 'CC', 'Donnie Brogioni', '8/14/1998', 'NORMAL', 'dbrogioniaz@list-manage.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (397000, 'CC', 'Tabby Bendley', '4/30/2004', 'NORMAL', 'tbendleyb0@jimdo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (398000, 'CC', 'Arleyne Suddards', '6/17/2000', 'NORMAL', 'asuddardsb1@rediff.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (399000, 'CC', 'Heriberto Durrans', '8/24/2004', 'NORMAL', 'hdurransb2@ft.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (400000, 'CC', 'Evangeline Pass', '1/26/2005', 'NORMAL', 'epassb3@goodreads.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (401000, 'CC', 'Clayson Mussilli', '4/22/2005', 'NORMAL', 'cmussillib4@arizona.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (402000, 'CC', 'Kata Dabel', '9/19/1995', 'NORMAL', 'kdabelb5@ox.ac.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (403000, 'CC', 'Tova Penvarden', '10/22/2000', 'NORMAL', 'tpenvardenb6@ucoz.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (404000, 'CC', 'Pammie Lesser', '7/14/1995', 'NORMAL', 'plesserb7@yale.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (405000, 'CC', 'Ferguson Haycock', '12/15/1998', 'NORMAL', 'fhaycockb8@g.co');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (406000, 'CC', 'Suki Diplock', '1/30/2003', 'NORMAL', 'sdiplockb9@icio.us');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (407000, 'CC', 'Barbabra Bugler', '4/15/2004', 'NORMAL', 'bbuglerba@bbc.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (408000, 'CC', 'Gael Bolter', '2/6/1999', 'NORMAL', 'gbolterbb@bigcartel.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (409000, 'CC', 'Curcio Sydes', '5/4/1998', 'NORMAL', 'csydesbc@china.com.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (410000, 'CC', 'Tito Tirrey', '11/7/1996', 'NORMAL', 'ttirreybd@imdb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (411000, 'CC', 'Gelya Gathwaite', '1/6/2002', 'NORMAL', 'ggathwaitebe@dion.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (412000, 'CC', 'Rickard von Grollmann', '8/31/1999', 'NORMAL', 'rvonbf@gravatar.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (413000, 'CC', 'Anastasia Gravener', '5/16/1998', 'NORMAL', 'agravenerbg@rambler.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (414000, 'CC', 'Alvy Gimeno', '4/12/2002', 'NORMAL', 'agimenobh@bbc.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (415000, 'CC', 'Michale Turri', '11/19/1995', 'NORMAL', 'mturribi@forbes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (416000, 'CC', 'Conney Rawle', '6/20/1996', 'NORMAL', 'crawlebj@home.pl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (417000, 'CC', 'Bernarr Shoutt', '10/14/2000', 'NORMAL', 'bshouttbk@nydailynews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (418000, 'CC', 'Aeriela Beniesh', '11/11/1999', 'NORMAL', 'abenieshbl@walmart.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (419000, 'CC', 'Merle Henrych', '8/30/2003', 'NORMAL', 'mhenrychbm@vk.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (420000, 'CC', 'Claus Alam', '5/21/1997', 'NORMAL', 'calambn@wsj.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (421000, 'CC', 'Pepillo Merit', '10/23/2004', 'NORMAL', 'pmeritbo@nytimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (422000, 'CC', 'Nathanil Infante', '11/18/2002', 'NORMAL', 'ninfantebp@infoseek.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (423000, 'CC', 'Prent Venour', '2/17/2000', 'NORMAL', 'pvenourbq@amazon.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (424000, 'CC', 'Gilli Ledgley', '11/18/2002', 'NORMAL', 'gledgleybr@google.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (425000, 'CC', 'Quincey McLugaish', '6/15/1996', 'NORMAL', 'qmclugaishbs@wikia.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (426000, 'CC', 'Bambi Rivilis', '2/4/2003', 'NORMAL', 'brivilisbt@phpbb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (427000, 'CC', 'Lishe Traylen', '11/10/2002', 'NORMAL', 'ltraylenbu@samsung.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (428000, 'CC', 'Hatti Thunnerclef', '8/26/1998', 'NORMAL', 'hthunnerclefbv@wisc.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (429000, 'CC', 'Kermit Reddell', '2/14/2005', 'NORMAL', 'kreddellbw@blogs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (430000, 'CC', 'Malissa Spurrior', '8/11/2000', 'NORMAL', 'mspurriorbx@bing.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (431000, 'CC', 'Hercule Oliphard', '5/3/1996', 'NORMAL', 'holiphardby@symantec.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (432000, 'CC', 'Barthel Robben', '5/1/1996', 'NORMAL', 'brobbenbz@unblog.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (433000, 'CC', 'Murdoch Rudolph', '7/15/1997', 'NORMAL', 'mrudolphc0@sourceforge.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (434000, 'CC', 'Charmane Epdell', '9/24/2001', 'NORMAL', 'cepdellc1@wired.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (435000, 'CC', 'Abbey Lagde', '12/11/1999', 'NORMAL', 'alagdec2@microsoft.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (436000, 'CC', 'Dorene Arnolds', '12/20/2001', 'NORMAL', 'darnoldsc3@google.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (437000, 'CC', 'Shae Griffiths', '11/4/2000', 'NORMAL', 'sgriffithsc4@webs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (438000, 'CC', 'Jamil Schmidt', '6/3/1999', 'NORMAL', 'jschmidtc5@hugedomains.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (439000, 'CC', 'Maryanna Swatland', '4/10/2005', 'NORMAL', 'mswatlandc6@blogs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (440000, 'CC', 'Bathsheba Southcomb', '12/13/2003', 'NORMAL', 'bsouthcombc7@mediafire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (441000, 'CC', 'Sharline Skym', '3/2/2000', 'NORMAL', 'sskymc8@domainmarket.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (442000, 'CC', 'Sutherlan Ebertz', '2/15/2004', 'NORMAL', 'sebertzc9@usnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (443000, 'CC', 'Ricard Androletti', '9/10/2002', 'NORMAL', 'randrolettica@shop-pro.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (444000, 'CC', 'Callean Logue', '10/8/1997', 'NORMAL', 'cloguecb@yahoo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (445000, 'CC', 'Ameline Spittal', '9/9/2002', 'NORMAL', 'aspittalcc@cbslocal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (446000, 'CC', 'Gayle Rubertis', '10/4/2004', 'NORMAL', 'grubertiscd@usda.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (447000, 'CC', 'Bridget Sudy', '4/13/2001', 'NORMAL', 'bsudyce@youtu.be');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (448000, 'CC', 'Thornton Boyford', '5/27/1996', 'NORMAL', 'tboyfordcf@google.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (449000, 'CC', 'Edi Chaffen', '2/7/2000', 'NORMAL', 'echaffencg@nationalgeographic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (450000, 'CC', 'Kippar Amor', '12/6/2002', 'NORMAL', 'kamorch@bloglovin.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (451000, 'CC', 'Micheline Hoopper', '5/7/1996', 'NORMAL', 'mhoopperci@51.la');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (452000, 'CC', 'Deanne Mix', '2/8/2000', 'NORMAL', 'dmixcj@admin.ch');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (453000, 'CC', 'Dianne Newitt', '10/14/2000', 'NORMAL', 'dnewittck@wikispaces.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (454000, 'CC', 'Benedicta Chown', '5/14/2002', 'NORMAL', 'bchowncl@soundcloud.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (455000, 'CC', 'Bax Kerrigan', '4/11/1996', 'NORMAL', 'bkerrigancm@youtu.be');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (456000, 'CC', 'Georgie Glencorse', '2/13/1996', 'NORMAL', 'gglencorsecn@hp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (457000, 'CC', 'Tony Gaggen', '2/26/1999', 'NORMAL', 'tgaggenco@miitbeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (458000, 'CC', 'Bobbie McCalister', '9/11/1996', 'NORMAL', 'bmccalistercp@so-net.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (459000, 'CC', 'Willa de Villier', '3/11/2002', 'NORMAL', 'wdecq@opera.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (460000, 'CC', 'Lenora Portinari', '2/25/2005', 'NORMAL', 'lportinaricr@paypal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (461000, 'CC', 'Sisile Ashmore', '12/25/1995', 'NORMAL', 'sashmorecs@slate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (462000, 'CC', 'Randie Murthwaite', '3/21/1998', 'NORMAL', 'rmurthwaitect@nature.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (463000, 'CC', 'Jade Durgan', '4/21/1996', 'NORMAL', 'jdurgancu@slideshare.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (464000, 'CC', 'Fairleigh Heinschke', '6/12/2003', 'NORMAL', 'fheinschkecv@theglobeandmail.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (465000, 'CC', 'Christi Kampshell', '10/15/2004', 'NORMAL', 'ckampshellcw@cdc.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (466000, 'CC', 'Barbara-anne Marr', '12/31/1997', 'NORMAL', 'bmarrcx@rediff.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (467000, 'CC', 'Amandi Bratt', '9/25/1997', 'NORMAL', 'abrattcy@stanford.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (468000, 'CC', 'Chiquita Dungate', '6/14/1997', 'NORMAL', 'cdungatecz@shinystat.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (469000, 'CC', 'Hersh Ginglell', '4/24/2004', 'NORMAL', 'hginglelld0@devhub.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (470000, 'CC', 'Umberto Van der Son', '12/19/2002', 'NORMAL', 'uvand1@fastcompany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (471000, 'CC', 'Grissel Koop', '8/6/1995', 'NORMAL', 'gkoopd2@cocolog-nifty.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (472000, 'CC', 'Feodor Poluzzi', '11/12/2000', 'NORMAL', 'fpoluzzid3@gnu.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (473000, 'CC', 'Gwyneth Desouza', '12/6/1996', 'NORMAL', 'gdesouzad4@nytimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (474000, 'CC', 'Allison Westhoff', '7/19/2002', 'NORMAL', 'awesthoffd5@abc.net.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (475000, 'CC', 'Sharyl Bischop', '6/17/2003', 'NORMAL', 'sbischopd6@engadget.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (476000, 'CC', 'Amandie Budibent', '3/9/1998', 'NORMAL', 'abudibentd7@myspace.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (477000, 'CC', 'Cora Priditt', '6/9/2003', 'NORMAL', 'cpridittd8@devhub.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (478000, 'CC', 'Julieta Malins', '2/27/1996', 'NORMAL', 'jmalinsd9@instagram.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (479000, 'CC', 'Brigham Render', '3/24/2003', 'NORMAL', 'brenderda@cocolog-nifty.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (480000, 'CC', 'Alisha Fetteplace', '5/30/1995', 'NORMAL', 'afetteplacedb@berkeley.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (481000, 'CC', 'Zulema Sussams', '7/4/1998', 'NORMAL', 'zsussamsdc@webmd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (482000, 'CC', 'Dorelia Anselm', '12/14/2003', 'NORMAL', 'danselmdd@sbwire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (483000, 'CC', 'Aline Nerne', '8/22/2004', 'NORMAL', 'anernede@nytimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (484000, 'CC', 'Dante Barus', '3/3/2001', 'NORMAL', 'dbarusdf@livejournal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (485000, 'CC', 'Lonni English', '7/19/1998', 'NORMAL', 'lenglishdg@toplist.cz');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (486000, 'CC', 'Fidelio O''Corr', '2/9/2002', 'NORMAL', 'focorrdh@oakley.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (487000, 'CC', 'Heath Quilter', '5/6/2002', 'NORMAL', 'hquilterdi@cbslocal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (488000, 'CC', 'Blancha Masham', '8/16/1997', 'NORMAL', 'bmashamdj@cisco.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (489000, 'CC', 'Brenda Skells', '8/13/2000', 'NORMAL', 'bskellsdk@printfriendly.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (490000, 'CC', 'Ram Killock', '4/3/1998', 'NORMAL', 'rkillockdl@java.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (491000, 'CC', 'Edvard Tummons', '8/8/1998', 'NORMAL', 'etummonsdm@delicious.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (492000, 'CC', 'Lanny Fancourt', '4/3/1996', 'NORMAL', 'lfancourtdn@fc2.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (493000, 'CC', 'Ermengarde Florence', '12/27/1995', 'NORMAL', 'eflorencedo@bbb.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (494000, 'CC', 'Delmer Gres', '4/20/2005', 'NORMAL', 'dgresdp@virginia.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (495000, 'CC', 'Feliza M''Barron', '5/14/2003', 'NORMAL', 'fmbarrondq@opera.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (496000, 'CC', 'Stephani Kenworthy', '8/2/2000', 'NORMAL', 'skenworthydr@naver.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (497000, 'CC', 'Violet Renny', '6/8/1995', 'NORMAL', 'vrennyds@fotki.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (498000, 'CC', 'Jillian Ambrosini', '6/27/1997', 'NORMAL', 'jambrosinidt@goo.gl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (499000, 'CC', 'Joycelin Stallibrass', '9/7/2000', 'NORMAL', 'jstallibrassdu@hp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (500000, 'CC', 'Antonina Licciardello', '12/5/2003', 'NORMAL', 'alicciardellodv@mozilla.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (501000, 'CC', 'Beverlie Pragnall', '2/10/1998', 'NORMAL', 'bpragnalldw@baidu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (502000, 'CC', 'Bing Pettingall', '12/1/2003', 'NORMAL', 'bpettingalldx@i2i.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (503000, 'CC', 'Marie Dreakin', '5/1/1998', 'NORMAL', 'mdreakindy@nih.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (504000, 'CC', 'Thoma Reichhardt', '12/23/1996', 'NORMAL', 'treichhardtdz@dmoz.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (505000, 'CC', 'Jasper Thunder', '9/2/1995', 'NORMAL', 'jthundere0@cocolog-nifty.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (506000, 'CC', 'Amaleta Bendel', '11/2/1996', 'NORMAL', 'abendele1@telegraph.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (507000, 'CC', 'Alane Himsworth', '7/22/2001', 'NORMAL', 'ahimsworthe2@omniture.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (508000, 'CC', 'Obediah Sarver', '11/14/2002', 'NORMAL', 'osarvere3@google.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (509000, 'CC', 'Felicio Bertelet', '12/25/2000', 'NORMAL', 'fbertelete4@unc.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (510000, 'CC', 'Pooh Hendrichs', '10/1/1996', 'NORMAL', 'phendrichse5@friendfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (511000, 'CC', 'Janey Mee', '2/19/2002', 'NORMAL', 'jmeee6@who.int');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (512000, 'CC', 'Ardeen Robjant', '2/13/1998', 'NORMAL', 'arobjante7@trellian.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (513000, 'CC', 'Forbes Huitt', '4/6/1996', 'NORMAL', 'fhuitte8@bloglines.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (514000, 'CC', 'Agata Tompkiss', '8/7/1995', 'NORMAL', 'atompkisse9@elegantthemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (515000, 'CC', 'Frazer Dotson', '9/1/2003', 'NORMAL', 'fdotsonea@marketwatch.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (516000, 'CC', 'Charmian Bramich', '3/30/1996', 'NORMAL', 'cbramicheb@netscape.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (517000, 'CC', 'Carolyne Aimeric', '6/3/2003', 'NORMAL', 'caimericec@wikispaces.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (518000, 'CC', 'Audre Colafate', '1/2/2005', 'NORMAL', 'acolafateed@yelp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (519000, 'CC', 'Morse Boatwright', '7/1/1998', 'NORMAL', 'mboatwrightee@alexa.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (520000, 'CC', 'Catlaina Laville', '3/15/1999', 'NORMAL', 'clavilleef@godaddy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (521000, 'CC', 'Corine Pester', '9/28/1996', 'NORMAL', 'cpestereg@themeforest.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (522000, 'CC', 'Rodina Ashmole', '4/12/1996', 'NORMAL', 'rashmoleeh@purevolume.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (523000, 'CC', 'Orly Pennicott', '8/15/2004', 'NORMAL', 'opennicottei@msn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (524000, 'CC', 'Dorrie Bythway', '10/18/2004', 'NORMAL', 'dbythwayej@a8.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (525000, 'CC', 'Ilene Tallet', '9/15/2002', 'NORMAL', 'italletek@mayoclinic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (526000, 'CC', 'Madelaine Penton', '8/3/2003', 'NORMAL', 'mpentonel@jugem.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (527000, 'CC', 'Killian Whales', '11/18/1996', 'NORMAL', 'kwhalesem@ehow.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (528000, 'CC', 'Shelby Pfaff', '2/3/2001', 'NORMAL', 'spfaffen@blogspot.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (529000, 'CC', 'Cary Rigeby', '8/21/1999', 'NORMAL', 'crigebyeo@si.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (530000, 'CC', 'Bronnie Renehan', '12/25/2002', 'NORMAL', 'brenehanep@feedburner.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (531000, 'CC', 'Tallie Weldrake', '4/3/2005', 'NORMAL', 'tweldrakeeq@live.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (532000, 'CC', 'Lewes Lambarth', '11/23/2001', 'NORMAL', 'llambarther@surveymonkey.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (533000, 'CC', 'Raquel Burnsall', '1/13/1996', 'NORMAL', 'rburnsalles@gov.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (534000, 'CC', 'Odilia Walling', '3/24/1997', 'NORMAL', 'owallinget@illinois.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (535000, 'CC', 'Beatrice Hares', '11/19/1999', 'NORMAL', 'bhareseu@google.com.hk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (536000, 'CC', 'Cull Calliss', '7/5/1997', 'NORMAL', 'ccallissev@nps.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (537000, 'CC', 'Mildred Nilges', '12/31/2000', 'NORMAL', 'mnilgesew@wikispaces.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (538000, 'CC', 'Gardie Akerman', '7/12/2001', 'NORMAL', 'gakermanex@bigcartel.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (539000, 'CC', 'Elbertina Stiebler', '7/15/2001', 'NORMAL', 'estieblerey@yandex.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (540000, 'CC', 'Marven McKelvey', '10/30/2001', 'NORMAL', 'mmckelveyez@about.me');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (541000, 'CC', 'Benny Trimby', '1/14/2001', 'NORMAL', 'btrimbyf0@histats.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (542000, 'CC', 'Sheelah Suller', '6/27/1996', 'NORMAL', 'ssullerf1@livejournal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (543000, 'CC', 'Stearne Marioneau', '10/20/2004', 'NORMAL', 'smarioneauf2@dion.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (544000, 'CC', 'Herschel Hanvey', '8/14/2002', 'NORMAL', 'hhanveyf3@usatoday.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (545000, 'CC', 'Candra Brugsma', '12/7/1996', 'NORMAL', 'cbrugsmaf4@miibeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (546000, 'CC', 'Aimil Lindsay', '4/15/1999', 'NORMAL', 'alindsayf5@about.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (547000, 'CC', 'Tammy Buncombe', '6/14/1997', 'NORMAL', 'tbuncombef6@tinyurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (548000, 'CC', 'Delilah Blaby', '5/19/1998', 'NORMAL', 'dblabyf7@scientificamerican.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (549000, 'CC', 'Charisse Bowe', '9/30/2001', 'NORMAL', 'cbowef8@angelfire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (550000, 'CC', 'Isaac Rosenqvist', '8/31/2004', 'NORMAL', 'irosenqvistf9@istockphoto.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (551000, 'CC', 'Torrence MacCallister', '4/9/2005', 'NORMAL', 'tmaccallisterfa@webs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (552000, 'CC', 'Maighdiln Goodby', '3/22/1998', 'NORMAL', 'mgoodbyfb@theatlantic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (553000, 'CC', 'Ronna Thornthwaite', '4/2/2001', 'NORMAL', 'rthornthwaitefc@dagondesign.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (554000, 'CC', 'Andree Wemyss', '12/13/1998', 'NORMAL', 'awemyssfd@angelfire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (555000, 'CC', 'Richmond Reuther', '8/13/1999', 'NORMAL', 'rreutherfe@loc.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (556000, 'CC', 'Susanetta Smaile', '3/17/2005', 'NORMAL', 'ssmaileff@yelp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (557000, 'CC', 'Giorgio Shovelton', '12/28/1995', 'NORMAL', 'gshoveltonfg@nyu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (558000, 'CC', 'Bartholomew Conlaund', '8/25/2004', 'NORMAL', 'bconlaundfh@engadget.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (559000, 'CC', 'Melissa Jakubiak', '4/21/2000', 'NORMAL', 'mjakubiakfi@yellowbook.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (560000, 'CC', 'Sharla McMeyler', '3/22/2004', 'NORMAL', 'smcmeylerfj@mayoclinic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (561000, 'CC', 'Isahella Mee', '1/1/1997', 'NORMAL', 'imeefk@blog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (562000, 'CC', 'Rennie Klausewitz', '4/10/1997', 'NORMAL', 'rklausewitzfl@telegraph.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (563000, 'CC', 'D''arcy Merrall', '1/9/2005', 'NORMAL', 'dmerrallfm@purevolume.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (564000, 'CC', 'Scotty Mote', '12/1/1999', 'NORMAL', 'smotefn@networkadvertising.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (565000, 'CC', 'Starlene Sheldon', '6/9/1997', 'NORMAL', 'ssheldonfo@pcworld.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (566000, 'CC', 'Efrem Grieswood', '12/21/1995', 'NORMAL', 'egrieswoodfp@xinhuanet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (567000, 'CC', 'Alexandrina Dawbury', '11/13/2000', 'NORMAL', 'adawburyfq@wufoo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (568000, 'CC', 'Bradan Lettsom', '2/9/1996', 'NORMAL', 'blettsomfr@cbc.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (569000, 'CC', 'Olympia Werrit', '12/7/2001', 'NORMAL', 'owerritfs@infoseek.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (570000, 'CC', 'Sapphira Pfleger', '4/25/1998', 'NORMAL', 'spflegerft@quantcast.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (571000, 'CC', 'Rhody Agus', '9/13/2002', 'NORMAL', 'ragusfu@irs.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (572000, 'CC', 'Lammond Tharme', '6/28/1995', 'NORMAL', 'ltharmefv@moonfruit.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (573000, 'CC', 'Napoleon Ruffey', '4/30/2001', 'NORMAL', 'nruffeyfw@psu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (574000, 'CC', 'Abdel Covil', '11/12/2002', 'NORMAL', 'acovilfx@ning.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (575000, 'CC', 'Albina Ferronel', '11/9/2004', 'NORMAL', 'aferronelfy@theatlantic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (576000, 'CC', 'Cacilia Adshed', '4/30/1999', 'NORMAL', 'cadshedfz@desdev.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (577000, 'CC', 'Romona Ortelt', '6/6/2001', 'NORMAL', 'rorteltg0@cbslocal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (578000, 'CC', 'Arthur Scholes', '1/11/2005', 'NORMAL', 'ascholesg1@webnode.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (579000, 'CC', 'Pietra Chinery', '1/10/2000', 'NORMAL', 'pchineryg2@jiathis.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (580000, 'CC', 'Joshia Cassy', '12/7/1998', 'NORMAL', 'jcassyg3@state.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (581000, 'CC', 'Dode Panswick', '11/10/2004', 'NORMAL', 'dpanswickg4@usatoday.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (582000, 'CC', 'Sergeant Kelinge', '10/12/2000', 'NORMAL', 'skelingeg5@github.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (583000, 'CC', 'Darsie Lornsen', '5/22/2004', 'NORMAL', 'dlornseng6@dagondesign.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (584000, 'CC', 'Bartlet Barthrop', '8/31/2002', 'NORMAL', 'bbarthropg7@barnesandnoble.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (585000, 'CC', 'Ursala Maunder', '2/25/1996', 'NORMAL', 'umaunderg8@blogs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (586000, 'CC', 'Phillipp Mcmanaman', '4/2/2001', 'NORMAL', 'pmcmanamang9@mac.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (587000, 'CC', 'Jamesy Enstone', '1/20/1998', 'NORMAL', 'jenstonega@histats.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (588000, 'CC', 'Borg Duckerin', '7/20/2001', 'NORMAL', 'bduckeringb@techcrunch.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (589000, 'CC', 'Myrlene Leel', '6/18/1996', 'NORMAL', 'mleelgc@github.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (590000, 'CC', 'Eleanor Cheers', '1/28/2000', 'NORMAL', 'echeersgd@pagesperso-orange.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (591000, 'CC', 'Fania MacCollom', '4/1/1996', 'NORMAL', 'fmaccollomge@multiply.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (592000, 'CC', 'Karlan MacPhaden', '10/3/2004', 'NORMAL', 'kmacphadengf@cnet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (593000, 'CC', 'Shayna Fryer', '6/30/1995', 'NORMAL', 'sfryergg@altervista.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (594000, 'CC', 'Marmaduke Nilges', '10/22/1996', 'NORMAL', 'mnilgesgh@cloudflare.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (595000, 'CC', 'Pippa Belvard', '1/22/1998', 'NORMAL', 'pbelvardgi@chronoengine.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (596000, 'CC', 'Gavan Henworth', '3/16/2002', 'NORMAL', 'ghenworthgj@wunderground.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (597000, 'CC', 'Roxie Abazi', '7/30/2002', 'NORMAL', 'rabazigk@usa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (598000, 'CC', 'Leta Bexley', '5/1/1999', 'NORMAL', 'lbexleygl@phoca.cz');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (599000, 'CC', 'Luisa Hold', '12/19/2002', 'NORMAL', 'lholdgm@intel.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (600000, 'CC', 'Rora Jeanenet', '5/24/1995', 'NORMAL', 'rjeanenetgn@google.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (601000, 'CC', 'Pearle Treasure', '5/21/2000', 'NORMAL', 'ptreasurego@google.pl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (602000, 'CC', 'Daniel Minichi', '8/23/2003', 'NORMAL', 'dminichigp@archive.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (603000, 'CC', 'Roxane Leffek', '10/28/2000', 'NORMAL', 'rleffekgq@engadget.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (604000, 'CC', 'Enoch Toon', '6/12/2000', 'NORMAL', 'etoongr@sciencedaily.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (605000, 'CC', 'Melisent Marusik', '1/27/2005', 'NORMAL', 'mmarusikgs@sohu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (606000, 'CC', 'Desdemona Choat', '5/6/1996', 'NORMAL', 'dchoatgt@scientificamerican.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (607000, 'CC', 'Johnathan Lamanby', '8/23/1997', 'NORMAL', 'jlamanbygu@xinhuanet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (608000, 'CC', 'Ancell Cattenach', '7/27/1999', 'NORMAL', 'acattenachgv@elegantthemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (609000, 'CC', 'Lucinda Frany', '12/23/1998', 'NORMAL', 'lfranygw@hud.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (610000, 'CC', 'Estella Greydon', '7/21/2003', 'NORMAL', 'egreydongx@phoca.cz');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (611000, 'CC', 'Wilhelmina Iczokvitz', '10/1/1997', 'NORMAL', 'wiczokvitzgy@theguardian.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (612000, 'CC', 'Nelli Christophersen', '3/2/1999', 'NORMAL', 'nchristophersengz@aol.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (613000, 'CC', 'Oliviero Naisbit', '6/2/2004', 'NORMAL', 'onaisbith0@cargocollective.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (614000, 'CC', 'Margalo Gateshill', '8/17/1996', 'NORMAL', 'mgateshillh1@youtu.be');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (615000, 'CC', 'Arty Joules', '6/18/2001', 'NORMAL', 'ajoulesh2@comcast.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (616000, 'CC', 'Cornelle Moretto', '5/19/1997', 'NORMAL', 'cmorettoh3@nationalgeographic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (617000, 'CC', 'Matias Molyneux', '3/8/2001', 'NORMAL', 'mmolyneuxh4@weebly.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (618000, 'CC', 'Kathe McElhinney', '11/26/1998', 'NORMAL', 'kmcelhinneyh5@usnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (619000, 'CC', 'Elizabeth Menaul', '1/30/2002', 'NORMAL', 'emenaulh6@squarespace.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (620000, 'CC', 'Jacob Hawson', '4/19/2000', 'NORMAL', 'jhawsonh7@zdnet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (621000, 'CC', 'Carola East', '9/1/1997', 'NORMAL', 'ceasth8@dailymotion.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (622000, 'CC', 'Gwynne Kitcat', '10/6/2001', 'NORMAL', 'gkitcath9@springer.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (623000, 'CC', 'Darby Gawthrope', '6/29/2000', 'NORMAL', 'dgawthropeha@princeton.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (624000, 'CC', 'Hastie Attwool', '7/6/1999', 'NORMAL', 'hattwoolhb@github.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (625000, 'CC', 'Nero Mewha', '6/13/2003', 'NORMAL', 'nmewhahc@umn.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (626000, 'CC', 'Alan Mathie', '6/18/1999', 'NORMAL', 'amathiehd@un.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (627000, 'CC', 'Guy Langthorne', '12/4/1999', 'NORMAL', 'glangthornehe@shutterfly.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (628000, 'CC', 'Cindra Fray', '9/30/1997', 'NORMAL', 'cfrayhf@linkedin.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (629000, 'CC', 'Kevyn Porritt', '10/24/2003', 'NORMAL', 'kporritthg@webmd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (630000, 'CC', 'Regan Trass', '11/10/1997', 'NORMAL', 'rtrasshh@bizjournals.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (631000, 'CC', 'Benoit Heninghem', '12/5/1995', 'NORMAL', 'bheninghemhi@goo.gl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (632000, 'CC', 'Kerrill Seedman', '5/9/1998', 'NORMAL', 'kseedmanhj@vimeo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (633000, 'CC', 'Lori Copestake', '9/3/1995', 'NORMAL', 'lcopestakehk@mtv.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (634000, 'CC', 'Bettine Malloy', '6/24/2000', 'NORMAL', 'bmalloyhl@blogger.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (635000, 'CC', 'Wally Figgen', '11/27/2002', 'NORMAL', 'wfiggenhm@csmonitor.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (636000, 'CC', 'Thelma Fettiplace', '7/6/2002', 'NORMAL', 'tfettiplacehn@ifeng.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (637000, 'CC', 'Mel McGrayle', '8/15/2004', 'NORMAL', 'mmcgrayleho@sourceforge.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (638000, 'CC', 'Lannie Michele', '12/29/1996', 'NORMAL', 'lmichelehp@vkontakte.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (639000, 'CC', 'Tatiania Castagnone', '3/15/2004', 'NORMAL', 'tcastagnonehq@cmu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (640000, 'CC', 'Rickie Scrinage', '6/20/1999', 'NORMAL', 'rscrinagehr@springer.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (641000, 'CC', 'Rosina D''Oyley', '1/18/1999', 'NORMAL', 'rdoyleyhs@geocities.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (642000, 'CC', 'Fabiano Clouston', '6/27/1999', 'NORMAL', 'fcloustonht@latimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (643000, 'CC', 'Rolph Glasscott', '1/17/2003', 'NORMAL', 'rglasscotthu@issuu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (644000, 'CC', 'Bren Scrivinor', '10/20/2004', 'NORMAL', 'bscrivinorhv@wikimedia.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (645000, 'CC', 'Coreen Moynihan', '4/2/1999', 'NORMAL', 'cmoynihanhw@sun.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (646000, 'CC', 'Waylan Hainsworth', '11/7/2004', 'NORMAL', 'whainsworthhx@pcworld.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (647000, 'CC', 'Donetta Dobie', '11/10/2001', 'NORMAL', 'ddobiehy@ihg.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (648000, 'CC', 'Frasquito Licence', '7/3/2000', 'NORMAL', 'flicencehz@comsenz.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (649000, 'CC', 'Piggy FitzGibbon', '5/2/1996', 'NORMAL', 'pfitzgibboni0@google.es');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (650000, 'CC', 'Zollie Islep', '10/15/1996', 'NORMAL', 'zislepi1@wordpress.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (651000, 'CC', 'Thelma Hibling', '4/16/1999', 'NORMAL', 'thiblingi2@live.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (652000, 'CC', 'Niko Pigott', '11/4/1998', 'NORMAL', 'npigotti3@whitehouse.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (653000, 'CC', 'Virgilio Broun', '6/28/1998', 'NORMAL', 'vbrouni4@google.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (654000, 'CC', 'Agneta Webben', '5/2/1999', 'NORMAL', 'awebbeni5@wix.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (655000, 'CC', 'Rochester Elsie', '8/5/1997', 'NORMAL', 'relsiei6@fema.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (656000, 'CC', 'Penn Elia', '1/11/2005', 'NORMAL', 'peliai7@360.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (657000, 'CC', 'Bobby Grim', '11/1/2004', 'NORMAL', 'bgrimi8@desdev.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (658000, 'CC', 'Gaylord Voas', '7/17/2002', 'NORMAL', 'gvoasi9@examiner.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (659000, 'CC', 'Idalia Dolman', '9/10/2003', 'NORMAL', 'idolmania@huffingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (660000, 'CC', 'Sampson Thamelt', '3/12/2001', 'NORMAL', 'sthameltib@smugmug.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (661000, 'CC', 'Tilly Spackman', '5/7/1997', 'NORMAL', 'tspackmanic@ebay.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (662000, 'CC', 'Hertha Vassman', '2/14/2001', 'NORMAL', 'hvassmanid@nymag.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (663000, 'CC', 'Rickie Sprigin', '8/4/2001', 'NORMAL', 'rspriginie@upenn.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (664000, 'CC', 'Joye Kain', '1/12/2005', 'NORMAL', 'jkainif@nationalgeographic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (665000, 'CC', 'Niels Struther', '7/11/1999', 'NORMAL', 'nstrutherig@sphinn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (666000, 'CC', 'Timmy Terrington', '10/25/2002', 'NORMAL', 'tterringtonih@fc2.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (667000, 'CC', 'Gunar Schermick', '4/5/2000', 'NORMAL', 'gschermickii@hp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (668000, 'CC', 'Weidar Dilks', '5/17/2003', 'NORMAL', 'wdilksij@posterous.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (669000, 'CC', 'Cathe Dalgety', '4/3/1998', 'NORMAL', 'cdalgetyik@psu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (670000, 'CC', 'Christel Sleford', '9/16/2001', 'NORMAL', 'cslefordil@netlog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (671000, 'CC', 'Quentin Bunt', '9/16/2001', 'NORMAL', 'qbuntim@1688.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (672000, 'CC', 'Salvatore Stabbins', '4/4/1997', 'NORMAL', 'sstabbinsin@behance.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (673000, 'CC', 'Calla O''Shevlin', '9/29/1997', 'NORMAL', 'coshevlinio@google.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (674000, 'CC', 'Kathryn MacAvddy', '6/14/2003', 'NORMAL', 'kmacavddyip@mapquest.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (675000, 'CC', 'Staffard Stanlake', '5/12/2000', 'NORMAL', 'sstanlakeiq@etsy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (676000, 'CC', 'Neils Pond', '5/4/2004', 'NORMAL', 'npondir@facebook.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (677000, 'CC', 'Garvin Midson', '6/30/1996', 'NORMAL', 'gmidsonis@sphinn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (678000, 'CC', 'Zaria Dougharty', '4/19/1997', 'NORMAL', 'zdoughartyit@netvibes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (679000, 'CC', 'Frederico Byfield', '7/11/1995', 'NORMAL', 'fbyfieldiu@who.int');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (680000, 'CC', 'Huntlee Lucius', '5/2/1999', 'NORMAL', 'hluciusiv@gravatar.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (681000, 'CC', 'Colver Shinefield', '1/11/2002', 'NORMAL', 'cshinefieldiw@craigslist.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (682000, 'CC', 'Rip Sumpter', '3/13/1996', 'NORMAL', 'rsumpterix@t-online.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (683000, 'CC', 'Conrade Ugoletti', '10/20/1997', 'NORMAL', 'cugolettiiy@elegantthemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (684000, 'CC', 'Timoteo Sturton', '7/23/2003', 'NORMAL', 'tsturtoniz@hao123.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (685000, 'CC', 'Lester Neilan', '5/6/1998', 'NORMAL', 'lneilanj0@storify.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (686000, 'CC', 'Dora Sturdy', '7/3/2004', 'NORMAL', 'dsturdyj1@abc.net.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (687000, 'CC', 'Anna-diana Ingry', '6/26/1997', 'NORMAL', 'aingryj2@sun.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (688000, 'CC', 'Ania Madge', '7/7/1996', 'NORMAL', 'amadgej3@addtoany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (689000, 'CC', 'Sig Stych', '4/13/1998', 'NORMAL', 'sstychj4@eepurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (690000, 'CC', 'Willem Busch', '4/20/2003', 'NORMAL', 'wbuschj5@multiply.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (691000, 'CC', 'Hayes O''Gormally', '12/11/1995', 'NORMAL', 'hogormallyj6@feedburner.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (692000, 'CC', 'Clayborn Carson', '12/29/1999', 'NORMAL', 'ccarsonj7@oracle.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (693000, 'CC', 'Baird Chestnutt', '10/27/1997', 'NORMAL', 'bchestnuttj8@umich.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (694000, 'CC', 'Clovis Wreath', '2/25/1998', 'NORMAL', 'cwreathj9@flavors.me');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (695000, 'CC', 'Henderson Parres', '9/27/1997', 'NORMAL', 'hparresja@soup.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (696000, 'CC', 'Vannie Heffernon', '6/22/2003', 'NORMAL', 'vheffernonjb@hibu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (697000, 'CC', 'Gui Chater', '2/1/1997', 'NORMAL', 'gchaterjc@va.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (698000, 'CC', 'Ignacio Ainscough', '6/27/1998', 'NORMAL', 'iainscoughjd@wp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (699000, 'CC', 'Huntington Bromilow', '12/31/1996', 'NORMAL', 'hbromilowje@bbb.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (700000, 'CC', 'Salli Olman', '6/20/1996', 'NORMAL', 'solmanjf@hexun.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (701000, 'CC', 'Abelard Patershall', '6/15/1996', 'NORMAL', 'apatershalljg@twitpic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (702000, 'CC', 'Hatti Pennycook', '12/31/1998', 'NORMAL', 'hpennycookjh@list-manage.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (703000, 'CC', 'Noemi Mulvy', '6/12/2003', 'NORMAL', 'nmulvyji@walmart.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (704000, 'CC', 'Zondra Lumby', '5/22/1996', 'NORMAL', 'zlumbyjj@va.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (705000, 'CC', 'Koralle Mashal', '11/3/1995', 'NORMAL', 'kmashaljk@newyorker.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (706000, 'CC', 'Beaufort Gowrich', '2/13/2002', 'NORMAL', 'bgowrichjl@nbcnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (707000, 'CC', 'Grata Machans', '1/18/2005', 'NORMAL', 'gmachansjm@nbcnews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (708000, 'CC', 'Seymour Grenshiels', '10/29/1995', 'NORMAL', 'sgrenshielsjn@cpanel.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (709000, 'CC', 'Isac Tuite', '6/27/2000', 'NORMAL', 'ituitejo@bbc.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (710000, 'CC', 'Jordana Rome', '3/6/2005', 'NORMAL', 'jromejp@instagram.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (711000, 'CC', 'Vannie Paulton', '12/8/2000', 'NORMAL', 'vpaultonjq@hugedomains.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (712000, 'CC', 'Jesse De Ferrari', '12/8/1999', 'NORMAL', 'jdejr@sciencedirect.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (713000, 'CC', 'Ronda Barczynski', '3/17/1997', 'NORMAL', 'rbarczynskijs@netlog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (714000, 'CC', 'Blondie Yitzovitz', '12/5/1995', 'NORMAL', 'byitzovitzjt@blogger.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (715000, 'CC', 'Merissa Wallen', '1/11/1997', 'NORMAL', 'mwallenju@trellian.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (716000, 'CC', 'Rafaello Blaymires', '11/4/1995', 'NORMAL', 'rblaymiresjv@indiegogo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (717000, 'CC', 'Flossie Anster', '8/25/1997', 'NORMAL', 'fansterjw@columbia.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (718000, 'CC', 'Cesare Valentine', '6/29/1997', 'NORMAL', 'cvalentinejx@slate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (719000, 'CC', 'Harmonia Dobbinson', '4/22/1999', 'NORMAL', 'hdobbinsonjy@nih.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (720000, 'CC', 'Patience O''Rudden', '8/1/1997', 'NORMAL', 'poruddenjz@google.com.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (721000, 'CC', 'Christiana Brookfield', '11/16/2000', 'NORMAL', 'cbrookfieldk0@illinois.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (722000, 'CC', 'Abe Pyper', '12/14/1998', 'NORMAL', 'apyperk1@washingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (723000, 'CC', 'Frasier Giacopetti', '5/19/1995', 'NORMAL', 'fgiacopettik2@meetup.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (724000, 'CC', 'Wilma McAvaddy', '7/8/1997', 'NORMAL', 'wmcavaddyk3@prnewswire.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (725000, 'CC', 'Robin Harradine', '7/12/1995', 'NORMAL', 'rharradinek4@vistaprint.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (726000, 'CC', 'Jeanine Turbayne', '8/20/2000', 'NORMAL', 'jturbaynek5@comsenz.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (727000, 'CC', 'Brinna Meni', '8/31/2003', 'NORMAL', 'bmenik6@biblegateway.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (728000, 'CC', 'Cello Antognazzi', '10/27/2001', 'NORMAL', 'cantognazzik7@redcross.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (729000, 'CC', 'Binny Hanwright', '1/28/2002', 'NORMAL', 'bhanwrightk8@webnode.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (730000, 'CC', 'Clementina Dalby', '9/15/1995', 'NORMAL', 'cdalbyk9@fda.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (731000, 'CC', 'Neal Attac', '1/28/2004', 'NORMAL', 'nattacka@goodreads.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (732000, 'CC', 'Pavia Gamage', '1/8/2000', 'NORMAL', 'pgamagekb@usatoday.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (733000, 'CC', 'Irena Greenlees', '12/11/1999', 'NORMAL', 'igreenleeskc@weibo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (734000, 'CC', 'Bobina Earie', '4/18/2002', 'NORMAL', 'beariekd@google.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (735000, 'CC', 'Gwen Mongan', '5/25/2001', 'NORMAL', 'gmonganke@apache.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (736000, 'CC', 'Bibbie Grazier', '5/16/1998', 'NORMAL', 'bgrazierkf@hostgator.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (737000, 'CC', 'Reuven Ferrier', '4/29/2003', 'NORMAL', 'rferrierkg@answers.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (738000, 'CC', 'Hiram Adshede', '11/13/2003', 'NORMAL', 'hadshedekh@mozilla.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (739000, 'CC', 'Malissia Drewes', '10/26/1996', 'NORMAL', 'mdreweski@alibaba.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (740000, 'CC', 'Rusty Hosburn', '9/13/2004', 'NORMAL', 'rhosburnkj@weibo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (741000, 'CC', 'Gillian Freckleton', '4/25/1999', 'NORMAL', 'gfreckletonkk@bizjournals.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (742000, 'CC', 'Klara Rushby', '10/23/1995', 'NORMAL', 'krushbykl@t-online.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (743000, 'CC', 'Sabra Laughren', '11/9/1996', 'NORMAL', 'slaughrenkm@cnn.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (744000, 'CC', 'Carl Kollasch', '10/24/1996', 'NORMAL', 'ckollaschkn@sfgate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (745000, 'CC', 'Muffin McColgan', '10/20/1998', 'NORMAL', 'mmccolganko@prlog.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (746000, 'CC', 'Garey Beurich', '3/19/1998', 'NORMAL', 'gbeurichkp@paginegialle.it');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (747000, 'CC', 'Leslie Uccello', '10/12/1996', 'NORMAL', 'luccellokq@google.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (748000, 'CC', 'Virge Kirton', '6/14/1998', 'NORMAL', 'vkirtonkr@icio.us');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (749000, 'CC', 'Margaretha Hackworthy', '11/3/1997', 'NORMAL', 'mhackworthyks@gizmodo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (750000, 'CC', 'Ginni Birrel', '11/2/1997', 'NORMAL', 'gbirrelkt@e-recht24.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (751000, 'CC', 'Anatol Kilian', '9/4/2004', 'NORMAL', 'akilianku@webmd.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (752000, 'CC', 'Kari Crookshank', '2/27/2003', 'NORMAL', 'kcrookshankkv@surveymonkey.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (753000, 'CC', 'Ennis Plevey', '1/23/2001', 'NORMAL', 'epleveykw@timesonline.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (754000, 'CC', 'Susette Roath', '12/24/1999', 'NORMAL', 'sroathkx@nydailynews.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (755000, 'CC', 'Brandi Stalf', '7/24/2001', 'NORMAL', 'bstalfky@flavors.me');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (756000, 'CC', 'Charmane Mumbeson', '12/16/2001', 'NORMAL', 'cmumbesonkz@123-reg.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (757000, 'CC', 'Eldredge Croad', '11/3/1995', 'NORMAL', 'ecroadl0@hibu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (758000, 'CC', 'Derry Southeran', '9/24/1995', 'NORMAL', 'dsoutheranl1@wix.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (759000, 'CC', 'Pavla Cockburn', '10/29/2003', 'NORMAL', 'pcockburnl2@pagesperso-orange.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (760000, 'CC', 'Ealasaid Dunsmore', '6/28/2002', 'NORMAL', 'edunsmorel3@mapy.cz');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (761000, 'CC', 'Caressa Lewins', '2/5/2003', 'NORMAL', 'clewinsl4@house.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (762000, 'CC', 'Antoni Filippazzo', '12/28/1998', 'NORMAL', 'afilippazzol5@slideshare.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (763000, 'CC', 'Maximilianus Sugden', '5/19/2001', 'NORMAL', 'msugdenl6@homestead.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (764000, 'CC', 'Rakel Swetenham', '1/5/1997', 'NORMAL', 'rswetenhaml7@weather.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (765000, 'CC', 'Lowell Lindblad', '9/15/1995', 'NORMAL', 'llindbladl8@huffingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (766000, 'CC', 'Silva Girardoni', '3/25/2000', 'NORMAL', 'sgirardonil9@tamu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (767000, 'CC', 'Leslie Byars', '4/19/2002', 'NORMAL', 'lbyarsla@cbslocal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (768000, 'CC', 'Tatiania Blenkharn', '9/21/1995', 'NORMAL', 'tblenkharnlb@yahoo.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (769000, 'CC', 'Celina Cluitt', '7/5/1996', 'NORMAL', 'ccluittlc@slate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (770000, 'CC', 'Araldo Haigh', '7/11/2003', 'NORMAL', 'ahaighld@godaddy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (771000, 'CC', 'Jillana Derges', '8/16/2001', 'NORMAL', 'jdergesle@51.la');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (772000, 'CC', 'Friedrick O''Hone', '1/11/2000', 'NORMAL', 'fohonelf@fema.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (773000, 'CC', 'Ramsay Steketee', '1/21/1998', 'NORMAL', 'rsteketeelg@nhs.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (774000, 'CC', 'Linea Martyns', '7/22/1998', 'NORMAL', 'lmartynslh@opensource.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (775000, 'CC', 'Ilsa Fearnley', '5/12/1999', 'NORMAL', 'ifearnleyli@geocities.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (776000, 'CC', 'Gilberte Waith', '7/24/1995', 'NORMAL', 'gwaithlj@unicef.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (777000, 'CC', 'Linette Conibear', '7/5/1995', 'NORMAL', 'lconibearlk@com.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (778000, 'CC', 'Ailis Waison', '3/13/2000', 'NORMAL', 'awaisonll@youtu.be');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (779000, 'CC', 'Joyous Richings', '1/5/2004', 'NORMAL', 'jrichingslm@joomla.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (780000, 'CC', 'Aretha Jzak', '12/20/1996', 'NORMAL', 'ajzakln@wp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (781000, 'CC', 'Mamie Sarfat', '10/30/1997', 'NORMAL', 'msarfatlo@google.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (782000, 'CC', 'Timmy de Castelain', '10/3/1996', 'NORMAL', 'tdelp@xrea.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (783000, 'CC', 'Lin Gaynor', '11/12/1997', 'NORMAL', 'lgaynorlq@google.fr');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (784000, 'CC', 'Theda Tynemouth', '4/26/2000', 'NORMAL', 'ttynemouthlr@taobao.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (785000, 'CC', 'Karyl Wrey', '8/12/1998', 'NORMAL', 'kwreyls@ftc.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (786000, 'CC', 'Irvin Siney', '5/26/1998', 'NORMAL', 'isineylt@goo.gl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (787000, 'CC', 'Jilli Fermoy', '3/30/2001', 'NORMAL', 'jfermoylu@elegantthemes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (788000, 'CC', 'Gamaliel Figure', '4/9/1998', 'NORMAL', 'gfigurelv@linkedin.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (789000, 'CC', 'Bibby Kenton', '7/26/1995', 'NORMAL', 'bkentonlw@theguardian.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (790000, 'CC', 'Husein Biernat', '3/23/1999', 'NORMAL', 'hbiernatlx@wikia.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (791000, 'CC', 'Corabelle Wannes', '3/10/2004', 'NORMAL', 'cwannesly@opensource.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (792000, 'CC', 'Bette Thorneley', '6/13/1999', 'NORMAL', 'bthorneleylz@github.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (793000, 'CC', 'Joellyn Hincham', '8/27/2001', 'NORMAL', 'jhinchamm0@soundcloud.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (794000, 'CC', 'Arne Trahair', '8/3/2000', 'NORMAL', 'atrahairm1@barnesandnoble.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (795000, 'CC', 'Alice Glander', '8/31/2000', 'NORMAL', 'aglanderm2@quantcast.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (796000, 'CC', 'Yurik Coetzee', '2/16/1999', 'NORMAL', 'ycoetzeem3@blogs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (797000, 'CC', 'Jude Merrikin', '8/4/2003', 'NORMAL', 'jmerrikinm4@i2i.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (798000, 'CC', 'Merrilee Doodson', '1/30/2005', 'NORMAL', 'mdoodsonm5@google.ca');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (799000, 'CC', 'Valentine McCombe', '11/15/2004', 'NORMAL', 'vmccombem6@moonfruit.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (800000, 'CC', 'Hazel Chasney', '4/13/2002', 'NORMAL', 'hchasneym7@engadget.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (801000, 'CC', 'Merrielle Conkay', '12/20/1995', 'NORMAL', 'mconkaym8@histats.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (802000, 'CC', 'Valerye Clother', '7/10/1999', 'NORMAL', 'vclotherm9@furl.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (803000, 'CC', 'Adel Harbinson', '9/24/2001', 'NORMAL', 'aharbinsonma@bbc.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (804000, 'CC', 'Cecily Ciccotti', '6/7/2003', 'NORMAL', 'cciccottimb@chronoengine.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (805000, 'CC', 'Lynn McKew', '7/26/2003', 'NORMAL', 'lmckewmc@senate.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (806000, 'CC', 'Rozalie Whorlton', '2/13/2001', 'NORMAL', 'rwhorltonmd@vistaprint.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (807000, 'CC', 'Hedy Astle', '8/5/2000', 'NORMAL', 'hastleme@homestead.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (808000, 'CC', 'Simeon Musker', '12/5/2003', 'NORMAL', 'smuskermf@pen.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (809000, 'CC', 'Baron Prescote', '1/9/2002', 'NORMAL', 'bprescotemg@amazonaws.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (810000, 'CC', 'Erica Glasspoole', '12/3/2002', 'NORMAL', 'eglasspoolemh@ihg.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (811000, 'CC', 'Lilian Folliss', '12/4/2004', 'NORMAL', 'lfollissmi@chron.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (812000, 'CC', 'Emilio Slisby', '2/6/1999', 'NORMAL', 'eslisbymj@friendfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (813000, 'CC', 'Georgetta Golder', '2/22/2004', 'NORMAL', 'ggoldermk@nature.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (814000, 'CC', 'Ranice Trewhitt', '6/28/2002', 'NORMAL', 'rtrewhittml@quantcast.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (815000, 'CC', 'Sigismondo Fewkes', '11/29/1999', 'NORMAL', 'sfewkesmm@163.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (816000, 'CC', 'Ellswerth Pettinger', '9/18/1998', 'NORMAL', 'epettingermn@fotki.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (817000, 'CC', 'Francoise Trittam', '12/17/2002', 'NORMAL', 'ftrittammo@deviantart.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (818000, 'CC', 'Ethelred Licas', '5/21/1998', 'NORMAL', 'elicasmp@blogspot.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (819000, 'CC', 'Yance Everleigh', '4/29/2002', 'NORMAL', 'yeverleighmq@pbs.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (820000, 'CC', 'Melissa Alsina', '6/20/1999', 'NORMAL', 'malsinamr@jiathis.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (821000, 'CC', 'Wilmar Bibb', '9/24/1996', 'NORMAL', 'wbibbms@yahoo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (822000, 'CC', 'Corrinne Clapp', '4/25/2003', 'NORMAL', 'cclappmt@rambler.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (823000, 'CC', 'Gertruda Maharg', '3/18/1997', 'NORMAL', 'gmahargmu@opera.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (824000, 'CC', 'Lorraine Joiner', '8/15/1995', 'NORMAL', 'ljoinermv@aboutads.info');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (825000, 'CC', 'Maxie Alexandersen', '2/12/2005', 'NORMAL', 'malexandersenmw@tmall.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (826000, 'CC', 'Tandy Glennard', '9/20/1999', 'NORMAL', 'tglennardmx@yelp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (827000, 'CC', 'Sollie Spybey', '4/1/2000', 'NORMAL', 'sspybeymy@ustream.tv');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (828000, 'CC', 'Abram Croxley', '7/14/2004', 'NORMAL', 'acroxleymz@si.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (829000, 'CC', 'Latrina Rocco', '5/20/1997', 'NORMAL', 'lroccon0@cpanel.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (830000, 'CC', 'Ebba Bernardeschi', '5/17/1995', 'NORMAL', 'ebernardeschin1@goo.gl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (831000, 'CC', 'Mata Tench', '2/1/2000', 'NORMAL', 'mtenchn2@psu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (832000, 'CC', 'Melitta Conway', '8/5/1996', 'NORMAL', 'mconwayn3@earthlink.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (833000, 'CC', 'Thedrick Rumbellow', '5/9/1998', 'NORMAL', 'trumbellown4@miitbeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (834000, 'CC', 'Donelle Pride', '6/10/2002', 'NORMAL', 'dpriden5@clickbank.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (835000, 'CC', 'Brooke Tousey', '12/27/2001', 'NORMAL', 'btouseyn6@miitbeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (836000, 'CC', 'Elmore Kettell', '9/26/1995', 'NORMAL', 'ekettelln7@amazonaws.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (837000, 'CC', 'Andres Meas', '6/13/1996', 'NORMAL', 'ameasn8@intel.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (838000, 'CC', 'Berkie Trenholm', '7/15/2001', 'NORMAL', 'btrenholmn9@lulu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (839000, 'CC', 'Averill Arbon', '8/6/2000', 'NORMAL', 'aarbonna@liveinternet.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (840000, 'CC', 'Sallie Kelsow', '12/16/1997', 'NORMAL', 'skelsownb@about.me');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (841000, 'CC', 'Dianne Bridgewater', '2/20/1999', 'NORMAL', 'dbridgewaternc@indiegogo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (842000, 'CC', 'Yasmeen Kilby', '7/25/1996', 'NORMAL', 'ykilbynd@blogtalkradio.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (843000, 'CC', 'Marie Wethey', '4/22/2004', 'NORMAL', 'mwetheyne@squarespace.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (844000, 'CC', 'Hi Horley', '8/4/1999', 'NORMAL', 'hhorleynf@w3.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (845000, 'CC', 'Gerek Coldrick', '12/25/1999', 'NORMAL', 'gcoldrickng@tripadvisor.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (846000, 'CC', 'Fernande Chirm', '1/6/2001', 'NORMAL', 'fchirmnh@buzzfeed.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (847000, 'CC', 'Rebekkah Dunlop', '11/14/2001', 'NORMAL', 'rdunlopni@nytimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (848000, 'CC', 'Liana Ferrettino', '7/27/1996', 'NORMAL', 'lferrettinonj@w3.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (849000, 'CC', 'Angela Casey', '4/20/2002', 'NORMAL', 'acaseynk@yellowpages.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (850000, 'CC', 'Kate Kopfer', '4/23/2001', 'NORMAL', 'kkopfernl@booking.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (851000, 'CC', 'Jacquelynn Aime', '9/16/1999', 'NORMAL', 'jaimenm@163.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (852000, 'CC', 'Brendon Lehrle', '12/7/1999', 'NORMAL', 'blehrlenn@tamu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (853000, 'CC', 'Cristen Grace', '8/30/2004', 'NORMAL', 'cgraceno@mlb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (854000, 'CC', 'Arleta Senett', '9/22/2004', 'NORMAL', 'asenettnp@squarespace.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (855000, 'CC', 'Trula Frusher', '3/1/1999', 'NORMAL', 'tfrushernq@eepurl.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (856000, 'CC', 'Flossie Eate', '7/31/1997', 'NORMAL', 'featenr@hud.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (857000, 'CC', 'Lida Janney', '11/14/2004', 'NORMAL', 'ljanneyns@shop-pro.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (858000, 'CC', 'Bathsheba Kocher', '1/26/2005', 'NORMAL', 'bkochernt@cpanel.net');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (859000, 'CC', 'Mohandis Vedishchev', '9/24/1998', 'NORMAL', 'mvedishchevnu@washingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (860000, 'CC', 'Marchall Trevascus', '5/30/1997', 'NORMAL', 'mtrevascusnv@com.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (861000, 'CC', 'Pinchas Yeowell', '2/18/2005', 'NORMAL', 'pyeowellnw@unicef.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (862000, 'CC', 'Haroun O''Gavin', '10/16/2004', 'NORMAL', 'hogavinnx@soup.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (863000, 'CC', 'Lauraine Newcom', '1/7/1996', 'NORMAL', 'lnewcomny@discovery.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (864000, 'CC', 'Chelsae Cantos', '1/3/1997', 'NORMAL', 'ccantosnz@reverbnation.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (865000, 'CC', 'Sharron McDonagh', '3/30/2002', 'NORMAL', 'smcdonagho0@netvibes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (866000, 'CC', 'Inglis Lachaize', '11/14/2001', 'NORMAL', 'ilachaizeo1@sciencedirect.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (867000, 'CC', 'Franny Hick', '10/28/2004', 'NORMAL', 'fhicko2@discovery.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (868000, 'CC', 'Austina Morritt', '9/21/2000', 'NORMAL', 'amorritto3@jimdo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (869000, 'CC', 'Alyce Holde', '10/26/1996', 'NORMAL', 'aholdeo4@wsj.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (870000, 'CC', 'Harlene Meaden', '2/13/1998', 'NORMAL', 'hmeadeno5@comsenz.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (871000, 'CC', 'Lance Broszkiewicz', '1/26/2002', 'NORMAL', 'lbroszkiewiczo6@google.com.hk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (872000, 'CC', 'Jennette McGraw', '1/22/2003', 'NORMAL', 'jmcgrawo7@github.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (873000, 'CC', 'Georgina Ditchburn', '1/5/2000', 'NORMAL', 'gditchburno8@nhs.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (874000, 'CC', 'Wain Dinnage', '4/25/1999', 'NORMAL', 'wdinnageo9@nationalgeographic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (875000, 'CC', 'Darnell Pauleau', '5/21/2003', 'NORMAL', 'dpauleauoa@miibeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (876000, 'CC', 'Merry Bahde', '11/22/1995', 'NORMAL', 'mbahdeob@admin.ch');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (877000, 'CC', 'Blanca Doel', '6/13/2001', 'NORMAL', 'bdoeloc@pcworld.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (878000, 'CC', 'Cornie Lusher', '7/9/2004', 'NORMAL', 'clusherod@pen.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (879000, 'CC', 'Fiorenze Sarll', '10/6/1999', 'NORMAL', 'fsarlloe@slate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (880000, 'CC', 'Gearalt Crim', '6/2/1999', 'NORMAL', 'gcrimof@arstechnica.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (881000, 'CC', 'Titus Mairs', '10/18/2001', 'NORMAL', 'tmairsog@w3.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (882000, 'CC', 'Mariellen Ferraro', '3/19/1997', 'NORMAL', 'mferrarooh@bandcamp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (883000, 'CC', 'Charlotta Savage', '11/3/1996', 'NORMAL', 'csavageoi@mozilla.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (884000, 'CC', 'Lorne Botha', '7/30/2003', 'NORMAL', 'lbothaoj@people.com.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (885000, 'CC', 'Gilda Thebe', '2/27/2001', 'NORMAL', 'gthebeok@columbia.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (886000, 'CC', 'Jeddy Stiller', '8/14/2001', 'NORMAL', 'jstillerol@fotki.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (887000, 'CC', 'Lita Clampe', '11/6/1997', 'NORMAL', 'lclampeom@accuweather.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (888000, 'CC', 'Bruce Rippingall', '8/23/1995', 'NORMAL', 'brippingallon@networkadvertising.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (889000, 'CC', 'Cathrin Doppler', '3/25/2002', 'NORMAL', 'cdoppleroo@nasa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (890000, 'CC', 'Marilyn Pieche', '1/20/2002', 'NORMAL', 'mpiecheop@latimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (891000, 'CC', 'Alec Beany', '10/4/1995', 'NORMAL', 'abeanyoq@sfgate.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (892000, 'CC', 'Brian Castanone', '5/20/1996', 'NORMAL', 'bcastanoneor@mysql.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (893000, 'CC', 'Sarene Brake', '9/23/1995', 'NORMAL', 'sbrakeos@eventbrite.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (894000, 'CC', 'Marney McGinty', '11/17/1996', 'NORMAL', 'mmcgintyot@parallels.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (895000, 'CC', 'Bibi Wegenen', '9/1/2000', 'NORMAL', 'bwegenenou@narod.ru');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (896000, 'CC', 'Kirsten Willman', '10/30/2004', 'NORMAL', 'kwillmanov@1688.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (897000, 'CC', 'Paquito Parncutt', '2/8/1997', 'NORMAL', 'pparncuttow@bizjournals.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (898000, 'CC', 'Paule Simmgen', '11/25/1996', 'NORMAL', 'psimmgenox@washingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (899000, 'CC', 'Arthur Eefting', '2/24/2003', 'NORMAL', 'aeeftingoy@github.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (900000, 'CC', 'Anatole Catherick', '10/12/1999', 'NORMAL', 'acatherickoz@abc.net.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (901000, 'CC', 'Ty Mattingly', '10/28/2004', 'NORMAL', 'tmattinglyp0@weibo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (902000, 'CC', 'Queenie Adamovitz', '12/29/1999', 'NORMAL', 'qadamovitzp1@goodreads.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (903000, 'CC', 'Rayshell Blacket', '11/24/2001', 'NORMAL', 'rblacketp2@dion.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (904000, 'CC', 'Melli Weson', '7/10/1999', 'NORMAL', 'mwesonp3@google.com.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (905000, 'CC', 'Kiley Wyllcocks', '9/11/2002', 'NORMAL', 'kwyllcocksp4@sina.com.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (906000, 'CC', 'Candi Ternouth', '2/15/1996', 'NORMAL', 'cternouthp5@telegraph.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (907000, 'CC', 'Dyana Cortes', '10/22/2003', 'NORMAL', 'dcortesp6@miibeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (908000, 'CC', 'Garwood Ruspine', '3/7/1999', 'NORMAL', 'gruspinep7@whitehouse.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (909000, 'CC', 'Kristoforo Sawyer', '3/22/1999', 'NORMAL', 'ksawyerp8@indiegogo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (910000, 'CC', 'Mendy Bickerstaffe', '11/23/1995', 'NORMAL', 'mbickerstaffep9@springer.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (911000, 'CC', 'Blayne Burnes', '1/1/2001', 'NORMAL', 'bburnespa@va.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (912000, 'CC', 'Michell Dobell', '1/15/2004', 'NORMAL', 'mdobellpb@squarespace.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (913000, 'CC', 'Flss Lude', '5/6/1999', 'NORMAL', 'fludepc@amazon.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (914000, 'CC', 'Libby Astall', '8/12/2003', 'NORMAL', 'lastallpd@msu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (915000, 'CC', 'Kain Tripcony', '6/15/2001', 'NORMAL', 'ktripconype@webs.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (916000, 'CC', 'Franklyn Putman', '4/5/2001', 'NORMAL', 'fputmanpf@icq.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (917000, 'CC', 'Obadiah Mee', '10/20/1997', 'NORMAL', 'omeepg@jimdo.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (918000, 'CC', 'Padgett Troni', '2/9/2002', 'NORMAL', 'ptroniph@statcounter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (919000, 'CC', 'Mariele Emmatt', '7/30/1995', 'NORMAL', 'memmattpi@amazonaws.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (920000, 'CC', 'Lorant Blaxeland', '5/10/2004', 'NORMAL', 'lblaxelandpj@paypal.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (921000, 'CC', 'Renee Richemont', '6/7/2001', 'NORMAL', 'rrichemontpk@goo.ne.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (922000, 'CC', 'Benedikt Bennedick', '6/2/2002', 'NORMAL', 'bbennedickpl@skype.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (923000, 'CC', 'Vickie Franken', '8/16/2003', 'NORMAL', 'vfrankenpm@icio.us');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (924000, 'CC', 'Dido Tilberry', '3/27/2002', 'NORMAL', 'dtilberrypn@delicious.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (925000, 'CC', 'Bayard Ong', '12/28/2004', 'NORMAL', 'bongpo@nytimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (926000, 'CC', 'Cornelius Mussilli', '6/22/1999', 'NORMAL', 'cmussillipp@ucsd.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (927000, 'CC', 'Sergeant Pattisson', '10/11/2001', 'NORMAL', 'spattissonpq@youtube.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (928000, 'CC', 'Retha Valentinuzzi', '3/11/2005', 'NORMAL', 'rvalentinuzzipr@hp.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (929000, 'CC', 'Tito Monte', '6/29/2001', 'NORMAL', 'tmonteps@utexas.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (930000, 'CC', 'Benjie Advani', '8/3/2000', 'NORMAL', 'badvanipt@163.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (931000, 'CC', 'Evvie Juschke', '4/20/1999', 'NORMAL', 'ejuschkepu@google.nl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (932000, 'CC', 'Minnie Salvador', '4/23/1997', 'NORMAL', 'msalvadorpv@yahoo.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (933000, 'CC', 'Torr Hebblewaite', '11/13/2001', 'NORMAL', 'thebblewaitepw@privacy.gov.au');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (934000, 'CC', 'Helga Shillabeare', '1/18/2003', 'NORMAL', 'hshillabearepx@ebay.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (935000, 'CC', 'Theo Botterman', '12/12/1995', 'NORMAL', 'tbottermanpy@oracle.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (936000, 'CC', 'Daisie Ferron', '4/24/1998', 'NORMAL', 'dferronpz@imdb.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (937000, 'CC', 'Dareen Laneham', '9/10/2003', 'NORMAL', 'dlanehamq0@independent.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (938000, 'CC', 'Tiertza Peachment', '2/18/2005', 'NORMAL', 'tpeachmentq1@admin.ch');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (939000, 'CC', 'Linnea Girod', '12/19/2004', 'NORMAL', 'lgirodq2@howstuffworks.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (940000, 'CC', 'Giffy McIlvoray', '9/18/2001', 'NORMAL', 'gmcilvorayq3@omniture.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (941000, 'CC', 'Sollie Kliment', '1/22/2003', 'NORMAL', 'sklimentq4@blinklist.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (942000, 'CC', 'Garrott Talboy', '9/27/2002', 'NORMAL', 'gtalboyq5@geocities.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (943000, 'CC', 'Tawsha Cowsby', '12/13/1998', 'NORMAL', 'tcowsbyq6@blog.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (944000, 'CC', 'Berget Minchin', '6/2/1998', 'NORMAL', 'bminchinq7@yahoo.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (945000, 'CC', 'Corny Dunniom', '1/20/2000', 'NORMAL', 'cdunniomq8@census.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (946000, 'CC', 'Rafa de Castelain', '5/29/2001', 'NORMAL', 'rdeq9@google.pl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (947000, 'CC', 'Chadd Vassar', '11/23/1997', 'NORMAL', 'cvassarqa@ebay.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (948000, 'CC', 'Brina Bargery', '7/25/1998', 'NORMAL', 'bbargeryqb@google.nl');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (949000, 'CC', 'Nicole Lyngsted', '10/14/2001', 'NORMAL', 'nlyngstedqc@answers.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (950000, 'CC', 'Ulberto Dewes', '2/16/2003', 'NORMAL', 'udewesqd@nationalgeographic.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (951000, 'CC', 'Harlan Attaway', '6/17/1997', 'NORMAL', 'hattawayqe@soup.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (952000, 'CC', 'Reginald Sprowle', '4/17/2003', 'NORMAL', 'rsprowleqf@soup.io');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (953000, 'CC', 'Ferdinande Morfell', '6/14/1998', 'NORMAL', 'fmorfellqg@dedecms.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (954000, 'CC', 'Brynn Clampe', '1/17/2000', 'NORMAL', 'bclampeqh@biblegateway.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (955000, 'CC', 'Zeb Beaton', '9/13/1995', 'NORMAL', 'zbeatonqi@zdnet.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (956000, 'CC', 'Elli Treadgold', '1/22/2000', 'NORMAL', 'etreadgoldqj@issuu.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (957000, 'CC', 'Electra Thominga', '6/16/2002', 'NORMAL', 'ethomingaqk@state.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (958000, 'CC', 'Edita Pedreschi', '1/6/1997', 'NORMAL', 'epedreschiql@discovery.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (959000, 'CC', 'Ardelle Minithorpe', '3/15/1997', 'NORMAL', 'aminithorpeqm@amazon.co.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (960000, 'CC', 'Julius Taffee', '11/5/1998', 'NORMAL', 'jtaffeeqn@seattletimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (961000, 'CC', 'Jobye Tufts', '11/14/1998', 'NORMAL', 'jtuftsqo@seattletimes.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (962000, 'CC', 'Myriam Flips', '2/11/1996', 'NORMAL', 'mflipsqp@nasa.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (963000, 'CC', 'Kamila Pitkins', '6/23/1999', 'NORMAL', 'kpitkinsqq@fastcompany.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (964000, 'CC', 'Hilly McNiven', '11/11/1997', 'NORMAL', 'hmcnivenqr@hud.gov');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (965000, 'CC', 'Warden Leppingwell', '7/18/1997', 'NORMAL', 'wleppingwellqs@pbs.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (966000, 'CC', 'Jaquith Sarfati', '8/30/2002', 'NORMAL', 'jsarfatiqt@un.org');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (967000, 'CC', 'Huntley Avrahm', '4/19/1997', 'NORMAL', 'havrahmqu@miibeian.gov.cn');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (968000, 'CC', 'Amble Godthaab', '10/7/2001', 'NORMAL', 'agodthaabqv@psu.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (969000, 'CC', 'Harbert Marlen', '8/28/1997', 'NORMAL', 'hmarlenqw@g.co');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (970000, 'CC', 'Johannah Muckeen', '10/30/2003', 'NORMAL', 'jmuckeenqx@techcrunch.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (971000, 'CC', 'Inger Linacre', '12/16/1996', 'NORMAL', 'ilinacreqy@sitemeter.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (972000, 'CC', 'Olag Ferber', '8/29/2004', 'NORMAL', 'oferberqz@t-online.de');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (973000, 'CC', 'Massimo Snaddin', '4/14/2002', 'NORMAL', 'msnaddinr0@artisteer.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (974000, 'CC', 'Yorke D''Agostini', '9/20/1998', 'NORMAL', 'ydagostinir1@tuttocitta.it');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (975000, 'CC', 'Olimpia Farrens', '5/5/2003', 'NORMAL', 'ofarrensr2@geocities.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (976000, 'CC', 'Sheryl Savill', '6/13/1999', 'NORMAL', 'ssavillr3@wsj.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (977000, 'CC', 'Lodovico Grigore', '1/11/2004', 'NORMAL', 'lgrigorer4@cam.ac.uk');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (978000, 'CC', 'Starr Pocklington', '1/24/1997', 'NORMAL', 'spocklingtonr5@netscape.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (979000, 'CC', 'Francklyn Mackneis', '11/13/2000', 'NORMAL', 'fmackneisr6@boston.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (980000, 'CC', 'Vi Brader', '9/28/1997', 'NORMAL', 'vbraderr7@techcrunch.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (981000, 'CC', 'Cissiee Gatch', '7/20/2002', 'NORMAL', 'cgatchr8@stanford.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (982000, 'CC', 'Shelli Gurrado', '6/23/1999', 'NORMAL', 'sgurrador9@deliciousdays.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (983000, 'CC', 'Aloin Halladey', '6/3/1999', 'NORMAL', 'ahalladeyra@ebay.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (984000, 'CC', 'Raimundo Krook', '10/12/1995', 'NORMAL', 'rkrookrb@oakley.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (985000, 'CC', 'Giffie Scorton', '3/13/1997', 'NORMAL', 'gscortonrc@blogger.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (986000, 'CC', 'Lewie Foddy', '9/8/1996', 'NORMAL', 'lfoddyrd@etsy.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (987000, 'CC', 'Cyrille Bourthouloume', '11/4/1999', 'NORMAL', 'cbourthouloumere@businessweek.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (988000, 'CC', 'Hube Gatcliff', '6/27/2001', 'NORMAL', 'hgatcliffrf@printfriendly.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (989000, 'CC', 'Gaelan Eggins', '5/26/2004', 'NORMAL', 'gegginsrg@wikia.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (990000, 'CC', 'Hastings Hawford', '10/25/2000', 'NORMAL', 'hhawfordrh@nifty.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (991000, 'CC', 'Amaleta Iredell', '9/2/1999', 'NORMAL', 'airedellri@huffingtonpost.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (992000, 'CC', 'Alica Tureville', '6/17/1999', 'NORMAL', 'aturevillerj@de.vu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (993000, 'CC', 'Allan Teffrey', '3/23/2001', 'NORMAL', 'ateffreyrk@blinklist.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (994000, 'CC', 'Evin Orteu', '11/19/1995', 'NORMAL', 'eorteurl@unc.edu');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (995000, 'CC', 'Melodie Mostyn', '9/15/2000', 'NORMAL', 'mmostynrm@com.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (996000, 'CC', 'Broddie Simao', '7/10/1995', 'NORMAL', 'bsimaorn@yahoo.co.jp');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (997000, 'CC', 'Annabel Mangeot', '7/9/1997', 'NORMAL', 'amangeotro@addthis.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (998000, 'CC', 'Carolyne Fairlaw', '10/25/2002', 'NORMAL', 'cfairlawrp@opera.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (999000, 'CC', 'Lizette Cooch', '7/24/2001', 'NORMAL', 'lcoochrq@myspace.com');
insert into ASPIRANTE (aspid, asptipodoc, aspnombre, aspfechanac, asptipoinscrito, aspcorreo) values (1000000, 'CC', 'Bianka Denisovich', '1/31/1998', 'NORMAL', 'bdenisovichrr@oakley.com');


insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (1,1,1000,1,'AC201711234598');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (2,2,2000,1,'AC201711234599');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (3,3,3000,1,'AC201711234600');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (4,4,4000,1,'AC201711234601');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (5,5,5000,1,'AC201711234602');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (6,6,6000,1,'AC201711234603');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (7,7,7000,1,'AC201711234604');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (8,1,8000,1,'AC201711234605');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (9,2,9000,1,'AC201711234606');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (10,3,10000,1,'AC201711234607');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (11,4,11000,1,'AC201711234608');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (12,5,12000,1,'AC201711234609');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (13,6,13000,1,'AC201711234610');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (14,7,14000,1,'AC201711234611');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (15,1,15000,1,'AC201711234612');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (16,2,16000,1,'AC201711234613');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (17,3,17000,1,'AC201711234614');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (18,4,18000,1,'AC201711234615');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (19,5,19000,1,'AC201711234616');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (20,6,20000,1,'AC201711234617');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (21,7,21000,1,'AC201711234618');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (22,1,22000,1,'AC201711234619');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (23,2,23000,1,'AC201711234620');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (24,3,24000,1,'AC201711234621');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (25,4,25000,1,'AC201711234622');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (26,5,26000,1,'AC201711234623');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (27,6,27000,1,'AC201711234624');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (28,7,28000,1,'AC201711234625');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (29,1,29000,1,'AC201711234626');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (30,2,30000,1,'AC201711234627');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (31,3,31000,1,'AC201711234628');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (32,4,32000,1,'AC201711234629');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (33,5,33000,1,'AC201711234630');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (34,6,34000,1,'AC201711234631');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (35,7,35000,1,'AC201711234632');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (36,1,36000,1,'AC201711234633');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (37,2,37000,1,'AC201711234634');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (38,3,38000,1,'AC201711234635');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (39,4,39000,1,'AC201711234636');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (40,5,40000,1,'AC201711234637');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (41,6,41000,1,'AC201711234638');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (42,7,42000,1,'AC201711234639');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (43,1,43000,1,'AC201711234640');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (44,2,44000,1,'AC201711234641');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (45,3,45000,1,'AC201711234642');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (46,4,46000,1,'AC201711234643');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (47,5,47000,1,'AC201711234644');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (48,6,48000,1,'AC201711234645');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (49,7,49000,1,'AC201711234646');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (50,1,50000,1,'AC201711234647');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (51,2,51000,1,'AC201711234648');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (52,3,52000,1,'AC201711234649');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (53,4,53000,1,'AC201711234650');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (54,5,54000,1,'AC201711234651');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (55,6,55000,1,'AC201711234652');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (56,7,56000,1,'AC201711234653');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (57,1,57000,1,'AC201711234654');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (58,2,58000,1,'AC201711234655');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (59,3,59000,1,'AC201711234656');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (60,4,60000,1,'AC201711234657');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (61,5,61000,1,'AC201711234658');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (62,6,62000,1,'AC201711234659');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (63,7,63000,1,'AC201711234660');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (64,1,64000,1,'AC201711234661');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (65,2,65000,1,'AC201711234662');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (66,3,66000,1,'AC201711234663');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (67,4,67000,1,'AC201711234664');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (68,5,68000,1,'AC201711234665');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (69,6,69000,1,'AC201711234666');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (70,7,70000,1,'AC201711234667');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (71,1,71000,1,'AC201711234668');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (72,2,72000,1,'AC201711234669');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (73,3,73000,1,'AC201711234670');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (74,4,74000,1,'AC201711234671');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (75,5,75000,1,'AC201711234672');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (76,6,76000,1,'AC201711234673');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (77,7,77000,1,'AC201711234674');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (78,1,78000,1,'AC201711234675');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (79,2,79000,1,'AC201711234676');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (80,3,80000,1,'AC201711234677');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (81,4,81000,1,'AC201711234678');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (82,5,82000,1,'AC201711234679');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (83,6,83000,1,'AC201711234680');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (84,7,84000,1,'AC201711234681');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (85,1,85000,1,'AC201711234682');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (86,2,86000,1,'AC201711234683');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (87,3,87000,1,'AC201711234684');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (88,4,88000,1,'AC201711234685');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (89,5,89000,1,'AC201711234686');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (90,6,90000,1,'AC201711234687');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (91,7,91000,1,'AC201711234688');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (92,1,92000,1,'AC201711234689');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (93,2,93000,1,'AC201711234690');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (94,3,94000,1,'AC201711234691');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (95,4,95000,1,'AC201711234692');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (96,5,96000,1,'AC201711234693');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (97,6,97000,1,'AC201711234694');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (98,7,98000,1,'AC201711234695');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (99,1,99000,1,'AC201711234696');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (100,2,100000,1,'AC201711234697');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (101,3,101000,1,'AC201711234698');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (102,4,102000,1,'AC201711234699');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (103,5,103000,1,'AC201711234700');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (104,6,104000,1,'AC201711234701');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (105,7,105000,1,'AC201711234702');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (106,1,106000,1,'AC201711234703');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (107,2,107000,1,'AC201711234704');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (108,3,108000,1,'AC201711234705');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (109,4,109000,1,'AC201711234706');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (110,5,110000,1,'AC201711234707');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (111,6,111000,1,'AC201711234708');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (112,7,112000,1,'AC201711234709');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (113,1,113000,1,'AC201711234710');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (114,2,114000,1,'AC201711234711');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (115,3,115000,1,'AC201711234712');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (116,4,116000,1,'AC201711234713');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (117,5,117000,1,'AC201711234714');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (118,6,118000,1,'AC201711234715');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (119,7,119000,1,'AC201711234716');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (120,1,120000,1,'AC201711234717');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (121,2,121000,1,'AC201711234718');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (122,3,122000,1,'AC201711234719');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (123,4,123000,1,'AC201711234720');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (124,5,124000,1,'AC201711234721');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (125,6,125000,1,'AC201711234722');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (126,7,126000,1,'AC201711234723');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (127,1,127000,1,'AC201711234724');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (128,2,128000,1,'AC201711234725');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (129,3,129000,1,'AC201711234726');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (130,4,130000,1,'AC201711234727');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (131,5,131000,1,'AC201711234728');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (132,6,132000,1,'AC201711234729');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (133,7,133000,1,'AC201711234730');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (134,1,134000,1,'AC201711234731');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (135,2,135000,1,'AC201711234732');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (136,3,136000,1,'AC201711234733');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (137,4,137000,1,'AC201711234734');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (138,5,138000,1,'AC201711234735');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (139,6,139000,1,'AC201711234736');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (140,7,140000,1,'AC201711234737');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (141,1,141000,1,'AC201711234738');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (142,2,142000,1,'AC201711234739');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (143,3,143000,1,'AC201711234740');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (144,4,144000,1,'AC201711234741');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (145,5,145000,1,'AC201711234742');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (146,6,146000,1,'AC201711234743');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (147,7,147000,1,'AC201711234744');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (148,1,148000,1,'AC201711234745');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (149,2,149000,1,'AC201711234746');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (150,3,150000,1,'AC201711234747');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (151,4,151000,1,'AC201711234748');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (152,5,152000,1,'AC201711234749');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (153,6,153000,1,'AC201711234750');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (154,7,154000,1,'AC201711234751');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (155,1,155000,1,'AC201711234752');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (156,2,156000,1,'AC201711234753');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (157,3,157000,1,'AC201711234754');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (158,4,158000,1,'AC201711234755');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (159,5,159000,1,'AC201711234756');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (160,6,160000,1,'AC201711234757');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (161,7,161000,1,'AC201711234758');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (162,1,162000,1,'AC201711234759');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (163,2,163000,1,'AC201711234760');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (164,3,164000,1,'AC201711234761');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (165,4,165000,1,'AC201711234762');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (166,5,166000,1,'AC201711234763');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (167,6,167000,1,'AC201711234764');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (168,7,168000,1,'AC201711234765');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (169,1,169000,1,'AC201711234766');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (170,2,170000,1,'AC201711234767');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (171,3,171000,1,'AC201711234768');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (172,4,172000,1,'AC201711234769');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (173,5,173000,1,'AC201711234770');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (174,6,174000,1,'AC201711234771');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (175,7,175000,1,'AC201711234772');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (176,1,176000,1,'AC201711234773');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (177,2,177000,1,'AC201711234774');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (178,3,178000,1,'AC201711234775');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (179,4,179000,1,'AC201711234776');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (180,5,180000,1,'AC201711234777');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (181,6,181000,1,'AC201711234778');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (182,7,182000,1,'AC201711234779');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (183,1,183000,1,'AC201711234780');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (184,2,184000,1,'AC201711234781');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (185,3,185000,1,'AC201711234782');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (186,4,186000,1,'AC201711234783');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (187,5,187000,1,'AC201711234784');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (188,6,188000,1,'AC201711234785');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (189,7,189000,1,'AC201711234786');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (190,1,190000,1,'AC201711234787');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (191,2,191000,1,'AC201711234788');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (192,3,192000,1,'AC201711234789');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (193,4,193000,1,'AC201711234790');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (194,5,194000,1,'AC201711234791');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (195,6,195000,1,'AC201711234792');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (196,7,196000,1,'AC201711234793');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (197,1,197000,1,'AC201711234794');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (198,2,198000,1,'AC201711234795');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (199,3,199000,1,'AC201711234796');
insert into aplica (apliid, pofid, aspid, apliprioridad,aplisnp) values (200,4,200000,1,'AC201711234797');

insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234598','2017.2', 'Daria Lourenco', 'CC',1000,180,84,29,68,74,76);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234599','2017.2', 'Annmaria Dabney', 'CC',2000,180,39,57,72,35,91);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234600','2017.2', 'Gerrilee McCobb', 'CC',3000,180,87,56,36,30,51);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234601','2017.2', 'Frasco Guarin', 'CC',4000,180,33,77,83,23,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234602','2017.2', 'Valaree Butteris', 'CC',5000,180,25,23,69,65,85);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234603','2017.2', 'Raynell Klausewitz', 'CC',6000,180,75,66,24,36,76);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234604','2017.2', 'Charlene Clinning', 'CC',7000,180,29,34,23,79,38);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234605','2017.2', 'Vernice Stansall', 'CC',8000,180,77,37,32,99,72);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234606','2017.2', 'Hephzibah Dielhenn', 'CC',9000,180,32,77,31,79,82);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234607','2017.2', 'Ebenezer Bullivent', 'CC',10000,180,82,47,34,68,70);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234608','2017.2', 'Stormy Kosel', 'CC',11000,180,32,33,61,32,36);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234609','2017.2', 'Carmel Doumenc', 'CC',12000,180,45,61,91,45,72);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234610','2017.2', 'Alberto Kernock', 'CC',13000,180,39,30,72,74,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234611','2017.2', 'Vivia McAlpine', 'CC',14000,180,89,26,79,53,32);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234612','2017.2', 'Iris Balcombe', 'CC',15000,180,39,99,69,81,84);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234613','2017.2', 'Eugen Biskup', 'CC',16000,180,54,88,50,49,61);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234614','2017.2', 'Alisander Tribell', 'CC',17000,180,99,38,87,42,83);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234615','2017.2', 'Alexis Dennerley', 'CC',18000,180,55,43,26,47,97);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234616','2017.2', 'Avie Brocket', 'CC',19000,180,87,28,59,31,81);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234617','2017.2', 'Betti Gorick', 'CC',20000,180,77,53,66,45,45);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234618','2017.2', 'Sheff Lamborn', 'CC',21000,180,88,62,43,93,75);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234619','2017.2', 'Merl Sidney', 'CC',22000,180,54,76,36,98,33);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234620','2017.2', 'Alayne Slograve', 'CC',23000,180,66,48,77,46,80);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234621','2017.2', 'Mickey Geeve', 'CC',24000,180,50,93,74,96,30);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234622','2017.2', 'Caresa Emmitt', 'CC',25000,180,80,50,46,62,28);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234623','2017.2', 'Thibaud Bursnall', 'CC',26000,180,44,53,80,68,87);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234624','2017.2', 'Elnore Gossage', 'CC',27000,180,97,52,83,74,33);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234625','2017.2', 'Colline Bickers', 'CC',28000,180,76,99,45,47,44);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234626','2017.2', 'Lizette Duddell', 'CC',29000,180,27,93,70,95,37);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234627','2017.2', 'Cora Miguel', 'CC',30000,180,30,87,67,71,66);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234628','2017.2', 'Charil Coweuppe', 'CC',31000,180,21,53,94,25,64);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234629','2017.2', 'Jerry Aingell', 'CC',32000,180,42,47,42,88,49);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234630','2017.2', 'Elvis Nolleau', 'CC',33000,180,42,84,93,91,73);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234631','2017.2', 'Adolf Holdey', 'CC',34000,180,95,49,94,55,48);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234632','2017.2', 'Axel Garfath', 'CC',35000,180,33,47,29,74,70);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234633','2017.2', 'Wright Humby', 'CC',36000,180,59,62,50,99,81);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234634','2017.2', 'Martin Keson', 'CC',37000,180,62,58,92,100,94);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234635','2017.2', 'Bogart Harbertson', 'CC',38000,180,55,50,21,58,36);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234636','2017.2', 'Winni Bertomier', 'CC',39000,180,24,83,82,39,20);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234637','2017.2', 'Lawrence Thomas', 'CC',40000,180,62,58,100,88,42);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234638','2017.2', 'Emory Timny', 'CC',41000,180,88,70,56,26,85);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234639','2017.2', 'Alys McComiskie', 'CC',42000,180,40,73,27,70,51);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234640','2017.2', 'Stanly Burgyn', 'CC',43000,180,71,94,80,40,45);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234641','2017.2', 'Lanny Dovidaitis', 'CC',44000,180,56,75,73,65,87);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234642','2017.2', 'Rowena Facchini', 'CC',45000,180,32,72,27,57,92);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234643','2017.2', 'Ediva Dogg', 'CC',46000,180,21,72,43,71,86);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234644','2017.2', 'Ileana Grange', 'CC',47000,180,75,82,40,66,63);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234645','2017.2', 'Siana Landre', 'CC',48000,180,97,50,58,23,94);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234646','2017.2', 'Claudianus McCarrick', 'CC',49000,180,76,68,64,62,56);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234647','2017.2', 'Adolph Nequest', 'CC',50000,180,52,75,65,47,54);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234648','2017.2', 'Josy Cesco', 'CC',51000,180,41,79,65,94,68);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234649','2017.2', 'Clemmie Feirn', 'CC',52000,180,31,87,59,34,59);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234650','2017.2', 'Isabella Pretorius', 'CC',53000,180,49,37,66,85,75);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234651','2017.2', 'Adelle Josephi', 'CC',54000,180,96,67,67,46,26);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234652','2017.2', 'Muriel Duddy', 'CC',55000,180,34,58,99,46,40);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234653','2017.2', 'Neilla Sunderland', 'CC',56000,180,22,38,100,41,77);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234654','2017.2', 'Inglebert Pilmoor', 'CC',57000,180,64,93,75,62,43);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234655','2017.2', 'Nettle Jiggens', 'CC',58000,180,74,77,63,80,99);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234656','2017.2', 'Othella Blakeslee', 'CC',59000,180,33,22,31,66,85);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234657','2017.2', 'Kimbra Heugh', 'CC',60000,180,95,96,48,47,28);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234658','2017.2', 'Evaleen Goodsir', 'CC',61000,180,68,82,29,71,69);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234659','2017.2', 'Spike Perutto', 'CC',62000,180,30,82,41,52,44);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234660','2017.2', 'Aubrey Ducarne', 'CC',63000,180,40,45,64,20,63);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234661','2017.2', 'Levey Merioth', 'CC',64000,180,31,33,94,53,51);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234662','2017.2', 'Gussi Pearsey', 'CC',65000,180,100,44,100,24,55);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234663','2017.2', 'Dick Robic', 'CC',66000,180,61,40,62,64,83);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234664','2017.2', 'Kym Eastlake', 'CC',67000,180,36,67,41,88,37);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234665','2017.2', 'Xaviera Carrick', 'CC',68000,180,53,43,72,50,39);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234666','2017.2', 'Sallyanne Lacaze', 'CC',69000,180,93,94,82,85,56);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234667','2017.2', 'Danyette Meynell', 'CC',70000,180,73,48,38,90,53);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234668','2017.2', 'Laurent Bartels', 'CC',71000,180,81,32,42,74,63);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234669','2017.2', 'Winnifred Cattle', 'CC',72000,180,78,97,64,53,75);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234670','2017.2', 'Jaquenette Muat', 'CC',73000,180,41,49,76,81,56);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234671','2017.2', 'Des Risson', 'CC',74000,180,40,51,22,37,39);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234672','2017.2', 'Charleen Milius', 'CC',75000,180,93,55,63,34,43);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234673','2017.2', 'Dalt Valentinetti', 'CC',76000,180,84,57,51,78,60);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234674','2017.2', 'Sydel Sabine', 'CC',77000,180,78,35,95,56,38);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234675','2017.2', 'Bunny Bottomore', 'CC',78000,180,36,60,77,38,85);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234676','2017.2', 'Kilian Downey', 'CC',79000,180,29,42,75,27,63);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234677','2017.2', 'Arel Urwin', 'CC',80000,180,23,40,74,55,88);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234678','2017.2', 'Christy MacAllan', 'CC',81000,180,80,32,57,45,80);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234679','2017.2', 'Claudine Guerrazzi', 'CC',82000,180,57,76,64,20,59);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234680','2017.2', 'Bernadene Hurry', 'CC',83000,180,83,80,62,93,50);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234681','2017.2', 'Wildon McMains', 'CC',84000,180,100,72,91,94,74);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234682','2017.2', 'Lockwood Langsbury', 'CC',85000,180,33,37,51,66,49);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234683','2017.2', 'Basilius Flarity', 'CC',86000,180,64,45,81,35,69);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234684','2017.2', 'Orren McLurg', 'CC',87000,180,91,56,58,25,86);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234685','2017.2', 'Mylo Sewill', 'CC',88000,180,72,52,63,95,100);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234686','2017.2', 'Bobby China', 'CC',89000,180,25,23,90,75,73);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234687','2017.2', 'Lisette Elcoux', 'CC',90000,180,99,48,93,65,86);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234688','2017.2', 'Thayne Bend', 'CC',91000,180,91,71,67,98,27);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234689','2017.2', 'Jeff Butland', 'CC',92000,180,43,23,68,79,31);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234690','2017.2', 'Allan Thoday', 'CC',93000,180,43,97,88,82,36);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234691','2017.2', 'Franklin Garci', 'CC',94000,180,86,94,34,76,21);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234692','2017.2', 'Raphael Andriulis', 'CC',95000,180,28,68,82,53,24);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234693','2017.2', 'Shellie Kellough', 'CC',96000,180,56,63,47,42,84);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234694','2017.2', 'Mariann Petegre', 'CC',97000,180,46,21,43,94,51);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234695','2017.2', 'Valaria Kilbane', 'CC',98000,180,91,98,65,28,84);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234696','2017.2', 'Lisbeth Trainor', 'CC',99000,180,55,96,98,78,49);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234697','2017.2', 'Jamesy Borit', 'CC',100000,180,32,24,70,68,25);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234698','2017.2', 'Rivy Capin', 'CC',101000,180,71,39,57,34,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234699','2017.2', 'Babs Vinau', 'CC',102000,180,22,35,41,65,27);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234700','2017.2', 'Dyan Choak', 'CC',103000,180,46,28,21,93,29);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234701','2017.2', 'Mel Gudgion', 'CC',104000,180,48,27,32,87,41);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234702','2017.2', 'Nickolaus Romaine', 'CC',105000,180,22,87,62,87,60);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234703','2017.2', 'Karmen McGowran', 'CC',106000,180,66,36,86,64,53);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234704','2017.2', 'Emmanuel Learoyde', 'CC',107000,180,58,36,51,56,57);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234705','2017.2', 'Netti Myner', 'CC',108000,180,89,99,76,46,72);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234706','2017.2', 'Kerry Kevane', 'CC',109000,180,40,22,66,22,51);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234707','2017.2', 'Jillian Becker', 'CC',110000,180,93,29,84,81,38);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234708','2017.2', 'Gardy Primo', 'CC',111000,180,75,28,57,37,87);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234709','2017.2', 'Pavlov Havik', 'CC',112000,180,97,23,21,21,50);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234710','2017.2', 'Budd Dundridge', 'CC',113000,180,20,88,32,84,27);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234711','2017.2', 'Philly Ubsdell', 'CC',114000,180,25,49,62,87,74);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234712','2017.2', 'Nadine Middler', 'CC',115000,180,86,33,56,91,73);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234713','2017.2', 'Mickie Caisley', 'CC',116000,180,85,40,44,55,44);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234714','2017.2', 'Denny Grafton-Herbert', 'CC',117000,180,66,81,68,81,79);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234715','2017.2', 'Pam Ilyunin', 'CC',118000,180,39,28,33,64,20);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234716','2017.2', 'Silvana Di Franceschi', 'CC',119000,180,40,41,75,24,93);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234717','2017.2', 'Lydon Antill', 'CC',120000,180,97,100,54,27,87);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234718','2017.2', 'Kare Fines', 'CC',121000,180,64,62,27,64,49);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234719','2017.2', 'Licha McCowan', 'CC',122000,180,41,29,94,54,92);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234720','2017.2', 'Jemie Satterfitt', 'CC',123000,180,66,24,50,61,21);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234721','2017.2', 'Irvin Ciobutaro', 'CC',124000,180,70,89,38,56,91);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234722','2017.2', 'Ruben Radbone', 'CC',125000,180,47,95,76,90,73);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234723','2017.2', 'Collen Caplan', 'CC',126000,180,56,46,50,29,92);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234724','2017.2', 'Arabel Lubbock', 'CC',127000,180,49,30,72,71,27);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234725','2017.2', 'Lamond Facer', 'CC',128000,180,20,54,58,30,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234726','2017.2', 'Fidela Tiebe', 'CC',129000,180,87,35,41,59,35);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234727','2017.2', 'Greta Toop', 'CC',130000,180,79,46,88,62,51);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234728','2017.2', 'Moises Lynd', 'CC',131000,180,23,97,58,56,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234729','2017.2', 'Harwilll Dreini', 'CC',132000,180,68,69,71,98,36);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234730','2017.2', 'Truman Phant', 'CC',133000,180,37,35,28,91,31);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234731','2017.2', 'Fidelia Cobelli', 'CC',134000,180,83,36,74,98,75);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234732','2017.2', 'Bennett D''Ambrogio', 'CC',135000,180,50,31,24,90,91);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234733','2017.2', 'Nikos Criple', 'CC',136000,180,55,95,40,54,98);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234734','2017.2', 'Eachelle Stickells', 'CC',137000,180,46,59,72,56,73);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234735','2017.2', 'Tracie Baythrop', 'CC',138000,180,48,91,71,71,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234736','2017.2', 'Brenna Northrop', 'CC',139000,180,45,93,51,46,32);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234737','2017.2', 'Salem Lyford', 'CC',140000,180,77,88,62,69,36);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234738','2017.2', 'Melodee Colebourne', 'CC',141000,180,25,85,65,25,25);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234739','2017.2', 'Garvin Shelf', 'CC',142000,180,20,23,79,36,23);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234740','2017.2', 'Freddy Giacomini', 'CC',143000,180,99,31,97,92,58);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234741','2017.2', 'Serge Crisford', 'CC',144000,180,71,76,46,81,88);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234742','2017.2', 'Bjorn Oris', 'CC',145000,180,56,30,97,22,62);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234743','2017.2', 'Addia Dunham', 'CC',146000,180,93,25,21,57,48);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234744','2017.2', 'Joceline Thalmann', 'CC',147000,180,49,97,78,26,97);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234745','2017.2', 'Sharline Purselow', 'CC',148000,180,33,71,50,86,32);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234746','2017.2', 'Casi Diament', 'CC',149000,180,59,91,60,73,72);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234747','2017.2', 'Katrinka Hurst', 'CC',150000,180,83,79,45,27,82);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234748','2017.2', 'Stephine Lamburn', 'CC',151000,180,98,23,84,94,45);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234749','2017.2', 'Issiah Emma', 'CC',152000,180,85,62,84,21,54);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234750','2017.2', 'Aprilette Ciciari', 'CC',153000,180,48,26,29,29,57);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234751','2017.2', 'Lamond Saill', 'CC',154000,180,31,74,61,72,91);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234752','2017.2', 'Opalina Elvin', 'CC',155000,180,86,50,54,39,33);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234753','2017.2', 'Leah Larkby', 'CC',156000,180,97,51,70,68,90);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234754','2017.2', 'Don Marcoolyn', 'CC',157000,180,93,48,42,73,65);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234755','2017.2', 'Demetris Pagel', 'CC',158000,180,77,46,50,42,64);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234756','2017.2', 'Aurie Diamant', 'CC',159000,180,27,86,100,78,55);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234757','2017.2', 'Woodrow Slocombe', 'CC',160000,180,83,63,80,99,66);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234758','2017.2', 'Peirce Hacaud', 'CC',161000,180,62,93,96,34,25);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234759','2017.2', 'Lucio Duddy', 'CC',162000,180,48,78,44,69,45);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234760','2017.2', 'Rooney Braven', 'CC',163000,180,62,53,99,99,31);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234761','2017.2', 'Joleen Dressel', 'CC',164000,180,88,58,75,96,60);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234762','2017.2', 'Douglas Roberson', 'CC',165000,180,90,37,30,98,61);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234763','2017.2', 'Dorey Tompkiss', 'CC',166000,180,31,36,84,100,98);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234764','2017.2', 'Vin Keirl', 'CC',167000,180,91,70,83,55,96);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234765','2017.2', 'Delano Marjoram', 'CC',168000,180,92,66,29,56,92);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234766','2017.2', 'Royce Zanetto', 'CC',169000,180,31,100,48,85,75);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234767','2017.2', 'Imogen Pratton', 'CC',170000,180,76,51,82,48,31);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234768','2017.2', 'Caleb Tape', 'CC',171000,180,40,96,84,36,100);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234769','2017.2', 'Annabella Zarb', 'CC',172000,180,66,44,21,73,39);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234770','2017.2', 'Inness Cantera', 'CC',173000,180,79,76,35,39,85);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234771','2017.2', 'Johnnie Kearford', 'CC',174000,180,99,91,75,28,91);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234772','2017.2', 'Hamlin Klimushev', 'CC',175000,180,97,91,80,55,87);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234773','2017.2', 'Florentia Kall', 'CC',176000,180,80,31,62,45,57);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234774','2017.2', 'Rosalind Pughsley', 'CC',177000,180,98,29,22,53,84);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234775','2017.2', 'Rockwell Londer', 'CC',178000,180,30,49,76,79,43);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234776','2017.2', 'Paquito Mayor', 'CC',179000,180,53,49,25,86,38);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234777','2017.2', 'Koren Sheppey', 'CC',180000,180,98,90,50,49,76);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234778','2017.2', 'Kay Fudge', 'CC',181000,180,51,88,67,38,74);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234779','2017.2', 'Halie Sutherel', 'CC',182000,180,62,72,44,73,56);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234780','2017.2', 'Abran Wrixon', 'CC',183000,180,59,80,46,79,62);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234781','2017.2', 'Raffaello Nanni', 'CC',184000,180,51,35,85,94,59);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234782','2017.2', 'Ludovico Baglin', 'CC',185000,180,32,42,84,80,28);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234783','2017.2', 'L;urette Keller', 'CC',186000,180,62,78,29,39,65);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234784','2017.2', 'Wolfgang Sumpton', 'CC',187000,180,78,23,100,60,89);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234785','2017.2', 'Kerby Sissons', 'CC',188000,180,83,74,71,91,56);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234786','2017.2', 'Arney Duny', 'CC',189000,180,51,84,54,34,69);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234787','2017.2', 'Lissi Littrell', 'CC',190000,180,49,73,52,42,24);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234788','2017.2', 'Bram Loseke', 'CC',191000,180,43,86,96,96,79);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234789','2017.2', 'Kay Rosenshine', 'CC',192000,180,86,57,24,62,84);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234790','2017.2', 'Sheila-kathryn Guess', 'CC',193000,180,73,83,25,73,68);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234791','2017.2', 'Karin Hegley', 'CC',194000,180,88,53,53,87,61);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234792','2017.2', 'Consolata Pluvier', 'CC',195000,180,29,39,86,36,83);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234793','2017.2', 'Estel Blees', 'CC',196000,180,57,50,50,65,39);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234794','2017.2', 'Dorry Beveridge', 'CC',197000,180,65,90,21,23,36);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234795','2017.2', 'Maxy Joly', 'CC',198000,180,95,36,63,36,93);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234796','2017.2', 'Eleonore Moseby', 'CC',199000,180,28,24,85,26,50);
insert into resultadoicfes (ressnp, resperiodo, resnombreest, restipodocest, residest, resglobal, reslecturacritica, resmatematicas, rescsociales, rescnaturales, resingles) values ('AC201711234797','2017.2', 'Raymond Whitear', 'CC',200000,180,69,37,63,47,46);
