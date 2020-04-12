package com.aboutspring.sourcecode.beans;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

public class PreBuildBeanFactory extends DefaultBeanFactory {

	private final Log logger = LogFactory.getLog(getClass());

	private List<String> beanNames = new ArrayList<>();

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
			throws BeanDefinitionRegistException {
		super.registerBeanDefinition(beanName, beanDefinition);
		synchronized (beanNames) {
			beanNames.add(beanName);
		}
	}

	public void preInstantiateSingletons() throws Exception {
		synchronized (beanNames) {
			for (String name : beanNames) {
				BeanDefinition bd = this.getBeanDefinition(name);
				if (bd.isSingleton()) {
					this.doGetBean(name);
					if (logger.isDebugEnabled()) {
						logger.debug("preInstantiate: name=" + name + " " + bd);
					}
				}
			}
		}
	}
}
