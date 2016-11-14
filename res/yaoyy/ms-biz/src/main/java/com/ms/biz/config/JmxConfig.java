package com.ms.biz.config;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.jmx.ParentAwareNamingStrategy;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.annotation.AnnotationMBeanExporter;
import org.springframework.jmx.export.naming.ObjectNamingStrategy;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.util.StringUtils;
import org.springframework.context.annotation.MBeanExportConfiguration.SpecificPlatform;

import javax.management.MBeanServer;

/**
 * Created by xiao on 2016/11/14.
 */
@Configuration
@ConditionalOnClass({ MBeanExporter.class })
@ConditionalOnProperty(prefix = "spring.jmx", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JmxConfig implements EnvironmentAware, BeanFactoryAware {

    private RelaxedPropertyResolver propertyResolver;

    private BeanFactory beanFactory;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.jmx.");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean(value = MBeanExporter.class, search = SearchStrategy.CURRENT)
    public AnnotationMBeanExporter mbeanExporter(ObjectNamingStrategy namingStrategy) {
        AnnotationMBeanExporter exporter = new AnnotationMBeanExporter();
        exporter.setRegistrationPolicy(RegistrationPolicy.FAIL_ON_EXISTING);
        exporter.setNamingStrategy(namingStrategy);
        //exporter.setAutodetectModeName("AUTODETECT_NONE");
        String server = this.propertyResolver.getProperty("server", "mbeanServer");
        if (StringUtils.hasLength(server)) {
            exporter.setServer(this.beanFactory.getBean(server, MBeanServer.class));
        }
        return exporter;
    }

    @Bean
    @ConditionalOnMissingBean(value = ObjectNamingStrategy.class, search = SearchStrategy.CURRENT)
    public ParentAwareNamingStrategy objectNamingStrategy() {
        ParentAwareNamingStrategy namingStrategy = new ParentAwareNamingStrategy(
                new AnnotationJmxAttributeSource());
        String defaultDomain = this.propertyResolver.getProperty("default-domain");
        if (StringUtils.hasLength(defaultDomain)) {
            namingStrategy.setDefaultDomain(defaultDomain);
        }
        namingStrategy.setEnsureUniqueRuntimeObjectNames(true);
        return namingStrategy;
    }

    @Bean
    @ConditionalOnMissingBean(MBeanServer.class)
    public MBeanServer mbeanServer() {
        SpecificPlatform platform = SpecificPlatform.get();
        if (platform != null) {
            return platform.getMBeanServer();
        }
        MBeanServerFactoryBean factory = new MBeanServerFactoryBean();
        factory.setLocateExistingServerIfPossible(true);
        factory.afterPropertiesSet();
        return factory.getObject();

    }

}