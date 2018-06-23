package com.yc.ordermanage.common.filter;

import com.yc.ordermanage.user.domain.UserVO;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthorFilter implements Filter {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthorFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		logger.info("--login auth--");
//		logger.info(httpRequest.getRequestURI());

		String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		if (url.startsWith("/") && url.length() > 1) {
			url = url.substring(1);
		}

		HttpSession session = httpRequest.getSession();
		UserVO userVO = (UserVO) session.getAttribute("currentUser");
		if ("login-page".equals(url)) {
			chain.doFilter(httpRequest, httpResponse);
		} else if (userVO == null) {
			// String basePath = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort();
			httpResponse.sendRedirect("loginerror-page");
		} else {
			session.setAttribute("currentUser", userVO);
			chain.doFilter(httpRequest, httpResponse);
		}

	}

	@Override
	public void destroy() {

	}
}
