package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String uri = req.getRequestURI();
        String context = req.getContextPath();

        if (req.getRequestURI().endsWith(".css")) {
            filterChain.doFilter(req, resp);
            return;
        }
        if (uri.endsWith("/logout") || uri.endsWith("/login")) {
            filterChain.doFilter(req, resp);
        } else if (req.getSession().getAttribute("isLogin") != null) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(context + "/login");
        }

    }

    public void destroy() {

    }
}
