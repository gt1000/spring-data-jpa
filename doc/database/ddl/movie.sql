drop table if exists movie;
drop table if exists movie_image;
drop table if exists review;

create table movie (
    movie_id                        bigint                                  comment '영화 고유키',
    title                           varchar(255) not null                   comment '제목',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (movie_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE movie comment = '영화';

create table movie_image (
    movie_image_id                  bigint                                  comment '영화 이미지 고유키',
    movie_id                        bigint                                  comment '영화 고유키',
    real_file_name                  varchar(255)                            comment '실제 파일명',
    image_name                      varchar(255)                            comment '파일 이름',
    path                            varchar(255)                            comment '경로',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (movie_image_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE movie_image comment = '영화 이미지';

create table review (
   review_id                        bigint                                  comment '영화 고유키',
   member_id                        varchar(32)                                  comment '사용자 고유키',
   movie_id                        bigint                                  comment '영화 고유키',
   title                           varchar(255) not null                   comment '제목',
   grade                            int(11)                                 comment '등급',
   message_text                     varchar(255)                            comment '내용',
   modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
   created_date                    timestamp(6) default current_timestamp comment '등록 시간',
   primary key (review_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE review comment = '리뷰';