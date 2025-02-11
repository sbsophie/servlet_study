package com.gn.common;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    public SessionListener() {}

    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("==== 세션 객체가 생성되는 시점에 동작함 ====");
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("==== 세션 사용이 불가능한 시점에 동작함 ====");
    }

    public void attributeAdded(HttpSessionBindingEvent event)  { 
    	System.out.println("세션 속성이 추가됬을때 동작함");
    }

    public void attributeRemoved(HttpSessionBindingEvent event)  { 
    	System.out.println("세션 속성이 제거 됬을때 동작함");
    }

    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    	System.out.println("세션 속성이 대체 됬을때 동작함");
    }
	
}
