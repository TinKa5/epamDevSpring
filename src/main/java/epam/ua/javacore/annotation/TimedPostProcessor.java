package epam.ua.javacore.annotation;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TimedPostProcessor implements BeanPostProcessor {
    private static final Logger log = Logger.getLogger(TimedPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        HashSet<String> annotateMethods = new HashSet<>(Arrays.stream(bean.getClass().getMethods())
                .filter(x -> x.isAnnotationPresent(Timed.class))
                .map(Method::getName)
                .collect(Collectors.toList()));

        if (!annotateMethods.isEmpty()) {
            Object resultProxy = Proxy.newProxyInstance(bean.getClass().getClassLoader(),
                    bean.getClass().getInterfaces(), getInvocationHandler(bean, annotateMethods));
            return resultProxy;
        } else {
            return bean;
        }
    }

    private InvocationHandler getInvocationHandler(Object bean, HashSet<String> methods) {
        return (object, method, args) -> {
            if (methods.contains(method.getName())) {
                long before = System.nanoTime();
                Object invokeResult = method.invoke(bean, args);
                long after = System.nanoTime();
                System.out.println(bean.getClass().getName() + "-->" + method.getName() + "-->" + (after - before) + "nanoseconds");
                return invokeResult;
            }
            return method.invoke(bean, args);
        };
    }
}




