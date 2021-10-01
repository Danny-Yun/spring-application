CREATE TABLE reply_tbl(
    r_no number (10, 0),
    b_no number (10, 0) NOT NULL,
    reply VARCHAR2 (1000) NOT NULL,
    replyer VARCHAR2 (50) NOT NULL,
    replyDate DATE DEFAULT sysdate,
    updateDate DATE DEFAULT sysdate
    );

CREATE SEQUENCE reply_num;

ALTER TABLE reply_tbl ADD CONSTRAINT pk_reply PRIMARY KEY (r_no);

ALTER TABLE reply_tbl ADD CONSTRAINT fk_reply FOREIGN KEY (b_no) REFERENCES board_tbl(b_no);

-- boardTBL의 bno에 프라이머리키 안걸어서 생긴 오류입니다. BOARD_TBL의 제약조건 보시면 없어요
ALTER TABLE board_tbl ADD CONSTRAINT pk_board PRIMARY KEY (b_no);

SELECT * FROM reply_tbl;

COMMIT;