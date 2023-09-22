## ע��汾���⣺
Ŀǰ֧��mysql5.7��������Ϊ7.15.0��֧��mysql8.0�İ汾��8.2.0��8.2.1�Ƴ���mysql֧��

## flyway��8.2.1�汾�Ƴ�mysql�Ľ������

��������:
```
<dependency>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-mysql</artifactId>
</dependency>
```

# springboot 3.0 Ŀǰ�ƺ���֧�� 5.7��mysql �� flyway ͬʱʹ��

# ��Ҫ�����ݿ�������ļ��м���: &createDatabaseIfNotExist=true �����Ϳ����Զ�����

## 2. Flyway ֪ʶ����

1. Flyway Ĭ�ϻ�ȥ��ȡ `classpath:db/migration`������ͨ�� `spring.flyway.locations` ȥָ���Զ���·�������·��ʹ�ð��Ӣ�Ķ��ŷָ����ڲ���Դʹ�� `classpath:`���ⲿ��Դʹ�� `file:`

2. �����Ŀ����û�����ݿ��ļ��������������� Flyway����ô����Ŀ������ʱ��Flyway ��ȥ����Ƿ���� SQL �ļ�����ʱ����Ҫ��������رգ�`spring.flyway.check-location = false`

3. Flyway ������Ŀ����������ʱ�򴴽�һ����Ϊ `flyway_schema_history` �ı������ű����¼���ݿ�ű�ִ�е���ʷ��¼����Ȼ�������ͨ�� `spring.flyway.table` ȥ�޸����ֵ

4. Flyway ִ�е� SQL �ű�������ѭһ����������`V<VERSION>__<NAME>.sql` ������ `V` ��Ȼ���ǰ汾�ţ�����汾���ж�����֣�ʹ��`_`�ָ�������`1_0`��`1_1`���汾�ŵĺ����� 2 ���»��ߣ������ SQL �ű������ơ�

   **������Ҫע�⣺V ��ͷ��ֻ��ִ��һ�Σ��´���Ŀ��������ִ�У�Ҳ�������޸�ԭʼ�ļ���������Ŀ�����ᱨ�������Ҫ�� V ��ͷ�Ľű����޸ģ���Ҫ���`flyway_schema_history`������и� SQL �ű���Ҫ��ÿ��������ʱ��ִ�У���ô�� V ��Ϊ `R` ��ͷ����**

5. Flyway Ĭ������»�ȥ���ԭʼ�⣬������ִ�� SQL �ű������������������ǲ���ȡ�ģ������Ҫ��������ùرգ�`spring.flyway.clean-disabled = true`

## 3. application.yml ����

> �����ҵ� application.yml ����

```yaml
spring:
  flyway:
    enabled: true
    # Ǩ��ǰУ�� SQL �ļ��Ƿ��������
    validate-on-migrate: true
    # ��������һ��Ҫ�ر�
    clean-disabled: true
    # У��·�����Ƿ���� SQL �ļ�
    check-location: false
    # �ʼ�Ѿ����ڱ�ṹ���Ҳ����� flyway_schema_history ��ʱ����Ҫ����Ϊ true
    baseline-on-migrate: true
    # �����汾 0
    baseline-version: 0
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/flyway-test?useSSL=false
    username: root
    password: root
    type: com.zaxxer.hikari.HikariDataSource
```

## 4. ����

### 4.1. ���� 1.0 �汾�� SQL �ű�

���� `V1_0__INIT.sql`

```mysql
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����',
  `username` varchar(32) NOT NULL COMMENT '�û���',
  `password` varchar(32) NOT NULL COMMENT '���ܺ������',
  `salt` varchar(32) NOT NULL COMMENT '����ʹ�õ���',
  `email` varchar(32) NOT NULL COMMENT '����',
  `phone_number` varchar(15) NOT NULL COMMENT '�ֻ�����',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT '״̬��-1���߼�ɾ����0�����ã�1������',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `last_login_time` datetime DEFAULT NULL COMMENT '�ϴε�¼ʱ��',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '�ϴθ���ʱ��',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone_number` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='1.0-�û���';
```

������Ŀ�����Կ�����־�����

```
2020-03-05 10:48:37.799  INFO 3351 --- [           main] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 5.2.1 by Boxfuse
2020-03-05 10:48:37.802  INFO 3351 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2020-03-05 10:48:37.971  INFO 3351 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2020-03-05 10:48:37.974  INFO 3351 --- [           main] o.f.c.internal.database.DatabaseFactory  : Database: jdbc:mysql://127.0.0.1:3306/flyway-test (MySQL 5.7)
2020-03-05 10:48:38.039  INFO 3351 --- [           main] o.f.core.internal.command.DbValidate     : Successfully validated 1 migration (execution time 00:00.015s)
2020-03-05 10:48:38.083  INFO 3351 --- [           main] o.f.c.i.s.JdbcTableSchemaHistory         : Creating Schema History table: `flyway-test`.`flyway_schema_history`
2020-03-05 10:48:38.143  INFO 3351 --- [           main] o.f.core.internal.command.DbMigrate      : Current version of schema `flyway-test`: << Empty Schema >>
2020-03-05 10:48:38.144  INFO 3351 --- [           main] o.f.core.internal.command.DbMigrate      : Migrating schema `flyway-test` to version 1.0 - INIT
2020-03-05 10:48:38.156  WARN 3351 --- [           main] o.f.c.i.s.DefaultSqlScriptExecutor       : DB: Unknown table 'flyway-test.t_user' (SQL State: 42S02 - Error Code: 1051)
2020-03-05 10:48:38.183  INFO 3351 --- [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 1 migration to schema `flyway-test` (execution time 00:00.100s)
```

������ݿ⣬���ִ����� 2 �ű�һ���� Flyway ��������ʷ����һ�ž������ǵ� `t_user` ��

<img src="http://static.xkcoding.com/spring-boot-demo/flyway/062903.jpg" alt="image-20200305105632047" style="zoom:50%;" />

�鿴�� `flyway-schema-history` ��

<img src="http://static.xkcoding.com/spring-boot-demo/flyway/062901.jpg" alt="image-20200305110147176" style="zoom:50%;" />

### 4.2. ���� 1.1 �汾�� SQL �ű�

���� `V1_1__ALTER.sql`

```
ALTER TABLE t_user COMMENT = '�û� v1.1';
```

������Ŀ�����Կ�����־�����

```
2020-03-05 10:59:02.279  INFO 3536 --- [           main] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 5.2.1 by Boxfuse
2020-03-05 10:59:02.282  INFO 3536 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2020-03-05 10:59:02.442  INFO 3536 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
2020-03-05 10:59:02.445  INFO 3536 --- [           main] o.f.c.internal.database.DatabaseFactory  : Database: jdbc:mysql://127.0.0.1:3306/flyway-test (MySQL 5.7)
2020-03-05 10:59:02.530  INFO 3536 --- [           main] o.f.core.internal.command.DbValidate     : Successfully validated 2 migrations (execution time 00:00.018s)
2020-03-05 10:59:02.538  INFO 3536 --- [           main] o.f.core.internal.command.DbMigrate      : Current version of schema `flyway-test`: 1.0
2020-03-05 10:59:02.538  INFO 3536 --- [           main] o.f.core.internal.command.DbMigrate      : Migrating schema `flyway-test` to version 1.1 - ALTER
2020-03-05 10:59:02.564  INFO 3536 --- [           main] o.f.core.internal.command.DbMigrate      : Successfully applied 1 migration to schema `flyway-test` (execution time 00:00.029s)
```

������ݿ⣬���Է��� `t_user` ���ע���Ѿ�����

<img src="http://static.xkcoding.com/spring-boot-demo/flyway/062851.jpg" alt="image-20200305105958181" style="zoom:50%;" />

�鿴�� `flyway-schema-history` ��

<img src="http://static.xkcoding.com/spring-boot-demo/flyway/062908.jpg" alt="image-20200305110057768" style="zoom:50%;" />

## �ο�

1. [Spring Boot �ٷ��ĵ� - Migration �½�](https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
2. [Flyway �ٷ��ĵ�](https://flywaydb.org/documentation/)