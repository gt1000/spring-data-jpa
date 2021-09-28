package gt1000.configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class JpaConfig {

    @PersistenceContext
    private EntityManager entityManager;

    private final YAMLConfig yamlConfig;

    // TODO 복호화 해서 사용해야 함
    @Bean
    public DataSource dataSource() {
        YAMLConfig.Datasource datasource = yamlConfig.getDatasource();
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(datasource.getDriverClassName());
        hikariDataSource.setJdbcUrl(datasource.getUrl());
        hikariDataSource.setUsername(datasource.getUsername());
        hikariDataSource.setPassword(datasource.getPassword());
        hikariDataSource.setMaximumPoolSize(datasource.getHikari().getMaximumPoolSize());
        hikariDataSource.setMinimumIdle(datasource.getHikari().getMinimumIdle());
        return hikariDataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("gt1000");
        // spring boot는 아래가 필요 없지 싶은데...
//        entityManagerFactoryBean.setPersistenceUnitName("MariaUnit");
//        entityManagerFactoryBean.setPersistenceXmlLocation("classpath:/META-INF/persistence.xml");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
