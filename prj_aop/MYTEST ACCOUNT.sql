CREATE TABLE tbl_test1( col1 VARCHAR2(50));
CREATE TABLE tbl_test2( col2 VARCHAR2(5));

commit;

SELECT * FROM tbl_test1;
SELECT * FROM tbl_test2;

DROP TABLE tbl_test1;