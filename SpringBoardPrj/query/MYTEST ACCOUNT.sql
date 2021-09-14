/* 오라클은 auto_increment가 없으므로 board_num이라는 시퀀스를 만들면 0이 저장된다.
   이후 primary key가 들어갈 자리에 board_num.nextval이라고 기입하면 실행할 때마다 
   1씩 증가된 새로운 값을 그 위치에 넣어준다. */
CREATE SEQUENCE board_num;   

CREATE TABLE board_tbl (
    b_no number(10, 0),
    b_title varchar2(200) not null,
    b_content varchar2(2000) not null,
    b_writer varchar2(50) not null,
    b_regdate date default sysdate,
    b_updatedate date default sysdate
);

ALTER TABLE board_tbl ADD CONSTRAINT pk_board primary key(bno);

INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '테스트1', '테스트111','글쓴이1');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '테스트2', '테스트222','글쓴이2');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '테스트3', '테스트333','글쓴이3');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '테스트4', '테스트444','글쓴이4');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '테스트5', '테스트555','글쓴이5');

SELECT * FROM board_tbl;

commit;
