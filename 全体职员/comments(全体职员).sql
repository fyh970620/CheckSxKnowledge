drop table CHECK_STAFF_INFO cascade constraints;

create sequence check_staff_info_seq
increment by 1
start with 1;


select * from check_staff_info;

/*==============================================================*/
/* Table: CHECK_STAFF_INFO                                      */
/*==============================================================*/
create table CHECK_STAFF_INFO  (
   STAFF_ID             NUMBER(9)                       not null,
   BEGIN_DATE           DATE,
   SCHOOL               VARCHAR2(200),
   BASE_ADDRESS         VARCHAR2(200),
   STAFF_GROUP_TYPE     VARCHAR2(20),
   constraint PK_CHECK_STAFF_INFO primary key (STAFF_ID)
);

alter table CHECK_STAFF_INFO add Staff_teacher_name varchar(40);

select * from check_staff_info;

comment on column CHECK_STAFF_INFO.STAFF_ID is
'STAFF_ID';

comment on column CHECK_STAFF_INFO.BEGIN_DATE is
'入司时间';

comment on column CHECK_STAFF_INFO.SCHOOL is
'毕业学校';

comment on column CHECK_STAFF_INFO.BASE_ADDRESS is
'籍贯';

comment on column CHECK_STAFF_INFO.STAFF_GROUP_TYPE is
'1前台2后台3工程';

comment on column CHECK_STAFF_INFO.Staff_teacher_name is
'导师名称';

select t.* from tp_domain t;
insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_CHECK_STAFF_INFO','人员归属组','VARCHAR2(20)','');
select t.* from tp_domain_dependencies t;
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_CHECK_STAFF_INFO','CHECK_STAFF_INFO','STAFF_GROUP_TYPE');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_CHECK_STAFF_INFO','1','前台',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_CHECK_STAFF_INFO','2','后台',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_CHECK_STAFF_INFO','3','工程',1,'0SA','','');

----------------------------------------------------------------------------------------------------------------
--sql data
update CHECK_STAFF_INFO 
set staff_teacher_name = '东哥';

select * from CHECK_STAFF_INFO;
select * from staff;  
delete from CHECK_STAFF_INFO where staff_id = 3;

delete from CHECK_STAFF_INFO where staff_id =1;

insert into CHECK_STAFF_INFO
  (STAFF_ID, BEGIN_DATE, SCHOOL, BASE_ADDRESS, STAFF_GROUP_TYPE)
values
  (1, to_date('2018-12-20', 'YYYY-MM-DD'), '福州大学', '福建福州', '2');
insert into CHECK_STAFF_INFO(STAFF_ID,BEGIN_DATE,SCHOOL,BASE_ADDRESS,STAFF_GROUP_TYPE)
values(check_staff_info_seq.nextval, to_date ( '2018-11-19' , 'YYYY-MM-DD' ),'福州大学','福建福州','2' );
insert into CHECK_STAFF_INFO(STAFF_ID,BEGIN_DATE,SCHOOL,BASE_ADDRESS,STAFF_GROUP_TYPE)
values(check_staff_info_seq.nextval, to_date ( '2018-10-23' , 'YYYY-MM-DD' ),'福州大学','福建福州','2' );
insert into CHECK_STAFF_INFO(STAFF_ID,BEGIN_DATE,SCHOOL,BASE_ADDRESS,STAFF_GROUP_TYPE)
values(check_staff_info_seq.nextval, to_date ( '2018-12-22' , 'YYYY-MM-DD' ),'福州大学','福建福州','2' );
insert into CHECK_STAFF_INFO(STAFF_ID,BEGIN_DATE,SCHOOL,BASE_ADDRESS,STAFF_GROUP_TYPE)
values(check_staff_info_seq.nextval, to_date ( '2018-12-30' , 'YYYY-MM-DD' ),'福州大学','福建福州','2' );

select cti.staff_id,user_name from 
check_staff_info cti left join staff 
on cti.staff_id = staff.staff_id
where cti.staff_group_type='2';

update CHECK_STAFF_INFO set staff_group_type = '1' where staff_id = 4


select cti.staff_id, user_name,staff_name
  from check_staff_info cti
  left join staff
    on cti.staff_id = staff.staff_id
 where cti.staff_group_type =2;
 
SELECT cwl.*, cwla.*
FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl where rownum<= 2) cwl,
CHECK_WEEKLY_LIST_ATTACH cwla
where cwl.weekly_id = cwla.weekly_id
and ckl.rownum > 0
select * from staff;
select * from CHECK_STAFF_INFO

  select cti.staff_id,user_name,staff_name
          from check_staff_info cti
          left join
              staff
          on
            cti.staff_id = staff.staff_id
  
        select cti.*,stf.user_name,stf.staff_name
          from check_staff_info cti
          left join
              staff stf
          on
            cti.staff_id = stf.staff_id
