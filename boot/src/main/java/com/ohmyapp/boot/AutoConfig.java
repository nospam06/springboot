package com.ohmyapp.boot;

import com.ohmyapp.boot.api.MyProxy;
import com.ohmyapp.boot.service.MyProxyImpl;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by lip on 5/16/2017.
 * bean definition
 */
@ComponentScan
public class AutoConfig {
    @Bean(name = "proxy1")
    public MyProxy proxy1(BeanFactory beanFactory) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setBeanFactory(beanFactory);
        proxyFactoryBean.setTarget(new MyProxyImpl());
        proxyFactoryBean.setInterceptorNames("before", "success", "exception");
        return (MyProxy) proxyFactoryBean.getObject();
    }
}
