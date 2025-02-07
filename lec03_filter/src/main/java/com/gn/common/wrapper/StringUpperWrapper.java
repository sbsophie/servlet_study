package com.gn.common.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class StringUpperWrapper extends HttpServletRequestWrapper{

	public StringUpperWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		//return super.getParameter(name.toUpperCase());라고 괄호 안쪽에 들어가면 key값을 대문자로 한다는 소리임
		// return super.getParameter(name)이게 value값을 가지고 오는 것이기 때문에 이 바깥에 붙여줘야 value값을 대문자로 반환함
		return super.getParameter(name).toUpperCase();
	}
	
	
	

}
