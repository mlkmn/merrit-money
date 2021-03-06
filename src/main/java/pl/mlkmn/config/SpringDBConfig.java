package pl.mlkmn.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.mlkmn.dao.UserDao;
import pl.mlkmn.dao.UserDaoImpl;
import pl.mlkmn.enums.ApplicationVariable;
import pl.mlkmn.enums.DataSourceProperty;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class SpringDBConfig {
    
    @Autowired
    @Bean(name = "dataSource")
    public DataSource getDataSource() throws IOException {
        BasicDataSource dataSource = new BasicDataSource();
        Properties dbProperties = loadProperties();
        
        dataSource.setDriverClassName(dbProperties.getProperty(DataSourceProperty.DRIVER_CLASS_NAME.getName()));
        dataSource.setUrl(dbProperties.getProperty(DataSourceProperty.URL.getName()));
        dataSource.setUsername(dbProperties.getProperty(DataSourceProperty.USERNAME.getName()));
        dataSource.setPassword(dbProperties.getProperty(DataSourceProperty.PASSWORD.getName()));

        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws IOException {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.scanPackages(ApplicationVariable.MODEL_PACKAGE.getName());
        sessionBuilder.addProperties(getHibernateProperties());
        return sessionBuilder.buildSessionFactory();
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "userDao")
    public UserDao getUserDao(SessionFactory sessionFactory) {
        return new UserDaoImpl(sessionFactory);
    }

    private Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        InputStream propertiesFile = getClass().getClassLoader().getResourceAsStream("db.properties");
        if (propertiesFile == null) {
            propertiesFile = getClass().getClassLoader().getResourceAsStream("db.properties.default");
        }
        properties.load(propertiesFile);
        return properties;
    }

    private Properties getHibernateProperties() throws IOException {
        Properties properties = new Properties();
        Properties dbProperties = loadProperties();
        properties.put("hibernate.dialect", dbProperties.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", dbProperties.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", dbProperties.getProperty("hibernate.format_sql"));
        return properties;
    }

}
