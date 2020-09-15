##CRUD
1. namespace

   namespace 中的包名要和mapper接口包名一致
   
2. select 

   选择，查询语句
   
      1. id：就是对应的namespace中的方法名
      2. resultType: sql语句执行的返回值!
      3. parameter: 参数类
      
      
   编写接口
   
   ```
         List<User> getUserList();
   ```      
   编写对象的mapper中的SQL语句
   
   ```
   <select id="getUserList" resultType="com.kuang.pojo.User">
            select * from mybatis.user;
   </select>
   ```
        
   测试
  ```    @Test
         public void test() {
     
             try {
                 //第一步：获得sqlSession对象
                sqlSession = MybatisUtils.getSqlSession();
                 //方式一：getMapper
                 UserMapper mapper = sqlSession.getMapper(UserMapper.class);
                 List<User> userList = mapper.getUserList();
                 //方式二：不建议使用
             List<User> userList = sqlSession.selectList("com.kuang.dao.UserDao.getUserList");
     
                 for (User user : userList) {
                     System.out.println(user);
                 }
                 }catch(Exception e) {
                     e.printStackTrace();
                 }finally{
                     //关闭sqlsession
                     sqlSession.close();
                 }
         }
  ``` 
        
 
   select
   ```
  <select id="getUserId" resultType="com.kuang.pojo.User">
        select * from mybatis.user where id = #{id};
    </select>
```

   insert
   ```
<insert id="addUser" parameterType="com.kuang.pojo.User">
        insert into mybatis.user(id, name, pwd) values (#{id},#{name},#{pwd});
    </insert>
```

update 
```
<update id="updateUser" parameterType="com.kuang.pojo.User">
        update mybatis.user set `name`=#{name}, `pwd`=#{pwd} where id = #{id};
    </update>
```

delete
```
<delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id = #{id};
    </delete>
```

###注意点：update, delete, insert需要提交事物
```
sqlSession.commit();
```
###读错，从下往上读


###万能的Map
假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map
```
    <insert id="addUser2" parameterType="map">
        insert into mybatis.user(id,name,pwd) values (#{userId},#{userName},#{passWord})
    </insert>
```
1. Map传递参数，直接在sql中去除key即可 [parameterType="map"]
2. 对象传递参数，直接在sql中取对象的属性即可[parameterType="Object"]
3. 只有一个基本类型参数的情况下， 可以直接在sql中取到
4. 多个参数用map或者注解


###模糊查询怎么写
1. Java代码执行的时候，传递通配符% %

2. 在sql拼接中使用通配符！

##配置解析
1.核心配置文件
   
   1. mybatis-config.xml
   2. MyBatis的配置文件包含了会深深影响Mybatis行为的设置和属性信息
   ```
The MyBatis configuration contains settings 
and properties that have a dramatic effect on how MyBatis behaves. 
The high level structure of the document is as follows:

configuration
properties
settings
typeAliases
typeHandlers
objectFactory
plugins
environments
environment
transactionManager
dataSource
databaseIdProvider
mappers
properties
```

学会使用配置多套运行环境
Mybatis 默认的事物管理器就是JDBC，连接池：POOLED
## 属性(properties)
我们可以通过properties属性来实现引用配置文件

These are externalizable, substitutable properties that can be configured in a typical Java Properties file instance, 
or passed in through sub-elements of the properties element. For example:

可以直接引入外部文件

可以在其中增加一些属性配置

如果两个文件有一个字段，优先使用外部配置文件的


##类型别名(typealiases)
1.实体类少的时候使用
```
 <typeAlias type="com.kuang.pojo.User" alias="User"/>
```

2.实体类多的时候
```
  <package name="com.kuang.pojo"/>
```

第一种可以自定义别名，但是第二种不行(但是可以在实体类使用注解解决这个问题)
```
@Alias("author")
public class Author {
    ...
}
```
##映射器(mappers)
```
 <mappers>
        方法一:通过 resource
        <mapper resource="com/kuang/dao/UserMapper.xml"/>
        方法二：通过class， 需要放在与UserMapper同包名下
        <mapper class="com.kuang.dao.UserMapper"/>
        方法三：通过package, 需要放在与UserMapper同包名下
        <package name="com.kuang.dao"/>
    </mappers>
```
##生命周期和作用域
生命周期和作用域，是至关重要的，因为错误的使用会导致非常严重的**并发问题**

流程图，查看processon网页

SqlSessionFactoryBuilder:

This class can be instantiated, used and thrown away. There is no need to keep it around once you've created your SqlSessionFactory 

* 一旦创建了SqlSessionFactory， 就不再需要builder了
* 局部变量

SqlSessionFactory:

Once created, the SqlSessionFactory should exist for the duration of your application execution. There should be little or no reason to ever dispose of it or recreate it

* 说白了就是数据库连接池
* 最简单的就是使用单列模式或者静态单列模式


SqlSession:

Each thread should have its own instance of SqlSession. . Instances of SqlSession are not to be shared and are not thread safe. Therefore the best scope is request or method scope

* 连接到连接池的一个请求
* 用完之后需要关闭，避免资源被占用
* 这里的每一个Mapper，就代表一个具体的业务