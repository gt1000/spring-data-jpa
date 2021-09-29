drop table if exists board;
drop table if exists board_reply;

create table board (
    board_id                        bigint                                  comment '방명록 고유키',
    title                           varchar(256) not null                   comment '제목',
    content                         text                                    comment '내용',
    member_Id                       varchar(32) not null                    comment '작성자',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (board_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE board comment = '게시판';

create table board_reply (
    board_reply_id                  bigint                                  comment '방명록 답글 고유키',
    board_id                        bigint                                  comment '방명록 고유키',
    content                         text                                    comment '답글',
    member_Id                       varchar(32) not null                    comment '작성자',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (board_reply_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE board_reply comment = '게시판 답글';
