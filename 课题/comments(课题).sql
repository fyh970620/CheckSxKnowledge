drop table CHECK_SUBJECT_LIST cascade constraints;

/*==============================================================*/
/* Table: CHECK_SUBJECT_LIST                                    */
/*==============================================================*/
create table CHECK_SUBJECT_LIST  (
   SUBJECT_ID           NUMBER(9)                       not null,
   SUBJECT_NAME         VARCHAR2(200),
   SUBJECT_REMARK       LONG,
   SUBJECT_TYPE         VARCHAR2(20)                   
      constraint CKC_SUBJECT_TYPE_CHECK_SU check (SUBJECT_TYPE is null or (SUBJECT_TYPE in ('1','2','3','4','5','6','7'))),
   SUBJECT_DEST         VARCHAR2(200),
   SUBJECT_TIME         NUMBER(5),
   SUBJECT_GROUP_TYPE   VARCHAR2(20)                   
      constraint CKC_SUBJECT_GROUP_TYP_CHECK_SU check (SUBJECT_GROUP_TYPE is null or (SUBJECT_GROUP_TYPE in ('1','2','3'))),
   constraint PK_CHECK_SUBJECT_LIST primary key (SUBJECT_ID)
);

comment on column CHECK_SUBJECT_LIST.SUBJECT_ID is
'����ID';
comment on column CHECK_SUBJECT_LIST.SUBJECT_NAME is
'��������';
comment on column CHECK_SUBJECT_LIST.SUBJECT_REMARK is
'��������';
comment on column CHECK_SUBJECT_LIST.SUBJECT_TYPE is
'��������';
comment on column CHECK_SUBJECT_LIST.SUBJECT_DEST is
'ѧϰĿ��';
comment on column CHECK_SUBJECT_LIST.SUBJECT_TIME is
'ѧϰ����';
comment on column CHECK_SUBJECT_LIST.SUBJECT_GROUP_TYPE is
'���ܹ��������1ǰ̨2��̨3����';

alter table CHECK_SUBJECT_LIST_ATTACH
   drop constraint FK_CHECK_SU_REFERENCE_CHECK_SU;

drop table CHECK_SUBJECT_LIST_ATTACH cascade constraints;

/*==============================================================*/
/* Table: CHECK_SUBJECT_LIST_ATTACH                             */
/*==============================================================*/
create table CHECK_SUBJECT_LIST_ATTACH  (
   ATTACH_ID            NUMBER(9)                       not null,
   ATTACH_NAME          VARCHAR2(200),
   ATTACH_REMARK        VARCHAR2(2000),
   SUBJECT_ID           NUMBER(9),
   constraint PK_CHECK_SUBJECT_LIST_ATTACH primary key (ATTACH_ID)
);


comment on column CHECK_SUBJECT_LIST_ATTACH.ATTACH_ID is
'����ID';

comment on column CHECK_SUBJECT_LIST_ATTACH.ATTACH_NAME is
'��������';

comment on column CHECK_SUBJECT_LIST_ATTACH.ATTACH_REMARK is
'�������ڷ�����·��';

comment on column CHECK_SUBJECT_LIST_ATTACH.SUBJECT_ID is
'����ID';

alter table CHECK_SUBJECT_LIST_ATTACH
   add constraint FK_CHECK_SU_REFERENCE_CHECK_SU foreign key (SUBJECT_ID)
      references CHECK_SUBJECT_LIST (SUBJECT_ID);

select * from staff;
 SELECT count(*) FROM  CHECK_STAFF_INFO
        WHERE
        STAFF_ID=999999
---------------------------------------------------------------------------------------------------------------------------------------------
--data
create sequence CHECK_SUBJECT_LIST_SEQ
increment by 1
start with 1;

create sequence CHECK_SUBJECT_LIST_ATTACH_SEQ
increment by 1
start with 1;


 SELECT * FROM CHECK_SUBJECT_LIST CSL
        LEFT JOIN
          CHECK_SUBJECT_LIST_ATTACH ASLA
        ON
          CSL.SUBJECT_ID = ASLA.SUBJECT_ID;

insert into CHECK_SUBJECT_LIST
  (SUBJECT_ID,
   SUBJECT_NAME,
   SUBJECT_REMARK,
   SUBJECT_TYPE,
   SUBJECT_DEST,
   SUBJECT_TIME,
   SUBJECT_GROUP_TYPE)
values
  (CHECK_SUBJECT_LIST_SEQ.nextval,
   '�����',
   '�����������',
   '2',
   '�������ѧϰĿ��',
   3�� '3');
   
   
 insert into CHECK_SUBJECT_LIST_ATTACH(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,SUBJECT_ID)
 values(CHECK_SUBJECT_LIST_ATTACH_SEQ.nextval,'������','������������',1);
 
select * from Check_Subject_List_Attach;

delete from Check_Subject_List_Attach where attach_id = 13;
 
select * from check_know_list_attach;

SELECT * FROM CHECK_SUBJECT_LIST CSL
        LEFT JOIN
          CHECK_SUBJECT_LIST_ATTACH ASLA
        ON
          CSL.SUBJECT_ID = ASLA.SUBJECT_ID
          
            SELECT * FROM CHECK_SUBJECT_LIST CSL
        LEFT JOIN
          CHECK_SUBJECT_LIST_ATTACH ASLA
        ON
          CSL.SUBJECT_ID = ASLA.SUBJECT_ID
        Order by
          CSL.SUBJECT_ID
        ASC 
