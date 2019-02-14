select t.* from tp_domain t where  domain_code='DOMAIN_PROJECT_INVEST_TYPE' domain_code='DOMAIN_BOOLEAN'
select t.* from tp_domain_dependencies t where table_name='STAFF_ORG_RELATION';
select t.* from tp_domain_listvalues t;

select t.* from tp_domain_dependencies t where column_name='IS_DEFAULT'

delete from tp_domain t where Domain_code='DOMAIN_KNOW_LIST_TYPE';
delete from tp_domain_dependencies t where domain_code ='DOMAIN_KNOW_LIST_TYPE';

insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_KNOW_LIST_TYPE','技能类型名称','VARCHAR2(20)','');
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_KNOW_LIST_TYPE','CHECK_KNOW_LIST','KNOW_TYPE');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','1','JAVA',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','2','前端',2,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','3','开源',3,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','4','后台',4,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','5','数据库',5,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','6','工具',6,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_LIST_TYPE','7','业务',7,'0SA','','');
--------------------------------------------------------------------------------------------------------------------------------------
insert into tp_domain t(DOMAIN_CODE,DOMAIN_NAME,DOMAIN_DATA_TYPE,COMMENTS) values('DOMAIN_KNOW_GROUP_TYPE','技能归属组分类','VARCHAR2(20)','');
insert into tp_domain_dependencies t(Domain_code,table_name,column_name) values('DOMAIN_KNOW_GROUP_TYPE','CHECK_KNOW_LIST','KNOW_GROUP_TYPE');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_GROUP_TYPE','1','前台',1,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_GROUP_TYPE','2','后台',2,'0SA','','');
insert into tp_domain_listvalues t(DOMAIN_CODE,LIST_VALUE,LIST_LABEL,SORT_ID,STATE,REMARK,UNIFY_CODE)
values('DOMAIN_KNOW_GROUP_TYPE','3','工程',3,'0SA','','');



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
update check_know_list_attach set attach_name='附件名称update' where attach_id=2;

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
--java-1 后台-2
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(1,'J2SE','面向对象概念/JAVA基本语法/基础编程/JAVA集合类/JAVA IO/JAVA异常处理/JAVA多线程/设计模式','1','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'J2EE','Servlet/JDBC/SpringMVC/Spring注解/使用Dom4j操作XML/Json/mybatis均需要熟练应用了解Spring BOOT/了解SpringCloud微服务组件','1','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'接口','webService/restful/rpc','1','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'消息','AMQP/RibbitMQ/了解其传输协议/体系结构/模型','1','掌握技术点',3,'2');
--前端-2  前台-1
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'JavaScript','原生JavaScript/ajax/操作dom/操作xml/json/数据对象/正则表达式/闭包等/了解ES6语法','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Jquery','','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ExtJs','','2','掌握技术点',3,'1');

insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Bootstrap','前端样式基础框架/需掌握','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'AnjularJs','','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Vue','前端基础框架/需掌握','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'elementUI','配套的vue开源组件库','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'LodashJs','最基础的工具库/需掌握/避免造不必要的轮子','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Moment.js','时间操作工具类','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'webPack','','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'HTML/CSS','','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'echarts','图表库','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'npm、yarn','前端开发软件依赖包安装工具','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'twaver','','2','掌握技术点',3,'1');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'NodeJS','','2','掌握技术点',3,'1');
--数据库-5 后台-2
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Oracle','','5','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'mysql','','5','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'postgreSQL','','5','掌握技术点',3,'2');
--后台-4 后台-2
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'C++/C','','4','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'linux','基础命令/vi 编辑器基础操作','4','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'oracle','','4','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'lua语法','','4','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'python','','4','掌握技术点',3,'2');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'shell','','4','掌握技术点',3,'2');
--开源-3 工程-3
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Solr','','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Hbase','','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ES查询','','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'大数据Hadoop生态圈','进行了解即可:HDFS/Zookeeper/Yarn/Ambari/Strom','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Opentsdb','指标存储/可重点学习','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ELK Stack','客服和BO端到端都用到/可做重点学习','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Spark','主要用作指标计算/往BO后端发展可学习','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Kafka','BO端到端用到的大数据消息组件/后续可能取代Rabbitmq/可重点学习','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Cassandra','Bo端到端用作存储详单数据等/往BO后端发展可学习','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Redis','BO端到端主要作为资源树缓存/可重点学习','3','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'ELK','部署/采集/查询','3','掌握技术点',3,'3');
--业务-7 后台-3
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Spider','知识库程序','7','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'itsmQuartz','短信邮件程序','7','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'TimingRoute','定时任务程序','7','掌握技术点',3,'3');
--工具-6 后台-3
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Weblogic','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'PL/SQL','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Eclipse/MyEclipse/Idea','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'SVN','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Gradle','自动化构建工具，JAVA项目构建基础，需了解','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'SoapUI','调测webService接口的工具','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'FTP','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'SSH/Telnet工具','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Git','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'httpwatch','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'fiddler','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'postman','','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Jenkins','自动打包编译','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'自动投放工具','自动打补丁工具','6','掌握技术点',3,'3');
insert into check_know_list(KNOW_ID, KNOW_NAME, KNOW_REMARK, KNOW_TYPE,KNOW_DEST,KNOW_TIME,KNOW_GROUP_TYPE)
values
(check_know_list_seq.nextval,'Docker','容器引擎。微服务应用部署基础工具','6','掌握技术点',3,'3');
--data check_know_list_attach
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(1,'附件一','www.baidu.com',1);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',1);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',2);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',2);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',3);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',3);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',4);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',4);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',5);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',5);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',6);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',6);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',7);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',7);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',8);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',8);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',9);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',9);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',10);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',10);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',11);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',11);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',12);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',13);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',14);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',14);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',15);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',16);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',17);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',18);


insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',19);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',20);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',20);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',20);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',21);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',22);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',23);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',24);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',25);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',26);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',27);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',28);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',28);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',29);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',30);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',31);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',32);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',32);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',33);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',33);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',34);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',35);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',36);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',37);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',38);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',39);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',40);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',41);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',42);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',42);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',43);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',44);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',45);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',46);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',47);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',48);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',49);

insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',50);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',51);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',51);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',52);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',53);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件一','www.baidu.com',54);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',55);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',56);
insert into check_know_list_attach(ATTACH_ID,ATTACH_NAME,ATTACH_REMARK,KNOW_ID)
values
(check_know_list_attach_seq.nextval,'附件二','www.baidu.com',57);

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
        
                
