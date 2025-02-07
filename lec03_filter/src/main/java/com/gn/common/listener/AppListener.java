package com.gn.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener
public class AppListener implements ServletContextListener{
	
	public AppListener() {}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 종료되는 시점에 어떤일이 일어났으면 좋겠는지 적어주는 곳
		System.out.println("===== 웹 어플리케이션 종료 =====");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 시작되는 시점에 어떤일이 일어났으면 좋겠는지 적어주는 곳
		System.out.println("===== 웹 어플리케이션 시작 =====");
		
	}
	
	
	
}
