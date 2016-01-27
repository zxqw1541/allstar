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
		
/* 이벤트 */
insert event(name) values ('자유게시판');
insert event(name) values ('농구');
insert event(name) values ('축구');
insert event(name) values ('야구');
insert event(name) values ('탁구');
insert event(name) values ('볼링');

/* 멤버 */
insert member(id,name,pwd) values ('abcd1','이름1',sha1('1111'));
insert member(id,name,pwd) values ('abcd2','이름2',sha1('1111'));
insert member(id,name,pwd) values ('abcd3','이름3',sha1('1111'));
insert member(id,name,pwd) values ('abcd4','이름4',sha1('1111'));
insert member(id,name,pwd) values ('abcd5','이름5',sha1('1111'));

/* 팀 */
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀1',10,100000,'팀소개1','12345','기본주소1');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀2',10,100000,'팀소개2','12345','기본주소2');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀3',10,100000,'팀소개3','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀4',10,100000,'팀소개4','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀5',10,100000,'팀소개5','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀6',10,100000,'팀소개6','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀7',10,100000,'팀소개7','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀8',10,100000,'팀소개8','12345','기본주소1');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀9',10,100000,'팀소개9','12345','기본주소2');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀10',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀11',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀12',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀13',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀14',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀15',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀16',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀17',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀18',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀19',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀20',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀21',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀22',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀23',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀24',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀25',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀26',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀27',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀28',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀29',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀30',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀31',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀32',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀33',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀34',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀35',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀36',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀37',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀38',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀39',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀40',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀41',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀42',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀43',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀44',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀45',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (3,'팀46',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀47',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀48',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀49',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (4,'팀50',10,100000,'팀소개14','12345','기본주소7');

/* 대회 */
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(2,1,'대회1',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용1','그림1','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(2,2,'대회2',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용2','그림2','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(3,3,'대회3',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용3','그림3','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(3,4,'대회4',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용4','그림4','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(4,5,'대회5',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용5','그림5','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(4,5,'대회6',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용5','그림5','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(4,5,'대회7',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용5','그림5','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(4,5,'대회8',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용5','그림5','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(4,5,'대회9',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용5','그림5','12345','기본주소');
insert competition(eno, tno, name, team_num, cost, sdt, edt, rsdt, redt, content, poster, pst_no, bas_addr)
values(4,5,'대회10',36,100000,'2015-01-01','2015-01-02','2015-01-03','2015-01-04','내용5','그림5','12345','기본주소')

/* 종목게시판 */
insert board(eno, mno, title, content, cre_dt) values (2,1,'게시물1','내용1',now());
insert board(eno, mno, title, content, cre_dt) values (2,2,'게시물2','내용2',now());
insert board(eno, mno, title, content, cre_dt) values (3,3,'게시물3','내용3',now());
insert board(eno, mno, title, content, cre_dt) values (3,4,'게시물4','내용4',now());
insert board(eno, mno, title, content, cre_dt) values (4,5,'게시물5','내용5',now());
insert board(eno, mno, title, content, cre_dt) values (4,4,'게시물6','내용6',now());
insert board(eno, mno, title, content, cre_dt) values (4,5,'게시물7','내용7',now());

/* 자유게시판 */
insert board(eno, mno, title, content, cre_dt) values (1,1,'게시물1','내용1',now());
insert board(eno, mno, title, content, cre_dt) values (1,2,'게시물2','내용2',now());
insert board(eno, mno, title, content, cre_dt) values (1,3,'게시물3','내용3',now());
insert board(eno, mno, title, content, cre_dt) values (1,4,'게시물4','내용4',now());
insert board(eno, mno, title, content, cre_dt) values (1,5,'게시물5','내용5',now());
insert board(eno, mno, title, content, cre_dt) values (1,4,'게시물6','내용6',now());
insert board(eno, mno, title, content, cre_dt) values (1,5,'게시물7','내용7',now());

/* 팀 참가 */
insert join_team(mno,tno,cre_dt,state,level) values (1,1,now(),0,1);
insert join_team(mno,tno,cre_dt,state,level) values (1,2,now(),0,1);
insert join_team(mno,tno,cre_dt,state,level) values (2,3,now(),0,1);
insert join_team(mno,tno,cre_dt,state,level) values (2,4,now(),0,1);
insert join_team(mno,tno,cre_dt,state,level) values (3,5,now(),0,1);
insert join_team(mno,tno,cre_dt,state,level) values (4,6,now(),0,1);
insert join_team(mno,tno,cre_dt,state,level) values (5,7,now(),0,1);

/* 대회 참가 */
insert join_comp(cno,tno,cre_dt,state) values (1,1,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (1,2,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (1,3,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (1,4,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (1,5,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (1,6,now(),0);

insert join_comp(cno,tno,cre_dt,state) values (2,1,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (2,2,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (2,3,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (2,4,now(),0);
insert join_comp(cno,tno,cre_dt,state) values (2,7,now(),0);

/* 종목 좋아요 */
insert like_event(mno, eno) values (1,2);
insert like_event(mno, eno) values (1,3);
insert like_event(mno, eno) values (2,2);
insert like_event(mno, eno) values (3,2);
insert like_event(mno, eno) values (4,3);
insert like_event(mno, eno) values (5,4);

/* 팀 */
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀1',10,100000,'팀소개1','12345','기본주소1');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀2',10,100000,'팀소개2','12345','기본주소2');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀3',10,100000,'팀소개3','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀4',10,100000,'팀소개4','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀5',10,100000,'팀소개5','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀6',10,100000,'팀소개6','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀7',10,100000,'팀소개7','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀8',10,100000,'팀소개8','12345','기본주소1');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀9',10,100000,'팀소개9','12345','기본주소2');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀10',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀11',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀12',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀13',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀14',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀15',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀16',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀17',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀18',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀19',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀20',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀21',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀22',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀23',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀24',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀25',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀26',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀27',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀28',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀29',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀30',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀31',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀32',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀33',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀34',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀35',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀36',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀37',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀38',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀39',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀40',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀41',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀42',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀43',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀44',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀45',10,100000,'팀소개10','12345','기본주소3');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀46',10,100000,'팀소개11','12345','기본주소4');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀47',10,100000,'팀소개12','12345','기본주소5');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀48',10,100000,'팀소개13','12345','기본주소6');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀49',10,100000,'팀소개14','12345','기본주소7');
insert team(eno, name, total_num, fee, introduce, pst_no,bas_addr)
values (2,'팀50',10,100000,'팀소개14','12345','기본주소7');
