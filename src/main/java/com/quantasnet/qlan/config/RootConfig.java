package com.quantasnet.qlan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;

@Configuration
@EnableScheduling
@EnableAspectJAutoProxy
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan(value = "com.quantasnet.qlan", excludeFilters = {
		@ComponentScan.Filter(value = Controller.class),
		@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebConfig.class) })
public class RootConfig {
	// nothing here yet, let's try to keep it that way
}