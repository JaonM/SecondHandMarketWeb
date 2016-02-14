package com.secondhandmarket.run;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.secondhandmarket.model.Item;
import com.secondhandmarket.service.ItemService;

/**
 * ����Ƿ�����Ԥ����Ʒ����3��û����ɽ���
 * ������ȡ������Ʒ�Ľ���״̬
 * @author lenovo
 *
 */
public class CleanItem {

	public static void main(String [] args) {
		
		int result=cleanItem();
		if(result==1)
			System.out.println("�������");
	}
	
	public static int cleanItem() {
		ApplicationContext context=new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		ItemService itemService=(ItemService)context.getBean("itemService");
		List<Item> itemList=itemService.SortByTime();
		for(Item item:itemList) {
			Date currentDate=new Date(System.currentTimeMillis());
			//����3��δ������ȡ����Ʒ�����ڽ���״̬
			if(item.getOrderTime()!=null&&(currentDate.getTime()-item.getOrderTime().getTime()>259200000)) {
				item.setBuyerId(0);
				item.setIsSend(0);
				item.setOrderTime(null);
				item.setStatus(0);
				itemService.update(item);
			}
		}
		return 1;
	}
}
