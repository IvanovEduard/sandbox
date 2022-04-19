package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AwareTest implements BeanNameAware, ApplicationContextAware, InitializingBean {
    @Autowired
    private ApplicationContext applicationContext;
    @Value("${test.value:4}")
    private int value;

    public AwareTest () {
        System.out.println("IN Ctor");
    }

    @PostConstruct
    public void init() {
        System.out.println("init @PostConstruct");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware applicationContext");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("interface InitializingBean afterPropertiesSet");
    }
}
