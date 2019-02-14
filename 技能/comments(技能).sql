select t.* from tp_domain t where  domain_code='DOMAIN_PROJECT_INVEST_TYPE' domain_code='DOMAIN_BOOLEAN'
select t.* from tp_domain_dependencies t where table_name='STAFF_ORG_RELATION';
select t.* from tp_domain_listvalues t;

select t.* from tp_domain_dependencies t where column_name='IS_DEFAULT'

delete from tp_domain t where Domain_code='DOMAIN_KNOW_LIST_TYPE';
delete from tp_domain_dependencies t where domain_code ='DOMAIN_KNOW_LIST_TYPE';

insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_KNOW_LIST_TYPE','������������','VARCHAR2(20)','');
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_KNOW_LIST_TYPE','CHECK_KNOW_LIST','KNOW_TYPE');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','1','JAVA',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','2','ǰ��',2,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','3','��Դ',3,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','4','��̨',4,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','5','���ݿ�',5,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','6','����',6,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','7','ҵ��',7,'0SA','','');
--------------------------------------------------------------------------------------------------------------------------------------
insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_KNOW_GROUP_TYPE','���ܹ��������','VARCHAR2(20)','');
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_KNOW_GROUP_TYPE','CHECK_KNOW_LIST','KNOW_GROUP_TYPE');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_GROUP_TYPE','1','ǰ̨',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_GROUP_TYPE','2','��̨',2,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_GROUP_TYPE','3','����',3,'0SA','','');



select * from tp_domain_listvalues where DOMAIN_CODE='DOMAIN_KNOW_LIST_TYPE';

select * from STAFF_ORG_RELATION
select * from check_know_list


select t.* from tp_domain_dependencies t where domain_code='DOMAIN_PROJECT_INVEST_TYPE';
------------------------------------------------------------------------------------------------------------------------------------------
--sql

select ckl.know_name,ckl.know_time,ckl.know_remark,ckl.know_dest,ckla.attach_name,ATTACH_ID,ckl.know_id
            from check_know_list ckl
            left join check_know_list_attach ckla
            on ckl.know_id = ckla.know_id


delete from check_know_list where know_id=2;
delete from check_know_list_attach where attach_id=2;
update check_know_list set know_time=6 where know_id=1;
update check_know_list_attach set attach_name='��������update' where attach_id=2;

DROP SEQUENCE check_know_list_seq;
DROP SEQUENCE check_know_list_attach_seq;
create sequence check_know_list_seq
increment by 1
start with 1;
create sequence check_know_list_attach_seq
increment by 1
start with 1;

select * from check_know_list;
select * from check_know_list_attach;
-------------------------------------------------------------------------------------------------
--data check_know_list
--java-1 ��̨-2
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(1,'J2SE','����������/JAVA�����﷨/�������/JAVA������/JAVA IO/JAVA�쳣����/JAVA���߳�/���ģʽ','1','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'J2EE','Servlet/JDBC/SpringMVC/Springע��/ʹ��Dom4j����XML/Json/mybatis����Ҫ����Ӧ���˽�Spring BOOT/�˽�SpringCloud΢�������','1','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'�ӿ�','webService/restful/rpc','1','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'��Ϣ','AMQP/RibbitMQ/�˽��䴫��Э��/��ϵ�ṹ/ģ��','1','���ռ�����',3,'2');
--ǰ��-2  ǰ̨-1
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'JavaScript','ԭ��JavaScript/ajax/����dom/����xml/json/���ݶ���/������ʽ/�հ���/�˽�ES6�﷨','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Jquery','','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ExtJs','','2','���ռ�����',3,'1');

insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Bootstrap','ǰ����ʽ�������/������','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'AnjularJs','','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Vue','ǰ�˻������/������','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'elementUI','���׵�vue��Դ�����','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'LodashJs','������Ĺ��߿�/������/�����첻��Ҫ������','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Moment.js','ʱ�����������','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'webPack','','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'HTML/CSS','','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'echarts','ͼ���','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'npm��yarn','ǰ�˿��������������װ����','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'twaver','','2','���ռ�����',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'NodeJS','','2','���ռ�����',3,'1');
--���ݿ�-5 ��̨-2
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Oracle','','5','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'mysql','','5','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'postgreSQL','','5','���ռ�����',3,'2');
--��̨-4 ��̨-2
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'C++/C','','4','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'linux','��������/vi �༭����������','4','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'oracle','','4','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'lua�﷨','','4','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'python','','4','���ռ�����',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'shell','','4','���ռ�����',3,'2');
--��Դ-3 ����-3
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Solr','','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Hbase','','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ES��ѯ','','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'������Hadoop��̬Ȧ','�����˽⼴��:HDFS/Zookeeper/Yarn/Ambari/Strom','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Opentsdb','ָ��洢/���ص�ѧϰ','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ELK Stack','�ͷ���BO�˵��˶��õ�/�����ص�ѧϰ','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Spark','��Ҫ����ָ�����/��BO��˷�չ��ѧϰ','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Kafka','BO�˵����õ��Ĵ�������Ϣ���/��������ȡ��Rabbitmq/���ص�ѧϰ','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Cassandra','Bo�˵��������洢�굥���ݵ�/��BO��˷�չ��ѧϰ','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Redis','BO�˵�����Ҫ��Ϊ��Դ������/���ص�ѧϰ','3','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ELK','����/�ɼ�/��ѯ','3','���ռ�����',3,'3');
--ҵ��-7 ��̨-3
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Spider','֪ʶ�����','7','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'itsmQuartz','�����ʼ�����','7','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'TimingRoute','��ʱ�������','7','���ռ�����',3,'3');
--����-6 ��̨-3
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Weblogic','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'PL/SQL','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Eclipse/MyEclipse/Idea','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'SVN','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Gradle','�Զ����������ߣ�JAVA��Ŀ�������������˽�','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'SoapUI','����webService�ӿڵĹ���','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'FTP','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'SSH/Telnet����','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Git','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'httpwatch','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'fiddler','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'postman','','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Jenkins','�Զ��������','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'�Զ�Ͷ�Ź���','�Զ��򲹶�����','6','���ռ�����',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Docker','�������档΢����Ӧ�ò����������','6','���ռ�����',3,'3');
--data check_know_list_attach
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(1,'����һ','www.baidu.com',1);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',1);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',2);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',2);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',3);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',3);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',4);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',4);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',5);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',5);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',6);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',6);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',7);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',7);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',8);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',8);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',9);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',9);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',10);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',10);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',11);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',11);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',12);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',13);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',14);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',14);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',15);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',16);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',17);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',18);


insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',19);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',20);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',20);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',20);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',21);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',22);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',23);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',24);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',25);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',26);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',27);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',28);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',28);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',29);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',30);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',31);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',32);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',32);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',33);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',33);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',34);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',35);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',36);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',37);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',38);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',39);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',40);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',41);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',42);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',42);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',43);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',44);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',45);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',46);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',47);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',48);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',49);

insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',50);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',51);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',51);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',52);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',53);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'����һ','www.baidu.com',54);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',55);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',56);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'������','www.baidu.com',57);

select * from check_know_list
   select ckl.know_name,ckl.know_time,ckl.know_remark,ckl.know_dest,ATTACH_ID,ckl.know_id
            from check_know_list ckl
            
            left join check_know_list_attach ckla
            on ckl.know_id = ckla.know_id
            where know_group_type='2'
            
            select ckl.know_name,ckl.know_time,ckl.know_remark,ckl.know_dest,ckl.know_id from check_know_list ckl;
            
            select rownum rn,ckl.know_name,ckl.know_time,ckl.know_remark,ckl.know_dest,ckl.know_id from check_know_list ckl where rn between 1 and 10;
            (select ckla.attach_name,ATTACH_ID from check_know_list_attach ckla)


SELECT t1.*
  FROM (select rownum rn, t.* from check_know_list t where rownum <= 5) t1
 where t1.rn > 2;

SELECT ckl.*, ckla.*
  FROM (select rownum rn, t.* from check_know_list t where rownum < 3) ckl,
       check_know_list_attach ckla 
 where ckl.know_id = ckla.know_id 
   and ckl.rn > 0;
   
   select * from check_know_list;
   
      SELECT ckl.*, ckla.*
                FROM (select rownum rn, t.* from check_know_list ckl where rownum <= 5 and know_group_type=1 ) ckl,
                check_know_list_attach ckla
                where ckl.know_id = ckla.know_id
                and ckl.rn > 0;
                
                
 SELECT ckl.*, ckla.*
                FROM (select rownum rn, ckl.* from check_know_list ckl where know_group_type=1 ) ckl,
                check_know_list_attach ckla
                where ckl.know_id = ckla.know_id;
                
          select ckl.know_name,ckl.know_time,ckl.know_remark,ckl.know_dest,ckla.attach_name,ATTACH_ID,ckl.know_id
            from check_know_list ckl
            left join check_know_list_attach ckla
            on ckl.know_id = ckla.know_id
            where know_group_type='2'
        
                
