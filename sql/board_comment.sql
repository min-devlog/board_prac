CREATE TABLE board_comment (
  comment_id NUMBER(10) PRIMARY KEY,      -- 댓글 ID
  board_id   NUMBER(10) NOT NULL,         -- 게시글 ID (board 테이블 참조)
  content    CLOB NOT NULL,               -- 댓글 내용
  writer     VARCHAR2(100) NOT NULL,      -- 작성자
  created_at TIMESTAMP DEFAULT SYSTIMESTAMP, -- 작성일시
  updated_at TIMESTAMP DEFAULT SYSTIMESTAMP  -- 수정일시
);


DROP TABLE board_comment;

-- bbs_id 외래키 연결
ALTER TABLE board_comment
  ADD CONSTRAINT board_comment_board_id_fk FOREIGN KEY (board_id)
  REFERENCES board (board_id);

-- 시퀀스 생성
CREATE SEQUENCE comment_comment_id_seq;

-- 데이터 확인
SELECT * FROM board_comment;


INSERT INTO board_comment (comment_id, board_id, content, writer)
VALUES (1, 21, '첫 번째 댓글입니다.', '테스터1');

SELECT * FROM board_comment WHERE board_id = 21;

UPDATE board_comment SET content = '수정된 댓글입니다.' WHERE comment_id = 1;
SELECT * FROM board_comment;

DELETE FROM board_comment WHERE comment_id = 1;
SELECT * FROM board_comment;


INSERT INTO board_comment (comment_id, board_id, content, writer)
VALUES (comment_comment_id_seq.nextval, 21, '다시 댓글달아요~.', '테스터1');