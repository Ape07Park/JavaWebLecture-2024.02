SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE board
(
	bid int NOT NULL AUTO_INCREMENT,
	title varchar(256) NOT NULL,
	content varchar(4000),
	uid varchar(12) NOT NULL,
	modTime datetime DEFAULT CURRENT_TIMESTAMP,
	isDeleted int DEFAULT 0,
	viewCount int DEFAULT 0,
	replyCount int DEFAULT 0,
	PRIMARY KEY (bid)
);


CREATE TABLE likes
(
	lid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	bid int NOT NULL,
	clickTime datetime DEFAULT NOW(), SYSDATE(),
	PRIMARY KEY (lid)
);


CREATE TABLE orders
(
	oid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	totalPrice int DEFAULT 0,
	orderTime datetime DEFAULT NOW(), SYSDATE(),
	status int DEFAULT 0,
	PRIMARY KEY (oid)
);


CREATE TABLE product
(
	pid int NOT NULL AUTO_INCREMENT,
	category varchar(32) NOT NULL,
	pname varchar(40) NOT NULL,
	price int DEFAULT 0 NOT NULL,
	description varchar(256),
	pimage varchar(64),
	PRIMARY KEY (pid),
	UNIQUE (pname)
);


CREATE TABLE reply
(
	rid int NOT NULL AUTO_INCREMENT,
	comment varchar(256) NOT NULL,
	regTime datetime DEFAULT CURRENT_TIMESTAMP,
	uid varchar(12) NOT NULL,
	bid int NOT NULL,
	PRIMARY KEY (rid)
);


CREATE TABLE sales
(
	sid int NOT NULL AUTO_INCREMENT,
	oid int NOT NULL,
	pid int NOT NULL,
	quantity int DEFAULT 0,
	PRIMARY KEY (sid)
);


CREATE TABLE users
(
	uid varchar(12) NOT NULL,
	pwd char(60) NOT NULL,
	uname varchar(16) NOT NULL,
	email varchar(32),
	regDate date DEFAULT (CURRENT_DATE),
	isDeleted int DEFAULT 0,
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE likes
	ADD FOREIGN KEY (bid)
	REFERENCES board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE reply
	ADD FOREIGN KEY (bid)
	REFERENCES board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sales
	ADD FOREIGN KEY (oid)
	REFERENCES orders (oid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE sales
	ADD FOREIGN KEY (pid)
	REFERENCES product (pid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE board
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE likes
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE orders
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE reply
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



