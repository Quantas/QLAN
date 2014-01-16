package com.quantasnet.qlan.web.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.quantasnet.qlan.web.config.RootConfig;
import com.quantasnet.qlan.web.config.SecurityConfig;
import com.quantasnet.qlan.web.config.WebConfig;

/**
 * @author andrewlandsverk
 */
public class WebAppInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, SecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(
			final ServletRegistration.Dynamic registration) {
		registration.setInitParameter("dispatchOptionsRequest", "true");
		registration.setAsyncSupported(true);
	}

	@Override
	public void onStartup(final ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext);
	}
}
