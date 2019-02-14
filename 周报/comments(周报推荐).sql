alter table CHECK_WEEKLY_TOP
   drop constraint FK_CHECK_WE_REFERENCE_CHECK_WE;

drop table CHECK_WEEKLY_TOP cascade constraints;

/*==============================================================*/
/* Table: CHECK_WEEKLY_TOP                                      */
/*==============================================================*/
create table CHECK_WEEKLY_TOP  (
   TOP_ID               NUMBER(9)                       not null,
   TOP_STAFF_ID         NUMBER(9),
   VISIT_CREATE_DATE    DATE,
   WEEKLY_ID            NUMBER(9),
   constraint PK_CHECK_WEEKLY_TOP primary key (TOP_ID)
);

comment on column CHECK_WEEKLY_TOP.TOP_ID is
'TOP_ID';

comment on column CHECK_WEEKLY_TOP.TOP_STAFF_ID is
'推荐人';

comment on column CHECK_WEEKLY_TOP.VISIT_CREATE_DATE is
'推荐时间';

comment on column CHECK_WEEKLY_TOP.WEEKLY_ID is
'周报ID';

alter table CHECK_WEEKLY_TOP
   add constraint FK_CHECK_WE_REFERENCE_CHECK_W4 foreign key (WEEKLY_ID)
      references CHECK_WEEKLY_LIST (WEEKLY_ID);
-------------------------------------------------------------------------------------
--data --sql
create sequence CHECK_WEEKLY_TOP_seq
increment by 1
start with 1;

select * from check_weekly_list;
select * from CHECK_WEEKLY_TOP;

INSERT INTO CHECK_WEEKLY_TOP
  (TOP_ID, TOP_STAFF_ID, VISIT_CREATE_DATE, WEEKLY_ID)
VALUES
  (CHECK_WEEKLY_TOP_seq.nextval, 4, sysdate, 1);
INSERT INTO CHECK_WEEKLY_TOP
  (TOP_ID, TOP_STAFF_ID, VISIT_CREATE_DATE, WEEKLY_ID)
VALUES
  (CHECK_WEEKLY_TOP_seq.nextval, 4, sysdate, 7);
  INSERT INTO CHECK_WEEKLY_TOP
  (TOP_ID, TOP_STAFF_ID, VISIT_CREATE_DATE, WEEKLY_ID)
VALUES
  (CHECK_WEEKLY_TOP_seq.nextval, 4, sysdate, 8);
  INSERT INTO CHECK_WEEKLY_TOP
  (TOP_ID, TOP_STAFF_ID, VISIT_CREATE_DATE, WEEKLY_ID)
VALUES
  (CHECK_WEEKLY_TOP_seq.nextval, 4, sysdate, 9);   
  
  select * from CHECK_WEEKLY_TOP;
  delete from CHECK_WEEKLY_TOP where top_id=21;
  select * from check_weekly_list;
  delete from check_weekly_list where weekly_ID=22;
  select * from check_weekly_list_attach;
  
  delete from check_weekly_list where attach_id=22;
  
  




  
