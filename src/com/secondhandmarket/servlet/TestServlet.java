package com.secondhandmarket.servlet;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends BaseServlet{

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String p=new String(request.getParameter("classfication").getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(p);
		response.getWriter().println(p);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		doGet(request,response);
		
	}
}
