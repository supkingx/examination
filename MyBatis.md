# 一、**mybatis中的#和$的区别：**

1、#{变量名}可以进行预编译、类型匹配等操作，#{变量名}会转化为jdbc的类型。 select * from tablename where id = #{id} 假设id的值为12，其中如果[数据库](https://cloud.tencent.com/solution/database?from=10680)字段id为字符型，那么#{id}表示的就是'12'，如果id为整型，那么id就是12，并且MyBatis会将上面SQL语句转化为jdbc的select * from tablename where id=?，把?参数设置为id的值。

 2、${变量名}不进行数据类型匹配，直接替换。 select * from tablename where id = ${id} 如果字段id为整型，sql语句就不会出错，但是如果字段id为字符型， 那么sql语句应该写成select * from table where id = '${id}'。 #方式能够很大程度防止sql注入。 $方式无法方式sql注入。 $方式一般用于传入数据库对象，例如传入表名。 尽量多用#方式，少用$方式。

3、什么是sql注入？为什么#能防止sql注入

sql注入：delete * from table where id=${}，这这时可以填入一个值 1，但是也可以填入 **1 OR 1=1**

即：delete * from table where id= 1 OR 1=1，这时就会删除所有数据，这就是漏洞！

为什么#能防止sql注入呢？因为#用到了预编译（prepareStatement）。

4、什么是预编译呢？

> 一条sql语句执行，需要进过语义解析，指定执行计划，执行并返回结果
>
> 而预编译的sql在执行sql的时候则直接进行执行计划，不会在进行语义解析，也就是DB不会在进行编译，而是直接执行编译过的sql。

所以 delete * from table where id=#{}，经过mybatis的时候，会被写成 delete * from table where id= ?，然后使用prepareStatement进行预编译，而其后注入的参数将不会再进行SQL编译。也就是说其后注入进来的参数系统将不会认为它会是一条SQL语句，而默认其是一条一个参数，即delete * from table where id=1，如果值是 1 or 1=1，那会报错，因为此时mysql会把1 or 1=1当成一个值，这明显是错误的。

# 二、mybatis中的二级缓存

1、一级缓存默认开启，所以在参数和SQL完全一样的情况下，我们使用同一个SqlSession对象调用一个Mapper方法，往往只执行一次SQL，因为使用SelSession第一次查询后，MyBatis会将其放在缓存中，以后再查询的时候，如果没有声明需要刷新，并且缓存没有超时的情况下，SqlSession都会取出当前缓存的数据，而不会再次发送SQL到数据库。

注意：MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象

2、二级缓存

SqlSessionFactory层面上的二级缓存默认是不开启的，二级缓存的开席需要进行配置，实现二级缓存的时候，MyBatis要求返回的POJO必须是可序列化的。 也就是要求实现Serializable接口，配置方法很简单，只需要在映射XML文件配置就可以开启缓存了<cache/>

```xml
<cache eviction="LRU" flushInterval="100000" readOnly="true" size="1024"/>

<!--可以通过设置useCache来规定这个sql是否开启缓存，ture是开启，false是关闭-->
    <select id="selectAllStudents" resultMap="studentMap" useCache="true">
        SELECT id, name, age FROM student
    </select>
    <!--刷新二级缓存
    <select id="selectAllStudents" resultMap="studentMap" flushCache="true">
        SELECT id, name, age FROM student
    </select>
    -->
```

