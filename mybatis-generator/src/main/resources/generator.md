### MyBatis Generator 详解
https://blog.csdn.net/isea533/article/details/42102297
### mybatis generator 自动生成点控制
1. 可以获取到插入数据时的自增id
2. tinyint 处理为 Integer
```
<table tableName="download_area" domainObjectName="DownloadArea" >
    <!--  自动获取自增id              -->
    <generatedKey column="id" sqlStatement="MySql" identity="true"/>
    <!--          控制生成实体的数据类型:tinyint 处理为 Integer     -->
    <columnOverride column="top" javaType="java.lang.Integer"/>
    <columnOverride column="type" javaType="java.lang.Integer"/>
    <columnOverride column="valid" javaType="java.lang.Integer"/>
</table>
```
3. 使用Java 8 的 LocalDateTime
```
 1.
 generatorConfig.xml 新增配置属性
      <property name="useJSR310Types" value="true"/>

 2. 
        <!-- MyBatis 支持 java8 LocalDateTime 映射解析依赖      -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-typehandlers-jsr310</artifactId>
            <version>1.0.2</version>
        </dependency>
 3.
```