package com.example.demo;

import io.undertow.UndertowOptions;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class AppConfig extends WebMvcAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix="my.datasource")
    public DataSource myDataSource() {
        return DataSourceBuilder.create().build();
    }

    //uruchamia HTTP/2 jesli serwerem jest Undertow zamiast Tomcat
    @Bean
    UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.addBuilderCustomizers(
                builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));
        return factory;
    }

//    @Bean
//    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
//        return (container) -> {
//            if (container instanceof TomcatEmbeddedServletContainerFactory) {
//                ((TomcatEmbeddedServletContainerFactory) container)
//                        .addConnectorCustomizers((connector) -> {
//                            connector.addUpgradeProtocol(new Http2Protocol());
//                        });
//            }
//        };
//    }

}
