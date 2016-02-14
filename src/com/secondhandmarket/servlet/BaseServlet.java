package com.secondhandmarket.servlet;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.secondhandmarket.model.Item;
import com.secondhandmarket.service.ItemService;

public class BaseServlet extends HttpServlet {

	protected ApplicationContext context;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("init");
		context=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		MarketScheduler scheduler=new MarketScheduler();
		scheduler.repeate(new ClearItem(), 0, 1000*60*60*24);
//		scheduler.repeate(new ClearItem(), 0, 1000*60);
	}
	
	protected  ApplicationContext getContext() {
		return context;
	}
	
	/**
	 * 内部类封装定时任务执行器
	 * @author maqiang
	 *
	 */
	private class MarketScheduler {
		
		ScheduledThreadPoolExecutor executor=new ScheduledThreadPoolExecutor(10);
		
		public void repeate(Runnable task,long delay,long period) {
			executor.scheduleAtFixedRate(task, delay, period, TimeUnit.MILLISECONDS);
		}
	}
	
	private class ClearItem implements Runnable {
		@Override
		public void run() {
//			ApplicationContext context=new FileSystemXmlApplicationContext("WebContent/WEB-INF/applicationContext.xml");
			ItemService itemService=(ItemService)getContext().getBean("itemService");
			List<Item> itemList=itemService.SortByTime();
			for(Item item:itemList) {
//				Date currentDate=new Date(System.currentTimeMillis());
				//超过1天未处理则取消物品的正在交易状态
				if(item.getOrderTime()!=null&&(System.currentTimeMillis()-item.getOrderTime().getTime()>86400000)) {
					item.setBuyerId(0);
					item.setIsSend(0);
					item.setOrderTime(null);
					item.setStatus(0);
					itemService.update(item);
				}
			}
		}
	}
}
