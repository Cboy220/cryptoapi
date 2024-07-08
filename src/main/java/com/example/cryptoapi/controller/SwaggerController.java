package com.example.cryptoapi.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "swaggerController", urlPatterns = "/swagger/*")
public class SwaggerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getPathInfo();
        if (requestPath == null || requestPath.equals("/")) {
            requestPath = "/index.html";
        }

        try (InputStream resource = getClass().getResourceAsStream("/META-INF/resources/webjars/swagger-ui/3.25.0" + requestPath)) {
            if (resource == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            byte[] buffer = new byte[1024];
            int length;
            while ((length = resource.read(buffer)) != -1) {
                resp.getOutputStream().write(buffer, 0, length);
            }
        }
    }
}
