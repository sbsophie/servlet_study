package com.gn.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

//@WebFilter(servletNames = "receiveDataServlet")
//@WebFilter("/receive/*")
//@WebFilter("/*")
@WebFilter("/receive/data")
public class DataFilter extends HttpFilter implements Filter{

	private static final long serialVersionUID = 1L;

	public DataFilter() {
		super();
		System.out.println("[DataFilter]생성됨");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("[DataFilter]init() : 필터 초기화");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[DataFilter]요청을 가로챔");
		chain.doFilter(req, res);
		System.out.println("[DataFilter]응답을 가로챔");
	}
	
	@Override
	public void destroy() {
		System.out.println("[DataFilter]필터 종료");
	}


	
	
	

}
