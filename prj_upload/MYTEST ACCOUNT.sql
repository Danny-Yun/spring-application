CREATE TABLE img_tbl(
    uuid VARCHAR(100) NOT NULL,
    uploadPath VARCHAR2(200) NOT NULL,
    fileName VARCHAR2(100) NOT NULL,
    fileType CHAR(1) DEFAULT 'I',
    b_no number(10, 0)
);

ALTER TABLE img_tbl ADD CONSTRAINT pk_img PRIMARY KEY (uuid);
ALTER TABLE img_tbl ADD CONSTRAINT fk_board_img FOREIGN KEY (b_no) REFERENCES board_tbl(b_no);

SELECT * FROM img_tbl;

COMMIT;