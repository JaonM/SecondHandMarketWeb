package com.secondhandmarket.dao.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.secondhandmarket.service.ItemService;

public class Test {

	public static void main(String [] args) {
		ApplicationContext context=new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		 
		ItemService itemDao=(ItemService)context.getBean("itemService");
		
//		System.out.println(itemDao.findByClassfication("ÊýÂëµçÆ÷", "µçÄÔ").get(1).getUploadTime().getTime());
		String s1="0.9.2";
		String s2="0.9.1";
//		System.out.println(itemDao.findByName("errete"));
		System.out.println(itemDao.findByName("·³·³·³").getOrderTime().getTime());
		System.out.println(System.currentTimeMillis()-itemDao.findByName("·³·³·³").getOrderTime().getTime());
	}
}
