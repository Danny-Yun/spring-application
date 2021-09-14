/* ����Ŭ�� auto_increment�� �����Ƿ� board_num�̶�� �������� ����� 0�� ����ȴ�.
   ���� primary key�� �� �ڸ��� board_num.nextval�̶�� �����ϸ� ������ ������ 
   1�� ������ ���ο� ���� �� ��ġ�� �־��ش�. */
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

INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '�׽�Ʈ1', '�׽�Ʈ111','�۾���1');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '�׽�Ʈ2', '�׽�Ʈ222','�۾���2');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '�׽�Ʈ3', '�׽�Ʈ333','�۾���3');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '�׽�Ʈ4', '�׽�Ʈ444','�۾���4');
INSERT INTO board_tbl (b_no, b_title, b_content, b_writer) values (board_num.nextval, '�׽�Ʈ5', '�׽�Ʈ555','�۾���5');

SELECT * FROM board_tbl;

commit;
