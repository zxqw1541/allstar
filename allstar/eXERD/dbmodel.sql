DROP DATABASE allstar;

CREATE DATABASE allstar;

USE allstar;

-- 종목게시판
DROP TABLE IF EXISTS BOARD RESTRICT;

-- 대회
DROP TABLE IF EXISTS COMPETITION RESTRICT;

-- 회원
DROP TABLE IF EXISTS MEMBER RESTRICT;

-- 팀
DROP TABLE IF EXISTS TEAM RESTRICT;

-- 보드댓글
DROP TABLE IF EXISTS BOAR_COMM RESTRICT;

-- 팀참여
DROP TABLE IF EXISTS JOIN_TEAM RESTRICT;

-- 대회참여
DROP TABLE IF EXISTS JOIN_COMP RESTRICT;

-- 종목
DROP TABLE IF EXISTS EVENT RESTRICT;

-- 선호종목
DROP TABLE IF EXISTS LIKE_EVENT RESTRICT;

-- 사진
DROP TABLE IF EXISTS PHOTO RESTRICT;

-- 대회댓글
DROP TABLE IF EXISTS COMP_COMM RESTRICT;

-- 종목게시판
CREATE TABLE BOARD (
	bno     INTEGER     NOT NULL COMMENT '종목게시물번호', -- 종목게시물번호
	eno     INTEGER     NOT NULL COMMENT '종목번호', -- 종목번호
	mno     INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
	title   VARCHAR(50) NOT NULL COMMENT '제목', -- 제목
	content TEXT        NOT NULL COMMENT '내용', -- 내용
	cre_dt  DATETIME    NOT NULL COMMENT '작성날짜', -- 작성날짜
	views   INTEGER     NOT NULL DEFAULT 0 COMMENT '조회수' -- 조회수
)
COMMENT '종목게시판';

-- 종목게시판
ALTER TABLE BOARD
	ADD CONSTRAINT PK_BOARD -- 종목게시판 기본키
		PRIMARY KEY (
			bno -- 종목게시물번호
		);

ALTER TABLE BOARD
	MODIFY COLUMN bno INTEGER NOT NULL AUTO_INCREMENT COMMENT '종목게시물번호';

-- 대회
CREATE TABLE COMPETITION (
	cno      INTEGER      NOT NULL COMMENT '대회번호', -- 대회번호
	eno      INTEGER      NOT NULL COMMENT '종목번호', -- 종목번호
	tno      INTEGER      NOT NULL COMMENT '생성팀번호', -- 생성팀번호
	name     VARCHAR(50)  NOT NULL COMMENT '대회명', -- 대회명
	team_num INTEGER      NOT NULL DEFAULT 0 COMMENT '최대팀수', -- 최대팀수
	join_num INTEGER      NOT NULL DEFAULT 0 COMMENT '현참여팀수', -- 현참여팀수
	cost     INTEGER      NOT NULL DEFAULT 0 COMMENT '참가비', -- 참가비
	sdt      DATE         NOT NULL COMMENT '시작일', -- 시작일
	edt      DATE         NOT NULL COMMENT '종료일', -- 종료일
	rsdt     DATE         NOT NULL COMMENT '모집시작일', -- 모집시작일
	redt     DATETIME     NOT NULL COMMENT '모집종료일', -- 모집종료일
	content  TEXT         NOT NULL COMMENT '내용', -- 내용
	poster   VARCHAR(255) NOT NULL COMMENT '포스터', -- 포스터
	pst_no   CHAR(5)      NOT NULL COMMENT '우편번호', -- 우편번호
	bas_addr VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
	oper     TEXT         NULL     COMMENT '경기운영' -- 경기운영
)
COMMENT '대회';

-- 대회
ALTER TABLE COMPETITION
	ADD CONSTRAINT PK_COMPETITION -- 대회 기본키
		PRIMARY KEY (
			cno -- 대회번호
		);

ALTER TABLE COMPETITION
	MODIFY COLUMN cno INTEGER NOT NULL AUTO_INCREMENT COMMENT '대회번호';

-- 회원
CREATE TABLE MEMBER (
	mno       INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	id        VARCHAR(25)  NOT NULL COMMENT '아이디', -- 아이디
	email     VARCHAR(40)  NULL     COMMENT '이메일', -- 이메일
	tel       VARCHAR(30)  NULL     COMMENT '전화번호', -- 전화번호
	name      VARCHAR(50)  NULL     COMMENT '이름', -- 이름
	pwd       VARCHAR(50)  NOT NULL COMMENT '비밀번호', -- 비밀번호
	photo     VARCHAR(255) NULL     COMMENT '회원사진', -- 회원사진
	pst_no    CHAR(5)      NULL     COMMENT '우편번호', -- 우편번호
	bas_addr  VARCHAR(255) NULL     COMMENT '기본주소', -- 기본주소
	address   VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
	gender    VARCHAR(10)  NULL     COMMENT '성별', -- 성별
	age       INTEGER      NULL     COMMENT '나이', -- 나이
	introduce TEXT         NULL     COMMENT '자기소개' -- 자기소개
)
COMMENT '회원';

-- 회원
ALTER TABLE MEMBER
	ADD CONSTRAINT PK_MEMBER -- 회원 기본키
		PRIMARY KEY (
			mno -- 회원번호
		);

-- 회원 유니크 인덱스
CREATE UNIQUE INDEX UIX_MEMBER
	ON MEMBER ( -- 회원
		id ASC -- 아이디
	);

-- 회원 유니크 인덱스2
CREATE UNIQUE INDEX UIX_MEMBER2
	ON MEMBER ( -- 회원
		email ASC -- 이메일
	);

-- 회원 유니크 인덱스3
CREATE UNIQUE INDEX UIX_MEMBER3
	ON MEMBER ( -- 회원
		tel ASC -- 전화번호
	);

ALTER TABLE MEMBER
	MODIFY COLUMN mno INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 팀
CREATE TABLE TEAM (
	tno       INTEGER      NOT NULL COMMENT '팀번호', -- 팀번호
	eno       INTEGER      NOT NULL COMMENT '종목번호', -- 종목번호
	name      VARCHAR(50)  NOT NULL COMMENT '팀명', -- 팀명
	total_num INTEGER      NOT NULL DEFAULT 0 COMMENT '총멤버수', -- 총멤버수
	join_num  INTEGER      NOT NULL DEFAULT 0 COMMENT '현재인원수', -- 현재인원수
	win       INTEGER      NOT NULL DEFAULT 0 COMMENT '승', -- 승
	draw      INTEGER      NOT NULL DEFAULT 0 COMMENT '무', -- 무
	lose      INTEGER      NOT NULL DEFAULT 0 COMMENT '패', -- 패
	fee       INTEGER      NOT NULL DEFAULT 0 COMMENT '회비', -- 회비
	meet_day  CHAR(6)      NULL     COMMENT '정모요일', -- 정모요일
	introduce TEXT         NOT NULL COMMENT '팀소개', -- 팀소개
	emblem    VARCHAR(255) NULL     COMMENT '엠블럼', -- 엠블럼
	aform     TEXT         NULL     COMMENT '가입양식폼', -- 가입양식폼
	pst_no    CHAR(5)      NOT NULL COMMENT '우편번호', -- 우편번호
	bas_addr  VARCHAR(255) NOT NULL COMMENT '기본주소' -- 기본주소
)
COMMENT '팀';

-- 팀
ALTER TABLE TEAM
	ADD CONSTRAINT PK_TEAM -- 팀 기본키
		PRIMARY KEY (
			tno -- 팀번호
		);

-- 팀 유니크 인덱스
CREATE UNIQUE INDEX UIX_TEAM
	ON TEAM ( -- 팀
		name ASC -- 팀명
	);

ALTER TABLE TEAM
	MODIFY COLUMN tno INTEGER NOT NULL AUTO_INCREMENT COMMENT '팀번호';

-- 보드댓글
CREATE TABLE BOAR_COMM (
	bcno   INTEGER      NOT NULL COMMENT '댓글번호', -- 댓글번호
	mno    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	bno    INTEGER      NOT NULL COMMENT '종목게시물번호', -- 종목게시물번호
	comm   TEXT         NOT NULL COMMENT '댓글', -- 댓글
	cre_dt DATETIME     NOT NULL COMMENT '작성시간', -- 작성시간
	photo  VARCHAR(255) NULL     COMMENT '사진경로' -- 사진경로
)
COMMENT '보드댓글';

-- 보드댓글
ALTER TABLE BOAR_COMM
	ADD CONSTRAINT PK_BOAR_COMM -- 보드댓글 기본키
		PRIMARY KEY (
			bcno -- 댓글번호
		);

ALTER TABLE BOAR_COMM
	MODIFY COLUMN bcno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 팀참여
CREATE TABLE JOIN_TEAM (
	mno     INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
	tno     INTEGER NOT NULL COMMENT '팀번호', -- 팀번호
	cre_dt  DATE    NOT NULL COMMENT '생성일', -- 생성일
	state   INTEGER NOT NULL COMMENT '상태', -- 상태
	content TEXT    NULL     COMMENT '내용', -- 내용
	level   INTEGER NOT NULL DEFAULT 0 COMMENT '등급' -- 등급
)
COMMENT '팀참여';

-- 팀참여
ALTER TABLE JOIN_TEAM
	ADD CONSTRAINT PK_JOIN_TEAM -- 팀참여 기본키
		PRIMARY KEY (
			mno, -- 회원번호
			tno  -- 팀번호
		);

-- 대회참여
CREATE TABLE JOIN_COMP (
	cno     INTEGER     NOT NULL COMMENT '대회번호', -- 대회번호
	tno     INTEGER     NOT NULL COMMENT '팀번호', -- 팀번호
	cre_dt  DATE        NOT NULL COMMENT '생성일', -- 생성일
	content TEXT        NULL     COMMENT '내용', -- 내용
	state   INTEGER     NOT NULL COMMENT '상태', -- 상태
	rank    VARCHAR(50) NULL     COMMENT '등수' -- 등수
)
COMMENT '대회참여';

-- 대회참여
ALTER TABLE JOIN_COMP
	ADD CONSTRAINT PK_JOIN_COMP -- 대회참여 기본키
		PRIMARY KEY (
			cno, -- 대회번호
			tno  -- 팀번호
		);

-- 종목
CREATE TABLE EVENT (
	eno  INTEGER     NOT NULL COMMENT '종목번호', -- 종목번호
	name VARCHAR(50) NOT NULL COMMENT '종목이름' -- 종목이름
)
COMMENT '종목';

-- 종목
ALTER TABLE EVENT
	ADD CONSTRAINT PK_EVENT -- 종목 기본키
		PRIMARY KEY (
			eno -- 종목번호
		);

ALTER TABLE EVENT
	MODIFY COLUMN eno INTEGER NOT NULL AUTO_INCREMENT COMMENT '종목번호';

-- 선호종목
CREATE TABLE LIKE_EVENT (
	mno INTEGER NOT NULL COMMENT '회원번호', -- 회원번호
	eno INTEGER NOT NULL COMMENT '종목번호' -- 종목번호
)
COMMENT '선호종목';

-- 선호종목
ALTER TABLE LIKE_EVENT
	ADD CONSTRAINT PK_LIKE_EVENT -- 선호종목 기본키
		PRIMARY KEY (
			mno, -- 회원번호
			eno  -- 종목번호
		);

-- 사진
CREATE TABLE PHOTO (
	pno   INTEGER      NOT NULL COMMENT '사진번호', -- 사진번호
	photo VARCHAR(255) NOT NULL COMMENT '사진경로', -- 사진경로
	bno   INTEGER      NOT NULL COMMENT '종목게시물번호' -- 종목게시물번호
)
COMMENT '사진';

-- 사진
ALTER TABLE PHOTO
	ADD CONSTRAINT PK_PHOTO -- 사진 기본키
		PRIMARY KEY (
			pno -- 사진번호
		);

ALTER TABLE PHOTO
	MODIFY COLUMN pno INTEGER NOT NULL AUTO_INCREMENT COMMENT '사진번호';

-- 대회댓글
CREATE TABLE COMP_COMM (
	ccno   INTEGER      NOT NULL COMMENT '댓글번호', -- 댓글번호
	cno    INTEGER      NOT NULL COMMENT '대회번호', -- 대회번호
	mno    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
	comm   TEXT         NOT NULL COMMENT '댓글', -- 댓글
	cre_dt DATETIME     NOT NULL COMMENT '작성시간', -- 작성시간
	photo  VARCHAR(255) NULL     COMMENT '사진경로' -- 사진경로
)
COMMENT '대회댓글';

-- 대회댓글
ALTER TABLE COMP_COMM
	ADD CONSTRAINT PK_COMP_COMM -- 대회댓글 기본키
		PRIMARY KEY (
			ccno -- 댓글번호
		);

ALTER TABLE COMP_COMM
	MODIFY COLUMN ccno INTEGER NOT NULL AUTO_INCREMENT COMMENT '댓글번호';

-- 종목게시판
ALTER TABLE BOARD
	ADD CONSTRAINT FK_MEMBER_TO_BOARD -- 회원 -> 종목게시판
		FOREIGN KEY (
			mno -- 회원번호
		)
		REFERENCES MEMBER ( -- 회원
			mno -- 회원번호
		);

-- 종목게시판
ALTER TABLE BOARD
	ADD CONSTRAINT FK_EVENT_TO_BOARD -- 종목 -> 종목게시판
		FOREIGN KEY (
			eno -- 종목번호
		)
		REFERENCES EVENT ( -- 종목
			eno -- 종목번호
		);

-- 대회
ALTER TABLE COMPETITION
	ADD CONSTRAINT FK_EVENT_TO_COMPETITION -- 종목 -> 대회
		FOREIGN KEY (
			eno -- 종목번호
		)
		REFERENCES EVENT ( -- 종목
			eno -- 종목번호
		);

-- 대회
ALTER TABLE COMPETITION
	ADD CONSTRAINT FK_TEAM_TO_COMPETITION -- 팀 -> 대회
		FOREIGN KEY (
			tno -- 생성팀번호
		)
		REFERENCES TEAM ( -- 팀
			tno -- 팀번호
		);

-- 팀
ALTER TABLE TEAM
	ADD CONSTRAINT FK_EVENT_TO_TEAM -- 종목 -> 팀
		FOREIGN KEY (
			eno -- 종목번호
		)
		REFERENCES EVENT ( -- 종목
			eno -- 종목번호
		);

-- 보드댓글
ALTER TABLE BOAR_COMM
	ADD CONSTRAINT FK_MEMBER_TO_BOAR_COMM -- 회원 -> 보드댓글
		FOREIGN KEY (
			mno -- 회원번호
		)
		REFERENCES MEMBER ( -- 회원
			mno -- 회원번호
		);

-- 보드댓글
ALTER TABLE BOAR_COMM
	ADD CONSTRAINT FK_BOARD_TO_BOAR_COMM -- 종목게시판 -> 보드댓글
		FOREIGN KEY (
			bno -- 종목게시물번호
		)
		REFERENCES BOARD ( -- 종목게시판
			bno -- 종목게시물번호
		);

-- 팀참여
ALTER TABLE JOIN_TEAM
	ADD CONSTRAINT FK_MEMBER_TO_JOIN_TEAM -- 회원 -> 팀참여
		FOREIGN KEY (
			mno -- 회원번호
		)
		REFERENCES MEMBER ( -- 회원
			mno -- 회원번호
		);

-- 팀참여
ALTER TABLE JOIN_TEAM
	ADD CONSTRAINT FK_TEAM_TO_JOIN_TEAM -- 팀 -> 팀참여
		FOREIGN KEY (
			tno -- 팀번호
		)
		REFERENCES TEAM ( -- 팀
			tno -- 팀번호
		);

-- 대회참여
ALTER TABLE JOIN_COMP
	ADD CONSTRAINT FK_COMPETITION_TO_JOIN_COMP -- 대회 -> 대회참여
		FOREIGN KEY (
			cno -- 대회번호
		)
		REFERENCES COMPETITION ( -- 대회
			cno -- 대회번호
		);

-- 대회참여
ALTER TABLE JOIN_COMP
	ADD CONSTRAINT FK_TEAM_TO_JOIN_COMP -- 팀 -> 대회참여
		FOREIGN KEY (
			tno -- 팀번호
		)
		REFERENCES TEAM ( -- 팀
			tno -- 팀번호
		);

-- 선호종목
ALTER TABLE LIKE_EVENT
	ADD CONSTRAINT FK_MEMBER_TO_LIKE_EVENT -- 회원 -> 선호종목
		FOREIGN KEY (
			mno -- 회원번호
		)
		REFERENCES MEMBER ( -- 회원
			mno -- 회원번호
		);

-- 선호종목
ALTER TABLE LIKE_EVENT
	ADD CONSTRAINT FK_EVENT_TO_LIKE_EVENT -- 종목 -> 선호종목
		FOREIGN KEY (
			eno -- 종목번호
		)
		REFERENCES EVENT ( -- 종목
			eno -- 종목번호
		);

-- 사진
ALTER TABLE PHOTO
	ADD CONSTRAINT FK_BOARD_TO_PHOTO -- 종목게시판 -> 사진
		FOREIGN KEY (
			bno -- 종목게시물번호
		)
		REFERENCES BOARD ( -- 종목게시판
			bno -- 종목게시물번호
		);

-- 대회댓글
ALTER TABLE COMP_COMM
	ADD CONSTRAINT FK_COMPETITION_TO_COMP_COMM -- 대회 -> 대회댓글
		FOREIGN KEY (
			cno -- 대회번호
		)
		REFERENCES COMPETITION ( -- 대회
			cno -- 대회번호
		);

-- 대회댓글
ALTER TABLE COMP_COMM
	ADD CONSTRAINT FK_MEMBER_TO_COMP_COMM -- 회원 -> 대회댓글
		FOREIGN KEY (
			mno -- 회원번호
		)
		REFERENCES MEMBER ( -- 회원
			mno -- 회원번호
		);
		
insert event(name) values ('자유게시판');