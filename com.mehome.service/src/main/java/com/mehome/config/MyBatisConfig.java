/**
 * pajk.com Inc.
 * Copyright (c) 2014-2015 All Rights Reserved.
 */
package com.mehome.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * mybatis配置
 *
 * @author pengfei
 * @version v 0.1 15/5/6 16:28 aaronyue Exp $$
 */
@Configuration
@AutoConfigureAfter({DruidDataSourceConfig.class})
@MapperScan(value = "com.mehome.dao", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class MyBatisConfig {
    @Autowired
    @Qualifier("MeHomeServiceDataSource")
    public DruidDataSource druidDataSource;
    @Value(value = "classpath:sqlmap/*.xml")
    private Resource[] mapperLocations;

    @Bean(autowire = Autowire.BY_NAME)
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate template = new TransactionTemplate();
        return template;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("MeHomeServiceDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(autowire = Autowire.BY_NAME)
    public SqlSessionFactoryBean sqlSessionFactory() {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(druidDataSource);
        ssfb.setMapperLocations(mapperLocations);
        ssfb.setTypeAliasesPackage("com.mehome.domain");
        return ssfb;
    }
}
