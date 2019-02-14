alter table CHECK_WEEKLY_VISIT_LIST
   drop constraint FK_CHECK_WE_REFERENCE_CHECK_WE;

drop table CHECK_WEEKLY_VISIT_LIST cascade constraints;

/*==============================================================*/
/* Table: CHECK_WEEKLY_VISIT_LIST                               */
/*==============================================================*/
create table CHECK_WEEKLY_VISIT_LIST  (
   VISIT_ID             NUMBER(9)                       not null,
   VISIT_STAFF_ID       NUMBER(9),
   VISIT_CREATE_DATE    DATE,
   WEEKLY_ID            NUMBER(9),
   constraint PK_CHECK_WEEKLY_VISIT_LIST primary key (VISIT_ID)
);

comment on column CHECK_WEEKLY_VISIT_LIST.VISIT_ID is
'访问ID';

comment on column CHECK_WEEKLY_VISIT_LIST.VISIT_STAFF_ID is
'访问人';

comment on column CHECK_WEEKLY_VISIT_LIST.VISIT_CREATE_DATE is
'访问时间';

comment on column CHECK_WEEKLY_VISIT_LIST.WEEKLY_ID is
'周报ID';

alter table CHECK_WEEKLY_VISIT_LIST
   add constraint FK_CHECK_WE_REFERENCE_CHECK_W5 foreign key (WEEKLY_ID)
      references CHECK_WEEKLY_LIST (WEEKLY_ID);
--------------------------------------------------------------------------------------------------------------------------
--data--sql
select cwvl.*, st.staff_name, st.staff_id
  from CHECK_WEEKLY_VISIT_LIST cwvl
  left join staff st
    on cwvl.VISIT_STAFF_ID = st.staff_id
 where cwvl.weekly_id = 1;
 
 create Sequence CHECK_WEEKLY_VISIT_LIST_seq
 increment by 1
 start with 1;
 
 insert into CHECK_WEEKLY_VISIT_LIST
   (VISIT_ID, VISIT_STAFF_ID, VISIT_CREATE_DATE, WEEKLY_ID)
 values

   (CHECK_WEEKLY_VISIT_LIST_seq.Nextval, 4, '2018', 1);
select * from (select cwvl.*,rownum as rn from CHECK_WEEKLY_VISIT_LIST cwvl order by VISIT_CREATE_DATE desc)
where rn =1;


select *
  from (select cwvl.*, rownum as rn
          from (select *
                  from CHECK_WEEKLY_VISIT_LIST cwvl
                 order by VISIT_CREATE_DATE desc) cwvl)
 where rn = 1
   and weekly_id = 1;


select * from CHECK_WEEKLY_TOP;
select * from check_weekly_collect;
select * from CHECK_WEEKLY_VISIT_LIST;
delete from CHECK_WEEKLY_TOP where top_id=41;

select * from check_weekly_list;


 insert into CHECK_WEEKLY_VISIT_LIST
           (VISIT_ID, VISIT_STAFF_ID, VISIT_CREATE_DATE, WEEKLY_ID)
         values
           (CHECK_WEEKLY_VISIT_LIST_seq.Nextval, 4, sysdate, 1)
  
  select cwvl.*, st.staff_name, st.staff_id
    from (select *
            from CHECK_WEEKLY_VISIT_LIST
           order by VISIT_CREATE_DATE desc) cwvl
    left join staff st
      on cwvl.VISIT_STAFF_ID = st.staff_id
   where cwvl.weekly_id = 1
   
   select * from check_weekly_list_comment;
   
   update check_weekly_list
   set weekly_type='2'
   where weekly_id=1;
   
   delete from check_weekly_list_comment where comment_id=64;
select * from
        (select * from CHECK_WEEKLY_LIST_COMMENT cwlc
        start with comment_id=2
        connect by prior
        comment_id = comment_parent_id) cwl
        
        where cwl.weekly_Id =1 and  comment_parent_id=2
        
    insert into check_weekly_list_comment(comment_id,comment_parent_id,comment_staff_id,weekly_id)
    values(check_weekly_list_comment_seq.nextval,23,4,1);
    
    
      SELECT cwl.*, st.*
        FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl) cwl,
        staff st
        WHERE
        cwl.create_staff_id = st.staff_id and cwl.weekly_id=1
   SELECT cwl.*, st.*
        FROM  CHECK_WEEKLY_LIST cwl
        left JOIN
        staff st
        ON
        cwl.create_staff_id = st.staff_id
        WHERE
        cwl.weekly_id=1
