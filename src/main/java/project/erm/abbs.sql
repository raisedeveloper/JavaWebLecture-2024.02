SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS anniversary;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS reply;
DROP TABLE IF EXISTS Board;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS users;




/* Create Tables */

CREATE TABLE anniversary
(
	aid int NOT NULL,
	aname varchar(20) NOT NULL,
	aDate char(8) NOT NULL,
	isHollyday int DEFAULT 0,
	uid varchar(12) NOT NULL,
	PRIMARY KEY (aid)
);


CREATE TABLE Board
(
	bid int NOT NULL AUTO_INCREMENT,
	uid varchar(12) NOT NULL,
	clickTime datetime NOT NULL,
	uid varchar(12) NOT NULL,
	title varchar(256),
	viewCount int,
	replyCount int,
	isDeleted int,
	likeCount int,
	files varchar(512),
	content varchar(4000),
	PRIMARY KEY (bid)
);


CREATE TABLE likes
(
	lid  NOT NULL,
	uid varchar(12) NOT NULL,
	bid int NOT NULL,
	value int,
	PRIMARY KEY (lid)
);


CREATE TABLE reply
(
	rid  NOT NULL,
	uid varchar(12) NOT NULL,
	bid int NOT NULL,
	bid int NOT NULL,
	uid varchar(12) NOT NULL,
	content varchar(256) NOT NULL,
	regTime datetime DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (rid)
);


CREATE TABLE schedule
(
	sid int NOT NULL,
	uid varchar(12) NOT NULL,
	sdate char(8) NOT NULL,
	 title char(40) NOT NULL,
	place varchar(40),
	endTime char(4),
	isImpoertant int DEFAULT 0,
	memo varchar(100),
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
	profile varchar(40),
	github varchar(40),
	insta varchar(40),
	location varchar(20),
	PRIMARY KEY (uid)
);



/* Create Foreign Keys */

ALTER TABLE likes
	ADD FOREIGN KEY (bid)
	REFERENCES Board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE reply
	ADD FOREIGN KEY (bid)
	REFERENCES Board (bid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE anniversary
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE Board
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


ALTER TABLE reply
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE schedule
	ADD FOREIGN KEY (uid)
	REFERENCES users (uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



