//package com.aboutspring.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
////AspectJ注解方式
//@Aspect
//@Component
//public class AspectAdviceBeanUseAnnotation {
//
//	// 定义一个全局的Pointcut
//	@Pointcut("execution(* com.aboutspring.aop.*.do*(..))")
//	public void doMethods() {
//	}
//
//	// 定义一个全局的Pointcut
//	@Pointcut("execution(* com.aboutspring.aop.*.service*(..))")
//	public void services() {
//	}
//
//	// 定义一个Before Advice
//	@Before("doMethods() && args(tk,..)")
//	public void before3(String tk) {
//		System.out.println("----------- AspectAdviceBeanUseAnnotation before3  增强  参数tk= " + tk);
//	}
//
//	//环绕增强
//	@Around("services() && args(name,..)")
//	public Object around2(ProceedingJoinPoint pjp, String name) throws Throwable {
//		System.out.println("--------- AspectAdviceBeanUseAnnotation arround2 参数 name=" + name);
//		System.out.println("----------- AspectAdviceBeanUseAnnotation arround2 环绕-前增强 for " + pjp);
//		Object ret = pjp.proceed();
//		System.out.println("----------- AspectAdviceBeanUseAnnotation arround2 环绕-后增强 for " + pjp);
//		return ret;
//	}
//
//	@AfterReturning(pointcut = "services()", returning = "retValue")
//	public void afterReturning(Object retValue) {
//		System.out.println("----------- AspectAdviceBeanUseAnnotation afterReturning 增强 , 返回值为： " + retValue);
//	}
//
//	@AfterThrowing(pointcut = "services()", throwing = "e")
//	public void afterThrowing(JoinPoint jp, Exception e) {
//		System.out.println("----------- AspectAdviceBeanUseAnnotation afterThrowing 增强  for " + jp);
//		System.out.println("----------- AspectAdviceBeanUseAnnotation afterThrowing 增强  异常 ：" + e);
//	}
//
//	@After("doMethods()")
//	public void after(JoinPoint jp) {
//		System.out.println("----------- AspectAdviceBeanUseAnnotation after 增强  for " + jp);
//	}
//
//	/*
//	 * BeanDefinitionRegistryPostProcessor BeanFactoryPostProcessor
//	 * InstantiationAwareBeanPostProcessor Bean实例创建前后 BeanPostProcessor
//	 */
//}
