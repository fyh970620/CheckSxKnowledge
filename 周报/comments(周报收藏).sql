alter table CHECK_WEEKLY_COLLECT
   drop constraint FK_CHECK_WE_REFERENCE_CHECK_WE;

drop table CHECK_WEEKLY_COLLECT cascade constraints;

/*==============================================================*/
/* Table: CHECK_WEEKLY_COLLECT                                  */
/*==============================================================*/
create table CHECK_WEEKLY_COLLECT  (
   COLLECT_ID           NUMBER(9)                       not null,
   COLLECT_STAFF_ID     NUMBER(9),
   VISIT_CREATE_DATE    DATE,
   WEEKLY_ID            NUMBER(9),
   COLLECT_REMARK       VARCHAR2(2000),
   constraint PK_CHECK_WEEKLY_COLLECT primary key (COLLECT_ID)
);

comment on column CHECK_WEEKLY_COLLECT.COLLECT_ID is
'COLLECT_ID';

comment on column CHECK_WEEKLY_COLLECT.COLLECT_STAFF_ID is
'收藏人';

comment on column CHECK_WEEKLY_COLLECT.VISIT_CREATE_DATE is
'收藏人时间';

comment on column CHECK_WEEKLY_COLLECT.WEEKLY_ID is
'周报ID';

comment on column CHECK_WEEKLY_COLLECT.COLLECT_REMARK is
'收藏备注';

alter table CHECK_WEEKLY_COLLECT
   add constraint FK_CHECK_WE_REFERENCE_CHECK_W3 foreign key (WEEKLY_ID)
      references CHECK_WEEKLY_LIST (WEEKLY_ID);

create sequence CHECK_WEEKLY_COLLECT_seq
increment by 1
start with 1;

select CWC.*,sta.staff_name from CHECK_WEEKLY_COLLECT CWC
        left join
          Staff sta
        on sta.STAFF_ID=CWC.COLLECT_STAFF_ID
        WHERE
       sta.STAFF_ID=4
select * from Check_Weekly_List;   --1,7,8,9,10,11,21,22
--------------------------------------------------------------------------------------------------------------------------------------------------
--data
select * from CHECK_WEEKLY_COLLECT;
delete from CHECK_WEEKLY_COLLECT where collect_id=4;


insert into CHECK_WEEKLY_COLLECT
  (COLLECT_ID,
   COLLECT_STAFF_ID,
   VISIT_CREATE_DATE,
   WEEKLY_ID,
   COLLECT_REMARK)
values
  (1, 4, sysdate, 1, 'moke.js');
insert into CHECK_WEEKLY_COLLECT(COLLECT_ID,COLLECT_STAFF_ID,VISIT_CREATE_DATE,WEEKLY_ID,COLLECT_REMARK) 
values(CHECK_WEEKLY_COLLECT_seq.Nextval,4,sysdate,7,'dafdffsdf');
insert into CHECK_WEEKLY_COLLECT(COLLECT_ID,COLLECT_STAFF_ID,VISIT_CREATE_DATE,WEEKLY_ID,COLLECT_REMARK) 
values(CHECK_WEEKLY_COLLECT_seq.Nextval,4,sysdate,8,'dafdffsdf');
    
 select CWC.*,sta.staff_name from CHECK_WEEKLY_COLLECT CWC
        left join
          Staff sta
        on sta.STAFF_ID=CWC.COLLECT_STAFF_ID
        WHERE
       sta.STAFF_ID=4
       select * from CHECK_WEEKLY_COLLECT;
