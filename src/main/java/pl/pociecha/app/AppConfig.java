package pl.pociecha.app;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"pl.pociecha", "pl.pociecha.app", "pl.pociecha.controllers"
, "pl.pociecha.models", "pl.pociecha.repositories"})
@EnableTransactionManagement
public class AppConfig implements WebMvcConfigurer {

    // NOWA KONFIGURACJA

    @Bean
    public LocalContainerEntityManagerFactoryBean createEMF(JpaVendorAdapter adapter, DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        Map<String, String> properties = new HashMap<>();


        // properties zastępujemy DataSource, ale poki co zostawiam hibernate'owe
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/photo_locations?useSSL=false&serverTimezone=UTC");
        properties.put("javax.persistence.jdbc.user", "root");
        properties.put("javax.persistence.jdbc.password", "coderslab");
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");


        // dodana w hibernate
        // properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");

        // properties dla hibernate

        // TODO po dodaniu DataSource znowu nie dziala kodowanie!!!

        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        properties.put("hibernate.connection.characterEncoding", "utf8");
        properties.put("hibernate.connection.CharSet", "utf-8");
        properties.put("hibernate.connection.useUnicode", "true");

        emf.setPersistenceUnitName("PhotoLocationPersistenceUnit");
        emf.setJpaPropertyMap(properties);
        emf.setJpaVendorAdapter(adapter);
        emf.setPackagesToScan("pl.pociecha");
        emf.setDataSource(ds);
        return emf;
    }

    @Bean
    public JpaVendorAdapter createVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);

        return adapter;
    }

    @Bean
    public DataSource createDS() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/photo_locations?useSSL=false&serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("coderslab");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setInitialSize(5);
        return ds;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager(emf);
        return tm;
    }


    // STARA KONFIUGRACJA (AKTUALNA UŻYWA LocalContainerEntityManagerFactoryBean
    // w celu wykorzystania pool'a połączeń

    /*@Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
        emfb.setPersistenceUnitName("PhotoLocationPersistenceUnit");
        return emfb;
    }*/
}
