package com.secondhandmarket.run;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.secondhandmarket.model.Item;
import com.secondhandmarket.service.ItemService;

/**
 * 检测是否有已预订物品超过3天没有完成交易
 * 若有则取消该物品的交易状态
 * @author lenovo
 *
 */
public class CleanItem {

	public static void main(String [] args) {
		
		int result=cleanItem();
		if(result==1)
			System.out.println("操作完成");
	}
	
	public static int cleanItem() {
		ApplicationContext context=new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
		ItemService itemService=(ItemService)context.getBean("itemService");
		List<Item> itemList=itemService.SortByTime();
		for(Item item:itemList) {
			Date currentDate=new Date(System.currentTimeMillis());
			//超过3天未处理则取消物品的正在交易状态
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
