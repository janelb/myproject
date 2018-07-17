package com.libang;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
public class Application {


}
