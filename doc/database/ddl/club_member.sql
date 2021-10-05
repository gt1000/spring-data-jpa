drop table if exists club_member;

create table club_member (
    club_member_id                       varchar(32)                            comment '아이디',
    name                            varchar(50) not null                   comment '이름',
    password                        varchar(256) not null                  comment '비밀번호',
    email                           varchar(256) not null                  comment 'email',
    from_social                     boolean                                 comment '소셜 로그인',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (club_member_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE club_member comment = '클럽 사용자';
