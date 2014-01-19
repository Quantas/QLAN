package com.quantasnet.qlan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "com.quantasnet.qlan", excludeFilters = {
		@ComponentScan.Filter(value = Controller.class),
		@ComponentScan.Filter(value = Configuration.class) })
@Import({ DataConfig.class })
@EnableScheduling
public class RootConfig {
	// nothing here yet
}
