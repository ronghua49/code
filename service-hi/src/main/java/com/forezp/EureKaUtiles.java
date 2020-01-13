package com.forezp;

import com.forezp.entity.*;
import com.forezp.service.IclassAttribute;
import feign.Feign;
import feign.Request.Options;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


@RestController
@RequestMapping("EureKaUtiles")
@Api(tags="EureKaUtiles 通过Eureka获取实体类，与实体类操作接口")
@Configuration
public class EureKaUtiles {	
	private Logger logger = LoggerFactory.getLogger(EureKaUtiles.class);
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
    WebApplicationContext applicationContext;
	/**
	 * 标识可被扫描到的实体类所在包，用于讯宜捷前端框架
	 */
	@Autowired
	private XyjconfigProperties xyjconfig;
	/** 
     * 以文件的形式来获取包下的所有Class 
     *  
     * @param packageName 
     * @param packagePath 
     * @param recursive 
     * @param classes 
     */  
    private void findAndAddClassesInPackageByFile(String packageName,  
            String packagePath, final boolean recursive, Set<Class<?>> classes) {  
        // 获取此包的目录 建立一个File  
        File dir = new File(packagePath);  
        // 如果不存在或者 也不是目录就直接返回  
        if (!dir.exists() || !dir.isDirectory()) {  
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");  
            return;  
        }  
        // 如果存在 就获取包下的所有文件 包括目录  
        File[] dirfiles = dir.listFiles(new FileFilter() {  
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)  
            public boolean accept(File file) {  
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));  
            }  
        });  
        // 循环所有文件  
        for (File file : dirfiles) {  
            // 如果是目录 则继续扫描  
            if (file.isDirectory()) {  
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive,classes);  
            } else {  
                // 如果是java类文件 去掉后面的.class 只留下类名  
                String className = file.getName().substring(0,file.getName().length() - 6);  
                try {  
                    // 添加到集合中去  
                    //classes.add(Class.forName(packageName + '.' + className));  
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净  
                  classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));    
                } catch (ClassNotFoundException e) {  
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");  
                    logger.error("[EureKaUtiles:findAndAddClassesInPackageByFile:ClassNotFoundException]", e);
                }  
            }  
        }  
    }
	
	/** 
     * 从包package中获取所有的Class 
     *  
     * @param pack 
     * @return 
     */  
    private Set<Class<?>> getClasses(String pack) {  
          // 第一个class类的集合  
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();  
        // 是否循环迭代  
        boolean recursive = true;  
        // 获取包的名字 并进行替换  
        String packageName = pack;  
        String packageDirName = packageName.replace('.', '/');  
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things  
        Enumeration<URL> dirs;  
        try {  
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);  
            // 循环迭代下去  
            while (dirs.hasMoreElements()) {  
                // 获取下一个元素  
                URL url = dirs.nextElement();  
                // 得到协议的名称  
                String protocol = url.getProtocol();  
                // 如果是以文件的形式保存在服务器上  
                if ("file".equals(protocol)) {  
                    // 获取包的物理路径  
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");  
                    // 以文件的方式扫描整个包下的文件 并添加到集合中  
                    findAndAddClassesInPackageByFile(packageName, filePath,recursive, classes);  
                } else if ("jar".equals(protocol)) {  
                    // 如果是jar包文件  
                    // 定义一个JarFile  
                    JarFile jar;  
                    try {  
                        // 获取jar  
                        jar = ((JarURLConnection)url.openConnection()).getJarFile();  
                        // 从此jar包 得到一个枚举类  
                        Enumeration<JarEntry> entries = jar.entries();  
                        // 同样的进行循环迭代  
                        while (entries.hasMoreElements()) {  
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
                            JarEntry entry = entries.nextElement();  
                            String name = entry.getName();  
                            // 如果是以/开头的  
                            if (name.charAt(0) == '/') {  
                                // 获取后面的字符串  
                                name = name.substring(1);  
                            }  
                            // 如果前半部分和定义的包名相同  
                            if (name.startsWith(packageDirName)) {  
                                int idx = name.lastIndexOf('/');  
                                // 如果以"/"结尾 是一个包  
                                if (idx != -1) {  
                                    // 获取包名 把"/"替换成"."  
                                    packageName = name.substring(0, idx).replace('/', '.');  
                                }  
                                // 如果可以迭代下去 并且是一个包  
                                if ((idx != -1) || recursive) {  
                                    // 如果是一个.class文件 而且不是目录  
                                    if (name.endsWith(".class") && !entry.isDirectory()) {  
                                        // 去掉后面的".class" 获取真正的类名  
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);  
                                        try {  
                                            // 添加到classes  
                                            classes.add(Class.forName(packageName + '.' + className));  
                                        } catch (ClassNotFoundException e) {  
                                            // log.error("添加用户自定义视图类错误 找不到此类的.class文件");  
                                        	logger.error("[EureKaUtiles:getClasses:ClassNotFoundException]", e);
                                        }  
                                    }  
                                }  
                            }  
                        }  
                    } catch (IOException e) {  
                    	logger.error("[EureKaUtiles:getClasses:IOException]", e);
                    }  
                }  
            }  
        } catch (IOException e) {  
        	logger.error("[EureKaUtiles:getClasses:IOException]", e);
        }  
        return classes;  
    }
	/** 
     * "获取Eureka服务器中的所有服务实例,注意Eureka的延时问题
     * @return 
     */  	
	@PostMapping(value="/registerService")
	@ApiOperation(value = "获取Eureka服务器中的所有服务实例", httpMethod = "POST", notes = "获取Eureka服务器中的所有服务实例，注意Eureka的延时问题")
	public List<eureKaEntity> registerService(){
		List<eureKaEntity> result = new ArrayList<eureKaEntity>();
		for(String s: discoveryClient.getServices()){
			List<ServiceInstance> serviceInstances = discoveryClient.getInstances(s);
			for(ServiceInstance si : serviceInstances){
				if(si.getPort() == 443) {
					eureKaEntity entity = new eureKaEntity(si.getHost(),String.valueOf(si.getPort()),si.getServiceId(),si.getUri().toString().replace("http", "https").replace(":"+si.getPort(), ""));
					result.add(entity);
				}else {
					eureKaEntity entity = new eureKaEntity(si.getHost(),String.valueOf(si.getPort()),si.getServiceId(),si.getUri().toString());
					result.add(entity);
				}
			}
		}
       return result;		
	}
	
	/** 
     * 返回当前微服务中指定包下的所有实体类名称
     * @return 返回实体类列表
     */  	
	@RequestMapping(value="/getEntityClass",method={RequestMethod.GET})
	@ApiOperation(value = "返回当前微服务中指定包下的所有实体类名称", httpMethod = "GET", notes = "返回当前微服务中指定包下的所有实体类名称")
	public List<entityName> getEntityClass(){		
		List<entityName> result = new ArrayList<entityName>();		
		boolean insertState = true;
		Set<Class<?>> classesCore = getClasses("com.xyjsoft.core.bean");
		for(Class<?> classInfo : classesCore){
			for (int i = 0; i < result.size(); i++) {
				if(((entityName)result.get(i)).getClassFullName().equals(classInfo.getName())){
					insertState = false;
					break;
				}
			}
			if(insertState){
				result.add(new entityName(classInfo.getName(),classInfo.getSimpleName()));
			}else{
				insertState = true;
			}
		}
		
		Set<Class<?>> classesCoreModel = getClasses("com.xyjsoft.core.model");
		for(Class<?> classInfo : classesCoreModel){
			for (int i = 0; i < result.size(); i++) {
				if(((entityName)result.get(i)).getClassFullName().equals(classInfo.getName())){
					insertState = false;
					break;
				}
			}
			if(insertState){
				result.add(new entityName(classInfo.getName(),classInfo.getSimpleName()));
			}else{
				insertState = true;
			}
		}
		
		Set<Class<?>> classesCoreVoModel = getClasses("com.xyjsoft.core.vo");
		for(Class<?> classInfo : classesCoreVoModel){
			for (int i = 0; i < result.size(); i++) {
				if(((entityName)result.get(i)).getClassFullName().equals(classInfo.getName())){
					insertState = false;
					break;
				}
			}
			if(insertState){
				result.add(new entityName(classInfo.getName(),classInfo.getSimpleName()));
			}else{
				insertState = true;
			}
		}
		/*扫描yaml配置文件中的配置包中的实体类*/
		if(xyjconfig.getScanmodel().size() > 0) {
			for(int i = 0;i < xyjconfig.getScanmodel().size();i++) {
				if(!StringUtils.isBlank(xyjconfig.getScanmodel().get(i))) {
					Set<Class<?>> classesAdmin = getClasses(xyjconfig.getScanmodel().get(i));
					for(Class<?> classInfo : classesAdmin){
						result.add(new entityName(classInfo.getName(),classInfo.getSimpleName()));
					}
				}
			}
		}
		
       return result;		
	}
	/** 
     * 查询实体类属性
     *  
     * @param  classFullName 全类名
     * @return 
     */  
	//@PostMapping(value="/getClassAttr")
	@RequestMapping(value="/getClassAttr",method={RequestMethod.GET})
	@ApiOperation(value = "获取本地实体类属性,如果返回为空数组代表接口错误", httpMethod = "GET", notes = "获取本地实体类属性,如果返回为空数组代表接口错误")
	public List<classInfo> getClassAttr(@ApiParam(name="classFullName",value="带包名实体类名称")@RequestParam("classFullName")String classFullName){
		List<classInfo> results = new ArrayList<classInfo>();
		if(classFullName == null){
			return results;
		}
		if(classFullName.trim().length()<=0){
			return results;
		}
		try {
			Class<?> clazz = Class.forName(classFullName);
//			Field[] tmpfield = clazz.getDeclaredFields(); // 返回属性
			Field[] tmpfield=ClassUtils.getAllDeclaredFields(clazz);// 返回所有属性
			for(Field f : tmpfield){
				if ("serialVersionUID".equals(f.getName())) {
					continue;
				}
				String opeName ="";
				ApiModelProperty annotation = f.getAnnotation(ApiModelProperty.class);
				if(annotation!=null){
					opeName = annotation.value();
					if(StringUtils.isBlank(opeName)){
						opeName= annotation.notes();
					}
				}
				results.add(new classInfo(clazz.getSimpleName(),f.getName(),f.getType().getSimpleName(),opeName));
			}
			return results;
		} catch (ClassNotFoundException e) {
			logger.error("[EureKaUtiles:getClassAttr:ClassNotFoundException]", e);
			return results;
		}	
	}



	/** 
     * "获取指定微服务中的实体类属性
     * @param pack 
     * @return 
     */  	
	@GetMapping(value="/getClassAttribute")
	@ApiOperation(value = "获取远程，指定微服务中的实体类属性", httpMethod = "GET", notes = "获取远程，指定微服务中的实体类属性，目前不支持服务ID调用远程服务接口")
	public List<classInfo> getClassAttribute(@ApiParam(name="servceId",value="微服务URL")@RequestParam("servceId") String servceId,
											 @ApiParam(name="classFullName",value="带包名实体类名称")@RequestParam("classFullName")String classFullName){
		IclassAttribute ca = (IclassAttribute) Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
				.options(new Options(1000,3500))
				.retryer(new Retryer.Default(5000,5000,3))
				.target(IclassAttribute.class,servceId);
		return ca.getClassAttr(classFullName);
	}
	
	/** 
     * 获取eureka中所有微服务的指定实体类名称，此微服务必须依赖“xyj-core.jar”核心包
     * 注意需要处理通过服务ID调用，现在还没有处理负载均衡
     * @return 
     */  	
	@GetMapping(value = "/getAllEntityClass")
	@ApiOperation(value = "获取eureka中所有微服务的指定实体类名称，此微服务必须依赖“xyj-core.jar”核心包", httpMethod = "GET", notes = "获取eureka中所有微服务的指定实体类名称，此微服务必须依赖“xyj-core.jar”核心包")
	public List<entityInfo> getAllEntityClass(){
		List<entityInfo> rList = new ArrayList<entityInfo>();
		List<eureKaEntity> listService = registerService();
		for(eureKaEntity ls: listService){
			try {
				IclassAttribute ca = (IclassAttribute) Feign.builder()
		                .encoder(new JacksonEncoder())
		                .decoder(new JacksonDecoder())
						.options(new Options(1000,3500))
						.retryer(new Retryer.Default(5000,5000,3))
						.target(IclassAttribute.class,ls.getUri());
				List<entityName> tmpList = ca.getEntityClass();
				for(entityName eName : tmpList){
					rList.add(new entityInfo(ls.getServiceId(),eName.getClassFullName(),eName.getclassName(),ls.getUri()));
				}
			}catch(Exception e) {
				logger.error("[EureKaUtiles:getAllEntityClass]微服务不可达", e);
			}
		}
		return rList;
	}
	
	/** 
	 * 获取eureka中所有微服务的指定实体类名称，此微服务必须依赖“xyj-core.jar”核心包
	 * 注意需要处理通过服务ID调用，现在还没有处理负载均衡
	 * @return 
	 */  	
	@GetMapping(value = "/getAllEntityURL")
	@ApiOperation(value = "获取所有URL", httpMethod = "GET", notes = "获取所有URL，此微服务必须依赖“xyj-core.jar”核心包")
	public HttpResult getAllEntityURL(){
		List<XyjControllerURL> rList = new ArrayList<XyjControllerURL>();
		List<eureKaEntity> listService = registerService();
		for(eureKaEntity ls: listService){
			try {
				IclassAttribute ca = (IclassAttribute) Feign.builder()
						.encoder(new JacksonEncoder())
						.decoder(new JacksonDecoder())
						.options(new Options(1000,3500))
						.retryer(new Retryer.Default(5000,5000,3))
						.target(IclassAttribute.class,ls.getUri());
				List<XyjControllerURL> list  = ca.getAllURL();
				for (XyjControllerURL xyjControllerURL : list) {
					if("disposeApply".equals(xyjControllerURL.getMethodname())) {
						xyjControllerURL.setServer(ls.getServiceId());
						if(xyjControllerURL.getClassurl() == null) {
							xyjControllerURL.setClassurl("");
						}else{
							if(!xyjControllerURL.getClassurl().startsWith("/")) {
								xyjControllerURL.setClassurl("/" + xyjControllerURL.getClassurl());
							}
						}
						if(xyjControllerURL.getMethodurl() == null) {
							xyjControllerURL.setMethodurl("");
						}
						xyjControllerURL.setCallurl("http://" + ls.getHost() +":"+ls.getPort() + xyjControllerURL.getMethodurl());
						xyjControllerURL.setServerurl("http://" + ls.getServiceId() + xyjControllerURL.getMethodurl());
						rList.add(xyjControllerURL);
					}
				}
			}catch(Exception e) {
				logger.error("[EureKaUtiles:getAllEntityClass]微服务不可达", e);
			}
		}
		return HttpResult.ok(rList);
	}

    @RequestMapping(value = "/getAllURL", method = RequestMethod.GET)
	@ApiOperation(value = "获取所有URL", httpMethod = "GET", notes = "获取所有URL，此微服务必须依赖“xyj-core.jar”核心包")
    public List<XyjControllerURL> getAllURL() {
        List<XyjControllerURL> resultList = new ArrayList<XyjControllerURL>();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            XyjControllerURL xyjControllerURL = new XyjControllerURL();
            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();
            // 类名
            xyjControllerURL.setClassname(handlerMethod.getMethod().getDeclaringClass().getName());
            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    xyjControllerURL.setClassdesc(api.tags()[0]);
                } else if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    if (null != requestMapping.value() && requestMapping.value().length > 0) {
                    	//类URL
                    	xyjControllerURL.setClassurl(requestMapping.value()[0]);
                    }
                }
            }
            // 方法名
            xyjControllerURL.setMethodname(handlerMethod.getMethod().getName());
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            if (annotations != null) {
                // 处理具体的方法信息
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ApiOperation) {
                        ApiOperation methodDesc = (ApiOperation) annotation;
                        String desc = methodDesc.value();
                        //接口描述
                        xyjControllerURL.setMethoddesc(desc);
                    }
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
            	//请求URL
            	xyjControllerURL.setMethodurl(url);
            }
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
            	//请求方式：POST/PUT/GET/DELETE
            	xyjControllerURL.setRequesttype(requestMethod.toString());
            }
            resultList.add(xyjControllerURL);
        }
        return resultList;
    }
	
}
