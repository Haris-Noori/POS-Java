
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


drop table if EXISTS invoices;
create table invoices(
   id INTEGER Primary key not null,
   total FLOAT DEFAULT 0,
   tax float DEFAULT 0,
   discount float DEFAULT 0,
   today string not null,
customer string not null,
username string not null
);



drop table if EXISTS dailysales;
CREATE  TABLE  dailysales (
   id INTEGER PRIMARY key autoincrement ,
   code INTEGER not null,
NAME  string    NOT NULL,
   TYPE     string NOT  NULL,
   LENGTH FLOAT NOT NULL,
   QUANTITY INTEGER NOT NULL,
   PRICE FLOAT NOT NULL,
   DESCRIPTION string,
   invoiceid INTEGER not null,
   FOREIGN key(code) REFERENCES zips(code)
FOREIGN key(invoiceid) REFERENCES invoices(id)
On delete cascade
	
);

insert into dailysales values(null,0,"none","none",0,0,0,"none",0);
UPDATE SQLITE_SEQUENCE SET seq = 100100 WHERE name = "dailysales";

PRAGMA foreign_keys = ON;
