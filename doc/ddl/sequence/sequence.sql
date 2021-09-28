
drop sequence if exists item_seq;
drop sequence if exists guestbook_seq;

create sequence item_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;
create sequence guestbook_seq start with 1 increment by 1 minvalue=1 maxvalue=999999999999 cache 1;