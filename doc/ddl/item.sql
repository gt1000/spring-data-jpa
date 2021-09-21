drop table if exists item;

create table item (
    item_id                         bigint,
    item_name                       varchar(50) not null,
    price                           int default 0,
    stock_number                    int default 0,
    sell_status                     varchar(255),
    item_detail                     text,
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp,
    created_date                    timestamp(6) default current_timestamp,
    primary key (item_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE item COMMENT = '상품';
ALTER TABLE item MODIFY item_id bigint COMMENT '상품 코드';
ALTER TABLE item MODIFY item_name varchar(50) not null COMMENT '상품명';
ALTER TABLE item MODIFY price int default 0 COMMENT '가격';
ALTER TABLE item MODIFY stock_number int default 0 COMMENT '재고수량';
ALTER TABLE item MODIFY sell_status varchar(255) COMMENT '상품 판매 상태';
ALTER TABLE item MODIFY item_detail text COMMENT '상세 설명';
ALTER TABLE item MODIFY modified_date timestamp(6) default current_timestamp on update current_timestamp COMMENT '수정 시간';
ALTER TABLE item MODIFY created_date timestamp(6) default current_timestamp COMMENT '등록 시간';