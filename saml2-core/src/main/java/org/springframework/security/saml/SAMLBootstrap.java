package org.springframework.security.saml;

import org.opensaml.DefaultBootstrap;
import org.opensaml.xml.ConfigurationException;
import org.opensaml.xml.parse.ParserPool;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.security.saml.parser.ParserPoolHolder;

/**
 * Initialization for SAML library. Is automatically called as part of Spring initialization.
 */
public class SAMLBootstrap implements BeanFactoryPostProcessor {

    @Autowired
    ParserPool parserPool;

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            DefaultBootstrap.bootstrap();
            new ParserPoolHolder(parserPool);
        } catch (ConfigurationException e) {
            throw new BootstrapException("Error invoking OpenSAML bootrap", e);
        }
    }

}
