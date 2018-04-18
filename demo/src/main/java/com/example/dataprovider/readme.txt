连接到数据库（Connection）、建立操作指令（Statement）、执行查询指令（executeQuery）、获得查询结果（ResultSet）等。

1、驱动程序管理类（DriverManager）
DriverManager类是JDBC的管理类，作用于用户和驱动程序之间。它跟踪在可用的驱动程序，并在数据库和相应驱动程序之间建立连接。另外，DriverManager类也处理诸如驱动程序登陆时间限制及登录和跟踪消息的显示事务。对于简单的应用程序，一般程序员需要在此类中直接使用唯一的方法时DriverManager.getConnection()。该方法将建立与数据库的链接。JDBC允许用户调用DriverManager的方法getDriver()、getDrivers()和registerDriver()及Driver的方法connect().

2、声明类（Statement）
Statement对象用于将SQL语句发送到数据库中。实际上有三种Statement对象，它们都作为在给定链接上执行SQL语句的包容器：Statement、PreparedStatement（它从Statement继承而来）和CallableStatement（它从PreparedStatement继承而来）。它们都专用于发送特定类型的SQL语句：

（1）Statement对象用于执行不带参数的简单的SQL语句；Statement接口提供了执行语句和获取结果的基本方法。

（2）PerparedStatement对象用于执行带或不带IN参数的预编译SQL语句；PeraredStatement接口添加处理IN参数的方法；

（3）CallableStatement对象用于执行对数据库已存储过程的调用；CallableStatement添加处理OUT参数的方法。

Statement提供了许多方法，最常用的方法如下：
（1）execute()方法：运行语句，返回是否有结果集。

（2）executeQuery()方法：运行查询语句，返回ReaultSet对象。

（3）executeUpdata()方法：运行更新操作，返回更新的行数。

（4）addBatch()方法：增加批处理语句。

（5）executeBatch()方法：执行批处理语句。

（6）clearBatch()方法：清除批处理语句。

3、数据库连接类 （Connection）
Connection对象代表与数据库的链接。连接过程包括所执行的SQL语句和在该连接上所返回的结果。一个应用程序可与单个数据库有一个或多个连接，或者可与很多数据库有连接。打开连接与数据库建立连接的标准方法是调用DriverManager.getConnection()方法。

String url="jdbc:mysql://127.0.0.1:3306/imooc";

String user="root";

String password="tiger";

DriverManager.getConnection(url,user,password);

4、结果集合类 （ResultSet）
ResultSet包含符合SQL语句中条件的所有行记录，并且它通过一套get方法（这些get方法可以访问当前行中的不同列）提供了对这些行中数据的访问。ResultSet.next()方法用于移动到ResultSet中的下一行，使下一行成为当前行。

5、JDBC编程步骤

（1）加载驱动程序：Class.forName(driverClass)

加载mysql驱动：Class.forName("com.mysql.jdbc.Driver");

加载oracle驱动：Class.forName("oracle.jdbc.driver.OracleDriver");

（2）获得数据库连接

DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/imooc",user,password);

DriverManager.gerConnection(URL,user,password);

（3）创建Statement对象：conn.createStatement();

（4）向数据库发送SQL命令

（5）处理数据库的返回结果(ResultSet类)
5 DatabaseMetaData
　　DatabaseMetaData是由Connection对象通过getMetaData方法获取而来，主要封装了是对数据库本身的一些整体综合信息，例如数据库的产品名称，数据库的版本号，数据库的URL，是否支持事务等等，能获取的信息比较多，具体可以参考DatabaseMetaData的API文档。

　　以下有一些关于DatabaseMetaData的常用方法：

　　·getDatabaseProductName：获取数据库的产品名称

　　·getDatabaseProductName：获取数据库的版本号

　　·getUserName：获取数据库的用户名

　　·getURL：获取数据库连接的URL

　　·getDriverName：获取数据库的驱动名称

　　·driverVersion：获取数据库的驱动版本号

　　·isReadOnly：查看数据库是否只允许读操作

　　·supportsTransactions：查看数据库是否支持事务.

ParameterMetaData
　　ParameterMetaData是由PreparedStatement对象通过getParameterMetaData方法获取而来，主要是针对PreparedStatement对象和其预编译的SQL命令语句提供一些信息，比如像”insert into account(id,name,money) values(?,?,?)”这样的预编译SQL语句，ParameterMetaData能提供占位符参数的个数，获取指定位置占位符的SQL类型等等，功能也比较多，这里不列举完，详细请看有关ParameterMetaData的API文档。

　　以下有一些关于ParameterMetaData的常用方法：

　　·getParameterCount：获取预编译SQL语句中占位符参数的个数

 

　　在我看来，ParameterMetaData对象能用的只有获取参数个数的getParameterCount()方法。

　　注意：ParameterMetaData许多方法MySQL并不友好支持，比如像获取指定参数的SQL类型的getParameterType方法，如果数据库驱动连接URL只是简单的“jdbc:mysql://localhost:3306/jdbcdemo”那么MyEclipse会抛出SQLException异常，必须要将URL修改为“jdbc:mysql://localhost:3306/jdbcdemo?generateSimpleParameterMetadata=true”才行。但是像getParameterType等等与其他的方法也没多好用，因为如下面的例子，这些方法好像只会将所有的参数认为是字符串(VARCHAR)类型。

ResultSetMetaData
　　ResultSetMetaData是由ResultSet对象通过getMetaData方法获取而来，主要是针对由数据库执行的SQL脚本命令获取的结果集对象ResultSet中提供的一些信息，比如结果集中的列数、指定列的名称、指定列的SQL类型等等，可以说这个是对于框架来说非常重要的一个对象。关于该结果集元数据对象的其他具体功能和方法请查阅有关ResultSetMetaData的API文档。

以下有一些关于ResultSetMetaData的常用方法：

　　·getColumnCount：获取结果集中列项目的个数

　　·getColumnType：获取指定列的SQL类型对应于Java中Types类的字段

　　·getColumnTypeName：获取指定列的SQL类型

　　·getClassName：获取指定列SQL类型对应于Java中的类型(包名加类名)


1、事务的特点
（1）原子性：事务是一个完整的操作

（2）一致性：当食物完成时，数据必须处于一致状态

（3）隔离性：对数据进行修改的所有兵法事务彼此隔离

（4）永久性：事务完成后，它对数据库的修改被永久的保存

2、JDBC对事务管理的支持
（1）我们通过提交Commit()或是回退rollback()来管理事务的操作

（2）事务操作默认是自动提交的

（3）可以通过调用setAutoCommit（false）来禁止自动提交。
