package com.yc.ordermanage.common.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FilterConfig {

	/**
	 * 注册过滤器
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new AuthorFilter());
		registrationBean.setName("authorFilter");
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/index-page/*");
		urlPatterns.add("/order-page/*");
		urlPatterns.add("/user-page/*");
		urlPatterns.add("/main-page/*");
		registrationBean.setUrlPatterns(urlPatterns);
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
