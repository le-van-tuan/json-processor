package vn.evolus.controller;

import javax.servlet.*;
import java.io.IOException;

public class GatewayServlet implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("hihi");
        chain.doFilter(request, response);
    }
}
