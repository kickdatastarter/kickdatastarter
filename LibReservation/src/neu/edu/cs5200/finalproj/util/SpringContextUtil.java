package neu.edu.cs5200.finalproj.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContextUtil {
	private static ApplicationContext context;

	@SuppressWarnings("unchecked")
	public static<T> T getService(String beanName){
		if(context == null) {
			context = new ClassPathXmlApplicationContext("spring.xml");
		}
		return (T)context.getBean(beanName);
	}
	
}
