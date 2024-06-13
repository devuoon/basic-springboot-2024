package com.devuoon.spring02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = { "com.devuoon.spring02.mapper" })
public class MyBatisConfig {
  
}
