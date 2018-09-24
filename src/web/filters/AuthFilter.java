package web.filters;

import web.command.enums.ControllerType;
import web.handler.RequestHandler;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/frontController")
public class AuthFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        ControllerType type = RequestHandler.getCommand(req);
        if (ControllerType.ORDERS.equals(type)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            if ((session.getAttribute("Account") == null)) {
                res.sendRedirect(contextPath + "/frontController?command=login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
