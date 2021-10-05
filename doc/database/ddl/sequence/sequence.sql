
drop sequence if exists item_seq;
drop sequence if exists guestbook_seq;
drop sequence if exists board_seq;
drop sequence if exists board_reply_seq;
drop sequence if exists movie_seq;
drop sequence if exists movie_image_seq;
drop sequence if exists review_seq;

create sequence item_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence guestbook_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence board_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence board_reply_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence movie_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence movie_image_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence review_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;