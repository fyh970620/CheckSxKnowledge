

# {{项目名}}

{{说明}}

## 开发环境要求
- jdk 1.8
- gradle 4.1或以上版本
- 数据库： oracle

## 基础技术框
- spring boot2
- mybatis

## 启动服务:
```
gradlew bootRun
```

## 打包

```
gradlew clean build
```

## 前台文件

```
--由ffitm-knowledges打包生成
src\main\resources\static
--src\main\resources\static\app\resources\ffitm-knowledges
--src\main\resources\static\knowledges
```

## 验证

访问: http://127.0.0.1:8080/KnowledgesController/index

可配置单点登录

http://localhost:7777/common/SingleSignOn?type=SingleAccess&username=chenfu&sourceid=2566&config=BO_SIGLE_ADDRESS&noKey=Y&noCheck=Y

select t.*,rowid from property_config t where t.code = 'BO_SIGLE_ADDRESS';