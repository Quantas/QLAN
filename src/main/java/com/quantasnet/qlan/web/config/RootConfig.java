package com.quantasnet.qlan.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(value = "com.quantasnet.qlan", excludeFilters = { @ComponentScan.Filter(value = Controller.class), @ComponentScan.Filter(value = Configuration.class) })
public class RootConfig {
	// nothing here yet
}
