CREATE TABLE users (
    username varchar2(50) NOT NULL PRIMARY KEY,
    password varchar2(100) NOT NULL,
    enabled char(1) DEFAULT '1');

CREATE TABLE authorities (
    username varchar2(50) NOT NULL,
    authority varchar2(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username));    
    
CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);    

INSERT INTO users (username, password) values ('user00', 'pw00');
INSERT INTO users (username, password) values ('member00', 'pw00');
INSERT INTO users (username, password) values ('admin00', 'pw00');

INSERT INTO authorities (username, authority) values ('user00', 'ROLE_USER');
INSERT INTO authorities (username, authority) values ('member00', 'ROLE_MANAGER');
INSERT INTO authorities (username, authority) values ('admin00', 'ROLE_MANAGER');
INSERT INTO authorities (username, authority) values ('admin00', 'ROLE_ADMIN');

SELECT * FROM users;
SELECT * FROM authorities;

CREATE TABLE member_tbl (
    userid varchar2(50) NOT NULL PRIMARY KEY,
    userpw varchar2(100) NOT NULL,
    username varchar2(100) NOT NULL,
    regdate date DEFAULT SYSDATE,
    updatedate date DEFAULT SYSDATE,
    enabled char(1) DEFAULT '1'
);

CREATE TABLE member_auth (
    userid varchar2(50) NOT NULL PRIMARY KEY,
    auth varchar2(50) NOT NULL,
    CONSTRAINT fk_member_auth FOREIGN KEY(userid) REFERENCES member_tbl(userid)
);

SELECT * FROM member_tbl;
SELECT * FROM member_auth;

-- 자동 로그인 
CREATE TABLE persistent_logins (
    username varchar(64) NOT NULL,
    series  varchar(64) PRIMARY KEY,
    token varchar(64) NOT NULL, 
    last_used timestamp NOT NULL
);

SELECT * FROM persistent_logins;

commit;