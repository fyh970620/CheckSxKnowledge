drop table CHECK_STAFF_LEAVE cascade constraints;

/*==============================================================*/
/* Table: CHECK_STAFF_LEAVE                                     */
/*==============================================================*/
create table CHECK_STAFF_LEAVE  (
   LEAVE_ID             NUMBER(9)                       not null,
   CHECK_STAFF_ID       NUMBER(9),
   BEGIN_LEAVE_DATE     DATE,
   END_LEAVE_DATE       DATE,
   LEAVE_DAY            NUMBER(9,2),
   STAFF_GROUP_TYPE     VARCHAR2(20),
   constraint PK_CHECK_STAFF_LEAVE primary key (LEAVE_ID)
);

comment on column CHECK_STAFF_LEAVE.LEAVE_ID is
'LEAVE_ID';

comment on column CHECK_STAFF_LEAVE.CHECK_STAFF_ID is
'请假人';

comment on column CHECK_STAFF_LEAVE.BEGIN_LEAVE_DATE is
'请假开始时间';

comment on column CHECK_STAFF_LEAVE.END_LEAVE_DATE is
'请假结束时间';

comment on column CHECK_STAFF_LEAVE.LEAVE_DAY is
'请假天数';

comment on column CHECK_STAFF_LEAVE.STAFF_GROUP_TYPE is
'1前台2后台3工程';

select * from CHECK_STAFF_LEAVE;

delete from CHECK_STAFF_LEAVE where leave_id=21;

insert into CHECK_STAFF_COMMENT csc
  (CHECK_ID,
   CHECK_STAFF_ID,
   VISIT_CREATE_DATE,
   STAFF_ID,
   COLLECT_REMARK,
   GRADE,
   STAFF_GROUP_TYPE)
values
  (CHECK_STAFF_COMMENT_SEQ.nextval,
   4,
   to_date('2019-01-22', 'yyyy-MM-dd'),
   5,
   '评价内容',
   97,
   '3');
   
   select * from CHECK_STAFF_COMMENT;
   
   select * from CHECK_STAFF_LEAVE;
   
   
    SELECT csl.LEAVE_ID,csl.CHECK_STAFF_ID,to_char(csl.BEGIN_LEAVE_DATE,'yyyy-mm-dd hh24:mi:ss'),
          to_char(csl.END_LEAVE_DATE,'yyyy-mm-dd hh24:mi:ss'),csl.LEAVE_DAY,csl.STAFF_GROUP_TYPE,st.*
         FROM CHECK_STAFF_LEAVE CSL
          LEFT JOIN  Staff st
        ON CSL.CHECK_STAFF_ID = st.STAFF_ID
          WHERE CHECK_STAFF_ID =5
          
          delete from CHECK_STAFF_LEAVE where leave_id=22;
          
 select * from check_weekly_list_comment;
 
 insert into check_weekly_list_comment(comment_id,comment_parent_id,weekly_id)
 values(check_weekly_list_comment_seq.nextval,0,1)
 
\
