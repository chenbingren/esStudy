package com.cbr.study.batisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author My
 */
@SpringBootApplication
/*要加上包扫描，不然报错：Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.cbr.study.batisplusdemo.mapper.UserMapper' */
@MapperScan("com.cbr.study.batisplusdemo")
public class BatisplusdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatisplusdemoApplication.class, args);
	}

}
