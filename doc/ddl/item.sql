drop table if exists item;

create table item (
    item_id                         bigint                                  comment '상품 코드',
    item_name                       varchar(50) not null                    comment '상품명',
    price                           int default 0                           comment '가격',
    stock_number                    int default 0                           comment '재고수량',
    sell_status                     varchar(255)                            comment '상품 판매 상태',
    item_detail                     text                                    comment '상세 설명',
    modified_date                   timestamp(6) default current_timestamp on update current_timestamp comment '수정 시간',
    created_date                    timestamp(6) default current_timestamp comment '등록 시간',
    primary key (item_id)
) engine=InnoDB;

-- 테이블 코멘트
ALTER TABLE item comment = '상품';
-- ALTER TABLE item MODIFY item_id bigint comment '상품 코드';
