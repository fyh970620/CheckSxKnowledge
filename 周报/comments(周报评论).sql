alter table CHECK_WEEKLY_LIST_COMMENT
   drop constraint FK_CHECK_WE_REFERENCE_CHECK_WE;

drop table CHECK_WEEKLY_LIST_COMMENT cascade constraints;

/*==============================================================*/
/* Table: CHECK_WEEKLY_LIST_COMMENT                             */
/*==============================================================*/
create table CHECK_WEEKLY_LIST_COMMENT  (
   COMMENT_ID           NUMBER(9)                       not null,
   COMMENT_PARENT_ID    NUMBER(9),
   "COMMENT"            VARCHAR2(4000),
   COMMENT_GRADE        NUMBER(9,2),
   COMMENT_DATE         DATE,
   COMMENT_STAFF_ID     NUMBER(9),
   WEEKLY_ID            NUMBER(9),
   constraint PK_CHECK_WEEKLY_LIST_COMMENT primary key (COMMENT_ID)
);

comment on column CHECK_WEEKLY_LIST_COMMENT.COMMENT_ID is
'主键ID';
comment on column CHECK_WEEKLY_LIST_COMMENT.COMMENT_PARENT_ID is
'评论回复归于哪个评论ID';
comment on column CHECK_WEEKLY_LIST_COMMENT."COMMENT" is
'评论内容';
comment on column CHECK_WEEKLY_LIST_COMMENT.COMMENT_GRADE is
'评论分数';
comment on column CHECK_WEEKLY_LIST_COMMENT.COMMENT_DATE is
'评论时间';
comment on column CHECK_WEEKLY_LIST_COMMENT.COMMENT_STAFF_ID is
'评论人';
comment on column CHECK_WEEKLY_LIST_COMMENT.WEEKLY_ID is
'周报ID';

alter table CHECK_WEEKLY_LIST_COMMENT
   add constraint FK_CHECK_WE_REFERENCE_CHECK_W2 foreign key (WEEKLY_ID)
      references CHECK_WEEKLY_LIST (WEEKLY_ID);
      
select * from CHECK_WEEKLY_LIST;
select * from CHECK_WEEKLY_LIST_COMMENT;   
select * from staff;
----------------------------------------------------------------------------------------------------------------------------
--data
create sequence CHECK_WEEKLY_LIST_COMMENT_seq
increment by 1
start with 1;

select * from CHECK_WEEKLY_LIST_COMMENT;
select avg(comment_grade) from CHECK_WEEKLY_LIST_COMMENT where weekly_Id=1 and comment_parent_id=0;


insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(1,0,'顶级评论内容',100,to_date('2019-1-9 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1);   
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,0,'顶级评论内容2',100,to_date('2019-1-9 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,0,'顶级评论内容3',100,to_date('2019-1-9 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,0,'顶级评论内容4',100,to_date('2019-1-9 13:11:23','yyyy-mm-dd hh24:mi:ss'),5,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,0,'顶级评论内容5',100,to_date('2019-1-9 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,1,'二级评论内容',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),5,1);    
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,1,'二级评论内容2',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1);   
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,2,'二级评论内容3',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1);  
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,86,'评论内容5',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1);  
   select * from check_weekly_list_comment;
   select * from check_weekly_list;
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,67,'三级评论内容5',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,67,'三级评论内容6',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,67,'三级评论内容6',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),4,1); 
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(CHECK_WEEKLY_LIST_COMMENT_seq.nextval,68,'三级评论内容6',90,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),5,1); 
--根数据
insert into CHECK_WEEKLY_LIST_COMMENT(
   COMMENT_ID,
   COMMENT_PARENT_ID,
   "COMMENT",
   COMMENT_GRADE,
   COMMENT_DATE,
   COMMENT_STAFF_ID,
   WEEKLY_ID)
   values(0, '','',100,to_date('2019-1-10 13:11:23','yyyy-mm-dd hh24:mi:ss'),'','');    
   
-------------------------------------------------------------------------------------------------------------------------

SELECT cwl.*, cwlc.*,st.staff_name,st.staff_Id
        FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl where rownum<= 4) cwl,
        CHECK_WEEKLY_LIST_COMMENT cwlc,staff st
        where cwl.weekly_id = cwlc.weekly_id and cwl.create_staff_id = st.staff_id and cwlc.comment_staff_id=st.staff_id
        and cwl.rn
        >0;
    

 SELECT cwl.*, cwla.*,st.*
        FROM (select rownum rn, cwl.* from CHECK_WEEKLY_LIST cwl ) cwl,
        CHECK_WEEKLY_LIST_ATTACH cwla,staff st
        where cwl.weekly_id = cwla.weekly_id and cwl.create_staff_id = st.staff_id
 select * from check_weekly_List;
 select * from CHECK_WEEKLY_LIST_COMMENT;     
 
 delete from CHECK_WEEKLY_LIST_COMMENT where comment_id=42;
 
select * from CHECK_WEEKLY_LIST_COMMENT where weekly_id=1;

select * from check_know_list where know_Id =129;

select * from CHECK_WEEKLY_LIST_COMMENT start with comment_id=0 connect by prior comment_id =  comment_parent_id;
delete from CHECK_WEEKLY_LIST_COMMENT  where  comment_id=6;

select * from check_weekly_list_comment;

select * from
(select * from CHECK_WEEKLY_LIST_COMMENT cwlc
        start with comment_id=0
        connect by prior
        comment_id =  comment_parent_id) cwl where cwl.weekly_Id = 1 

  select * from
        (select * from CHECK_WEEKLY_LIST_COMMENT cwlc
                start with comment_id=67
                connect by prior
                comment_id = comment_parent_id) cwl
        where cwl.weekly_Id = 1
        
        
  select * from       CHECK_WEEKLY_LIST_COMMENT;
  SELECT * FROM CHECK_WEEKLY_LIST_COMMENT WHERE COMMENT_ID=1
  delete from CHECK_WEEKLY_LIST_COMMENT where comment_parent_id=4;
        
        
        
        
        
        
        
        
        
        
        
        
        
