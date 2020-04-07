
drop table if exists users;

CREATE TABLE users(

   NAME  string PRIMARY KEY    NOT NULL,
   TYPE     string NOT  NULL,
   PASSWORD string Not null,
   question string not NULL,
   answer string not NULL
);

insert into users values( "admin", "superuser", "5f4dcc3b5aa765d61d8327deb882cf99","what is your age","22");



drop table if exists zips;
CREATE TABLE zips(
   code INTEGER PRIMARY KEY   NOT NULL,
   NAME     string    NOT NULL,
   TYPE     string NOT  NULL,
   LENGTH FLOAT NOT NULL,
   QUANTITY INTEGER NOT NULL,
   PRICE FLOAT NOT NULL,
   DESCRIPTION string

);


insert into zips values(00,"none","none",0,0,0,"none");


drop table if EXISTS dailysales;
CREATE  TABLE  dailysales (
   id INTEGER PRIMARY key autoincrement ,
   code INTEGER not null,
   customer string not null,
   QUANTITY INTEGER NOT NULL,
   tax INTEGER DEFAULT 0,
   discount INTEGER DEFAULT 0,
   total FLOAT DEFAULT 0,
   today string not null,
   FOREIGN key(code) REFERENCES zips(code)

);

insert into dailysales values(null,00,"none",0,0,0,0,"none");
UPDATE SQLITE_SEQUENCE SET seq = 100100 WHERE name = "dailysales";

