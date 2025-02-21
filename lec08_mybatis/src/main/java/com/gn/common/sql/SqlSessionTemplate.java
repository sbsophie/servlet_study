package com.gn.common.sql;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	public static SqlSession getSqlSession() {
		SqlSession session = null;
		try {
			// 1. mybatis-config.xml의 설정 정보를 읽어오기
			String path = "/mybatis-config.xml";
			InputStream is = Resources.getResourceAsStream(path);
			
			// 2. SqlSessionFactoryBuilder 객체 생성
			SqlSessionFactoryBuilder sfb
				= new SqlSessionFactoryBuilder();
			
			// 3. SqlSessionFactory 객체 생성
			SqlSessionFactory factory = sfb.build(is);
			
			// 4. SqlSession 객체 생성
			// 1. 매개변수를 안쓰면 AutoCommit을 안한다는 거임
			// true를 넣으면  AutoCommit :o
			// false를 넣으면 AutoCommit :x
			session = factory.openSession(true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return session;
	}
}
