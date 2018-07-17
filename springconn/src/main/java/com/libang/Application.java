package com.libang;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *
 * 配置类来替代配置文件
 * @author libang
 * @date 2018/7/17 9:31
 */
//加载的是配置文件的头
@Configuration
//开启自动扫描路径，默认所在的包及其子包开始扫描，可以通过basePackages指定扫描路径
@ComponentScan
//开启aopde 注解模式
@EnableAspectJAutoProxy
//读取配置文件
@PropertySource("config.properties") 
public class Application {
        //获取该类的对象
        @Autowired
        private Environment environment;

        //将返回值注入到spring容器
        @Bean
        public DataSource dataSource(){
            BasicDataSource basicDataSource = new BasicDataSource();
            basicDataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
            basicDataSource.setUrl(environment.getProperty("jdbc.url"));
            basicDataSource.setUsername(environment.getProperty("jdbc.username"));
            basicDataSource.setPassword(environment.getProperty("jdbc.password"));
            return basicDataSource;
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource){

            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);

            return jdbcTemplate;

        }


}
