alter table CHECK_WEEKLY_LIST_ATTACH
   drop constraint FK_CHECK_WE_REFERENCE_CHECK_WE;
drop table CHECK_WEEKLY_LIST_ATTACH cascade constraints;

/*==============================================================*/
/* Table: CHECK_WEEKLY_LIST_ATTACH                              */
/*==============================================================*/
create table CHECK_WEEKLY_LIST_ATTACH  (
   ATTACH_ID            NUMBER(9)                       not null,
   ATTACH_NAME          VARCHAR2(200),
   ATTACH_REMARK        VARCHAR2(2000),
   WEEKLY_ID            NUMBER(9),
   constraint PK_CHECK_WEEKLY_LIST_ATTACH primary key (ATTACH_ID)
);

comment on column CHECK_WEEKLY_LIST_ATTACH.ATTACH_ID is
'主键ID';
comment on column CHECK_WEEKLY_LIST_ATTACH.ATTACH_NAME is
'附件名称';
comment on column CHECK_WEEKLY_LIST_ATTACH.ATTACH_REMARK is
'附件所在服务器路径';
comment on column CHECK_WEEKLY_LIST_ATTACH.WEEKLY_ID is
'周报ID';

alter table CHECK_WEEKLY_LIST_ATTACH
   add constraint FK_CHECK_WE_REFERENCE_CHECK_WE foreign key (WEEKLY_ID)
      references CHECK_WEEKLY_LIST (WEEKLY_ID);
      
--------------------------------------------------------------------------------------------------------------------------------      
drop table CHECK_WEEKLY_LIST cascade constraints;

/*==============================================================*/
/* Table: CHECK_WEEKLY_LIST                                     */
/*==============================================================*/
create table CHECK_WEEKLY_LIST  (
   WEEKLY_ID            NUMBER(9)                       not null,
   SUBJECT_NAME         VARCHAR2(200),
   CREATE_STAFF_ID      NUMBER(9),
   SUBJECT_CLASS        VARCHAR2(20),
   CREATE_DATE          DATE,
   WEEKLY_DESC          VARCHAR2(4000),
   NEXT_WEEKLY_DESC     VARCHAR2(4000)                 
      constraint CKC_NEXT_WEEKLY_DESC_CHECK_WE check (NEXT_WEEKLY_DESC is null or (NEXT_WEEKLY_DESC in ('1','2','3','4','5','6','7'))),
   SUBJECT_GROUP_TYPE   VARCHAR2(20)                   
      constraint CKC_SUBJECT_GROUP_TYP_CHECK_WE check (SUBJECT_GROUP_TYPE is null or (SUBJECT_GROUP_TYPE in ('1','2','3'))),
   KEY_WORD             VARCHAR2(2000),
   SUBJECT_NOTE         LONG,
   STAFF_TYPE           VARCHAR2(20),
   constraint PK_CHECK_WEEKLY_LIST primary key (WEEKLY_ID)
);

comment on column CHECK_WEEKLY_LIST.WEEKLY_ID is
'周报主键ID';

comment on column CHECK_WEEKLY_LIST.SUBJECT_NAME is
'技能名称';

comment on column CHECK_WEEKLY_LIST.CREATE_STAFF_ID is
'创建人ID';

comment on column CHECK_WEEKLY_LIST.SUBJECT_CLASS is
'哪一届新人';

comment on column CHECK_WEEKLY_LIST.CREATE_DATE is
'创建时间';

comment on column CHECK_WEEKLY_LIST.WEEKLY_DESC is
'本周周报描述';

comment on column CHECK_WEEKLY_LIST.NEXT_WEEKLY_DESC is
'下周学习计划';

comment on column CHECK_WEEKLY_LIST.SUBJECT_GROUP_TYPE is
'人员归属组分类1前台2后台3工程';

comment on column CHECK_WEEKLY_LIST.KEY_WORD is
'关键字标签';

comment on column CHECK_WEEKLY_LIST.SUBJECT_NOTE is
'周报笔记';

comment on column CHECK_WEEKLY_LIST.STAFF_TYPE is
'区分是2社招还是1实习生';


select t.* from tp_domain t;
insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_NEXT_WEEKLY_DESC','下周学习计划','VARCHAR2(4000)','');
insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_SUBJECT_GROUP_TYPE','人员归属组','VARCHAR2(20)','');
insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_WEEKLY_STAFF_TYPE','区分','VARCHAR2(20)','');
select t.* from tp_domain_dependencies t;
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_NEXT_WEEKLY_DESC','CHECK_WEEKLY_LIST','NEXT_WEEKLY_DESC');
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_SUBJECT_GROUP_TYPE','CHECK_WEEKLY_LIST','SUBJECT_GROUP_TYPE');
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_WEEKLY_STAFF_TYPE','CHECK_WEEKLY_LIST','STAFF_TYPE');
select t.* from tp_domain_listvalues t;
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_WEEKLY_STAFF_TYPE','1','实习生',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_WEEKLY_STAFF_TYPE','2','社招',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_SUBJECT_GROUP_TYPE','1','前台',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_SUBJECT_GROUP_TYPE','2','后台',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_SUBJECT_GROUP_TYPE','3','工程',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','1','JAVA',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','2','前端',2,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','3','开源',3,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','4','后台',4,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','5','数据库',5,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','6','工具',6,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_NEXT_WEEKLY_DESC','7','业务',7,'0SA','','');

------------------------------------------------------------------------------------------------------------------------------
--data
select * from Check_weekly_list;
select * from check_weekly_list_attach;

create sequence Check_weekly_list_seq
increment by 1
start with 1;

create sequence Check_weekly_list_attach_seq
increment by 1
start with 1;

insert into Check_weekly_list 
        (WEEKLY_ID ,SUBJECT_NAME ,CREATE_STAFF_ID ,SUBJECT_CLASS ,CREATE_DATE,
        WEEKLY_DESC ,NEXT_WEEKLY_DESC ,SUBJECT_GROUP_TYPE ,KEY_WORD ,SUBJECT_NOTE ,STAFF_TYPE)
        VALUES
        (1,'moke.js' ,4 ,'2015' ,to_date('2018-12-11 10:01:00','yyyy-mm-dd hh24:mi:ss')
         ,'学习了使用moke.js' ,'2' ,'2' , 'moke' , '学习了moke的使用方法，js的简单书写' , '1');
insert into Check_weekly_list 
        (WEEKLY_ID ,SUBJECT_NAME ,CREATE_STAFF_ID ,SUBJECT_CLASS ,CREATE_DATE,
        WEEKLY_DESC ,NEXT_WEEKLY_DESC ,SUBJECT_GROUP_TYPE ,KEY_WORD ,SUBJECT_NOTE ,STAFF_TYPE)
        VALUES
        (Check_weekly_list_seq.nextval,'ElementUI.js' ,5 ,'2015' ,to_date('2018-12-11 10:01:00','yyyy-mm-dd hh24:mi:ss')
         ,'学习了使用ElementUI.js' ,'2' ,'2' , 'ElementUI' , '学习了ElementUI的使用方法，js的简单书写' , '1');
 insert into Check_weekly_list 
        (WEEKLY_ID ,SUBJECT_NAME ,CREATE_STAFF_ID ,SUBJECT_CLASS ,CREATE_DATE,
        WEEKLY_DESC ,NEXT_WEEKLY_DESC ,SUBJECT_GROUP_TYPE ,KEY_WORD ,SUBJECT_NOTE ,STAFF_TYPE)
        VALUES
        (Check_weekly_list_seq.nextval,'springBoot' ,5 ,'2015' ,to_date('2018-12-11 10:01:00','yyyy-mm-dd hh24:mi:ss')
         ,'学习了使用SpringBoot' ,'2' ,'2' , 'SpringBoot' , '学习了SpringBoot的使用方法，springboot配置简单书写' , '1');    
insert into Check_weekly_list
  (WEEKLY_ID,
   SUBJECT_NAME,
   CREATE_STAFF_ID,
   SUBJECT_CLASS,
   CREATE_DATE,
   WEEKLY_DESC,
   NEXT_WEEKLY_DESC,
   SUBJECT_GROUP_TYPE,
   KEY_WORD,
   SUBJECT_NOTE,
   STAFF_TYPE)
VALUES
  (Check_weekly_list_seq.nextval,
   #{subjectName},
   #{createStaffId},
   #{subjectClass},
   to_date(#{createDate}, 'yyyy-mm-dd hh24:mi:ss'),
   #{weeklyDesc},
   #{nextWeeklyDesc},
   #{nextWeeklyDesc},
   #{subjectGroupType},
   #{subjectNote},
   #{staffType})
 
 
 insert into Check_weekly_list_attach
        (ATTACH_ID ,ATTACH_NAME ,ATTACH_REMARK , WEEKLY_ID)
        VALUES
        (1 ,'附件一' ,'www.baidu.com' ,1);
   insert into Check_weekly_list_attach
        (ATTACH_ID ,ATTACH_NAME ,ATTACH_REMARK , WEEKLY_ID)
        VALUES
        (Check_weekly_list_attach_seq.nextval ,'附件二' ,'www.baidu.com' ,1);
 insert into Check_weekly_list_attach
        (ATTACH_ID ,ATTACH_NAME ,ATTACH_REMARK , WEEKLY_ID)
        VALUES
        (Check_weekly_list_attach_seq.nextval ,'附件二' ,'www.baidu.com' ,2);
  insert into Check_weekly_list_attach
        (ATTACH_ID ,ATTACH_NAME ,ATTACH_REMARK , WEEKLY_ID)
        VALUES
        (Check_weekly_list_attach_seq.nextval ,'附件一' ,'www.baidu.com' ,2);
  insert into Check_weekly_list_attach
        (ATTACH_ID ,ATTACH_NAME ,ATTACH_REMARK , WEEKLY_ID)
        VALUES
        (Check_weekly_list_attach_seq.nextval ,'附件一' ,'www.baidu.com' ,3);
  insert into Check_weekly_list_attach
        (ATTACH_ID ,ATTACH_NAME ,ATTACH_REMARK , WEEKLY_ID)
        VALUES
        (Check_weekly_list_attach_seq.nextval ,'附件二' ,'www.baidu.com' ,3);
        
        
  SELECT cwl.*, cwla.*, st.*
        FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl) cwl,
        CHECK_WEEKLY_LIST_ATTACH cwla,staff st
        WHERE
        cwl.weekly_id = cwla.weekly_id and cwl.create_staff_id = st.staff_id
        and cwl.weekly_id=1
        
   SELECT cwl.*, cwla.*, st.*
        FROM (select rownum rn,cwl.weekly_id,cwl.subject_name,cwl.create_staff_id,
        cwl.subject_class,cwl.weekly_desc,cwl.next_weekly_desc,cwl.subject_group_type
        ,cwl.key_word,cwl.subject_note,cwl.staff_type,
        to_char(cwl.create_Date,'yyyy-mm-dd hh24:mi:ss') ccd
        from CHECK_WEEKLY_LIST cwl) cwl,
        CHECK_WEEKLY_LIST_ATTACH cwla,staff st
        where
        cwl.weekly_id = cwla.weekly_id and cwl.create_staff_id = st.staff_id
         AND (cwl.weekly_desc like '%2018-12-11%'
                    OR ccd like '%2018-12-11%'
                    OR st.user_name like '%2018-12-11%');
                    
    SELECT cwl.*, cwla.*,st.*
        FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl where rownum <=3) cwl,
        CHECK_WEEKLY_LIST_ATTACH cwla,staff st
        where cwl.weekly_id = cwla.weekly_id and cwl.create_staff_id = st.staff_id
        and cwl.rn
        > 0;
        
        select * from CHECK_WEEKLY_LIST;
        
        
        SELECT cwl.*, cwla.*,st.*
        FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl where rownum
        <= 2) cwl,
        CHECK_WEEKLY_LIST_ATTACH cwla,staff st
        where cwl.weekly_id = cwla.weekly_id and cwl.create_staff_id = st.staff_id
        and cwl.rn> 0


select * from CHECK_WEEKLY_LIST;
select * from check_weekly_list_attach;

SELECT cwl.*, cwla.*, st.*
  FROM (select rownum rn,
               cwl.weekly_id,
               cwl.subject_name,
               cwl.create_staff_id,
               cwl.subject_class,
               cwl.weekly_desc,
               cwl.next_weekly_desc,
               cwl.subject_group_type,
               cwl.key_word,
               cwl.SUBJECT_NOTE,
               cwl.staff_type,
               to_char(cwl.create_Date, 'yyyy-mm-dd hh24:mi:ss') ccd
          from CHECK_WEEKLY_LIST cwl) cwl,
       CHECK_WEEKLY_LIST_ATTACH cwla,
       staff st
 WHERE cwl.weekly_id = cwla.weekly_id
   and cwl.create_staff_id = st.staff_id
   AND (cwl.weekly_desc LIKE % ? % OR ccd like % ? % OR st.user_name like % ? %)
   
 
