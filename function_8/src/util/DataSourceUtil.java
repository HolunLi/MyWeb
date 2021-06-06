package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataSourceUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false&serverTimezone=GMT%2B8";
    private static final String USER = "root";
    private static final String PWD = "haolun";
    private static final String DATABASEDRIVERCLASS = "com.mysql.cj.jdbc.Driver";

    //tomcat-dbcp(以JNDI的方式配置数据源)
    public static DataSource getDataSourceByTomcatDbcp() {
        DataSource dataSource = null;
        try {
            //初始化查找命名空间
            Context context = context = new InitialContext();
            //获取DataSource。根据数据源名称进行定位,java:comp/env是必须加的,后面跟你的DataSource名。
            dataSource = (DataSource) context.lookup("java:comp/env/student");
            return dataSource;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //dbcp(硬编码方式,即直接在类中编写代码来创建数据源,并配置数据源)
    public static DataSource getDataSourceByDbcp() {
        //创建数据源basicDataSource
        BasicDataSource basicDataSource = new BasicDataSource();

        //设置数据库驱动类名
        basicDataSource.setDriverClassName(DATABASEDRIVERCLASS);
        //设置连接数据库的URL
        basicDataSource.setUrl(URL);
        //设置连接数据库的的用户名
        basicDataSource.setUsername(USER);
        //设置连接数据库的密码
        basicDataSource.setPassword(PWD);
        //设置初始化,连接池中的"数据库连接"的数量(可不设置,有默认值)
        basicDataSource.setInitialSize(15);
        //设置连接池中,处于活动状态的数据库连接的最大数量(可不设置,有默认值)
        basicDataSource.setMaxActive(10);
        //设置连接池中,处于空闲状态的数据库连接的最大数量(可不设置,有默认值)
        basicDataSource.setMaxIdle(3);

        return basicDataSource;
    }

    //dbcp(配置文件方式,即将数据源的配置信息以key-value对的形式存放到xx.properties属性文件中，再通过输入流读取)
    public static DataSource getDataSourceByDbcpProperties() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("dbcpconfig.properties");
        Properties properties = new Properties();
        DataSource dataSource = null;
        try {
            properties.load(inputStream); //将dacpconfig.properties属性配置文件中的数据加载的properties集合中
            //使用BasicDataSourceFactory创建数据源
            dataSource = BasicDataSourceFactory.createDataSource(properties);
            return dataSource;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //c3p0(硬编码方式)
    public static DataSource getDataSourceByc3p0() {
        ComboPooledDataSource dataSource = null;
        try {
            //使用无参数的构造方法来创建连接池,说明是硬编码方式
            dataSource = new ComboPooledDataSource();
            //设置数据库驱动类名
            dataSource.setDriverClass(DATABASEDRIVERCLASS);  //setDriverClass() 方法会抛出个异常,需要try-catch
            //设置连接数据库的URL
            dataSource.setJdbcUrl(URL);
            //设置数据库用户名
            dataSource.setUser(USER);
            //设置数据库密码
            dataSource.setPassword(PWD);
            //设置初始化,连接池中的"数据库连接"的数量(可不设置,有默认值)
            dataSource.setInitialPoolSize(15);
            return dataSource;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }

    //c3p0(配置文件方式)
    public static DataSource getDataSourceByc3p0Xml() {
        //会自动从c3p0-config.xml中找name=holun的<name-config>
        ComboPooledDataSource dataSource = new ComboPooledDataSource("holun");
        //注:在已创建好c3p0-config.xml文件的情况下,若想用c3p0-config.xml中的默认配置<default-config>,
        //直接使用无参数的构造方法创建 ComboPooledDataSource对象即可

        return dataSource;
    }
}
