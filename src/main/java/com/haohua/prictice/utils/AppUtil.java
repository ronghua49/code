package com.haohua.prictice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class AppUtil
  implements ApplicationContextAware
{
  protected static final Logger LOGGER = LoggerFactory.getLogger(AppUtil.class);
  private static ApplicationContext context;

  public void setApplicationContext(ApplicationContext _context)
    throws BeansException
  {
    context = _context;
  }

  public static ApplicationContext getApplicaitonContext()
  {
    return context;
  }

  public static Object getBean(String beanId)
  {
    try
    {
      return context.getBean(beanId);
    }
    catch (Exception ex) {
      LOGGER.debug("getBean:" + beanId + "," + ex.getMessage());
    }
    return null;
  }

  public static <T> T getBean(Class<T> beanClass)
  {
    try
    {
      return context.getBean(beanClass);
    }
    catch (Exception ex) {
      LOGGER.debug("getBean:" + beanClass + "," + ex.getMessage());
    }
    return null;
  }

  public static List<Class> getImplClass(Class clazz)
    throws ClassNotFoundException
  {
    List list = new ArrayList();

    Map map = context.getBeansOfType(clazz);
    for (Iterator localIterator = map.values().iterator(); localIterator.hasNext(); ) { Object obj = localIterator.next();
      String name = obj.getClass().getName();
      int pos = name.indexOf("$$");
      if (pos > 0) {
        name = name.substring(0, name.indexOf("$$"));
      }
      Class cls = Class.forName(name);

      list.add(cls);
    }
    return list;
  }

  public static Map<String, Object> getImplInstance(Class clazz)
    throws ClassNotFoundException
  {
    Map map = context.getBeansOfType(clazz);
    return map;
  }



  public static void publishEvent(ApplicationEvent event)
  {
    if (context != null)
      context.publishEvent(event);
  }

  public static void publishEvent(Object var)
  {
    if (context != null)
      context.publishEvent(var);
  }

  public static String getClasspath()
  {
    String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String rootPath = "";

    if ("\\".equals(File.separator)) {
      rootPath = classPath.substring(1);
      rootPath = rootPath.replace("/", "\\");
    }

    if ("/".equals(File.separator)) {
      rootPath = classPath.substring(1);
      rootPath = rootPath.replace("\\", "/");
    }
    return rootPath;
  }
  
	public static String getDataSourceName() {
		Object bean = getBean("pageHelperProperties");
		return ((Properties)bean).getProperty("helperDialect");
	}
  
}