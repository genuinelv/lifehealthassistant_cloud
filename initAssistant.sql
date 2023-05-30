create table user 
( id VARCHAR(30) not null, 
password VARCHAR(16) not null,
name VARCHAR(8) not null,
sex VARCHAR(10),
photo VARCHAR(255),
birthday DATE,
PRIMARY KEY(id));

create table useremail
( id VARCHAR(30) not null, 
email VARCHAR(50),
emailstate INT(1),
existdate DATETIME,
checkcode VARCHAR(10),
PRIMARY KEY(id));