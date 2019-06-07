package com.mailbackstage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Yan liang
 * @create 2019/6/2
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.mailbackstage.dao")
public class MybatisConfig {

}