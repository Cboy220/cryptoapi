package com.example.cryptoapi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Jersey servlet
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/api/*");
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.example.cryptoapi.controller");
        jerseyServlet.setInitParameter("javax.ws.rs.Application", "com.example.cryptoapi.config.SwaggerConfig");

        // Swagger UI kaynakları için servlet
        ServletHolder swaggerServlet = context.addServlet(DefaultServlet.class, "/swagger-ui/*");
        swaggerServlet.setInitParameter("resourceBase", "src/main/resources/META-INF/resources/webjars/swagger-ui/");
        swaggerServlet.setInitParameter("pathInfoOnly", "true");

        // Webjars kaynakları için servlet
        ServletHolder webjarsServlet = context.addServlet(DefaultServlet.class, "/webjars/*");
        webjarsServlet.setInitParameter("resourceBase", "src/main/resources/META-INF/resources/webjars/");
        webjarsServlet.setInitParameter("pathInfoOnly", "true");

        // OpenAPI JSON kaynakları için servlet
        ServletHolder apiDocsServlet = context.addServlet(DefaultServlet.class, "/api-docs/*");
        apiDocsServlet.setInitParameter("resourceBase", "src/main/resources/api/");
        apiDocsServlet.setInitParameter("pathInfoOnly", "true");

        server.start();
        server.join();
    }
}
