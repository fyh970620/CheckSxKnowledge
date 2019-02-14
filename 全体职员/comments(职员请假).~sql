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

create sequence CHECK_STAFF_LEAVE_SEQ
increment by 1
start with 1;

insert into CHECK_STAFF_LEAVE
  (LEAVE_ID,
   CHECK_STAFF_ID,
   BEGIN_LEAVE_DATE,
   END_LEAVE_DATE,
   LEAVE_DAY,
   STAFF_GROUP_TYPE)
values
  (CHECK_STAFF_LEAVE_SEQ.nextval)

select * from check_staff_leave

select count(0)
  from (SELECT csl.LEAVE_ID,
               csl.CHECK_STAFF_ID,
               to_char(csl.BEGIN_LEAVE_DATE, 'yyyy-mm-dd hh24:mi:ss'),
               to_char(csl.END_LEAVE_DATE ',yyyy-mm-dd hh24:mi:ss'),
               csl.LEAVE_DAY,
               csl.STAFF_GROUP_TYPE
          FROM CHECK_STAFF_LEAVE CSL
          LEFT JOIN Staff st
            ON CSL.CHECK_STAFF_ID = st.STAFF_ID
         WHERE CHECK_STAFF_ID = ?) tmp_count
         
SELECT csl.LEAVE_ID,
               csl.CHECK_STAFF_ID,
               to_char(csl.BEGIN_LEAVE_DATE, 'yyyy-mm-dd hh24:mi:ss'),
               to_char(csl.END_LEAVE_DATE ,'yyyy-mm-dd hh24:mi:ss'),
               csl.LEAVE_DAY,
               csl.STAFF_GROUP_TYPE
          FROM CHECK_STAFF_LEAVE CSL
          LEFT JOIN Staff st
            ON CSL.CHECK_STAFF_ID = st.STAFF_ID
            
            
       SELECT *
         FROM CHECK_STAFF_INFO csi
         LEFT JOIN Staff st
           ON csi.STAFF_ID = st.STAFF_ID
        where st.STAFF_ID = #{staffId}

    SELECT csi.STAFF_ID,to_char(csi.BEGIN_DATE,'YYYY-MM-DD'),csi.SCHOOL,csi.BASE_ADDRESS,csi.STAFF_GROUP_TYPE,csi.STAFF_TEACHER_NAME,st.*
         FROM CHECK_STAFF_INFO csi
         LEFT JOIN Staff st
           ON csi.STAFF_ID = st.STAFF_ID
        where st.STAFF_ID = 4
        
       UPDATE CHECK_STAFF_INFO csi
        SET
          BEGIN_DATE=to_date('2018-12-22', 'YYYY-MM-DD'),
          SCHOOL='草鸡大学',
          BASE_ADDRESS='籍贯',
          STAFF_GROUP_TYPE='3',
          STAFF_TEACHER_NAME='哈哈'
        WHERE STAFF_ID=4
        select * from CHECK_STAFF_INFO
        
   alter table CHECK_STAFF_INFO
   add (MAJOR  VARCHAR2(200));
   
 SELECT *
   FROM (SELECT TMP_PAGE.*, ROWNUM ROW_ID
           FROM (select csi.STAFF_ID,
                        csi.BEGIN_DATE,
                        csi.SCHOOL,
                        csi.BASE_ADDRESS,
                        csi.STAFF_GROUP_TYPE,
                        csi.STAFF_TEACHER_NAME,
                        csi.MAJOR,
                        st.staff_name
                   from CHECK_STAFF_INFO csi
                   LEFT JOIN staff st
                     on st.STAFF_ID = csi.STAFF_ID) TMP_PAGE
          WHERE ROWNUM <= 10)
  WHERE ROW_ID > 0
  select t.*
    from (select csi.STAFF_ID,
                 csi.BEGIN_DATE,
                 csi.SCHOOL,
                 csi.BASE_ADDRESS,
                 csi.STAFF_GROUP_TYPE,
                 csi.STAFF_TEACHER_NAME,
                 csi.MAJOR,
                 st.staff_name,
                 st.staff_id
            from CHECK_STAFF_INFO csi, staff st
           where st.staff_id(+) = csi.staff_id) t
  
