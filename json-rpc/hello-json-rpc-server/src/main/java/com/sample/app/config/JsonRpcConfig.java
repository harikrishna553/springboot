package com.sample.app.config;

import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.sample.app.service.HelloService;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class JsonRpcConfig {

    @Bean
    public ServletRegistrationBean<?> jsonRpcServlet(HelloService helloService) {
        JsonRpcServer server = new JsonRpcServer(helloService, HelloService.class);

        HttpServlet servlet = new HttpServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp)
                    throws RuntimeException {
                try {
                    server.handle(req, resp);
                } catch (Exception e) {
                    throw new RuntimeException("Error handling JSON-RPC request", e);
                }
            }
        };

        return new ServletRegistrationBean<>(servlet, "/api");
    }
}