drop table if exists guestbook;

create table guestbook (
    guestbook_id                    bigint                                  comment '방명록 고유키',
    title                           varchar(256) not null                   comment '제목',
    content                         text                                    comment '내용',
    writer                          varchar(50) not null                    comment '작성자',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (guestbook_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE guestbook comment = '방명록';
